package sorting.criacaoListas;

import java.util.Random;

public class listaAleatoria {

    // Gera uma lista com valores aleatórios (distribuição uniforme)
    public int[] generateRandomList(int size) {
        int[] list = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list[i] = random.nextInt(size * 10); // Intervalo: 0 a size*10 (para incluir repetições)
        }
        return list;
    }
}
