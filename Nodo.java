/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author Roberto Ureta
 */
public class Nodo {
    private int[][] matriz;
    private Nodo nodoPadre;

    public Nodo(Nodo nodoPadre) {
        this.matriz = new int[3][3];    
        this.nodoPadre = nodoPadre;
    }
    public Nodo(Nodo nodoPadre,int[][] matriz) {
        this.matriz = matriz;    
        this.nodoPadre = nodoPadre;
    }
    
    public Nodo obtenerNodoPadre(){
        return nodoPadre;
    }
    public void llenarMatrizInicial(ArrayList<Integer> lista){
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {             
                matriz[i][j] = lista.get(k);
                k++;
            }
        }
    }

    public int[][] obtenerMatriz(){
        int[][] matriz = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[i][j] = this.matriz[i][j];
            }
        }
        return matriz;
    }

     public void imprimirMatriz(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%d ", matriz[i][j]);
            }
            System.out.println("");
        }
    }
     
    
    
    
}
