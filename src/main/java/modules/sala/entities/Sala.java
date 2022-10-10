package modules.sala.entities;

import modules.numeroBloco;

public class Sala {
    int qtdCadeiras;
    int projetor;
    boolean arCondicionado;
    numeroBloco numBloco;
    int numSala;

    public Sala(numeroBloco numBloco, int numeroSala, int qtdCadeiras, int projetor, boolean arCondicionado){
        this.qtdCadeiras = qtdCadeiras;
        this.projetor = projetor;
        this.arCondicionado = arCondicionado;
        this.numSala = numeroSala;
        this.numBloco = numBloco;
    }
}
