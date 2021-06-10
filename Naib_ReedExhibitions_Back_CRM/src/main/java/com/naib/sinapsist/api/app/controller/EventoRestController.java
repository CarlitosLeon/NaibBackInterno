package com.naib.sinapsist.api.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naib.sinapsist.api.app.models.service.ICentroExposicionService;
import com.naib.sinapsist.api.app.models.service.IDetalleEventoService;
import com.naib.sinapsist.api.app.models.service.IDetalleUsuarioService;
import com.naib.sinapsist.api.app.models.service.IEventoService;
import com.naib.sinapsist.api.app.models.service.IRolService;
import com.naib.sinapsist.api.app.models.service.IUsuarioService;

@RestController 
@CrossOrigin(origins = { "http://localhost:4200", "*"})
@RequestMapping("/crmEvento")
public class EventoRestController {

	@Autowired
	private IEventoService eventoService;
	
	@Autowired
	private IDetalleEventoService detalleEventService;
	
	@Autowired
	private ICentroExposicionService centroExpoService;
	
	@Autowired
	private IDetalleUsuarioService detalleUsuService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IRolService rolService;
}
