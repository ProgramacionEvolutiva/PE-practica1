package interfaz;

public class Parametros {
	private int tamPoblacion;
	private int numGeneraciones;
	private double probCruce;
	private double probMutacion;
	private double tolerancia;
	private int funcion;
	
	public Parametros (int tam, int generaciones, double cruce, double mutacion, double tolerancia, int funcion){
		this.tamPoblacion = tam;
		this.numGeneraciones = generaciones;
		this.probCruce = cruce;
		this.probMutacion = mutacion;
		this.tolerancia = tolerancia;
		this.funcion = funcion;
	}
	
	public int getTamPoblacion() {
		return tamPoblacion;
	}
	public void setTamPoblacion(int tamPoblacion) {
		this.tamPoblacion = tamPoblacion;
	}
	public int getNumGeneraciones() {
		return numGeneraciones;
	}
	public void setNumGeneraciones(int numGeneraciones) {
		this.numGeneraciones = numGeneraciones;
	}
	public double getProbCruce() {
		return probCruce;
	}
	public void setProbCruce(double probCruce) {
		this.probCruce = probCruce;
	}
	public double getProbMutacion() {
		return probMutacion;
	}
	public void setProbMutacion(double probMutacion) {
		this.probMutacion = probMutacion;
	}
	public double getTolerancia() {
		return tolerancia;
	}
	public void setTolerancia(double tolerancia) {
		this.tolerancia = tolerancia;
	}

	public int getFuncion() {
		return funcion;
	}

	public void setFuncion(int funcion) {
		this.funcion = funcion;
	}
}
