package view;
import java.sql.*;
import banco.ConexaoBanco;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import view.SegundaTela;
import javax.swing.JSeparator;
import java.awt.Toolkit;

public class CadastroVeiculos extends JFrame {

	private JPanel contentPane;
	private JTextField txtMarca;
	private JTextField txtCor;
	private JTextField txtAno;
	private JTextField txtDescricao;
	private JTextField txtPreco;
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	/**
	 * Inicio da aplicação.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroVeiculos frame = new CadastroVeiculos();
					frame.setVisible(true);
					frame.setTitle("Cadastro");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//método cadastrar veiculo
	public void Cadastrar () {
		String sql = "INSERT INTO DEV_CARROS (DESCRICAO, MARCA, ANO, COR, PRECO) VALUES(?, ?, ?, ?, ?);";
		try {
			// as linhas abaixo preparam a consulta ao banco de dados
			//em função do que foi digitado nas caixas de texto. o ? é substituido pelo conteudo das variaveis txtUsuario e txtSenha
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtDescricao.getText());
			pst.setString(2, txtMarca.getText());
			pst.setString(3, txtAno.getText());
			pst.setString(4, txtCor.getText());
			pst.setLong(5, Integer.valueOf(txtPreco.getText()));
			
			int teste = pst.executeUpdate();
			if(teste > 0) {
				JOptionPane.showMessageDialog(null,"Cadastrado com Sucesso!");
				txtDescricao.setText(null);
				txtMarca.setText(null);
				txtAno.setText(null);
				txtCor.setText(null);
				txtPreco.setText(null);
			} else {
				JOptionPane.showMessageDialog(null,"Dados incorretos","Erro",JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Construtor
	public CadastroVeiculos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Iago\\git\\ConceMax-Project\\ConceMax\\src\\icons\\pngwing.com.png"));
		conexao = ConexaoBanco.conector();
		
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 329);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Cor:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("Ano:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_3 = new JLabel("Marca:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtMarca.setColumns(10);
		
		txtCor = new JTextField();
		txtCor.setColumns(10);
		
		txtAno = new JTextField();
		txtAno.setColumns(10);
		
		JButton btnGravar = new JButton("G\u0332ravar Dados");
		btnGravar.setIcon(new ImageIcon("C:\\Users\\Iago\\git\\ConceMax-Project\\ConceMax\\src\\icons\\verifica.png"));
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastrar();
				SegundaTela frame = new SegundaTela();			
				dispose();
				frame.setVisible(true);
				
			}
		});
		btnGravar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNewLabel = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Pre\u00E7o:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Preencha os campos e clique em Gravar Dados ");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnVoltar = new JButton("V\u0332oltar");
		btnVoltar.setIcon(new ImageIcon("C:\\Users\\Iago\\git\\ConceMax-Project\\ConceMax\\src\\icons\\de-volta.png"));
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SegundaTela frame = new SegundaTela();			
				dispose();
				frame.setVisible(true);
			}
		});
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		
		JSeparator separator_2 = new JSeparator();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(68, Short.MAX_VALUE)
					.addComponent(lblNewLabel_5)
					.addGap(59))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnGravar)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_3)
										.addComponent(lblNewLabel_2)
										.addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel_4))
									.addGap(29)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(txtPreco, Alignment.LEADING)
											.addComponent(txtCor, Alignment.LEADING))
										.addComponent(txtAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGap(91)
							.addComponent(btnVoltar)
							.addGap(30))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addContainerGap(188, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_5)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_3))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(txtCor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addComponent(txtPreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGravar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
