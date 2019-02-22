package com.hacker.rank.tutorial.statistics;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
 * Find the mean
 * calculate squared distance from mean for each (xi - m)^2 for each xi
 * Now compute square root of (sum of (xi - m)^2 divided by total no of elements)
 */
public class Day1_StandardDaviation
{
   private static void quartiles(int[] a)
   {
      a = insertionSort(a);

      DecimalFormat df = new DecimalFormat("#.#");
      float mean = mean(a);
      double sd = standardDaviation(a, mean);

      System.out.println(df.format(sd));
   }

   private static double standardDaviation(int[] a, float mean)
   {
      double sumOfSquareDistances = 0;
      for (int i = 0; i < a.length; i++)
      {
         sumOfSquareDistances += Math.pow(Math.abs(a[i] - mean), 2);
      }
      return Math.sqrt(sumOfSquareDistances / a.length);
   }

   private static float mean(int[] a)
   {
      long sum = 0;
      for (int i = 0; i < a.length; i++)
      {
         sum += a[i];
      }
      return (1.0f * sum) / a.length;
   }

   private static int[] insertionSort(int[] a)
   {
      for (int i = 0; i < a.length; i++)
      {
         int val = a[i];
         int j = i;
         while (j > 0 && a[j - 1] > val)
         {
            a[j] = a[j - 1];
            j--;
         }
         a[j] = val;
      }
      return a;
   }

   public static void main(String[] args)
   {
      try (Scanner reader = new Scanner(System.in))
      {
         int count = Integer.parseInt(reader.nextLine().trim());
         int[] a = IntStream.range(0, count).map(i -> reader.nextInt()).toArray();
         quartiles(a);
      }
   }
}
