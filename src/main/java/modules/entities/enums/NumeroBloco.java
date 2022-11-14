package modules.entities.enums;

import java.util.Arrays;
import java.util.List;

public enum NumeroBloco {
    BLOCO_1(1),
    BLOCO_2(2),
    BLOCO_3(3),
    BLOCO_4(4);

    private int numero;
    private NumeroBloco(int numero){

        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public static NumeroBloco valueOf(int numero) {
        for (NumeroBloco value : NumeroBloco.values()) {
            if (value.getNumero() == numero) {
                return value;
            }
        }

        throw new IllegalArgumentException("Bloco não pode ser nulo");
    }


}
