package com.hacker.rank.practice.java.data.structures.linkedlist;

public class DoublyLinkedListOperations
{
   static class DoublyLinkedListNode
   {
      int data;
      DoublyLinkedListNode next;
      DoublyLinkedListNode prev;

      DoublyLinkedListNode(int data)
      {
         this.data = data;
      }
   }

   static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data)
   {
      DoublyLinkedListNode x = new DoublyLinkedListNode(data);

      if (head == null || head.data > data) // smallest
      {
         x.next = head;
         x.next.prev = x;
         head = x;
         return head;
      }

      DoublyLinkedListNode node = head;
      DoublyLinkedListNode y = null;
      while (node != null && node.data < data)
      {
         y = node;
         node = node.next;
      }

      if (node == null) // largest
      {
         y.next = x;
         x.prev = y;
      }
      else // in middle
      {
         x.prev = y;
         x.next = y.next;
         x.next.prev = x;
         x.prev.next = x;
      }
      return head;
   }

   static DoublyLinkedListNode reverse(DoublyLinkedListNode head)
   {
      DoublyLinkedListNode node = head;
      while (node.next != null)
      {
         DoublyLinkedListNode temp = node.prev;
         node.prev = node.next;
         node.next = temp;

         node = node.prev;
      }
      node.next = node.prev;
      node.prev = null;
      return node;
   }

   static void print(DoublyLinkedListNode node)
   {
      while (node != null)
      {
         System.out.println(node.data);
         node = node.next;
      }
   }

   public static void main(String[] args)
   {
      DoublyLinkedListNode head = new DoublyLinkedListNode(1);
      head.next = new DoublyLinkedListNode(3);
      head.next.prev = head;

      head.next.next = new DoublyLinkedListNode(4);
      head.next.next.prev = head.next;

      head.next.next.next = new DoublyLinkedListNode(10);
      head.next.next.next.prev = head.next.next;

      System.out.println("original");
      print(head);

      head = sortedInsert(head, 5);
      System.out.println("after sorted insert");
      print(head);

      head = reverse(head);
      System.out.println("after reverse");
      print(head);
   }
}
