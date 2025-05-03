package sorting.criacaoListas;

import java.util.ArrayList;
import java.util.List;

public class listaCrescente {
    public listaCrescente(int i) {
        //TODO Auto-generated constructor stub
    }
    // Gera uma lista ordenada em ordem crescente
    public static List<Integer> generateAscendingList(int size) {
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }
}
