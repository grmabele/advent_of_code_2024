package org.example;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class day01_part1 {

    public static void main(String[] args) {
        String fileName = "day01_part1_data.txt";

        try (InputStream inputStream = day01_part1.class.getClassLoader().getResourceAsStream(fileName)) {
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

            Collections.sort(leftList);
            Collections.sort(rightList);

            int totalDistance = 0;
            for (int i = 0; i < leftList.size(); i++) {
                totalDistance += Math.abs(leftList.get(i) - rightList.get(i));
            }

            System.out.println("Distance totale : " + totalDistance);
        } catch (IOException e) {
            System.err.println("Erreur : Impossible de lire le fichier " + fileName + " !");
            e.printStackTrace();
        }
    }
}
