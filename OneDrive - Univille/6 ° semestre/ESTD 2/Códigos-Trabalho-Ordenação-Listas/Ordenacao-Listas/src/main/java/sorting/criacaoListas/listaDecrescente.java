package sorting.criacaoListas;

import java.util.ArrayList;
import java.util.List;

public class listaDecrescente {
    public listaDecrescente(int i) {
        //TODO Auto-generated constructor stub
    }
    // Gera uma lista ordenada em ordem decrescente
    public static List<Integer> generateDescendingList(int size) {
        List<Integer> list = new ArrayList<>(size);
        for (int i = size - 1; i >= 0; i--) {
            list.add(i);
        }
        return list;
    }
}
