import java.util.ArrayList;

/**
 * Graph
 */
public class Graph {

    private ArrayList<Vertex> vertices;
    

    public Graph(){
        this.vertices = new ArrayList<Vertex>();
        
    }

    public void addVertex(Integer number, String station){
        Vertex newVertex = new Vertex(number,station);
        this.vertices.add(newVertex); 
    }

    public void addEdge(Vertex vertex1, Vertex vertex2, Integer weight){
        vertex1.addEdge(vertex2, weight);
    }



    public void removeVertex(Vertex vertex){
        this.vertices.remove(vertex);
    }

    public ArrayList<Vertex> getVertices(){
        return this.vertices;
    }


    public Vertex getVertexByValue(String value){
        for(Vertex v: this.vertices){
            if(v.getNumber() == Integer.parseInt(value)){
                return v;
            }
        }
        return null;
        
    }

    public void print(){
        for(Vertex v: this.vertices){
            v.print();
        }
    }



}