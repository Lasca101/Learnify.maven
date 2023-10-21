package app;

import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

import service.*;


public class Aplicacao {
	
	public static void main(String[] args) {
    	VelocityTemplateEngine engine = new VelocityTemplateEngine();
        staticFiles.location("/public");
    	
        port(6789);
        
        get("/home", (request, response) -> CursoService.mostrarCursos(request, response), engine);
        get("/aula", (request, response) -> AulaService.mostrarVideoAula(request, response), engine);
        get("/curso", (request, response) -> AulaService.mostrarAulas(request, response), engine);
    }
}