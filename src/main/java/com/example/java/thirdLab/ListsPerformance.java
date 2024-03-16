package com.example.java.thirdLab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Проверка перфоманса (? не понял тз, через subList совсем другие результаты будут)
 * @author o_agafon
 * @version 1.0
 * @since 1.0
 */
public class ListsPerformance {
    public static void main(String[] args) {
        testCollection("ArrayList", new ArrayList<>());
        testCollection("LinkedList", new LinkedList<>());
    }

    private static void testCollection(String collectionName, List<Integer> list) {
        System.out.println("Testing " + collectionName + "...");

        long startTime, endTime;
        int iterations = 1000;

        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            list.add(i);
        }
        endTime = System.nanoTime();
        long addTime = endTime - startTime;

        startTime = System.nanoTime();
        for (int i = 1; i < iterations; i++) {
            list.get(list.size() % i);
        }
        endTime = System.nanoTime();
        long getTime = endTime - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            list.remove(0);
        }
        endTime = System.nanoTime();
        long removeTime = endTime - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            list.contains(i);
        }
        endTime = System.nanoTime();
        long containsTime = endTime - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            list.indexOf(i);
        }
        endTime = System.nanoTime();
        long indexOfTime = endTime - startTime;

        startTime = System.nanoTime();
        list.clear();
        endTime = System.nanoTime();
        long clearTime = endTime - startTime;


        System.out.println("Results for " + collectionName + ":");
        System.out.println("Add time: " + addTime + " ns");
        System.out.println("Remove time: " + removeTime + " ns");
        System.out.println("Get time: " + getTime + " ns");
        System.out.println("Contains time: " + containsTime + " ns");
        System.out.println("IndexOf time: " + indexOfTime + " ns");
        System.out.println("Clear time: " + clearTime + " ns");
        System.out.println();
    }
}
