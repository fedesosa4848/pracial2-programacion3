import Excepciones.ExisteException;
import Generic.Conteiner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class Personaje {

    private String nombre;
    private int edad;
    private boolean jedi;
    private String planeta_nacimiento;
    private Maestro maestro;
    private ArrayList<Amigo> amigos;
    private ArrayList<Evento> eventos;

    public Personaje(String nombre, int edad, boolean jedi, String planeta_nacimiento, Maestro maestro, ArrayList<Amigo> amigos, ArrayList<Evento> evento) {
        this.nombre = nombre;
        this.edad = edad;
        this.jedi = jedi;
        this.planeta_nacimiento = planeta_nacimiento;
        this.maestro = maestro;
        this.amigos = amigos;
        this.eventos = evento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getPlaneta_nacimiento() {
        return planeta_nacimiento;
    }

    public Maestro getMaestro() {
        return maestro;
    }

    public boolean isJedi() {
        return jedi;
    }

    public ArrayList<Amigo> getAmigos() {
        return amigos;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public static Conteiner<String,Personaje> FromJson(String archivo) throws JSONException {
        // Lee el contenido del archivo y convierte a JSONObject
        String contenido = JsonUtiles.leer(archivo);
        JSONObject jsonObject = new JSONObject(contenido);

        // Obtiene el array "personajes"
        JSONArray jsonArray = jsonObject.getJSONArray("personajes");
        Conteiner<String,Personaje> conteiner = new Conteiner<>();
        //ArrayList<Personaje> personajes = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

            String nombre = jsonObject1.getString("nombre");
            int edad = jsonObject1.getInt("edad");
            boolean jedi = jsonObject1.getBoolean("jedi");
            String planetaNacimiento = jsonObject1.getString("planeta_nacimiento");

            JSONObject jsonMaestro = jsonObject1.getJSONObject("maestro");
            String nombreMaestro = jsonMaestro.getString("nombre");
            boolean esJediMaestro = jsonMaestro.getBoolean("es_jedi");
            JSONArray jsonHabilidadesMaestro = jsonMaestro.getJSONArray("habilidades");

            ArrayList<String> habilidadesMaestro = new ArrayList<>();
            for (int j = 0; j < jsonHabilidadesMaestro.length(); j++) {
                habilidadesMaestro.add(jsonHabilidadesMaestro.getString(j));
            }

            Maestro maestro = new Maestro(nombreMaestro, esJediMaestro, habilidadesMaestro);

            JSONArray jsonAmigos = jsonObject1.getJSONArray("amigos");
            ArrayList<Amigo> amigos = new ArrayList<>();

            for (int j = 0; j < jsonAmigos.length(); j++) {
                JSONObject jsonAmigo = jsonAmigos.getJSONObject(j);
                String nombreAmigo = jsonAmigo.getString("nombre");
                boolean pilotoAmigo = jsonAmigo.getBoolean("piloto");

                JSONObject jsonNave = jsonAmigo.optJSONObject("nave");
                Nave nave = null;
                if (jsonNave != null) {
                    String nombreNave = jsonNave.getString("nombre");
                    String modeloNave = jsonNave.getString("modelo");
                    nave = new Nave(nombreNave, modeloNave);
                }
                Amigo amigo = new Amigo(nombreAmigo, pilotoAmigo, nave);
                amigos.add(amigo);
            }

            JSONArray jsonEventos = jsonObject1.getJSONArray("eventos");
            ArrayList<Evento> eventos = new ArrayList<>();

            for (int j = 0; j < jsonEventos.length(); j++) {
                JSONObject jsonEvento = jsonEventos.getJSONObject(j);
                String nombreEvento = jsonEvento.getString("nombre");
                int anioEvento = jsonEvento.getInt("anio");
                boolean ganadaEvento = jsonEvento.getBoolean("ganada");
                Evento evento = new Evento(nombreEvento, anioEvento, ganadaEvento);
                eventos.add(evento);
            }

            Personaje personaje = new Personaje(nombre, edad, jedi, planetaNacimiento, maestro, amigos, eventos);
            //personajes.add(personaje);
            try{
                conteiner.agregar(personaje.getNombre(),personaje);
            }catch (ExisteException e){
                e.printStackTrace();
            }
        }
        return conteiner;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "nombre='" + getNombre() + '\'' +
                ", edad=" + getEdad() +
                ", Es jedi ? " + jedi +
                ", planeta_nacimiento='" + getPlaneta_nacimiento() + '\'' +
                ", maestro=" + getMaestro() +
                ", amigos=" + getAmigos() +
                ", evento=" + getEventos() +
                '}';
    }
}
