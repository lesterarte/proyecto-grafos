
package proyecto_grafos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.*;
public class Proyecto_Grafos {
   
    
    

    public static void main(String[] args) {
       
       FileNameExtensionFilter filter = new  FileNameExtensionFilter("txt","txt");
       JFileChooser FSArchivo = new JFileChooser();
       FSArchivo.setFileFilter(filter);
       int opcion = FSArchivo.showDialog(FSArchivo, "aceptar");
       String nombreArchivo = FSArchivo.getSelectedFile().toString();
       Graph newGrafo = new SingleGraph("Amigos");
       newGrafo.addAttribute("ui.quality");
       newGrafo.addAttribute("ui.antialias");
       System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
       
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
                    if(searchNode(tokens[1],newGrafo) && 
                    searchNode(tokens[0],newGrafo)){
                        newGrafo.addEdge(tokens[0]+ "->"+tokens[1], tokens[0], tokens[1]);
                                                                  
                    }
                    
                }              
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            } 
        }
       
       //pesos
       for(Edge e:newGrafo.getEachEdge()) {
            e.addAttribute("length",1);
       }
       
       //labels en nodos
       for(Node n:newGrafo){
            n.addAttribute("ui.label", "            " + n.getId());
       }       
       
       //labels en edges
       for(Edge e:newGrafo.getEachEdge()){
            e.addAttribute("ui.label",e.getId());
       } 
       int opcionIngresada = 0;
       while(opcionIngresada != 3){
           opcionIngresada = Integer.parseInt(JOptionPane.showInputDialog(""
                   + "Que desea hacer?\n1.Ver amistad\n2. Ruta mas corta\n"
                   + "3. Visualizar Grafo"));
           
           if(opcionIngresada == 1){
               String verAmistadConsulta = JOptionPane.showInputDialog(""
                   + "Ingrese el nombre de quien desea ver amistad"); 
                   if(searchNode(verAmistadConsulta,newGrafo)){
                       String otraPersona = JOptionPane.showInputDialog(""
                   + "Ingrese el nombre de quien desea saber si es amigo de "+
                               verAmistadConsulta);
                       if(searchNode(otraPersona,newGrafo)){
                            if(frienship(verAmistadConsulta,otraPersona,newGrafo)){
                                JOptionPane.showMessageDialog(FSArchivo,verAmistadConsulta 
                                + " y " + otraPersona +  " SON AMIGOS!!!!" );                            
                            }
                            else{
                                JOptionPane.showMessageDialog(FSArchivo,verAmistadConsulta 
                                + " y " + otraPersona +  " NO SON AMIGOS :(  :(" );   
                            }   
                       }
                       else{
                           JOptionPane.showMessageDialog(FSArchivo, "No existe el vertice especificado");   
                       }
                       
                   }
                   else{
                       JOptionPane.showMessageDialog(FSArchivo, "No existe el vertice especificado");
                   }
           }
           if (opcionIngresada == 2) {
               String shortedPath = JOptionPane.showInputDialog(""
                   + "Ingrese el nombre de quien desea un Amigo");
               if(searchNode(shortedPath,newGrafo)){
                       String quererAmistad = JOptionPane.showInputDialog(""
                   + "Ingrese el nombre de quien desea ser Amigo "+
                               shortedPath);
                       if(searchNode(quererAmistad,newGrafo)){
                            Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");
                            dijkstra.init(newGrafo);
                            dijkstra.setSource(newGrafo.getNode(shortedPath));
                            dijkstra.compute(); 
                            Iterator<Path> pathIterator = dijkstra.getAllPathsIterator(newGrafo
				.getNode(quererAmistad));
                            
                            while (pathIterator.hasNext()){
                                JOptionPane.showMessageDialog(FSArchivo,"El camino mas rapido "
                                        + "para que "+shortedPath+" sea amig@ de "+
                                        quererAmistad+ " es:\n " +pathIterator.next());                                
                            }
                       }
                       else{
                           JOptionPane.showMessageDialog(FSArchivo, "La persona que dessea no se encuentra");   
                       }
                       
                   }
                   else{
                       JOptionPane.showMessageDialog(FSArchivo, "No existe el vertice especificado");
                   }     
           }
           if (opcionIngresada == 3) {
                newGrafo.display();
           }
        }
    }
    
    public static boolean searchNode(String node, Graph grafo){
        
        for(Node n:grafo){
            if(n.getId().equals(node)){
                return true;  
            }   
        }        
        return false;
        
    }
    
    public static boolean frienship(String persona1, String persona2, Graph grafo){
        
        for(Edge e:grafo.getEachEdge()) {
            if(e.getId().equals(persona1+"->"+persona2) || 
                    e.getId().equals(persona2+"->"+persona1)){
                return true;
            }            
        }
        return false; 
    }
    
    
    
}
