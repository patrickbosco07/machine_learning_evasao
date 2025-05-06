package sorting.algorithms;

public class SelectionSort implements sorting.interfaceListas.Isort {
    @Override
    public int[] ordenar(int[] elementos) {
        int n = elementos.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (elementos[j] < elementos[minIdx]) {
                    minIdx = j;
                }
            }

            int temp = elementos[minIdx];
            elementos[minIdx] = elementos[i];
            elementos[i] = temp;
        }
        return elementos;
    }

    @Override
    public String getName() {
        return "SelectionSort";
    }
    
}
