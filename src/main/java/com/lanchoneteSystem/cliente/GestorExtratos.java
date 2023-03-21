/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lanchoneteSystem.cliente;

import com.lanchoneteSystem.sistema.Connection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author JUAN-PC
 */
public class GestorExtratos {
    
    private final Connection<Extrato> connectionExtrato = new Connection<>();

    /**
     * Salva um extrato gerado no BD
     * @param e objeto extrato
     * @throws IOException 
     */
    public void gerarExtrato(Extrato e) throws IOException{
        connectionExtrato.dump(e, connectionExtrato.getPathExtratos());
    }
    
    
    /**
     * Lista os extratos na base de dados e os retorna
     * @return retorna uma lista de extratos
     * @throws IOException 
     */
    public Extrato[] listarExtrato() throws IOException{
        ArrayList<Extrato> extratos = connectionExtrato.dadosJson(connectionExtrato.getPathExtratos(), Extrato[].class);
        Extrato[] dados = new Extrato[extratos.size()];
        dados = extratos.toArray(dados);
        return dados;
    }
    
    
    /**
     * Remove o Extrato do cliente
     * @param id id do extrato
     * @throws IOException 
     */
    public void removerExtrato(int id) throws IOException{
        ArrayList<Extrato> extratos = connectionExtrato.dadosJson(connectionExtrato.getPathExtratos(), Extrato[].class);
        Iterator<Extrato> allExtratos = extratos.iterator();
        
        while (allExtratos.hasNext()) {
            Extrato e = allExtratos.next();
            if (e.getId() == id) {
                allExtratos.remove();
                connectionExtrato.saveState(connectionExtrato.getPathExtratos(), extratos);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "GestorExtratos{" + "connectionExtrato=" + connectionExtrato + '}';
    }
}
