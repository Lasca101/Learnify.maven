package service;

import java.util.ArrayList;
import java.util.HashMap;
import dao.PerguntasDAO;
import model.Perguntas;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

public class PerguntasService {

    private static PerguntasDAO perguntasDAO = new PerguntasDAO();

    public PerguntasService() {
    }

    public static ModelAndView mostrarPerguntas(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        ArrayList<Perguntas> perguntasX = new ArrayList<Perguntas>();

        int idAula = Integer.parseInt(req.queryParams("id_aula"));
        Perguntas perguntas[] = perguntasDAO.getPerguntas(); 
       
        for (Perguntas pergunta : perguntas) {
            if (pergunta.getId_aula() == idAula) {
                perguntasX.add(pergunta);
            }
        }

        try {
            model.put("perguntas", perguntasX);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView(model, "/view/aula.vm");
    }
}
