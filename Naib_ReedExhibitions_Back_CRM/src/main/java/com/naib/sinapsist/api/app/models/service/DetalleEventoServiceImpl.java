package com.naib.sinapsist.api.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naib.sinapsist.api.app.models.dao.IDetalleEventoDao;
import com.naib.sinapsist.api.app.models.entity.DetalleEvento;

@Service
public class DetalleEventoServiceImpl implements IDetalleEventoService{
	
	@Autowired
	private IDetalleEventoDao detalleEv;

	@Override
	public DetalleEvento save(DetalleEvento detalleE) {
		return detalleEv.save(detalleE);
	}

}
