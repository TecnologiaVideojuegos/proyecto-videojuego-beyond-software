/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author alvar
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        float x = 200f;
        int ancho = 120;
        float resultado = x + ancho / 2;
        System.out.println("1) "+resultado);
        resultado = x + (ancho / 2);
        System.out.println("2) "+resultado);
        resultado = x + (float) (ancho / 2);
        System.out.println("3) "+resultado);
        resultado = x +  ((float)ancho / 2f);
        System.out.println("4) "+resultado);
    }
    
}
