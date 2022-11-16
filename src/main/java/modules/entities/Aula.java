package modules.entities;

import modules.entities.enums.DiaDaSemana;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "aula", uniqueConstraints = { @UniqueConstraint( columnNames = { "sala_id", "dia_da_semana", "horario_aula" } ) } )
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "horario_aula")
    private String horarioAula;
    @Column(name = "dia_da_semana")
    private Integer diaDaSemana;
    private String disciplina;
    private String turma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    public Aula() {
    }

    public Aula(Sala sala,  String horarioAula, DiaDaSemana diaDaSemana, String disciplina, String turma) {
        this.sala = sala;
        this.horarioAula = horarioAula;
        setDiaDaSemana(diaDaSemana);
        this.disciplina = disciplina;
        this.turma = turma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHorarioAula() {
        return horarioAula;
    }

    public void setHorarioAula(String horarioAula) {
        this.horarioAula = horarioAula;
    }

    public DiaDaSemana getDiaDaSemana() {
        if(diaDaSemana == null) return null;

        return DiaDaSemana.valueOf(diaDaSemana);
    }

    public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
        if(diaDaSemana != null) {
            this.diaDaSemana = diaDaSemana.getNumero();
        }
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", horarioAula='" + horarioAula + '\'' +
                ", diaDaSemana=" + diaDaSemana +
                ", disciplina='" + disciplina + '\'' +
                ", turma='" + turma + '\'' +
                ", num_sala=" + sala.getNumSala() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return Objects.equals(id, aula.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
