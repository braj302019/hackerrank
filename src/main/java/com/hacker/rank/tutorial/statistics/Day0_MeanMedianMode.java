package com.hacker.rank.tutorial.statistics;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.IntStream;

/*
 * mean -> avg
 * median -> middle value, if even then avg of middle values
 * mode -> occurrences of any value
 */
public class Day0_MeanMedianMode
{
   private static void meanMedianMode(int[] a)
   {
      long sum = 0;
      float mean = 0;
      float median = 0;
      Map<Integer, Integer> mode = new TreeMap<>();

      int[] sorted = new int[a.length];
      for (int i = 0; i < a.length; i++)
      {
         orderedInsertion(sorted, i, a[i]);
         sum += a[i];
         mode.put(a[i], mode.getOrDefault(a[i], 0) + 1);
      }
      mean = round(1.0f * sum / a.length);
      System.out.println(mean);

      int mid = a.length / 2;
      if (a.length % 2 == 0)
         median = (sorted[mid - 1] + sorted[mid]) / 2.0f;
      else
         median = sorted[mid] / 2.0f;

      System.out.println(median);

      Map.Entry<Integer, Integer> max = mode.entrySet().stream().max(Map.Entry.comparingByValue()).get();
      System.out.println(max.getKey());
   }

   private static float round(float value)
   {
      return Math.round(10.0f * value) / 10.0f;
   }

   private static int orderedInsertion(int[] sorted, int index, int val)
   {
      int j = index;
      while (j > 0 && sorted[j - 1] > val)
      {
         sorted[j] = sorted[j - 1];
         j--;
      }
      sorted[j] = val;
      return j;
   }

   private static int[] readCountAndValues()
   {
      try (Scanner reader = new Scanner(System.in))
      {
         int count = Integer.parseInt(reader.nextLine().trim());
         return IntStream.range(0, count).map(i -> reader.nextInt()).toArray();
      }
   }

   public static void main(String[] args)
   {
      int[] values = readCountAndValues();
      meanMedianMode(values);
   }
}
