package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import Model.instaladores;

public class InstaladorDAO {
	
	public InstaladorDAO() throws ClassNotFoundException, SQLException {
		ConexaoMysql conexao = new ConexaoMysql();
		conexao.conectar();
		conexao.getIdConexao();
    }

    public boolean inserirInstalador(instaladores instalador) throws ClassNotFoundException, SQLException {
    	ConexaoMysql conexao = new ConexaoMysql();
    	
    	String sql = "INSERT INTO Instaladores (nome, empresa, telefone) VALUES (?, ?, ?)";
        try{
            conexao.conectar();
            
            PreparedStatement stmt = conexao.getIdConexao().prepareStatement(sql);
            stmt.setString(1, instalador.getNome());
            stmt.setString(2, instalador.getEmpresa());
            stmt.setString(3, instalador.getTelefone());
            
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Instalador inserido com sucesso!");
            }            
            conexao.desConectar();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir instalador: " + e.getMessage());
            conexao.desConectar();
            return false;
        }
    }

    public ArrayList<instaladores> listarInstaladores() throws SQLException, ClassNotFoundException {
    	ConexaoMysql conexao = new ConexaoMysql();
        conexao.conectar();
        Connection conn = conexao.getIdConexao();
        
        String sql = "SELECT * FROM Instaladores";
        PreparedStatement comando = conn.prepareStatement(sql);
        ResultSet resultado = comando.executeQuery(sql);

        ArrayList<instaladores> listaInstaladores = new ArrayList<>();
        while (resultado.next()) {
            instaladores instalador = new instaladores();
            instalador.setId(resultado.getInt(1));
            instalador.setNome(resultado.getString(2));
            instalador.setEmpresa(resultado.getString(3));
            instalador.setTelefone(resultado.getString(4));
            listaInstaladores.add(instalador);
        }
	    resultado.close();
	    comando.close();
	    conexao.desConectar();
        
        return listaInstaladores;
    }
    
    public ArrayList<instaladores> buscarInstaladoresPorNome(String nome) throws SQLException, ClassNotFoundException {
    	ConexaoMysql conexao = new ConexaoMysql();
        conexao.conectar();
        Connection conn = conexao.getIdConexao();    	
    	
    	ArrayList<instaladores> listaInstaladores = new ArrayList<>();
        String sql = "SELECT * FROM instaladores WHERE nome LIKE ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                instaladores instalador = new instaladores();
                instalador.setId(rs.getInt("id"));
                instalador.setNome(rs.getString("nome"));
                instalador.setEmpresa(rs.getString("empresa"));
                instalador.setTelefone(rs.getString("telefone"));
                listaInstaladores.add(instalador);
            }
        }
        return listaInstaladores;
    }
    
    public ArrayList<instaladores> buscarInstaladoresPorEmpresa(String empresa) throws SQLException, ClassNotFoundException {
    	ConexaoMysql conexao = new ConexaoMysql();
        conexao.conectar();
        Connection conn = conexao.getIdConexao();    	
    	
    	ArrayList<instaladores> listaInstaladores = new ArrayList<>();
        String sql = "SELECT * FROM instaladores WHERE empresa LIKE ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + empresa + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                instaladores instalador = new instaladores();
                instalador.setId(rs.getInt("id"));
                instalador.setNome(rs.getString("nome"));
                instalador.setEmpresa(rs.getString("empresa"));
                instalador.setTelefone(rs.getString("telefone"));
                listaInstaladores.add(instalador);
            }
        }
        return listaInstaladores;
    }
    
    public boolean excluirInstalador(instaladores instalador) throws ClassNotFoundException, SQLException {
    	ConexaoMysql conexao = new ConexaoMysql();
        conexao.conectar();
        Connection conn = conexao.getIdConexao();
        
        String sql = "DELETE FROM Instaladores WHERE nome = ? AND empresa = ? AND telefone = ?";
        PreparedStatement comando = conn.prepareStatement(sql);
        
    	comando.setString(1, instalador.getNome());	
    	comando.setString(2, instalador.getEmpresa());	
    	comando.setString(3, instalador.getTelefone());	
    	comando.executeUpdate();    	
    	
    	comando.close();
    	conexao.desConectar();
    	return true;
    }
    
    public boolean alterarInstalador(instaladores instalador) throws ClassNotFoundException, SQLException {
    	ConexaoMysql conexao = new ConexaoMysql();
        conexao.conectar();
        Connection conn = conexao.getIdConexao();

		String sql = "UPDATE Instaladores SET nome=?, empresa=?, telefone=? WHERE id =?";
		PreparedStatement comando = conn.prepareStatement(sql);
		
		comando.setString(1, instalador.getNome());
		comando.setString(2, instalador.getEmpresa());
		comando.setString(3, instalador.getTelefone());
		comando.setInt(4, instalador.getId());
		comando.executeUpdate();

    	comando.close();
    	conexao.desConectar();
    	return true;
	}
}
