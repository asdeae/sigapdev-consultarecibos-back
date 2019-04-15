package edu.moduloalumno.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.moduloalumno.model.PruebaTCambio;

@Component("floatformat")
public class FloatFormat {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static String dateString = format.format( new Date()   );
	
	public float round(float number, int decimalPlace) {
		BigDecimal bd = new BigDecimal(number);
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
	
	// tipo de cambio api sunat
	public PruebaTCambio dolares_a_soles(String fecha) throws MalformedURLException, ParseException {

		logger.info("fuck "+fecha);
		
  		PruebaTCambio p= null;		
  		URL url = new URL("https://api.sunat.cloud/cambio/"+fecha);
		ObjectMapper mapper = new ObjectMapper();

		try {				
			
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(url.openStream()));

	        String inputLine="",lineafinal="";
	        int campos=0;
	        while ((inputLine = in.readLine()) != null) {
	        	if(campos == 2 || campos == 3)
	        			lineafinal+=inputLine;
	         campos++;
	        }
	        
	        lineafinal="{"+lineafinal+"}";
	        
	        logger.info("cuerpo "+lineafinal.trim());
	        
	        Reader reader = new StringReader(lineafinal.trim());
	        p = mapper.readValue(reader, PruebaTCambio.class);


			logger.info("> objeto "+p);
			//Cuando no existe variaciones en el tipo de cambio en la fecha
			if(p.getCompra() == 0.0 ) {
					//logger.info("> string "+ fecha);


					String fechas[] = fecha.split("-",3); //Se realiza un split por regex "-" para separar días,meses y años gaaaa

					//Capturando los días, meses y años
					Integer dia = Integer.valueOf(fechas[2]);
					Integer mes  = Integer.valueOf(fechas[1]);
					Integer anio  = Integer.valueOf(fechas[0]);


					//Reduciendo días para encontrar el último valor del tipo de cambio


					//Verificando los días
					if(dia==1)
					{
						switch (mes)
						{
							case 1: anio--;
									dia=31;
									mes=12;
									break;
							case 2: dia=31;
									mes=1;
									break;
							case 3: if(isLeap(anio))
										dia=29;
									else
										dia=28;
									mes=2;
									break;
							case 4:	dia=31;
									mes=3;
									break;
							case 5:	dia=30;
									mes=4;
									break;
							case 6: dia=31;
									mes=5;
									break;
							case 7: dia=30;
									mes=6;
									break;
							case 8: dia=31;
									mes=7;
									break;
							case 9: dia=31;
									mes=8;
									break;
							case 10: dia=30;
									 mes=9;
									 break;
							case 11: dia=31;
									 mes=10;
									 break;
							case 12: dia=30;
							         mes=11;
									break;
							default: break;
						}
					}
					else
						dia--;

					//Se construye un formato para la fecha con StringBuilder
					StringBuilder fecha_anterior = new StringBuilder();
					fecha_anterior.append(Integer.valueOf(anio)+"-"+"0"+mes+"-"+dia);

					//Se aplica el método recursivo
					p=dolares_a_soles(fecha_anterior.toString());
			}
			logger.info("> objeto "+p);

			System.out.println(dateString);
			in.close();
		
		} catch (IOException e){
			System.out.println("ERROR! USUARIOS NO GUARDADOS : " + e.getMessage());
		}

		return p; 
  	}


  	//Confirms if the current year is leap
  	public boolean isLeap(Integer year)
	{
		if (((year % 4 == 0) && (year % 100!= 0)) || (year%400 == 0))
				return true;
			else
				return false;
	}
	
}
