package org.example;

import java.util.Arrays;

import sorting.algorithms.QuickSort;
import sorting.criacaoListas.listaAleatoria;

public class Main {
    public static void main(String[] args) {
        listaAleatoria lista = new listaAleatoria();
        var listaAleatoria = lista.generateRandomList(1000);
        QuickSort quickSort = new QuickSort();
        quickSort.ordenar(listaAleatoria);

        
        System.out.println("Lista ordenada: " + Arrays.toString(listaAleatoria));
    }
}