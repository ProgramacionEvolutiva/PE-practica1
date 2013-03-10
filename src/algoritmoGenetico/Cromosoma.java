package algoritmoGenetico;

import java.text.DecimalFormat;

public abstract class Cromosoma {
	protected boolean[] genes;
	protected double fenotipo;
	protected double aptitud;
	protected double puntuacionAcumulada;
	protected int longitud;
	
	public Cromosoma(){}
	
	public Cromosoma(boolean[] genes, double fenotipo, double aptitud, double puntuacionAcumulada) 
	{
		this.genes = genes;
		this.fenotipo = fenotipo;
		this.aptitud = aptitud;
		this.puntuacionAcumulada = puntuacionAcumulada;
	}
	
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
	 * @param tolerancia
	 * @param valor máximo del intervalo de x
	 * @param valor mínimo del intervalo de x
	 * @param valor máximo del intervalo de y
	 * @param valor mínimo del intervalo de y
	 * @return longitud
	 */
	protected int calcularLongitud(double tolerancia, double xMax, double xMin, double yMax, double yMin, int longitudx, int longitudy) {
		double longx = 1 + ( (xMax - xMin) / tolerancia);	
		longx = (Math.log(longx) / Math.log(2));
		longitudx = (int) Math.ceil(longx);
		double longy = 1 + ( (yMax - yMin) / tolerancia);	
		longy = (Math.log(longy) / Math.log(2));
		longitudy = (int) Math.ceil(longy);
		return longitudx + longitudy;
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
		return (xMin + (xMax - xMin) * Integer.parseInt(this.toString(), 2) ) / ( (Math.pow(2,longitud) - 1) );
	}	
	
	protected abstract Cromosoma clone();	


	/**
	 * 
	 * @param posicion
	 * @param gen
	 */
	protected void setGen(int posicion, boolean gen)
	{
		this.genes[posicion] = gen;
	}

	protected void mutaGen(int posicion)
	{
		this.genes[posicion] = !this.genes[posicion];
	}
	
	@Override
	public String toString()
	{
		String s = "";
		for (int i = 0; i < genes.length; i++) {
			if (this.genes[i]) s += "1";
			else s += "0";
		}
		return s ;
	}
	

	public void mostrar(){
		// TODO
		System.out.println("Solucion => " + toString() + " Fenotipo =>" + getFenotipo() +
				" Aptitud =>" + getAptitud());
	}
	
	protected abstract double evaluarCromosoma();
}
