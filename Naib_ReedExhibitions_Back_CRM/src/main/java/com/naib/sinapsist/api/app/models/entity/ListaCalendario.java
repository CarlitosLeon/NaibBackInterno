package com.naib.sinapsist.api.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Synapsist-Serv
 */
@Entity
@Table(name = "lista_calendario")
public class ListaCalendario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lista_calendario")
    private int id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "color")
    private String color;

    @Column(name = "icono")
    private String icono;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", insertable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_lista_calendario", insertable = true, updatable = true) // Automaticamente
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<EventoCalendario> lstEventoCalendario;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<EventoCalendario> getLstEventoCalendario() {
        return lstEventoCalendario;
    }

    public void setLstEventoCalendario(List<EventoCalendario> lstEventoCalendario) {
        this.lstEventoCalendario = lstEventoCalendario;
    }

    private static final long serialVersionUID = 1L;

}
