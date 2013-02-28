package algoritmoGenetico;

import interfaz.Parametros;

public class AlgoritmoGenetico {
	Cromosoma[] pob;
	int tam_pob;
	int num_max_gen;
	Cromosoma elMejor;
	int pos_mejor;
	double prob_cruce;
	double prob_mut;
	double tolerancia;
	
	public Cromosoma algoritmo_genetico(Parametros parametros) {
		// Obtenemos la poblacion inicial 
		Cromosoma[] p = poblacion_inicial(); 
		int pos_mejor = evaluarPoblacion(p);
		
		// bucle de evolución
		while (!terminacion())
		{
			selección(pob, parametros);
			reproduccion(pob parametros);
			evaluacion(pob, parametros, pos_mejor, sumadaptacion); 
		}
		return pob[pos_mejor];
	}

	private boolean terminacion() {
		// TODO Auto-generated method stub
		return false;
	}

	private Cromosoma[] poblacion_inicial() {
		
		return null;
	}
	
	/**
	 * 
	 * @param poblacion a evaluar
	 * @return la posicion del mejor individuo
	 */
	private int evaluarPoblacion(Cromosoma[] poblacion){
		double puntuacionAcumulada = 0;
		double aptitudMejor = 0;
		double sumaAptitudes = 0;
		
		int pos_mejor = 0;
		
		for (int i = 0; i< poblacion.length; i++) {
			sumaAptitudes += poblacion[i].getAptitud();
			if (poblacion[i].getAptitud() > aptitudMejor) {
				pos_mejor = i;
				aptitudMejor = poblacion[i].getAptitud();
			}
		}
		
		return pos_mejor;
	}
	
}
