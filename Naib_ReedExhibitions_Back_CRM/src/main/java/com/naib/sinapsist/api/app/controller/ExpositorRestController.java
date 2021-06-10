package com.naib.sinapsist.api.app.controller;

import com.naib.sinapsist.api.app.models.entity.DetalleUsuario;
import com.naib.sinapsist.api.app.models.entity.EvidenciaPorcentajeArmado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.naib.sinapsist.api.app.models.entity.Expositor;
import com.naib.sinapsist.api.app.models.service.IEvidenciaPorcentajeArmado;
import com.naib.sinapsist.api.app.models.service.IExpositorService;
import com.naib.sinapsist.api.app.utilities.Environment;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = { Environment.CORS_ANGULAR_DEFAULT, Environment.CORS_ORIGIN_PROD })
@RequestMapping("/api")
public class ExpositorRestController {

	@Autowired
	private IExpositorService expositorService;


	@Autowired
	private IEvidenciaPorcentajeArmado evidenciaArmadoService;


	@Secured("ROLE_FM")
	@GetMapping("/expositor")
	public List<Expositor> listaExpositor() {
		return expositorService.findAll();
	}

	@GetMapping("/detalleExpositor/evidencias/{id}")
	public List<EvidenciaPorcentajeArmado> evidenciasArmado(@PathVariable int id) {
		return evidenciaArmadoService.findAllEvidenciasById(id);
	}

	@PostMapping("/detalleExpositor/uploadEvidenciaArmado")
	public ResponseEntity<?> uploadEvidenciaArmado(@RequestParam("foto") MultipartFile archivo,
			@RequestParam("idStandRefe") int idStandRefe, @RequestParam("idDetalleUsuario") int idDetalleUsuario,
			@RequestParam("porcentaje") String porcentaje) {
		Map<String, Object> response = new HashMap();
		EvidenciaPorcentajeArmado registro = new EvidenciaPorcentajeArmado();
		if (!archivo.isEmpty()) {
			String resultado = evidenciaArmadoService.subirEvidencia(archivo);
			if (resultado.contains(":")) {
				response.put("mensaje", "Error al subir la imagen al servidor");
				response.put("error", resultado);
				return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				registro.setImg(resultado);
				registro.setPorcentajeArmado(porcentaje);
				registro.setIdStandReferencia(idStandRefe);
				registro.setIdDetalleUsuario(idDetalleUsuario);
				registro.setRegistro(new Date());
				evidenciaArmadoService.guardarEvidenciaArmado(registro);
				response.put("evidencia", registro);
				response.put("mensaje", resultado);
			}
		}
		return new ResponseEntity(response, HttpStatus.CREATED);
	}

	@GetMapping("/detalleExpositor/showEvidenciaArmado/{nombreFoto:.+}")
	public ResponseEntity<Resource> showEvidenciaArmado(@PathVariable String nombreFoto) {
		Path rutaArchivo = Environment.GET_SRC_EVIDENCIA_ARMADO(nombreFoto);
		Resource recurso = null;
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		/*
		 * if (!recurso.exists() || !recurso.isReadable()) { rutaArchivo =
		 * Paths.get("src/main/resources/static/images").resolve("user.png").
		 * toAbsolutePath(); try { recurso = new UrlResource(rutaArchivo.toUri()); }
		 * catch (MalformedURLException e) { e.printStackTrace(); } throw new
		 * RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto); }
		 */
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity(recurso, cabecera, HttpStatus.OK);
	}


	@GetMapping("/detalleExpositor/showEvidenciaServicio/{nombreFoto:.+}")
	public ResponseEntity<Resource> showEvidenciaServicio(@PathVariable String nombreFoto) {
		Path rutaArchivo = Environment.GET_SRC_EVIDENCIA_SERVICIO(nombreFoto);
		Resource recurso = null;
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity(recurso, cabecera, HttpStatus.OK);
	}


}
