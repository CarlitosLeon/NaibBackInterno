package com.naib.sinapsist.api.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.DetalleEvento;

public interface IDetalleEventoDao extends CrudRepository<DetalleEvento, Integer> {

	public DetalleEvento save(DetalleEvento detalleE);
}
