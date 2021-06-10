package com.naib.sinapsist.api.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.ConfigMail;

public interface IConfigMailDao extends CrudRepository<ConfigMail, Integer>{
	
	@Query("SELECT c FROM ConfigMail c WHERE c.usuario.id=?1")
	public ConfigMail findByUser(int idU);

}
