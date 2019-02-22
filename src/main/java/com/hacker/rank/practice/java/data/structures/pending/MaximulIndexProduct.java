package com.hacker.rank.practice.java.data.structures.pending;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * You are given a list of N numbers a1,a2,...an. For each element at position i (1<=i<=N), we define Left(i) and Right(i) as:
 * Left(i) = closest index j such that j < i and aj > ai. If no such j exists then Left(i) = 0.
 * Right(i) = closest index k such that k > i and ak > ai. If no such k exists then Right(i) = 0.
 * We define IndexProduct(i) = Left(i) * Right(i). You need to find out the maximum IndexProduct(i) among all i
 */
public class MaximulIndexProduct
{

   static class IndexedData implements Comparable<IndexedData>
   {
      int index;
      int data;

      IndexedData(int index, int data)
      {
         this.index = index;
         this.data = data;
      }

      @Override
      public int compareTo(IndexedData other)
      {
         return Integer.compare(this.data, other.data);
      }

      @Override
      public String toString()
      {
         return "[" + index + "]=" + data;
      }
   }

   static int orderedInsert(IndexedData arr[], int first, int last, int target)
   // insert target into arr such that arr[first..last] is sorted,
   // given that arr[first..last-1] is already sorted.
   // Return the position where inserted.
   {
      int i = last;
      while ((i > first) && (target < arr[i - 1].data))
      {
         arr[i] = arr[i - 1];
         i = i - 1;
      }
      arr[i] = new IndexedData(last, target);
      return i;
   }

   static long solve3(int[] arr)
   {
      IndexedData[] data = new IndexedData[arr.length];
      for (int i = 0; i < arr.length; i++)
      {
         data[i] = new IndexedData(i, arr[i]);
      }

      mergeSort(data, 0, data.length - 1);
      System.out.println(Arrays.toString(data));

      int[] originalToSortedIndexMapping = new int[arr.length];
      int index = 0;
      for (IndexedData x : data)
      {
         originalToSortedIndexMapping[x.index] = index++;
      }

      return -1;
   }

   static void mergeSort(IndexedData[] data, int p, int r)
   {
      // FIXME not working
      if (p < r)
      {
         int q = (p + r) / 2;
         mergeSort(data, p, q);
         mergeSort(data, q + 1, r);
         merge(data, p, q, r);
      }
   }

   static void merge(IndexedData[] data, int p, int q, int r)
   {
      int n1 = q - p + 1;
      int n2 = r - q;
      IndexedData[] left = new IndexedData[n1 + 1];
      IndexedData[] right = new IndexedData[n2 + 1];
      for (int i = 0; i < n1; i++)
      {
         left[i] = data[p + i];
      }
      left[n1] = new IndexedData(n1, Integer.MAX_VALUE);
      for (int i = 0; i < n2; i++)
      {
         right[i] = data[q + i + 1];
      }
      right[n2] = new IndexedData(n2, Integer.MAX_VALUE);

      int i = 0;
      int j = 0;
      for (int k = p; k < r; k++)
      {
         if (left[i].data < right[j].data)
         {
            data[k] = left[i];
            i++;
         }
         else
         {
            data[k] = right[j];
            j++;
         }
      }

   }

   static long solve2(int[] arr)
   {
      long startTime = System.currentTimeMillis();
      IndexedData[] sorted = new IndexedData[arr.length];
      int[] lefts = new int[arr.length];
      for (int i = 0; i < arr.length; i++)
      {
         int index = orderedInsert(sorted, 0, i, arr[i]);

         for (int j = index + 1; j <= i; j++)
         {
            if (sorted[j].index < i && sorted[j].data > arr[i])
            {
               lefts[i] = sorted[j].index + 1;
               break;
            }
         }
      }
      System.out.println("time taken:" + (System.currentTimeMillis() - startTime));
      startTime = System.currentTimeMillis();

      int[] originalToSortedIndexMapping = new int[arr.length];
      int index = 0;
      for (IndexedData x : sorted)
      {
         originalToSortedIndexMapping[x.index] = index++;
      }
      System.out.println("time taken:" + (System.currentTimeMillis() - startTime));
      startTime = System.currentTimeMillis();

      long maxinumIndexProduct = 0;
      for (int i = 0; i < arr.length; i++)
      {
         int left = lefts[i];
         if (left == 0)
            continue;

         int right = 0;
         int sortedIndex = originalToSortedIndexMapping[i];
         for (int j = sortedIndex + 1; j < arr.length; j++)
         {
            if (sorted[j].index > i && sorted[j].data > arr[i])
            {
               right = sorted[j].index + 1;
               break;
            }
         }
         if (right == 0)
            continue;

         long indexProduct = left * right;
         if (indexProduct > maxinumIndexProduct)
         {
            maxinumIndexProduct = indexProduct;
         }
      }
      System.out.println("time taken:" + (System.currentTimeMillis() - startTime));
      return maxinumIndexProduct;
   }

   static long solve(int[] arr)
   {
      long startTime = System.currentTimeMillis();
      List<IndexedData> sorted = IntStream.range(0, arr.length).mapToObj(i -> new IndexedData(i, arr[i])).sorted().collect(Collectors.toList());

      System.out.println("time taken 1:" + (System.currentTimeMillis() - startTime));
      startTime = System.currentTimeMillis();

      int[] originalToSortedIndexMapping = new int[arr.length];
      int index = 0;
      for (IndexedData x : sorted)
      {
         originalToSortedIndexMapping[x.index] = index++;
      }

      System.out.println("time taken 2:" + (System.currentTimeMillis() - startTime));
      startTime = System.currentTimeMillis();

      long maxinumIndexProduct = 0;
      for (int i = 0; i < arr.length; i++)
      {
         int[] leftRight = leftRight(originalToSortedIndexMapping, sorted, arr[i], i);
         if (leftRight[0] == 0 || leftRight[1] == 0)
            continue;

         long indexProduct = leftRight[0] * leftRight[1];
         if (indexProduct > maxinumIndexProduct)
         {
            maxinumIndexProduct = indexProduct;
         }
      }
      System.out.println("time taken 3:" + (System.currentTimeMillis() - startTime));
      return maxinumIndexProduct;
   }

   static int[] leftRight(int[] originalToSortedIndexMapping, List<IndexedData> sorted, int value, int index)
   {
      if (index == 0)
         return new int[] { 0, 0 };

      int left = 0;
      int right = 0;
      int sortedIndex = originalToSortedIndexMapping[index];
      for (int i = sortedIndex + 1; i < sorted.size(); i++)
      {
         IndexedData x = sorted.get(i);
         if (x.data > value)
         {
            if (left == 0 && x.index < index)
               left = x.index + 1;

            if (right == 0 && x.index > index)
               right = x.index + 1;

            if (left > 0 && right > 0)
               break;
         }
      }

      return new int[] { left, right };
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      int arrCount = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] arr = new int[arrCount];

      String[] arrItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int arrItr = 0; arrItr < arrCount; arrItr++)
      {
         int arrItem = Integer.parseInt(arrItems[arrItr]);
         arr[arrItr] = arrItem;
      }

      long result = solve3(arr);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }

}
