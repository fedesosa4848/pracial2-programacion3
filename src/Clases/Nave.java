import java.io.Serializable;

public class Nave implements Serializable {
    private String nombre;
    private String modelo;

    public Nave (String nombre,String modelo){
        this.nombre = nombre;
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Nave{" +
                "nombre='" + nombre + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
