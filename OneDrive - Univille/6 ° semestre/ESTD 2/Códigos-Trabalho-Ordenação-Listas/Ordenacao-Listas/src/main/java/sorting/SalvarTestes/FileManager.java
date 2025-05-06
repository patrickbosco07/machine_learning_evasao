package sorting.SalvarTestes;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {

    public static void saveResults(String algorithm, int size, String distribution, List<Long> executionTimes) {
        String directory = "resultados/" + algorithm;
        String filename = directory + "/" + size + "_" + distribution + ".csv";

        try {
            // Cria o diretório se não existir
            Path dirPath = Paths.get(directory);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // Escreve os dados no CSV
            try (FileWriter writer = new FileWriter(filename)) {
                writer.write("Execucao,Tempo(ns)\n");
                for (int i = 0; i < executionTimes.size(); i++) {
                    writer.write(String.format("%d,%d\n", i + 1, executionTimes.get(i)));
                }

                // Adiciona média e desvio padrão
                double media = calculateAverage(executionTimes);
                double desvioPadrao = calculateStandardDeviation(executionTimes, media);
                writer.write(String.format("Media,%.2f\n", media));
                writer.write(String.format("DesvioPadrao,%.2f\n", desvioPadrao));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double calculateAverage(List<Long> times) {
        return times.stream().mapToLong(Long::longValue).average().orElse(0);
    }

    private static double calculateStandardDeviation(List<Long> times, double mean) {
        double variance = times.stream()
            .mapToDouble(time -> Math.pow(time - mean, 2))
            .average()
            .orElse(0);
        return Math.sqrt(variance);
    }
}