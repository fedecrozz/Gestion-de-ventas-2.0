import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.JPasswordField;
import java.awt.Component;
import javax.swing.Box;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JDateChooser fecha;
	private JTextField detalle;
	private JTextField salida;
	private JTextField ingreso;
	private JTextField saldo_final;
	private DefaultTableModel modelo = new DefaultTableModel();
	private DefaultTableModel modeloFiados = new DefaultTableModel();
	private JComboBox medio_de_pago;
	private Conector con = new Conector();
	private JScrollPane scrollPane;	
	private double saldoInicial = 0;
	private GUI gui = new GUI();
	private boolean estado_caja;
	private JDateChooser fecha_desde;
	private JTable table_1;
	private JLabel fecha_resumen;
	private JButton actualizar;
	private JDateChooser fecha_hasta;
	private JLabel subtotalIngresos;
	private JLabel total;
	private JLabel cantSalidas;
	private JLabel cantIngresos;
	private JLabel subtotalSalidas;
	private JLabel ingresosEfectivo;
	private JLabel ingresosTarjeta;
	private JLabel ingresosQR;
	private JLabel totalIngresos;
	private JLabel salidasEfectivo;
	private JLabel salidasTarjeta;
	private JLabel salidasQR;
	private JLabel totalSalidas;
	private JLabel cantSalidasTarjeta;
	private JLabel cantSalidasQR;
	private JLabel cantSalidasEfectivo;
	private JLabel cantIngresosEfectivo;
	private JLabel cantIngresosTarjeta;
	private JLabel cantIngresosQR;
	private JTable table_2;
	private JTextField totalFiados;
	private JTextField saldo_inicial;
	private boolean seguridad_activada = true;
	private JButton btnEliminarMovimiento;
	private JButton btnNewButton_3;
	private String pass = "admin";
	private JPasswordField clave;
	private JPanel panel_3;
	private JPanel panel_2;
	private JPanel panelVentas;
	private JPanel panelResumenDia;
	private JScrollPane panelFiados;
	private JPanel panelFiados2;
	private JTextField buscador;
	private JLabel saldoFinalEfectivo;
	private JButton btnRestarDeuda;
	private JButton btnDetalleDeuda;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setResizable(false);
		setTitle("Sofware Facil - Gestion de Ventas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		Toolkit toolkit =  getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1264, 681);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#219ebc"));
		tabbedPane.addTab("Movimientos diarios", null, panel, null);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 1239, 498);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Fecha", "Detalle", "Ingreso", "Salida", "Medio de pago", "Saldo"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(10, 11, 1239, 68);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fecha");
		lblNewLabel.setBounds(10, 11, 124, 26);
		panel_4.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		fecha = new JDateChooser();
		fecha.setBounds(10, 35, 124, 20);
		panel_4.add(fecha);
				
		detalle = new JTextField();
		
		detalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {									
					ingresarMovimiento();									
				}
			}
		});
		detalle.setBounds(144, 35, 124, 20);
		panel_4.add(detalle);
		detalle.setColumns(10);
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setBounds(144, 12, 124, 25);
		panel_4.add(lblDetalle);
		lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblIngreso = new JLabel("Ingreso");
		lblIngreso.setBounds(278, 12, 124, 25);
		panel_4.add(lblIngreso);
		lblIngreso.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		ingreso = new JTextField();
		ingreso.setBounds(278, 35, 124, 20);
		panel_4.add(ingreso);
		ingreso.setColumns(10);
		
		ingreso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {									
					ingresarMovimiento();									
				}
			}
		});
		
		salida = new JTextField();
		salida.setBounds(412, 34, 124, 20);
		panel_4.add(salida);
		salida.setColumns(10);
		
		salida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {									
					ingresarMovimiento();									
				}
			}
		});
		
		JLabel lblMonto = new JLabel("Salida");
		lblMonto.setBounds(412, 11, 124, 25);
		panel_4.add(lblMonto);
		lblMonto.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblMedioDePago = new JLabel("Medio de pago");
		lblMedioDePago.setBounds(546, 12, 124, 25);
		panel_4.add(lblMedioDePago);
		lblMedioDePago.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		medio_de_pago = new JComboBox();
		
		medio_de_pago.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {									
					ingresarMovimiento();									
				}
			}
		});
		medio_de_pago.setBounds(546, 35, 124, 20);
		panel_4.add(medio_de_pago);
		medio_de_pago.setModel(new DefaultComboBoxModel(new String[] {"EFECTIVO", "TARJETA", "QR"}));
		
		JButton btnNewButton = new JButton("Ingresar movimiento");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ingresarMovimiento();
			}
		});
		btnNewButton.setBounds(680, 34, 159, 23);
		panel_4.add(btnNewButton);
		
		btnNewButton_3 = new JButton("Activar Seguridad");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				con.conectar();
				int seguridad = con.getSeguridad();
				con.cerrarConexion();
				if(seguridad==1) {					
					if(clave.getText().equals(pass)) {
						
						clave.setText("");
						clave.setEnabled(false);
						
						con.conectar();
						con.setSeguridad(0);
						con.cerrarConexion();
						
					}else {
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
					}
				}else {
					
					clave.setEnabled(true);
					con.conectar();
					con.setSeguridad(1);
					con.cerrarConexion();
				}
				iniciarSeguridad();
			}
		});
		btnNewButton_3.setBounds(1056, 35, 173, 26);
		panel_4.add(btnNewButton_3);
		
		clave = new JPasswordField();
		clave.setBounds(1056, 11, 173, 20);
		panel_4.add(clave);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(10, 599, 1239, 43);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel label_1 = new JLabel("Saldo del d\u00EDa");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(886, 11, 124, 20);
		panel_5.add(label_1);
		
		saldo_final = new JTextField();
		saldo_final.setEditable(false);
		saldo_final.setColumns(10);
		saldo_final.setBounds(1020, 11, 209, 20);
		panel_5.add(saldo_final);
		
		btnEliminarMovimiento = new JButton("Eliminar movimiento");
		btnEliminarMovimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Tiene que seleccionar un movimiento");
				}else {
					eliminarMovimiento();					
				}
			}
		});
		btnEliminarMovimiento.setBounds(10, 11, 159, 23);
		panel_5.add(btnEliminarMovimiento);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.decode("#219ebc"));
		tabbedPane.addTab("Resumen", null, panel_2, null);
		panel_2.setLayout(null);
		
		panelVentas = new JPanel();
		panelVentas.setBounds(10, 11, 1239, 342);
		panel_2.add(panelVentas);
		panelVentas.setLayout(null);
		
		fecha_desde = new JDateChooser();
		fecha_desde.setBounds(64, 49, 130, 20);
		panelVentas.add(fecha_desde);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setHorizontalAlignment(SwingConstants.LEFT);
		lblDesde.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDesde.setBounds(10, 48, 62, 20);
		panelVentas.add(lblDesde);
		
		JLabel lblFechaDeVentas = new JLabel("Fecha de ventas:");
		lblFechaDeVentas.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaDeVentas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFechaDeVentas.setBounds(10, 11, 158, 20);
		panelVentas.add(lblFechaDeVentas);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setHorizontalAlignment(SwingConstants.LEFT);
		lblHasta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHasta.setBounds(204, 48, 62, 20);
		panelVentas.add(lblHasta);
		
		actualizar = new JButton("Actualizar");
		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarResumenGeneral();
			}
		});
		actualizar.setBounds(396, 49, 130, 20);
		panelVentas.add(actualizar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 80, 1219, 251);
		panelVentas.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Ingresos Efectivo", "Salidas Efectivo", "Ingresos Tarjetas", "Salidas Tarjetas", "Ingresos QR", "Salidas QR", "Total Ingresos", "Total Salidas", "Saldo Inicial"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		fecha_hasta = new JDateChooser();
		fecha_hasta.setBounds(256, 49, 130, 20);
		panelVentas.add(fecha_hasta);
		
		panelResumenDia = new JPanel();
		panelResumenDia.setBounds(10, 364, 1239, 278);
		panel_2.add(panelResumenDia);
		panelResumenDia.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBounds(10, 11, 393, 256);
		panelResumenDia.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblResumenDelDa = new JLabel("Resumen del d\u00EDa:");
		lblResumenDelDa.setBounds(10, 11, 132, 20);
		panel_6.add(lblResumenDelDa);
		lblResumenDelDa.setBackground(Color.LIGHT_GRAY);
		lblResumenDelDa.setHorizontalAlignment(SwingConstants.LEFT);
		lblResumenDelDa.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		fecha_resumen = new JLabel("05/05/22");
		fecha_resumen.setBounds(140, 11, 195, 20);
		panel_6.add(fecha_resumen);
		fecha_resumen.setHorizontalAlignment(SwingConstants.LEFT);
		fecha_resumen.setFont(new Font("Tahoma", Font.BOLD, 14));
		fecha_resumen.setBackground(Color.LIGHT_GRAY);
		
		subtotalIngresos = new JLabel("$0");
		subtotalIngresos.setBorder(new LineBorder(new Color(0, 0, 0)));
		subtotalIngresos.setBounds(174, 42, 209, 20);
		panel_6.add(subtotalIngresos);
		subtotalIngresos.setHorizontalAlignment(SwingConstants.LEFT);
		subtotalIngresos.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblIngresosEnEfectivo = new JLabel("Subtotal Ingresos:");
		lblIngresosEnEfectivo.setBounds(10, 42, 141, 20);
		panel_6.add(lblIngresosEnEfectivo);
		lblIngresosEnEfectivo.setHorizontalAlignment(SwingConstants.LEFT);
		lblIngresosEnEfectivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblSalidasEnEfectivo = new JLabel("Subtotal Salidas:");
		lblSalidasEnEfectivo.setBounds(10, 73, 132, 20);
		panel_6.add(lblSalidasEnEfectivo);
		lblSalidasEnEfectivo.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalidasEnEfectivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		subtotalSalidas = new JLabel("$0");
		subtotalSalidas.setBorder(new LineBorder(new Color(0, 0, 0)));
		subtotalSalidas.setBounds(174, 73, 209, 20);
		panel_6.add(subtotalSalidas);
		subtotalSalidas.setHorizontalAlignment(SwingConstants.LEFT);
		subtotalSalidas.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		total = new JLabel("$0");
		total.setBorder(new LineBorder(new Color(0, 0, 0)));
		total.setBounds(152, 225, 231, 20);
		panel_6.add(total);
		total.setHorizontalAlignment(SwingConstants.LEFT);
		total.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(10, 225, 132, 20);
		panel_6.add(lblTotal);
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblCantidadDeIngresos = new JLabel("Cantidad de ingresos:");
		lblCantidadDeIngresos.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadDeIngresos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCantidadDeIngresos.setBounds(10, 104, 154, 20);
		panel_6.add(lblCantidadDeIngresos);
		
		JLabel lblCantidadDeSalidas = new JLabel("Cantidad de salidas:");
		lblCantidadDeSalidas.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadDeSalidas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCantidadDeSalidas.setBounds(10, 135, 154, 20);
		panel_6.add(lblCantidadDeSalidas);
		
		cantIngresos = new JLabel("0");
		cantIngresos.setBorder(new LineBorder(new Color(0, 0, 0)));
		cantIngresos.setHorizontalAlignment(SwingConstants.LEFT);
		cantIngresos.setFont(new Font("Tahoma", Font.BOLD, 13));
		cantIngresos.setBounds(174, 104, 209, 20);
		panel_6.add(cantIngresos);
		
		cantSalidas = new JLabel("0");
		cantSalidas.setBorder(new LineBorder(new Color(0, 0, 0)));
		cantSalidas.setHorizontalAlignment(SwingConstants.LEFT);
		cantSalidas.setFont(new Font("Tahoma", Font.BOLD, 13));
		cantSalidas.setBounds(174, 135, 209, 20);
		panel_6.add(cantSalidas);
		
		JLabel lblSaldoInicial = new JLabel("Saldo inicial:");
		lblSaldoInicial.setHorizontalAlignment(SwingConstants.LEFT);
		lblSaldoInicial.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSaldoInicial.setBounds(10, 194, 154, 20);
		panel_6.add(lblSaldoInicial);
		
		saldo_inicial = new JTextField();
		saldo_inicial.setBorder(new LineBorder(Color.BLACK));
		saldo_inicial.setFont(new Font("Tahoma", Font.BOLD, 12));
		saldo_inicial.setOpaque(false);
		saldo_inicial.setBounds(174, 194, 120, 20);
		panel_6.add(saldo_inicial);
		saldo_inicial.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Abrir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirCaja();
			}
		});
		btnNewButton_2.setBounds(304, 194, 79, 20);
		panel_6.add(btnNewButton_2);
		
		JLabel lblSaldoEnEfectivo = new JLabel("Saldo en Efectivo:");
		lblSaldoEnEfectivo.setHorizontalAlignment(SwingConstants.LEFT);
		lblSaldoEnEfectivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSaldoEnEfectivo.setBounds(10, 163, 154, 20);
		panel_6.add(lblSaldoEnEfectivo);
		
		saldoFinalEfectivo = new JLabel("0");
		saldoFinalEfectivo.setHorizontalAlignment(SwingConstants.LEFT);
		saldoFinalEfectivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		saldoFinalEfectivo.setBorder(new LineBorder(new Color(0, 0, 0)));
		saldoFinalEfectivo.setBounds(174, 163, 209, 20);
		panel_6.add(saldoFinalEfectivo);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBounds(413, 11, 403, 256);
		panelResumenDia.add(panel_7);
		
		JLabel lblCantidadDeVentas = new JLabel("Detalle ingresos del d\u00EDa");
		lblCantidadDeVentas.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadDeVentas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCantidadDeVentas.setBackground(Color.LIGHT_GRAY);
		lblCantidadDeVentas.setBounds(10, 11, 174, 20);
		panel_7.add(lblCantidadDeVentas);
		
		ingresosEfectivo = new JLabel("$0");
		ingresosEfectivo.setBorder(new LineBorder(new Color(0, 0, 0)));
		ingresosEfectivo.setHorizontalAlignment(SwingConstants.LEFT);
		ingresosEfectivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		ingresosEfectivo.setBounds(152, 53, 183, 20);
		panel_7.add(ingresosEfectivo);
		
		JLabel lblVentasEnEfectivo = new JLabel("Ingresos en efectivo");
		lblVentasEnEfectivo.setHorizontalAlignment(SwingConstants.LEFT);
		lblVentasEnEfectivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVentasEnEfectivo.setBounds(10, 53, 141, 20);
		panel_7.add(lblVentasEnEfectivo);
		
		JLabel lblVentas = new JLabel("Ingresos en tarjeta:");
		lblVentas.setHorizontalAlignment(SwingConstants.LEFT);
		lblVentas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVentas.setBounds(10, 84, 132, 20);
		panel_7.add(lblVentas);
		
		ingresosTarjeta = new JLabel("$0");
		ingresosTarjeta.setBorder(new LineBorder(new Color(0, 0, 0)));
		ingresosTarjeta.setHorizontalAlignment(SwingConstants.LEFT);
		ingresosTarjeta.setFont(new Font("Tahoma", Font.BOLD, 13));
		ingresosTarjeta.setBounds(152, 84, 183, 20);
		panel_7.add(ingresosTarjeta);
		
		totalIngresos = new JLabel("$0");
		totalIngresos.setBorder(new LineBorder(new Color(0, 0, 0)));
		totalIngresos.setHorizontalAlignment(SwingConstants.LEFT);
		totalIngresos.setFont(new Font("Tahoma", Font.BOLD, 13));
		totalIngresos.setBounds(152, 225, 241, 20);
		panel_7.add(totalIngresos);
		
		JLabel lblTotalIngresos = new JLabel("Total ingresos:");
		lblTotalIngresos.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalIngresos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalIngresos.setBounds(10, 225, 132, 20);
		panel_7.add(lblTotalIngresos);
		
		JLabel lblIngresosEnQr = new JLabel("Ingresos en QR:");
		lblIngresosEnQr.setHorizontalAlignment(SwingConstants.LEFT);
		lblIngresosEnQr.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIngresosEnQr.setBounds(10, 115, 132, 20);
		panel_7.add(lblIngresosEnQr);
		
		ingresosQR = new JLabel("$0");
		ingresosQR.setBorder(new LineBorder(new Color(0, 0, 0)));
		ingresosQR.setHorizontalAlignment(SwingConstants.LEFT);
		ingresosQR.setFont(new Font("Tahoma", Font.BOLD, 13));
		ingresosQR.setBounds(152, 115, 183, 20);
		panel_7.add(ingresosQR);
		
		cantIngresosEfectivo = new JLabel("0");
		cantIngresosEfectivo.setBorder(new LineBorder(new Color(0, 0, 0)));
		cantIngresosEfectivo.setHorizontalAlignment(SwingConstants.LEFT);
		cantIngresosEfectivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		cantIngresosEfectivo.setBounds(345, 53, 48, 20);
		panel_7.add(cantIngresosEfectivo);
		
		cantIngresosTarjeta = new JLabel("0");
		cantIngresosTarjeta.setHorizontalAlignment(SwingConstants.LEFT);
		cantIngresosTarjeta.setFont(new Font("Tahoma", Font.BOLD, 13));
		cantIngresosTarjeta.setBorder(new LineBorder(new Color(0, 0, 0)));
		cantIngresosTarjeta.setBounds(345, 84, 48, 20);
		panel_7.add(cantIngresosTarjeta);
		
		cantIngresosQR = new JLabel("0");
		cantIngresosQR.setHorizontalAlignment(SwingConstants.LEFT);
		cantIngresosQR.setFont(new Font("Tahoma", Font.BOLD, 13));
		cantIngresosQR.setBorder(new LineBorder(new Color(0, 0, 0)));
		cantIngresosQR.setBounds(345, 115, 48, 20);
		panel_7.add(cantIngresosQR);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.setBounds(826, 11, 403, 256);
		panelResumenDia.add(panel_9);
		
		JLabel lblDetalleSalidasDel = new JLabel("Detalle salidas del d\u00EDa");
		lblDetalleSalidasDel.setHorizontalAlignment(SwingConstants.LEFT);
		lblDetalleSalidasDel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDetalleSalidasDel.setBackground(Color.LIGHT_GRAY);
		lblDetalleSalidasDel.setBounds(10, 11, 174, 20);
		panel_9.add(lblDetalleSalidasDel);
		
		salidasEfectivo = new JLabel("$0");
		salidasEfectivo.setHorizontalAlignment(SwingConstants.LEFT);
		salidasEfectivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		salidasEfectivo.setBorder(new LineBorder(new Color(0, 0, 0)));
		salidasEfectivo.setBounds(152, 53, 183, 20);
		panel_9.add(salidasEfectivo);
		
		JLabel lblSalidasEnEfectivo_1 = new JLabel("Salidas en efectivo");
		lblSalidasEnEfectivo_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalidasEnEfectivo_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSalidasEnEfectivo_1.setBounds(10, 53, 141, 20);
		panel_9.add(lblSalidasEnEfectivo_1);
		
		JLabel lblSalidasEnTarjeta = new JLabel("Salidas en tarjeta:");
		lblSalidasEnTarjeta.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalidasEnTarjeta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSalidasEnTarjeta.setBounds(10, 84, 132, 20);
		panel_9.add(lblSalidasEnTarjeta);
		
		salidasTarjeta = new JLabel("$0");
		salidasTarjeta.setHorizontalAlignment(SwingConstants.LEFT);
		salidasTarjeta.setFont(new Font("Tahoma", Font.BOLD, 13));
		salidasTarjeta.setBorder(new LineBorder(new Color(0, 0, 0)));
		salidasTarjeta.setBounds(152, 84, 183, 20);
		panel_9.add(salidasTarjeta);
		
		totalSalidas = new JLabel("$0");
		totalSalidas.setHorizontalAlignment(SwingConstants.LEFT);
		totalSalidas.setFont(new Font("Tahoma", Font.BOLD, 13));
		totalSalidas.setBorder(new LineBorder(new Color(0, 0, 0)));
		totalSalidas.setBounds(152, 225, 241, 20);
		panel_9.add(totalSalidas);
		
		JLabel lblTotalSalidas = new JLabel("Total salidas:");
		lblTotalSalidas.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalSalidas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalSalidas.setBounds(10, 225, 132, 20);
		panel_9.add(lblTotalSalidas);
		
		JLabel lblSalidasEnQr = new JLabel("Salidas en QR:");
		lblSalidasEnQr.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalidasEnQr.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSalidasEnQr.setBounds(10, 115, 132, 20);
		panel_9.add(lblSalidasEnQr);
		
		salidasQR = new JLabel("$0");
		salidasQR.setHorizontalAlignment(SwingConstants.LEFT);
		salidasQR.setFont(new Font("Tahoma", Font.BOLD, 13));
		salidasQR.setBorder(new LineBorder(new Color(0, 0, 0)));
		salidasQR.setBounds(152, 115, 183, 20);
		panel_9.add(salidasQR);
		
		cantSalidasEfectivo = new JLabel("0");
		cantSalidasEfectivo.setHorizontalAlignment(SwingConstants.LEFT);
		cantSalidasEfectivo.setFont(new Font("Tahoma", Font.BOLD, 13));
		cantSalidasEfectivo.setBorder(new LineBorder(new Color(0, 0, 0)));
		cantSalidasEfectivo.setBounds(345, 53, 48, 20);
		panel_9.add(cantSalidasEfectivo);
		
		cantSalidasTarjeta = new JLabel("0");
		cantSalidasTarjeta.setHorizontalAlignment(SwingConstants.LEFT);
		cantSalidasTarjeta.setFont(new Font("Tahoma", Font.BOLD, 13));
		cantSalidasTarjeta.setBorder(new LineBorder(new Color(0, 0, 0)));
		cantSalidasTarjeta.setBounds(345, 84, 48, 20);
		panel_9.add(cantSalidasTarjeta);
		
		cantSalidasQR = new JLabel("0");
		cantSalidasQR.setHorizontalAlignment(SwingConstants.LEFT);
		cantSalidasQR.setFont(new Font("Tahoma", Font.BOLD, 13));
		cantSalidasQR.setBorder(new LineBorder(new Color(0, 0, 0)));
		cantSalidasQR.setBounds(345, 115, 48, 20);
		panel_9.add(cantSalidasQR);
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.decode("#219ebc"));
		tabbedPane.addTab("Deuda de clientes", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBounds(10, 11, 1239, 83);
		panel_3.add(panel_10);
		panel_10.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Deuda de clientes");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(495, 22, 249, 38);
		panel_10.add(lblNewLabel_1);
		
		panelFiados = new JScrollPane();
		panelFiados.setBounds(10, 159, 1239, 429);
		panel_3.add(panelFiados);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Cliente", "Deuda"
			}
		));
		panelFiados.setViewportView(table_2);
		
		panelFiados2 = new JPanel();
		panelFiados2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFiados2.setBounds(10, 599, 1239, 43);
		panel_3.add(panelFiados2);
		panelFiados2.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Agregar Cliente");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ingresarFiado();
			}
		});
		btnNewButton_1.setBounds(10, 10, 141, 23);
		panelFiados2.add(btnNewButton_1);
		
		JButton btnModificarCliente = new JButton("Agregar Deuda");
		btnModificarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_2.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Seleccione un cliente");
				}else {
					agregarDeudaCliente(table_2.getValueAt(table_2.getSelectedRow(), 0).toString());
				}
			}
		});
		btnModificarCliente.setBounds(312, 10, 141, 23);
		panelFiados2.add(btnModificarCliente);
		
		JButton btnEliminarCliente = new JButton("Eliminar Cliente");
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_2.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Seleccione un cliente");
				}else {
					eliminarCliente(table_2.getValueAt(table_2.getSelectedRow(), 0).toString());
				}
			}
		});
		btnEliminarCliente.setBounds(161, 10, 141, 23);
		panelFiados2.add(btnEliminarCliente);
		
		JLabel lblTotalFiados = new JLabel("Total deudas");
		lblTotalFiados.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalFiados.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalFiados.setBounds(886, 11, 124, 20);
		panelFiados2.add(lblTotalFiados);
		
		totalFiados = new JTextField();
		totalFiados.setText("0.0");
		totalFiados.setEditable(false);
		totalFiados.setColumns(10);
		totalFiados.setBounds(1020, 11, 209, 20);
		panelFiados2.add(totalFiados);
		
		btnRestarDeuda = new JButton("Restar Deuda");
		btnRestarDeuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table_2.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Seleccione un cliente");
				}else {
					restarDeudaCliente(table_2.getValueAt(table_2.getSelectedRow(), 0).toString());
				}
			}
		});
		btnRestarDeuda.setBounds(463, 10, 141, 23);
		panelFiados2.add(btnRestarDeuda);
		
		btnDetalleDeuda = new JButton("Detalle Deuda");
		btnDetalleDeuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_2.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Seleccione un cliente");
				}else {
					con.conectar();
					Cliente c = con.getCliente(table_2.getValueAt(table_2.getSelectedRow(), 0).toString());
					con.cerrarConexion();
										
					con.conectar();
					ArrayList<DeudaDet> deudas = con.getDeudaDet(c.getNombre());
					con.cerrarConexion();
										
					Deuda d = new Deuda(c,deudas);
					
					d.setVisible(true);
					
				}
			}
		});
		btnDetalleDeuda.setBounds(614, 10, 141, 23);
		panelFiados2.add(btnDetalleDeuda);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 105, 1239, 43);
		panel_3.add(panel_1);
		panel_1.setLayout(null);
		
		buscador = new JTextField();
		buscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				actualizarTablaDeudas(buscador.getText());
			}
		});
		buscador.setBounds(77, 11, 488, 20);
		panel_1.add(buscador);
		buscador.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCliente.setBounds(10, 9, 57, 25);
		panel_1.add(lblCliente);
		
		iniciarTodo();
		
		
		
	}
	
	public void iniciarTodo() {
		cargarMovimientos();
		iniciarSaldoInicial();
		iniciarFecha();
		actualizarResumenGeneral();
		iniciarFiados();
		iniciarSeguridad();
	}
	
	public void iniciarSeguridad() {
		int seguridad = 1;
		
		con.conectar();
		seguridad = con.getSeguridad();
		con.cerrarConexion();
		
		if(seguridad==1) {
			btnEliminarMovimiento.setEnabled(false);
			btnNewButton_3.setText("Desactivar seguridad");
			panelVentas.setVisible(false);
			panelResumenDia.setVisible(false);
			panelFiados.setVisible(false);
			panelFiados2.setVisible(false);
		}else {
			btnEliminarMovimiento.setEnabled(true);
			btnNewButton_3.setText("Activar seguridad");
			panelVentas.setVisible(true);
			panelResumenDia.setVisible(true);
			panelFiados.setVisible(true);
			panelFiados2.setVisible(true);
		}
	}
	
	public void iniciarSaldoInicial() {
		con.conectar();
		double saldo = con.getSaldoInicial(getFechaByDate(new Date()));
		con.cerrarConexion();
		
		saldo_inicial.setText(String.valueOf(saldo));
	}
	
	public void agregarDeudaCliente(String nombre) {
		
		String input =JOptionPane.showInputDialog("¿Cuanto quiere agregar?");
				
		if(Double.valueOf(input) <=0) {
			JOptionPane.showMessageDialog(null, "Ingrese un monto valido");
		}else {
			
			con.conectar();
			Cliente c1 = new Cliente();
			c1 = con.getCliente(nombre);
			con.cerrarConexion();
			
			double saldoAnterior = c1.getDeuda();
			Cliente c = new Cliente();
			c.setNombre(nombre);
			c.setDeuda(saldoAnterior+Double.valueOf(input));
			
			con.conectar();
			con.modificarCliente(c);
			con.cerrarConexion();
			
			con.conectar();
			con.agregarDeuda(c.getNombre(),Double.valueOf(input),getFechaByDate(fecha.getDate())+" "+getHora());
			con.cerrarConexion();
			
			
			
			iniciarFiados();
		}
	}
	
	public void restarDeudaCliente(String nombre) {
		
		String input =JOptionPane.showInputDialog("¿Cuanto quiere restar?");
				
		if(Double.valueOf(input) <=0) {
			JOptionPane.showMessageDialog(null, "Ingrese un monto valido");
		}else {
			
			con.conectar();
			Cliente c1 = new Cliente();
			c1 = con.getCliente(nombre);
			con.cerrarConexion();
			
			double saldoAnterior = c1.getDeuda();
			Cliente c = new Cliente();
			c.setNombre(nombre);
			c.setDeuda(saldoAnterior-Double.valueOf(input));
			
			con.conectar();
			con.modificarCliente(c);
			con.cerrarConexion();
			
			con.conectar();
			con.agregarDeuda(c.getNombre(),(-1*Double.valueOf(input)),getFechaByDate(fecha.getDate())+" "+getHora());
			con.cerrarConexion();
			
			iniciarFiados();
		}
	}
	
	public void eliminarCliente(String nombre) {
		int input = JOptionPane.showConfirmDialog(null, "¿Estas seguro que quiere eliminar a este cliente?");
		if(input == 0) {
			con.conectar();
			con.borrarCliente(nombre);
			con.cerrarConexion();
			
			con.conectar();
			con.eliminarDeuda(nombre);
			con.cerrarConexion();
			
			iniciarFiados();
		}
	}
	
	public void ingresarFiado() {
		String nombre_cliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
		nombre_cliente = nombre_cliente.toUpperCase();
		
		if(nombre_cliente.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tiene que agregar un nombre para el cliente");
		}else {
			con.conectar();
			boolean existe = con.existeCliente(nombre_cliente);
			con.cerrarConexion();
			
			if(existe) {
				JOptionPane.showMessageDialog(null, "Ya existe un cliente con este nombre");
			}else {
				Cliente c = new Cliente();
				c.setNombre(nombre_cliente);
				c.setDeuda(0);
				
				String input = JOptionPane.showInputDialog("Ingrese la deuda de este cliente");
				
				if(Double.valueOf(input) <=0) {
					JOptionPane.showMessageDialog(null, "Ingrese un monto valido");
				}else {
					
					c.setDeuda(Double.valueOf(input));
					con.conectar();
					con.guardarCliente(c);
					con.cerrarConexion();
					
					con.conectar();
					con.agregarDeuda(c.getNombre(),Double.valueOf(input),getFechaByDate(fecha.getDate())+" "+getHora());
					con.cerrarConexion();
					
					iniciarFiados();
				}
			}
			
			
		}
	}

	public void iniciarFiados() {
		modeloFiados = new DefaultTableModel();
		
		con.conectar();
		ArrayList<Cliente> clientes = con.getClientes();
		con.cerrarConexion();
		
		modeloFiados.addColumn("Cliente");
		modeloFiados.addColumn("Deuda");
		
		
		double totalDeuda = 0;
		
		for(int i = 0 ; i< clientes.size();i++) {
			Cliente c = clientes.get(i);
			totalDeuda=totalDeuda + c.getDeuda();
			actualizarTotalFiado(totalDeuda);
			modeloFiados.addRow(new Object[] {c.getNombre(),"$"+String.valueOf(c.getDeuda())});
			
		}
		
		actualizarTotalFiado(totalDeuda);
		table_2.setModel(modeloFiados);
		
	}
	
	public void actualizarResumenGeneral() {
		actualizarVentas();
		actualizarSalidasDia();
		actualizarIngresosDia();
		actualizarResumenDia();
	}
	
	public void actualizarSalidasDia() {
		
		//EFECTIVO
		con.conectar();
		double salidasEfectivo = con.getMontoBy(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "SALIDA","EFECTIVO");
		con.cerrarConexion();		
		this.salidasEfectivo.setText("$"+String.valueOf(salidasEfectivo));
		
		con.conectar();
		int cantSalidasEfectivo = con.getCantidad(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "SALIDA","EFECTIVO");
		con.cerrarConexion();
		this.cantSalidasEfectivo.setText(String.valueOf(cantSalidasEfectivo));
		
		//TARJETA		
		con.conectar();
		double salidasTarjeta = con.getMontoBy(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "SALIDA","TARJETA");
		con.cerrarConexion();
		this.salidasTarjeta.setText("$"+String.valueOf(salidasTarjeta));
		
		con.conectar();
		int cantSalidasTarjeta = con.getCantidad(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "SALIDA","TARJETA");
		con.cerrarConexion();
		this.cantSalidasTarjeta.setText(String.valueOf(cantSalidasTarjeta));
		
		//QR
		con.conectar();
		double salidasQR = con.getMontoBy(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "SALIDA","QR");
		con.cerrarConexion();
		this.salidasQR.setText("$"+String.valueOf(salidasQR));
		
		con.conectar();
		int cantSalidasQR = con.getCantidad(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "SALIDA","QR");
		con.cerrarConexion();
		this.cantSalidasQR.setText(String.valueOf(cantSalidasQR));
		
		
		subtotalSalidas.setText("$"+(salidasEfectivo + salidasTarjeta + salidasQR));		
		totalSalidas.setText("$"+(salidasEfectivo + salidasTarjeta + salidasQR));  
		
	}
	
	public void actualizarIngresosDia() {
		
		//EFECTIVO
		con.conectar();
		double ingresosEfectivo = con.getMontoBy(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "INGRESO","EFECTIVO");
		con.cerrarConexion();		
		this.ingresosEfectivo.setText("$"+String.valueOf(ingresosEfectivo));
		
		con.conectar();
		int cantIngresosEfectivo = con.getCantidad(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "INGRESO","EFECTIVO");
		con.cerrarConexion();
		this.cantIngresosEfectivo.setText(String.valueOf(cantIngresosEfectivo));
		
		//TARJETA
		con.conectar();
		double ingresosTarjeta = con.getMontoBy(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "INGRESO","TARJETA");
		con.cerrarConexion();
		this.ingresosTarjeta.setText("$"+String.valueOf(ingresosTarjeta));
		
		con.conectar();
		int cantIngresosTarjeta = con.getCantidad(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "INGRESO","TARJETA");
		con.cerrarConexion();
		this.cantIngresosTarjeta.setText(String.valueOf(cantIngresosTarjeta));
		
		//QR
		con.conectar();
		double ingresosQR = con.getMontoBy(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "INGRESO","QR");
		con.cerrarConexion();
		this.ingresosQR.setText("$"+String.valueOf(ingresosQR));
		
		con.conectar();
		int cantIngresosQR = con.getCantidad(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "INGRESO","QR");
		con.cerrarConexion();
		this.cantIngresosQR.setText(String.valueOf(cantIngresosQR));
		
		
		
		subtotalIngresos.setText("$"+(ingresosEfectivo + ingresosTarjeta + ingresosQR));	
		totalIngresos.setText("$"+(ingresosEfectivo + ingresosTarjeta + ingresosQR));  
		
	}
	
	public void actualizarResumenDia() {
		
		con.conectar();
		int cantIngresos = con.getCantidad(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "INGRESO");
		con.cerrarConexion();
		this.cantIngresos.setText(String.valueOf(cantIngresos));
		
		
		con.conectar();
		int cantSalidas = con.getCantidad(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "SALIDA");
		con.cerrarConexion();
		this.cantSalidas.setText(String.valueOf(cantSalidas));
		
		con.conectar();
		double total = con.getMontoBy(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()));
		con.cerrarConexion();
		
		double saldo_inicial = 0;
		con.conectar();
		saldo_inicial = con.getSaldoInicial(getFechaByDate(fecha.getDate()));		
		con.cerrarConexion();
		
		
		con.conectar();
		double ingresoEfectivo = con.getMontoBy(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "INGRESO","EFECTIVO");
		con.cerrarConexion();
		
		
		con.conectar();
		double salidaEfectivo = con.getMontoBy(getFechaByDate(fecha.getDate()), getFechaByDate(fecha.getDate()), "SALIDA","EFECTIVO");
		con.cerrarConexion();
		
		
		
		saldoFinalEfectivo.setText("$"+(saldo_inicial+ingresoEfectivo+salidaEfectivo));
				
		
		this.total.setText("$"+(total + saldo_inicial));
		
		
	}
		
	public void abrirCaja() {
		if(saldo_inicial.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tiene que ingresar un monto");
		}else {			
			if(Double.valueOf(saldo_inicial.getText())>0) {
				double saldo = 0;
				con.conectar();
				saldo = con.getSaldoInicial(getFechaByDate(fecha.getDate()));
				con.cerrarConexion();
				
				if(saldo!=0) {
					int input = JOptionPane.showConfirmDialog(null, "¿Desea modificar el saldo de la caja de $"+saldo+" a $"+saldo_inicial.getText()+"?");
					if(input == 0) {
						con.conectar();
						con.modificarSaldoInicial(getFechaByDate(fecha.getDate()), Double.valueOf(saldo_inicial.getText()));
						con.cerrarConexion();
					}
				}else {
					con.conectar();
					con.guardarSaldoInicial(getFechaByDate(fecha.getDate()), Double.valueOf(saldo_inicial.getText()));
					con.cerrarConexion();
					JOptionPane.showMessageDialog(null, "Se ha abierto la caja");
				}
			}
		}
		actualizarResumenGeneral();
	}
	
	public String getFechaByDate(java.util.Date date) {
		DateFormat df= new SimpleDateFormat("dd/MM/yyyy");
		String finall = df.format(date);	
		
		return getFechaYYYYMMDD(finall);
	}
	
	public void actualizarVentas() {
		con.conectar();
		ArrayList<Movimiento> movimientos = con.getMovimientos();
		con.cerrarConexion();
		
		double ingresosEfectivo = 0;
		double salidasEfectivo = 0;
		double ingresosTarjeta = 0;
		double salidasTarjeta = 0;
		double ingresosQR = 0;
		double salidasQR = 0;
		double totalIngresos = 0;
		double totalSalidas = 0;
		double totalSaldoInicial = 0;
		//EFECTIVO
		con.conectar();
		ingresosEfectivo = con.getMontoBy(getFechaByDate(fecha_desde.getDate()), getFechaByDate(fecha_hasta.getDate()), "INGRESO","EFECTIVO");
		con.cerrarConexion();
		table_1.setValueAt("$"+String.valueOf(ingresosEfectivo),0, 0);
		
		con.conectar();
		salidasEfectivo = con.getMontoBy(getFechaByDate(fecha_desde.getDate()), getFechaByDate(fecha_hasta.getDate()), "SALIDA","EFECTIVO");
		con.cerrarConexion();
		table_1.setValueAt("$"+String.valueOf(salidasEfectivo),0, 1);
		
		//TARJETA		
		con.conectar();
		ingresosTarjeta = con.getMontoBy(getFechaByDate(fecha_desde.getDate()), getFechaByDate(fecha_hasta.getDate()), "INGRESO","TARJETA");
		con.cerrarConexion();
		table_1.setValueAt("$"+String.valueOf(ingresosTarjeta),0, 2);
		
		con.conectar();
		salidasTarjeta = con.getMontoBy(getFechaByDate(fecha_desde.getDate()), getFechaByDate(fecha_hasta.getDate()), "SALIDA","TARJETA");
		con.cerrarConexion();
		table_1.setValueAt("$"+String.valueOf(salidasTarjeta),0, 3);
		
		//QR
		con.conectar();
		ingresosQR = con.getMontoBy(getFechaByDate(fecha_desde.getDate()), getFechaByDate(fecha_hasta.getDate()), "INGRESO","QR");
		con.cerrarConexion();
		table_1.setValueAt("$"+String.valueOf(ingresosQR),0, 4);
		
		con.conectar();
		salidasQR = con.getMontoBy(getFechaByDate(fecha_desde.getDate()), getFechaByDate(fecha_hasta.getDate()), "SALIDA","QR");
		con.cerrarConexion();
		table_1.setValueAt("$"+String.valueOf(salidasQR),0, 5);
		
		//TOTALES
		totalIngresos = ingresosEfectivo + ingresosTarjeta + ingresosQR;
		table_1.setValueAt("$"+String.valueOf(totalIngresos),0,6);
		
		totalSalidas = salidasEfectivo + salidasTarjeta + salidasQR;
		table_1.setValueAt("$"+String.valueOf(totalSalidas),0, 7);
		
		con.conectar();
		totalSaldoInicial = con.getSaldoInicial(getFechaByDate(fecha_desde.getDate()), getFechaByDate(fecha_hasta.getDate()));
		con.cerrarConexion();
		table_1.setValueAt("$"+String.valueOf(totalSaldoInicial),0, 8);
		
		
	}
		
	public void cerrarCaja() {
		
		String saldo = this.saldo_final.getText();
		
		String Detalle="CIERRE DE CAJA";
		double Ingreso =0;
		double Salida = -1 * Double.valueOf(this.saldo_final.getText());
		double Monto = Salida;
		String Tipo="SALIDA";
		String Medio = "EFECTIVO";
	
		 
		Movimiento m = new Movimiento();
		m.setFecha(getFecha()+" "+getHora());
		m.setDetalle(Detalle);
		m.setMonto(Monto);
		m.setMedio_de_pago(Medio);
		m.setTipo(Tipo);
		
		
		con.conectar();
		con.guardarMovimiento(m);
		con.cerrarConexion();
		
		iniciarFecha();
		detalle.setText("");
		ingreso.setText("");
		salida.setText("");
		
		cargarMovimientos();
		
		JOptionPane.showMessageDialog(null, "La caja se ha cerrado correctamente con $"+ saldo);
	}
			
	public void iniciarFecha() {		
		Date date = new Date();
		fecha.setDate(date);
		fecha_desde.setDate(date);
		fecha_hasta.setDate(date);
		fecha_resumen.setText(getFechaByDate(date));
	}
	
	public String getFecha() {
		LocalDate fechaHoy = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
		String fechaFinal=fechaHoy.format(formato).toString();
		return fechaFinal;	
	}
	
	public String getHora() {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
		return formateador.format(LocalDateTime.now());
	}
	
	public String getFechaYYYYMMDD(String fecha){		
		Date date1;
		String finall="";
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
			DateFormat df= new SimpleDateFormat("yyyy/MM/dd");
			finall = df.format(date1);	
		} catch (ParseException e) {			
			e.printStackTrace();
		}					
		return finall;
	}
	
	public String getFechaDDMMYYYY(String fecha){		
		Date date1;
		String finall="";
		try {
			date1 = new SimpleDateFormat("yyyy/MM/dd").parse(fecha);
			DateFormat df= new SimpleDateFormat("dd/MM/yyyy");
			finall = df.format(date1);	
		} catch (ParseException e) {			
			e.printStackTrace();
		}					
		return finall;
	}
	
	public void ingresarMovimiento() {
				
		if(!ingreso.getText().isEmpty() && !salida.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "No puede cargarse el ingreso y salida al mismo tiempo");
			}else {
					if(ingreso.getText().isEmpty() && salida.getText().isEmpty() ) {
						JOptionPane.showMessageDialog(null, "Tiene que cargar una salida o un ingreso");
					}else {
						
						String Detalle=detalle.getText();
						double Ingreso =0;
						double Salida = 0;
						double Monto = 0;
						String Tipo="";
						String Medio = medio_de_pago.getSelectedItem().toString();
					
						
						if(ingreso.getText().isEmpty()) {
							Ingreso = 0;
						}else {
							Ingreso = Double.valueOf(ingreso.getText());				
						}
						
						
						if(salida.getText().isEmpty()) {
							Salida = 0;
						}else {
							Salida = Double.valueOf(salida.getText());		
							Salida = Salida * -1;
						}		
						
						
						Monto = Ingreso + Salida;						
						
						
						if(Ingreso>0) {
							Tipo = "INGRESO";
						}else {
							Tipo = "SALIDA";
						}
						 
						Movimiento m = new Movimiento();
						
						m.setFecha(getFechaByDate(fecha.getDate())+" "+getHora());
						
						m.setDetalle(Detalle);
						m.setMonto(Monto);
						m.setMedio_de_pago(Medio);
						m.setTipo(Tipo);
						
						
						con.conectar();
						con.guardarMovimiento(m);
						con.cerrarConexion();
						
						iniciarFecha();
						detalle.setText("");
						ingreso.setText("");
						salida.setText("");
						
						cargarMovimientos();
						actualizarResumenGeneral();
						
						
						
					}
				}
			}
			
	public void cargarMovimientos() {
		
		modelo = new DefaultTableModel();
		table.setDefaultRenderer(Object.class,gui);
		con.conectar();
		ArrayList<Movimiento> movimientos = con.getMovimientos();
		con.cerrarConexion();
		
		modelo.addColumn("Fecha");
		modelo.addColumn("Detalle");
		modelo.addColumn("Ingreso");
		modelo.addColumn("Salida");
		modelo.addColumn("Medio de pago");
		modelo.addColumn("Saldo");
		
		
		double saldo = 0;
		
		for(int i = 0 ; i< movimientos.size();i++) {
			Movimiento m = movimientos.get(i);
			saldo=saldo + m.getMonto();
			actualizarSaldo(saldo);
			if(m.getMonto()>0) {
				modelo.addRow(new Object[] {m.getFecha(),m.getDetalle(),"$"+m.getMonto(),"",m.getMedio_de_pago(),"$"+String.valueOf(saldo)});
			}else {
				modelo.addRow(new Object[] {m.getFecha(),m.getDetalle(),"","$"+m.getMonto(),m.getMedio_de_pago(),"$"+String.valueOf(saldo)});
			}
		}
		actualizarSaldo(saldo);
		table.setModel(modelo);
		table.setDefaultRenderer(Object.class,gui);
	}
	
	public void actualizarSaldo(double saldo) {
		this.saldo_final.setText(String.valueOf(saldo));
	}
	
	public void actualizarTotalFiado(double total) {
		this.totalFiados.setText(String.valueOf(total));
	}

	public void eliminarMovimiento() {
		con.conectar();
		con.borrarMovimiento(table.getValueAt(table.getSelectedRow(), 0).toString());
		con.cerrarConexion();
		cargarMovimientos();
		actualizarResumenGeneral();
	}

	public void actualizarTablaDeudas(String busqueda) {
		modeloFiados = new DefaultTableModel();
		
		con.conectar();
		ArrayList<Cliente> clientes = con.getClientes(busqueda);
		con.cerrarConexion();
		
		modeloFiados.addColumn("Cliente");
		modeloFiados.addColumn("Deuda");
		
		
		double totalDeuda = 0;
		
		for(int i = 0 ; i< clientes.size();i++) {
			Cliente c = clientes.get(i);
			totalDeuda=totalDeuda + c.getDeuda();
			actualizarTotalFiado(totalDeuda);
			modeloFiados.addRow(new Object[] {c.getNombre(),"$"+String.valueOf(c.getDeuda())});
			
		}
		
		actualizarTotalFiado(totalDeuda);
		table_2.setModel(modeloFiados);
	}
}
