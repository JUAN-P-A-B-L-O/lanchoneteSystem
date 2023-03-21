package com.lanchoneteSystem.sistema;


public class Produto {
    private String nome;
    private String descricao;
    private float valor;
    private int id;
    
    /**
     * Construtor do Produto
     * @param nome define o nome do produto
     * @param descricao define a drescrição do produto, ou seja, do que ele é composto
     * @param valor  define o valor (R$) do produto
     */
    public Produto(String nome, String descricao, float valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }
    
    /**
     * 
     * @return retorna o nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @param nome define o nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * 
     * @return retorna a descrição do produto
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * 
     * @param descricao define a descrição do produto
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    /**
     * 
     * @return retorna o valor do protudo
     */
    public float getValor() {
        return valor;
    }
    
    /**
     * 
     * @param valor define o valor do produto
     */
    public void setValor(float valor) {
        this.valor = valor;
    }
    
    /**
     * 
     * @return retorna o ID único do produto
     */
    public int getId() {
        return id;
    }
    
    /**
     *
     * @param id define o ID único do produto
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Produto{" + "nome=" + nome + ", descricao=" + descricao + ", valor=" + valor + ", id=" + id + '}';
    }
    
    
}
