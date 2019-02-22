package com.hacker.rank.practice.java.data.structures;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/*
 * A linked list is said to contain a cycle if any node is visited more than once while traversing the list.
 */
public class CyclcDetection
{

   static boolean hasCycle(SinglyLinkedListNode head)
   {
      if (head == null || head.next == null)
         return false;

      SinglyLinkedListNode slow = head;
      SinglyLinkedListNode fast = head.next;
      while (slow != null && fast!= null)
      {
         if (slow == fast)
            return true;

         if (fast.next == null)
            return false;

         slow = slow.next;
         fast = fast.next.next;
      }
      return false;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      int tests = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int testsItr = 0; testsItr < tests; testsItr++)
      {
         int index = scanner.nextInt();
         scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

         SinglyLinkedList llist = new SinglyLinkedList();

         int llistCount = scanner.nextInt();
         scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

         for (int i = 0; i < llistCount; i++)
         {
            int llistItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            llist.insertNode(llistItem);
         }

         SinglyLinkedListNode extra = new SinglyLinkedListNode(-1);
         SinglyLinkedListNode temp = llist.head;

         for (int i = 0; i < llistCount; i++)
         {
            if (i == index)
            {
               extra = temp;
            }

            if (i != llistCount - 1)
            {
               temp = temp.next;
            }
         }

         temp.next = extra;

         boolean result = hasCycle(llist.head);

         bufferedWriter.write(String.valueOf(result ? 1 : 0));
         bufferedWriter.newLine();
      }

      bufferedWriter.close();

      scanner.close();
   }

   static class SinglyLinkedList
   {
      SinglyLinkedListNode head;

      void insertNode(int llistItem)
      {
         SinglyLinkedListNode node = new SinglyLinkedListNode(llistItem);
         if (head != null)
         {
            node.next = head.next;
         }
         head = node;
      }
   }

   static class SinglyLinkedListNode
   {
      SinglyLinkedListNode next;
      int data;

      SinglyLinkedListNode()
      {}

      SinglyLinkedListNode(int data)
      {
         this.data = data;
      }

      @Override
      public String toString()
      {
         return String.valueOf(data);
      }
   }
}
