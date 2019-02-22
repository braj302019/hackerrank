package com.hacker.rank.practice.java.data.structures;

import java.lang.reflect.Array;

public class Stack<T>
{
   int top; // empty when zero
   int length; // full when top = length
   T[] array;

   @SuppressWarnings("unchecked")
   Stack(Class<T> clazz, int maxLength)
   {
      this.length = maxLength;
      this.array = (T[]) Array.newInstance(clazz, maxLength);
   }

   void push(T value)
   {
      if (top >= length)
         throw new RuntimeException("overflow");

      array[top] = value;
      top++;
   }

   T pop()
   {
      top--;
      return array[top];
   }

   boolean isEmpty()
   {
      return top == 0;
   }
}
