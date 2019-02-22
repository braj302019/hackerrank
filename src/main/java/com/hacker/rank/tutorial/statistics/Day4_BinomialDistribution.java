package com.hacker.rank.tutorial.statistics;

import java.text.DecimalFormat;
import java.util.Scanner;

/*
 * The ratio of boys to girls for babies born in Russia is 1.09 : 1. If there is 1 child born per birth,
 * what proportion of Russian families with exactly 6 children will have at least 3 boys?
 */
public class Day4_BinomialDistribution
{
   public static void main(String[] args)
   {
      try (Scanner reader = new Scanner(System.in))
      {
         double boyPropability = reader.nextFloat();
         double girlPropability = reader.nextFloat();

         double q = girlPropability / (boyPropability + girlPropability);
         double p = 1 - q;

         double probability = 0;
         for (int i = 3; i <= 6; i++)
         {
            probability += binomialDistribution(i, 6, p, q);
         }
         DecimalFormat df = new DecimalFormat("#.###");
         System.out.println(df.format(probability));
      }
   }

   private static double binomialDistribution(int x, int n, double p, double q)
   {
      return Math.pow(p, x) * Math.pow(q, n - x) * ncr(n, x);
   }

   private static long ncr(long n, long r)
   {
      long numerator = 1, denominator = 1;
      if (r > n - r)
      {
         r = n - r;
      }
      for (long i = 1L; i <= r; ++i)
      {
         denominator *= i;
      }
      for (long i = n - r + 1L; i <= n; ++i)
      {
         numerator *= i;
      }
      return numerator / denominator;
   }

}
