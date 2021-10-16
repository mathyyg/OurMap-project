package org.jolmkbL2B.ourUtil;

import java.util.Random;

public class Generator {

    public static final String DESCRIPTION = "This type is only a library of custom utilitary.";

    public static String generateString()	{
        int lowAsciiLimit = 97;
        int highAsciiLimit = 122; //Ascii codes of a and z
        int targetLength = 15;
        Random rand = new Random();

        return (rand.ints(lowAsciiLimit, highAsciiLimit +1).limit(targetLength).collect(
                    StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());
        }
}
