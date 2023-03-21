package com.lanchoneteSystem.lanchonetev2;
import com.lanchoneteSystem.cliente.Cliente;
import com.lanchoneteSystem.cliente.Extrato;
import com.lanchoneteSystem.cliente.GestorClientes;
import com.lanchoneteSystem.cliente.GestorExtratos;
import com.lanchoneteSystem.cliente.GestorPedidos;
import com.lanchoneteSystem.cliente.Pedidos;
import com.lanchoneteSystem.empregados.Funcionario;
import com.lanchoneteSystem.empregados.GestorFuncionarios;
import com.lanchoneteSystem.sistema.GestorProdutos;
import com.lanchoneteSystem.view.TelaLogin;
import java.io.IOException;
import com.lanchoneteSystem.sistema.Produto;
import com.lanchoneteSystem.sistema.Sistema;
import java.util.ArrayList;


public class LanchoneteV2 {

    public static void main(String[] args) throws IOException, Exception {
        
        //GUI
        TelaLogin start = new TelaLogin();
        start.setVisible(true);
//--------------------------------------------------------------------------------------------------------------------------
        /*
            OBS: O código foi voltado para a interface, assim a maioria das funções são na verdade retornos. Com isso,
                 essas questões comentadas não representam o todo do trabalho, o que, por consequência, pode ocasionar
                 erros. Tais erros são perceptíveis pelo sistema e representados visualmente na GUI, logo é ela quem 
                 representa o sistema completo.
        */
        
        /*
            OBS2: Todos os testes foram feitos na versão 19 do java. 
                  Outros testes foram feitos em versões anteriores, porém existia um erro de compilação.
        */
//--------------------------------------------------------------------------------------------------------------------------        
        
/*
        Questão 3.1 e 6 - CRUD Cliente
        Como o trabalho foi feito voltado para a Interface Gráfica, deve-se checar os dados no json para observar as mudanças
        ou usar a função de listar dentro de um for(o mesmo irá valer para as outras questões).
        */
        
        //GestorClientes gC1 = new GestorClientes(); //Objeto de gerência do cliente
        //gC1.cadastrarCliente("Douglas", "987.654.321-00", "98800000", "Centro"); //Cadastrar
        //gC1.removerCliente("987.654.321-00"); //Remover
        //gC1.editarCliente("987.654.321-00", "nome", "Martins Douglas"); //Editar (deve ser passado o dado que se quer editar e a nova informação)
        //System.out.println(gC1.pesquisarCliente("123.142.443-65")); //Pesquisar
        /*//Listar:
        Cliente[] dados = gC1.listarClientes();
        for (Cliente dado : dados) {
            System.out.println(dado);
        }
        */
        
//--------------------------------------------------------------------------------------------------------------------------       
        
        /*
        Questão 3.2 - CRUD Empregados
        */
        //GestorFuncionarios gF1 = new GestorFuncionarios(); //objeto de gerência dos funcionários
        //gF1.cadastrarEmpregado("Silvino", "000.222.000-22", "1234", false); //Cadastrar
        //gF1.removerEmpregado("122.222.333-54"); //Remover
        //gF1.editarEmpregado("232.222.876-65", "nome", "Fabio Luiz"); //Editar (CPF de quem quer editar; dataType é o atributo que será editado e info o novo valor)
        /*//Listar:
        Funcionario[] listaFun=gF1.listarEmpregados();
        for (Funcionario funcionario : listaFun) {
            System.out.println(funcionario);
        }
        */     
        
//--------------------------------------------------------------------------------------------------------------------------               
        
        /*
        Questão 3.3 - Alterar Senha Administrador
        Como a senha está com hash, não adianta lista-lá para ver se houve alteração. A melhor solução aqui é tentar logar no sistema pela interface
        */
        //GestorFuncionarios gF2 = new GestorFuncionarios(); //objeto de gerência dos funcionários
        //gF2.editarEmpregado("123.456.789-23", "senha", "123"); //Edita a senha para 123
        
//--------------------------------------------------------------------------------------------------------------------------

        /*
        Questão 3.4 e 7 - CRUD Produto
        */
        //GestorProdutos gP1 = new GestorProdutos(); //objeto de gerência dos produtos
        //gP1.cadastrarProduto("Batata Frita", "Batata Inglesa, frita ao óleo", 4.5F); //Cadastrar
        //gP1.removerProduto(9); //Remover (Usa os ID para isso. Nesse caso, remove a batata)
        //gP1.editarProduto(9, "valor", "5.5"); //Editar (ID de quem quer editar; dataType e info)
        /*//Listar(Para uma melhor visualização utilize a interface):
        Produto[] listaProds= gP1.listarProduto();
        for (Produto produto : listaProds) {
            System.out.println(produto);
        }
        */            
        
//-------------------------------------------------------------------------------------------------------------------------- 

        /*
        Questão 3.5 - CRUD Pedido
        */
        //GestorPedidos gP1 = new GestorPedidos(); //objeto de gerência dos pedidos
        //int[] idsProdutos = {1,3};
        //gP1.cadastrarPedidos("123.142.223-65", idsProdutos);//Cadastrar - Uma lista de ids de Produtos deve ser passada
        //gP1.removerPedido(2); //Remover (Usa os ID para isso)
        //int[] idsProdutosEditar = {4,5};
        //gP1.editarProdutoInPedido(1, idsProdutosEditar); //Editar (ID de quem quer editar; Ids dos novos produtos)
        /*//Listar:
        Pedidos[] dados = gP1.listarPedidos();
        for (Pedidos dado : dados){
            System.out.println(dado);
        }
        */            
        
//-------------------------------------------------------------------------------------------------------------------------- 
       
        //Questão 3.6 - Pesquisar Produtos em Intervalo de Tempo
        /*
        GestorPedidos gP1 = new GestorPedidos(); //objeto de gerência dos pedidos
        Pedidos[] dados = gP1.getClientePedidos("123.142.223-65", "2000-01-01", "2050-01-01", 0, 0); //CPF do cliente e intervalo de tempo
        for(Pedidos dado: dados){
            System.out.println(dado.getProdutos());
        }
        */           
        
//-------------------------------------------------------------------------------------------------------------------------- 

        /*
        Questão 3.7 - Gerar Estatística do dia
        */
        /*
        GestorPedidos gP2 = new GestorPedidos(); //objeto de gerência dos pedidos
        
        float[] resultado = new float[2];
        resultado = gP2.getEstatistica("2000-12-12", "2022-12-21", 0, 4); //Data Inicia e Final; Hora Inical e Final
        if(resultado != null){
            System.out.println("Média: " + resultado[0]);
            System.out.println("Desvio Padrão: " + resultado[1]); //Se houver somente um valor, não haverá DP
        }
        */
        
//--------------------------------------------------------------------------------------------------------------------------
        
        /*
        Questão 5 - Cadastro Estatíco de 15 colaboradores
        */ 
        //GestorFuncionarios gF2 = new GestorFuncionarios(); //objeto de gerência dos funcionários
        //gF2.CadastrarEstaticoColaborador();
        
//--------------------------------------------------------------------------------------------------------------------------

        /*
        Questão 9 - Extratos
        */ 
        //GestorExtratos gE1 = new GestorExtratos(); //objeto de gerência dos extratos
        /*//Listar:
        Extrato[] dados = gE1.listarExtrato();
        for (Extrato dado : dados){
            System.out.println(dado);
        }
        */
             
//--------------------------------------------------------------------------------------------------------------------------

        /*
        Questão 10.a e 11 - Instâncias get/set
        */ 
        
        
        //System.out.println(GestorClientes.contadorCliente()); // Cliente.getContador() também funcionária
        /*Retorna quantas instâncias foram criadas da classe Cliente. Como o
        static só irá incrementar por compilação, seria interessante instânciar alguns clientes(por meio do cadastrar) para
        ver um resultado diferente de 0.
        */
        
        /*
        GestorClientes gC2 = new GestorClientes(); //Objeto de gerência do cliente
        gC2.cadastrarCliente("João", "333.111.333-00", "9811111", "MG"); //Cadastrar
        gC2.cadastrarCliente("Pedro", "111.333.111-00", "9822222", "MG"); //Cadastrar
        System.out.println(GestorClientes.contadorCliente());
        */

//--------------------------------------------------------------------------------------------------------------------------

        /*
        Questão 10.b - Protected
        */ 
        
        //System.out.println(GestorClientes.contadorClienteProtected()); 
        /*Retorna quantas instâncias protected foram criadas da classe Cliente. Como o
        static só irá incrementar por compilação, seria interessante instânciar alguns clientes(por meio do cadastrar) para
        ver um resultado diferente de 0.
        */
        
        /*
        GestorClientes gC2 = new GestorClientes(); //Objeto de gerência do cliente
        gC2.cadastrarCliente("João", "333.121.333-00", "9811111", "MG"); //Cadastrar
        gC2.cadastrarCliente("Pedro", "111.323.111-00", "9822222", "MG"); //Cadastrar
        System.out.println(GestorClientes.contadorClienteProtected());
        */

//--------------------------------------------------------------------------------------------------------------------------
       
        //Questão 10.c - Protected
        
        /*
           A variável private tem vantagem de maior encapsulamento, maior modularização e segurança. No entanto, há uma certa
           perca no desempenho já que se faz duas chamadas no sistema.
       
           Já na protected existe um ganho de desempenho, pois ela é acessa com somente uma chamada. No entanto, os príncipios
           do encapsulamento, segurança e engenharia de software não são totalmente respeitados.
        */

//--------------------------------------------------------------------------------------------------------------------------

       /*
        Questão 12 - Comparator Interface
        A interface está implementa implicitamente no código. Ao listar os clientes eles estarão ordenados por ordem
        alfabética. Já os pedidos, serão listados com base no valor total, do maior para o menor
        */ 

//--------------------------------------------------------------------------------------------------------------------------

        //Questão 14 - Pesquisar Pedidos em intervalo de tempo
        /*
        GestorPedidos gP1 = new GestorPedidos(); //objeto de gerência dos pedidos
        Pedidos[] dados = gP1.getClientePedidos("123.142.223-65", "2000-01-01", "2050-01-01", 0, 0); //CPF do cliente e intervalo de tempo
        for(Pedidos dado: dados){
            System.out.println(dado);
        }
        */
//--------------------------------------------------------------------------------------------------------------------------
        
        //INTERFACE
    
        /*TelaLogin start = new TelaLogin();
        start.setVisible(true);*/
    }
}
