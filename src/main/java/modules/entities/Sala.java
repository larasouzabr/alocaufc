package modules.entities;

import modules.entities.enums.NumeroBloco;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class Sala implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    int qtdCadeiras;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    boolean projetor;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    boolean arCondicionado;
    int numBloco;
    int numSala;

    @OneToMany(mappedBy = "sala")
    private Set<Aula> aulas;

    public Sala() {

    }
    public Sala(Integer id, NumeroBloco numBloco, int numeroSala, int qtdCadeiras, boolean projetor, boolean arCondicionado){
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
    public void setProjetor(boolean projetor){
        this.projetor = projetor;
    }
    public void setArCondicionado(boolean arCondicionado){
        this.arCondicionado = arCondicionado;
    }
    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }
    public void setNumBloco(NumeroBloco numBloco){
        if(numBloco != null) {
            this.numBloco = numBloco.getNumero();
        }
    }

    public int getQtdCadeiras(){
        return this.qtdCadeiras;
    }
    public boolean getProjetor(){
        return this.projetor;
    }
    public boolean getArCondidionado(){
        return this.arCondicionado;
    }
    public NumeroBloco getBloco(){
        return NumeroBloco.valueOf(numBloco);
    }
    public int getNumSala(){
        return this.numSala;
    }

    public Set<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(Set<Aula> aulas) {
        this.aulas = aulas;
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
