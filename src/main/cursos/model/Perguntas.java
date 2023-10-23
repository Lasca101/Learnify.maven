package model;

public class Perguntas {
    private int id_aula;
    private int id_pergunta;
    private String descricao;

    public Perguntas(int id_aula, int id_pergunta, String descricao) {
        this.id_aula = id_aula;
        this.id_pergunta = id_pergunta;
        this.descricao = descricao;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    public int getId_pergunta() {
        return id_pergunta;
    }

    public void setId_pergunta(int id_pergunta) {
        this.id_pergunta = id_pergunta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
