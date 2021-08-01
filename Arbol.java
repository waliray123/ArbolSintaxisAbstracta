import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class Arbol {
    
    private String strG;
    private Nodo nodoPadre;

    public void generarArbol(Nodo nodoPadre){        
        this.nodoPadre = nodoPadre;
        generarStr();
        escribirGraphviz();
    }

    private void generarStr(){
        strG = "digraph G { \n";


        strG += "node"+nodoPadre.getNumNodo() + "[label = \""+ nodoPadre.getValor()+"\"];\n";
        List<Nodo> nodosHijo = nodoPadre.getNodosHijo();
        for (Nodo nodo : nodosHijo) {
            insertarNodo(nodo,nodoPadre);
        }

        strG += "}";
    }

    private void insertarNodo(Nodo nodo, Nodo nodoP){
        strG += "node"+nodo.getNumNodo() + "[label = \""+ nodo.getValor()+"\"];\n";
        strG += "node"+nodoP.getNumNodo() + " -> " +"node"+nodo.getNumNodo() +";\n";
        List<Nodo> nodosHijo = nodo.getNodosHijo();
        for (Nodo nodoI : nodosHijo) {
            insertarNodo(nodoI,nodo);
        }
    }

    private void escribirGraphviz(){
        try {        
            File archivo = new File("arbol.dot");            
            FileWriter escribir = new FileWriter(archivo, true);
            escribir.write(strG);            
            escribir.close();
        }
        catch (Exception e) {
            System.out.println("Error al escribir");
        }
    }
}
