import Excepciones.ExisteException;
import Excepciones.NoExisteException;
import Generic.Conteiner;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/*
Nota :
    La funcion de listarYcargarLa hice junta para simplificarme un poco la vida porque no llegaba
    Sry for this mess up T_T
 */

public class Main {
    static Conteiner<String,Personaje> conteiner= cargarMapaFromJson();
    static Controladora controladora = new Controladora();

    public static void main(String[] args) {

        listarYcargarArchivo();
        cargarPesonaje();
        buscar();
        eliminar();
        limpiarTodo();


        ArrayList<Nave> naves = controladora.leer();
        System.out.println(naves);



    }

    public static void limpiarTodo(){
        conteiner.limpiarTodo();
    }

    public static void listarYcargarArchivo(){
        Iterator<Map.Entry<String,Personaje>> iteratorMapa = conteiner.obtenerMapa().entrySet().iterator();
        ArrayList<Nave> naves = new ArrayList<>();
        while (iteratorMapa.hasNext()){
            Map.Entry<String,Personaje> entradaMapa = iteratorMapa.next();

            System.out.printf("Clave: %s Valor: %s%n", entradaMapa.getKey(), entradaMapa.getValue());

            Personaje aux = entradaMapa.getValue(); //Agarro el personaje
            ArrayList<Amigo> amigos = aux.getAmigos(); //Agarro a los amigos que los amigos tienen Naves

            Nave auxNave = null; //Dejlaro una nave aux

            for (Amigo amigo : amigos){ //Para cada amigo obtengo la nave
                auxNave = amigo.getNave();
                naves.add(auxNave); //La agrego al array
            }
            controladora.grabar(naves); //La grabo
        }
    }

    public static void buscar(){
        Personaje abuscar = null;
        try
        {
            abuscar = conteiner.obterPorNombre("Yoda");
            System.out.println(abuscar);
        }catch (NoExisteException e){
            System.out.println(e.getMessage());
        }
    }

    public static void eliminar(){
        try{
            conteiner.eliminar("Yoda");
        }catch (NoExisteException e){
            System.out.println(e.getMessage());
        }
    }

    public static void cargarPesonaje(){
        ArrayList<String> habilidades = new ArrayList<>();
        habilidades.add("comer a reventar");
        habilidades.add("dormir");

        Nave nave = new Nave("nombreNave","12345");

        Maestro maestro = new Maestro("nombreMaestro",true,habilidades);

        ArrayList<Amigo> amigos = new ArrayList<>();
        Amigo amigo = new Amigo("nombreAmigo",true,nave);
        amigos.add(amigo);

        ArrayList<Evento>eventos = new ArrayList<>();
        Evento evento = new Evento("nombreEvento",3541,false);
        eventos.add(evento);

        Personaje personaje = new Personaje("nombrePj",123,true,"apePlanet",maestro,amigos,eventos);

        try{
            conteiner.agregar(personaje.getNombre(),personaje);
        }catch (ExisteException e){
            System.out.println(e.getMessage());
        }
    }

    public static Conteiner<String,Personaje> cargarMapaFromJson(){
        Conteiner <String,Personaje> conteiner = new Conteiner<>();
        try {
            conteiner = Personaje.FromJson("archivo");
            System.out.println("Personajes");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return conteiner;
    }
}
