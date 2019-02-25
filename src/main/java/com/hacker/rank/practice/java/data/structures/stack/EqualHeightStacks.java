package com.hacker.rank.practice.java.data.structures.stack;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import com.hacker.rank.practice.java.data.structures.Stack;

public class EqualHeightStacks
{
   static class StackItem
   {
      int height;
      int totalHeight;

      StackItem(int height, int totalHeight)
      {
         this.height = height;
         this.totalHeight = totalHeight;
      }
   }

   static int equalStacks(int[] h1, int[] h2, int[] h3)
   {
      Stack<StackItem> s1 = new Stack<>(h1.length);
      Stack<StackItem> s2 = new Stack<>(h2.length);
      Stack<StackItem> s3 = new Stack<>(h3.length);

      fillStack(s1, h1);
      fillStack(s2, h2);
      fillStack(s3, h3);

      while ((!s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty())
         && (s1.peek().totalHeight != s2.peek().totalHeight || s2.peek().totalHeight != s3.peek().totalHeight))
      {
         Stack<StackItem> maxHeightStack = maxHeightStack(s1, s2, s3);
         maxHeightStack.pop();
      }

      return (s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) ? 0 : s1.peek().totalHeight;
   }

   private static void fillStack(Stack<StackItem> stack, int[] heights)
   {
      int t = 0;
      for (int i = heights.length - 1; i >= 0; i--)
      {
         t += heights[i];
         stack.push(new StackItem(heights[i], t));
      }
   }

   private static Stack<StackItem> maxHeightStack(Stack<StackItem>... stacks)
   {
      Stack<StackItem> maxHeightStack = stacks[0];
      for (int i = 1; i < stacks.length; i++)
      {
         if (stacks[i].peek().totalHeight > maxHeightStack.peek().totalHeight)
         {
            maxHeightStack = stacks[i];
         }
      }
      return maxHeightStack;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      String[] n1N2N3 = scanner.nextLine().split(" ");

      int n1 = Integer.parseInt(n1N2N3[0].trim());

      int n2 = Integer.parseInt(n1N2N3[1].trim());

      int n3 = Integer.parseInt(n1N2N3[2].trim());

      int[] h1 = new int[n1];

      String[] h1Items = scanner.nextLine().split(" ");

      for (int h1Itr = 0; h1Itr < n1; h1Itr++)
      {
         int h1Item = Integer.parseInt(h1Items[h1Itr].trim());
         h1[h1Itr] = h1Item;
      }

      int[] h2 = new int[n2];

      String[] h2Items = scanner.nextLine().split(" ");

      for (int h2Itr = 0; h2Itr < n2; h2Itr++)
      {
         int h2Item = Integer.parseInt(h2Items[h2Itr].trim());
         h2[h2Itr] = h2Item;
      }

      int[] h3 = new int[n3];

      String[] h3Items = scanner.nextLine().split(" ");

      for (int h3Itr = 0; h3Itr < n3; h3Itr++)
      {
         int h3Item = Integer.parseInt(h3Items[h3Itr].trim());
         h3[h3Itr] = h3Item;
      }

      int result = equalStacks(h1, h2, h3);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedWriter.close();
   }
}
