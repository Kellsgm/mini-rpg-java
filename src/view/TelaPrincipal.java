package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setTitle("Washing Machine");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(866, 643);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        confirmarSaida();
		    }
		});

		contentPane = new JPanel();
		contentPane.setBackground(new Color(21, 51, 47));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout());

		setContentPane(contentPane);

		JPanel painelCentro = new JPanel();

		contentPane.add(painelCentro);

		painelCentro.setOpaque(false);
		painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));

		ImageIcon icon = new ImageIcon(TelaPrincipal.class.getResource("/imagens/maquinas.png"));

		Image img = icon.getImage();

		Image imgRedimensionada = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

		JLabel lblImagem = new JLabel();
		lblImagem.setIcon(new ImageIcon(imgRedimensionada));
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);

		lblImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelCentro.add(lblImagem);

		JLabel lblWM = new JLabel("Washing Machine");

		lblWM.setBackground(new Color(255, 255, 255));
		lblWM.setForeground(new Color(255, 255, 255));
		lblWM.setFont(new Font("MV Boli", Font.BOLD | Font.ITALIC, 17));
		lblWM.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblWM.setHorizontalAlignment(SwingConstants.CENTER);
		lblWM.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

		lblWM.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelCentro.add(lblWM);

		JButton btnJogar = new JButton("Novo Jogo");
		btnJogar.setForeground(new Color(255, 255, 255));
		btnJogar.setFont(new Font("Verdana", Font.ITALIC, 12));
		btnJogar.setBorderPainted(false);
		btnJogar.setContentAreaFilled(false);
		btnJogar.setFocusPainted(false);
		btnJogar.setOpaque(false);
		btnJogar.setForeground(Color.WHITE);

		btnJogar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnJogar.setForeground(Color.YELLOW);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnJogar.setForeground(Color.WHITE);
			}
		});
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEscolhaClasse telaEscolhaClasse = new TelaEscolhaClasse();
				telaEscolhaClasse.setVisible(true);
				dispose();

			}
		});



		btnJogar.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelCentro.add(btnJogar);

		JButton btnJogoSalvo = new JButton("Continuação");
		btnJogoSalvo.setForeground(new Color(255, 255, 255));
		btnJogoSalvo.setFont(new Font("Verdana", Font.ITALIC, 12));
		btnJogoSalvo.setBorderPainted(false);
		btnJogoSalvo.setContentAreaFilled(false);
		btnJogoSalvo.setFocusPainted(false);
		btnJogoSalvo.setForeground(Color.WHITE);

		btnJogoSalvo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnJogoSalvo.setForeground(Color.YELLOW);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnJogoSalvo.setForeground(Color.WHITE);
			}
		});

		btnJogoSalvo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSaves telaSaves = new TelaSaves();
				telaSaves.setVisible(true);
				dispose();
				;
			}
		});

		btnJogoSalvo.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelCentro.add(btnJogoSalvo);

		JButton btnSair = new JButton("Sair");
		btnSair.setForeground(new Color(255, 255, 255));
		btnSair.setFont(new Font("Verdana", Font.ITALIC, 12));
		btnSair.setBorderPainted(false);
		btnSair.setContentAreaFilled(false);
		btnSair.setFocusPainted(false);
		btnSair.setForeground(Color.WHITE);
		
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSair.setForeground(Color.YELLOW);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSair.setForeground(Color.WHITE);
			}
		});

		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				confirmarSaida();
			}
		});
		btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelCentro.add(btnSair);

	}
	private void confirmarSaida() {
	    int resposta = JOptionPane.showConfirmDialog(
	            this,
	            "Tem certeza que deseja sair?\nTodo progresso não salvo será perdido.",
	            "Confirmar saída",
	            JOptionPane.YES_NO_OPTION
	    );

	    if (resposta == JOptionPane.YES_OPTION) {
	        dispose();
	    }
	}

}
