package com.hacker.rank.practice.java.data.structures.stack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.hacker.rank.practice.java.data.structures.Stack;

public class StackOperations
{

   static int[] getMaxValues(String[] queries)
   {
      List<Integer> res = new ArrayList<>();

      Stack<Integer> stack = new Stack<>(queries.length);
      Stack<Integer> maxValueStack = new Stack<>(queries.length);

      for (String query : queries)
      {
         String[] inputs = query.split(" ");
         int op = Integer.parseInt(inputs[0]);
         if (op == 1)
         {
            int value = Integer.parseInt(inputs[1]);
            stack.push(value);
            maxValueStack.push(Math.max(value, maxValueStack.isEmpty() ? 0 : maxValueStack.peek()));
         }
         else if (op == 2)
         {
            stack.pop();
            maxValueStack.pop();
         }
         else
         {
            res.add(maxValueStack.peek());
         }
      }

      return IntStream.range(0, res.size()).map(i -> res.get(i)).toArray();
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      int queriesCount = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      String[] queries = new String[queriesCount];

      for (int i = 0; i < queriesCount; i++)
      {
         String queriesItem = scanner.nextLine();
         queries[i] = queriesItem;
      }

      int[] res = getMaxValues(queries);

      for (int i = 0; i < res.length; i++)
      {
         System.out.println(res[i]);
      }

      scanner.close();
   }
}
