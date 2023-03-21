package com.lanchoneteSystem.empregados;


public class Administrador extends Funcionario{
    
    /**
    * Construtor do Administrador que chama o construtor da superclasse "Funcionario"
    * @param nome define um nome para um objeto administrador
    * @param cpf define um cpf para um objeto administrador
    * @param senha define a senha do administrador
     * @param isAdm define se é ADM ou não
    */
    public Administrador(String nome, String cpf, String senha, boolean isAdm){
        super(nome, cpf, senha, isAdm);
    }

    @Override
    public String toString() {
        return "Administrador{" + "Nome=" + super.getNome() + ", CPF=" + super.getCpf() +", senha=" + super.getSenha() + ", isAdm=" + super.getIsAdm() + '}';
    }
}
