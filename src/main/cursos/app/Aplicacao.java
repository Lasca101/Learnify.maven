package app;

import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

import service.*;


public class Aplicacao {
	
	private static CursoService cursoService = new CursoService();
	private static AulaService aulaService = new AulaService();
	
    public static void main(String[] args) {
    	VelocityTemplateEngine engine = new VelocityTemplateEngine();
        staticFiles.location("/public");
    	
        port(6789);
        
        get("/home", (request, response) -> CursoService.mostrarCursos(request, response), engine);
        // comentario teste
        get("/aula", (request, response) -> AulaService.mostrarAulas(request, response), engine);

    }
}