package com.hacker.rank.practice.java.data.structures.queue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MinHeapOperations
{

   static int[] getMinValues(String[] queries)
   {
      List<Integer> res = new ArrayList<>();

      MinPriorityQueue queue = new MinPriorityQueue(queries.length);

      for (String query : queries)
      {
         String[] inputs = query.split(" ");
         int op = Integer.parseInt(inputs[0]);

         if (op == 1)
            queue.insert(Integer.parseInt(inputs[1]));
         else if (op == 2)
            queue.delete(Integer.parseInt(inputs[1]));
         else
            res.add(queue.minimum());
      }
      return IntStream.range(0, res.size()).map(i -> res.get(i)).toArray();
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      int queriesCount = scanner.nextInt();
      scanner.nextLine();

      String[] queries = new String[queriesCount];

      for (int i = 0; i < queriesCount; i++)
      {
         String queriesItem = scanner.nextLine();
         queries[i] = queriesItem.trim();
      }

      int[] res = getMinValues(queries);

      for (int i = 0; i < res.length; i++)
      {
         System.out.println(res[i]);
      }

      scanner.close();
   }
}
