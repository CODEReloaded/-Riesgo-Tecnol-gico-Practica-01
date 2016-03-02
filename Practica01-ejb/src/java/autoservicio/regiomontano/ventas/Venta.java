/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoservicio.regiomontano.ventas;

import java.io.IOException;
import static java.lang.System.out;
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
    
    public void calcula(String bruto,String date,String name, String apellidop,String apellidom) throws IOException, SQLException, ClassNotFoundException{
        try {
            //Capturista cap=new Capturista(name,apellidop,apellidom);
            total=Double.parseDouble(bruto);
            impuesto=total*.16;
            neto=total-impuesto;
            Conexion c = new Conexion();            
            String query = "INSERT INTO capturista(nombres,apellido_p,apellido_m) VALUES('"+name+"','"+apellidop+"','"+apellidom+"');";
            String query1="INSERT INTO venta(total,neto,impuestos) VALUES("+total+","+neto+","+impuesto+");";
            String query2="INSERT INTO captura(fecha) VALUES('"+date+"');";            
            c.guardar(query);
            c.guardar(query1);
            c.guardar(query2);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");   
        }catch (java.lang.NumberFormatException e) {            
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");   
        }        
    }
    
}
