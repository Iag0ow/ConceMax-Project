package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import banco.ConexaoBanco;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JRadioButton;

public class ConsultaVeiculos extends JFrame {

	private JComboBox comboBox;
	private JPanel contentPane;
	private JTextField txtDescricao;
	private JTextField txtMarca;
	private JTextField txtAno;
	private JTextField txtCor;
	private JTextField txtPreco;
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
					ConsultaVeiculos frame = new ConsultaVeiculos();
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
	public void Editar() {
		String sql = "UPDATE DEV_CARROS C SET C.DESCRICAO = ?, C.MARCA = ?, C.ANO = ?, COR = ?, C.PRECO = ? WHERE DESCRICAO = ?";
	 try {
		pst = conexao.prepareStatement(sql);
		pst.setString(6, (String)comboBox.getSelectedItem());
		pst.setString(1, txtDescricao.getText());
		pst.setString(2, txtMarca.getText());
		pst.setString(3, txtAno.getText());
		pst.setString(4, txtCor.getText());
		pst.setLong(5, Integer.valueOf(txtPreco.getText()));
		pst.executeUpdate();
		JOptionPane.showMessageDialog(null,"Atualizado com Sucesso!");
	 
	 	} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
	  }
	 
	}
	
	public void Excluir() {
	int confirma=JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o veiculo?");
	if (confirma == JOptionPane.YES_OPTION) {
		try {
			String sql = "DELETE FROM dev_carros where descricao=?";
			pst = conexao.prepareStatement(sql);
			pst.setString(1, (String)comboBox.getSelectedItem());
			int apagado = pst.executeUpdate();
			if(apagado > 0) {
				JOptionPane.showMessageDialog(null, "Carro excluido com Sucesso!");
			}
		}			
		catch(Exception e3) {
			
		}
	 }
	}
	
	
	public void PreencheComboBox() {
		String sql = "SELECT * FROM dev_carros";
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				comboBox.addItem(rs.getString("DESCRICAO"));
								
				
			}
		} catch (Exception e) {
		}
	}
	
	public ConsultaVeiculos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\bin\\icons\\pngwing.com.png"));
		conexao = ConexaoBanco.conector();
		setTitle("Consultar Veiculos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSalvar = new JButton("S\u0332alvar");
		btnSalvar.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalvar.setIcon(new ImageIcon("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\src\\icons\\verifica.png"));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar();
				ConsultaVeiculos frame = new ConsultaVeiculos();			
				dispose();
				frame.setVisible(true);
			}
		});
		btnSalvar.setBounds(10, 227, 105, 23);
		contentPane.add(btnSalvar);
		
		JButton btnExcluir = new JButton("E\u0332xcluir");
		btnExcluir.setHorizontalAlignment(SwingConstants.LEFT);
		btnExcluir.setIcon(new ImageIcon("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\src\\icons\\excluir_1.png"));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Excluir();
				ConsultaVeiculos frame = new ConsultaVeiculos();			
				dispose();
				frame.setVisible(true);
			}
		});
		btnExcluir.setBounds(145, 227, 105, 23);
		contentPane.add(btnExcluir);
		
		JButton btnVoltar = new JButton("V\u0332oltar");
		btnVoltar.setHorizontalAlignment(SwingConstants.LEFT);
		btnVoltar.setIcon(new ImageIcon("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\src\\icons\\de-volta.png"));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SegundaTela frame = new SegundaTela();			
				dispose();
				frame.setVisible(true);
			}
		});
		btnVoltar.setBounds(279, 227, 102, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel = new JLabel("Selecione o veiculo que deseja consultar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 273, 14);
		contentPane.add(lblNewLabel);
		
		
		comboBox = new JComboBox();
		PreencheComboBox();
		comboBox.setBounds(10, 28, 144, 23);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "SELECT * FROM dev_carros where descricao=? ";
				try {
					pst = conexao.prepareStatement(sql);
					pst.setString(1, (String)comboBox.getSelectedItem());
					rs = pst.executeQuery();
					while(rs.next()) {
						
						txtDescricao.setText(rs.getString(2));
						txtPreco.setText(rs.getString(6));
						txtMarca.setText(rs.getString(3));
						txtCor.setText(rs.getString(5));
						txtAno.setText(rs.getString(4));
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 62, 376, 14);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 76, 60, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Marca:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 101, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(68, 74, 153, 20);
		contentPane.add(txtDescricao);
		txtDescricao.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ano:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 126, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Cor:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 151, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Pre\u00E7o:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 176, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(68, 99, 105, 20);
		contentPane.add(txtMarca);
		txtMarca.setColumns(10);
		
		txtAno = new JTextField();
		txtAno.setBounds(68, 124, 71, 20);
		contentPane.add(txtAno);
		txtAno.setColumns(10);
		
		txtCor = new JTextField();
		txtCor.setBounds(68, 149, 60, 20);
		contentPane.add(txtCor);
		txtCor.setColumns(10);
		
		txtPreco = new JTextField();
		txtPreco.setBounds(68, 174, 86, 20);
		contentPane.add(txtPreco);
		txtPreco.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 216, 376, 15);
		contentPane.add(separator_1);
	}
}
