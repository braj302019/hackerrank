package com.hacker.rank.practice.java.data.structures.queue;

/*
 * Heap is a complete binary tree that is good choice for priority queue
 */
public class MinPriorityQueue
{

   // (binary) heap data structure is nearly complete binary tree
   // it can be represented in array where elements are accessed in level from top to bottom and from left to right
   final int[] heap;
   final int length; // number of elements in array
   int heapSize; // valid values from 0 to heapSize - 1 that are considered as valid for heap

   MinPriorityQueue(int size)
   {
      length = size;
      heap = new int[size];
   }

   int size()
   {
      return heapSize;
   }

   boolean isEmpty()
   {
      return heapSize == 0;
   }

   int parent(int i)
   {
      return (int) Math.ceil(i / 2.0) - 1;
   }

   int left(int i)
   {
      return 2 * i + 1;
   }

   int right(int i)
   {
      return 2 * i + 2;
   }

   int minimum() // => O(1)
   {
      return heap[0];
   }

   int extraxtMin() // => O(logn)
   {
      if (heapSize < 1)
         throw new RuntimeException("underflow");

      int min = heap[0];

      heap[0] = heap[heapSize - 1]; // last element assigned to root
      heapSize--; // discard the last element
      minHeapify(0); // minHeapify to maintain the heap property

      return min;
   }

   void minHeapify(int i) // => O(logn)
   {
      int left = left(i);
      int right = right(i);
      int smallest = i;

      if (left < heapSize && Integer.compare(heap[left], heap[smallest]) < 0)
      {
         smallest = left;
      }
      if (right < heapSize && Integer.compare(heap[right], heap[smallest]) < 0)
      {
         smallest = right;
      }

      if (smallest != i)
      {
         exchange(i, smallest);
         minHeapify(smallest); // recursive minHeapify
      }
   }

   void exchange(int i, int j)
   {
      int temp = heap[i];
      heap[i] = heap[j];
      heap[j] = temp;
   }

   void insert(int value) // => O(logn)
   {
      if (heapSize >= length)
         throw new RuntimeException("overflow");

      heapSize++;
      heap[heapSize - 1] = value;

      // Note: below steps to check all the parents instead of calling minHeapify
      int i = heapSize - 1;
      while (i > 0 && Integer.compare(heap[parent(i)], heap[i]) > 0)
      {
         exchange(parent(i), i);
         i = parent(i);
      }
   }

   void delete(int value) // => O(nlogn)
   {
      int index = 0;
      while (index < heapSize)
      {
         if (heap[index] == value)
         {
            break;
         }
         index++;
      }
      exchange(index, heapSize - 1);
      heapSize--;
      // Note: Important step to call minHeapify for all its successor
      for (int i = index; i >= 0; i--)
      {
         minHeapify(i);
      }
   }

}
