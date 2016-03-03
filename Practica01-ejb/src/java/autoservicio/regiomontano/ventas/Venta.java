/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoservicio.regiomontano.ventas;

import java.io.IOException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
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
    
    public Pinta[] graficador() throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/autoservicio", "postgres", "postgres");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select TO_CHAR(fecha, 'dd Mon YYYY'), sum(total), sum(neto) from captura join venta on (id = id_venta) group by fecha order by fecha asc;");
        LinkedList<Pinta> salida = new LinkedList<>();
        while(rs.next()){
            System.out.println("Uno");
            salida.add(new Pinta(rs.getString(1), rs.getDouble(2), rs.getDouble(3)));
        }
        return salida.toArray(new Pinta[0]);
    }
    
    public String subtitle() throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/autoservicio", "postgres", "postgres");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select TO_CHAR(fecha, 'dd Mon YYYY'), sum(total) from captura join venta on (id = id_venta) group by fecha order by fecha desc limit 2;");
        double [] salida = new double[2];
        int i = 0;
        while(rs.next()){
            salida[i++] = rs.getDouble(2);
        }
        if(salida[1] - salida[0] == 0) return "se han mantenido.";
        return salida[1] - salida[0] > 0 ? "han disminuido." : "han incrementado.";
    }
}
