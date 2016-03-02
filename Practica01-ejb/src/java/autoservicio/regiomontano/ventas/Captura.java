/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoservicio.regiomontano.ventas;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jon
 */
@ApplicationScoped
public class Captura {
    @NotNull        
    private String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
