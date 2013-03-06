package controlador;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.Cromosoma;
import interfaz.InterfazGrafica;

public class Controlador {

	private static InterfazGrafica interfaz;
	private static AlgoritmoGenetico genetico;
	
	public static void _main (String[] args){
		interfaz = new InterfazGrafica();
		genetico = new AlgoritmoGenetico();
		
		Cromosoma cromosomaMejor = genetico.algoritmo_genetico(interfaz.getParametros());
		
		interfaz.mostrar(cromosomaMejor);
		
	}
	
	
	public static void main (String[] args)
	{
		System.out.print("1101 => " + Integer.parseInt("1101", 2));
		
	}
	
}
