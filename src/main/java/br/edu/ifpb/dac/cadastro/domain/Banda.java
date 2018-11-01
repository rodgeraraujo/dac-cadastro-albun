/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.cadastro.domain;

import java.util.List;

/**
 *
 * @author rodger
 */
public class Banda {
    private int id;
    private String nome;
    private String localDeOrigem;
    private List<String> integrantes;

    public Banda(int id, String nome, String localDeOrigem, List<String> 
            integrantes) {
        this.id = id;
        this.nome = nome;
        this.localDeOrigem = localDeOrigem;
        this.integrantes = integrantes;
    }

    public Banda(String nome, String localDeOrigem, List<String> integrantes) {
        this.nome = nome;
        this.localDeOrigem = localDeOrigem;
        this.integrantes = integrantes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalDeOrigem() {
        return localDeOrigem;
    }

    public void setLocalDeOrigem(String localDeOrigem) {
        this.localDeOrigem = localDeOrigem;
    }

    public List<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<String> integrantes) {
        this.integrantes = integrantes;
    }

    @Override
    public String toString() {
        return "Banda{" + "id=" + id + ", nome=" + nome + ", localDeOrigem=" + 
                localDeOrigem + ", integrantes=" + integrantes + '}';
    }

    
}
