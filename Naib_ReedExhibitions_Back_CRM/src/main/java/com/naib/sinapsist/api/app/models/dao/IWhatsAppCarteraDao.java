/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.dao;

import com.naib.sinapsist.api.app.models.entity.WhatsAppCartera;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Synapsist-Serv
 */
public interface IWhatsAppCarteraDao extends CrudRepository<WhatsAppCartera, Integer> {
    
      @Query("SELECT wc FROM WhatsAppCartera wc WHERE wc.detalleCartera.id=?1 AND wc.status='1' ORDER BY fecha_programado")
       public List<WhatsAppCartera> getWhatsProgramadosCarteraById(int idC);
}
