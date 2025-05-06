package sorting.criacaoListas;

public class listaCrescente {
    
    // Gera uma lista ordenada em ordem crescente
    public int[] generateAscendingList(int size) {
        int[] list = new int[size];
        for (int i = 0; i < size; i++) {
            list[i] = i;
        }
        return list;
    }
}
