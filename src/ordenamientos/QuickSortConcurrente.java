package ordenamientos;

import java.util.Random;

import java.util.concurrent.RecursiveTask;

//Clase que extiende RecursiveTask para usar Fork/Join
public class QuickSortConcurrente extends RecursiveTask<Void> {

    int low, high;
    int[] array;

   

    public QuickSortConcurrente(int low, int high, int[] array) {
		this.low = low;
		this.high = high;
		this.array = array;
	}
    
    // Método para partir el arreglo y posicionar el pivote en su lugar
    private int partition(int[] arr,int low, int high) {
    	int i = low;
    	int j = high;
    	
        // Elegimos un índice aleatorio como pivote	
    	int pivot =new Random().nextInt(j-i+1)+i;
    	
    	// Intercambiamos el pivote con el último elemento
        int temp = arr[j];
        arr[j] = arr[pivot];
        arr[pivot] = temp;
        j--;	
        
        // Recorremos el arreglo para separar menores y mayores que el pivote
        while (i <= j) {
            if (arr[i] <= arr[high]) {
                i++;
                continue;
            }

            if (arr[j] >= arr[high]) {
                j--;
                continue;
            }

            // Intercambiamos elementos que están mal posicionados
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            j--;
            i++;
        }

        // Colocamos el pivote en su posición final
        temp = arr[j + 1];
        arr[j + 1] = arr[high];
        arr[high] = temp;

        return j + 1; // Devolvemos el índice final del pivote
    }
    
    @Override
	protected Void compute() {
    	
     // Caso base: si el subarreglo tiene un solo elemento o ninguno, no hacemos nada
    	if (low >= high) return null;
    	
        // Obtenemos el índice del pivote después de particionar
        int p = partition(array, low, high);
        
        // Creamos dos tareas nuevas: una para la izquierda y otra para la derecha del pivote
        QuickSortConcurrente izquierda = new QuickSortConcurrente(low,p-1,array);
        QuickSortConcurrente derecha = new QuickSortConcurrente(p+1,high,array);
        
        // Ejecutamos la parte izquierda en paralelo
        izquierda.fork();

        // Ejecutamos la parte derecha en el hilo actual
        derecha.compute();

        // Esperamos que termine la parte izquierda
        izquierda.join();

        return null;
    }
    	
}

