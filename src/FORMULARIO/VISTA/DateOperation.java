/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import java.util.Calendar;
import java.util.Date;

public class DateOperation {

	public static void main(String[] args){
		
		//Establecemos la fecha que deseamos en un Calendario
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		//Desplegamos la fecha
		Date tempDate = cal.getTime();
		System.out.println("Fecha actual: " + tempDate);
		
		//Le cambiamos la hora y minutos
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)+ 2);
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)- 5);
		tempDate = cal.getTime();
		System.out.println("Hora modificada: " + tempDate);
		
		//Le cambiamos el mes
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
		System.out.println("Fecha modificada: " + cal.getTime());
		
		//De la misma forma se puede cambiar año, semana, etc
		
	}
}