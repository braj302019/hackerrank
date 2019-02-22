package com.hacker.rank.tutorial.statistics;

import java.util.Scanner;
import java.util.stream.IntStream;

/*
 * (sum of (value * weight)) / total weight
 */
public class Day0_WeightedMean
{
   private static void weightedMean(int[] a, int[] w)
   {
      long sum = 0;
      int totalWeight = 0;
      for (int i = 0; i < a.length; i++)
      {
         sum += (a[i] * w[i]);
         totalWeight += w[i];
      }
      float mean = round(1.0f * sum / totalWeight);
      System.out.println(mean);
   }

   private static float round(float value)
   {
      return Math.round(10.0f * value) / 10.0f;
   }

   public static void main(String[] args)
   {
      try (Scanner reader = new Scanner(System.in))
      {
         int count = Integer.parseInt(reader.nextLine().trim());
         int[] a = IntStream.range(0, count).map(i -> reader.nextInt()).toArray();
         int[] w = IntStream.range(0, count).map(i -> reader.nextInt()).toArray();
         weightedMean(a, w);
      }
   }
}
