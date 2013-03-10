package algoritmoGenetico;

import interfaz.Parametros;

/**
 * Clase que implementa el algoritmo genetico principal
 * 
 */
public class AlgoritmoGenetico {
	private int longitudCromosoma;

	/**
	 * Implementacion del algoritmo genetico
	 * 
	 * @param parametros del problema
	 * @return el mejor cromosoma encontrado
	 */
	public Cromosoma algoritmo_genetico(Parametros parametros) {
		// Obtenemos la poblacion inicial 
		Cromosoma[] pob = poblacion_inicial(parametros); 
		int pos_mejor = evaluarPoblacion(pob);	

		//mejor cromosoma
		Cromosoma mejor;
		
		//Obtenemos la longitud de los cromosomas para este problema
		int funcionSeleccionada = parametros.getFuncion();
		switch(funcionSeleccionada) {
			case 1:
				this.longitudCromosoma = CromosomaF1.longitud;
				mejor= new CromosomaF1(parametros.getTolerancia());
				break;
			case 2:
				this.longitudCromosoma = CromosomaF2.longitud;
				mejor= new CromosomaF2(parametros.getTolerancia());
				break;
			case 3:
				this.longitudCromosoma = CromosomaF3.longitud;
				mejor= new CromosomaF3(parametros.getTolerancia());
				break;
			case 4:
				this.longitudCromosoma = CromosomaF4.longitud;
				mejor= new CromosomaF4(parametros.getTolerancia());
				break;
			case 5:
				this.longitudCromosoma = CromosomaF5.longitud;
				mejor= new CromosomaF5(parametros.getTolerancia());
				break;
			default: 
				this.longitudCromosoma = CromosomaF1.longitud;
				mejor= new CromosomaF1(parametros.getTolerancia());
		}

		// bucle de evolucion
		for (int i = 0; i < parametros.getNumGeneraciones(); i++) {
			// 1) seleccion
			pob = seleccion(pob, parametros);
			
			// 2) reproduccion
			pob = reproduccion(pob, parametros);
			
			// 3) mutacion
			mutacion(pob, parametros);
			
			// 4) tratar la nueva solucion
			pos_mejor = evaluarPoblacion(pob);
			if (pob[pos_mejor].getAptitud()>mejor.getAptitud()){
				mejor=pob[pos_mejor].clone();
			}
			// mejor.mostrar();
		}
		
		return mejor;
	}

	/** 
	 * Metodo para obtener una poblacion inicial aleatoria
	 * 
	 * @param parametros del problema
	 * @return poblacion generada
	 */
	private Cromosoma[] poblacion_inicial(Parametros param) 
	{
		int tam = param.getTamPoblacion();
		Cromosoma[] pob = new Cromosoma[tam];
		
		for (int i = 0; i < tam; i++) {
			switch(param.getFuncion()) {
			case 1:
				pob[i] = new CromosomaF1(param.getTolerancia());
				break;
			case 2:
				pob[i] = new CromosomaF2(param.getTolerancia());
				break;
			case 3:
				pob[i] = new CromosomaF3(param.getTolerancia());
				break;
			case 4:
				pob[i] = new CromosomaF4(param.getTolerancia());
				break;
			case 5:
				pob[i] = new CromosomaF5(param.getTolerancia());
				break;
			}
		}		
		return pob;
	}
	
	/**
	 * La funcion de seleccion escoge un numero de supervivientes
	 * igual al tam de la poblacion.
	 * 
	 * Metodo de seleccion => Seleccion por ruleta.
	 * 
	 * @param poblacion
	 * @param parametros del problema
	 * @return Poblacion seleccionada
	 */
	private Cromosoma[] seleccion(Cromosoma[] pob, Parametros parametros) 
	{
		Cromosoma[] poblacionSeleccionada = new Cromosoma[parametros.getTamPoblacion()];
		int posicionSuperviviente;
		
		double random;
		for (int i = 0; i < parametros.getTamPoblacion(); i++) {
			random = Math.random();
			posicionSuperviviente = 0;
			while ( (random > pob[posicionSuperviviente].getPuntuacionAcumulada()) &&
					(posicionSuperviviente < parametros.getTamPoblacion()) ) {
				posicionSuperviviente++;
			}
			
			poblacionSeleccionada[i] = pob[posicionSuperviviente];
		}
		
		return poblacionSeleccionada;
	}

	/**
	 * La reproduccion consiste en la seleccion de los individuos a reproducirse
	 * y en la aplicacion del operador de cruce a cada una de las parejas.
	 * 
	 * @param poblacion
	 * @param parametros del problema
	 * @return nueva poblacion resultante
	 */
	private Cromosoma[] reproduccion(Cromosoma[] pob, Parametros parametros) 
	{
		// 1. Seleccionados para reproducir
		int[] selCruce= new int[parametros.getTamPoblacion()];
		
		// 2. Contador seleccionados
		int numSelCruce = 0;
		int puntoCruce;
		double prob;		

		//Inicializamos los hijos
		Cromosoma hijo1=null;
		Cromosoma hijo2=null;
		switch(parametros.getFuncion()) {
			case 1:
				hijo1 = new CromosomaF1(parametros.getTolerancia());
				hijo2 = new CromosomaF1(parametros.getTolerancia());
				break;
			case 2:
				hijo1 = new CromosomaF2(parametros.getTolerancia());
				hijo2 = new CromosomaF2(parametros.getTolerancia());
				break;
			case 3:
				hijo1 = new CromosomaF3(parametros.getTolerancia());
				hijo2 = new CromosomaF3(parametros.getTolerancia());
				break;
			case 4:
				hijo1 = new CromosomaF4(parametros.getTolerancia());
				hijo2 = new CromosomaF4(parametros.getTolerancia());
				break;
			case 5:
				hijo1 = new CromosomaF5(parametros.getTolerancia());
				hijo2 = new CromosomaF5(parametros.getTolerancia());
				break;
		}
		
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
	 * La mutacion cambia ciertos genes de ciertos individuos de la poblacion
	 * 
	 * @param poblacion
	 * @param paramametros del problema
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
		hijo.inicializarGenes(padre.getLongitud());
		hija.inicializarGenes(madre.getLongitud());
		
		// primera parte del intercambio: 
		// copiar progenitor desde el principio hasta el punto de cruce
		
		for (int i = 0; i < puntoCruce; i++){
			hijo.setGen(i, padre.getGenes()[i]);
			hija.setGen(i, madre.getGenes()[i]);
		}
		
		// segunda parte del intercambio:
		// copiar desde el punto de cruce hasta el final del otro progenitor
		
		for (int i = puntoCruce; i <= padre.getLongitud(); i++){
			hija.setGen(i, padre.getGenes()[i]);
			hijo.setGen(i, madre.getGenes()[i]);
		}
		
		// evaluar los nuevos individuos
		
		hijo.evaluarCromosoma();
		hija.evaluarCromosoma();
		
		
	}
	
	/**
	 * 
	 * @param poblacion a evaluar
	 * @return la posicion del mejor individuo
	 */
	private int evaluarPoblacion(Cromosoma[] poblacion)
	{
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
