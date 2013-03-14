package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

import controlador.Controlador;


/**
 * Interfaz grafica.
 * 
 */
//TODO: Eliminar del proyecto CondigPanel
public class InterfazGrafica extends JFrame
{
	private static final long serialVersionUID = 1L;

	/* Controlador & Parametros */
	private Controlador controlador;
	private Parametros parametros;
	
	/* Definicion de constantes */
	private static final int FRAME_WEIGHT = 800;
	private static final int FRAME_HEIGHT = 480;
	
	/* Componentes graficos */
	private JPanel panelPrincipal; 
		private JPanel panelFormulario;
			private JPanel panelPoblacion;
				private JLabel labelPoblacion;
				private JTextField formPoblacion;
			private JPanel panelGeneraciones;
				private JLabel labelGeneraciones;
				private JTextField formGeneraciones;
			private JPanel panelCruce;
				private JLabel labelCruce;
				private JTextField formCruce;
			private JPanel panelMutacion;
				private JLabel labelMutacion;
				private JTextField formMutacion;
			private JPanel panelTolerancia;
				private JLabel labelTolerancia;
				private JTextField formTolerancia;
			private JPanel panelFuncion;
				private JLabel labelFuncion;
				private JTextField formFuncion;
			private JPanel panelCargar;
				private JButton botonCargar;		
		
		private Plot2DPanel panelResultados; 
			// grafica
	
	public InterfazGrafica(Controlador c) 
	{
		this.controlador = c;
		this.parametros = new Parametros();
		
		this.setContentPane(obtenerPanelPrincipal());
		propiedadesBasicas();
	}
	
	public JPanel obtenerPanelPrincipal()
	{
		panelPrincipal = new JPanel();
		
		// 1) Panel izquierdo: formulario
		panelFormulario = new JPanel();
		obtenerFormulario();
		// 2) Panel derecho: resultados
		panelResultados = new Plot2DPanel();
		// 3) insertar los paneles
		panelPrincipal.setLayout(new GridLayout(0, 2, 0, 0));	
		panelPrincipal.add(panelFormulario);
		panelPrincipal.add(panelResultados);
		
		return panelPrincipal;
	}

	/**
	 * Crea el panel formulario usando como layout GridLayout(7 filas)
	 *  
	 * @return panel
	 */
	private void obtenerFormulario()
	{
		panelFormulario.setLayout(new GridLayout(7, 1, 0, 0));
		
		panelPoblacion = new JPanel();
		panelFormulario.add(panelPoblacion);
			panelPoblacion.setLayout(new GridLayout(1,0,0,0)); 
			labelPoblacion = new JLabel("Poblacion: ");
			panelPoblacion.add(labelPoblacion, "1, 1, fill, fill");
			formPoblacion = new JTextField("100");
			panelPoblacion.add(formPoblacion, "3, 1, fill, fill");
		
		panelGeneraciones = new JPanel();
		panelGeneraciones.setLayout(new GridLayout(1, 0, 0, 0));
		panelFormulario.add(panelGeneraciones);
			labelGeneraciones = new JLabel("Generaciones: ");
			panelGeneraciones.add(labelGeneraciones);
			formGeneraciones = new JTextField("50");
			panelGeneraciones.add(formGeneraciones);

		panelCruce = new JPanel();
		panelCruce.setLayout(new GridLayout(1, 0, 0, 0));
		panelFormulario.add(panelCruce);
			labelCruce = new JLabel("Probabilidad de Cruce: ");
			panelCruce.add(labelCruce);
			formCruce = new JTextField("0.6");
			panelCruce.add(formCruce);
			
		panelMutacion = new JPanel();
		panelMutacion.setLayout(new GridLayout(1, 0, 0, 0));
		panelFormulario.add(panelMutacion);
			labelMutacion = new JLabel("Probabilidad de Mutacion: ");
			panelMutacion.add(labelMutacion);
			formMutacion = new JTextField("0.01");
			panelMutacion.add(formMutacion);
			
		panelTolerancia = new JPanel();
		panelTolerancia.setLayout(new GridLayout(1, 0, 0, 0));
		panelFormulario.add(panelTolerancia);
			labelTolerancia = new JLabel("Tolerancia: ");
			panelTolerancia.add(labelTolerancia);
			formTolerancia = new JTextField("0.01");
			panelTolerancia.add(formTolerancia);
		
		panelFuncion = new JPanel();
		panelFuncion.setLayout(new GridLayout(1, 0, 0, 0));
		panelFormulario.add(panelFuncion);
			labelFuncion = new JLabel("Funcion: ");
			panelFuncion.add(labelFuncion);
			formFuncion = new JTextField("1");
			panelFuncion.add(formFuncion);
			
		panelCargar = new JPanel();
		panelCargar.setLayout(new GridLayout(1, 0, 0, 0));
		panelFormulario.add(panelCargar);
			botonCargar = new JButton("OK");
			botonCargar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					recogerParametros();
					controlador.lanzaGenetico();
				}
			});
			panelCargar.add(botonCargar);
	}
	
	private void obtenerResultados(double[] aptitudesMejores, double[] gokusMejores)
	{
		panelResultados.removeAllPlots();
		
		// definir los datos
		double[] x = new double[parametros.getNumGeneraciones()];
		for(int i=0;i<parametros.getNumGeneraciones();i++){
			x[i]=i;
		}
 
		// define the legend position
		panelResultados.addLegend("SOUTH");
		// add a line plot to the PlotPanel
		panelResultados.addLinePlot("Cromosoma mejor de cada generaci—n", x, aptitudesMejores);
		panelResultados.addLinePlot("Cromosoma mejor encontrado", x, gokusMejores);
	}
	
	private void recogerParametros()
	{
		// TODO => Validar campos
		this.parametros.setTamPoblacion(Integer.parseInt(formPoblacion.getText()));
		this.parametros.setNumGeneraciones(Integer.parseInt(formGeneraciones.getText()));
		this.parametros.setProbCruce(Double.parseDouble(formCruce.getText()));
		this.parametros.setProbMutacion(Double.parseDouble(formMutacion.getText()));
		this.parametros.setTolerancia(Double.parseDouble(formTolerancia.getText()));
		this.parametros.setFuncion(Integer.parseInt(formFuncion.getText()));
	}
	
	private void propiedadesBasicas()
	{
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, FRAME_WEIGHT, FRAME_HEIGHT);
		setResizable(false);
	}
	
	public Parametros getParametros(){ return parametros; }
	
	public void mostrar(double[] aptitudesMejores, double[] gokusMejores)
	{
		
		obtenerResultados(aptitudesMejores,gokusMejores);
		
	}
}
