package com.naib.sinapsist.api.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.naib.sinapsist.api.app.models.entity.WhatsAppContacto;

public interface IWhatsAppContactoDao extends CrudRepository<WhatsAppContacto, Integer> {

	@Query("SELECT wc FROM WhatsAppContacto wc WHERE wc.contacto.id=?1 ORDER BY wc.id")
	// @Query("SELECT wc FROM WhatsAppContacto wc ORDER BY wc.id")
	public List<WhatsAppContacto> getwhatsContacto(int idwEx);

	public WhatsAppContacto save(WhatsAppContacto whatsAppContacto);

	@Query("SELECT wc FROM WhatsAppContacto wc WHERE wc.contacto.id=?1 AND wc.status='1' ORDER BY fecha_programado")
	public List<WhatsAppContacto> getWhatsProgramados(int idEx);
}
