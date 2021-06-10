package com.naib.sinapsist.api.app.models.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.naib.sinapsist.api.app.models.dao.IWhatsAppCarteraDao;
import com.naib.sinapsist.api.app.models.dao.IWhatsAppContactoDao;
import com.naib.sinapsist.api.app.models.entity.WhatsAppCartera;
import com.naib.sinapsist.api.app.models.entity.WhatsAppContacto;
import com.naib.sinapsist.api.app.utilities.Environment;

@Service
public class WhatsAppContactoServiceImpl implements IWhatsAppContactoService {
	@Autowired
	private IWhatsAppContactoDao WhatsAppContactoDa;

	@Autowired
	private IWhatsAppCarteraDao whatsCarteraDao;

	@Override
	@Transactional(readOnly = true)
	public List<WhatsAppContacto> findAll() {
		return (List<WhatsAppContacto>) WhatsAppContactoDa.findAll();
	}

	@Override
	public List<WhatsAppContacto> getwhatsContacto(int idwEx) {
		return WhatsAppContactoDa.getwhatsContacto(idwEx);
	}

	@Override
	public WhatsAppContacto save(WhatsAppContacto whatsAppContacto) {
		return WhatsAppContactoDa.save(whatsAppContacto);
	}

	@Override
	public String subirPdfContacto(MultipartFile archivo) {
		String nombre = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace("", "");
		Path rutaArchivo = Environment.GET_SRC_CRM(nombre);
		try {
			Files.copy(archivo.getInputStream(), rutaArchivo);
			return nombre;
		} catch (IOException e) {
			return (e.getMessage() + " : " + e.getCause().toString());
		}
	}

	@Override
	public List<WhatsAppContacto> getWhatsProgramados(int idExpo) {
		return WhatsAppContactoDa.getWhatsProgramados(idExpo);
	}

	/////////////
	@Override
	public List<WhatsAppCartera> getWhatsProgramadosCartera(int idC) {
		return whatsCarteraDao.getWhatsProgramadosCarteraById(idC);
	}

}
