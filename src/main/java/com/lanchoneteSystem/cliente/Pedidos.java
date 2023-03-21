package com.lanchoneteSystem.cliente;
import com.lanchoneteSystem.sistema.Produto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalTime;

public class Pedidos{
    
    private ArrayList<Produto> produtos = new ArrayList<>();
    private String dataPedido;
    private String status;
    private String cpf;
    private float valorTotal;
    private int horarioEntrega;
    private int horarioPedido;
    private int id;
    
    /**
     * Por padrão, a data e o horário do pedido são setados no momento em que o objeto é inciado. 
     * Além disso, seu statu inicial é "em preparo"
     * @param valorTotal define o valor total do pedido
     * @param cpf define o cpf do cliente que fez o pedido
     */
    public Pedidos(float valorTotal, String cpf) {
        dataPedido = LocalDate.now().toString();
        horarioPedido = LocalTime.now().getHour();
        this.valorTotal = valorTotal;
        this.status = "em preparo";
        this.cpf = cpf;
        //setId();
    }
    
    /**
     * 
     * @return retorna o array list dos produtos do pedido
     */
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }
    
    /**
     * 
     * @param produtos define o array list de produtos
     */
    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
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
     * @return retrona o status atual do pedido
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * 
     * @param status define o status do pedido
     */
    public void setStatus(String status) {
        this.status = status;
        if(status.equals("Entregue")){
            setHorarioEntrega(LocalTime.now().getHour());
        }
    }
    
    /**
     * 
     * @return retorna o cpf do cliente que fez o pedido
     */
    public String getCpf() {
        return cpf;
    }
    
    /**
     * 
     * @param cpf define o cpf do cliente
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    /**
     * 
     * @return retorna o valor total do pedido
     */
    public float getValorTotal() {
        return valorTotal;
    }

    /**
     * Logicamente, o valor total do produto deverá ser chamado para definir a soma de todos os valores de cada produto
     * @param valorTotal define o valor total do produto
     */
    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * 
     * @return retorna o horário da entrega
     */
    public int getHorarioEntrega() {
        return horarioEntrega;
    }
    
    /**
     * 
     * @param horarioEntrega define o horário da entrega
     */
    public void setHorarioEntrega(int horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }

    /**
     * 
     * @return retorna o horário do pedido
     */
    public int getHorarioPedido() {
        return horarioPedido;
    }
    
    /**
     * 
     * @param horarioPedido define o horário em que o pedido foi feito
     */
    public void setHorarioPedido(int horarioPedido) {
        this.horarioPedido = horarioPedido;
    }
    
    /**
     * 
     * @return retorna o ID único do pedido
     */
    public int getId() {
        return id;
    }
    
    /**
     * 
     * @param id define o ID único do pedido
     */
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Pedidos{" + "produtos=" + produtos + ", dataPedido=" + dataPedido + ", status=" + status + ", cpf=" + cpf + ", valorTotal=" + valorTotal + ", horarioEntrega=" + horarioEntrega + ", horarioPedido=" + horarioPedido + ", id=" + id + '}';
    }
   
}
