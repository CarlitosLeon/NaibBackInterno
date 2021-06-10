package com.naib.sinapsist.api.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naib.sinapsist.api.app.models.dao.IConfigMailDao;
import com.naib.sinapsist.api.app.models.entity.ConfigMail;

@Service
public class ConfigMailServiceImpl implements IConfigMailService{
	
	@Autowired
	private IConfigMailDao configMailDao;

	@Override
	@Transactional(readOnly = true)
	public ConfigMail findByUser(int idU) {
		return configMailDao.findByUser(idU);
	}

}
