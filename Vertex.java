import java.util.ArrayList;

public class Vertex implements Comparable<Vertex>{
    private Integer number;
    private String station;
    private int distance = Integer.MAX_VALUE;
    private ArrayList<Edge> edges;
    private Vertex previous;

    public Vertex(Integer number, String station){
        this.number = number;
        this.station = station;
        this.edges = new ArrayList<Edge>();
    }


    public void addEdge(Vertex endStation, Integer distance){
        this.edges.add(new Edge(this, endStation, distance));

    }

    public String getStation(){
        return this.station;
    }

    public int getDistance(){
        return distance;
    }


    public void setDistance(int distance){
        this.distance = distance;
    }

    public Integer getNumber(){
        return this.number;
    }

    public ArrayList<Edge> getEdges(){
        return this.edges;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    public void print(){//For printing out a vertex and all its edges.
        String message = "";

        if(this.edges.size() == 0){
            System.out.println(this.number + " " + this.station + " -->");
            return;
        }

        for(int i =  0; i < this.edges.size(); i++){
            if(i==0){
                message += this.number + " " + this.edges.get(i).getStart().station + " -->  ";
            }

            message += this.edges.get(i).getEnd().getNumber() + " " + this.edges.get(i).getEnd().station;

            
            message += " (" + this.edges.get(i).getWeight() + ")";
            

            if(i != this.edges.size() - 1){
                message += ", ";
            }
        }
        
        System.out.println(message); 
    }

    @Override
    public int compareTo(Vertex other) {//helps for easy priority queue implementation.
        return Integer.compare(this.distance, other.distance);
    }

     @Override
    public String toString() {
        String message = "";
        message += getNumber() + " " + getStation();
        return message;
    }


    
}
