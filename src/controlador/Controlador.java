package controlador;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.Cromosoma;
import interfaz.InterfazGrafica;

public class Controlador {

	private static InterfazGrafica interfaz;
	private static AlgoritmoGenetico genetico;
	
	public static void main (String[] args){
		interfaz = new InterfazGrafica();
		genetico = new AlgoritmoGenetico();
		
		Cromosoma cromosomaMejor = genetico.algoritmo_genetico(interfaz.getParametros());
		
		interfaz.mostrar(cromosomaMejor);
		
	}
	
	/*
	public static void main (String[] args)
	{
		System.out.print("Empezamos \n");
		
		double aux = 1 + ( (2 - (-3)) / 0.1);	
		aux = (Math.log(aux) / Math.log(2));
		System.out.println ( "====> " + aux + "\n");
		System.out.println ( "====> " + (int) Math.ceil(aux) + "\n");
	}
	*/
}
