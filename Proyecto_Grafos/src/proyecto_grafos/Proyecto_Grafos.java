
package proyecto_grafos;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.*;
public class Proyecto_Grafos {
   
    
    

    public static void main(String[] args) {
       
       FileNameExtensionFilter filter = new  FileNameExtensionFilter("txt","txt");
       JFileChooser FSArchivo = new JFileChooser();
       FSArchivo.setFileFilter(filter);
       int opcion = FSArchivo.showDialog(FSArchivo, "aceptar");
       
        
        
       Graph newGrafo = new SingleGraph("Grafo 1");  
       newGrafo.addNode("A");
       newGrafo.addNode("B");
       newGrafo.addEdge("AB", "A", "B");
       newGrafo.addNode("C");
           
    }
    
}
