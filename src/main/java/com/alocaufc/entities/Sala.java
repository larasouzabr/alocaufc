package com.alocaufc.entities;

import com.alocaufc.entities.enums.Bloco;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Sala")
@Table(name = "sala")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer bloco;
    private Integer lugares;
    private Boolean arCondicionado;
    private Boolean projetor;
    private Integer qtdComputadores;
    private String observacao;
    @OneToMany(mappedBy = "sala")
    @OrderBy("diaSemana asc, horaInicio asc")
    private List<Horario> horarios;

    public Sala() {
    }

    public Sala(Long id, String titulo, Integer bloco, Integer lugares, Boolean arCondicionado, Boolean projetor, Integer qtdComputadores, String observacao) {
        this.id = id;
        this.titulo = titulo;
        this.bloco = bloco;
        this.lugares = lugares;
        this.arCondicionado = arCondicionado;
        this.projetor = projetor;
        this.qtdComputadores = qtdComputadores;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Bloco getBloco() {
        if(bloco == null) return null;

        return Bloco.valueOf(bloco);
    }

    public void setBloco(Bloco bloco) {

        if(bloco != null) {
            this.bloco = bloco.getNumero();
        }
    }

    public Integer getLugares() {
        return lugares;
    }

    public void setLugares(Integer lugares) {
        this.lugares = lugares;
    }

    public Boolean getArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(Boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public Boolean getProjetor() {
        return projetor;
    }

    public void setProjetor(Boolean projetor) {
        this.projetor = projetor;
    }

    public Integer getQtdComputadores() {
        return qtdComputadores;
    }

    public void setQtdComputadores(Integer qtdComputadores) {
        this.qtdComputadores = qtdComputadores;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }



    public void setBloco(Integer bloco) {
        this.bloco = bloco;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}


