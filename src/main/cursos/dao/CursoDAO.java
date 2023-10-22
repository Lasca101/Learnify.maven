package dao;

// IMPORTS
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import model.Curso;
import dao.DAO;
// END_IMPORTS
public class CursoDAO {	
	private DAO conexao;

	public Curso[] getCursos() {
		Curso[] curso = null;
		conexao = new DAO();
		try {
			ResultSet rs = conexao.executeQuery("SELECT * FROM cursos");	
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
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		conexao.close();
		return curso;
	}

	public boolean inserirCurso(Curso curso) {
		boolean status = false;
		conexao = new DAO();
		try {  
			String query = "INSERT INTO cursos (id_curso, preco, id_usuario, categoria, nome, descricao, imagem, banner) "
				       + "VALUES ("+curso.getId_curso()+ ", '" + curso.getPreco() + "', '"  
				       + curso.getId_usuario() + "', '" + curso.getCategoria() + "', '"
					   + curso.getNome() + "', '" + curso.getDescricao() + "', ?, ?);";
			PreparedStatement st = conexao.prepareStatement(query);
			st.setBytes(1, curso.getImagem());
			st.setBytes(2, curso.getBanner());
			conexao.executeUpdate(st);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		conexao.close();
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