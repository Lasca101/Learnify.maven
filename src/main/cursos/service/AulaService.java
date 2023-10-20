package service;

import java.util.Base64;
import java.util.HashMap;
import dao.AulaDAO;
import model.Aula;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

public class AulaService {

    private static AulaDAO aulaDAO = new AulaDAO();

    public AulaService() {
    }

    public static ModelAndView mostrarAulas(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        Aula[] aulas = aulaDAO.getAulas();
        model.put("aulas", aulas);

        return new ModelAndView(model, "/view/aula.vm");
    }
}
