/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Roberto Ureta
 */
public class Grafo {
    private ArrayList<Integer> lista;
    private Queue<Nodo> cola;
    private Stack<Nodo> pila;
    private ArrayList<Nodo> solucion;
    private ArrayList<Nodo> nodosCreados;
    private boolean tipoBus;
    private int sumaNodos;
    private int sumaCambio;

    public Grafo() {
        
        this.sumaCambio = 0;
        this.lista = new ArrayList<>();
        Nodo nodoRaiz = new Nodo(null,desordenarMatriz(50));//cambiar el numero para mas desorden de la matriz
        /*this.lista.add(2);
        this.lista.add(3);
        this.lista.add(6);
        this.lista.add(8);
        this.lista.add(0);
        this.lista.add(4);
        this.lista.add(1);
        this.lista.add(5);
        this.lista.add(7);
        
        nodoRaiz.llenarMatrizInicial(this.lista);
        nodoRaiz.imprimirMatriz();*/
        tipoBus=true;
        while(true){
            imprimirMatriz(nodoRaiz.obtenerMatriz());
            this.sumaNodos = 0;
            this.cola = new LinkedList<>();
            this.pila = new Stack<>();
            this.solucion = new ArrayList<>();
            this.nodosCreados = new ArrayList<>();
            long inicio=0;
            long fin=0;
            menu();
            if (tipoBus) {
                System.out.println("Ejecutando BFS...");
                cola.add(nodoRaiz);
                nodosCreados.add(nodoRaiz);
                inicio = System.currentTimeMillis();
                BFS();
                fin = System.currentTimeMillis();
                System.out.println("Tiempo Transcurrido:"+(int)(fin-inicio)/1000+" segundos");
            }
            else{
                System.out.println("Ejecutando DFS...");
                pila.push(nodoRaiz);
                nodosCreados.add(nodoRaiz);
                inicio = System.currentTimeMillis();
                DFS();
                fin = System.currentTimeMillis();
                System.out.println("Tiempo Transcurrido:"+(int)(fin-inicio)/1000+" segundos");
            }
        }
        
        
    }
    //desordena la matriz solucion
    public int[][] desordenarMatriz(int n) {
        int[][] matriz = matrizSolucion();
        int random = 0;
        for (int i = 0; i < n; i++) {
            random = (int) (Math.random() * 4);
            switch (revisarCero(matriz)) {
                //arriba 0
                //abajo 1
                //izq 2
                //der 3
                case "00":
                    if (random == 3) {
                        matriz = intercambio(matriz,
                                             0, 0, 0, 1);
                        sumaCambio++;
                    }
                    if (random == 1) {
                        matriz = intercambio(matriz,
                                             0, 0, 1, 0);
                        sumaCambio++;
                    }
                    break;
                case "01":
                    if (random == 2) {
                        matriz = intercambio(matriz,
                                             0, 1, 0, 0);
                        sumaCambio++;
                    }
                    if (random == 1) {
                        matriz = intercambio(matriz,
                                             0, 1, 1, 1);
                        sumaCambio++;
                    }
                    if (random == 3) {
                        matriz = intercambio(matriz,
                                             0, 1, 0, 2);
                        sumaCambio++;
                    }
                    break;
                case "02":
                    if (random == 2) {
                        matriz = intercambio(matriz,
                                             0, 2, 0, 1);
                        sumaCambio++;
                    }
                    if (random == 1) {
                        matriz = intercambio(matriz,
                                             0, 2, 1, 2);
                        sumaCambio++;
                    }
                    break;
                case "10":
                    if (random == 0) {
                        matriz = intercambio(matriz,
                                             1, 0, 0, 0);
                        sumaCambio++;
                    }
                    if (random == 3) {
                        matriz = intercambio(matriz,
                                             1, 0, 1, 1);
                        sumaCambio++;
                    }
                    if (random == 1) {
                        matriz = intercambio(matriz,
                                             1, 0, 2, 0);
                        sumaCambio++;
                    }
                case "11":
                    if (random == 0) {
                        matriz = intercambio(matriz,
                                             1, 1, 0, 1);
                        sumaCambio++;
                    }
                    if (random == 2) {
                        matriz = intercambio(matriz,
                                             1, 1, 1, 0);
                        sumaCambio++;
                    }
                    if (random == 3) {
                        matriz = intercambio(matriz,
                                             1, 1, 1, 2);
                        sumaCambio++;
                    }
                    if (random == 1) {
                        matriz = intercambio(matriz,
                                             1, 1, 2, 1);
                        sumaCambio++;
                    }
                    break;
                case "12":
                    if (random == 0) {
                        matriz = intercambio(matriz,
                                             1, 2, 0, 2);
                        sumaCambio++;
                    }
                    if (random == 2) {
                        matriz = intercambio(matriz,
                                             1, 2, 1, 1);
                        sumaCambio++;
                    }
                    if (random == 1) {
                        matriz = intercambio(matriz,
                                             1, 2, 2, 2);
                        sumaCambio++;
                    }
                    break;
                case "20":
                    if (random == 0) {
                        matriz = intercambio(matriz,
                                             2, 0, 1, 0);
                        sumaCambio++;
                    }
                    if (random == 3) {
                        matriz = intercambio(matriz,
                                             2, 0, 2, 1);
                        sumaCambio++;
                    }
                    break;
                case "21":
                    if (random == 2) {
                        matriz = intercambio(matriz,
                                             2, 1, 2, 0);
                        sumaCambio++;
                    }
                    if (random == 0) {
                        matriz = intercambio(matriz,
                                             2, 1, 1, 1);
                        sumaCambio++;
                    }
                    if (random == 3) {
                        matriz = intercambio(matriz,
                                             2, 1, 2, 2);
                        sumaCambio++;
                    }
                    break;
                case "22":
                    if (random == 0) {
                        matriz = intercambio(matriz,
                                             2, 2, 1, 2);
                        sumaCambio++;
                    }
                    if (random == 2) {
                        matriz = intercambio(matriz,
                                             2, 2, 2, 1);
                        sumaCambio++;
                    }
                    break;
            }
        }
        return matriz;
    }
    /**
     * Menu de seleccion
     */
    public void menu(){
        System.out.println("1.- BFS");
        System.out.println("2.- DFS");
        System.out.println("3.- Salir");
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        if (n==1) 
            tipoBus = true;
        if(n==2)
            tipoBus = false;
        if (n==3) {
            System.exit(0);
        }
    }
    
