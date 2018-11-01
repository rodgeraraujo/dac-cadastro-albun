package br.edu.ifpb.dac.cadastro.DAO;

import br.edu.ifpb.dac.cadastro.domain.Album;
import br.edu.ifpb.dac.cadastro.domain.AlbumInterface;
import br.edu.ifpb.dac.cadastro.domain.Banda;
import br.edu.ifpb.dac.cadastro.domain.Estilo;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
                    "INSERT INTO albuns VALUES (?,?,?);"
            );
            createStatement.setString(1, String.valueOf(album.getEstilo()));
            createStatement.setArray(2, (Array) album.getBanda());
            createStatement.setDate(3, Date.valueOf(album.getAnoDeLancamento()));
            
            
            createStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Album> listarAlbuns() {
        List<Album> lista = new ArrayList<>();
        try {
            Statement createStatement = this.connection.createStatement();
            ResultSet result = createStatement.executeQuery("SELECT * FROM album;");
            iterarAlbuns(result, lista);

        } catch (SQLException ex) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Banda buscarBanda(String bandaNome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void iterarAlbuns(ResultSet result, List<Album> lista) throws SQLException {
        while (result.next()) {
            lista.add(
                    criarAlbum(result)
            );
        }
    }

    private Album criarAlbum(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        Estilo estilo = Estilo.valueOf(result.getString("estilo"));
        String banda = result.getString("banda");
        Date anoDeLancamento = result.getDate("anoDeLancamento");

        return new Album(id, estilo, banda, anoDeLancamento);
    }
}
