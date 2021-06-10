/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.entity.EventoCalendario;
import com.naib.sinapsist.api.app.models.entity.ImgEventoCalendario;
import com.naib.sinapsist.api.app.models.entity.ListaCalendario;
import com.naib.sinapsist.api.app.models.entity.SubTareaCalendario;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Synapsist-Serv
 */
public interface ICalendarioService {
    
public ListaCalendario getListaCalendario(int id);
    
    public List<ListaCalendario> getDatosCalendarioByIdUsusario(int id);
    
    public ListaCalendario saveLista(ListaCalendario lst);
    
    public EventoCalendario getEventoCalendarioById(int id);
    
    public int updateEstatusEventoCalendarioById(boolean estatus,int id);
    
    public SubTareaCalendario getSubTareaById(int id);
    
    public int updateEstatusSubTareaCalendarById(boolean status, int id);
    
    public void deleteEventoCalendario(int id);
    
    public void deleteSubTareaCalendario(int id);
    
    public String subirEvidencia(MultipartFile file);
    
    public void BorrarImagen(ImgEventoCalendario imgEventoC);
    
    public void deleteListaCalendario(int id);
}
