package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Perguntas;

public class PerguntasDAO {
    private DAO conexao;

    public PerguntasDAO() {
    }

    public Perguntas[] getPerguntas() {
        conexao = new DAO();
        Perguntas[] perguntas = null;

        try {
            ResultSet rs = conexao.executeQuery("SELECT * FROM perguntas");
            if (rs.next()) {
                rs.last();
                perguntas = new Perguntas[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    perguntas[i] = new Perguntas(rs.getInt("id_aula"), rs.getInt("id_pergunta"),
                            rs.getString("descricao"));
                }
            }
            conexao.closeStatement();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        conexao.close();
        return perguntas;
    }

    public boolean inserirPergunta(Perguntas pergunta) {
        boolean status = false;
        try {
            String query = "INSERT INTO perguntas (id_aula, id_pergunta, descricao) "
                    + "VALUES (" + pergunta.getId_aula() + ", " + pergunta.getId_pergunta() + ", '"
                    + pergunta.getDescricao() + "');";

            PreparedStatement st = conexao.prepareStatement(query);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean excluirPergunta(int id_aula, int id_pergunta) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM perguntas WHERE id_aula = " + id_aula + " AND id_pergunta = " + id_pergunta);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean atualizarPergunta(Perguntas pergunta, int id_aula, int id_perguntaAlvo) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE perguntas SET id_aula = " + pergunta.getId_aula() + ", id_pergunta = " + pergunta.getId_pergunta() +
                    ", descricao = '" + pergunta.getDescricao() + "' WHERE id_aula = " + id_aula + " AND id_pergunta = " + id_perguntaAlvo;
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}
