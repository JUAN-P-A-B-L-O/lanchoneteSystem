package com.lanchoneteSystem.cliente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Extrato{
    
    private String nome;
    private String cpf;
    private String dataPedido;
    private float valorTotal;
    private int id;
    
    /**
     * Construtor da classe extrato
     * @param valorTotal define o valor total no extrato
     * @param cpf define o cpf do cliente na qual o extrato pertence
     * @param nome define o nome do cliente na qual o extrato pertence
     * @param dataPedido define a data em que o cliente realizou o pedido
     */
    public Extrato(float valorTotal, String cpf, String nome, String dataPedido) {
        this.nome = nome;
        this.cpf = cpf;
        this.valorTotal = valorTotal;
        
        final DateFormat df = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
        Calendar c1 = Calendar.getInstance();
        final String dataFormatada = df.format(c1.getTime());
        this.dataPedido = dataFormatada;
    }

    /**
     * 
     * @return retorna o nome do cliente no extrato
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @param nome define o nome do cliente no extrato
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * 
     * @return retorna o cpf do cliente no extrato
     */
    public String getCpf() {
        return cpf;
    }
    
    /**
     * 
     * @param Cpf define o cpf de um cliente em um extrato 
     */
    public void setCpf(String Cpf) {
        this.cpf = Cpf;
    }

    /**
     * 
     * @return retorna a data do pedido
     */
    public String getDataPedido() {
        return dataPedido;
    }
    
    /**
     * 
     * @param dataPedido define a data do pedido
     */
    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }
    
    /**
     * 
     * @return retorna o valor total do pedido
     */
    public float getValorTotal() {
        return valorTotal;
    }
    
    /**
     * 
     * @param valorTotal define o valor total do pedido
     */
    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    /**
     * 
     * @return retorna o id do extrato
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id define o id do extrato
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Extrato{" + "nome=" + nome + ", cpf=" + cpf + ", dataPedido=" + dataPedido + ", valorTotal=" + valorTotal + ", id=" + id + '}';
    }

    
}
