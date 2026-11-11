/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.calculadora_recursiva;

/**
 *
 * @author Erick López
 */
public class MainCalculadora {

    /**
     * @param args the command line arguments
     */
 public static void main(String[] args) {
        probar("2+7+7+9+9+8"); 
        probar("7+10+2");
        probar("1+5*(67-1)+10");
        probar("5+7+(1+62*(8-79)+28)-104");
        probar("9-2*(75-9+(6+93+(1+32)*16)+7)+9");

        // Pruebas de errores
        probar("5++1+10");
        probar("-5+10");
        probar("74-7/7");
        probar("10+88-JJ");
    }

    public static void probar(String expresion) {

        try {
            int resultado =
                    new Calculadora_Recursiva(expresion).resolver();

            System.out.println("Expresion: " + expresion  + " = " + resultado );

        } catch (RuntimeException e) {

            System.out.println("ERROR en la expresion: " + expresion);

            System.out.println("Motivo: " + e.getMessage() );
        }

        System.out.println("----------------------------");
    }
}