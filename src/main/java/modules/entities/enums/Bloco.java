package modules.entities.enums;

public enum Bloco {
    BLOCO_1(1),
    BLOCO_2(2),
    BLOCO_3(3),
    BLOCO_4(4);

    private Integer numero;
    private Bloco(Integer numero){

        this.numero = numero;
    }

    public Integer getNumero() {
        return numero;
    }

    public static Bloco valueOf(int numero) {
        for (Bloco value : Bloco.values()) {
            if (value.getNumero() == numero) {
                return value;
            }
        }

        throw new IllegalArgumentException("Bloco não pode ser nulo");
    }
}
