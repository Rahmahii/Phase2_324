package javaapplication8;
 public class Edge implements Comparable<Edge> {

        public int source;
        public int destination;
        public int weight;

        public Edge() {
        }

        public Edge(int src, int node, int cost) {
            this.destination = node;
            this.weight = cost;
            this.source = src;
        }

        @Override
        public int compareTo(Edge compare) {
            return this.weight - compare.weight;
        }
    }