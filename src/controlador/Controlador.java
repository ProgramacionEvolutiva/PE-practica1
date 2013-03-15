package controlador;

import interfaz.InterfazGrafica;

import java.util.ArrayList;
import java.util.Iterator;

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
		// Llamada al algoritmo
		genetico.algoritmo_genetico(interfaz.getParametros());
		
		// Recoger: Mejores cromosomas
		Cromosoma[] mejores = genetico.getMejoresCromosomas();
		double[] aptitudesMejores = new double[interfaz.getParametros().getNumGeneraciones()];
		for(int i = 0; i < interfaz.getParametros().getNumGeneraciones(); i++) {
			aptitudesMejores[i] = mejores[i].getAptitud();
		}
		
		// Recoger: Gokus
		Cromosoma[] gokus = genetico.getGokus();
		double[] aptitudesGokus = new double[interfaz.getParametros().getNumGeneraciones()];
		for(int i = 0; i < interfaz.getParametros().getNumGeneraciones(); i++) {
			aptitudesGokus[i] = gokus[i].getAptitud();
		}
		
		// Recoger: Picos
		ArrayList<Cromosoma> picos = genetico.getPicos();
		
		// mostrar(lineaBase, lineaRoja, tablaSuperior)
		interfaz.mostrar(aptitudesMejores, aptitudesGokus, picos);
	}
	
	
	/* Main */
	public static void main (String[] args)
	{
		Controlador controlador = new Controlador();
		controlador.inicializar();
	}
	
}