    /**
     * Algoritmo de Busqueda en Anchura, el cual utiliza una cola.
     */
    public void BFS(){
        while(!cola.isEmpty()){
            if (cola.peek()!=null) {
                if (revisar(cola.poll()))
                    break;
            } 
        }
    }
    
    /**
     * Algoritmo de Busqueda en profundidad, el cual utiliza una pila.
     */
    public void DFS(){
        while(!pila.isEmpty()){
            if (pila.peek()!=null) {
                if (revisar(pila.pop()))
                    break;
            } 
        }
    }
    
    public boolean revisar(Nodo actual) {
        if (esSolucion(actual)) {
            generarSolucion(actual);
            return true;
        }
        agregarHijos(actual);
        System.out.println("Cantidad de Nodos:"+sumaNodos);
        return false;
    }
    
    public int[][] matrizSolucion(){
        int[][] matriz = new int[3][3];
        matriz[0][0] = 1;
        matriz[0][1] = 2;
        matriz[0][2] = 3;
        matriz[1][0] = 4;
        matriz[1][1] = 0;
        matriz[1][2] = 5;
        matriz[2][0] = 6;
        matriz[2][1] = 7;
        matriz[2][2] = 8;
        return matriz;
    } 

    private boolean esSolucion(Nodo actual) {
        return Arrays.deepEquals(actual.obtenerMatriz(), matrizSolucion());
    }

