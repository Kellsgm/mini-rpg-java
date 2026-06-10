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
import model.Item;
import model.Personagem;
import model.PersonagemFactory;

public class TelaInventario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Personagem heroina;
	private JComboBox<Item> comboArmas;
	private JComboBox<Item> comboArmaduras;
	private JComboBox<Item> comboPocoes;
	private JLabel lblEquipado;
	private JLabel lblMoedas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personagem heroinaTeste = PersonagemFactory.criarArqueira("Bianca");
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
		setSize(866, 643);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 91, 98));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInventario = new JLabel("Inventario:");
		lblInventario.setBounds(149, 25, 275, 14);
		lblInventario.setFont(new Font("Arial Black", Font.BOLD, 14));
		contentPane.add(lblInventario);

		comboArmas = new JComboBox();
		comboArmas.setBounds(208, 69, 129, 22);
		contentPane.add(comboArmas);

		comboArmaduras = new JComboBox();
		comboArmaduras.setBounds(208, 116, 129, 22);
		contentPane.add(comboArmaduras);

		comboPocoes = new JComboBox();
		comboPocoes.setBounds(208, 165, 129, 22);
		contentPane.add(comboPocoes);

		JLabel lblArmas = new JLabel("Armas");
		lblArmas.setBounds(91, 72, 89, 14);
		lblArmas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblArmas);

		JLabel lblArmaduras = new JLabel("Armaduras");
		lblArmaduras.setBounds(91, 119, 94, 14);
		lblArmaduras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblArmaduras);

		JLabel lblPocoes = new JLabel("Poções");
		lblPocoes.setBounds(91, 168, 46, 14);
		lblPocoes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblPocoes);

		lblEquipado = new JLabel("Equipado: " + mostrarItemEquipado());
		lblEquipado.setBounds(28, 198, 350, 14);
		contentPane.add(lblEquipado);

		lblMoedas = new JLabel("Moedas: " + heroina.getMoedas());
		lblMoedas.setBounds(300, 198, 350, 14);
		contentPane.add(lblMoedas);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(51, 227, 89, 23);

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial telaInicial = new TelaInicial(heroina);
				telaInicial.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVoltar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(270, 227, 89, 23);
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
		contentPane.add(btnSalvar);

		JButton btnEqipar = new JButton("Equipar");
		btnEqipar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Item itemSelecionado = (Item) comboArmas.getSelectedItem();

				if (itemSelecionado == null) {
					JOptionPane.showMessageDialog(null, "Selecione uma arma primeiro!");
					return;
				}

				heroina.equiparItem(itemSelecionado);
				lblEquipado.setText("Equipado: " + heroina.getItemEquipado().getNome());

				JOptionPane.showMessageDialog(null, itemSelecionado.getNome() + " equipado!");
			}
		});

		btnEqipar.setBounds(162, 227, 89, 23);
		contentPane.add(btnEqipar);
		carregarInventario();

	}

	private void carregarInventario() {
		comboArmas.removeAllItems();
		comboArmaduras.removeAllItems();
		comboPocoes.removeAllItems();

		for (Item item : heroina.getInventario()) {
			if (item.getTipo().equalsIgnoreCase("arma")) {
				comboArmas.addItem(item);
			} else if (item.getTipo().equalsIgnoreCase("armadura")) {
				comboArmaduras.addItem(item);
			} else if (item.getTipo().equalsIgnoreCase("pocao")) {
				comboPocoes.addItem(item);
			}
		}
	}

	private String mostrarItemEquipado() {
		if (heroina.getItemEquipado() == null) {
			return "Nenhum";
		}

		return heroina.getItemEquipado().getNome();
	}
}
