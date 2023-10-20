package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import model.Aula;

public class AulaDAO {
    private Connection conexao;

    public AulaDAO() {
        conexao = null;
        conectar();
    }

    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        String serverName = "paulopuc.postgres.database.azure.com";
        String mydatabase = "postgres";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
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

    public Aula[] getAulas() {
        Aula[] aulas = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM aulas");
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
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return aulas;
    }

    public boolean inserirAula(Aula aula) {
        boolean status = false;
        try {
            String query = "INSERT INTO aulas (id_aula, id_curso, titulo, thumbnail) "
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
