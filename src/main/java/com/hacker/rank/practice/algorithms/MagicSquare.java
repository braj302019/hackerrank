package com.hacker.rank.practice.algorithms;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/*
 * We define a magic square to be an n x n matrix of distinct positive integers from 1 to n^2
 * where the sum of any row, column, or diagonal of length n is always equal to the same number: the magic constant.
 *
 * You will be given a 3 x 3 matrix  of integers in the inclusive range [1,9].
 * We can convert any digit a to any other digit b in the range [1-9] at cost of |a-b|.
 * Given s, convert it into a magic square at minimal cost.
 * Print this cost on a new line.
 */
public class MagicSquare
{

   static int formingMagicSquare(int[][] s)
   {
      //TODO
      return 0;
   }

   static boolean isMagicSquare(int[][] s)
   {
      long firstRowSum = rowSum(s, 0);
      for (int i = 1; i < s.length; i++)
      {
         if (firstRowSum != rowSum(s, i))
         {
            return false;
         }
      }

      long firstColSum = columnSum(s, 0);
      for (int i = 1; i < s.length; i++)
      {
         if (firstColSum != columnSum(s, i))
         {
            return false;
         }
      }

      if (topLeftToBottomRightDiagonalSum(s) != topRightToBottomLeftDiagonalSum(s))
      {
         return false;
      }

      return true;
   }

   static long rowSum(int[][] s, int row)
   {
      int sum = 0;
      for (int col = 0; col < s[row].length; col++)
      {
         sum += s[row][col];
      }
      return sum;
   }

   static long columnSum(int[][] s, int col)
   {
      int sum = 0;
      for (int row = 0; row < s.length; row++)
      {
         sum += s[row][col];
      }
      return sum;
   }

   static long topLeftToBottomRightDiagonalSum(int[][] s)
   {
      int sum = 0;
      for (int row = 0; row < s.length; row++)
      {
         sum += s[row][row];
      }
      return sum;
   }

   static long topRightToBottomLeftDiagonalSum(int[][] s)
   {
      int sum = 0;
      int col = s.length - 1;
      for (int row = 0; row < s.length; row++)
      {
         sum += s[row][col--];
      }
      return sum;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      int[][] s = new int[3][3];

      for (int i = 0; i < 3; i++)
      {
         String[] sRowItems = scanner.nextLine().split(" ");
         scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

         for (int j = 0; j < 3; j++)
         {
            int sItem = Integer.parseInt(sRowItems[j]);
            s[i][j] = sItem;
         }
      }

      int result = formingMagicSquare(s);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
