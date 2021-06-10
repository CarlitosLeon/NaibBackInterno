package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.entity.ConfigMail;

public interface IConfigMailService {
	
	public ConfigMail findByUser(int idU);

}
