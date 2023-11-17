public class Edge {
    private Vertex startStation;
    private Vertex endStation;
    private Integer distance;

    public Edge(Vertex startStation, Vertex endStation, Integer distance){
        this.startStation = startStation;
        this.endStation = endStation;
        this.distance = distance;

    }

    public Vertex getStart(){
        return this.startStation;
    }

    public Vertex getEnd(){
        return this.endStation;
    }

    public Integer getWeight(){
        return this.distance;
    }
}
