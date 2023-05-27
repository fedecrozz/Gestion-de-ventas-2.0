import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class Deuda extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel cliente;
	private JLabel deuda;
	private Cliente Cliente;
	private ArrayList<DeudaDet> deudas;
	private JScrollPane scrollPane;
	private DefaultTableModel modelo=  new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deuda frame = new Deuda(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param actionListener 
	 */
	public Deuda(Cliente c, ArrayList<DeudaDet> d) {
		
		this.Cliente = c;
		this.deudas= d;
		setResizable(false);
		setTitle("Deuda por cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 656, 484);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#219ebc"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 630, 43);
		contentPane.add(panel);
		panel.setLayout(null);
		
		cliente = new JLabel("Cliente:");
		cliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		cliente.setBounds(10, 9, 254, 25);
		panel.add(cliente);
		
		deuda = new JLabel("Deuda:");
		deuda.setFont(new Font("Tahoma", Font.BOLD, 13));
		deuda.setBounds(366, 9, 254, 25);
		panel.add(deuda);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 409, 630, 35);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
		btnAceptar.setBounds(427, 6, 193, 23);
		panel_2.add(btnAceptar);
	
		int variable = 5;
		
		JButton btnNewButton = new JButton("Imprimir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirTabla();
			}
		});
		btnNewButton.setBounds(91, 6, 89, 23);
		panel_2.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 630, 333);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Fecha", "Monto", "Total"
			}
		));
		scrollPane.setViewportView(table);
		
		iniciarCliente();
		iniciarDeudas();
	}
	
	public void iniciarCliente() {
		cliente.setText("Cliente: "+Cliente.getNombre());
		deuda.setText("Deuda: "+Cliente.getDeuda());
	}

	public void iniciarDeudas() {
		modelo = new DefaultTableModel();
		
		modelo.addColumn("Cliente");
		modelo.addColumn("Fecha");
		modelo.addColumn("Monto");
		modelo.addColumn("Total");
		
		
		
		double totalDeuda = 0;
		String importe = "";
		for(int i = 0 ; i< deudas.size();i++) {
			
			DeudaDet d = deudas.get(i);
			totalDeuda=totalDeuda + d.getMonto();
			
			if(d.getMonto()<0) {
				importe = "Restó de su deuda $"+String.valueOf(d.getMonto());
			}else {
				importe = "Agregó a su deuda $"+String.valueOf(d.getMonto());
			}
			
			if(deudas.get(i).getMonto() == 0) {
				importe = "Sin deuda";
			}
			modelo.addRow(new Object[] {d.getCliente(),d.getFecha(), importe,"$"+String.valueOf(totalDeuda)});
			
		}
		
		table.setModel(modelo);
	}

	public void cerrar() {
		this.dispose();
	}

	public void imprimirTabla() {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		
		printerJob.setPrintable(table.getPrintable(
                JTable.PrintMode.NORMAL, null, null));
		
		
		 if (printerJob.printDialog()) {
	            try {
	                printerJob.print();
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	}

	public boolean isSecondCharacterDash(String input) {
        if (input.length() >= 2 && input.charAt(1) == '-') {
            return true;
        } else {
            return false;
        }
    }
	
	
	public void imprimirPanel() {
		// Obtener el PrinterJob predeterminado
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        // Asignar el Printable al PrinterJob
        printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                // Imprimir el contenido del JPanel
                contentPane.printAll(graphics);

                return PAGE_EXISTS;
            }
        });

        // Mostrar el diálogo de impresión
        if (printerJob.printDialog()) {
            try {
                // Iniciar la impresión
                printerJob.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
	}
}
