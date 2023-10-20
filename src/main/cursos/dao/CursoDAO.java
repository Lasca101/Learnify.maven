package dao;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import model.Curso;

public class CursoDAO {
	private Connection conexao;
	
	public CursoDAO() {
		conexao = null;
		conectar();
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "paulopuc.postgres.database.azure.com";
		String mydatabase = "postgres";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "paulo";
		String password = "Ph09351234";
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
	
	
	public Curso[] getCursos() {
		Curso[] curso = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM cursos");		
	         if(rs.next()){
	             rs.last();
	             curso = new Curso[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                curso[i] = new Curso(rs.getInt("id_curso"), rs.getFloat("preco"), 
	                		                  rs.getInt("id_usuario"), rs.getString("categoria"),
												rs.getString("nome"), rs.getString("descricao"),
												rs.getBytes("imagem"), rs.getBytes("banner"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return curso;
	}
	
	public boolean inserirCurso(Curso curso) {
		boolean status = false;
		try {  
			String query = "INSERT INTO cursos (id_curso, preco, id_usuario, categoria, nome, descricao, imagem, banner) "
				       + "VALUES ("+curso.getId_curso()+ ", '" + curso.getPreco() + "', '"  
				       + curso.getId_usuario() + "', '" + curso.getCategoria() + "', '"
					   + curso.getNome() + "', '" + curso.getDescricao() + "', ?, ?);";
			
			PreparedStatement st = conexao.prepareStatement(query);
			st.setBytes(1, curso.getImagem());
			st.setBytes(2, curso.getBanner());
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirCurso(int id_curso) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM cursos WHERE id_curso = " + id_curso);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarCurso(Curso curso, int id_cursoAlvo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE clientes SET id_curso = '" + curso.getId_curso() + "', preco = '"  
				       + curso.getPreco() + "', id_usuario = '" + curso.getId_usuario() + "', categoria = '"
				       + curso.getCategoria() + "', nome = '" + curso.getNome() + "', descricao = '"
				       + curso.getDescricao() + "', imagem = '" + curso.getImagem() + "', banner = '"
				       + curso.getBanner() + "'" + " WHERE id_curso = " + id_cursoAlvo;
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
}