/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.*;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Synapsist-Serv
 */
@Entity
@Table(name="evento_calendario")
public class EventoCalendario implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_evento_calendario")
    private int id;
    
    @Column(name="titulo")
    private String titulo;
    
    @Column(name="notas")
    private String notas;
    
    @Column(name="fecha_hora_inicio")
    private LocalDateTime hora;
    
    @Column(name="ubicacion")
    private String ubicacion;
    
    @Column(name="estatus")
    private boolean estatus;
    
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_evento_calendario", insertable = true, updatable = true) // Automaticamente
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<SubTareaCalendario> lstSubTareaCalendario;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_evento_calendario", insertable = true, updatable = true) // Automaticamente
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ImgEventoCalendario> lstImgEventoCalendario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public List<SubTareaCalendario> getLstSubTareaCalendario() {
        return lstSubTareaCalendario;
    }

    public void setLstSubTareaCalendario(List<SubTareaCalendario> lstSubTareaCalendario) {
        this.lstSubTareaCalendario = lstSubTareaCalendario;
    }

    public List<ImgEventoCalendario> getLstImgEventoCalendario() {
        return lstImgEventoCalendario;
    }

    public void setLstImgEventoCalendario(List<ImgEventoCalendario> lstImgEventoCalendario) {
        this.lstImgEventoCalendario = lstImgEventoCalendario;
    }
    
    
    
    private static final long serialVersionUID = 1L;
}
