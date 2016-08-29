
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
       String nombreArchivo = FSArchivo.getSelectedFile().toString();
       Graph newGrafo = new SingleGraph("Amigos");
       if(opcion == JFileChooser.APPROVE_OPTION){
           try {
                //abrir archivo
                FileReader archivo = new FileReader(nombreArchivo);
                BufferedReader buffer = new BufferedReader(archivo);
                String linea;
                
                while((linea = buffer.readLine()) != null){
                  
                   String[] tokens = linea.split(",");
                   if(!(searchNode(tokens[0],newGrafo))){
                       newGrafo.addNode(tokens[0]);
                   }
                    if(!(searchNode(tokens[1],newGrafo))){
                       newGrafo.addNode(tokens[1]);
                   }
                   
                }              
            } catch (Exception e) {
                System.err.println("Problemas al leer archivo");
            } 
          
        }
       newGrafo.display();
      
    }
    
    public static boolean searchNode(String node, Graph grafo){
        
        for(Node n:grafo){
            if(n.getId().equals(node)){
                return true;  
            }   
        }        
        return false;
        
    }
    
}
