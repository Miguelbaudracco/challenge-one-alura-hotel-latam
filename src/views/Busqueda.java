package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jdbc.controller.HuespedController;
import jdbc.controller.ReservaController;
import jdbc.modelo.Huespedes;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import java.awt.Toolkit;

public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modeloHuesped;
	private DefaultTableModel modeloReserva;
	private HuespedController huespedController;
	private ReservaController reservaController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(647, 85, 158, 31);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		
		JLabel lblNewLabel_4 = new JLabel("Sistema de Búsqueda");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_4.setBounds(155, 42, 258, 42);
		contentPane.add(lblNewLabel_4);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(815, 416, 54, 41);
		contentPane.add(btnSalir);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(10, 127, 874, 265);
		contentPane.add(panel);
		
		tbHuespedes = new JTable();
		tbHuespedes.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), tbHuespedes, null);
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("");
		modeloHuesped.addColumn("");
		modeloHuesped.addColumn("");
		modeloHuesped.addColumn("");
		modeloHuesped.addColumn("");
		modeloHuesped.addColumn("");
		modeloHuesped.addColumn("");

		tbReservas = new JTable();
		tbReservas.setFillsViewportHeight(true);
		tbReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), tbReservas, null);
		modeloReserva = (DefaultTableModel) tbReservas.getModel();
		modeloReserva.addColumn("");
		modeloReserva.addColumn("");
		modeloReserva.addColumn("");
		modeloReserva.addColumn("");
		modeloReserva.addColumn("");
	
		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpiar();
				panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), tbHuespedes, null);
				panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), tbReservas, null);
			}
		});
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpiar();

				panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), tbHuespedes, null);				
				panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), tbReservas, null);

				ListarHuespedes();
			}
		});
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa2.png")));
		btnBuscar.setBounds(815, 75, 54, 41);
		contentPane.add(btnBuscar);
		
		JButton btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
				panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), tbHuespedes, null);				
				panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), tbReservas, null);
			}
		});
		btnEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/editar-texto.png")));
		btnEditar.setBackground(SystemColor.menu);
		btnEditar.setBounds(587, 416, 54, 41);
		contentPane.add(btnEditar);

		btnEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/deletar.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
				panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), tbHuespedes, null);				
				panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), tbReservas, null);
			}
		});
		btnEliminar.setBackground(SystemColor.menu);
		btnEliminar.setBounds(651, 416, 54, 41);
		contentPane.add(btnEliminar);
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(713, 416, 54, 41);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(25, 10, 104, 107);
		contentPane.add(lblNewLabel_2);
		setResizable(false);

	}
	
    private boolean tieneFilaElegida() {
        return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
    }

    private void modificar() {
        if (tieneFilaElegida()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }

        Optional.ofNullable(tbHuespedes.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                    String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
                    String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
                    Date fechanacimiento = (Date) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3);
                    String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
                    String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
                    Integer idreserva = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
                    
                    var filasModificadas = this.huespedController.modificar(id, nombre, apellido, fechanacimiento, nacionalidad, telefono, idreserva);
                    
                    JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void eliminar() {
        if (tieneFilaElegida()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }

        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                    Integer idreserva = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());

                    var filasModHuesped = this.huespedController.eliminar(id);
                    var filasModReserva = this.reservaController.eliminar(idreserva);

                    modeloHuesped.removeRow(tbHuespedes.getSelectedRow());

                    JOptionPane.showMessageDialog(this,
                            String.format("%d %d item eliminado con éxito!", filasModHuesped,filasModReserva));
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    
	private void ListarHuespedes() {
		modeloReserva.getDataVector().clear();
		modeloHuesped.getDataVector().clear();
		huespedController = new HuespedController();
		String huespedBuscado = txtBuscar.getText();
		int idBuscado;
		var <List> campos = huespedController.listarHuesped(huespedBuscado);
		idBuscado = campos.get(0).getIdreserva(); 
		campos.forEach(huesped -> {

			     modeloHuesped.addRow(
			            		new Object[] {
			            		huesped.getId(),
			                    huesped.getNombre(),
			                    huesped.getApellido(),
			                    huesped.getFechanacimiento(),
			                    huesped.getNacionalidad(),
			                    huesped.getTelefono(),
			                    huesped.getIdreserva()
			            		});
		         });
        reservaController = new ReservaController();
	    var reservas = reservaController.listarReserva(idBuscado);

	    reservas.forEach(reserva -> {
	            modeloReserva.addRow(new Object[] {
	                    reserva.getId(),
	                    reserva.getFechaE(),
	                    reserva.getFechaS(),
	                    reserva.getValor(),
	                    reserva.getFormaPago(),
	            		});
	         	});
	    
	}

	public void Limpiar() {
		modeloReserva.getDataVector().clear();
		modeloHuesped.getDataVector().clear();
    }
}
