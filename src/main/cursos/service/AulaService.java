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

    public static ModelAndView mostrarVideoAula(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        Aula[] aulas = aulaDAO.getAulas();
        Aula aulaX = aulas[Integer.parseInt(req.queryParams("id_aula"))];
        Curso cursos[] = cursoDAO.getCursos();
        Curso cursoX = cursos[aulaX.getIdCurso()];

        try {
            model.put("aula", aulaX);
            model.put("curso", cursoX);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView(model, "/view/aula.vm");
    }

    public static ModelAndView mostrarAulas(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        Aula[] aulas = aulaDAO.getAulas();
        Aula[] aulasX = new Aula[aulas.length];

        int idCurso = Integer.parseInt(req.queryParams("id_curso"));
        Curso cursos[] = cursoDAO.getCursos();
        Curso cursoX = cursos[idCurso];
        int i = 0;
        for (Aula aula : aulas) {
            if (aula.getIdCurso() == idCurso) {
                aulasX[i] = aula;;
                i++;
            }
        }
        
        try {
            model.put("aulas", aulasX);
            model.put("curso", cursoX);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new ModelAndView(model, "/view/curso.vm");
    }
}
