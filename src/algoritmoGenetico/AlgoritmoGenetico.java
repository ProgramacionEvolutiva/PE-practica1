package algoritmoGenetico;

import interfaz.Parametros;

public class AlgoritmoGenetico {
	private int longitudCromosoma;

	public Cromosoma algoritmo_genetico(Parametros parametros) {
		// Obtenemos la poblacion inicial 
		Cromosoma[] pob = poblacion_inicial(parametros); 
		int pos_mejor = evaluarPoblacion(pob);
		
		//Obtenemos la longitud de los cromosomas para este problema
		// FIXME: parametros => funcion seleccionada
		this.longitudCromosoma = CromosomaF1.getLongitud();
		
		//Inicializamos el mejor cromosoma
		Cromosoma mejor= new CromosomaF1(longitudCromosoma);
		
		// bucle de evolución
		for (int i = 0; i < parametros.getNumGeneraciones(); i++) {
			pob = seleccion(pob, parametros);
			pob = reproduccion(pob, parametros);
			mutacion(pob, parametros);
			pos_mejor = evaluarPoblacion(pob);
			if (pob[pos_mejor].getAptitud()>mejor.getAptitud()){
				mejor=pob[pos_mejor].clone();
			}
		}
		return mejor;
	}

	/**
	 * La función de selección escoge un número de supervivientes
	 * igual al tamaño de la población.
	 * 
	 * Método de selección => Selección por ruleta.
	 * @param pob
	 * @param parametros
	 * @return Poblacion seleccionada
	 */
	private Cromosoma[] seleccion(Cromosoma[] pob, Parametros parametros) {
		Cromosoma[] poblacionSeleccionada = new Cromosoma[parametros.getTamPoblacion()];
		int posicionSuperviviente;
		
		double random;
		for (int i = 0; i < parametros.getTamPoblacion(); i++) {
			random = Math.random();
			posicionSuperviviente = 0;			
			while ((posicionSuperviviente < (parametros.getTamPoblacion())) &&
					(random > pob[posicionSuperviviente].getPuntuacionAcumulada())					) {				
				posicionSuperviviente++;
			}
			
			
			poblacionSeleccionada[i] = pob[posicionSuperviviente];
		}
		
		return poblacionSeleccionada;
	}

	/**
	 * La reproducción consiste en la selección
	 * de los individuos a reproducirse entre los de la población
	 * resultante, y en la aplicación del operador de cruce a cada
	 * una de las parejas.

	 * @param pob
	 * @param parametros
	 * @return nueva poblacion
	 */
	private Cromosoma[] reproduccion(Cromosoma[] pob, Parametros parametros) {
		// 1. Seleccionados para reproducir
		int[] selCruce= new int[parametros.getTamPoblacion()];
		
		// 2. Contador seleccionados
		int numSelCruce = 0;
		int puntoCruce;
		double prob;
		Cromosoma hijo1 = new CromosomaF1(longitudCromosoma);
		Cromosoma hijo2 = new CromosomaF1(longitudCromosoma);
		
		// 3. Se eligen los individuos a cruzar
		for (int i=0; i<parametros.getNumGeneraciones(); i++){
			prob=Math.random();
			if (prob < parametros.getProbCruce()){
				selCruce[numSelCruce] = i;
				numSelCruce++;
			}
		}
		
		// 4. El numero de seleccionados se hace par
		if ((numSelCruce % 2) == 1){
			numSelCruce--;
		}
		
		// 5. Se cruzan los individuos elegidos en un punto al azar
		for (int i=0; i<numSelCruce; i+=2){
			puntoCruce = aleatorioEntre(0, longitudCromosoma);
			cruce(pob[selCruce[i]],pob[selCruce[i+1]],hijo1,hijo2,puntoCruce);
			//los nuevos individuos sustituyen a sus progenitores
			pob[selCruce[i]]=hijo1;
			pob[selCruce[i+1]]=hijo2;
		}
		return pob;
	}
	
	/**
	 * El operador de cruce toma dos padres y genera dos cadenas hijas.
	 * La función calcula la aptitud de los nuevos individuos.
 	 *
	 * @param Cromosoma padre
	 * @param Cromosoma madre
	 * @param Cromosoma hijo1 (hijo)
	 * @param Cromosoma hijo2 (hija)
	 * @param puntoCruce
	 */
	private void cruce(Cromosoma padre, Cromosoma madre, Cromosoma hijo, Cromosoma hija, int puntoCruce) 
	{
		// primera parte del intercambio: 
		// copiar progenitor desde el principio hasta el punto de cruce
		
		for (int i = 0; i < puntoCruce; i++){
			hijo.setGen(i, padre.getGenes()[i]);
			hija.setGen(i, madre.getGenes()[i]);
		}
		
		// segunda parte del intercambio:
		// copiar desde el punto de cruce hasta el final del otro progenitor
		
		for (int i = puntoCruce; i < longitudCromosoma; i++){
			hija.setGen(i, padre.getGenes()[i]);
			hijo.setGen(i, madre.getGenes()[i]);
		}
		
		// evaluar los nuevos individuos
		
		hijo.evaluarCromosoma();
		hija.evaluarCromosoma();
		
		
	}

	/**
	 * 
	 * @param pob
	 * @param param
	 * @return
	 */
	private void mutacion(Cromosoma[] pob, Parametros parametros)
	{
		double random;
		for (int i = 0; i < parametros.getTamPoblacion(); i++) {
			for (int j = 0; j < longitudCromosoma; j++) {
				random = Math.random();
				if (random < parametros.getProbMutacion()) {
					pob[i].mutaGen(j);
				}
			}
		}
		
	}
	
	private Cromosoma[] poblacion_inicial(Parametros param) 
	{
		int tam = param.getTamPoblacion();
		Cromosoma[] pob = new Cromosoma[tam];
		
		for (int i = 0; i < tam; i++)
		{
			pob[i] = new CromosomaF1(param.getTolerancia());
		}
		
		return pob;
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
		
		for (int i = 0; i< poblacion.length; i++) {
			puntuacionAcumulada+=poblacion[i].getAptitud();
			poblacion[i].setPuntuacionAcumulada(puntuacionAcumulada/sumaAptitudes);
		}
		
		return pos_mejor;
	}
	
	private int aleatorioEntre(int a, int b){
		return a + (int)(Math.random() * ((b - a) + 1));		
	}
	
}
