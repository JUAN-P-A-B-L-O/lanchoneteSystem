package com.lanchoneteSystem.sistema;

import com.lanchoneteSystem.cliente.Pedidos;
import java.util.Comparator;


public class PedidoComparator implements Comparator<Pedidos>{

    @Override
    public int compare(Pedidos p1, Pedidos p2) {
        if(p1.getValorTotal() > p2.getValorTotal()){
            return 1;
        }
        else if(p1.getValorTotal() < p2.getValorTotal()){
            return -1;
        }
        else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "PedidoComparator{" + '}';
    }
    
}
