package service;

import java.util.Base64;
import java.util.HashMap;
import dao.AulaDAO;
import dao.CursoDAO;
import model.Aula;
import model.Curso;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

public class AulaService {

    private static AulaDAO aulaDAO = new AulaDAO();
    private static CursoDAO cursoDAO = new CursoDAO();

    public AulaService() {
    }

    public static ModelAndView mostrarAulas(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        Aula[] aulas = aulaDAO.getAulas();
        Aula aulaX = aulas[Integer.parseInt(req.queryParams("id_aula"))];
        Curso cursos[] = cursoDAO.getCursos();
        Curso cursoX = cursos[aulaX.getIdCurso()];
        model.put("aula", aulaX);
        model.put("curso", cursoX);

        return new ModelAndView(model, "/view/aula.vm");
    }
}
