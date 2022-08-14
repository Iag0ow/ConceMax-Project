package view;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import banco.ConexaoBanco;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JProgressBar;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class Pdv extends JFrame {
	private JComboBox comboBox;
	private JPanel contentPane;
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	/**
	 * Inicio da aplicação
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pdv frame = new Pdv();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//metodo que vende o veiculo
	public void vender() {
		int confirma=JOptionPane.showConfirmDialog(null, "Tem certeza que deseja vender o veiculo?");
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				String sql = "INSERT INTO DEV_VENDAS (DTMOV, CODCAR, VLVENDA) VALUES (DATE(SYSDATE()),(SELECT CODCAR FROM DEV_CARROS WHERE DESCRICAO = ?),(SELECT PRECO FROM DEV_CARROS WHERE DESCRICAO = ?))";
				pst = conexao.prepareStatement(sql);
				pst.setString(1, (String)comboBox.getSelectedItem());
				pst.setString(2, (String)comboBox.getSelectedItem());
				int apagado = pst.executeUpdate();
				if(apagado > 0) {
					JOptionPane.showMessageDialog(null, "Carro vendido com Sucesso!");
					Pdv pdv = new Pdv();
					pdv.setVisible(true);
					dispose();
				}
			}			
			catch(Exception e3) {
				
			}
		}
	
	}
	
	//método que guarda as opções do banco de dados no comboBox
	public void consultar() {
	String sql = "SELECT * FROM dev_carros";
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				comboBox.addItem(rs.getString("descricao"));
								
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	/**
	 * Construtor
	 */
	public Pdv() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\src\\icons\\pngwing.com.png"));
		setTitle("PDV");
		setResizable(false);
		
		conexao = ConexaoBanco.conector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JLabel txtCor = new JLabel("-------");
		JLabel txtUnit = new JLabel("------");
		JLabel txtMarca = new JLabel("-----");
		JLabel txtAno = new JLabel("-----");
		JButton btnVoltar = new JButton("V\u0332oltar");
		btnVoltar.setIcon(new ImageIcon("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\src\\icons\\de-volta.png"));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SegundaTela frame = new SegundaTela();			
				dispose();
				frame.setVisible(true);
			}
		});
		
		JButton btnVender = new JButton("V\u0332ender");
		btnVender.setIcon(new ImageIcon("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\src\\icons\\etiqueta-de-venda_1.png"));
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vender();
		
			}
		});
		
		comboBox = new JComboBox();
		contentPane.add(comboBox);
		consultar();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "SELECT * FROM dev_carros where descricao=? ";
				try {
					pst = conexao.prepareStatement(sql);
					pst.setString(1, (String)comboBox.getSelectedItem());
					rs = pst.executeQuery();
					while(rs.next()) {
						
						txtUnit.setText(rs.getString(6));
						txtMarca.setText(rs.getString(3));
						txtCor.setText(rs.getString(5));
						txtAno.setText(rs.getString(4));
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("PRE\u00C7O:");
		
		JLabel lblNewLabel_2 = new JLabel("MARCA:");
		
		JLabel lblNewLabel_3 = new JLabel("COR:");
		
		JLabel lblNewLabel_4 = new JLabel("ANO:");
		
		JLabel lblNewLabel = new JLabel("Selecione o carro que deseja vender");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVender)
							.addGap(191)
							.addComponent(btnVoltar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addGap(18)
									.addComponent(txtMarca))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNewLabel_3)
											.addGap(18)
											.addComponent(txtCor))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNewLabel_1)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(txtUnit)))
									.addGap(115)
									.addComponent(lblNewLabel_4)
									.addGap(6)
									.addComponent(txtAno))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)))
					.addGap(9))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(170, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(9)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(txtMarca))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(txtCor))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtUnit)
						.addComponent(lblNewLabel_4)
						.addComponent(txtAno))
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVender)
						.addComponent(btnVoltar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
