/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lanchoneteSystem.cliente;

import com.lanchoneteSystem.sistema.Connection;
import com.lanchoneteSystem.sistema.GestorProdutos;
import com.lanchoneteSystem.sistema.PedidoComparator;
import com.lanchoneteSystem.sistema.Produto;
import com.lanchoneteSystem.sistema.Sistema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author JUAN-PC
 */
public class GestorPedidos {
    private final Connection<Pedidos> connectionPedidos = new Connection<>();

    /**
     * Cadastra um pedido na base de dados
     * @param cpf cpf do cliente que terá o pedido cadastrado
     * @param produtos lista com os ids dos produtos que serão cadastrados
     * @throws IOException 
     */
    public void cadastrarPedidos(String cpf, int[] produtos) throws IOException{
        try {
            float valor = 0;
            GestorProdutos gP1 = new GestorProdutos();
            ArrayList<Produto> allProdutos = gP1.getConnectionProduto().dadosJson(gP1.getConnectionProduto().getPathProdutos(), Produto[].class);
            ArrayList<Produto> produtosPedido = new ArrayList<>();

            for(Produto p: allProdutos){
                for(int i = 0; i < produtos.length; i++){
                    if(p.getId() == produtos[i]){
                        produtosPedido.add(p);
                        valor += p.getValor();
                    }
                }
            }
            GestorExtratos gE1 =new GestorExtratos();
            GestorClientes gC1 = new GestorClientes();
            
            Pedidos newPedido = new Pedidos(valor, cpf);
            newPedido.setId(this.defIdUnicoPedido(cpf));
            
            newPedido.setProdutos(produtosPedido);
            connectionPedidos.dump(newPedido, connectionPedidos.getPathPedidos());
            
            Cliente c = gC1.pesquisarCliente(cpf);
            Extrato e = new Extrato(valor, cpf, c.getNome(), newPedido.getDataPedido());
            e.setId(newPedido.getId());
            gE1.gerarExtrato(e);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Remove um pedido da base de dados
     * @param id define o id do pedido que será removido
     * @throws IOException 
     */
    public void removerPedido(int id) throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        Iterator<Pedidos> allPedidos = pedidos.iterator();
        GestorExtratos gE1 = new GestorExtratos();
        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            if (p.getId() == id) {
                gE1.removerExtrato(id);
                allPedidos.remove();
                connectionPedidos.saveState(connectionPedidos.getPathPedidos(), pedidos);
                break;
            }
        }
    }
    
    
    /**
     * Permite editar um determinado pedido
     * @param id define o id do pedido que será editado
     * @param dataType define qual dado será alterado, podendo ser o status, cpf, valor ou horarioEntrega
     * @param info define a nova informação
     * @throws IOException 
     */
    public void editarPedido(int id, String dataType, String info) throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        for (Pedidos p : pedidos) {
            if (p.getId() == id) {
                if (null != dataType)switch (dataType) {
                    case "status" -> p.setStatus(info);
                    case "cpf" -> p.setCpf(info);
                    case "horarioEntrega" -> p.setHorarioEntrega(Integer.parseInt(info));
                    default -> {
                    }
                }
                connectionPedidos.saveState(connectionPedidos.getPathPedidos(), pedidos);
                break;
            }
        }
    }
    
    /**
     * Lista os pedidos da base de dados
     * @return retorna um array com todos os pedidos
     * @throws IOException 
     */
    public Pedidos[] listarPedidos() throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        Collections.sort(pedidos, new PedidoComparator());
        Pedidos[] dados = new Pedidos[pedidos.size()];
        dados = pedidos.toArray(dados);
        return dados;
    }
 
    
    /**
     * Função que permite editar somente os produtos dentro de um pedido e recalcula o valor
     * @param id id do pedido
     * @param idProdutos ids dos novos produtos
     * @throws IOException 
     */
    public void editarProdutoInPedido(int id, int[] idProdutos) throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        GestorProdutos gP1 = new GestorProdutos();
        ArrayList<Produto> allProdutos = gP1.getConnectionProduto().dadosJson(gP1.getConnectionProduto().getPathProdutos(), Produto[].class);
        ArrayList<Produto> produtos = new ArrayList<>();
        float newValue = 0;
        for (Pedidos p : pedidos) {
            if (p.getId() == id) {
                for(Produto listProdutos: allProdutos){
                    for(int j = 0; j < idProdutos.length; j++){
                        if(idProdutos[j] == listProdutos.getId()){
                            produtos.add(listProdutos);
                            newValue += listProdutos.getValor();
                        }
                    }
                }
                
                p.setProdutos(produtos);
                p.setValorTotal(newValue);
                connectionPedidos.saveState(connectionPedidos.getPathPedidos(), pedidos);
                return;
            }
        }
        throw new RuntimeException();
    }
    
    
    /**
     * Função que verifica o maior ID da base de dados a fim de gerar um id único
     * @param type define o nome do objeto que será criado um id de controle
     * @return retorna o maior ID da base de dados +1
     * @throws IOException 
     */
    public final int defIdUnicoPedido(String type) throws IOException{
       
            int max = 0;
            ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
            for (Pedidos p: pedidos){
                if(p.getId() > max){
                    max = p.getId();
                }
            }
            return max+1;
     
    }
    
    
    /**
     * Define as estatística em um determinado período de tempo
     * @param date1 data inicial
     * @param date2 data final
     * @param hora1 hora inicial
     * @param hora2 hora final
     * @return retorna a array com a média e o desvio padrão, respectivamente
     * @throws IOException 
     */
    public float[] getEstatistica(String date1, String date2, int hora1, int hora2) throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        Iterator<Pedidos> allPedidos = pedidos.iterator();
        ArrayList<Float> valores = new ArrayList<>();
        
        float mean = 0;
        float desvioPadrao = 0;
        Sistema s1 = new Sistema();
        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            if(s1.filterDate(date1, date2,hora1,hora2, 
                                p.getDataPedido(), p.getHorarioPedido())){
                valores.add(p.getValorTotal());
                mean += p.getValorTotal();
            }
        }
        
        if(valores.isEmpty()) return null;
        
        mean = mean/valores.size();
        
        for (Float v : valores){

            desvioPadrao += (float)Math.pow((v-mean),2);
        }
        
        desvioPadrao = (float)Math.sqrt((desvioPadrao/(valores.size()-1)));
        
        float[] dados = {mean, desvioPadrao};
        return dados;
    }
    
    
    /**
     * Pesquisa os pedidos de um cliente em um intervalo de tempo
     * @param cpf cpf do cliente que será pesquisado
     * @param date1 data inicial
     * @param date2 data final
     * @param hora1 hora inicial
     * @param hora2 hora final
     * @return retorna um ArrayList com todos os pedidos vendidos para um determinado cliente
     * @throws IOException 
     */
    public Pedidos[] getClientePedidos(String cpf, String date1, String date2, int hora1, int hora2) throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        GestorClientes gC1 = new GestorClientes();
        ArrayList<Cliente> clientes = gC1.getConnectionCliente().dadosJson(gC1.getConnectionCliente().getPathClinte(), Cliente[].class);
        ArrayList<Pedidos> pedidosCliente = new ArrayList<>();
        Sistema s1 = new Sistema();
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                for(Pedidos p: pedidos){
                        if(p.getCpf().equals(cpf)){
                            if(s1.filterDate(date1, date2,hora1,hora2, 
                                p.getDataPedido(), p.getHorarioPedido())){
                                pedidosCliente.add(p);
                        }
                    }
                }
                break;
            }
        }
        Pedidos[] dados = new Pedidos[pedidosCliente.size()];
        dados = pedidosCliente.toArray(dados);
        return dados;
    }
    
    /**
     * 
     * @return retorna a conexão dos pedidos
     */
    public Connection<Pedidos> getConnectionPedidos() {
        return connectionPedidos;
    }

    @Override
    public String toString() {
        return "GestorPedidos{" + "connectionPedidos=" + connectionPedidos + '}';
    }
    
}
