import java.io.*;
import java.util.ArrayList;

public class Controladora {

    public Controladora(){

    }
    public void crearArchivoSiNoExiste(String archivo) {
        File file = new File(archivo + ".json");
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write("[]"); // Crear un JSON vacío
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void grabar(ArrayList<Nave> listaNaves) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("listaNaves.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Nave nave : listaNaves) {
                objectOutputStream.writeObject(nave);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();

                if (objectOutputStream != null)
                    objectOutputStream.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    public ArrayList<Nave> leer() {
        ArrayList<Nave> listaNaves = new ArrayList<>();

        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream("listaNaves.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

            while (true) {
                Nave aux = (Nave) objectInputStream.readObject();
                listaNaves.add(aux);
            }
        } catch (EOFException ex) {
            System.out.println("FIN de ARCHIVO");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();

                if (objectInputStream != null)
                    objectInputStream.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

        return listaNaves;
    }
}