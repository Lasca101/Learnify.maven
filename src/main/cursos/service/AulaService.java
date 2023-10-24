package service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import dao.AulaDAO;
import dao.CursoDAO;
import model.Aula;
import model.Curso;
import model.Perguntas;
import service.PerguntasService;
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
        Aula aulaX = null;
        int i = 0;
        while(aulas[i] != null){
            if(aulas[i].getIdAula() == Integer.parseInt(req.queryParams("id_aula"))){
                aulaX = aulas[i];
                break;
            }
            i++;
        }
        Curso cursos[] = cursoDAO.getCursos();
        Curso cursoX = cursos[aulaX.getIdCurso()];

        ArrayList<Perguntas> perguntasX = new ArrayList<Perguntas>();
        perguntasX = PerguntasService.mostrarPerguntas(req, res);
        try {
            model.put("aula", aulaX);
            model.put("curso", cursoX);
            model.put("perguntas", perguntasX);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView(model, "/view/aula.vm");
    }

    public static ModelAndView mostrarAulas(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        Aula[] aulas = aulaDAO.getAulas();
        ArrayList<Aula> aulasX = new ArrayList<Aula>();

        int idCurso = Integer.parseInt(req.queryParams("id_curso"));
        Curso cursos[] = cursoDAO.getCursos();
        Curso cursoX = cursos[idCurso];
        for (Aula aula : aulas) {
            if (aula.getIdCurso() == idCurso) {
                aulasX.add(aula);
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

    // public static int insertTeste(Request request, Response response) {
    //      File imagemx = new File("/home/tiago/Downloads/t3.jpg");

    //      try {
    //          FileInputStream inputStream = new FileInputStream(imagemx);
    //        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    //        // Lê os bytes da imagem e escreve no ByteArrayOutputStream
    //        int bytesRead;
    //        byte[] buffer = new byte[1024];
    //        while ((bytesRead = inputStream.read(buffer)) != -1) {
    //            outputStream.write(buffer, 0, bytesRead);
    //        }

    //        // Obtém o array de bytes da imagem
    //        byte[] imagemBytes = outputStream.toByteArray();

    //        // Agora, imagemBytes contém os bytes da imagem no formato BLOB

    // //       // Fecha os streams
    //        inputStream.close();
    //        outputStream.close();

    // //         // Agora, a variável imagemBytes contém os bytes da imagem

    //          int id_curso = 1;
    //          int id_aula = 2;
    //          String titulo = "Definição de Limite - Cálculo 1 (#3)";
    //          String link = "voBexx2V7gw";
    //          byte[] thumbnail = imagemBytes;

    //          Aula aula = new Aula(id_aula, id_curso, titulo, thumbnail, link);
    //          if (aulaDAO.inserirAula(aula) == true) {
    //              response.status(201); // 201 Created
    //         } else {
    //              response.status(404); // 404 Not found
    //          }


    //      } catch (IOException e) {
    //          e.printStackTrace();
    //      }
    //     return 0;

    //  }
}
