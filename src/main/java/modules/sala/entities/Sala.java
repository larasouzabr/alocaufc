package modules.sala.entities;

import modules.numeroBloco;

public class Sala {
    int qtdCadeiras;
    int projetor;
    boolean arCondicionado;
    numeroBloco numBloco;
    int numSala;

    public Sala(numeroBloco numBloco, int numeroSala, int qtdCadeiras, int projetor, boolean arCondicionado){
        setNumBloco(numBloco);
        setNumSala(numeroSala);
        setQtdCadeiras(qtdCadeiras);
        setProjetor(projetor);
        setArCondicionado(arCondicionado);
    };
    public void setQtdCadeiras(int qtdCadeiras){
        this.qtdCadeiras = qtdCadeiras;
    };
    public void setProjetor(int projetor){
        this.projetor = projetor;
    };
    public void setArCondicionado(boolean arCondicionado){
        this.arCondicionado = arCondicionado;
    };
    public void setNumSala(int numSala) {
        this.numSala = numSala;
    };
    public void setNumBloco(numeroBloco numBloco){
        this.numBloco = numBloco;
    };

    public int getQtdCadeiras(){
        return this.qtdCadeiras;
    };
    public int getProjetor(){
        return this.projetor;
    };
    public boolean getArCondidionado(){
        return this.arCondicionado;
    }
    public numeroBloco getBloco(){
        return this.numBloco;
    }
    public int getNumSala(){
        return this.numSala;
    }



}
