package org.example;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class day01_part2 {

    public static void main(String[] args) {
        String fileName = "day01_part1_data.txt";

        try (InputStream inputStream = day01_part2.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                System.err.println("Erreur : Le fichier " + fileName + " n'a pas été trouvé !");
                return;
            }
            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            String[] lines = content.split("\n");

            List<Integer> leftList = new ArrayList<>();
            List<Integer> rightList = new ArrayList<>();

            for (String line : lines) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    leftList.add(Integer.parseInt(parts[0]));
                    rightList.add(Integer.parseInt(parts[1]));
                }
            }

            // Calcul de la fréquence de chaque nombre dans la liste de droite
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            for (int number : rightList) {
                frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
            }

            // Calcul du score de similarité
            int similarityScore = 0;
            for (int number : leftList) {
                if (frequencyMap.containsKey(number)) {
                    similarityScore += number * frequencyMap.get(number);
                }
            }

            System.out.println("Score de similarité : " + similarityScore);
        } catch (IOException e) {
            System.err.println("Erreur : Impossible de lire le fichier " + fileName + " !");
            e.printStackTrace();
        }
    }
}
