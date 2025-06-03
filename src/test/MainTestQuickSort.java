package test;


import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import ordenamientos.QuickSortSecuencial;
import ordenamientos.QuickSortConcurrente;

public class MainTestQuickSort {

    public static void main(String[] args) {

        int[] tamanios = {10, 1000, 100000, 1000000,10000000};

        for (int tam : tamanios) {
            System.out.println("Tamaño: " + tam);

            // Generar arreglo aleatorio
            int[] original = generarArregloAleatorio(tam);
            
            // Secuencial
            int[] copiaSecuencial = Arrays.copyOf(original, original.length);
            long inicioSecuencial = System.currentTimeMillis();
            QuickSortSecuencial.quicksort(copiaSecuencial, 0, copiaSecuencial.length - 1);
            long finSecuencial = System.currentTimeMillis();
            System.out.println("Tiempo secuencial: " + (finSecuencial - inicioSecuencial) + " ms");

            // Concurrente
            int[] copiaConcurrente = Arrays.copyOf(original, original.length);
            long inicioConcurrente = System.currentTimeMillis();
            ForkJoinPool.commonPool().invoke(new QuickSortConcurrente(0, copiaConcurrente.length - 1, copiaConcurrente));
            long finConcurrente = System.currentTimeMillis();
            System.out.println("Tiempo concurrente: " + (finConcurrente - inicioConcurrente) + " ms");

            System.out.println("---------------------------");
        }
    }
	
    // Genera arreglo aleatorio
    private static int[] generarArregloAleatorio(int tam) {
        Random r = new Random();
        int[] arr = new int[tam];
        for (int i = 0; i < tam; i++) {
            arr[i] = r.nextInt(100000); // valores entre 0 y 99.999
        }
        return arr;
    }

    private static int[] generarArregloOrdenado(int tam) {
        int[] arr = new int[tam];
        for (int i = 0; i < tam; i++) arr[i] = i;
        return arr;
    }
    
    private static int[] generarArregloInvertido(int tam) {
        int[] arr = new int[tam];
        for (int i = 0; i < tam; i++) arr[i] = tam - i;
        return arr;
    }
}
