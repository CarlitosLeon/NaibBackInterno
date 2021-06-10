package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.entity.CarteraEvento;
import com.naib.sinapsist.api.app.models.entity.Componente;
import com.naib.sinapsist.api.app.models.entity.Stand;
import java.util.List;

public interface IMapaVentasService {

//	Osvaldo
	public List<Stand> getAllReferenciasStandMapa(int id);

	public List<Componente> getAllComponentesActivos(int id);

	public List<CarteraEvento> getAllExpositoresCRM(int id);

	public Componente findById(int idCom);

	public CarteraEvento findCarteraById(int id);

	public Componente guardarComponenteAgrupado(Componente com);

	public void deleteAgrupacion(int id);

	public List<Componente> getComponentesByAgrupacion(String agrupacion);

	public int updateStatusReubicacion(boolean status, int id);

	public List<CarteraEvento> getCarteraEventoByVendedor(int id);

	public List<CarteraEvento> getCarteraEventoByVendedorAndEvento(int id, int idEvento);

//	Heras
	public List<Componente> componenteAgrupacion(int id);

	public List<Stand> standExpositor(int id);
}