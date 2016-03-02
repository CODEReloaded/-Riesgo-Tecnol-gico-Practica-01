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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author jon
 */
@ApplicationScoped
public class Conexion {
    
    private Statement stmt;
    Connection con;
    
    public Conexion(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("no se puede iniciar");
        }
        try {
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/autoservicio", "postgres", "postgres");
        } catch (SQLException ex) {
            System.out.println("no se conecto");
        }        
    }
    
    public ResultSet consultar(String sql){
        try{
            this.stmt = this.con.createStatement();
            ResultSet rs = this.stmt.executeQuery(sql);
            if(rs.next()){ return rs;}
        } catch(SQLException e){
            return null;
        }
        return null;
    }
    
    public boolean actualizar(String sql){
        try {
            this.stmt = this.con.createStatement();
            return this.stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println("no se guardo");
            System.out.println(ex.getMessage());
            desconectar();
            return false;
        }
    }
    
    /**
     * Desconecta al sistema de la base de datos. 
     */
    public void desconectar() {
        try {
            this.stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            this.con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    public void guardar(String query) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/autoservicio", "postgres", "postgres");
        Statement stmt = con.createStatement();        
        stmt.execute(query);       
    }    
}
