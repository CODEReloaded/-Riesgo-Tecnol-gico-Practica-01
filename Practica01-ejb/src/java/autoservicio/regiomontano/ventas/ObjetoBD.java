/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoservicio.regiomontano.ventas;

/**
 *
 * @author jon
 */
public class ObjetoBD {
    private int id;
    private String valor;
    
    public ObjetoBD(int id, String val) {
        this.id = id;
        this.valor = val;
    }
    
    public ObjetoBD(int id){
        this.id= id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }    
    
    public String toString(){
        return "id: "+id+","+valor;
    }
            
}
