package javaapplication8;
import java.util.Collections;
import static javaapplication8.Phase2.AllEdges;
import static javaapplication8.Phase2.vertices;

public class Kruskal {

    // A class to represent a subset for
    // union-find
    class subset {

        int parent, rank;
    }

    // Creates a graph with V vertices and E edges
    Kruskal() {

    }

    // A utility function to find set of an
    // element i (uses path compression technique)
    int find(subset subsets[], int i) {
        // find root and make root as parent of i
        // (path compression)
        if (subsets[i].parent != i) {
            subsets[i].parent
                    = find(subsets, subsets[i].parent);
        }

        return subsets[i].parent;
    }

    // A function that does union of two sets
    // of x and y (uses union by rank)
    void Union(subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root
        // of high rank tree (Union by Rank)
        if (subsets[xroot].rank
                < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank
                > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } // If ranks are same, then make one as
        // root and increment its rank by one
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // The main function to construct MST using Kruskal's
    // algorithm
    void KruskalMST() {
        // Tnis will store the resultant MST
        Edge result[] = new Edge[vertices];

        // An index variable, used for result[]
        int e = 0;

        // An index variable, used for sorted edge
        for (int i = 0; i < vertices; ++i) {
            result[i] = new Edge();
        }

        // Step 1: Sort all the edges in non-decreasing
        // order of their weight. If we are not allowed to
        // change the given graph, we can create a copy of
        // array of edges
        Collections.sort(AllEdges);//instead of using array In the original code, we used All Edges from the main and sort it 
        // Allocate memory for creating V ssubsets
        subset subsets[] = new subset[vertices];
        for (int i = 0; i < vertices; ++i) {
            subsets[i] = new subset();
        }

        // Create V subsets with single elements
        for (int v = 0; v < vertices; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        int i = 0; // Index used to pick next edge
        // Number of edges to be taken is equal to V-1
        while (e < vertices - 1) {
            // Step 2: Pick the smallest edge. And increment
            // the index for next iteration
            Edge next_edge = AllEdges.get(i++);
            int x = find(subsets, next_edge.source);
            int y = find(subsets, next_edge.destination);

            // If including this edge does't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            if (i >= AllEdges.size() || e == result.length) {
                break;
            }
            // Else discard the next_edge
        }

        // print the contents of result[] to display
        // the built MST
//        System.out.println("Following are the edges in "
//                + "the constructed MST");
//        int minimumCost = 0;
//        for (i = 0; i < e; ++i) {
//            System.out.println(result[i].source + " -- "
//                    + result[i].destination
//                    + " == " + result[i].weight);
//            minimumCost += result[i].weight;
//        }
//        System.out.println("Minimum Cost Spanning Tree "
//                + minimumCost);
//    }
    }
}
