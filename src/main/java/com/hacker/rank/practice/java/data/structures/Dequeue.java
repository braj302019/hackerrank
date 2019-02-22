package com.hacker.rank.practice.java.data.structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * In computer science, a double-ended queue (dequeue) is an abstract data type that generalizes a queue,
 * for which elements can be added to or removed from either the front (head) or back (tail).
 *
 * Deque interfaces can be implemented using various types of collections such as LinkedList or ArrayDeque classes.
 */
/*
 * In this problem, you are given n integers. You need to find the maximum number of unique integers among all the possible contiguous subarrays of size m.
 */
public class Dequeue
{

   void insertFirst(Node node)
   {
      node.next = Node.nil.next; // set first node as next of the new node
      node.prev = Node.nil; // set NIL as prev of the new node

      Node.nil.next.prev = node; // set new node as prev of the first node
      Node.nil.next = node; // set new node as next of the NIL node
   }

   void insertLast(Node node)
   {
      node.next = Node.nil;
      node.prev = Node.nil.prev;

      Node.nil.prev.next = node;
      Node.nil.prev = node;
   }

   void remove(Node node)
   {
      if (node != Node.nil)
      {
         node.next.prev = node.prev;
         node.prev.next = node.next;
      }
   }

   void removeFirst()
   {
      remove(Node.nil.next);
   }

   void removeLast()
   {
      remove(Node.nil.prev);
   }

   Node getLast()
   {
      return Node.nil.prev;
   }

   Node getFirst()
   {
      return Node.nil.next;
   }

   void printAll()
   {
      System.out.println();
      Node node = Node.nil.next;
      while (node != Node.nil)
      {
         System.out.print(node.value + " ");
         node = node.next;
      }
      System.out.println();
   }

   boolean contains(int value)
   {
      Node node = Node.nil.next;
      while (node != Node.nil && node.value != value)
      {
         node = node.next;
      }
      return node != Node.nil;
   }

   Node getByIndex(int index)
   {
      Node node = Node.nil.next;
      for (int i = 0; i < index; i++)
      {
         node = node.next;
         if (node == Node.nil)
         {
            node = node.next;
         }
      }
      return node;
   }

   List<Integer> distinct(int n, int m)
   {
      /*
       * Use calculation done in previous subarray to find the unique in next subarray
       */
      Map<Integer, Integer> frequencyByValue = new HashMap<Integer, Integer>();
      List<Integer> distinct = new ArrayList<Integer>();
      Node node = Node.nil.next;
      Node first = node;
      for (int i = 0; i < m; i++)
      {
         Integer value = node.value;
         frequencyByValue.put(value, frequencyByValue.getOrDefault(value, 0) + 1);
         node = node.next;
      }
      distinct.add(frequencyByValue.size());
      for (int i = m; i < n; i++)
      {
         Integer firstValue = first.value;
         frequencyByValue.put(firstValue, frequencyByValue.getOrDefault(firstValue, 1) - 1);
         frequencyByValue.remove(firstValue, 0);
         first = first.next;

         Integer value = node.value;
         frequencyByValue.put(value, frequencyByValue.getOrDefault(value, 0) + 1);
         distinct.add(frequencyByValue.size());
         node = node.next;
      }
      return distinct;
   }

   public static void main(String[] args)
   {
      long start = System.currentTimeMillis();
      try (Scanner reader = new Scanner(System.in))
      {
         int n = reader.nextInt();
         int m = reader.nextInt();
         List<Integer> values = IntStream.range(0, n).mapToObj(i -> reader.nextInt()).collect(Collectors.toList());

         Dequeue dequeue = new Dequeue();
         values.stream().forEach(v -> dequeue.insertLast(new Node(v)));

         List<Integer> uniqueCount = dequeue.distinct(n, m);
         int max = uniqueCount.stream().max(Integer::compare).get();
         System.out.println(max);
      }
      System.out.println("took:" + (System.currentTimeMillis() - start) + "ms");
   }

   static class Node
   {
      static final Node nil = getSentinelNode();

      Node prev;
      Node next;
      Integer value;

      Node()
      {}

      Node(int value)
      {
         this.value = value;
      }

      static Node getSentinelNode()
      {
         Node nil = new Node();
         nil.prev = nil;
         nil.next = nil;
         return nil;
      }

      @Override
      public String toString()
      {
         return String.valueOf(value);
      }
   }
}