    private void generarSolucion(Nodo actual) {
        while(actual.obtenerNodoPadre()!=null){
            solucion.add(actual);
            actual = actual.obtenerNodoPadre();
        }
        solucion.add(actual);
        int suma = 0;
        System.out.println("solucion encontrada!!!");
        for (int i = solucion.size()-1; i >= 0 ; i--) {
            solucion.get(i).imprimirMatriz();
            suma++;
            System.out.println("");
        }
        System.out.println("Bloques intercambiados:"+sumaCambio);
        System.out.println("Total de Nodos Recorridos en la solucion:"+suma);
        System.out.println("Total de Nodos creados:"+sumaNodos);
    }

    /**
     * Realiza el intercambio de dos bloques.
     * @param matriz matriz a intercambiar.
     * @param filaVacio fila donde se encuentra el 0.
     * @param colVacio columna donde se encuentra el 0.
     * @param filaNum fila donde se encuentra el num a cambiar.
     * @param colNum columna donde se encuentra el num a cambiar.
     * @return la nueva matriz.
     */
    public int[][] intercambio(int[][] matriz, int filaVacio,int colVacio,int filaNum,int colNum){
        int num = matriz[filaNum][colNum];
        int num1 = matriz[filaVacio][colVacio];
        if (num1 == 0) {
            matriz[filaNum][colNum] = num1;
            matriz[filaVacio][colVacio] = num;
        }  
        return matriz;
    }
    /**
     * Imprime una matriz.
     * @param matriz matriz a imprimir.
     */
    public void imprimirMatriz(int[][] matriz){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%d ", matriz[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    private void agregarHijos(Nodo actual) {
        switch(revisarCero(actual.obtenerMatriz())) {
         case "00" :
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 0, 0, 0, 1));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 0, 0, 1, 0));
            break;
         case "01" :
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 0, 1, 0, 0));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 0, 1, 1, 1));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 0, 1, 0, 2));
            break;
         case "02" :
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 0, 2, 0, 1));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 0, 2, 1, 2));
            break;
         case "10" :
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 1, 0, 0, 0));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 1, 0, 1, 1));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 1, 0, 2, 0));
         case "11" :
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 1, 1, 0, 1));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 1, 1, 1, 0));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 1, 1, 1, 2));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 1, 1, 2, 1));
            break;
         case "12" :
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 1, 2, 0, 2));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 1, 2, 1, 1));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 1, 2, 2, 2));
            break;
         case "20" :
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 2, 0, 1, 0));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 2, 0, 2, 1));
            break;
         case "21" :
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 2, 1, 2, 0));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 2, 1, 1, 1));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 2, 1, 2, 2));
            break;
         case "22" :
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 2, 2, 1, 2));
            revisarConPadre(actual,intercambio(actual.obtenerMatriz(), 2, 2, 2, 1));
            break;
      }
    }
    
    public void revisarConPadre(Nodo actual, int[][] hijo){
        if (actual.obtenerNodoPadre()!=null) {
            if(revisarNodosCreados(actual, hijo)){
                if (tipoBus) {
                    cola.add(new Nodo(actual,hijo)); 
                    nodosCreados.add(new Nodo(actual,hijo));
                    sumaNodos++;
                    //imprimirMatriz(hijo);
                }
                else{
                    pila.push(new Nodo(actual,hijo));
                    nodosCreados.add(new Nodo(actual,hijo));
                    sumaNodos++;
                    //imprimirMatriz(hijo);
                }
                
            }
        }
        else{
            if (tipoBus) {
                cola.add(new Nodo(actual, hijo));
                nodosCreados.add(new Nodo(actual, hijo));
                sumaNodos++;
                //imprimirMatriz(hijo);
            }
            else {
                pila.push(new Nodo(actual, hijo));
                nodosCreados.add(new Nodo(actual, hijo));
                sumaNodos++;
                //imprimirMatriz(hijo);
            }
        }  
    }
    
    public boolean revisarNodosCreados(Nodo actual, int[][] hijo){
        for (int i = 0; i < nodosCreados.size(); i++) {
            if (Arrays.deepEquals(nodosCreados.get(i).obtenerMatriz(), hijo)) {
                return false;
            }
        }
        return true;
    }
    
    private String revisarCero(int[][] matriz){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(matriz[i][j]==0)
                    return ""+i+j;
            }
        }
        return "";
    }
}
