/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.cadastro.DAO;

import br.edu.ifpb.dac.cadastro.domain.Banda;
import br.edu.ifpb.dac.cadastro.domain.BandaInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodger
 */
public class BandaDAO implements BandaInterface{
    
    private Connection connection;

    public BandaDAO() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://host-banco:5432/albuns",
                    "rodger", "mnb098"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    @Override
    public Banda buscarBandaInfo(int bandaId) {
        try {
            Statement createStatement = this.connection.createStatement();
            ResultSet result = createStatement
                    .executeQuery("SELECT * FROM banda WHERE id = '"+bandaId+"';");
            
            if (result.next()){
                return criarBanda(result);
            }  
            
        } catch (SQLException ex) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Banda criarBanda(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String nome = result.getString("nome");
        String localDeOrigem = result.getString("localDeOrigem");
        
        String listaIntegrantes = result.getString("integrantes");
        List<String> integrantes = new ArrayList<String>(Arrays.asList(listaIntegrantes.split(",")));
        
        return new Banda(id, nome, localDeOrigem, integrantes);
    }
      
}
