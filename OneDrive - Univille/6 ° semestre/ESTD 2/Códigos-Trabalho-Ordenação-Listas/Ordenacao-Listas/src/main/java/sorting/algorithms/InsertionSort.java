package sorting.algorithms;

public class InsertionSort implements sorting.interfaceListas.Isort {
    @Override
    public int[] ordenar(int[] elementos) {
        int n = elementos.length;
        for (int i = 1; i < n; ++i) {
            int key = elementos[i];
            int j = i - 1;

            // Mexe os elementos do elementos que são maiores
            while (j >= 0 && elementos[j] > key) {
                elementos[j + 1] = elementos[j];
                j = j - 1;
            }
            elementos[j + 1] = key;
        }
        return elementos;
    }

    @Override
    public String getName() {
        return "InsertionSort";
    }
    
}
