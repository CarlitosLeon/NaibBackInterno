package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.dao.IComponenteDao;
import com.naib.sinapsist.api.app.models.entity.CarteraEvento;
import com.naib.sinapsist.api.app.models.entity.Componente;
import com.naib.sinapsist.api.app.models.entity.Stand;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MapaVentasImpl implements IMapaVentasService {
	@Autowired
	private IComponenteDao mapaVentasDao;

	@Override
	public List<Stand> getAllReferenciasStandMapa(int id) {
		return mapaVentasDao.findStandById(id);
	}

	@Override
	public List<Componente> getAllComponentesActivos(int id) {
		return mapaVentasDao.findComponenteById(id);
	}

	@Override
	public List<CarteraEvento> getAllExpositoresCRM(int id) {
		return mapaVentasDao.findCarteraEventoById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Componente findById(int idCom) {
		return mapaVentasDao.findById(idCom);
	}

	@Override
	public int updateStatusReubicacion(boolean status, int id) {
		return mapaVentasDao.updateStatusExpositorReubicacion(status, id);
	}

	@Override
	public CarteraEvento findCarteraById(int id) {
		return mapaVentasDao.findCarteraById(id);
	}

	@Override
	public Componente guardarComponenteAgrupado(Componente com) {
		return mapaVentasDao.save(com);
	}

	@Override
	public void deleteAgrupacion(int id) {
		mapaVentasDao.DeleteById(id);
		mapaVentasDao.DeleteComponenteById(id);
	}

	@Override
	public List<Componente> getComponentesByAgrupacion(String agrupacion) {
		return mapaVentasDao.findComponenteByAgrupacion(agrupacion);
	}

	@Override
	public List<CarteraEvento> getCarteraEventoByVendedor(int id) {
		return mapaVentasDao.getLstCarteraEventoByIdUsusario(id);
	}

	@Override
	public List<CarteraEvento> getCarteraEventoByVendedorAndEvento(int id, int idEvento) {
		return mapaVentasDao.getLstCarteraEventoByIdUsusarioAndEvento(id, idEvento);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Componente> componenteAgrupacion(int id) {
		return mapaVentasDao.componenteAgrupacion(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Stand> standExpositor(int id) {
		return mapaVentasDao.standExpositor(id);
	}

}