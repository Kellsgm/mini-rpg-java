package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PersonagemDAO;
import model.Personagem;

public class TelaInventario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Personagem heroina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personagem heroinaTeste = new Personagem("Arqueira", 35, 5, 6, 6, 5, 15);
					TelaInventario frame = new TelaInventario(heroinaTeste);
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
	public TelaInventario(Personagem heroina) {

	    this.heroina = heroina;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 91, 98));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInventario = new JLabel("Inventario:");
		lblInventario.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblInventario.setBounds(149, 25, 275, 14);
		contentPane.add(lblInventario);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(248, 69, 30, 22);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(248, 116, 30, 22);
		contentPane.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(248, 165, 30, 22);
		contentPane.add(comboBox_2);
		
		JLabel lblArmas = new JLabel("Armas");
		lblArmas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblArmas.setBounds(144, 72, 89, 14);
		contentPane.add(lblArmas);
		
		JLabel lblArmaduras = new JLabel("Armaduras");
		lblArmaduras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblArmaduras.setBounds(144, 120, 94, 14);
		contentPane.add(lblArmaduras);
		
		JLabel lblPocoes = new JLabel("Poções");
		lblPocoes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPocoes.setBounds(144, 169, 46, 14);
		contentPane.add(lblPocoes);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);
			
			}
		});
		btnVoltar.setBounds(101, 215, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			    if (heroina == null) {
			        JOptionPane.showMessageDialog(null, "Escolha uma personagem primeiro!");
			        return;
			    }

			    PersonagemDAO dao = new PersonagemDAO();
			    boolean sucesso;

			    if (heroina.getId() == 0) {
			        sucesso = dao.salvar(heroina);
			    } else {
			        sucesso = dao.atualizar(heroina);
			    }

			    if (sucesso) {
			        JOptionPane.showMessageDialog(null, "Personagem salvo com sucesso!");
			    } else {
			        JOptionPane.showMessageDialog(null, "Erro ao salvar personagem!");
			    }
			}
		});
		btnSalvar.setBounds(230, 215, 89, 23);
		contentPane.add(btnSalvar);

	}
}
