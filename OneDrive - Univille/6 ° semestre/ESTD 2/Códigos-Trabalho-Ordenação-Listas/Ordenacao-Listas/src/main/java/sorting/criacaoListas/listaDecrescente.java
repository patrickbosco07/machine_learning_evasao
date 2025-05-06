package sorting.criacaoListas;

public class listaDecrescente {
    
    // Gera uma lista ordenada em ordem decrescente
    public int[] generateDescendingList(int size) {
        int[] list = new int[size];
        for (int i = 0; i < size; i++) {
            list[i] = size - i;  // Gera 5, 4, 3, 2, 1 para size=5
        }
        return list;
    }
}
