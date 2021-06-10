package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import com.naib.sinapsist.api.app.models.entity.CentroExposicion;

public interface ICentroExposicionService {

public CentroExposicion save (CentroExposicion centro);
	
	public List<CentroExposicion> findAll();
}
