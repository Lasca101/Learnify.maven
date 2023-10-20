package model;

public class Aula {
    private int idAula;
    private int idCurso;
    private String titulo;
    private String thumbnail;

    public Aula(int idAula, int idCurso, String titulo, String thumbnail) {
        this.idAula = idAula;
        this.idCurso = idCurso;
        this.titulo = titulo;
        this.thumbnail = thumbnail;
    }

    public Aula() {
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
