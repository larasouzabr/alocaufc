package modules.entities.enums;

public enum NumeroBloco {
    BLOCO1(1),
    BLOCO2(2),
    BLOCO3(3),
    BLOCO(4);

    private int numero;
    private NumeroBloco(int numero){
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public static NumeroBloco valueOf(int code) {
        for (NumeroBloco value : NumeroBloco.values()) {
            if (value.getNumero() == code) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
