package javaapplication8;

import java.util.*;
import javaapplication8.PrimPQ.PQGraph;

public class Phase2 {

    static List<Edge> AllEdges = new ArrayList<Edge>();//it contain all edges on the system
    static List<List< Edge>> adjacencyList;//it is a list of all vertices with all vertices that conected with it 
    static int vertices;//number of the vertices in all system
    static int edges;//number of the edges in all system

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose the number of cases :");//From 1 to 10 since we have 10 cases
        int choice = input.nextInt();//from user
        chooseCase(choice);//chooseCase function gives a number for vertices and edges
        //after after specify the numbers for vertices and edges we make instance of our algorithms
        PrimPQ PQ = new PrimPQ();//for prim using Priority Queue
        PQGraph graphPQ = PQ.createGraph();//to use it in make_graph function for prim using Priority Queue
        Kruskal kruskal = new Kruskal();//for Kruskal 
        PrimHeap Pheap = new PrimHeap();//for prime using heap

        make_graph(Pheap, graphPQ, PQ);//to generate one graph for whole system
        System.out.println("");

        ////////////////////////////////////////calculation of run time for prim using Priority Queue
        long startTimePQ = System.nanoTime();//start time
        PQ.getPrimsMST(graphPQ);//calling for prim using Priority Queue to execute 
        long EndTimePQ = System.nanoTime();//End time
        long timeElapsedPQ = EndTimePQ - startTimePQ;//run time for algorithm = end time-start time 
        System.out.println(
                "Execution time in nanoseconds for Prim using Priority Queue : " + timeElapsedPQ);
        ////////////////////////////////////////calculation of run time for kruskal
        long startTimeKrus = System.nanoTime();//start time
        kruskal.KruskalMST();//calling for kruskal to execute
        long EndTimeKrus = System.nanoTime();//End time
        long timeElapsedKrus = EndTimeKrus - startTimeKrus;//run time for algorithm = end time-start time
        System.out.println(
                "Execution time in nanoseconds for kruskal : " + timeElapsedKrus);
        ////////////////////////////////////////calculation of run time for prime using heap
        long startTimeHeap = System.nanoTime();//start time
        Pheap.primMST();//calling for prime using heap to execute
        long EndTimeHeap = System.nanoTime();//End time
        long timeElapsedHeap = EndTimeHeap - startTimeHeap;//run time for algorithm = end time-start time
        System.out.println(
                "Execution time in nanoseconds for Prim using Min Heap : " + timeElapsedHeap);
    }

     static void make_graph(PrimHeap Pheap, PQGraph graphPQ, PrimPQ PQ) {

        Random random = new Random();//to get random vertice
          Edge n;//using later
          
        adjacencyList = new ArrayList<>();
        //each vertics has list of vertices
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
      //creat edges between vertices
        for (int i = 0; i < edges; i++) {
            
            // randomly select two vertices to create an edge between them
            int v = random.nextInt(vertices);
            //priority choice of vertices which did not connect with any vertice to avoid unconnected graph
            for (int j = 0; j < vertices; j++) {
                if (adjacencyList.get(j).isEmpty()) {
                    v = j;
                }
            }//if no one is empty then get the random one
            int w = random.nextInt(vertices);
            int t = 0;
            for (int j = 0; j < AllEdges.size(); j++) {
                //if these two vertices connected with each other in any way or they make self loop then wont connect them with new edge 
                if ((AllEdges.get(j).source == v && AllEdges.get(j).destination == w) || (AllEdges.get(j).source == w && AllEdges.get(j).destination == v) || (v == w)) {
                    t = -1;
                    break;
                }
            }
            if (t == -1) {//if the vertices can't connect ,then repeat loop with new choice of vertices
                i = i - 1;//don't count this loop 
                continue;
            }
            //random weight for edge
            int rand = getRandomNumber();
            // add an edge between them
            n = new Edge(v, w, rand);

            adjacencyList.get(v).add(n);
            PQ.addEdge(graphPQ, v, w, rand);//add the edge for prim using Priority Queue
            Pheap.addEdge(v, w, rand);//add the edge for prim using heap
            AllEdges.add(n);//also it used in kruskal
        }

//        for (int i = 0; i < AllEdges.size(); i++) {
//            System.out.print("(" + AllEdges.get(i).source + ")TO(" + AllEdges.get(i).destination + "):(" + AllEdges.get(i).weight + ")---");
//        }
    }
//---------------------------------------------------------------------------------------     
//function to generate random number from 1 to 10 for Edges weight
    public static int getRandomNumber() {
        int x = (int) ((Math.random() * ((10 - 1) + 1))) + 1;
        return x;
    }

//---------------------------------------------------------------------------------------
    //giveing a number for vertices and edges depend on the chosen case 
    public static void chooseCase(int c) {
        switch (c) {
            case 1:
                vertices = 1000;
                edges = 10000;
                break;
            case 2:
                vertices = 1000;
                edges = 15000;
                break;
            case 3:
                vertices = 1000;
                edges = 25000;
                break;
            case 4:
                vertices = 5000;
                edges = 15000;
                break;
            case 5:
                vertices = 5000;
                edges = 25000;
                break;
            case 6:
                vertices = 10000;
                edges = 15000;
                break;
            case 7:
                vertices = 10000;
                edges = 25000;
                break;
            case 8:
                vertices = 20000;
                edges = 200000;
                break;
            case 9:
                vertices = 20000;
                edges = 300000;
                break;
            case 10:
                vertices = 50000;
                edges = 1000000;
                break;
            default:
                System.out.println("There is no case matched");
        }
    }
}
