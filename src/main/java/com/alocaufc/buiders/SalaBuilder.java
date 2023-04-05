package com.alocaufc.buiders;

import com.alocaufc.entities.Sala;
import com.alocaufc.entities.enums.Bloco;

public class SalaBuilder {
    private Sala sala;

    public SalaBuilder() {
        this.sala = new Sala();
    }

    public static SalaBuilder builder() {
        return new SalaBuilder();
    }

    public SalaBuilder setTitulo(String titulo) {
        sala.setTitulo(titulo);
        return this;
    }

    public SalaBuilder setBloco(Bloco bloco) {
        sala.setBloco(bloco);
        return this;
    }

    public SalaBuilder setLugares(Integer lugares) {
        sala.setLugares(lugares);
        return this;
    }

    public SalaBuilder hasArCondicionado() {
        sala.setArCondicionado(true);
        return this;
    }

    public SalaBuilder hasProjetor() {
        sala.setProjetor(true);
        return this;
    }

    public SalaBuilder setQtdComputadores(Integer qtdComputadores) {
        sala.setQtdComputadores(qtdComputadores);
        return this;
    }

    public SalaBuilder setObservacao(String observacao) {
        sala.setObservacao(observacao);
        return this;
    }

    public Sala build() {
        return sala;
    }


}
