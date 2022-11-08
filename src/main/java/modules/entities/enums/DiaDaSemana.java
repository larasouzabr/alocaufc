package modules.entities.enums;

public enum DiaDaSemana {
    SEGUNDA(2),
    TERCA(3),
    QUARTA(4),
    QUINTA(5),
    SEXTA(6);

    private int numero;
    private DiaDaSemana(int numero){
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public static DiaDaSemana valueOf(int numero) {
        for (DiaDaSemana value : DiaDaSemana.values()) {
            if (value.getNumero() == numero) {
                return value;
            }
        }

        throw new IllegalArgumentException("Dia da semana não pode ser nulo");
    }

}
