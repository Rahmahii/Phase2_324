package javaapplication1;

public class dijkstraPhase2 {

    public void dijkstra(int[][] graph, String sourceArr[]) {

        String source = sourceArr[0];//jeddah
        int count = graph.length;
        boolean[] visitedVertex = new boolean[count];
        int[] distance = new int[count];
        for (int i = 0; i < count; i++) {
            visitedVertex[i] = false;
            distance[i] = Integer.MAX_VALUE;
        }

        // Distance of self loop is zero
        distance[0] = 0;
        for (int i = 0; i < count; i++) {

            // Update the distance between neighbouring vertex and source vertex
            int u = findMinDistance(distance, visitedVertex);
            visitedVertex[u] = true;

            // Update all the neighbouring vertex distances
            for (int v = 0; v < count; v++) {
                if (!visitedVertex[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v])) {
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }
        for (int i = 0; i < distance.length; i++) {
            System.out.println(String.format("Distance from %s to %s is %s", source, sourceArr[i], distance[i]));
        }
       
    }

    // Finding the minimum distance
    private static int findMinDistance(int[] distance, boolean[] visitedVertex) {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visitedVertex[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minDistanceVertex = i;
            }
        }
        return minDistanceVertex;
    }

    public static void main(String[] args) {
        int graph[][] = new int[][]{//all distances in the same order with source array
            {0, 79, 420, 949, 1343, 167, 625, 1024, 863, 777, 710, 905},
            {79, 0, 358, 870, 1265, 88, 627, 1037, 876, 790, 685, 912},
            {420, 358, 0, 848, 1343, 446, 985, 679, 518, 432, 1043, 1270},
            {949, 870, 848, 0, 395, 782, 1064, 1304, 330, 640, 1272, 950},
            {1343, 1265, 1343, 395, 0, 1177, 1495, 1729, 725, 1035, 1667, 1345},
            {167, 88, 446, 782, 1177, 0, 561, 1204, 936, 957, 763, 864},
            {625, 627, 985, 1064, 1459, 561, 0, 1649, 1488, 1402, 202, 280},
            {1024, 1037, 679, 1304, 1729, 1204, 1649, 0, 974, 664, 1722, 1929},
            {863, 876, 518, 330, 725, 936, 1488, 974, 0, 310, 1561, 1280},
            {777, 798, 432, 640, 1035, 957, 1482, 664, 974, 0, 1475, 1590},
            {710, 685, 1843, 1272, 1667, 763, 202, 1722, 1561, 1475, 0, 482},
            {905, 912, 1270, 950, 1345, 864, 280, 1929, 1280, 1590, 482, 0}
        };
        String source[] = new String[12];//all cities in the same order with distances in array graph 
        source[0] = "Jeddah";
        source[1] = "Makkah";
        source[2] = "Madinah";
        source[3] = "Riyadh";
        source[4] = "Dammam";
        source[5] = "Taif";
        source[6] = "Abha";
        source[7] = "Tabuk";
        source[8] = "Qasim";
        source[9] = "Hail";
        source[10] = "Jizan";
        source[11] = "Najran";

        dijkstraPhase2 T = new dijkstraPhase2();
        T.dijkstra(graph, source);
    }
}
