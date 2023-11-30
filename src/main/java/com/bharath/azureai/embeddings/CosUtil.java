package com.bharath.azureai.embeddings;

import java.util.List;

public class CosUtil {

    public static double[] toArray(List<Double> list) {
        double[] arr = new double[list.size()];
        int i = 0;
        for (Double embedding : list) {
            arr[i++] = embedding;
        }
        return arr;
    }
    public static double cos(double[] vec1, double[] vec2) {
        return dot(vec1, vec2) / (norm(vec1) * norm(vec2));
    }

    public static double dot(double[] vec1, double[] vec2) {
        double sum = 0;
        for (int i = 0; i < vec1.length && i < vec2.length; i++)
            sum += vec1[i] * vec2[i];
        return sum;
    }

    public static double norm(double[] vec) {
        double sum = 0;
        for (double v : vec)
            sum += v * v;
        return Math.sqrt(sum);
    }
}
