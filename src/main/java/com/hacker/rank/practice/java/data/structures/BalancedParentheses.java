package com.hacker.rank.practice.java.data.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * a stack or LIFO (last in, first out) is an abstract data type that serves as a collection of elements
 *
 * A string containing only parentheses is balanced if the following is true:
 * 1. if it is an empty string
 * 2. if A and B are correct, AB is correct,
 * 3. if A is correct, (A) and {A} and [A] are also correct.
 */
public class BalancedParentheses
{

   public static void main(String[] args)
   {
      List<String> lines = readLines();
      lines.forEach(l -> System.out.println(isBalanced(l)));
   }

   private static List<String> readLines()
   {
      List<String> lines = new ArrayList<>();
      try (Scanner reader = new Scanner(System.in))
      {
         while (reader.hasNext()) // Use Ctrl + Z to send EOF when taking inputs from Eclipse console
         {
            lines.add(reader.next());
         }
      }
      return lines;
   }

   private static boolean isBalanced(String line)
   {
      Stack<Character> stack = new Stack<>(line.length());
      Stack<Character> closingStack = new Stack<>(line.length());
      for (char ch : line.toCharArray())
      {
         stack.push(ch);
      }
      while (!stack.isEmpty())
      {
         char ch = stack.pop();
         if (isOpeningChar(ch))
         {
            if (closingStack.isEmpty())
            {
               return false;
            }
            char closingChar = closingStack.pop();
            if (!isMatchingParentheses(ch, closingChar))
            {
               return false;
            }
         }
         else if (isClosingChar(ch))
         {
            closingStack.push(ch);
         }
      }
      return stack.isEmpty() && closingStack.isEmpty();
   }

   private static boolean isOpeningChar(char ch)
   {
      return ch == '(' || ch == '{' || ch == '[';
   }

   private static boolean isClosingChar(char ch)
   {
      return ch == ')' || ch == '}' || ch == ']';
   }

   private static boolean isMatchingParentheses(char opening, char closing)
   {
      return (opening == '(' && closing == ')') || (opening == '{' && closing == '}') || (opening == '[' && closing == ']');
   }

}
