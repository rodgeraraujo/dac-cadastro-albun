package br.edu.ifpb.dac.cadastro.DAO;

import br.edu.ifpb.dac.cadastro.domain.Album;
import br.edu.ifpb.dac.cadastro.domain.AlbumInterface;
import br.edu.ifpb.dac.cadastro.domain.Banda;
import br.edu.ifpb.dac.cadastro.domain.Estilo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodger
 */
public class AlbumDAO implements AlbumInterface{
    
    private Connection connection;

    public AlbumDAO() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://host-banco:5432/albuns",
                    "rodger", "mnb098"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void novo(Album album) {
        
        try {
            PreparedStatement createStatement = this.connection.prepareStatement(
                    "INSERT INTO album(nome, estilo, banda, anoDeLancamento) VALUES (?,?,?);"
            );
            createStatement.setString(1, album.getNome());
            createStatement.setString(2, String.valueOf(album.getEstilo()));
            createStatement.setInt(3, album.getBanda());
            createStatement.setDate(4, java.sql.Date.valueOf(album.getAnoDeLancamento()));
            
            createStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int buscarBanda(String bandaNome) {
        try {
            Statement createStatement = this.connection.createStatement();
            ResultSet result = createStatement
                    .executeQuery("SELECT id FROM banda WHERE nome = '"+bandaNome+"';");
            if (result.next()) {
                return result.getInt("id");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

   
    
    @Override
    public List<Album> todosOsAlbuns() {
        List<Album> lista = new ArrayList<>();
        try {
            Statement createStatement = this.connection.createStatement();
            ResultSet result = createStatement.executeQuery("SELECT * FROM album;");
            iterarComAlbuns(result, lista);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
    private void iterarComAlbuns(ResultSet result, List<Album> lista) throws SQLException {
        while (result.next()) {
            lista.add(
                criarAlbum(result)
            );
        }
    }
    
    private Album criarAlbum(ResultSet result) throws SQLException {
        
        int id = result.getInt("id");
        String nome = result.getString("nome");
        Estilo estilo = Estilo.valueOf(result.getString("estilo"));
        int banda = result.getInt("banda");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String anoDeLancamento = result.getString("anoDeLancamento");

        System.out.println(new Album(id, nome, estilo, banda, anoDeLancamento));
        return new Album(id, nome, estilo, banda, anoDeLancamento);
    }

}
