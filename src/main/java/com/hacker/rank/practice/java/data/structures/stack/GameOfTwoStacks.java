package com.hacker.rank.practice.java.data.structures.stack;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameOfTwoStacks
{

   static int twoStacks(int x, int[] a, int[] b)
   {
      List<Integer> aSum = new ArrayList<>();
      int aSteps = 0;
      int sum = 0;
      for (int i : a)
      {
         sum += i;
         if (sum <= x)
         {
            aSteps++;
            aSum.add(sum);
         }
         else
            break;
      }

      List<Integer> bSum = new ArrayList<>();
      int bSteps = 0;
      sum = 0;
      for (int i : b)
      {
         sum += i;
         if (sum <= x)
         {
            bSteps++;
            bSum.add(sum);
         }
         else
            break;
      }

      int steps = 0;
      for (int i = 0; i < aSum.size(); i++)
      {
         int rem = x - aSum.get(i);
         int rightSteps = findInsertPosition(bSum, rem);
         steps = Math.max(steps, rightSteps + i + 1);
      }

      return Math.max(steps, Math.max(aSteps, bSteps));
   }

   static int findInsertPosition(List<Integer> array, int x)
   {
      int lo = 0;
      int hi = array.size();
      while (lo < hi)
      {
         int mid = (lo + hi) / 2;
         if (array.get(mid) > x)
            hi = mid;
         else
            lo = mid + 1;
      }
      return lo;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      int g = Integer.parseInt(scanner.nextLine().trim());

      for (int gItr = 0; gItr < g; gItr++)
      {
         String[] nmx = scanner.nextLine().split(" ");

         int n = Integer.parseInt(nmx[0].trim());

         int m = Integer.parseInt(nmx[1].trim());

         int x = Integer.parseInt(nmx[2].trim());

         int[] a = new int[n];

         String[] aItems = scanner.nextLine().split(" ");

         for (int aItr = 0; aItr < n; aItr++)
         {
            int aItem = Integer.parseInt(aItems[aItr].trim());
            a[aItr] = aItem;
         }

         int[] b = new int[m];

         String[] bItems = scanner.nextLine().split(" ");

         for (int bItr = 0; bItr < m; bItr++)
         {
            int bItem = Integer.parseInt(bItems[bItr].trim());
            b[bItr] = bItem;
         }

         int result = twoStacks(x, a, b);

         bufferedWriter.write(String.valueOf(result));
         bufferedWriter.newLine();
      }

      bufferedWriter.close();
   }
}
