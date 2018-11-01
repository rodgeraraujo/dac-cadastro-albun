/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.cadastro.domain;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author rodger
 */
public class Album {  
    private int id;
    private String nome;    
    private Estilo estilo; //Rock, Pop, Sertanejo
    private int banda;
    private String anoDeLancamento;

    
    public Album(String nome, Estilo estilo, int banda, String anoDeLancamento) {
        this.nome = nome;
        this.estilo = estilo;
        this.banda = banda;
        this.anoDeLancamento = anoDeLancamento;
    }
    
    public Album(int id, String nome, Estilo estilo, int banda, String anoDeLancamento) {
        this.nome = nome;
        this.id = id;
        this.estilo = estilo;
        this.banda = banda;
        this.anoDeLancamento = anoDeLancamento;
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
    

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public int getBanda() {
        return banda;
    }

    public void setBanda(int banda) {
        this.banda = banda;
    }

    public String getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(String anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", estilo=" + estilo + ", banda=" + 
                banda + ", anoDeLancamento=" + anoDeLancamento + '}';
    }
    
    
    
}
