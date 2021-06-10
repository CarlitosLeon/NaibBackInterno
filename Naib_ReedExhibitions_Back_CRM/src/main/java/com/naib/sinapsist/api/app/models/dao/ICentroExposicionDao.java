package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.CentroExposicion;

public interface ICentroExposicionDao extends CrudRepository<CentroExposicion, Integer>{

	public CentroExposicion save (CentroExposicion centro);
	
	public List<CentroExposicion> findAll();
}
