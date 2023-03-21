package com.lanchoneteSystem.sistema;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONArray;

public class Connection <T>{
    
    private String pathClinte;
    private String pathEmpregado;
    private String pathProdutos;
    private String pathPedidos;
    private String pathExtratos;

    /**
     * Construtor que inicializa os caminhos de diretórios para a base de dados.
     */
    public Connection() {
        setPathClinte("src\\main\\java\\Dados\\clientes.json");
        setPathEmpregado("src\\main\\java\\Dados\\empregados.json");
        setPathProdutos("src\\main\\java\\Dados\\produtos.json");
        setPathPedidos("src\\main\\java\\Dados\\pedidos.json");
        setPathExtratos("src\\main\\java\\Dados\\extratos.json");
    }

    /**
     * A função open irá acessar um arquivo específico na base de dados e retornar seus dados no formato de String.
     * @param path define o arquivo que se quer abrir
     * @return retorna um ArrayList com todos os dados referente ao arquivo que se abriu
     * @throws IOException 
     */
    private ArrayList<String> open(String path) throws IOException{
        File file = new File(path);
        ArrayList<String> dados = new ArrayList<>();
        
        String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        JSONArray jsonArray = new JSONArray(content);
        for(int i = 0; i < jsonArray.length(); i++){
            dados.add(jsonArray.get(i).toString());
        }
        return dados;
    }
    
    /**
     * A fução dump irá ler as informações de seu respectivo arquivo json e jogar um novo dado para esse arquivo.
     * @param object define o objetivo que se deseja armazenar as informações
     * @param path define o caminho do json
     * @throws IOException 
     */
    public void dump(T object, String path) throws IOException{
        FileWriter writeFile = null;
        ArrayList<String> dados = open(path);
        Gson gson = new Gson();
        String jsonDados = gson.toJson(object);
        dados.add(jsonDados);
        try {
            writeFile = new FileWriter(path, false);
            writeFile.write(dados.toString());
        } catch (IOException e) {
        }finally{
            writeFile.close();
        }
    }   
    
    /**
     * A função dados Json irá ler os dados do json.
     * @param path define o caminho do json
     * @param classType define o tipo da classe
     * @return retornar uma ArrayList com objetos do tipo especifíco que o json armazena.
     * @throws IOException 
     */
    public ArrayList<T> dadosJson(String path, Class<T[]> classType) throws IOException{
        File file = new File(path);
        Gson gson = new Gson();
        String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        T[] dadosArr = gson.fromJson(content, classType);
        ArrayList<T> dados = new ArrayList<>(Arrays.asList(dadosArr));
        return dados;
    }
    
    
    /**
     * A função saveState objetiva salvar o estado de uma base de dados.
     * Sua vantagem é que ela pode ser utilizada em funções como remover e editar.
     * @param path define o caminho do json
     * @param dados define um ArrayList dos dados que seja salvar
     * @throws IOException 
     */
    public void saveState(String path, ArrayList dados) throws IOException{
        FileWriter writeFile = null;
        Gson gson = new Gson();
        String jsonDados = gson.toJson(dados);
        try {
            writeFile = new FileWriter(path, false);
            writeFile.write(jsonDados);
            writeFile.close();
        } catch (IOException e) {
        }finally{
            writeFile.close();
        }
    }

    /**
     * 
     * @return retorna o path do json cliente
     */
    public String getPathClinte() {
        return pathClinte;
    }

    /**
     * 
     * @param pathClinte defnie o path do json cliente
     */
    public final void setPathClinte(String pathClinte) {
        this.pathClinte = pathClinte;
    }
    
    /**
     * 
     * @return retorna o path do json empregado
     */
    public String getPathEmpregado() {
        return pathEmpregado;
    }

    /**
     * 
     * @param pathEmpregado defnie o path do json empregado
     */
    public final void setPathEmpregado(String pathEmpregado) {
        this.pathEmpregado = pathEmpregado;
    }
    
    /**
     * 
     * @return retorna o path do json produto
     */
    public String getPathProdutos() {
        return pathProdutos;
    }
    
    /**
     * 
     * @param pathProdutos defnie o path do json produtos
     */
    public final void setPathProdutos(String pathProdutos) {
        this.pathProdutos = pathProdutos;
    }

    /**
     * 
     * @return retorna o path do json produto
     */
    public String getPathPedidos() {
        return pathPedidos;
    }
    
    /**
     * 
     * @param pathPedidos define o path do json pedidos
     */
    public final void setPathPedidos(String pathPedidos) {
        this.pathPedidos = pathPedidos;
    }
    
    /**
     * 
     * @return retorna o path do json extratos
     */
    public String getPathExtratos() {
        return pathExtratos;
    }
    
    /**
     * 
     * @param pathExtratos define o path do json extratos
     */
    public final void setPathExtratos(String pathExtratos) {
        this.pathExtratos = pathExtratos;
    }

    @Override
    public String toString() {
        return "Connection{" + "pathClinte=" + pathClinte + ", pathEmpregado=" + pathEmpregado + ", pathProdutos=" + pathProdutos + ", pathPedidos=" + pathPedidos + ", pathExtratos=" + pathExtratos + '}';
    }
}
