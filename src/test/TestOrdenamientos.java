package test;

import java.util.concurrent.ForkJoinPool;

import ordenamientos.QuickSortSecuencial;
import ordenamientos.QuickSortConcurrente;

public class TestOrdenamientos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        /*int[] arreglo = {9, 3, 5, 1, 7, 4, 6, 2, 8};
        QuickSortSecuencial qs = new QuickSortSecuencial();
        qs.ordenar(arreglo);

        System.out.println("Arreglo ordenado:");
        for (int num : arreglo) {
            System.out.print(num + " ");
            
        }
        */
		
        // Creamos un arreglo de prueba
        int[] arreglo = { 45, 12, 78, 34, 23, 89, 56 };

        // Creamos un ForkJoinPool (puede ser el común)
        ForkJoinPool pool = ForkJoinPool.commonPool();

        // Ejecutamos el QuickSortConcurrente
        pool.invoke(new QuickSortConcurrente(0, arreglo.length - 1, arreglo));

        // Imprimimos el arreglo ordenado
        System.out.println("Arreglo ordenado:");
        for (int num : arreglo) {
            System.out.print(num + " ");
        }
		
		
	}

}
