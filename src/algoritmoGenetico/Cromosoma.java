package algoritmoGenetico;

import java.text.DecimalFormat;

public abstract class Cromosoma {
	protected boolean[] genes;
	protected double fenotipo;
	protected double aptitud;
	protected double puntuacion;
	protected double puntuacionAcumulada;
	protected int longitud;
	
	public boolean[] getGenes() {
		return genes;
	}
	public void setGenes(boolean[] genes) {
		this.genes = genes;
	}
	public double getFenotipo() {
		return fenotipo;
	}
	public void setFenotipo(double fenotipo) {
		this.fenotipo = fenotipo;
	}
	public double getAptitud() {
		return aptitud;
	}
	public void setAptitud(double aptitud) {
		this.aptitud = aptitud;
	}
	public double getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
	public double getPuntuacionAcumulada() {
		return puntuacionAcumulada;
	}
	public void setPuntuacionAcumulada(double puntuacionAcumulada) {
		this.puntuacionAcumulada = puntuacionAcumulada;
	}
	
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	
	protected boolean[] inicializarGenes(int longitud)
	{
		boolean[] genes = new boolean[longitud];
		
		double numero;
		for (int i = 0; i<longitud; i++) {
			numero = Math.random();
			if (numero < 0.5){
				genes[i] = false;
			} else {
				genes[i] = true;
			}
		}
		
		return genes;
	}
	
	/**
	 * @param tolerancia
	 * @param valor máximo del intervalo
	 * @param valor mínimo del intervalo
	 * @return longitud
	 */
	protected int calcularLongitud(double tolerancia, int xMax, int xMin)
	{
		double aux = 1 + ( (xMax - xMin) / tolerancia);	
		aux = (Math.log(aux) / Math.log(2));
		return (int) Math.ceil(aux);
	}
	
	/**
	 * 
	 * @param longitud
	 * @param xMax
	 * @param xMin
	 * @return
	 */
	protected double calcularFenotipo(int longitud, int xMax, int xMin)
	{
		return (xMin + (xMax - xMin) * Integer.parseInt(genes.toString(), 2) ) / ( (Math.pow(2,longitud) - 1) );
	}	
	
	/**
	 * 
	 * @param posicion
	 * @param gen
	 */
	protected void setGen(int posicion, boolean gen)
	{
		this.genes[posicion] = gen;
	}

	protected abstract double evaluarCromosoma();
}
