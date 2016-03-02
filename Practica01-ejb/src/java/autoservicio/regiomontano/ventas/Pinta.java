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
 * @author jorge
 */
@ApplicationScoped
public class Pinta {
    String fecha;
    double total;
    double neto;
    
    public Pinta(String fecha, double total, double neto) {
        this.fecha = fecha;
        this.total = total;
        this.neto= neto;
    }

    public String getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public double getNeto() {
        return neto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setNeto(double neto) {
        this.neto = neto;
    }
}
