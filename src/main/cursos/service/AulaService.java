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
        makeForm();
    }

    public static ModelAndView mostrarAulas(Request req, Response res) {
        HashMap<String, String> model = new HashMap<>();
        model.put("htmlAulas", makeForm());

        return new ModelAndView(model, "/view/aula.vm");
    }

    public static String makeForm() {
        Aula[] aulas = aulaDAO.getAulas();
        StringBuilder htmlAulas = new StringBuilder();

        htmlAulas.append("<table border=\"1\">\n"); 

        for (Aula aula : aulas) {
            String thumbnailBase64 = aula.getThumbnail(); // Já é uma string no formato Base64

            htmlAulas.append("<tr>\n"); 
            htmlAulas.append("    <td><img src=\"data:image/jpeg;base64," + thumbnailBase64 + "\" alt=\"aula\"></td>\n");
            htmlAulas.append("    <td>" + aula.getTitulo() + "</td>\n");
            htmlAulas.append("    <td><a href=\"/pagina-aula/pagina_aula.html?id=" + aula.getIdAula() + "\">Ver Detalhes</a></td>\n");
            htmlAulas.append("</tr>\n"); 
        }

        htmlAulas.append("</table>"); // Fecha a tabela

        return htmlAulas.toString();
    }

}
