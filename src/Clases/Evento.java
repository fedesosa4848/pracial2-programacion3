public class Evento{
    private String nombreEvento;
    private int anio;
    private boolean granadaEvento;

    public Evento(String nombreEvento, int anioEvento, boolean ganadaEvento) {
        this.nombreEvento=nombreEvento;
        this.anio = anioEvento;
        this.granadaEvento = ganadaEvento;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nombreEvento='" + nombreEvento + '\'' +
                ", anio=" + anio +
                ", granadaEvento=" + granadaEvento +
                '}';
    }
}