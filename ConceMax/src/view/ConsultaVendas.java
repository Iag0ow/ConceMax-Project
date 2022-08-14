package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.*;
import banco.ConexaoBanco;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ConsultaVendas extends JFrame {

	private JPanel contentPane;
	private JTextField txtDtinicial;
	private JTextField txtDtfinal;
	private JTable table;
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
					ConsultaVendas frame = new ConsultaVendas();
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
	private void MostrarDados() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Data");
		model.addColumn("Veiculo");
		model.addColumn("Quantidade");
		model.addColumn("Venda");
		try {
			String sql = "SELECT DATE_FORMAT(DATE(V.DTMOV),\"%d/%m/%Y\") DATA, CONCAT(V.CODCAR, \" - \",C.DESCRICAO) CARRO, COUNT(V.IDMOV) QT_VENDA, SUM(V.VLVENDA) VENDA FROM DEV_VENDAS V, DEV_CARROS C WHERE V.CODCAR = C.CODCAR AND DATE_FORMAT(DATE(DTMOV),\"%d/%m/%Y\") BETWEEN ? AND ? GROUP BY DATE_FORMAT(DATE(DTMOV),\"%d/%m/%Y\"), CONCAT(V.CODCAR, \" - \",C.DESCRICAO)";
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtDtinicial.getText());
			pst.setString(2, txtDtfinal.getText());
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] {
						rs.getString("DATA"),
						rs.getString("CARRO"),
						rs.getString("QT_VENDA"),
						rs.getString("VENDA")
				});
			} rs.close();
			  pst.close();
			  
			  table.setModel(model);
			  table.setAutoResizeMode(0);
			 
			  table.getColumnModel().getColumn(0).setPreferredWidth(140);
			  table.getColumnModel().getColumn(1).setPreferredWidth(140);
			  table.getColumnModel().getColumn(0).setPreferredWidth(40);
			  table.getColumnModel().getColumn(0).setPreferredWidth(140);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
	 }
	}
	public ConsultaVendas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\src\\icons\\pngwing.com.png"));
		setTitle("Relat\u00F3rio de Vendas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 345);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		conexao = ConexaoBanco.conector();
		JLabel lblNewLabel = new JLabel("Relat\u00F3rio de Vendas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(176, 11, 138, 14);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 36, 471, 14);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Per\u00EDodo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 49, 52, 14);
		contentPane.add(lblNewLabel_1);
		
		txtDtinicial = new JTextField();
		txtDtinicial.setBounds(60, 47, 86, 20);
		contentPane.add(txtDtinicial);
		txtDtinicial.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u00E0");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(156, 49, 17, 14);
		contentPane.add(lblNewLabel_2);
		
		txtDtfinal = new JTextField();
		txtDtfinal.setBounds(173, 47, 86, 20);
		contentPane.add(txtDtfinal);
		txtDtfinal.setColumns(10);
		
		JButton btnPesquisar = new JButton("P\u0332esquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarDados();
			}
		});
		btnPesquisar.setHorizontalAlignment(SwingConstants.LEADING);
		btnPesquisar.setIcon(new ImageIcon("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\src\\icons\\simbolo-de-interface-de-lupa-de-pesquisa (1).png"));
		btnPesquisar.setBounds(269, 46, 113, 23);
		contentPane.add(btnPesquisar);
		
		JLabel lblNewLabel_3 = new JLabel("Vendas Realizadas");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 87, 124, 14);
		contentPane.add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 112, 471, 148);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(UIManager.getBorder("FormattedTextField.border"));
		
		JButton btnNewButton = new JButton("V\u0332oltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SegundaTela frame = new SegundaTela();			
				dispose();
				frame.setVisible(true);
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Levi\\eclipse-workspace\\InterfaceGrafica.4.0Vers\u00E3o\\src\\icons\\de-volta.png"));
		btnNewButton.setBounds(384, 265, 97, 30);
		contentPane.add(btnNewButton);
	}
}
