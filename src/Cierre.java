import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Cierre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cierre frame = new Cierre();
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
	public Cierre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 864, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel PanelCreditos = new JPanel();
		PanelCreditos.setBounds(0, 0, 850, 473);
		contentPane.add(PanelCreditos);
		PanelCreditos.setLayout(null);
		
		JLabel Creditos = new JLabel("");
		Creditos.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		Creditos.setIcon(new ImageIcon(Cierre.class.getResource("/imagenes/Sin título ‑ Made with FlexClip (1).gif")));
		Creditos.setBounds(0, 0, 850, 473);
		PanelCreditos.add(Creditos);
	}

}