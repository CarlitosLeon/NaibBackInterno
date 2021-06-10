/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.dao.ICalendarioDao;
import com.naib.sinapsist.api.app.models.entity.EventoCalendario;
import com.naib.sinapsist.api.app.models.entity.ImgEventoCalendario;
import com.naib.sinapsist.api.app.models.entity.ListaCalendario;
import com.naib.sinapsist.api.app.models.entity.SubTareaCalendario;
import com.naib.sinapsist.api.app.utilities.Environment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Synapsist-Serv
 */
@Service
public class CalendarioServiceImpl implements ICalendarioService{

    @Autowired
    private ICalendarioDao calendarDao;
    
    @Override
    public List<ListaCalendario> getDatosCalendarioByIdUsusario(int id) {
        return calendarDao.findAllById(id);
    }

    @Override
    public ListaCalendario getListaCalendario(int id) {
        return calendarDao.findById(id);
    }

    @Override
    public ListaCalendario saveLista(ListaCalendario lst) {
        return calendarDao.save(lst);
    }

    @Override
    public EventoCalendario getEventoCalendarioById(int id) {
        return calendarDao.findEventoById(id);
    }

    @Override
    public int updateEstatusEventoCalendarioById(boolean estatus, int id) {
        return calendarDao.updateStatusEventoCalendario(estatus, id);
    }

    @Override
    public SubTareaCalendario getSubTareaById(int id) {
        return calendarDao.findSubTareaById(id);
    }

    @Override
    public int updateEstatusSubTareaCalendarById(boolean status, int id) {
        return calendarDao.updateStatusSubTareaCalendario(status, id);
    }

    @Override
    public void deleteEventoCalendario(int id) {
        calendarDao.DeleteEventoCalendarioById(id);
    }

    @Override
    public void deleteSubTareaCalendario(int id) {
        calendarDao.DeleteSubTareaCalendarioById(id);
    }
    
     @Override
    public String subirEvidencia(MultipartFile archivo) {
        String nombre = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
        Path rutaArchivo = Environment.GET_SRC_CALENDARIO(nombre);
        try {
            Files.copy(archivo.getInputStream(), rutaArchivo);
            return nombre;
        } catch (IOException e) {
            return (e.getMessage() + " : " + e.getCause().toString());
        }
    }
    
    public void BorrarImagen(ImgEventoCalendario imgEventoC) {
        String nombreFotoAnterior = imgEventoC.getUrl();
        if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
            Path rutaFotoAnterior = Environment.GET_SRC_CALENDARIO(nombreFotoAnterior);
            File fotoAnterior = rutaFotoAnterior.toFile();
            if (fotoAnterior.exists() && fotoAnterior.canRead()) {
                fotoAnterior.delete();
            }
        }
    }

    @Override
    public void deleteListaCalendario(int id) {
        calendarDao.DeleteListaCalendarioById(id);
    }
   
}