package br.edu.ifpb.dac.cadastro.domain;

import java.util.List;

/**
 *
 * @author rodger
 */
public interface AlbumInterface {
    void novo(Album album);
    
    int buscarBanda(String bandaNome);
    
//    List<Album> listarAlbuns();
    
    List<Album> todosOsAlbuns();
    
}
