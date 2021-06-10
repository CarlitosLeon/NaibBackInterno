package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naib.sinapsist.api.app.models.dao.IRolDao;
import com.naib.sinapsist.api.app.models.entity.Rol;

@Service
public class RolServiceImpl implements IRolService{
	
	@Autowired
	private IRolDao rolDao;

	@Override
	public List<Rol> findAll() {
		return rolDao.findAll();
	}

}
