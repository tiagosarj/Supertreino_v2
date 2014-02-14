package br.ufba.matc89.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.text.TextWatcher;
import android.widget.EditText;

public class DateUtil extends Activity{

	public static java.sql.Date convertDateUtil2DateSql(java.util.Date date){
		java.sql.Date dataSql = new java.sql.Date(date.getTime());
		return dataSql;
	}
	
	public static Date getDate(String strDate){
		
		Locale locale = new Locale("pt", "BR");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", locale);  
	    Date data = new Date();
		
	    try {
	    	data = new java.sql.Date(format.parse(strDate).getTime());
		
		} catch (ParseException e) {			
			e.printStackTrace();
		}  
		
	    return data; 
	}
	
	public static String getDataSimples(Date data){
		String dataSimples = "00-00-0000";
		
		Locale locale = new Locale("pt", "BR");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", locale);
		
		dataSimples = sdf.format(data);			
			
		return dataSimples;
	}
	
	
	public static void validarDataEmPreenchimento(EditText data, String mascara){
		final TextWatcher nascimentoMask;
		
		nascimentoMask = Mascara.aplicarMascara(mascara, data);
		data.addTextChangedListener(nascimentoMask);
	}
	
	public static Date getMaiorData(List<Date> datas){
		Date dataAtual = datas.get(0);		
		
		for(Date d:datas){
			if(d.after(dataAtual)){
				dataAtual = d;				
			}	
		}
		return dataAtual;
	}
	
	/*public static java.sql.Date formatarDdMMyyy(java.sql.Date date){
		
	}*/
		
//	public static void main(String[] args){
//		
//		System.out.println(getDataSimples(new Date()));
//	}
}
