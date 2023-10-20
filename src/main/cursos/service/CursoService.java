package service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import dao.CursoDAO;
import model.Curso;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

public class CursoService {

	private static CursoDAO cursoDAO = new CursoDAO();

	public CursoService() {
		makeForm();
	}

	public static ModelAndView mostrarCursos(Request req, Response res) {
//		 insertTeste(req, res);
		HashMap<String, String> model = new HashMap<>();
		model.put("htmlCursos", makeForm());

		return new ModelAndView(model, "/view/home.vm");
	}

	public static String makeForm() {
		// Aqui, você pode criar a lógica para obter os clientes do banco de dados
		// e preencher a tabela no HTML com os dados dos clientes.
		Curso[] cursos = cursoDAO.getCurso();
		String htmlCursos = "";

		for (Curso curso : cursos) {
			byte[] imagemBytes = curso.getImagem();
			String imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);

			htmlCursos += "<div class=\"col-md-6 curso\" data-title=\"" + curso.getNome() + "\" data-category=\"" + curso.getCategoria() + "\">\n" +
			"    <a href=\"/pagina-curso/pagina_curso.html?id=" + curso.getId_curso() + "\" class=\"ancora-cursos\"><img src=\"data:image/jpeg;base64," + imagemBase64 + "\" alt=\"curso\">\n" +
			"    <h3>" + curso.getNome() + "</h3></a>\n" +
			"    <p class=\"categoria-curso\">Categoria: " + curso.getCategoria() + "</p>\n" +
			"</div>";
		}

		return htmlCursos;
	}

	// public Object atualizaClientes(Request request, Response response) {
	// makeForm(); // Atualiza a tabela de clientes
	// return new VelocityTemplateEngine().render(new ModelAndView(null,
	// "templates/clientes.vm"));
	// }
	//
	// public Object insert(Request request, Response response) {
	// Curso[] clientes = cursoDAO.getCliente();
	// int id = clientes.length;
	// String nome = request.queryParams("nome");
	// int idade = Integer.parseInt(request.queryParams("idade"));
	// String carro = request.queryParams("carro");

	// Curso cliente = new Curso(id, nome, idade, carro);
	// if(cursoDAO.inserirCliente(cliente) == true) {
	// response.status(201); // 201 Created
	// } else {
	// response.status(404); // 404 Not found
	// }

	// response.redirect("/");
	// return mostrarClientes(request, response);
	// }

	// public Object edit(Request request, Response response) {
	// int id = Integer.parseInt(request.params(":id"));
	// String nome = request.queryParams("editNome");
	// int idade = Integer.parseInt(request.queryParams("editIdade"));
	// String carro = request.queryParams("editCarro");

	// Curso cliente = new Curso(id, nome, idade, carro);
	// if(cursoDAO.atualizarCliente(cliente, id) == true) {
	// response.status(201); // 201 Created
	// } else {
	// response.status(404); // 404 Not found
	// }

	// response.redirect("/");
	// return mostrarClientes(request, response);
	// }

	// public Object delete(Request request, Response response) {
	// int id = Integer.parseInt(request.params(":id"));

	// if(cursoDAO.excluirCliente(id) == true) {
	// response.status(201); // 201 Created
	// } else {
	// response.status(404); // 404 Not found
	// }

	// response.redirect("/");
	// return mostrarClientes(request, response);
	// }

//	public static void insertTeste(Request request, Response response) {
//		File imagemx = new File("/home/tiago/Documentos/img_curso.jpg");
//
//		try {
//			FileInputStream inputStream = new FileInputStream(imagemx);
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//            // Lê os bytes da imagem e escreve no ByteArrayOutputStream
//            int bytesRead;
//            byte[] buffer = new byte[1024];
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//
//            // Obtém o array de bytes da imagem
//            byte[] imagemBytes = outputStream.toByteArray();
//
//            // Agora, imagemBytes contém os bytes da imagem no formato BLOB
//            
//            // Fecha os streams
//            inputStream.close();
//            outputStream.close();
//
//			// Agora, a variável imagemBytes contém os bytes da imagem
//
//			int id_curso = 0;
//			double preco = 120.99;
//			int id_usuario = 0;
//			String categoria = "categoria-teste";
//			String nome = "nome-teste";
//			String descricao = "descricao-teste";
//			byte[] imagem = imagemBytes;
//			byte[] banner = imagemBytes;
//
//			Curso curso = new Curso(id_curso, preco, id_usuario, categoria, nome, descricao, imagem, banner);
//			if (cursoDAO.inserirCurso(curso) == true) {
//				response.status(201); // 201 Created
//			} else {
//				response.status(404); // 404 Not found
//			}
//
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//
//	}
}