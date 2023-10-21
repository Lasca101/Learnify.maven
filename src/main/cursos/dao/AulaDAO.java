package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import model.Aula;
import dao.DAO;

public class AulaDAO {
    private DAO conexao;

	public AulaDAO() {
	}
    public Aula[] getAulas() {
        conexao = new DAO();
        Aula[] aulas = null;
        
        try {
            ResultSet rs = conexao.executeQuery("SELECT * FROM aulas");
            if (rs.next()) {
                rs.last();
                aulas = new Aula[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    aulas[i] = new Aula(rs.getInt("id_aula"), rs.getInt("id_curso"),
                            rs.getString("titulo"), rs.getBytes("thumbnail"), 
                            rs.getString("link"));
                }
            }
            conexao.closeStatement();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        conexao.close();
        return aulas;
    }
    // arrumar conexao
    public boolean inserirAula(Aula aula) {
        boolean status = false;
        try {
            String query = "INSERT INTO aulas (id_aula, id_curso, titulo, thumbnail, link) "
                    + "VALUES (" + aula.getIdAula() + ", '" + aula.getIdCurso() + "', '"
                    + aula.getTitulo() + "', ?, '" + aula.getLink() + "');";
			
			PreparedStatement st = conexao.prepareStatement(query);
			st.setBytes(1, aula.getThumbnail());
			st.executeUpdate();
			st.close();
			status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean excluirAula(int id_aula) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM aulas WHERE id_aula = " + id_aula);
            st.close();
            status = true;
        } catch (SQLException u) {
        	throw new RuntimeException(u);
        }
        return status;
    }

    public boolean atualizarAula(Aula aula, int id_aulaAlvo) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE aulas SET id_aula = '" + aula.getIdAula() + "', id_curso = '"
                    + aula.getIdCurso() + "', titulo = '" + aula.getTitulo() + "', thumbnail = '"
                    + aula.getThumbnail() + "', link = '" + aula.getLink() + "' WHERE id_aula = " + id_aulaAlvo;
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}
