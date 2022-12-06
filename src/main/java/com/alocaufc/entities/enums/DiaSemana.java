package com.alocaufc.entities.enums;

public enum DiaSemana {
    SEGUNDA(2),
    TERCA(3),
    QUARTA(4),
    QUINTA(5),
    SEXTA(6);

    private int numero;
    private DiaSemana(int numero){
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public static DiaSemana valueOf(int numero) {
        for (DiaSemana value : DiaSemana.values()) {
            if (value.getNumero() == numero) {
                return value;
            }
        }

        throw new IllegalArgumentException("Dia da semana não pode ser nulo");
    }

    @Override
    public String toString() {
        switch (numero) {
            case 2:
                return "Segunda-feira";
            case 3:
                return "Terça-feira";
            case 4:
                return "Quarta-feira";
            case 5:
                return "Quinta-feira";
            case 6:
                return "Sexta-feira";
            default:
                return "";
        }
    }
}
