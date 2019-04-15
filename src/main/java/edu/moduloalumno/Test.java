package edu.moduloalumno;

import com.fasterxml.jackson.databind.util.JSONPObject;
import edu.moduloalumno.component.FloatFormat;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.boot.json.GsonJsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {

    public static void main(String argumentitos[])
    {

        FloatFormat ff = new FloatFormat();
        try {
            System.out.println(ff.dolares_a_soles("2019-04-01"));
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }/*
        try {

            URL url = new URL("https://api.sunat.cloud/cambio/2019-04-13");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);

            StringBuilder json = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                json.append(output);
                System.out.println(output);
            }


            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }

        String numero = "04";

        System.out.println(Integer.valueOf(numero));

        System.out.println("0"+Integer.valueOf(numero));
    */}
    }


