package edu.moduloalumno;

import edu.moduloalumno.component.FloatFormat;

public class Test {

    public static void main(String argumentitos[])
    {

        FloatFormat ff = new FloatFormat();
        try {
            System.out.println(ff.dolares_a_soles("2019-04-15"));
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }



}
