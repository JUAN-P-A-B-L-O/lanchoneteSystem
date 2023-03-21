/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lanchoneteSystem.sistema;

import com.lanchoneteSystem.cliente.GestorPedidos;
import com.lanchoneteSystem.cliente.Pedidos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author JUAN-PC
 */
public class GestorProdutos {
    
    private final Connection<Produto> connectionProduto = new Connection<>();
    
    /**
     * Cadastra um novo produto na base de dados
     * @param nome define o nome do produto
     * @param descricao define a descrição do produto
     * @param valor define o valor do produto
     * @throws IOException 
     */
    public void cadastrarProduto(String nome, String descricao, float valor) throws IOException{
        try {
            Produto newProduto = new Produto(nome,descricao,valor);
            newProduto.setId(this.defIdUnicoProduto());
            connectionProduto.dump(newProduto, connectionProduto.getPathProdutos());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Remove um produto da base de dados
     * @param id descreve o id do produto que será removido
     * @throws IOException 
     */
    public void removerProduto(int id) throws IOException{
        ArrayList<Produto> produtos = connectionProduto.dadosJson(connectionProduto.getPathProdutos(), Produto[].class);
        Iterator<Produto> allProduto = produtos.iterator();
        while (allProduto.hasNext()) {
            Produto p = allProduto.next();
            if (p.getId() == id) {
                allProduto.remove();
                connectionProduto.saveState(connectionProduto.getPathProdutos(), produtos);
                break;
            }
        }
    }
    
    /**
     * Edita algum dado sobre o produto
     * @param id se refere ao id do produto à ser editado
     * @param dataType define o tipo de dado que irá alterar, podendo ser "nome", "descrição" e "valor"
     * @param info define a nova informação
     * @throws IOException 
     */
    public void editarProduto(int id, String dataType, String info) throws IOException{
        //acessar no BD e editar
        ArrayList<Produto> produtos = connectionProduto.dadosJson(connectionProduto.getPathProdutos(), Produto[].class);
        for (Produto p : produtos) {
            if (p.getId() == id) {
                if (null != dataType)switch (dataType) {
                    case "nome" -> p.setNome(info);
                    case "descrição" -> p.setDescricao(info);
                    case "valor" -> p.setValor(Float.parseFloat(info));
                    default -> {
                    }
                }
                connectionProduto.saveState(connectionProduto.getPathProdutos(), produtos);
                break;
            }
        }
    }
    
    /**
     * Lista os Produtos na base de dados e os retorna
     * @return retorna um array com todos os produtos da base de dados
     * @throws IOException 
     */
    public Produto[] listarProduto() throws IOException{
        ArrayList<Produto> produtos = connectionProduto.dadosJson(connectionProduto.getPathProdutos(), Produto[].class);
        Produto[] dados = new Produto[produtos.size()];
        dados = produtos.toArray(dados);
        return dados;
    }
    
    /**
     * Função que verifica o maior ID da base de dados a fim de gerar um id único
     * @return retorna o maior ID da base de dados +1
     * @throws IOException 
     */
    public final int defIdUnicoProduto() throws IOException{
            int max = 0;
            ArrayList<Produto> produtos = connectionProduto.dadosJson(connectionProduto.getPathProdutos(), Produto[].class);
            for (Produto p: produtos){
                if(p.getId() > max){
                    max = p.getId();
                }
            }
            return max+1;
    }
    
    /**
     * Pesquisa um produto vendido em um intervalo de tempo
     * @param date1 data inicial
     * @param date2 data final
     * @param hora1 horário inicial
     * @param hora2 horário final
     * @param cpf cpf do cliente que será pesquisado
     * @return retorna um ArrayList com todos os produtos vendidos para um determinado cliente
     * @throws IOException 
     */
    public ArrayList<Produto> pesquisarProdutos(String date1, String date2, int hora1, int hora2, String cpf) throws IOException{
        GestorPedidos gP1 = new GestorPedidos();
        Sistema s1 = new Sistema();
        ArrayList<Pedidos> pedidos = gP1.getConnectionPedidos().dadosJson(gP1.getConnectionPedidos().getPathPedidos(), Pedidos[].class);
        Iterator<Pedidos> allPedidos = pedidos.iterator();
        ArrayList<Produto> produtos = new ArrayList<>();
        Collections.sort(pedidos, new PedidoComparator());
        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            if (p.getCpf().equals(cpf)){
                if(s1.filterDate(date1, date2, hora1, hora2, p.getDataPedido(), p.getHorarioPedido())){
                    for (Produto pr : p.getProdutos()){
                        produtos.add(pr);
                    }
                }
            }
        }
        return produtos;
    }
    
    /**
     * 
     * @return retorna a conexão com o Produto
     */
    public Connection<Produto> getConnectionProduto() {
        return connectionProduto;
    }

    @Override
    public String toString() {
        return "GestorProdutos{" + "connectionProduto=" + connectionProduto + '}';
    }
}
