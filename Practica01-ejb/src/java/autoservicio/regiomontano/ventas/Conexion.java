/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoservicio.regiomontano.ventas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author jon
 */
@ApplicationScoped
public class Conexion {
    public Conexion(){}
    
    public String capturista(String name){
        name = "INSERT INTO capturista(nombres,apellido_p,apellido_m) VALUES('JONATHAN', 'ABREGO', 'ALVAREZ');";
        return name;
    }
    
    public String venta(String bruto){
        bruto = "INSERT INTO venta(total,neto,impuestos) VALUES(1000,840,160);";
        return bruto;        
    }
    
    public String fecha(String date){
        date="INSERT INTO captura(fecha) VALUES('01/10/1999');";//1999/10/01
        return date;
    }

    public void guardar(String name, String bruto, String date) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/autoservicio", "postgres", "postgres");
        Statement stmt = con.createStatement();
        String query=capturista(name);
        query+=venta(bruto);
        query+=fecha(date);        
        stmt.execute(query);        
    }    
}
