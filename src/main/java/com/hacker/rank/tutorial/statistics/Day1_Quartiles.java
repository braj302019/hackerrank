package com.hacker.rank.tutorial.statistics;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
 * sort the elements in non-decreasing order
 * median as Q2
 * median of lower half as Q1
 * median of upper half as Q3
 */
public class Day1_Quartiles
{
   private static void quartiles(int[] a)
   {
      a = insertionSort(a);

      int mid = a.length / 2;

      DecimalFormat df = new DecimalFormat("#.#");
      float q1 = median(a, 0, mid);
      float q2 = median(a, 0, a.length);
      float q3 = median(a, a.length % 2 == 0 ? mid : mid + 1, a.length);

      System.out.println(df.format(q1));
      System.out.println(df.format(q2));
      System.out.println(df.format(q3));
   }

   private static float median(int[] a, int start, int end)
   {
      int mid = (start + end) / 2;
      int count = end - start;
      if (count % 2 == 0)
      {
         return (a[mid - 1] + a[mid]) / 2.0f;
      }
      return a[mid];
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
