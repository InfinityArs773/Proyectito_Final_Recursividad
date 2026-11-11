package com.mycompany.calculadora_recursiva;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Erick López
 */

public class Calculadora_Recursiva {

    private String texto;
    private int posicion;

    public Calculadora_Recursiva(String texto) {
        if (texto == null || texto.isEmpty()) {
            throw new IllegalArgumentException("Expresion vacia");
        }

        this.texto = texto;
        this.posicion = 0;
    }

    public int resolver() {
        int resultado = expresion();

        espacios();

        if (posicion != texto.length()) {
            throw new IllegalArgumentException("Expresion invalida");
        }

        return resultado;
    }

    private int expresion() {
        int resultado = termino();
        return continuarExpresion(resultado);
    }

    private int continuarExpresion(int resultado) {
        espacios();

        if (posicion >= texto.length()
                || texto.charAt(posicion) == ')') {
            return resultado;
        }

        char operador = texto.charAt(posicion);

        if (operador == '+' || operador == '-') {
            posicion++;

            int siguiente = termino();

            if (operador == '+') {
                resultado = Math.addExact(resultado, siguiente);
            } else {
                resultado = Math.subtractExact(resultado, siguiente);
            }

            return continuarExpresion(resultado);
        }

        return resultado;
    }

    private int termino() {
        int resultado = factor();
        return continuarTermino(resultado);
    }

    private int continuarTermino(int resultado) {
        espacios();

        if (posicion < texto.length()
                && texto.charAt(posicion) == '*') {

            posicion++;

            int siguiente = factor();

            resultado = Math.multiplyExact(resultado, siguiente);

            return continuarTermino(resultado);
        }

        return resultado;
    }

    private int factor() {
        espacios();

        if (posicion >= texto.length()) {
            throw new IllegalArgumentException("Expresion incompleta");
        }

        char caracter = texto.charAt(posicion);

        if (caracter == '(') {
            posicion++;

            int resultado = expresion();

            espacios();

            if (posicion >= texto.length()
                    || texto.charAt(posicion) != ')') {
                throw new IllegalArgumentException("Falta cerrar parentesis");
            }

            posicion++;

            return resultado;
        }

        if (caracter >= '0' && caracter <= '9') {
            return numero();
        }

        throw new IllegalArgumentException("Caracter invalido");
    }

    private int numero() {
        return continuarNumero(0);
    }

    private int continuarNumero(int resultado) {
        if (posicion >= texto.length()) {
            return resultado;
        }

        char caracter = texto.charAt(posicion);

        if (caracter >= '0' && caracter <= '9') {
            int digito = caracter - '0';

            resultado = Math.addExact(
                    Math.multiplyExact(resultado, 10),
                    digito
            );

            posicion++;

            return continuarNumero(resultado);
        }

        return resultado;
    }

    private void espacios() {
        if (posicion < texto.length()
                && Character.isWhitespace(texto.charAt(posicion))) {
            posicion++;
            espacios();
        }
    }
}
