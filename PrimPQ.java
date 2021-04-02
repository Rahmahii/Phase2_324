package javaapplication8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import static javaapplication8.Phase2.vertices;

public class PrimPQ {

    public class Node implements Comparable<Node> {

        int vertice, key;

        Node(int vertice, int key) {
            this.vertice = vertice;
            this.key = key;
        }
//==============================

        @Override
        public int compareTo(Node o) {
            return this.key - o.key;
        }
    }
//======================= array list for all nodes 

    public class AdjList {

        ArrayList<Node> nodes;
    }
//======================= create graph 

    class PQGraph {
        AdjList[] adjLists;
    }

     PQGraph createGraph() {
        PQGraph graph = new PQGraph();
        graph.adjLists = new AdjList[vertices];
        for (int i = 0; i < vertices; i++) {
            AdjList adjList = new AdjList();
            adjList.nodes = new ArrayList<Node>(); //initialize its node list too
            graph.adjLists[i] = adjList;
        }
        return graph;
    }

     void addEdge(PQGraph graph, int src, int dest, int key) {
        Node srcNode = new Node(src, key);
        Node destNode = new Node(dest, key);
        graph.adjLists[src].nodes.add(destNode);
        graph.adjLists[dest].nodes.add(srcNode);
    }

//        public void printGraph(PQGraph graph) {
//           
//            for (int i = 0; i < graph.V; i++) {
//                System.out.print(i + " ->");
//                for (Node node : graph.adjLists[i].nodes) {
//                    System.out.print(" " + node.vertice);
//                    
//                }
//                System.out.println();
//            }
//        }
     void getPrimsMST(PQGraph graph) {
        Node keys[] = new Node[vertices];
        int parent[] = new int[vertices];
        boolean mstSet[] = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            keys[i] = new Node(i, Integer.MAX_VALUE);
            parent[i] = -1;
            mstSet[i] = false;
        }
        keys[0].key = 0;
        Queue<Node> pQueue = new PriorityQueue<>();
        pQueue.addAll(Arrays.asList(keys));

        while (pQueue.size() > 1) {
            Node u = pQueue.remove();
            mstSet[u.vertice] = true;

            for (Node node : graph.adjLists[u.vertice].nodes) {

                if (mstSet[node.vertice] == false && node.key < keys[node.vertice].key) {
                    pQueue.remove(keys[node.vertice]); //remove that node from q

                    keys[node.vertice].key = node.key; //change key
                    parent[node.vertice] = u.vertice;

                    pQueue.add(keys[node.vertice]); //add back
                    //remove add can me made single function by using a visited flag 
                    //remove_add() in O(lg(n))
                }

            }
        }
        // print_mst(keys, parent, graph);

    }
}
//
//        public void print_mst(Node[] keys, int[] parent, PQGraph graph) {
//            int sum=0;
//            for (int i = 1; i < graph.V; i++) {
//                System.out.println(parent[keys[i].vertice] + "-" + keys[i].vertice + " " + keys[i].key);
//                sum+=keys[i].key;
//            }
//            System.out.println("path cost is :"+sum);
//        }
//    }  

