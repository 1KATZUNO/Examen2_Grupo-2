import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;

public class PantallaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	

	public PantallaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel PanelMenu = new JPanel();
		PanelMenu.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		PanelMenu.setOpaque(false);
		PanelMenu.setBounds(29, 24, 60, 506);
		contentPane.add(PanelMenu);
		PanelMenu.setLayout(null);
		
		JButton BotonIngresar = new JButton("");
		BotonIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//AQUI ES PARA INGRESAR
				
				
			}
		});
		BotonIngresar.setToolTipText("Insertar");
		BotonIngresar.setFocusable(false);
		BotonIngresar.setBackground(new Color(0, 0, 0));
		BotonIngresar.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/iconos/icons8-crear-20.png")));
		BotonIngresar.setBounds(10, 10, 40, 40);
		PanelMenu.add(BotonIngresar);
		
		JButton BotonConsultar = new JButton("");
		BotonConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
			//AQUI ES PARA CONSULTAR
			
			
			}
		});
		BotonConsultar.setToolTipText("Consultar");
		BotonConsultar.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/iconos/icons8-buscar-20 (1).png")));
		BotonConsultar.setFocusable(false);
		BotonConsultar.setBackground(Color.BLACK);
		BotonConsultar.setBounds(10, 60, 40, 40);
		PanelMenu.add(BotonConsultar);
		
		JButton BotonActualizar = new JButton("");
		BotonActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//AQUI ES PARA ACTUALIZAR
				
				
				
			}
		});
		BotonActualizar.setToolTipText("Actualizar");
		BotonActualizar.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/iconos/icons8-actualizar-20.png")));
		BotonActualizar.setFocusable(false);
		BotonActualizar.setBackground(Color.BLACK);
		BotonActualizar.setBounds(10, 110, 40, 40);
		PanelMenu.add(BotonActualizar);
		
		JButton BotonEliminar = new JButton("");
		BotonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//AQUI ES PARA PODER ELIMINAR
				
				
			}
		});
		BotonEliminar.setToolTipText("Eliminar");
		BotonEliminar.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/iconos/icons8-eliminar-20 (1).png")));
		BotonEliminar.setFocusable(false);
		BotonEliminar.setBackground(Color.BLACK);
		BotonEliminar.setBounds(10, 160, 40, 40);
		PanelMenu.add(BotonEliminar);
		
		JButton BotonInstruccionnes = new JButton("");
		BotonInstruccionnes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				//ESTA SON COMO PARA DESPLEGAR UNAS INSTRUCCIONES DEL CODIGO
				
				
				
			}
		});
		BotonInstruccionnes.setToolTipText("Instrucciones");
		BotonInstruccionnes.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/iconos/icons8-acerca-de-20.png")));
		BotonInstruccionnes.setFocusable(false);
		BotonInstruccionnes.setBackground(Color.BLACK);
		BotonInstruccionnes.setBounds(10, 210, 40, 40);
		PanelMenu.add(BotonInstruccionnes);
		
		JButton BotonSalirAplicacion = new JButton("");
		BotonSalirAplicacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				//CERRAR LA APLICACION
				
PantallaPrincipal z = new PantallaPrincipal();
				
				z.setVisible(false);
				z.dispose();
				
				
				Cierre x = new Cierre();
				
				x.setVisible(true);
			   x.setLocationRelativeTo(null);
				Timer timer = new Timer(24000, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                System.exit(0); // Cerrar el programa
		            }
		        });
		        timer.setRepeats(false); // Ejecutar solo una vez
		        timer.start();
				
				
				
				
			}
		});
		BotonSalirAplicacion.setToolTipText("Cerrar Programa");
		BotonSalirAplicacion.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/iconos/icons8-eliminar-20 (2).png")));
		BotonSalirAplicacion.setFocusable(false);
		BotonSalirAplicacion.setBackground(Color.BLACK);
		BotonSalirAplicacion.setBounds(10, 456, 40, 40);
		PanelMenu.add(BotonSalirAplicacion);
		
		JButton BotonCerrarSesion = new JButton("");
		BotonCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//BOTON PARA CERRAR SESION
				
				
				
			}
		});
		BotonCerrarSesion.setToolTipText("Cerrar Sesion");
		BotonCerrarSesion.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/iconos/icons8-cerrar-sesión-20.png")));
		BotonCerrarSesion.setFocusable(false);
		BotonCerrarSesion.setBackground(Color.BLACK);
		BotonCerrarSesion.setBounds(10, 406, 40, 40);
		PanelMenu.add(BotonCerrarSesion);
		
		JPanel PanelPrincipal = new JPanel();
		PanelPrincipal.setBackground(new Color(0, 0, 0));
		PanelPrincipal.setBounds(0, 0, 886, 563);
		contentPane.add(PanelPrincipal);
		PanelPrincipal.setLayout(null);
		
		JLabel Fondo1 = new JLabel("");
		Fondo1.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/file (39).png")));
		Fondo1.setBackground(new Color(0, 0, 0));
		Fondo1.setBounds(0, 0, 886, 563);
		PanelPrincipal.add(Fondo1);
	
		
	
	
	}


}
