/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.controller;

import com.naib.sinapsist.api.app.models.entity.CarteraEvento;
import com.naib.sinapsist.api.app.models.entity.EventoCalendario;
import com.naib.sinapsist.api.app.models.entity.ImgEventoCalendario;
import com.naib.sinapsist.api.app.models.entity.ListaCalendario;
import com.naib.sinapsist.api.app.models.entity.SubTareaCalendario;
import com.naib.sinapsist.api.app.models.entity.Usuario;
import com.naib.sinapsist.api.app.models.entity.WhatsAppCartera;
import com.naib.sinapsist.api.app.models.entity.WhatsAppContacto;
import com.naib.sinapsist.api.app.models.service.ICalendarioService;
import com.naib.sinapsist.api.app.models.service.IMapaVentasService;
import com.naib.sinapsist.api.app.models.service.IUsuarioService;
import com.naib.sinapsist.api.app.models.service.IWhatsAppContactoService;
import com.naib.sinapsist.api.app.utilities.Environment;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Synapsist-Serv
 */
@RestController
@CrossOrigin(origins = { Environment.CORS_ANGULAR_DEFAULT, Environment.CORS_ORIGIN_PROD })
@RequestMapping("/calendar")
public class CalendarioController {

	@Autowired
	private ICalendarioService calendarService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IMapaVentasService mapaService;

	@Autowired
	private IWhatsAppContactoService whatsService;

