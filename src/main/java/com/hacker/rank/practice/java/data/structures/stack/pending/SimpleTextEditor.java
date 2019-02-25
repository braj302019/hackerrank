package com.hacker.rank.practice.java.data.structures.stack.pending;

import java.io.IOException;
import java.util.Scanner;

import com.hacker.rank.practice.java.data.structures.Stack;

public class SimpleTextEditor
{

   static class StackItem
   {
      String data;
      int op;

      StackItem(String data, int op)
      {
         this.data = data;
         this.op = op;
      }
   }

   static char[] performOperations(String[] queries)
   {
      Stack<StackItem> minimalQueries = new Stack<>(queries.length);

      for (int i = queries.length - 1; i >= 0; i--)
      {
         String[] inputs = queries[i].split(" ");
         int op = Integer.parseInt(inputs[0]);

         if (op <= 2 && !minimalQueries.isEmpty() && minimalQueries.peek().op == 4)
            minimalQueries.pop();
         else
            minimalQueries.push(new StackItem(inputs.length > 1 ? inputs[1] : null, op));
      }

      Stack<String> textAfterUpdateAction = new Stack<>(queries.length);
      StringBuilder text = new StringBuilder();
      StringBuilder response = new StringBuilder();

      while (!minimalQueries.isEmpty())
      {
         StackItem item = minimalQueries.pop();
         int op = item.op;
         if (op == 1) // append in the end
         {
            text.append(item.data);
            textAfterUpdateAction.push(text.toString());
         }
         else if (op == 2) // delete last k chars
         {
            int k = Integer.parseInt(item.data);
            text.setLength(text.length() - k);
            textAfterUpdateAction.push(text.toString());
         }
         else if (op == 3) // print kth char
         {
            int k = Integer.parseInt(item.data);
            response.append(text.charAt(k - 1));
         }
         else // undo
         {
            textAfterUpdateAction.pop();
            text = new StringBuilder(!textAfterUpdateAction.isEmpty() ? textAfterUpdateAction.peek() : "");
         }
      }
      return response.toString().toCharArray();
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

      long start = System.currentTimeMillis();
      char[] res = performOperations(queries);
      long timeTaken = System.currentTimeMillis() - start;
      for (int i = 0; i < res.length; i++)
      {
         System.out.println(res[i]);
      }
      System.out.println("time taken:" + timeTaken);
      scanner.close();
   }
}
