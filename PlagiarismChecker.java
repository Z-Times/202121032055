package com.example.personalobj;


import java.io.*;
import java.util.*;

public class PlagiarismChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入原文文件路径：");
        String originalFilePath = scanner.nextLine();

        System.out.println("请输入抄袭版文件路径：");
        String plagiarizedFilePath = scanner.nextLine();

        System.out.println("请输入输出文件路径：");
        String outputFilePath = scanner.nextLine();

        try {
            String originalText = readFile(originalFilePath);
            String plagiarizedText = readFile(plagiarizedFilePath);

            double similarity = calculateSimilarity(originalText, plagiarizedText);

            writeResult(outputFilePath, similarity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static double calculateSimilarity(String text1, String text2) {
        // 这里简单地比较两份文本的相同词语数量
        String[] words1 = text1.split("\\s+");
        String[] words2 = text2.split("\\s+");

        int commonWords = 0;
        for (String word1 : words1) {
            for (String word2 : words2) {
                if (word1.equalsIgnoreCase(word2)) {
                    commonWords++;
                    break;
                }
            }
        }

        int totalWords = Math.max(words1.length, words2.length);
        return (double) commonWords / totalWords;
    }

    private static void writeResult(String filePath, double similarity) throws IOException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println("重复率：" + (similarity * 100) + "%");
        }
    }
}
