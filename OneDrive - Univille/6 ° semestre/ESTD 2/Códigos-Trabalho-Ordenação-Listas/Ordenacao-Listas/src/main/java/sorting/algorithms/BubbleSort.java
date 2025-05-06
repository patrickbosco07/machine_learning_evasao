package sorting.algorithms;

import sorting.interfaceListas.Isort;

public class BubbleSort implements Isort {
    @Override
    public int[] ordenar(int[] elementos) {
        int n = elementos.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (elementos[j] > elementos[j + 1]) {
                    // Troca de elemento
                    int temp = elementos[j];
                    elementos[j] = elementos[j + 1];
                    elementos[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return elementos;
    }

    @Override
    public String getName() {
        return "BubbleSort";
    }
}
