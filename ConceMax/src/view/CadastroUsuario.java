package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import banco.ConexaoBanco;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuario frame = new CadastroUsuario();
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
	
	public void Cadastrar() {
		String sql = "INSERT INTO DEV_USUARIOS(NOME, LOGIN, SENHA) VALUES (?, ?, ?);";
	 try {
		 pst = conexao.prepareStatement(sql);
		 pst.setString(1, txtNome.getText());
		 pst.setString(2, txtLogin.getText());
		 pst.setString(3, new String(txtSenha.getPassword()));
		 int teste = pst.executeUpdate();
		if (txtNome.getText().length() <= 0  || txtLogin.getText().length() <= 0 || new String(txtSenha.getPassword()).length() <= 0 ) {
			JOptionPane.showMessageDialog(null,"Preencha todos os campos","Erro",JOptionPane.ERROR_MESSAGE);
		}
		else  {
			JOptionPane.showMessageDialog(null,"Cadastrado com Sucesso!");
			txtNome.setText(null);
			txtLogin.setText(null);
			txtSenha.setText(null);
		} 
	 } catch(Exception e) {
		 JOptionPane.showMessageDialog(null, e);
	 }
	 }
	
	
	public CadastroUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\src\\icons\\pngwing.com.png"));
		conexao = ConexaoBanco.conector();
		setTitle("Cadastro de Usu\u00E1rios");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 345);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(36, 77, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(36, 134, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(36, 191, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtNome = new JTextField();
		txtNome.setBounds(36, 96, 173, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(36, 154, 114, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(36, 209, 82, 20);
		contentPane.add(txtSenha);
		
		JButton btnSalvar = new JButton("    S\u0332alvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastrar();
				SegundaTela frame = new SegundaTela();			
				dispose();
				frame.setVisible(true);
			}
		});
		btnSalvar.setVerticalAlignment(SwingConstants.TOP);
		btnSalvar.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalvar.setIcon(new ImageIcon("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\src\\icons\\salve- (1).png"));
		btnSalvar.setBounds(21, 262, 114, 33);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("   C\u0332ancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SegundaTela frame = new SegundaTela();			
				dispose();
				frame.setVisible(true);	
			}
		});
		btnCancelar.setIcon(new ImageIcon("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\src\\icons\\cancelar.png"));
		btnCancelar.setBounds(223, 262, 123, 33);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\src\\icons\\adicionar-usuario.png"));
		lblNewLabel_4.setBounds(21, -3, 54, 44);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Cadastro de Usu\u00E1rios");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(65, 11, 154, 14);
		contentPane.add(lblNewLabel_5);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 52, 378, 23);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 240, 378, 14);
		contentPane.add(separator_1);
	}
}
