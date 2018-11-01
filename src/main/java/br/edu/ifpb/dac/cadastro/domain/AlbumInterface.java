package br.edu.ifpb.dac.cadastro.domain;

import java.util.List;

/**
 *
 * @author rodger
 */
public interface AlbumInterface {
    void novo(Album album);
    
    Banda buscarBanda(String bandaNome);
    
    List<Album> listarAlbuns();
    
}
