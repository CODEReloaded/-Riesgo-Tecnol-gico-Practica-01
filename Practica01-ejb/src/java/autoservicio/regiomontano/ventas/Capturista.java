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
public class Capturista {
    @NotNull
    private String nombres;
    @NotNull
    private String apaterno;
    @NotNull
    private String amaterno;
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }
    
    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }
}
