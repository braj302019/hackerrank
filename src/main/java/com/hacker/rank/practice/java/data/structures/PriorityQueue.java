package com.hacker.rank.practice.java.data.structures;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * prints the names of the students that are not served yet in the priority order. If there are no such student, then the code prints EMPTY.
 */
public class PriorityQueue
{

   // (binary) heap data structure is nearly complete binary tree
   // it can be represented in array where elements are accessed in level from top to bottom and from left to right
   final Student[] heap;
   final int length; // number of elements in array
   int heapSize; // valid values from 0 to heapSize - 1 that are considered as valid for heap

   PriorityQueue(int size)
   {
      length = size;
      heap = new Student[size];
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

   Student extraxtMax()
   {
      if (heapSize < 1)
         throw new RuntimeException("underflow"); // TODO comment to run the program as per output

      Student max = heap[0];

      heap[0] = heap[heapSize - 1]; // last element assigned to root
      heapSize--; // discard the last element
      maxHeapify(0); // maxHeapify to maintain the heap property

      return max;
   }

   void maxHeapify(int i)
   {
      int left = left(i);
      int right = right(i);
      int largest = i;

      if (left < heapSize && heap[left].compareTo(heap[largest]) > 0)
      {
         largest = left;
      }
      if (right < heapSize && heap[right].compareTo(heap[largest]) > 0)
      {
         largest = right;
      }

      if (largest != i)
      {
         exchange(i, largest);
         maxHeapify(largest); // recursive maxHeapify
      }
   }

   void exchange(int i, int j)
   {
      Student temp = heap[i];
      heap[i] = heap[j];
      heap[j] = temp;
   }

   void insert(Student student)
   {
      if (heapSize >= length)
         throw new RuntimeException("overflow");

      heapSize++;
      heap[heapSize - 1] = student;

      // Note: below steps to check all the parents instead of calling maxHeapify only
      int i = heapSize - 1;
      while (i > 0 && heap[parent(i)].compareTo(heap[i]) < 0)
      {
         exchange(parent(i), i);
         i = parent(i);
      }
   }

   List<Student> getStudents(List<String> events)
   {
      for (String event : events)
      {
         if (event.startsWith(Event.SERVED.name()))
         {
            // if (heapSize > 0) // TODO uncomment to run the program as per output
            // {
            extraxtMax();
            // }
         }
         else if (event.startsWith(Event.ENTER.name()))
         {
            String[] values = event.split(" "); // ENTER name CGPA id

            String name = values[1];
            int id = Integer.parseInt(values[3]);
            double cgpa = Double.parseDouble(values[2]);
            Student student = new Student(id, name, cgpa);

            insert(student);
         }
         else
         {
            throw new IllegalArgumentException("invalid event");
         }
      }
      List<Student> remainingStudents = IntStream.range(0, heapSize).mapToObj(i -> extraxtMax()).collect(Collectors.toList());
      return remainingStudents;
   }

   private static List<String> readCountAndEvents()
   {
      try (Scanner reader = new Scanner(System.in))
      {
         int count = Integer.parseInt(reader.nextLine().trim());
         return IntStream.range(0, count).mapToObj(i -> reader.nextLine()).collect(Collectors.toList());
      }
   }

   public static void main(String[] args)
   {
      List<String> events = readCountAndEvents();
      int size = events.size();
      PriorityQueue priorityQueue = new PriorityQueue(size);
      List<Student> serverdStudents = priorityQueue.getStudents(events);
      if (serverdStudents.isEmpty())
      {
         System.out.println("EMPTY");
      }
      else
      {
         serverdStudents.forEach(st -> System.out.println(st));
      }
   }

   enum Event
   {
      ENTER, SERVED;
   }

   class Student implements Comparable<Student>
   {
      int id;
      String name;
      double cgpa;

      Comparator<Student> comparator = Comparator.comparing(Student::getCGPA).reversed().thenComparing(st -> st.getName()).thenComparing(Student::getID);

      Student(int id, String name, double cgpa)
      {
         this.id = id;
         this.name = name;
         this.cgpa = cgpa;
      }

      public int getID()
      {
         return id;
      }

      public String getName()
      {
         return name;
      }

      public double getCGPA()
      {
         return cgpa;
      }

      @Override
      public String toString()
      {
         return name + "(" + cgpa + ", " + id + ")";
      }

      @Override
      public int compareTo(Student other)
      {
         return comparator.compare(other, this);
      }
   }

}