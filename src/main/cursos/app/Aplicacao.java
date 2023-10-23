package app;

import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

import service.*;


public class Aplicacao {
	
	public static void main(String[] args) {
        // adicioanar ideia de cache
    	VelocityTemplateEngine engine = new VelocityTemplateEngine();
        staticFiles.location("/public");
    	
        port(6781);
        
        get("/home", (request, response) -> CursoService.mostrarCursos(request, response), engine);
        get("/aula", (request, response) -> AulaService.mostrarVideoAula(request, response), engine);
        get("/aula", (request, response) -> PerguntasService.mostrarPerguntas(request, response), engine);
        get("/curso", (request, response) -> AulaService.mostrarAulas(request, response), engine);

        // get("/logout", (request, response) -> {
        //     request.session().removeAttribute("usuario_id");
        //     response.redirect("/login");
        //     return null;
        // },engine);

        // get("/add", (request, response) -> AulaService.insertTeste(request, response));
    }
}