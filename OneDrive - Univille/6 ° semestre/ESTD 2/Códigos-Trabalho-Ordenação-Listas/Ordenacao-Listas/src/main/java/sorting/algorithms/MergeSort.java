package sorting.algorithms;

import java.util.Arrays;

import sorting.interfaceListas.Isort;

public class MergeSort implements Isort {
    @Override
    public int[] ordenar(int[] elementos) {
        if (elementos.length > 1) {
            // Divide o array em duas metades
            int mid = elementos.length / 2;
            int[] left = Arrays.copyOfRange(elementos, 0, mid);
            int[] right = Arrays.copyOfRange(elementos, mid, elementos.length);

            // Ordena recursivamente as duas metades
            ordenar(left);
            ordenar(right);

            // Combina as metades ordenadas
            merge(elementos, left, right);
        }
        return elementos;
    }

    private void merge(int[] elementos, int[] left, int[] right) {
        int i = 0; // Índice do subarray left
        int j = 0; // Índice do subarray right
        int k = 0; // Índice do array principal (elementos)

        // Compara elementos de left e right e insere o menor no array principal
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                elementos[k] = left[i];
                i++;
            } else {
                elementos[k] = right[j];
                j++;
            }
            k++;
        }

        // Copia elementos restantes de left (se houver)
        while (i < left.length) {
            elementos[k] = left[i];
            i++;
            k++;
        }

        // Copia elementos restantes de right (se houver)
        while (j < right.length) {
            elementos[k] = right[j];
            j++;
            k++;
        }
    }

    @Override
    public String getName() {
        return "MergeSort";
    }

}
