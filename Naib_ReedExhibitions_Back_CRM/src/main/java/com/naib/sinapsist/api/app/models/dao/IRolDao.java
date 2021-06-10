package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.Rol;

public interface IRolDao extends CrudRepository<Rol, Integer>{
	
	public List<Rol> findAll();

}
