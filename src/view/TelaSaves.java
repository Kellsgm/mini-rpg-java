package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PersonagemDAO;
import model.Personagem;

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
		setSize(866, 643);
		setLocationRelativeTo(null);
		
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

		JButton btnSlot1 = new JButton(" Vazio");
		JButton btnSlot2 = new JButton(" Vazio");
		JButton btnSlot3 = new JButton(" Vazio");

		btnSlot1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSlot2.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSlot3.setAlignmentX(Component.CENTER_ALIGNMENT);

		painelCentro.add(lblTitulo);
		painelCentro.add(btnSlot1);
		painelCentro.add(btnSlot2);
		painelCentro.add(btnSlot3);

		contentPane.add(painelCentro, BorderLayout.CENTER);
		PersonagemDAO dao = new PersonagemDAO();
		ArrayList<Personagem> personagens = dao.listarPersonagens();

		if (personagens.size() > 0) {
			Personagem p = personagens.get(0);
			btnSlot1.setText(p.getNome() + " • " + p.getClasse());

			btnSlot1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TelaInicial telaInicial = new TelaInicial(p);
					telaInicial.setVisible(true);
					dispose();
				}

			});
		}
		if (personagens.size() > 1) {
			Personagem p = personagens.get(1);
			btnSlot2.setText(p.getNome() + " • " + p.getClasse());
			btnSlot2.setText(p.getNome() + " • " + p.getClasse());

			btnSlot2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TelaInicial telaInicial = new TelaInicial(p);
					telaInicial.setVisible(true);
					dispose();
				}

			});
		}

		if (personagens.size() > 2) {
			Personagem p = personagens.get(2);
			btnSlot3.setText(p.getNome() + " • " + p.getClasse());
			btnSlot3.setText(p.getNome() + " • " + p.getClasse());

			btnSlot3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TelaInicial telaInicial = new TelaInicial(p);
					telaInicial.setVisible(true);
					dispose();
				}

			});
		}

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
