package org.example;

import java.util.Arrays;

import sorting.SalvarTestes.FileManager;
import sorting.algorithms.*;
import sorting.criacaoListas.listaAleatoria;
import sorting.criacaoListas.listaCrescente;
import sorting.criacaoListas.listaDecrescente;
import sorting.interfaceListas.Isort;

import java.util.ArrayList;
import java.util.List;

public class Main {
    // Flag para controlar a execução de experimentos
    private static final boolean RUN_EXPERIMENTS = true; 

    public static void main(String[] args) {
        // Teste demonstrativo original (mantido)
        runDemoTest();

        // Executa os experimentos completos se a flag estiver ativa
        if(RUN_EXPERIMENTS) {
            runFullExperiments();
        }
    }

    private static void runDemoTest() {
        listaAleatoria lista = new listaAleatoria();
        int[] listaAleatoria = lista.generateRandomList(1000);
        QuickSort quickSort = new QuickSort();
        
        System.out.println("\n=== TESTE DEMONSTRATIVO ===");
        System.out.println("Algoritmo: " + quickSort.getName());
        System.out.println("Array antes: " + Arrays.toString(Arrays.copyOf(listaAleatoria, 10)) + " ..."); // Mostra só os primeiros 10
        
        long start = System.nanoTime();
        quickSort.ordenar(listaAleatoria);
        long end = System.nanoTime();
        
        System.out.println("Array após: " + Arrays.toString(Arrays.copyOf(listaAleatoria, 10)) + " ...");
        System.out.printf("Tempo: %.3f ms\n", (end - start)/1_000_000.0);
        System.out.println("Ordenado? " + (isSorted(listaAleatoria) ? "Sim" : "Não"));
    }

    private static void runFullExperiments() {
        System.out.println("\n=== INICIANDO EXPERIMENTOS ===");
        
        // Configurações dos experimentos
        int[] sizes = {1000, 10000};
        String[] distributions = {"random", "crescente", "decrescente"};
        Isort[] algorithms = {
            new BubbleSort(),
            new InsertionSort(),
            new SelectionSort(),
            new QuickSort(),
            new MergeSort()
        };

        // Executa todos os cenários
        for (Isort algorithm : algorithms) {
            for (int size : sizes) {
                for (String distribution : distributions) {
                    runExperiment(algorithm, size, distribution);
                }
            }
        }
    }

    private static void runExperiment(Isort algorithm, int size, String distribution) {
        List<Long> tempos = new ArrayList<>();
        listaAleatoria gerador = new listaAleatoria();
        listaCrescente geradorDois = new listaCrescente();
        listaDecrescente geradorTres = new listaDecrescente();

        for (int i = 0; i < 30; i++) {
            int[] lista = switch (distribution) {
                case "random" -> gerador.generateRandomList(size);
                case "crescente" -> geradorDois.generateAscendingList(size);
                case "decrescente" -> geradorTres.generateDescendingList(size);
                default -> new int[0];
            };

            long start = System.nanoTime();
            algorithm.ordenar(lista.clone()); // Clone para não afetar outras execuções
            long end = System.nanoTime();
            
            tempos.add(end - start);
            
            // Validação opcional (pode ser removida para melhor performance)
            if(!isSorted(lista)) {
                System.err.println("Erro na ordenação!");
            }
        }
        
        // Salva resultados
        FileManager.saveResults(
            algorithm.getName(),
            size,
            distribution,
            tempos
        );
        
        System.out.printf("Concluído: %-12s %6d elementos %-12s\n",
            algorithm.getName(), size, distribution);
    }

    // Métodos auxiliares mantidos
    private static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) return false;
        }
        return true;
    }
}