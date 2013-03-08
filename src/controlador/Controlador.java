package controlador;

import interfaz.InterfazGrafica;
import interfaz.Parametros;
import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.Cromosoma;

public class Controlador {

	private static InterfazGrafica interfaz;
	private static AlgoritmoGenetico genetico;
	
	public static void main (String[] args){
		Controlador c=new Controlador();
		interfaz = new InterfazGrafica(c);
		
	}
	
	public void lanzaGenetico(Parametros parametros)
	{

		genetico = new AlgoritmoGenetico();
		
		Cromosoma cromosomaMejor = genetico.algoritmo_genetico(parametros);
		
		interfaz.mostrar(cromosomaMejor);
	}
	
	
}
