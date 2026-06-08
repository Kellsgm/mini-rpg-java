package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaSaves extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSaves frame = new TelaSaves();
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
	public TelaSaves() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(225, 208, 155));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		JPanel painelCentro = new JPanel();
		painelCentro.setBackground(new Color(225, 208, 155));
		painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));

		JLabel lblTitulo = new JLabel("Jogos Salvos");
		lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton btnSlot1 = new JButton("Slot 1 - Vazio");
		JButton btnSlot2 = new JButton("Slot 2 - Vazio");
		JButton btnSlot3 = new JButton("Slot 3 - Vazio");

		btnSlot1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSlot2.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSlot3.setAlignmentX(Component.CENTER_ALIGNMENT);

		painelCentro.add(lblTitulo);
		painelCentro.add(btnSlot1);
		painelCentro.add(btnSlot2);
		painelCentro.add(btnSlot3);

		contentPane.add(painelCentro, BorderLayout.CENTER);

		JPanel painelRodape = new JPanel();
		painelRodape.setBackground(new Color(225, 208, 155));
		painelRodape.setLayout(new FlowLayout(FlowLayout.LEFT));

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal telaPrincipal = new TelaPrincipal();
				telaPrincipal.setVisible(true);
				dispose();
			}
		});

		painelRodape.add(btnVoltar);

		contentPane.add(painelRodape, BorderLayout.SOUTH);

	}

}
