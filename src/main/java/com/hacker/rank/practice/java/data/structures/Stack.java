package com.hacker.rank.practice.java.data.structures;

public class Stack<T>
{
   int top; // empty when zero
   int length; // full when top = length
   Object[] array;

   public Stack(int maxLength)
   {
      this.length = maxLength;
      this.array = new Object[maxLength];
   }

   public void push(T value)
   {
      if (top >= length)
         throw new RuntimeException("overflow");

      array[top] = value;
      top++;
   }

   @SuppressWarnings("unchecked")
   public T pop()
   {
      if (isEmpty())
         throw new RuntimeException("underflow");

      top--;
      return (T) array[top];
   }

   @SuppressWarnings("unchecked")
   public T peek()
   {
      if (isEmpty())
         throw new RuntimeException("underflow");

      return (T) array[top - 1];
   }

   public boolean isEmpty()
   {
      return top == 0;
   }

   public void clear()
   {
      top = 0;
   }

   public int size()
   {
      return top;
   }


   private static char[] getCharsUpto(Stack<Character> stack, int k)
   {
      char[] chars = new char[k];
      getCharsUpto(stack, k - 1, 0, stack.pop(), chars);
      return chars;
   }

   private static void getCharsUpto(Stack<Character> stack, int k, int index, char ch, char[] chars)
   {
      if(index == k)
      {
         stack.push(ch);
         chars[index] = ch;
         return;
      }

      getCharsUpto(stack, k, index + 1, stack.pop(), chars);
      stack.push(ch);
      chars[index] = ch;
   }

   private static char getCharAt(Stack<Character> stack, int k)
   {
      return getCharAt(stack, k - 1, 0, stack.pop());
   }

   private static char getCharAt(Stack<Character> stack, int k, int index, char ch)
   {
      if (index == k)
      {
         stack.push(ch);
         return ch;
      }

      Character value = getCharAt(stack, k, index + 1, stack.pop());
      stack.push(ch);
      return value;
   }

   public static void main(String[] args)
   {
      Stack<Character> stack = new Stack<>(10);
      stack.push('c');
      stack.push('b');
      stack.push('a');

      System.out.println(getCharAt(stack, 1));
      System.out.println("--------------");
      System.out.println(getCharsUpto(stack, 3));
      System.out.println("--------------");
      while(!stack.isEmpty())
      {
         System.out.println(stack.pop());
      }
   }
}
