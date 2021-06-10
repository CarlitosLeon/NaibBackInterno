package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import com.naib.sinapsist.api.app.models.entity.Evento;

public interface IEventoService {

	public Evento detailUser(int idU, int sts);

	public Evento findById(int idE);
	
	public List<Evento> findAll();
	
	public Evento save(Evento event);

}