	@PostMapping("/datos/{id}/{idEvento}")
	public ResponseEntity<?> datosCalendarByUsuario(@PathVariable int id, @PathVariable int idEvento) {
		Map<String, Object> response = new HashMap<>();
		try {
			Usuario user = usuarioService.findById(id);
			if (user != null) {
				List<WhatsAppContacto> nuevaListaWContacto = new ArrayList<>();
				List<WhatsAppCartera> nuevaListaWCartra = new ArrayList<>();
				List<ListaCalendario> lst = calendarService.getDatosCalendarioByIdUsusario(user.getId());
				response.put("lstEventoCalendario", lst);
				List<CarteraEvento> cart = mapaService.getCarteraEventoByVendedorAndEvento(user.getId(), idEvento);
				for (int i = 0; i < cart.size(); i++) {
					List<WhatsAppContacto> wc = whatsService.getWhatsProgramados(cart.get(i).getExpositor().getId());
					if (wc.size() > 0)
						nuevaListaWContacto.addAll(wc);
					List<WhatsAppCartera> wca = whatsService.getWhatsProgramadosCartera(cart.get(i).getId());
					if (wca.size() > 0)
						nuevaListaWCartra.addAll(wca);
				}
				response.put("lstWhatsPFromContacto", nuevaListaWContacto);
				response.put("lstWhatsPFromCartera", nuevaListaWCartra);
				return new ResponseEntity(response, HttpStatus.OK);
			} else {
				response.put("mensaje", "El usuario no existe");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/info/guardar/{idUs}")
	public ResponseEntity<?> saveDatosCalendario(@RequestBody ListaCalendario lstCalendario, @PathVariable int idUs) {
		Map<String, Object> response = new HashMap<>();
		ListaCalendario lst;
		try {
			Usuario usuario = usuarioService.findById(idUs);
			if (usuario != null) {
				lstCalendario.setUsuario(usuario);
				lst = calendarService.saveLista(lstCalendario);
				return new ResponseEntity<ListaCalendario>(lst, HttpStatus.OK);
			} else {
				response.put("mensaje", "El usuario no es valido");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/info/update/")
	public ResponseEntity<?> updateDatosCalendario(@RequestBody ListaCalendario listaCalendario) {
		Map<String, Object> response = new HashMap<>();
		ListaCalendario lst = null;
		try {
			ListaCalendario lista = calendarService.getListaCalendario(listaCalendario.getId());
			if (lista != null) {
				lista.setColor(listaCalendario.getColor());
				lista.setIcono(listaCalendario.getIcono());
				lista.setTitulo(listaCalendario.getTitulo());
				lista.setLstEventoCalendario(listaCalendario.getLstEventoCalendario());
				lst = calendarService.saveLista(lista);
				return new ResponseEntity<ListaCalendario>(lst, HttpStatus.OK);
			} else {
				response.put("mensaje", "El usuario no es valido");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("info/update/eventoCalendario/")
	public ResponseEntity<?> updateEstatusEventoCalendario(@RequestBody ListaCalendario listaCalendario) {
		Map<String, Object> response = new HashMap<>();
		try {
			for (EventoCalendario eventoCalendar : listaCalendario.getLstEventoCalendario()) {
				EventoCalendario evento = calendarService.getEventoCalendarioById(eventoCalendar.getId());
				if (evento != null) {
					calendarService.updateEstatusEventoCalendarioById(eventoCalendar.isEstatus(),
							eventoCalendar.getId());
				}
			}
			return new ResponseEntity<ListaCalendario>(listaCalendario, HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
	}

	@PutMapping("info/update/subTareaCalendario/")
	public ResponseEntity<?> updateEstatusSubTareaCalendario(@RequestBody EventoCalendario eventoCalendario) {
		Map<String, Object> response = new HashMap<>();
		try {
			for (SubTareaCalendario subTarea : eventoCalendario.getLstSubTareaCalendario()) {
				SubTareaCalendario subTC = calendarService.getSubTareaById(subTarea.getId());
				if (subTC != null) {
					calendarService.updateEstatusSubTareaCalendarById(subTarea.getEstatus(), subTarea.getId());
				}
			}
			return new ResponseEntity<EventoCalendario>(eventoCalendario, HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/info/turnOff/eventoCalendario/")
	public ResponseEntity<?> deleteEventoCalendario(@RequestBody EventoCalendario eventoCalendar) {
		Map<String, Object> response = new HashMap<>();
		try {
			EventoCalendario evento = calendarService.getEventoCalendarioById(eventoCalendar.getId());
			if (evento != null) {
				calendarService.deleteEventoCalendario(evento.getId());
				for (ImgEventoCalendario imgC : eventoCalendar.getLstImgEventoCalendario()) {
					calendarService.BorrarImagen(imgC);
				}
				return new ResponseEntity<>(evento, HttpStatus.OK);
			} else {
				response.put("mensaje", "El evento no existe");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("info/turnOff/subTareaCalendario/")
	public ResponseEntity<?> deleteSubTareaCalendario(@RequestBody List<SubTareaCalendario> lstSubTareaCalendar) {
		Map<String, Object> response = new HashMap<>();
		try {
			System.out.println("Eliminar datos jeje");
			for (SubTareaCalendario subT : lstSubTareaCalendar) {
				SubTareaCalendario evento = calendarService.getSubTareaById(subT.getId());
				if (evento != null) {
					System.out.println(evento.getId());
					calendarService.deleteSubTareaCalendario(evento.getId());
				}
			}
			return new ResponseEntity<>(lstSubTareaCalendar, HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/upload/uploadFotoEventoCalendario/{idLista}/{idEvento}")
	public ResponseEntity<?> uploadEvidenciaCalendario(@RequestParam("foto") List<MultipartFile> lstArchivo,
			@PathVariable int idLista, @PathVariable int idEvento) {
		Map<String, Object> response = new HashMap();
		List<ImgEventoCalendario> lstImg = new ArrayList<>();
		if (!lstArchivo.isEmpty()) {
			for (MultipartFile archivo : lstArchivo) {
				String resultado = calendarService.subirEvidencia(archivo);
				if (resultado.contains(":")) {
					response.put("mensaje", "Error al subir la imagen al servidor");
					response.put("error", resultado);
					return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					ImgEventoCalendario registro = new ImgEventoCalendario();
					registro.setUrl(resultado);
					lstImg.add(registro);
				}
			}
			ListaCalendario listaC = calendarService.getListaCalendario(idLista);
			for (EventoCalendario evC : listaC.getLstEventoCalendario()) {
				if (evC.getId() == idEvento)
					evC.getLstImgEventoCalendario().addAll(lstImg);

			}
			ListaCalendario finalLstCalendar = calendarService.saveLista(listaC);

			response.put("registro", finalLstCalendar);
		}
		return new ResponseEntity(response, HttpStatus.CREATED);
	}

	@GetMapping("/calendario/calendarTask/{nombreFoto:.+}")
	public ResponseEntity<Resource> showEvidenciaArmado(@PathVariable String nombreFoto) {
		Path rutaArchivo = Environment.GET_SRC_CALENDARIO(nombreFoto);
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

	@DeleteMapping("/calendario/listaCalendario/User/Delete/")
	public ResponseEntity<?> deleteListaCalendario(@RequestParam("idLista") String idLista) {
		Map<String, Object> response = new HashMap<>();
		try {
			ListaCalendario lista = calendarService.getListaCalendario(Integer.parseInt(idLista));
			if (lista != null) {
				for (EventoCalendario evC : lista.getLstEventoCalendario()) {
					for (ImgEventoCalendario img : evC.getLstImgEventoCalendario()) {
						calendarService.BorrarImagen(img);
					}
				}
				calendarService.deleteListaCalendario(Integer.parseInt(idLista));
				response.put("mensaje", "La lista se ha eliminado");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.put("mensaje", "La lista no existe");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
