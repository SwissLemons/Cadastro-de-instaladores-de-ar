package View;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import DAO.HistoricoDAO;
import Model.Historicos;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;

public class Historico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableHistorico;
	private JTextField textFieldId;
	private JTextField textFieldPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Historico frame = new Historico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Historico() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		////
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 8, 704, 340);
		contentPane.add(scrollPane);
		
		tableHistorico = new JTable(new DefaultTableModel(new Object[] {"ID", "Marca", "Modelo","Preço", "Qtd", "Data"},0));
		tableHistorico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		tableHistorico.getModel();
		
		scrollPane.setViewportView(tableHistorico);
		
		carregarHistoricoNaTabela();
		
		////		
		
		JPanel painelPesquiasas = new JPanel();
		painelPesquiasas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		painelPesquiasas.setBounds(10, 397, 704, 43);
		contentPane.add(painelPesquiasas);
		painelPesquiasas.setLayout(null);
		
		////
		
		JLabel lblPesquisa = new JLabel("Pesquisa por nome:");
		lblPesquisa.setBounds(267, 11, 145, 22);
		painelPesquiasas.add(lblPesquisa);
		
		////
		
		JComboBox<String> comboBoxPesquisas = new JComboBox<String>();
		comboBoxPesquisas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxPesquisas.getSelectedIndex() == 1) {
					lblPesquisa.setText("Pesquisa por Marca: ");
				} else {
					lblPesquisa.setText("Pesquisa por Modelo: ");
				}
				
			}
		});
		comboBoxPesquisas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxPesquisas.setBounds(108, 11, 149, 22);
		comboBoxPesquisas.addItem("Marca");
		comboBoxPesquisas.addItem("Modelo");
		painelPesquiasas.add(comboBoxPesquisas);
		
		////
		
		JLabel lblTipoPesquisa = new JLabel("Pesquisar por:");
		lblTipoPesquisa.setBounds(10, 11, 88, 22);
		painelPesquiasas.add(lblTipoPesquisa);
		
		////
		
		textFieldPesquisa = new JTextField();
		textFieldPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
//				try {
//		            String termo = textFieldPesquisa.getText().trim();
//		            if (comboBoxPesquisas.getSelectedIndex() == 1) {
//		            	//////////////////////////////////////////////////////
//						pesquisarInstaladorPorEmpresa(termo);
//						lblPesquisa.setText("Pesquisa por empresa: ");
//					} else {
//						//////////////////////////////////////////////////////
//		            	pesquisarInstaladorPorNome(termo);
//						lblPesquisa.setText("Pesquisa por nome: ");
//					}
//		        } catch (ClassNotFoundException | SQLException ex) {
//		            ex.printStackTrace();
//		        }
			}
		});
		textFieldPesquisa.setBounds(408, 11, 104, 22);
		painelPesquiasas.add(textFieldPesquisa);
		textFieldPesquisa.setColumns(10);
		
		////
		
		JButton btnEditar = new JButton("Editar Item");
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setBounds(586, 11, 107, 22);
		painelPesquiasas.add(btnEditar);
		
		////
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(522, 11, 22, 21);
		painelPesquiasas.add(lblNewLabel);
		
		////
		
		textFieldId = new JTextField();
		textFieldId.setToolTipText("O ID é automatico");
		textFieldId.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldId.setEditable(false);
		textFieldId.setColumns(10);
		textFieldId.setBounds(546, 11, 30, 22);
		painelPesquiasas.add(textFieldId);

	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void carregarHistoricoNaTabela() throws ClassNotFoundException, SQLException {
		HistoricoDAO historicoDAO = new HistoricoDAO();
		ArrayList<Historicos> historicoInstalador = historicoDAO.listarHistoricos();
		DefaultTableModel model = (DefaultTableModel) tableHistorico.getModel();
	    model.setRowCount(0);  // Limpa a tabela antes de adicionar novos dados
        	    	
    	for (Historicos historico : historicoInstalador) {
    		Object[] row = {
    				historico.getId(),
    				historico.getMarca(),
    				historico.getModelo(),
    				historico.getPreco(),
    				historico.getQuantidade(),
    				historico.getData(),
    				
    		};
    		model.addRow(row);
    	}
    	
    	// tamanhos das colunas personalizado
    	tableHistorico.getColumnModel().getColumn(0).setPreferredWidth(30); // Coluna "ID" menor
    	tableHistorico.getColumnModel().getColumn(1).setPreferredWidth(150); // Coluna "Marca" maior
    	tableHistorico.getColumnModel().getColumn(2).setPreferredWidth(150); // Coluna "Modelo" maior
    	tableHistorico.getColumnModel().getColumn(3).setPreferredWidth(90); // Coluna "Preco"
    	tableHistorico.getColumnModel().getColumn(4).setPreferredWidth(90); // Coluna "Quantidade"
    	tableHistorico.getColumnModel().getColumn(5).setPreferredWidth(90); // Coluna "Data"

    	//centralização das colunas
    	// Cria um renderizador de célula para centralizar o texto
    	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
    	centralizado.setHorizontalAlignment(SwingConstants.CENTER);

    	// Aplica o renderizador a cada coluna desejada
    	tableHistorico.getColumnModel().getColumn(0).setCellRenderer(centralizado); // Coluna "ID"
    	tableHistorico.getColumnModel().getColumn(3).setCellRenderer(centralizado); // Coluna "Preco"
    	tableHistorico.getColumnModel().getColumn(4).setCellRenderer(centralizado); // Coluna "Quantidade"
    	tableHistorico.getColumnModel().getColumn(5).setCellRenderer(centralizado); // Coluna "Data"
	}
//	
//	private void pesquisarInstaladorPorNome(String nome) throws ClassNotFoundException, SQLException {
//	    InstaladorDAO instaladorDAO = new InstaladorDAO();
//	    ArrayList<instaladores> instaladoresFiltrados = instaladorDAO.buscarInstaladoresPorNome(nome);
//	    DefaultTableModel model = (DefaultTableModel) tableHistorico.getModel();
//	    model.setRowCount(0);  // Limpa a tabela antes de adicionar novos dados
//
//	    for (instaladores instalador : instaladoresFiltrados) {
//	        Object[] row = {
//	            instalador.getId(),
//	            instalador.getNome(),
//	            instalador.getEmpresa(),
//	            instalador.getTelefone()
//	        };
//	        model.addRow(row);
//	    }
//	}
//	
//	private void pesquisarInstaladorPorEmpresa(String empresa) throws ClassNotFoundException, SQLException {
//	    InstaladorDAO instaladorDAO = new InstaladorDAO();
//	    ArrayList<instaladores> instaladoresFiltrados = instaladorDAO.buscarInstaladoresPorEmpresa(empresa);
//	    DefaultTableModel model = (DefaultTableModel) tableHistorico.getModel();
//	    model.setRowCount(0);  // Limpa a tabela antes de adicionar novos dados
//
//	    for (instaladores instalador : instaladoresFiltrados) {
//	        Object[] row = {
//	            instalador.getId(),
//	            instalador.getNome(),
//	            instalador.getEmpresa(),
//	            instalador.getTelefone()
//	        };
//	        model.addRow(row);
//	    }
//	}
}
