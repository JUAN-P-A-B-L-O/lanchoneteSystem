package com.lanchoneteSystem.cliente;
import com.lanchoneteSystem.empregados.Pessoa;

public class Cliente extends Pessoa{
    
    private String telefone;
    private String endereco;
    private static int contador;
    protected static int contadorProtected;
    
    /**
    * Construtor da classe Cliente
    * @param nome define um nome para um objeto cliente
    * @param cpf define um cpf para um objeto cliente
    * @param telefone define um telefone para o cliente
    * @param  endereco define um endreço para o cliente
    */
    public Cliente(String nome, String cpf, String telefone, String endereco){
        super(nome, cpf);
        Cliente.contadorProtected += 1;
        setContador();
        this.endereco = endereco;
        this.telefone = telefone;
    }
    
    /**
     * 
     * @return retrona o telefone do cliente
     */
    public String getTelefone() {
        return telefone;
    }
    
    /**
     * 
     * @param telefone define o telefone do cliente
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * 
     * @return retorna o endereço do cliente
     */
    public String getEndereco() {
        return endereco;
    }
    
    /**
     * 
     * @param endereco define o endereço do cliente
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    /**
     * 
     * @return retorna o número de instâncias do objeto cliente
     */
    public static int getContador() {
        return contador;
    }
    
    /**
     * Adiciona em 1 a quantidade de clientes instânciados
     */
    public static void setContador() {
        Cliente.contador += 1;
    }
    
    @Override
    public String toString() {
        return "Cliente{" + "Nome=" + super.getNome() + ", CPF=" + super.getCpf() + "telefone=" + telefone + ", endereco=" + endereco + '}';
    }
}
