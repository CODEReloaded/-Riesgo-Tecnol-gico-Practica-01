/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoservicio.regiomontano.ventas;

import java.io.IOException;
import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jon
 */
@ApplicationScoped
public class Venta {
    @NotNull
    @Digits(integer=6,fraction=2)
    private double total;        
    private double neto;    
    private double impuesto;
    
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getNeto() {
        return neto;
    }

    public void setNeto(double neto) {
        this.neto = neto;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }
    
    public String[] separaNombre(String nombre){        
        String delimitadores= "[ ]+";
        String[] palabraSeparada = nombre.split(delimitadores);
        return palabraSeparada;
    }
    
    public void calcula(String name, String apellidop, String apellidom, String bruto, String date) throws IOException, SQLException, ClassNotFoundException{
        try {
            this.total=Double.parseDouble(bruto);
            this.impuesto=total*.16;
            this.neto=total-impuesto;
            Conexion c = new Conexion();
            int r = c.actualizar("INSERT INTO venta(total,neto,impuestos) VALUES("+this.total+","+this.neto+","+this.impuesto+");");
            System.out.println(r == 1);
            c.actualizar("INSERT INTO capturista(nombres,apellido_p,apellido_m) VALUES('"+
                name+"', '"+apellidop+"', '"+apellidom+"');");            
            c.actualizar("INSERT INTO captura(fecha) VALUES('"+date+"');");
            c.desconectar();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");   
        }catch (java.lang.NumberFormatException e) {            
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");   
        }        
    }
    
}
