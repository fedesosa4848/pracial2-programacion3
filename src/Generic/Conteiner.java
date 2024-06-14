package Generic;

import Excepciones.ExisteException;
import Excepciones.NoExisteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Conteiner <String,K> {

    private HashMap<String, K> conteiner;

    public Conteiner() {
        this.conteiner = new HashMap<>();
    }

    public void agregar(String key, K value) throws ExisteException {
        if (conteiner.containsKey(key)) {
            throw new ExisteException("No se puede agregar " + value.toString());
        }
        conteiner.put(key, value);

    }

    public HashMap<String, K> obtenerMapa() {
        return conteiner;
    }

    public K obterPorNombre(String key) throws NoExisteException {
        if (!conteiner.containsKey(key)) {
            throw new NoExisteException("No se encontro el personaje " + key);
        }
        return conteiner.get(key);
    }

    public void eliminar(String key) throws NoExisteException{
        if(!conteiner.containsKey(key)){
            throw new NoExisteException("No se puede eliminar porque no esta en el mapa");
        }
        conteiner.remove(key);
    }

    public void limpiarTodo (){
        conteiner.clear();
    }

    //Nose si listar con nombres o listar todos por las dudas hice las dos
    public ArrayList<String> mostrarPorNombre() {

        ArrayList<String> listaPorNombre = new ArrayList<>();

        Iterator<Map.Entry<String, K>> iterator = conteiner.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, K> entry = iterator.next();
            listaPorNombre.add(entry.getKey());
        }

        return listaPorNombre;
    }


}
