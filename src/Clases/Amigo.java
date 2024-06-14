public class Amigo{
    private String nombre;
    private boolean piloto;
    private Nave nave;

    public Amigo(String nombreAmigo, boolean pilotoAmigo, Nave nave) {
        this.nombre = nombreAmigo;
        this.piloto = pilotoAmigo;
        this.nave = nave;
    }

    public Nave getNave() {
        return nave;
    }

    @Override
    public String toString() {
        return "Amigo{" +
                "nombre='" + nombre + '\'' +
                ", piloto=" + piloto +
                ", nave=" + nave +
                '}';
    }
}