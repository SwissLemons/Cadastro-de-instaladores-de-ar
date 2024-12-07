package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Historicos;
import Model.instaladores;

public class HistoricoDAO {
	public ArrayList<Historicos> listarHistoricos() throws SQLException, ClassNotFoundException {
    	ConexaoMysql conexao = new ConexaoMysql();
        conexao.conectar();
        Connection conn = conexao.getIdConexao();
        
//        instaladores instaladores = new instaladores();
        
        String sql = "SELECT * FROM Historico";
        PreparedStatement comando = conn.prepareStatement(sql);
//        comando.setInt(1, );
        
        ResultSet resultado = comando.executeQuery(sql);

        ArrayList<Historicos> listarHistorico = new ArrayList<>();
        while (resultado.next()) {
        	Historicos historico = new Historicos();
        	historico.setId(resultado.getInt(1));
        	historico.setMarca(resultado.getString(2));
        	historico.setModelo(resultado.getString(3));
        	historico.setPreco(resultado.getFloat(4));
        	historico.setQuantidade(resultado.getInt(5));
        	historico.setData(resultado.getString(6));
            listarHistorico.add(historico);
        }
	    resultado.close();
	    comando.close();
	    conexao.desConectar();
        
        return listarHistorico;
    }	
}
