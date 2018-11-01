/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.cadastro.domain;

import br.edu.ifpb.dac.cadastro.DAO.AlbumDAO;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rodger
 */
@WebServlet(name = "ControladorDeAlbum", urlPatterns = {"/albuns"})
public class ControladorDeAlbum extends HttpServlet {

    private AlbumDAO albuns = new AlbumDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style>\n" +
                            "table {\n" +
                            "    font-family: arial, sans-serif;\n" +
                            "    border-collapse: collapse;\n" +
                            "    width: 100%;\n" +
                            "}\n" +
                            "\n" +
                            "td, th {\n" +
                            "    border: 1px solid #dddddd;\n" +
                            "    text-align: left;\n" +
                            "    padding: 8px;\n" +
                            "}\n" +
                            "\n" +
                            "tr:nth-child(even) {\n" +
                            "    background-color: #dddddd;\n" +
                            "}" +
                            "</style>");
            out.println("<title>Servlet ControladorDeAlbum</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listagem de Album(s) e Banda(s)</h1>");
            out.println("<table>\n" +
                            "  <tr>\n" +
                            "    <th>Code</th>\n" +
                            "    <th>Name</th>\n" +
                            "    <th>Price</th>\n" +
                            "  </tr>\n");
            imprimir(out);
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Estilo estilo = tipoEstilo(request.getParameter("estilo"));
        Banda banda = buscarPorBanda(request.getParameter("banda"));
        LocalDate anoDePublicacao = LocalDate.parse(request.getParameter("anoDeLancamento"));
        
        Album album = new Album(
                estilo, 
                banda, 
                anoDePublicacao
        );
        
        this.albuns.novo(album);
        
        response.sendRedirect(request.getRequestURI());
        
    }

    private Estilo tipoEstilo(String estilo) {
        if (estilo.equals(Estilo.Pop)) {
            return Estilo.Pop;
        } else if (estilo.equals(Estilo.Rock)){
            return Estilo.Rock;
        } else if (estilo.equals(Estilo.Sertanejo)){
            return Estilo.Sertanejo;
        }    
        return null;
    }

    private Banda buscarPorBanda(String bandaNome) {
        return this.albuns.buscarBanda(bandaNome);
    }

    private void imprimir(PrintWriter out) {
        this.albuns.listarAlbuns()
                .forEach(
                        c -> out.println("<tr>\n" +
                            "    <td>" + c.getEstilo() + "</td>\n" +
                            "    <td>"+ 
                                    imprimeBandaInfo(c.getBanda())+
                            "    </td>\n" +
                            "    <td>" + c.getAnoDeLancamento() + "</td>\n" +
                            "  </tr>\n")
                );
    }

    private String imprimeBandaInfo(Banda banda) {
        out.println("<table>\n"
                + "     <tr>\n"
                + "         <th>Nome</th>\n"
                + "         <th>Local de origem</th>\n"
                + "         <th>Integrates</th>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "         <td>"+banda.getNome()+"</td>\n"
                + "         <td>"+banda.getIntegrates()+"</td>\n"
                + "         <td>"+banda.getLocalDeOrigem()+"</td>\n"
                + "     </tr>\n"
                + "</table>");
        return null;
    }
}
