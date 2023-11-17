import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


public class ParisMetro {

    private Graph parisMetro;


    public ParisMetro(){
        this.parisMetro = readMetro("metro.txt");
    }

    public Graph getParisMetro(){
        return this.parisMetro;
    }

    public static Graph readMetro(String filename){
        String filePath = filename;
        Graph metro = new Graph();
        Boolean flag = false;
        

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            br.readLine();//just to get rid of the first line.
            String line;
            String[] parts;
            while((line = br.readLine()) != null){
                if(line.equals("$")){
                    flag = true;
                } else{
                    if(!flag){ // if dollar sign hasn't been reached.
                        parts = line.split(" ",2);
                        metro.addVertex(Integer.parseInt(parts[0]),parts[1]);
                    } else{ // if $ has been reached.
                        parts = line.split(" ");
                        Integer v1 = Integer.parseInt(parts[0]);//index of vertex 1
                        Integer v2 = Integer.parseInt(parts[1]);//index of vertex 2
                        Integer d = Integer.parseInt(parts[2]); //distance
                        metro.addEdge(metro.getVertices().get(v1), metro.getVertices().get(v2), d);
                    }    

                }
                
            }

        } catch(IOException e){
            e.printStackTrace();
        }

        return metro;
    }

    //all thats left are the algorithms for 2i and 2ii + sample outputs.

    public ArrayList<Vertex> DFS2I(Vertex N1, ArrayList<Vertex> visitedVertices){
        
        for(Edge e: N1.getEdges()){
            if(e.getWeight() != -1){
                Vertex nextStation  = e.getEnd();

                if(!visitedVertices.contains(nextStation)){
                    visitedVertices.add(nextStation);
                    this.DFS2I(nextStation, visitedVertices);
                }
            }
        }
        return visitedVertices;
    
    }

     public ArrayList<Vertex> getShortestPath2II(Vertex N1, Vertex N2) {
        //get all the trains on the line. If n2 is not on the line
        //why is the vertex setting its distance to 0 if its is the edges that have a distance?
        //It is because, when you are doing dijkstra's alggorithm, there are 2 types of values.
        //the weights of the edges and the weights of the vertices themselves.
        N1.setDistance(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(getParisMetro().getVertices());

        while(!priorityQueue.isEmpty()){
            Vertex current = priorityQueue.poll();
            for(Edge edge : current.getEdges()){
                Vertex neighbor = edge.getEnd();
                Integer weight = (edge.getWeight() == -1)? 90 : edge.getWeight(); // -1 CHECK IS HERE TO MAKE SURE 90 IS ADDED TO ADDRESS WALKING.
                int newDistance = current.getDistance() + weight;//Have to do a -1 check somehow
                //it is not vertices that have -1 but edges. so if the edge's weight is -1
                
                if(newDistance < neighbor.getDistance()){
                    priorityQueue.remove(neighbor);
                    neighbor.setDistance(newDistance);
                    neighbor.setPrevious(current);
                    priorityQueue.add(neighbor);
                }
            }
        }

        //after dijkstra is done, get the path in reverse order. Collections.reverse goes crazy.
    
        ArrayList<Vertex> path = new ArrayList<>();
        for (Vertex vertex = N2; vertex != null; vertex = vertex.getPrevious()) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }


    public ArrayList<Vertex> SPath2Broken(Vertex N1, Vertex N2, Vertex N3){//N3 is the endpoint of the broken line.
        //Do I just go through and set all the edges
        ArrayList<Vertex> brokenLine = DFS2I(N1, new ArrayList<Vertex>());
         N1.setDistance(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(getParisMetro().getVertices());

        while(!priorityQueue.isEmpty()){//how do I edit this code so it accounts for the fact that one of the statio
            Vertex current = priorityQueue.poll();
            for(Edge edge : current.getEdges()){
                Vertex neighbor = edge.getEnd();
                Integer weight = (edge.getWeight() == -1)? 90 : edge.getWeight(); // -1 CHECK IS HERE TO MAKE SURE 90 IS ADDED TO ADDRESS WALKING.
                int newDistance = current.getDistance() + weight;//Have to do a -1 check somehow
                //it is not vertices that have -1 but edges. so if the edge's weight is -1
                
                if(newDistance < neighbor.getDistance()){
                    priorityQueue.remove(neighbor);
                    neighbor.setDistance(newDistance);
                    neighbor.setPrevious(current);
                    priorityQueue.add(neighbor);
                }
            }
        }

        //after dijkstra is done, get the path in reverse order. Collections.reverse goes crazy.
    
        ArrayList<Vertex> path = new ArrayList<>();
        for (Vertex vertex = N2; vertex != null; vertex = vertex.getPrevious()) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    } 

    public String bonus(){
        String message = "Didn't do the bonus!";
        return message;
    }


    public static void main(String[] args){
        ParisMetro parisMetro = new ParisMetro();
        
        if(args.length == 1){
            Vertex n1Vertex = parisMetro.getParisMetro().getVertexByValue(args[0]);
            ArrayList<Vertex> visitedVertices = new ArrayList<>();
            visitedVertices.add(n1Vertex);
            String message = "[LINE:   ";
            for(Vertex v: parisMetro.DFS2I(n1Vertex,visitedVertices)){
                message += v.getNumber().toString() + " " + v.getStation() + "  ***  ";
            }
            System.out.println(message + "END OF LINE] ");
         
        } else if(args.length == 2){
            Integer travelTime = 0;//for getting total travel time;
            Vertex n1Vertex = parisMetro.getParisMetro().getVertexByValue(args[0]);
            Vertex n2Vertex = parisMetro.getParisMetro().getVertexByValue(args[1]);
            String message = "[SHORTEST PATH LINE: ";
            ArrayList<Vertex> path = parisMetro.getShortestPath2II(n1Vertex, n2Vertex);
            travelTime = path.get(path.size()-1).getDistance();
            for(Vertex v: path){
                message += v.getNumber().toString() + " " + v.getStation() + "  ***  ";
            }
            System.out.println(message + "END OF LINE]  Time Taken: " + travelTime);

        } else if(args.length == 3){
            // System.out.println(parisMetro.SPath2Broken(args[0],args[1],args[2]));
        } else{
            System.out.println(parisMetro.bonus());
        }

        
    }


    
}
