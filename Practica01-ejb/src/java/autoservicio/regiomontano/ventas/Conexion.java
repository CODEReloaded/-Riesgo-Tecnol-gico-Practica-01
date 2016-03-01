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
    

    public ObjetoBD[] consultar() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/autoservicio", "postgres", "postgres");
        Statement stmt = con.createStatement();
        String query = "SELECT * FROM capturista;";
        ResultSet resultados = stmt.executeQuery(query);
        LinkedList<ObjetoBD> salida = new LinkedList<>();
        while (resultados.next()) {
            salida.add(new ObjetoBD(resultados.getInt("id"),resultados.getString("nombres")));
        }
        return salida.toArray(new ObjetoBD[0]);
    }
}
