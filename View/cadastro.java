package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import DAO.HistoricoDAO;
import DAO.InstaladorDAO;
import Model.Historicos;
import Model.instaladores;
import Utils.Validador;
import java.awt.Cursor;

public class cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldEmpresa;
	private JTextField textFieldTelefone;
	private JTable tableInstaladores;
	public JTextField textFieldId;
	private JTextField textFieldPesquisa;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cadastro frame = new cadastro();
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
	public cadastro() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cadastro de instaladores");
		setBounds(100, 100, 740, 490);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		////
		
		JLabel lblTitulo = new JLabel("Digite os dados nessesarios do instalador");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 8, 257, 14);
		contentPane.add(lblTitulo);
		
		////
		
		JLabel lblNomeInstalador = new JLabel("Nome:");
		lblNomeInstalador.setBounds(10, 38, 74, 14);
		contentPane.add(lblNomeInstalador);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(94, 35, 173, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setDocument(new Validador(40));
		textFieldNome.setColumns(10);
		
		////
		
		JLabel lblEmpresaInstalador = new JLabel("Empresa:");
		lblEmpresaInstalador.setBounds(10, 69, 74, 14);
		contentPane.add(lblEmpresaInstalador);
		
		textFieldEmpresa = new JTextField();
		textFieldEmpresa.setColumns(10);
		textFieldEmpresa.setBounds(94, 66, 173, 20);
		textFieldEmpresa.setDocument(new Validador(40));
		contentPane.add(textFieldEmpresa);
		
		////
		
		JLabel lblTelefoneInstalador = new JLabel("Telefone:");
		lblTelefoneInstalador.setBounds(10, 100, 74, 14);
		contentPane.add(lblTelefoneInstalador);
		
		textFieldTelefone = new JTextField(); /// Esse text field apenas aceita numeros
		textFieldTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if(!caracteres .contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		textFieldTelefone.setToolTipText("Telefone fixo ou Celular");
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(94, 97, 109, 20);
		textFieldTelefone.setDocument(new Validador(11));
		contentPane.add(textFieldTelefone);
		
		////

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(213, 100, 22, 14);
		contentPane.add(lblNewLabel);
		
		textFieldId = new JTextField();
		textFieldId.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		textFieldId.setToolTipText("O ID é automatico");
		textFieldId.setEditable(false);
		textFieldId.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldId.setBounds(237, 97, 30, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		
		////
		
		JLabel lblStatusCadastro = new JLabel("");
		lblStatusCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusCadastro.setBounds(10, 158, 257, 20);
		contentPane.add(lblStatusCadastro);
		
		////
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(277, 8, 437, 378);
		contentPane.add(scrollPane);
		
		tableInstaladores = new JTable(new DefaultTableModel(new Object[] {"ID", "Nome", "Empresa","Telefone"},0));
		tableInstaladores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		tableInstaladores.getModel();
		
		carregarInstaladoresNaTabela();
		
		scrollPane.setViewportView(tableInstaladores);
		
		////
		
		JButton btnSalvarInstalador = new JButton("Salvar Instalador");
		btnSalvarInstalador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvarInstalador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instaladores instaladores = new instaladores();
				
				if (instaladores.setNome(textFieldNome.getText()) == true) {
					textFieldNome.setForeground(new Color(0, 204, 0));
				} else {
					textFieldNome.setForeground(new Color(204, 0, 0));
					System.out.println("erro no nome.");
				}
				
				if (instaladores.setEmpresa(textFieldEmpresa.getText()) == true) {
					textFieldEmpresa.setForeground(new Color(0, 204, 0));
				} else {
					textFieldEmpresa.setForeground(new Color(204, 0, 0));
					System.out.println("erro na empresa.");
				}
				
				if (instaladores.setTelefone(textFieldTelefone.getText()) == true) {
					textFieldTelefone.setForeground(new Color(0, 204, 0));
				} else {
					textFieldTelefone.setForeground(new Color(204, 0, 0));
					System.out.println("erro no telefone.");
				}

				if(instaladores.setNome(textFieldNome.getText()) == true && instaladores.setEmpresa(textFieldEmpresa.getText()) == true && instaladores.setTelefone(textFieldTelefone.getText()) == true) {
					try {
						InstaladorDAO instaladorDAO = new InstaladorDAO();
		                boolean sucesso = instaladorDAO.inserirInstalador(instaladores);	
		                
						if(sucesso) {
							// sucesso antes da conexao bd
							lblStatusCadastro.setForeground(new Color(0,204,0));
							lblStatusCadastro.setText("Instalador cadastrado com sucesso.");

							carregarInstaladoresNaTabela();
						} else {
							// sucesso mas erro na hora de cadastrar no bd
		                    lblStatusCadastro.setForeground(new Color(204, 0, 0));
		                    lblStatusCadastro.setText("Erro ao cadastrar instalador no banco.");
		                }
						
					}catch (ClassNotFoundException | SQLException ex) {
						// erro ao conectar com bd
		                ex.printStackTrace();
		                lblStatusCadastro.setForeground(new Color(204, 0, 0));
		                lblStatusCadastro.setText("Erro ao conectar ao banco.");
					}
				} else {
					// erro antes da conexao bd
					lblStatusCadastro.setForeground(new Color(204,0,0));
					lblStatusCadastro.setText("Falha ao cadastrar o instalador.");
				}
			}
		});
		btnSalvarInstalador.setBounds(10, 128, 257, 23);
		contentPane.add(btnSalvarInstalador);
		
		////
		
		JButton btnPegarInstalador = new JButton("Pegar Dados do Instalador");
		btnPegarInstalador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPegarInstalador.setToolTipText("Pega os dados do instalador selecionado na tabela");
		btnPegarInstalador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableInstaladores.getSelectedRow();
		        if (selectedRow != -1) {
		            // Obtém os valores das colunas da linha selecionada
		        	String id = tableInstaladores.getValueAt(selectedRow, 0).toString();
		        	String nome = tableInstaladores.getValueAt(selectedRow, 1).toString();
		            String empresa = tableInstaladores.getValueAt(selectedRow, 2).toString();
		            String telefone = tableInstaladores.getValueAt(selectedRow, 3).toString();

		            // Preenche os JTextFields com os valores obtidos
		            textFieldId.setText(id);
		            textFieldNome.setText(nome);
		            textFieldEmpresa.setText(empresa);
		            textFieldTelefone.setText(telefone);
		            
		            lblStatusCadastro.setForeground(new Color(0,204,0));
					lblStatusCadastro.setText("Dados extraidos com sucesso da tabela.");
		        } else {
		        	lblStatusCadastro.setForeground(new Color(204,0,0));
					lblStatusCadastro.setText("Falha ao extrair os dados do instalador.");
		        }
			}
		});
		btnPegarInstalador.setBounds(10, 189, 257, 23);
		contentPane.add(btnPegarInstalador);
		
		////
		
		JButton btnLimparInstalador = new JButton("Limpar Dados do Instalador");
		btnLimparInstalador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimparInstalador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnLimparInstalador.setToolTipText("Limpa os dados do instalador selecionado na tabela");
		btnLimparInstalador.setBounds(10, 223, 257, 23);
		contentPane.add(btnLimparInstalador);
		
		////
		
		JButton btnAlterarInstalador = new JButton("Alterar Instalador");
		btnAlterarInstalador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterarInstalador.setToolTipText("É preciso selecionar um instalador antes de altera-lo");
		btnAlterarInstalador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {				
					//verificar se uma linha foi selecionada:
					instaladores instalador = new instaladores();

					if(instalador.setNome(textFieldNome.getText()) == true && instalador.setEmpresa(textFieldEmpresa.getText()) == true && instalador.setTelefone(textFieldTelefone.getText()) == true) {
						InstaladorDAO instaladorDAO = new InstaladorDAO();

		                // Pega e atualisa os valores
		                instalador.setNome(textFieldNome.getText());
						instalador.setEmpresa(textFieldEmpresa.getText());
						instalador.setTelefone(textFieldTelefone.getText());
						instalador.setId(Integer.parseInt(textFieldId.getText()));
		                
		                // Atualiza o banco de dados
		                instaladorDAO.alterarInstalador(instalador);
		                carregarInstaladoresNaTabela();
		                
		                lblStatusCadastro.setForeground(new Color(0,204,0));
						lblStatusCadastro.setText("Instalador alterado com sucesso.");
						reset();
					} else {
						lblStatusCadastro.setForeground(new Color(204,0,0));
						lblStatusCadastro.setText("Falha ao alterar instalador.");
			        }
				
					carregarInstaladoresNaTabela();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAlterarInstalador.setBounds(10, 274, 257, 23);
		contentPane.add(btnAlterarInstalador);

		////
		
		JButton btnDeletarInstalador = new JButton("Deletar Instalador");
		btnDeletarInstalador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeletarInstalador.setToolTipText("É preciso selecionar um instalador antes de deleta-lo");
		btnDeletarInstalador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//excluir uma linha da tabela:
					instaladores instalador = new instaladores();

					if(instalador.setNome(textFieldNome.getText()) == true && instalador.setEmpresa(textFieldEmpresa.getText()) == true && instalador.setTelefone(textFieldTelefone.getText()) == true) {
						InstaladorDAO instaladorDAO = new InstaladorDAO();

						instalador.setNome(textFieldNome.getText());
						instalador.setEmpresa(textFieldEmpresa.getText());
						instalador.setTelefone(textFieldTelefone.getText());
						try {
							instaladorDAO.excluirInstalador(instalador);
							
							lblStatusCadastro.setForeground(new Color(0,204,0));
							lblStatusCadastro.setText("Instalador deletado com sucesso.");
							reset();
							
							carregarInstaladoresNaTabela();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						lblStatusCadastro.setForeground(new Color(204,0,0));
						lblStatusCadastro.setText("Falha ao deletar o instalador.");
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		btnDeletarInstalador.setBounds(10, 308, 257, 23);
		contentPane.add(btnDeletarInstalador);
		
		////
		
		JPanel painelPesquiasas = new JPanel();
		painelPesquiasas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		painelPesquiasas.setBounds(10, 397, 703, 43);
		contentPane.add(painelPesquiasas);
		painelPesquiasas.setLayout(null);
		
		////
		
		JLabel lblPesquisa = new JLabel("Pesquisa por nome:");
		lblPesquisa.setBounds(267, 15, 145, 14);
		painelPesquiasas.add(lblPesquisa);
		
		////
		
		JComboBox<String> comboBoxPesquisas = new JComboBox<String>();
		comboBoxPesquisas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxPesquisas.getSelectedIndex() == 1) {
					lblPesquisa.setText("Pesquisa por empresa: ");
				} else {
					lblPesquisa.setText("Pesquisa por nome: ");
				}
				
			}
		});
		comboBoxPesquisas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxPesquisas.setBounds(108, 11, 149, 22);
		comboBoxPesquisas.addItem("Nome");
		comboBoxPesquisas.addItem("Empresa");
		painelPesquiasas.add(comboBoxPesquisas);
		
		////
		
		JLabel lblTipoPesquisa = new JLabel("Pesquisar por:");
		lblTipoPesquisa.setBounds(10, 15, 88, 14);
		painelPesquiasas.add(lblTipoPesquisa);
		
		////
		
		textFieldPesquisa = new JTextField();
		textFieldPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
		            String termo = textFieldPesquisa.getText().trim();
		            if (comboBoxPesquisas.getSelectedIndex() == 1) {
						pesquisarInstaladorPorEmpresa(termo);
						lblPesquisa.setText("Pesquisa por empresa: ");
					} else {
		            	pesquisarInstaladorPorNome(termo);
						lblPesquisa.setText("Pesquisa por nome: ");
					}
		        } catch (ClassNotFoundException | SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		textFieldPesquisa.setBounds(407, 12, 286, 20);
		painelPesquiasas.add(textFieldPesquisa);
		textFieldPesquisa.setColumns(10);
		
		////
		
		JButton btnMostrarHistorico = new JButton("Mostrar todo Historico de Compras");
		btnMostrarHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            Historico view;
				try {
					view = new Historico();
					view.setVisible(true);
					//dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMostrarHistorico.setToolTipText("Abre a aba de historico de vendas");
		btnMostrarHistorico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMostrarHistorico.setBounds(10, 363, 257, 23);
        contentPane.add(btnMostrarHistorico);
        
        ////
    }
	
	private void carregarInstaladoresNaTabela() throws ClassNotFoundException, SQLException {
		InstaladorDAO instaladorDAO = new InstaladorDAO();
		ArrayList<instaladores> instaladoresCadastrados = instaladorDAO.listarInstaladores();
		DefaultTableModel model = (DefaultTableModel) tableInstaladores.getModel();
	    model.setRowCount(0);  // Limpa a tabela antes de adicionar novos dados
        	    	
    	for (instaladores instalador : instaladoresCadastrados) {
    		Object[] row = {
    				instalador.getId(),
    				instalador.getNome(),
    				instalador.getEmpresa(),
    				instalador.getTelefone()
    		};
    		model.addRow(row);
    	}
    	
    	// tamanhos das colunas personalizado
    	tableInstaladores.getColumnModel().getColumn(0).setPreferredWidth(30); // Coluna "ID" menor
    	tableInstaladores.getColumnModel().getColumn(1).setPreferredWidth(150); // Coluna "Nome" maior
    	tableInstaladores.getColumnModel().getColumn(2).setPreferredWidth(150); // Coluna "Empresa" maior
    	tableInstaladores.getColumnModel().getColumn(3).setPreferredWidth(90); // Coluna "Telefone"

    	//centralização das colunas
    	// Cria um renderizador de célula para centralizar o texto
    	DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
    	centralizado.setHorizontalAlignment(SwingConstants.CENTER);

    	// Aplica o renderizador a cada coluna desejada
    	tableInstaladores.getColumnModel().getColumn(0).setCellRenderer(centralizado); // Coluna "ID"
    	tableInstaladores.getColumnModel().getColumn(3).setCellRenderer(centralizado); // Coluna "Telefone"
	}
	
	////
	
	private void pesquisarInstaladorPorNome(String nome) throws ClassNotFoundException, SQLException {
	    InstaladorDAO instaladorDAO = new InstaladorDAO();
	    ArrayList<instaladores> instaladoresFiltrados = instaladorDAO.buscarInstaladoresPorNome(nome);
	    DefaultTableModel model = (DefaultTableModel) tableInstaladores.getModel();
	    model.setRowCount(0);  // Limpa a tabela antes de adicionar novos dados

	    for (instaladores instalador : instaladoresFiltrados) {
	        Object[] row = {
	            instalador.getId(),
	            instalador.getNome(),
	            instalador.getEmpresa(),
	            instalador.getTelefone()
	        };
	        model.addRow(row);
	    }
	}
	
	////
	
	private void pesquisarInstaladorPorEmpresa(String empresa) throws ClassNotFoundException, SQLException {
	    InstaladorDAO instaladorDAO = new InstaladorDAO();
	    ArrayList<instaladores> instaladoresFiltrados = instaladorDAO.buscarInstaladoresPorEmpresa(empresa);
	    DefaultTableModel model = (DefaultTableModel) tableInstaladores.getModel();
	    model.setRowCount(0);  // Limpa a tabela antes de adicionar novos dados

	    for (instaladores instalador : instaladoresFiltrados) {
	        Object[] row = {
	            instalador.getId(),
	            instalador.getNome(),
	            instalador.getEmpresa(),
	            instalador.getTelefone()
	        };
	        model.addRow(row);
	    }
	}
	
	////
	
	private void reset() {
		textFieldId.setText(null);
		textFieldNome.setText(null);
		textFieldEmpresa.setText(null);
		textFieldTelefone.setText(null);
	}
}
