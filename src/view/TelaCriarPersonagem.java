package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.PersonagemDAO;
import model.Personagem;
import model.PersonagemFactory;

public class TelaCriarPersonagem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String classeEscolhida;
	private JTextField txtNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCriarPersonagem frame = new TelaCriarPersonagem("Maga");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */

	public TelaCriarPersonagem(String classeEscolhida) {
		this.classeEscolhida = classeEscolhida;
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(866, 643);
		setLocationRelativeTo(null);
	
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 200, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		JPanel painelCentro = new JPanel();
		contentPane.add(painelCentro, BorderLayout.CENTER);
		painelCentro.setBackground(new Color(225, 208, 155));
		painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));

		JLabel lblTitulo = new JLabel("Criação de personagens: ");
		lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(new Color(225, 208, 155));
		painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JLabel lblClasse = new JLabel("Classe: " + classeEscolhida);
		lblClasse.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelCentro.add(lblClasse);
		
		
		txtNome = new JTextField();
		txtNome.setMaximumSize(new Dimension(300, 30));
		painelCentro.add(lblTitulo);
		painelCentro.add(txtNome);
		txtNome.setColumns(30);
		contentPane.add(painelBotoes, BorderLayout.SOUTH);
		
	
//		JButton btnVoltar = new JButton ("voltar");
//		btnVoltar.addActionListener(new ActionListener (){
//			public void actionPerformed(ActionEvent e) {
//				TelaEscolhaClasse telaEscolhaClasse = new TelaEscolhaClasse();
//				telaEscolhaClasse.setVisible(true);
//				dispose();
//			}
//		});
//		painelBotoes.add(btnVoltar);
	
		
		JButton btnCriarPersonagem = new JButton("Criar personagem");
		btnCriarPersonagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtNome.getText().trim();

				if (nome.isEmpty()) {
				    JOptionPane.showMessageDialog(null, "Digite um nome para o personagem!");
				    return;
				}

				Personagem personagem = PersonagemFactory.criarPorClasse(classeEscolhida, nome);

				PersonagemDAO dao = new PersonagemDAO();
				boolean sucesso = dao.salvar(personagem);

				if (sucesso) {
				    JOptionPane.showMessageDialog(null, "Personagem criado com sucesso!");
					TelaMapa telaMapa = new TelaMapa();
					telaMapa.setVisible(true);
				
					dispose();

				} else {
				    JOptionPane.showMessageDialog(null, "Erro ao criar personagem.");
				}}
		});
		painelBotoes.add(btnCriarPersonagem);
		contentPane.add(painelBotoes, BorderLayout.SOUTH);

	

}}
