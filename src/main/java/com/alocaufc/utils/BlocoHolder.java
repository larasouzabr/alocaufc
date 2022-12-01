package com.alocaufc.utils;

import com.alocaufc.entities.enums.Bloco;

public final class BlocoHolder {
    private static BlocoHolder INSTANCE;
    private Bloco bloco;

    private BlocoHolder() {}

    public static BlocoHolder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BlocoHolder();
        }
        return INSTANCE;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public Bloco getBloco() {
        return this.bloco;
    }
}
