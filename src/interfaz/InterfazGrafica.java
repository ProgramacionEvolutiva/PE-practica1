package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.math.plot.Plot2DPanel;

import controlador.Controlador;


/**
 * Interfaz grafica.
 * 
 */
public class InterfazGrafica extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static final int LINEAS_CABECERA = 2;
	
	/* Controlador & Parametros  + Info de las tablas */
	private Controlador controlador;
	private Parametros parametros;
	private Info[] info;
	
	/* Definicion de constantes */
	private static final int FRAME_WEIGHT = 800;
	private static final int FRAME_HEIGHT = 480;
	
	/* Componentes graficos */
	private JPanel panelPrincipal; 
	
	// Izquierda
	private JTabbedPane panelSelector;	
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
				private JComboBox formFuncion;
			private JPanel panelSeleccion;
				private JLabel labelSeleccion;
				private JComboBox formSeleccion;
			private JPanel panelCargar;
				private JButton botonCargar;
				private JButton botonLimpiar;
				
		private JPanel panelInfoCromosomas;
			private JPanel panelLista; // FIXME: JScrollPane
				private JList listaPicos;
	// Derecha	
	private Plot2DPanel panelResultados; 
	private JPanel panelDetalles;
	private JTextArea areaInfo;
		
		
			
	public InterfazGrafica(Controlador c) 
	{
		this.controlador = c;
		this.parametros = new Parametros();
		this.info = null;
		
		this.setContentPane(obtenerPanelPrincipal());
		propiedadesBasicas();
	}
	
	public JPanel obtenerPanelPrincipal()
	{
		panelPrincipal = new JPanel();
		
		// 1) panel izquierdo: tabbed pane
		panelSelector = new JTabbedPane(JTabbedPane.TOP);
		obtenerFormulario();
		obtenerInfoCromosomas();
		
		// 2) Panel derecho: resultados
		panelResultados = new Plot2DPanel();
		
		// 3) insertar los paneles
		panelPrincipal.setLayout(new GridLayout(0, 2, 0, 0));	
		panelPrincipal.add(panelSelector);
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
		panelFormulario = new JPanel();
		panelSelector.addTab("Parametros", null, panelFormulario, null);
		panelFormulario.setLayout(new GridLayout(8, 1, 0, 0));
				
			panelPoblacion = new JPanel();
			panelFormulario.add(panelPoblacion);
			panelPoblacion.setLayout(new GridLayout(1,0,0,0)); 
			labelPoblacion = new JLabel("  Poblacion: ");
			panelPoblacion.add(labelPoblacion);
			panelPoblacion.add(new JPanel()); // hueco intermedio
			formPoblacion = new JTextField(String.valueOf(Parametros.TAM_POBLACION_DEFECTO));
			panelPoblacion.add(formPoblacion);
			
			panelGeneraciones = new JPanel();
			panelGeneraciones.setLayout(new GridLayout(1, 0, 0, 0));
			panelFormulario.add(panelGeneraciones);
			labelGeneraciones = new JLabel("  Generaciones: ");
			panelGeneraciones.add(labelGeneraciones);
			panelGeneraciones.add(new JPanel()); // hueco intermedio
			formGeneraciones = new JTextField(String.valueOf(Parametros.NUM_GENERACIONES_DEFECTO));
			panelGeneraciones.add(formGeneraciones);
			
			panelCruce = new JPanel();
			panelCruce.setLayout(new GridLayout(1, 0, 0, 0));
			panelFormulario.add(panelCruce);
			labelCruce = new JLabel("  Prob. Cruce: ");
			panelCruce.add(labelCruce);
			panelCruce.add(new JPanel()); // hueco intermedio
			formCruce = new JTextField(String.valueOf(Parametros.PROB_CRUCE_DEFECTO));
			panelCruce.add(formCruce);
					
			panelMutacion = new JPanel();
			panelMutacion.setLayout(new GridLayout(1, 0, 0, 0));
			panelFormulario.add(panelMutacion);
			labelMutacion = new JLabel("  Prob. Mutacion: ");
			panelMutacion.add(labelMutacion);
			panelMutacion.add(new JPanel()); // hueco intermedio
			formMutacion = new JTextField(String.valueOf(Parametros.PROB_MUTACION_DEFECTO));
			panelMutacion.add(formMutacion);
			
			panelTolerancia = new JPanel();
			panelTolerancia.setLayout(new GridLayout(1, 0, 0, 0));
			panelFormulario.add(panelTolerancia);
			labelTolerancia = new JLabel("  Tolerancia: ");
			panelTolerancia.add(labelTolerancia);
			panelTolerancia.add(new JPanel()); // hueco intermedio
			formTolerancia = new JTextField(String.valueOf(Parametros.TOLERANCIA_DEFECTO));
			panelTolerancia.add(formTolerancia);
			
			panelFuncion = new JPanel();
			panelFuncion.setLayout(new GridLayout(1, 0, 0, 0));
			panelFormulario.add(panelFuncion);
			labelFuncion = new JLabel("  Funcion: ");
			panelFuncion.add(labelFuncion);
			panelFuncion.add(new JPanel()); // hueco intermedio
			formFuncion = new JComboBox();
			formFuncion.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
			panelFuncion.add(formFuncion);
			
			panelSeleccion = new JPanel();
			panelSeleccion.setLayout(new GridLayout(1, 0, 0, 0));
			panelFormulario.add(panelSeleccion);
			labelSeleccion = new JLabel("  Algoritmo de selecci—n: ");
			panelSeleccion.add(labelSeleccion);
			panelSeleccion.add(new JPanel()); // hueco intermedio
			formSeleccion = new JComboBox();
			formSeleccion.setModel(new DefaultComboBoxModel(new String[] {"Torneo", "Ruleta"}));
			formSeleccion.setSelectedIndex(1);
			panelSeleccion.add(formSeleccion);
			
			panelCargar = new JPanel();
			panelCargar.setLayout(new GridLayout(2, 3, 0, 0));
			/* fila de huecos */
			panelCargar.add(new JPanel());
			panelCargar.add(new JPanel());
			panelCargar.add(new JPanel());
			/* *** */
			panelFormulario.add(panelCargar);
				botonLimpiar = new JButton("Limpiar");
				botonLimpiar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						resetearCampos();
					}
				});
			panelCargar.add(botonLimpiar);
			panelCargar.add(new JPanel()); // hueco intermedio
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
	
	private void obtenerInfoCromosomas()
	{
		panelInfoCromosomas = new JPanel();
		panelInfoCromosomas.setLayout(new GridLayout(2, 1, 0, 0));
		
		// 1) Lista
		panelLista = new JPanel();
		panelLista.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panelLista.setLayout(new GridLayout(0, 1, 0, 0));
		listaPicos = new JList();
		listaPicos.setSelectedIndex(2); //FIXME: No funciona
		listaPicos.setLayoutOrientation(JList.VERTICAL);
		listaPicos.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mostrarDetallesCromosoma(e.getFirstIndex(), e.getLastIndex());
			}
		});
		panelLista.add(listaPicos);
		
		// 2) Detalles
		panelDetalles = new JPanel();
		panelDetalles.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panelDetalles.setLayout(new GridLayout(1, 0, 0, 0));
		areaInfo = new JTextArea();
		panelDetalles.add(areaInfo);
		
		// 3) add
		panelInfoCromosomas.add(panelLista);
		panelInfoCromosomas.add(panelDetalles);
		panelSelector.addTab("Detalles", null, panelInfoCromosomas, null);
	}
	
	private void obtenerResultados(double[] aptitudesMejores, double[] gokusMejores)
	{
		// borrar la grafica anterior
		panelResultados.removeAllPlots();
		// definir los datos
		double[] x = new double[parametros.getNumGeneraciones()];
		for(int i=0;i<parametros.getNumGeneraciones();i++){
			x[i]=i;
		}
		// definir la layenda de datos
		panelResultados.addLegend("SOUTH");
		// dibujar la grafica
		panelResultados.addLinePlot("Cromosoma mejor de cada generaci—n", x, aptitudesMejores);
		panelResultados.addLinePlot("Cromosoma mejor encontrado", x, gokusMejores);
	}
	
	private void obtenerTabla()
	{
		String[] datos = new String[info.length + LINEAS_CABECERA];
		datos[0] = new String("Lista de picos");
		datos[1] = new String("==============");
		
		for(int i = 0; i < info.length; i++) {
			datos[i + LINEAS_CABECERA] = new String(
					"generacion: " + info[i].generacion +
					"\t\t fenotipo: " + String.valueOf(info[i].aptitud)
					);
		}
		
		listaPicos.setListData(datos);
	}
	
	private void recogerParametros()
	{
		// TODO => Validar campos
		this.parametros.setTamPoblacion(Integer.parseInt(formPoblacion.getText()));
		this.parametros.setNumGeneraciones(Integer.parseInt(formGeneraciones.getText()));
		this.parametros.setProbCruce(Double.parseDouble(formCruce.getText()));
		this.parametros.setProbMutacion(Double.parseDouble(formMutacion.getText()));
		this.parametros.setTolerancia(Double.parseDouble(formTolerancia.getText()));
		this.parametros.setFuncion(formFuncion.getSelectedIndex() + 1);
		this.parametros.setSeleccion(formSeleccion.getSelectedIndex());
	}
	
	private void resetearCampos()
	{
		this.formPoblacion.setText(String.valueOf(Parametros.TAM_POBLACION_DEFECTO));
		this.formGeneraciones.setText(String.valueOf(Parametros.NUM_GENERACIONES_DEFECTO));
		this.formCruce.setText(String.valueOf(Parametros.PROB_CRUCE_DEFECTO));
		this.formMutacion.setText(String.valueOf(Parametros.PROB_MUTACION_DEFECTO));
		this.formTolerancia.setText(String.valueOf(Parametros.TOLERANCIA_DEFECTO));
		this.formFuncion.setSelectedIndex(0);
		this.formSeleccion.setSelectedIndex(1);
	}
	
	private void mostrarDetallesCromosoma(int primerIndice, int ultimoIndice)
	{
		//TODO: Captar las lineas de cabecera
		// FIXME: no funcionan bien los indices. 
		String s = "";
		for (int i = primerIndice; i <= ultimoIndice; i++) {
			s += 	"Cromosoma: " + info[i-LINEAS_CABECERA].cadena + "\n" + 
					"Representa: " + info[i-LINEAS_CABECERA].fenotipo + "\n" + 
					"Aptitud: " + info[i-LINEAS_CABECERA].aptitud + "\n" +
					"----------------------------------------------------- \n";
		}
		areaInfo.setText(s);
	}
	
	private void propiedadesBasicas()
	{
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, FRAME_WEIGHT, FRAME_HEIGHT);
		setResizable(false);
	}
	
	public Parametros getParametros(){ return parametros; }
	
	/**
	 * TODO: hacer el javadoc
	 * @param aptitudesMejores
	 * @param gokusMejores
	 * @param picos
	 */
	public void mostrar( double[] aptitudesMejores, double[] gokusMejores, Info[] infoMostrar)
	{
		// XXX
		this.info = infoMostrar;
		obtenerResultados(aptitudesMejores,gokusMejores);
		obtenerTabla();
	}
}
