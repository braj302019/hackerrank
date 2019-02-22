package com.hacker.rank.practice.java.strings.regex;

/*
 * write a regular expression and assign it to the pattern such that it can be used to validate an IP address
 */
public class IpAddressValidator
{
   private static final String pattern = "(([0-1]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-1]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])";

   public static boolean validate(String address)
   {
      return address.matches(pattern);
   }
}
