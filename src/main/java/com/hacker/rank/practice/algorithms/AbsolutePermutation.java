package com.hacker.rank.practice.algorithms;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.IntStream;

/*
 * We define P to be a permutation of the first n natural numbers in the range . Let pos[i] denote the value at position i in permutation P using 1-based indexing.
 *
 * P is considered to be an absolute permutation if |pos[i]-i| = k holds true for every i in [1,n].
 *
 * Given n and k, print the lexicographically smallest absolute permutation P. If no absolute permutation exists, print -1.
 *
 */
public class AbsolutePermutation
{
   public void AbsolutePermutation(){}

   static int[] absolutePermutation(int n, int k)
   {
      if (k == 0)
      {
         return IntStream.range(1, n + 1).toArray();
      }
      if (n == 1 || n % k != 0)
      {
         return new int[] { -1 };
      }

      int[] permutation = new int[n];
      Map<Integer, Integer> ranges = new TreeMap<>();
      for (int i = 0; i < n; i += k)
      {
         ranges.put(i + 1, i + k);
      }
      int index = 0;
      for (int i = 1; i <= n; i += k)
      {
         int rangeSetStart = getNextRangeSetStartPosition(ranges, i, k);
         if (rangeSetStart == -1)
         {
            return new int[] { -1 };
         }
         int start = rangeSetStart;
         int end = ranges.get(rangeSetStart);
         for (int j = start; j <= end; j++)
         {
            permutation[index++] = j;
         }
         ranges.remove(start);
      }
      return permutation;
   }

   static int getNextRangeSetStartPosition(Map<Integer, Integer> ranges, int posStart, int k)
   {
      for (Map.Entry<Integer, Integer> e : ranges.entrySet())
      {
         int rangeStart = e.getKey();
         int diff = Math.min(Math.abs(posStart - rangeStart), posStart + rangeStart);
         if (diff == k)
         {
            return rangeStart;
         }
      }
      return -1;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      int t = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int tItr = 0; tItr < t; tItr++)
      {
         String[] nk = scanner.nextLine().split(" ");

         int n = Integer.parseInt(nk[0]);

         int k = Integer.parseInt(nk[1]);

         int[] result = absolutePermutation(n, k);

         for (int i = 0; i < result.length; i++)
         {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1)
            {
               bufferedWriter.write(" ");
            }
         }

         bufferedWriter.newLine();
      }

      bufferedWriter.close();

      scanner.close();
   }
}
