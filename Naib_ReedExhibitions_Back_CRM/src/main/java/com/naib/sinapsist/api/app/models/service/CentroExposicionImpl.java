package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naib.sinapsist.api.app.models.dao.ICentroExposicionDao;
import com.naib.sinapsist.api.app.models.entity.CentroExposicion;

@Service
public class CentroExposicionImpl implements ICentroExposicionService{
	
	@Autowired
	private ICentroExposicionDao centroExp;
	
	@Override
	public CentroExposicion save(CentroExposicion centro) {
		return centroExp.save(centro);
	}

	@Override
	public List<CentroExposicion> findAll() {
		return centroExp.findAll();
	}

}
