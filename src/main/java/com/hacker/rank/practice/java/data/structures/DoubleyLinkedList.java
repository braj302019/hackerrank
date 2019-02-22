package com.hacker.rank.practice.java.data.structures;

public class DoubleyLinkedList<T>
{
   Node<T> nil = createSentinelNode();

   void addFirst(Node<T> node)
   {
      node.next = nil.next;
      node.prev = nil.prev;

      nil.next.prev = node;
      nil.next = node;
   }

   Node<T> removeLast()
   {
      Node<T> last = nil.prev;

      if (last != nil)
      {
         last.next.prev = last.prev;
         last.prev.next = last.next;
      }

      return last;
   }

   boolean isEmpty()
   {
      return nil.next == nil && nil.prev == nil;
   }

   Node<T> createSentinelNode()
   {
      Node<T> nil = new Node<T>();
      nil.prev = nil;
      nil.next = nil;
      return nil;
   }

   static class Node<T>
   {
      Node<T> prev;
      Node<T> next;
      T value;

      Node()
      {}

      Node(T value)
      {
         this.value = value;
      }

      @Override
      public String toString()
      {
         return String.valueOf(value);
      }
   }

}
