package com.hacker.rank.tutorial.statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
 * The interquartile range of an array is the difference between its first (Q1) and third (Q3) quartiles.
 */
public class Day1_InterquartileRange
{
   private static void interquartileRange(int[] a)
   {
      int mid = a.length / 2;
      System.out.println(Arrays.toString(a));

      float q1 = median(a, 0, mid);
      float q3 = median(a, a.length % 2 == 0 ? mid : mid + 1, a.length);
      float interquartileRange = q3 - q1;

      System.out.println(interquartileRange);
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

   private static Data[] insertionSort(Data[] a)
   {
      for (int i = 0; i < a.length; i++)
      {
         int freq = a[i].frequency;
         int val = a[i].value;
         int j = i;
         while (j > 0 && a[j - 1].value > val)
         {
            a[j] = a[j - 1];
            j--;
         }
         a[j] = new Data(val, freq);
      }
      return a;
   }

   public static void main(String[] args)
   {
      try (Scanner reader = new Scanner(System.in))
      {
         int count = Integer.parseInt(reader.nextLine().trim());
         int[] a = IntStream.range(0, count).map(i -> reader.nextInt()).toArray();
         int[] f = IntStream.range(0, count).map(i -> reader.nextInt()).toArray();

         Data[] data = new Data[count];
         for (int i = 0; i < count; i++)
         {
            data[i] = new Data(a[i], f[i]);
         }

         data = insertionSort(data);

         List<Integer> e = new ArrayList<>();
         for (Data d : data)
         {
            for (int j = 0; j < d.frequency; j++)
            {
               e.add(d.value);
            }
         }

         int[] arr = e.stream().mapToInt(Integer::intValue).toArray();

         interquartileRange(arr);
      }
   }

   static class Data implements Comparable<Data>
   {
      int value;
      int frequency;

      Data(int value, int frequency)
      {
         this.value = value;
         this.frequency = frequency;
      }

      @Override
      public int compareTo(Data other)
      {
         return Integer.compare(this.value, other.value);
      }
   }
}
