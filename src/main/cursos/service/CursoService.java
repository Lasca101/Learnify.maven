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
	}

	public static ModelAndView mostrarCursos(Request req, Response res) {
//		  insertTeste(req, res);
		HashMap<String, Object> model = new HashMap<>();
		Curso[] cursos = cursoDAO.getCursos();
		

		try {
			model.put("cursos", cursos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView(model, "/view/home.vm");
	}

	// public Object insertCurso(Request request, Response response) {
	// 	File imagemx = new File("/home/tiago/Documentos/img_curso.jpg");                          

	// 	try {
	// 		FileInputStream inputStream = new FileInputStream(imagemx);
	// 		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	// 		// Lê os bytes da imagem e escreve no ByteArrayOutputStream
	// 		int bytesRead;
	// 		byte[] buffer = new byte[1024];
	// 		while ((bytesRead = inputStream.read(buffer)) != -1) {
	// 			outputStream.write(buffer, 0, bytesRead);
	// 		}

	// 		// Obtém o array de bytes da imagem
	// 		byte[] imagemBytes = outputStream.toByteArray();

	// 		// Agora, imagemBytes contém os bytes da imagem no formato BLOB
			
	// 		// Fecha os streams
	// 		inputStream.close();
	// 		outputStream.close();


	// 		Curso[] cursos = cursoDAO.getCursos();
	// 		int id_usuario = ;
	// 		int id_curso = cursos.length;
	// 		double preco = Double.parseDouble(request.queryParams("preco"));
	// 		String categoria = request.queryParams("categoria");
	// 		String nome = request.queryParams("nome");
	// 		String descricao = request.queryParams("descricao");
	// 		String imagem = request.queryParams("imagem");
	// 		String banner = request.queryParams("banner");


	// 		Curso[] clientes = cursoDAO.getCliente();
	// 		int id = clientes.length;
	// 		String nome = request.queryParams("nome");
	// 		int idade = Integer.parseInt(request.queryParams("idade"));
	// 		String carro = request.queryParams("carro");

	// 		Curso cliente = new Curso(id, nome, idade, carro);
	// 		if (cursoDAO.inserirCliente(cliente) == true) {
	// 			response.status(201); // 201 Created
	// 		} else {
	// 			response.status(404); // 404 Not found
	// 		}

	// 		response.redirect("/");
	// 		return mostrarClientes(request, response);

	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 	}
	// }

	// public Object edit(Request request, Response response) {
	// 	int id = Integer.parseInt(request.params(":id"));
	// 	String nome = request.queryParams("editNome");
	// 	int idade = Integer.parseInt(request.queryParams("editIdade"));
	// 	String carro = request.queryParams("editCarro");

	// 	Curso cliente = new Curso(id, nome, idade, carro);
	// 	if (cursoDAO.atualizarCliente(cliente, id) == true) {
	// 		response.status(201); // 201 Created
	// 	} else {
	// 		response.status(404); // 404 Not found
	// 	}

	// 	response.redirect("/");
	// 	return mostrarClientes(request, response);
	// }

	// public Object delete(Request request, Response response) {
	// 	int id = Integer.parseInt(request.params(":id"));

	// 	if (cursoDAO.excluirCliente(id) == true) {
	// 		response.status(201); // 201 Created
	// 	} else {
	// 		response.status(404); // 404 Not found
	// 	}

	// 	response.redirect("/");
	// 	return mostrarClientes(request, response);
	// }

	// public static int insertTeste(Request request, Response response) {
	// 	File imagemx = new File("/home/tiago/Downloads/imagem2.jpg");
	// 	File bannerx = new File("/home/tiago/Downloads/banner2.png");

	// 	try {
	// 		FileInputStream inputStream = new FileInputStream(imagemx);
	// 		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	// 		FileInputStream inputStream2 = new FileInputStream(bannerx);
	// 		ByteArrayOutputStream outputStream2= new ByteArrayOutputStream();

	// 		// Lê os bytes da imagem e escreve no ByteArrayOutputStream
	// 		int bytesRead;
	// 		byte[] buffer = new byte[1024];
	// 		while ((bytesRead = inputStream.read(buffer)) != -1) {
	// 			outputStream.write(buffer, 0, bytesRead);
	// 		}

	// 		int bytesRead2;
	// 		byte[] buffer2 = new byte[1024];
	// 		while ((bytesRead2 = inputStream2.read(buffer2)) != -1) {
	// 			outputStream2.write(buffer2, 0, bytesRead2);
	// 		}

	// 		// Obtém o array de bytes da imagem
	// 		byte[] imagemBytes = outputStream.toByteArray();
	// 		byte[] bannerBytes = outputStream2.toByteArray();

	// 		// Agora, imagemBytes contém os bytes da imagem no formato BLOB
			
	// 		// Fecha os streams
	// 		inputStream.close();
	// 		outputStream.close();
	// 		inputStream2.close();
	// 		outputStream2.close();

	// 		// Agora, a variável imagemBytes contém os bytes da imagem

	// 		int id_curso = 1;
	// 		double preco = 80.00;
	// 		int id_usuario = 0;
	// 		String categoria = "matematica";
	// 		String nome = "Equaciona Com Paulo Pereira - Aulas de Cálculo";
	// 		String descricao = "Aulas de cálculo 1 com a melhor didática possivel!";
	// 		byte[] imagem = imagemBytes;
	// 		byte[] banner = bannerBytes;

	// 		Curso curso = new Curso(id_curso, preco, id_usuario, categoria, nome, descricao, imagem, banner);
	// 		if (cursoDAO.inserirCurso(curso) == true) {
	// 			response.status(201); // 201 Created
	// 		} else {
	// 			response.status(404); // 404 Not found
	// 		}

			
	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 	}
	// 	return 0;

	// }
}