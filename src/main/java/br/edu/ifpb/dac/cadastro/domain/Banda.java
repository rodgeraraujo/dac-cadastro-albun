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
    private List<String> integrates;

    public Banda(int id, String nome, String localDeOrigem, List<String> 
            integrates) {
        this.id = id;
        this.nome = nome;
        this.localDeOrigem = localDeOrigem;
        this.integrates = integrates;
    }

    public Banda(String nome, String localDeOrigem, List<String> integrates) {
        this.nome = nome;
        this.localDeOrigem = localDeOrigem;
        this.integrates = integrates;
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

    public List<String> getIntegrates() {
        return integrates;
    }

    public void setIntegrates(List<String> integrates) {
        this.integrates = integrates;
    }

    @Override
    public String toString() {
        return "Banda{" + "id=" + id + ", nome=" + nome + ", localDeOrigem=" + 
                localDeOrigem + ", integrates=" + integrates + '}';
    }

    
}
