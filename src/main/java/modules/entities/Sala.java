package modules.entities;

import modules.numeroBloco;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Sala implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    int qtdCadeiras;
    int projetor;
    boolean arCondicionado;
    numeroBloco numBloco;
    int numSala;

    public Sala() {

    }
    public Sala(Integer id, numeroBloco numBloco, int numeroSala, int qtdCadeiras, int projetor, boolean arCondicionado){
        this.id = id;
        setNumBloco(numBloco);
        setNumSala(numeroSala);
        setQtdCadeiras(qtdCadeiras);
        setProjetor(projetor);
        setArCondicionado(arCondicionado);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQtdCadeiras(int qtdCadeiras){
        this.qtdCadeiras = qtdCadeiras;
    }
    public void setProjetor(int projetor){
        this.projetor = projetor;
    }
    public void setArCondicionado(boolean arCondicionado){
        this.arCondicionado = arCondicionado;
    }
    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }
    public void setNumBloco(numeroBloco numBloco){
        this.numBloco = numBloco;
    }

    public int getQtdCadeiras(){
        return this.qtdCadeiras;
    }
    public int getProjetor(){
        return this.projetor;
    }
    public boolean getArCondidionado(){
        return this.arCondicionado;
    }
    public numeroBloco getBloco(){
        return this.numBloco;
    }
    public int getNumSala(){
        return this.numSala;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sala sala = (Sala) o;
        return Objects.equals(id, sala.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
