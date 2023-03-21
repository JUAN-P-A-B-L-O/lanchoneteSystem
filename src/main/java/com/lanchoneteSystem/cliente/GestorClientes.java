/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lanchoneteSystem.cliente;

import com.lanchoneteSystem.sistema.ClienteComparator;
import com.lanchoneteSystem.sistema.Connection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author JUAN-PC
 */
public class GestorClientes {
    private final Connection<Cliente> connectionCliente = new Connection<>();
   
    /**
     * Cadastra um cliente na base de dados
     * @param nome define o nome do cliente à ser cadastrado
     * @param cpf define o cpf à ser cadastrado
     * @param telefone define o telefone à ser cadastrado
     * @param end define o endereço à ser cadastrado
     * @throws IOException 
     */
    public void cadastrarCliente(String nome, String cpf, String telefone, String end) throws IOException, Exception{
        try {
            if(uniqueCpfCliente(cpf)){
                Cliente newCliente = new Cliente(nome,cpf,telefone,end);
                connectionCliente.dump(newCliente, connectionCliente.getPathClinte());
            }else{
                throw new Exception();
            }
            
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Remove um cliente da base de dados
     * @param cpf define o cpf do cliente que será removido
     * @throws IOException 
     */
    public void removerCliente(String cpf) throws IOException{
      
        ArrayList<Cliente> clientes = connectionCliente.dadosJson(connectionCliente.getPathClinte(), Cliente[].class);
        Iterator<Cliente> allClientes = clientes.iterator();
        while (allClientes.hasNext()) {
            Cliente c = allClientes.next();
            if (c.getCpf().equals(cpf)) {
                System.out.println(c.getNome());
                allClientes.remove();
                connectionCliente.saveState(connectionCliente.getPathClinte(), clientes);
                return;
            }
        }
        throw new RuntimeException();
    }
    
    /**
     * Permite editar os dados de um cliente
     * @param cpf define o cpf do cliente que será editado
     * @param dataType define o dado que se deseja alterar
     * @param info o novo dado que será passado
     * @throws IOException 
     */
    public void editarCliente(String cpf, String dataType, String info) throws IOException{        
        ArrayList<Cliente> clientes = connectionCliente.dadosJson(connectionCliente.getPathClinte(), Cliente[].class);
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                System.out.println(c.getNome());
                if (null != dataType)switch (dataType) {
                    case "end" -> c.setEndereco(info);
                    case "nome" -> c.setNome(info);
                    case "telefone" -> c.setTelefone(info);
                    case "cpf" -> c.setCpf(info);
                    default -> {
                    }
                }
                connectionCliente.saveState(connectionCliente.getPathClinte(), clientes);
                return;
            }
        }
        throw new RuntimeException();
    }
    
    /**
     * Lista todos os clientes da base de dados
     * @return retorna um array de clientes
     * @throws IOException 
     */
    public Cliente[] listarClientes() throws IOException{
        ArrayList<Cliente> clientes = connectionCliente.dadosJson(connectionCliente.getPathClinte(), Cliente[].class);
        Collections.sort(clientes, new ClienteComparator());
        Cliente[] dados = new Cliente[clientes.size()];
        dados = clientes.toArray(dados);
        return dados;
    }
    
    /**
     * Permite pesquisar um cliente específico na base de dados
     * @param cpf define o cpf do cliente a ser pesquisado
     * @return  retorna um objeto do tipo cliente
     * @throws IOException 
     */
    public Cliente pesquisarCliente(String cpf) throws IOException{
        ArrayList<Cliente> clientes = connectionCliente.dadosJson(connectionCliente.getPathClinte(), Cliente[].class);
        Collections.sort(clientes, new ClienteComparator());
        for(Cliente c: clientes){
            if(cpf.equals(c.getCpf())){
                return c;
            }
        }
        return null;
    }
    
 
    /**
     * Define se o cpf do cliente é único ou não
     * @param cpf cpf a ser comparado
     * @return retorna true se ele for único
     * @throws IOException 
     */
    public boolean uniqueCpfCliente(String cpf) throws IOException{
       ArrayList<Cliente> clientes = connectionCliente.dadosJson(connectionCliente.getPathClinte(), Cliente[].class);

        for(Cliente c: clientes){
            if(c.getCpf().equals(cpf)){
                return false;
            }
        }
        return true;
    }
    
    
    
    /**
     * Contador estatíco das instâncias de cliente
     * @return Retorna quantos clientes foram criados estatícamente
     */
    public static int contadorCliente(){
        return Cliente.getContador();
    }
    
    /**
     * Contador estatíco das instâncias protected de cliente
     * @return Retorna quantos clientes foram criados estatícamente
     */
    public static int contadorClienteProtected(){
        return Cliente.contadorProtected;
    }
    
    /**
     * 
     * @return retorna a conexão com os clientes
     */
    public Connection<Cliente> getConnectionCliente() {
        return connectionCliente;
    }

    @Override
    public String toString() {
        return "GestorClientes{" + "connectionCliente=" + connectionCliente + '}';
    }
}
