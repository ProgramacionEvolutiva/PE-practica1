package controlador;

import interfaz.Info;
import interfaz.InterfazGrafica;

import java.util.ArrayList;
import java.util.Iterator;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.Cromosoma;
import algoritmoGenetico.CromosomaF1;

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
		ArrayList<Integer> generacionesPicos = genetico.getGeneracionesPicos();
		Iterator<Cromosoma> itPicos = picos.iterator();
		Iterator<Integer> itGeneraciones = generacionesPicos.iterator();
		Cromosoma cromosomaActual = null;
		Info[] info = new Info[picos.size()];
		int i = 0;
		
		//for(variable : estructura) FIXME: eliminar los iteradores
		while(itGeneraciones.hasNext()) {
			cromosomaActual = itPicos.next();
			info[i] = new Info(itGeneraciones.next().intValue(), cromosomaActual.toString(), cromosomaActual.getFenotipo(), cromosomaActual.getAptitud(), cromosomaActual);
			i++;
		}
				
		// mostrar(lineaBase, lineaRoja, tablaSuperior, tablaInferior)
		interfaz.mostrar(aptitudesMejores, aptitudesGokus, genetico.getMedias(), info);
	}
	
	/* Main */
	public static void main (String[] args)
	{
		Controlador controlador = new Controlador();
		controlador.inicializar();
	}
	
}
