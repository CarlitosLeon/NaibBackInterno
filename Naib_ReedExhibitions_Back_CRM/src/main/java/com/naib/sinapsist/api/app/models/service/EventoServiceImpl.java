package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naib.sinapsist.api.app.models.dao.IEventoDao;
import com.naib.sinapsist.api.app.models.entity.Evento;

@Service
public class EventoServiceImpl implements IEventoService {

	@Autowired
	private IEventoDao eventoDao;

	@Override
	@Transactional(readOnly = true)
	public Evento detailUser(int idU, int sts) {
		return eventoDao.detailUser(idU, sts);
	}

	@Override
	@Transactional(readOnly = true)
	public Evento findById(int idE) {
		return eventoDao.findById(idE);
	}

	@Override
	public List<Evento> findAll() {
		return eventoDao.findAll();
	}

	@Override
	public Evento save(Evento event) {
		return eventoDao.save(event);
	}

}
