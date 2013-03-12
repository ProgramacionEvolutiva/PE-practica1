package controlador;

import interfaz.InterfazGrafica;
import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.Cromosoma;

public class Controlador
{
	private InterfazGrafica interfaz;
	private AlgoritmoGenetico genetico;
	
	public Controlador(){}
	
	public void inicializar()
	{
		this.interfaz = new InterfazGrafica(this);
		this.genetico = new AlgoritmoGenetico();
	}
	
	public void lanzaGenetico()
	{
		Cromosoma cromosomaMejor = genetico.algoritmo_genetico(interfaz.getParametros());		
		interfaz.mostrar(cromosomaMejor);
	}
	
	/* Main */
	public static void main (String[] args)
	{
		Controlador controlador = new Controlador();
		controlador.inicializar();
	}
	
}
