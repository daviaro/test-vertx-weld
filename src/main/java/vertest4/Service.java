package vertest4;

import java.io.Serializable;

/**
 * Created by leydi on 10/06/17.
 */
public class Service implements Serializable {

    private String nombre;
    private String apellido;

    public Service(){
        this.setNombre("David");
        this.setApellido("Aroca");
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
