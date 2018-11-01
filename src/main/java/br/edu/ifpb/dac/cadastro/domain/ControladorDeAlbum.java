/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.cadastro.domain;

import br.edu.ifpb.dac.cadastro.DAO.AlbumDAO;
import br.edu.ifpb.dac.cadastro.DAO.BandaDAO;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rodger
 */
@WebServlet(name = "ControladorDeAlbum", urlPatterns = {"/ControladorDeAlbum"})
public class ControladorDeAlbum extends HttpServlet {

    private AlbumDAO albuns = new AlbumDAO();
    private BandaDAO bandas = new BandaDAO();
    
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
                            "    border: 1px solid lightgray;\n" +
                            "    text-align: left;\n" +
                            "    padding: 8px;\n" +
                            "}\n" +
                            "\n" +
                            "tr:nth-child(1) {\n" +
                            "    background-color: #dddddd;\n" +
                            "}\n" +
                            "</style>");
            out.println("<title>Servlet ControladorDeAlbum</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listagem de Album(s) e Banda</h1>");
            out.println("<table>\n" +
                            "  <tr>\n" +
                            "    <th>Nome</th>\n" +
                            "    <th>Estilo</th>\n" +
                            "    <th>Banda</th>\n" +
                            "    <th>Ano de Lançamento</th>\n" +
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
        
        String nome = request.getParameter("nome");
        Estilo estilo = Estilo.valueOf(request.getParameter("estilo"));
        int banda = buscarPorBanda(request.getParameter("banda"));
        String anoDeLancamento = request.getParameter("anoDeLancamento");
 
        Album album = new Album(nome, estilo, banda, anoDeLancamento);
               
        response.sendRedirect(request.getRequestURI());        
    }
    
    private void imprimir(PrintWriter out) {  
        List<Album> listaAlbuns = this.albuns.todosOsAlbuns();
        int i = 0;
        Banda bandaInfo = imprimeBandaInfo(listaAlbuns.get(i).getBanda());
        for (int j = 0; i < 10; i++) {
            out.println("<tr>\n" +
                            "    <td>" + listaAlbuns.get(i).getNome() + "</td>\n" +        
                            "    <td>" + listaAlbuns.get(i).getEstilo() + "</td>\n" +
                            "    <td>"
                                    +"<table>\n"
                                    + "     <tr>\n"
                                    + "         <th>Nome</th>\n"
                                    + "         <th>Local de origem</th>\n"
                                    + "         <th>Integrates</th>\n"
                                    + "     </tr>\n"
                                    + "     <tr>\n"
                                    + "         <td>"+bandaInfo.getNome()+"</td>\n"
                                    + "         <td>"+bandaInfo.getLocalDeOrigem()+"</td>\n"
                                    + "         <td>"+bandaInfo.getIntegrantes()+"</td>\n"                                            
                                    + "     </tr>\n"
                                    + "</table>"+
                            "    </td>\n" +
                            "    <td>" + listaAlbuns.get(i).getAnoDeLancamento() + "</td>\n" +
                            "  </tr>\n");
        }
        
    }

    private Banda imprimeBandaInfo(int bandaId) {
        return bandas.buscarBandaInfo(bandaId);
    }

    private List<Album> listarAlbuns() {
        return this.albuns.todosOsAlbuns();
    }
    
    private int buscarPorBanda(String bandaNome) {
        return this.albuns.buscarBanda(bandaNome);
    }
   
}
