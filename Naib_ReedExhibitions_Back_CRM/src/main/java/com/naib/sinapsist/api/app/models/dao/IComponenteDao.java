package com.naib.sinapsist.api.app.models.dao;

import com.naib.sinapsist.api.app.models.entity.CarteraEvento;
import com.naib.sinapsist.api.app.models.entity.Componente;
import com.naib.sinapsist.api.app.models.entity.Stand;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IComponenteDao extends CrudRepository<Componente, Integer> {

//	Osvaldo

	@Query("FROM Stand s WHERE s.componente.salon.detalleEvento.evento.id=?1 AND s.estatus_asignacion!='4' AND s.estatus_asignacion!='5' ORDER BY s.componente.numeroStand")
	public List<Stand> findStandById(int id);

	@Query("FROM Componente c WHERE c.salon.detalleEvento.evento.id=?1 ORDER BY numeroStand")
	public List<Componente> findComponenteById(int id);

	@Query("FROM CarteraEvento ce WHERE ce.evento.id=?1 AND ce.expositor.estatusCrm>=2")
	public List<CarteraEvento> findCarteraEventoById(int id);

	@Transactional
	@Modifying
	@Query("UPDATE CarteraEvento st SET st.reubicacion=?1 where st.id=?2")
	int updateStatusExpositorReubicacion(boolean estatus, int id);

	@Query("FROM CarteraEvento ce WHERE ce.id=?1")
	public CarteraEvento findCarteraById(int id);

	public Componente save(Componente com);

	public Componente findById(int idCom);

	@Query("FROM Componente where agrupacion=?1")
	public List<Componente> findComponenteByAgrupacion(String agrupacion);

	@Transactional
	@Modifying
	@Query("DELETE FROM Stand st WHERE st.componente.id=?1")
	public void DeleteById(int id);

	@Transactional
	@Modifying
	@Query("DELETE FROM Componente com WHERE com.id=?1")
	public void DeleteComponenteById(int id);

	@Query("FROM CarteraEvento cae WHERE cae.vendedor.id=?1")
	public List<CarteraEvento> getLstCarteraEventoByIdUsusario(int id);

	@Query("FROM CarteraEvento cae WHERE cae.vendedor.id=?1 AND cae.evento.id=?2")
	public List<CarteraEvento> getLstCarteraEventoByIdUsusarioAndEvento(int id, int idEvento);

//    Heras --------------------------------------------
	@Query("FROM Componente c WHERE c.salon.detalleEvento.evento.id=?1 AND c.status=1 ORDER BY numeroStand")
	public List<Componente> componenteAgrupacion(int id);

	@Query("FROM Stand s WHERE s.componente.salon.detalleEvento.evento.id=?1 AND s.estatus_asignacion!='4' AND s.estatus_asignacion!='5' AND s.componente.status=1 ORDER BY s.componente.numeroStand")
	public List<Stand> standExpositor(int id);
}
