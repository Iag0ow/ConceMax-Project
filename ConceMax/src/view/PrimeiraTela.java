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
import java.awt.Toolkit;
public class PrimeiraTela extends JFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;	

private JPanel contentPane;
private JTextField txtUsuario;
private JPasswordField txtSenha;

	/**
	 * Inicio da aplicação.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrimeiraTela frame = new PrimeiraTela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// metodo logar no banco
	public void logar() {
		String sql = "SELECT * FROM dev_usuarios WHERE login =? and senha =?";
		try {
			// as linhas abaixo preparam a consulta ao banco de dados
			//em função do que foi digitado nas caixas de texto. o ? é substituido pelo conteudo das variaveis txtUsuario e txtSenha
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtUsuario.getText());
			pst.setString(2, new String(txtSenha.getPassword()));
			// a linha abaixo executa a query
			rs = pst.executeQuery();
			// se existir usuario e senha correspondente
			if(rs.next()) {
				JOptionPane.showMessageDialog(null,"Acesso permitido");
				SegundaTela frame = new SegundaTela();			
				dispose();
				frame.setVisible(true);
				conexao.close();
			} else {
				JOptionPane.showMessageDialog(null,"Dados incorretos","Erro",JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	//metodo construtor
	public PrimeiraTela() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Iago\\git\\ConceMax-Project\\ConceMax\\src\\icons\\pngwing.com.png"));
		conexao = ConexaoBanco.conector();
		
		
		setResizable(false);
		setTitle("Login");
		getContentPane().setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 301);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton logar = new JButton("Login");

		
		
		JLabel lblNewLabel = new JLabel("Senha:");
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		
		txtSenha = new JPasswordField();
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setIcon(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Iago\\git\\ConceMax-Project\\ConceMax\\src\\icons\\Imagem1 (5).png"));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(80)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtSenha)
						.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(136, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2)
					.addGap(120))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(154, Short.MAX_VALUE)
					.addComponent(logar, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(144))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addGap(17)
									.addComponent(lblNewLabel))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addComponent(logar)))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		logar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				logar();
				
			}
		});
		
		if (conexao != null) {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bancoUp.jpg")));
			/*lblStatus.setText("Conectado");*/
		}
		else {
			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bancoDown.jpg")));
		}
		
		contentPane.setLayout(gl_contentPane);
		
		
	}

}
