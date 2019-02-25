package com.hacker.rank.practice.java.data.structures.queue;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class LeastSweettenCookie
{

   static int cookies(int k, int[] A)
   {
      MinPriorityQueue queue = new MinPriorityQueue(A.length);

      for (int a : A)
      {
         queue.insert(a);
      }

      if (!queue.isEmpty() && queue.minimum() >= k)
      {
         return 0; // best case where all the cookies are already sweeet enough
      }

      int steps = 0;
      boolean found = false;
      while (queue.size() > 1)
      {
         steps++;
         int mixedCookie = queue.extraxtMin() + (2 * queue.extraxtMin());
         queue.insert(mixedCookie);

         if (queue.minimum() >= k)
         {
            found = true;
            break;
         }
      }
      return found ? steps : -1;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      String[] nk = scanner.nextLine().split(" ");

      int n = Integer.parseInt(nk[0].trim());

      int k = Integer.parseInt(nk[1].trim());

      int[] A = new int[n];

      String[] AItems = scanner.nextLine().split(" ");

      for (int AItr = 0; AItr < n; AItr++)
      {
         int AItem = Integer.parseInt(AItems[AItr].trim());
         A[AItr] = AItem;
      }

      int result = cookies(k, A);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedWriter.close();
   }
}
