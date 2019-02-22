package com.hacker.rank.interview;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.stream.IntStream;


enum DifficultLevel
{
   EASY('E', 1)
   {
      @Override
      public <IN, OUT> OUT accept(DifficultLevelVisitor<IN, OUT> visitor, IN in)
      {
         return visitor.visitEasy(in);
      }
   },
   MEDIUM('M', 3)
   {
      @Override
      public <IN, OUT> OUT accept(DifficultLevelVisitor<IN, OUT> visitor, IN in)
      {
         return visitor.visitMedium(in);
      }
   },
   HARD('H', 5)
   {
      @Override
      public <IN, OUT> OUT accept(DifficultLevelVisitor<IN, OUT> visitor, IN in)
      {
         return visitor.visitHard(in);
      }
   };

   private char identifier;
   private int score;

   DifficultLevel(char identifier, int score)
   {
      this.identifier = identifier;
      this.score = score;
   }

   public char getIdentifier()
   {
      return identifier;
   }

   public int getScore()
   {
      return score;
   }

   public static DifficultLevel fromIdentifier(char identifier)
   {
      for (DifficultLevel dl : DifficultLevel.values())
      {
         if (dl.identifier == Character.toUpperCase(identifier))
         {
            return dl;
         }
      }
      throw new IllegalArgumentException("Invalid difficulty level identifier");
   }

   public abstract <IN, OUT> OUT accept(DifficultLevelVisitor<IN, OUT> visitor, IN in);

   private static final Object nullParameter = null;

   public <OUT> OUT accept(DifficultLevelVisitor<Void, OUT> visitor)
   {
      return accept(visitor, (Void) nullParameter);
   }

}

interface DifficultLevelVisitor<IN, OUT>
{
   OUT visitEasy(IN in);

   OUT visitMedium(IN in);

   OUT visitHard(IN in);
}

class ScoreProvider implements DifficultLevelVisitor<Void, Integer>
{

   public static class ScoreInstanceProvider
   {
      private static ScoreProvider scoreProvider = new ScoreProvider();

      public static ScoreProvider getInstance()
      {
         return scoreProvider;
      }
   }

   private ScoreProvider()
   {}

   @Override
   public Integer visitEasy(Void in)
   {
      return DifficultLevel.EASY.getScore();
   }

   @Override
   public Integer visitMedium(Void in)
   {
      return DifficultLevel.MEDIUM.getScore();
   }

   @Override
   public Integer visitHard(Void in)
   {
      return DifficultLevel.HARD.getScore();
   }

}

class Result
{

   /*
    * Complete the 'winner' function below.
    *
    * The function is expected to return a STRING. The function accepts following parameters: 1. STRING erica 2. STRING bob
    */

   public static String winner(String erica, String bob)
   {
      long ericaScore = score(erica.toCharArray());
      long bobScore = score(bob.toCharArray());

      if (ericaScore > bobScore)
         return "Erica";
      else if (ericaScore < bobScore)
         return "Bob";

      return "Tie";
   }

   private static long score(char[] levels)
   {
      ScoreProvider scoreProvider = ScoreProvider.ScoreInstanceProvider.getInstance();
      return IntStream.range(0, levels.length).map(i -> DifficultLevel.fromIdentifier(levels[i]).accept(scoreProvider)).sum();
   }

}

public class FindTheWinner
{
   public static void main(String[] args) throws IOException
   {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      String erica = bufferedReader.readLine();

      String bob = bufferedReader.readLine();

      String result = Result.winner(erica, bob);

      bufferedWriter.write(result);
      bufferedWriter.newLine();

      bufferedReader.close();
      bufferedWriter.close();
   }
}
