package model;

import java.awt.image.BufferedImage;

public class Curso {
	private int id_curso;
    private float preco;
    private int id_usuario;
    private String categoria;
    private String nome;
    private String descricao;
    private BufferedImage imagem;
    private BufferedImage banner;
    
    public Curso(int id_curso, float preco, int id_usuario, String categoria, String nome, String descricao, BufferedImage imagem, BufferedImage banner) {
		this.id_curso = id_curso;
		this.preco = preco;
		this.id_usuario = id_usuario;
		this.categoria = categoria;
		this.nome = nome;
		this.descricao = descricao;
		this.imagem = imagem;
		this.banner = banner;
	}
    
    public Curso() {
    }
    
	public int getId_curso() {
		return id_curso;
	}
	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BufferedImage getImagem() {
		return imagem;
	}
	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}
	public BufferedImage getBanner() {
		return banner;
	}
	public void setBanner(BufferedImage banner) {
		this.banner = banner;
	}
}