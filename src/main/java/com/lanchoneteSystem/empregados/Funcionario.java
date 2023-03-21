package com.lanchoneteSystem.empregados;

public class Funcionario extends Pessoa{
    
    private String senha;
    private boolean isAdm;
    /**
    * Construtor do Funcionário que chama o construtor da superclasse "Usuario"
    * @param nome define um nome para um objeto funcionário
    * @param cpf define um cpf para um objeto funcionário
    * @param senha define a senha do funcionário
     * @param isAdm define se é ADM ou não
    */
    public  Funcionario(String nome, String cpf, String senha, boolean isAdm){
        super(nome, cpf);
        this.isAdm = isAdm;
        this.senha = senha;
    }
    
    /**
    * 
    * @return retorna a senha do funcionário
    */
    public String getSenha() {
        return senha;
    }
    
    /**
     * 
     * @param senha define a senha do usuário
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    /**
     * 
     * @return retorna se o funcionário é admin ou não
     */
    public boolean getIsAdm() {
        return isAdm;
    }

    /**
     *
     * @param isAdm define o grau de acesso do funcionário
     */
    public void setisAdm(boolean isAdm) {
        this.isAdm = isAdm;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "Nome=" + super.getNome() + ", CPF=" + super.getCpf() +", senha=" + senha + ", isAdm=" + isAdm + '}';
    }
}
