/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lanchoneteSystem.empregados;

import com.lanchoneteSystem.sistema.Connection;
import com.lanchoneteSystem.sistema.Proxy;
import com.lanchoneteSystem.sistema.Sistema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author JUAN-PC
 */
public class GestorFuncionarios {
    private final Connection<Funcionario> connectionFuncionario = new Connection<>();
    private final Connection<Administrador> connectionAdmnistrador = new Connection<>();
    Funcionario funcionarios[] = new Funcionario[15];
    
    String nomes[] = {"Vicente Pires", "Davi Moreira", "Daniela Pires", "Emanuelly Aragão", "Mirella da Cunha",
                      "Evelyn da Luz", "Bernardo Gonçalves", "Pedro Aragão", "Rodrigo Vieira", "Maria Alice", "Gabriel Novaes",
                      "Laura da Paz", "Raquel da Luz", "Bruno Cunha", "Marcela Lopes"};
    
    String cpfs[] = {"41334161046", "54765983080", "21539673090", "43970882095", "53986071032", "05538463028", "30504563025",
                    "42061623018", "56296427077", "30544141083", "21494379023", "57994467040", "60657174017", "98942706037",
                    "64298908054"};
    
    /**
     * Cadastra os 15 colaboradores iniciais
     * @throws IOException 
     */
    public void CadastrarEstaticoColaborador() throws IOException{
        for(int i = 0; i < 15; i++){
            funcionarios[i] = new Funcionario(nomes[i], cpfs[i], "1234", false);
            connectionFuncionario.dump(funcionarios[i], connectionFuncionario.getPathEmpregado());
            funcionarios[i] = null;
        }
    }
  
    /**
     * Cadastra um empregado (funcionário ou administrador)
     * @param name define o nome do funcionário
     * @param cpf define o cpf do funcionário
     * @param senha define a senha do funcionário
     * @param isADM define se o tipo de funcionário é administrador ou não
     * @throws IOException 
     */
    public void cadastrarEmpregado(String name, String cpf, String senha, boolean isADM) throws IOException, Exception{
        try {
            if(uniqueCpfEmpregado(cpf)){
                Proxy p1 = new Proxy();
                String senhaCodificada = p1.codificaSenha(senha);
                if (isADM == false){
                    Funcionario newFuncionario = new Funcionario(name, cpf, senhaCodificada, isADM);
                    connectionFuncionario.dump(newFuncionario, connectionFuncionario.getPathEmpregado());
                }
                else{
                    Administrador newAdm = new Administrador(name, cpf, senhaCodificada, isADM);
                    connectionAdmnistrador.dump(newAdm, connectionAdmnistrador.getPathEmpregado());
                }
            }
            else{
                throw new Exception();
            }
            
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    /**
     * Remove um empregado da base de dados
     * @param cpf define o cpf do empregado que será removido
     * @throws IOException 
     */
    public void removerEmpregado(String cpf) throws IOException{
        //remover do BD
        ArrayList<Funcionario> users = connectionFuncionario.dadosJson(connectionFuncionario.getPathEmpregado(), Funcionario[].class);
        Iterator<Funcionario> allUsers = users.iterator();
        while (allUsers.hasNext()) {
            Pessoa u = allUsers.next();
            if (u.getCpf().equals(cpf)) {
                allUsers.remove();
                connectionFuncionario.saveState(connectionFuncionario.getPathEmpregado(), users);
                return;
            }
        }
        throw new RuntimeException();
    }
    
    /**
     * Permite editar os dados de um empregado
     * @param cpf define o cpf do empregado que será editado
     * @param dataType define o dado que se deseja alterar
     * @param info o novo dado que será passado
     * @throws IOException 
     */
    public void editarEmpregado(String cpf, String dataType, String info) throws IOException{
        ArrayList<Funcionario> users = connectionFuncionario.dadosJson(connectionFuncionario.getPathEmpregado(), Funcionario[].class);
        Proxy p1 = new Proxy();

        for (Funcionario f : users) {
            if (f.getCpf().equals(cpf)) {
                if (null != dataType){
                    switch (dataType) {
                    case "senha" -> f.setSenha(p1.codificaSenha(info));
                    case "nome" -> f.setNome(info);
                    case "cpf" -> f.setCpf(info);
                    case "toAdm" -> f.setisAdm(true);
                    case "toFunc" -> f.setisAdm(false);
              
                default -> {
                    }
                
                }
            }
                connectionFuncionario.saveState(connectionFuncionario.getPathEmpregado(), users);
                return;
            }
        }
        throw new RuntimeException();
    }
    
    /**
     * Lista todos os empregados da base de dados
     * @return retorna um array de empregados
     * @throws IOException 
     */
    public Funcionario[] listarEmpregados() throws IOException{
        ArrayList<Funcionario> users = connectionFuncionario.dadosJson(connectionFuncionario.getPathEmpregado(), Funcionario[].class);
        Funcionario[] dados = new Funcionario[users.size()];
        dados = users.toArray(dados);
        return dados;
    }
 
    /**
     * Define se o cpf do empregado é único ou não
     * @param cpf cpf a ser comparado
     * @return retorna true se ele for único
     * @throws java.io.IOException
     */
    public boolean uniqueCpfEmpregado(String cpf) throws IOException{
        ArrayList<Funcionario> empregados = connectionFuncionario.dadosJson(connectionFuncionario.getPathEmpregado(), Funcionario[].class);

        for(Funcionario f: empregados){
            if(f.getCpf().equals(cpf)){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Permite logar no sistema e setar se é um ADM ou funcionário usando o sistema
     * @param userName nome de usuário(cpf)
     * @param senha senha
     * @return se os dados estiverem corretos, retorna true
     * @throws IOException 
     */
    public boolean logar(String userName, String senha) throws IOException{
        Funcionario[] empregados = listarEmpregados();
        Proxy p1 = new Proxy();
        String senhaCodificada = p1.codificaSenha(senha);
        for (Funcionario empregado : empregados) {
            if (empregado.getCpf().equals(userName) && empregado.getSenha().equals(senhaCodificada)) {
                if (empregado.getIsAdm()) {
                    Sistema.setIsAdm(true);
                } else {
                    Sistema.setIsAdm(false);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "GestorFuncionarios{" + "connectionFuncionario=" + connectionFuncionario + ", connectionAdmnistrador=" + connectionAdmnistrador + ", funcionarios=" + funcionarios + ", nomes=" + nomes + ", cpfs=" + cpfs + '}';
    }  
    
}
