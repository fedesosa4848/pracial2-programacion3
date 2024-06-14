import java.util.ArrayList;

public class Maestro {

    private String nombre;
    private boolean es_jedi;
    private ArrayList<String> habilidades;

    public Maestro(String nombreMaestro, boolean esJediMaestro, ArrayList<String> habilidadesMaestro) {
        this.nombre = nombreMaestro;
        this.es_jedi = esJediMaestro;
        this.habilidades = habilidadesMaestro;
    }

    @Override
    public String toString() {
        return "Maestro{" +
                "nombre='" + nombre + '\'' +
                ", es_jedi=" + es_jedi +
                ", habilidades=" + habilidades +
                '}';
    }
}
