/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.dao;

import com.naib.sinapsist.api.app.models.entity.EventoCalendario;
import com.naib.sinapsist.api.app.models.entity.ListaCalendario;
import com.naib.sinapsist.api.app.models.entity.SubTareaCalendario;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Synapsist-Serv
 */
public interface ICalendarioDao extends CrudRepository<ListaCalendario, Integer> {

	@Query("FROM ListaCalendario lc WHERE lc.usuario.id=?1")
    public List<ListaCalendario> findAllById(int id);

    public ListaCalendario save(ListaCalendario lst);

    @Query("FROM ListaCalendario lc WHERE lc.id=?1")
    public ListaCalendario findById(int id);

    @Query("FROM EventoCalendario ec WHERE ec.id=?1")
    public EventoCalendario findEventoById(int id);

    @Transactional
    @Modifying
    @Query("UPDATE EventoCalendario ec SET ec.estatus=?1 where ec.id=?2")
    int updateStatusEventoCalendario(boolean estatus, int id);

    @Query("FROM SubTareaCalendario sbc WHERE sbc.id=?1")
    public SubTareaCalendario findSubTareaById(int id);

    @Transactional
    @Modifying
    @Query("UPDATE SubTareaCalendario ec SET ec.estatus=?1 where ec.id=?2")
    int updateStatusSubTareaCalendario(boolean estatus, int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM EventoCalendario st WHERE st.id=?1")
    public void DeleteEventoCalendarioById(int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM SubTareaCalendario st WHERE st.id=?1")
    public void DeleteSubTareaCalendarioById(int id);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM ListaCalendario st WHERE st.id=?1")
    public void DeleteListaCalendarioById(int id);
}
