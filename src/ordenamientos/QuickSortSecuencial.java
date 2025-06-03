package ordenamientos;

public class QuickSortSecuencial {
	
	public void ordenar(int[] array) {
		quicksort(array,0,array.length - 1);
	}
	
	// Implementación clásica de QuickSort (recursiva)
	public static void quicksort(int[] array, int low, int high) {
		if(low < high) {
			
			//Elegimos un punto de particion, un pivote
			int pivot = partition(array,low,high);
			
			// Ordenamos recursivamente la parte izquierda y derecha
			quicksort(array,low,pivot-1);
			quicksort(array,pivot+1,high);
			
		}
	}
	
	private static int partition(int[] array, int low, int high) {
	    int mid = low + (high - low) / 2;
	    int pivot = array[mid];

	    // Intercambiar el pivote con el último para usar mismo código
	    swap(array, mid, high);

	    int i = low - 1;
	    for (int j = low; j < high; j++) {
	        if (array[j] <= pivot) {
	            i++;
	            swap(array, i, j);
	        }
	    }
	    swap(array, i + 1, high);
	    return i + 1;
	}

	private static void swap(int[] array, int i, int j) {
	    int temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	}
		
}


