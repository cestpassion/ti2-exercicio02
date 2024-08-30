package dao;

import java.sql.*;
import model.Music;

public class MusicDAO {
	private Connection conexao;
	
	public MusicDAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "ti2exercicio";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2ex2";
		String password = "furt4d00";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirMusica(Music musica) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO musica (codigo, nome, artista, genero) "
					       + "VALUES ("+musica.getCodigo()+ ", '" + musica.getNome() + "', '"  
					       + musica.getArtista() + "', '" + musica.getGenero() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarMusica(Music musica) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE musica SET nome = '" + musica.getNome() + "', artista = '"  
				       + musica.getArtista() + "', genero = '" + musica.getGenero() + "'"
					   + " WHERE codigo = " + musica.getCodigo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirMusica(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM musica WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

    public Music[] getCodigo() {
        Music[] codigos = null;

        try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM musica");		
            if(rs.next()){
                rs.last();
                codigos = new Music[rs.getRow()];
                rs.beforeFirst();

                for(int i = 0; rs.next(); i++)
                    codigos[i] = new Music(rs.getInt("codigo"));
            }
            st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return codigos;
	}
	
	public Music[] getMusica() {
		Music[] musica = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM musica");		
	         if(rs.next()){
	             rs.last();
	             musica = new Music[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                musica[i] = new Music(rs.getInt("codigo"), rs.getString("nome"), 
	                		                  rs.getString("artista"), rs.getString("genero"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return musica;
	}

	
	public Music[] getMusicaMasculinos() {
		Music[] musica = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM musica WHERE musica.sexo LIKE 'M'");		
	         if(rs.next()){
	             rs.last();
	             musica = new Music[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                musica[i] = new Music(rs.getInt("codigo"), rs.getString("nome"), 
                         		                  rs.getString("artista"), rs.getString("genero"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return musica;
	}
}