package cpw;

public class ShortestPath {
	

	// Utility function to find a minimum distance between ports and ship
	public int minDistance(double dist[], boolean sptSet[]) {
		
		double min = Double.MAX_VALUE;
		int min_index=-1;
		for( int v=0; v<dist.length; v++) {
			if(sptSet[v]== false && dist[v]<=min) {
				min=dist[v];
				min_index=v;
			}
		}
		return min_index;
	}
	
	//Utility function to printout results
	public void printShortest(double dist[]) {
		
		System.out.println("Port \t\t Distance from Source");
		for(int i=0; i<dist.length;i++) {
			System.out.println(i + " \t\t "+dist[i]);
		}
	}
	
	// dijkstra algorithms with adjacency matrix
	public void dijkstra(double graph[][], int src) {
		int V=graph.length;
		// The output array to hold the shortest path from src to i
		double dist[] = new double[V];
		// The array to hold whether True/False a port is included in shortest path tree
		boolean sptSet[] = new boolean[V];
		// Initialize all distances as inifinite and sptSet[] as false
		for (int i=0;i<V;i++) {
			dist[i]=Double.MAX_VALUE;
			sptSet[i]=false;
		}
		// Distance from source(Ship) from itself =0
		dist[src]=0;
		//Find the shortest path for all 
		for(int count=0; count<V-1;count++) {
			//Pick the smallest distance
			int u=minDistance(dist, sptSet);
			sptSet[u]=true;
			
			//Update the dist value to adjucent port of the picked node
			for (int v = 0; v < V; v++)
                // Update dist[v] only if is not in sptSet, there is an edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Double.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
		}
		// print the constructed distance array
		printShortest(dist);
		
	}
	
	public void nearestNeighbor(double graph[][], int src) {
		
		int V=graph.length;
		// The output array to hold the shortest path from src to i
		double dist[] = new double[V];
		// The array to hold whether True/False a port is included in shortest path tree
		boolean sptSet[] = new boolean[V];
		// Initialize all distances as inifinite and sptSet[] as false
		for (int i=0;i<V;i++) {
			dist[i]=Double.MAX_VALUE;
			sptSet[i]=false;
		}
		// Distance from source(Ship) from itself =0
		dist[src]=0;
		//Find the shortest path for all 
		for(int count=0; count<V-1;count++) {
			//Pick the smallest distance
			int u=minDistance(dist, sptSet);
			sptSet[u]=true;
			
			//Update the dist value to adjucent port of the picked node
			for (int v = 0; v < V; v++)
                // Update dist[v] only if is not in sptSet, there is an edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Double.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
		}
		// print the constructed distance array
		printShortest(dist);
		
	}
	
	public static double[][] createGraph(Port[] ports, Ship s) {
		int len=ports.length;
		double graph[][] = new double[len+1][len+1];
		int cnt=0;
		for(int i=0;i<len;i++) {
			graph[0][i+1]=s.getDistPort(ports[i]);
		}
		for(int j=0;j<len;j++) {
			Port p=ports[j];
			graph[j+1][0]=p.getDistShip(s);
			for(int i=0;i<len;i++) {
				//Dynamic Programming distance from i to j = j to i
				if(graph[i+1][j+1]!=0) {
					graph[j+1][i+1]=graph[i+1][j+1];
					cnt++;
				}
				graph[j+1][i+1]=p.getDist(ports[i]);
			}
		}
		System.out.println(cnt);
		return graph;
	}
	
	
	public static void printGraph(double[][] graph) {
		for(int j=0;j<graph[0].length;j++) {
			for(int i=0;i<graph[0].length;i++) {
				System.out.print(graph[j][i]+" ");
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		Ship X = new Ship("X", 100);
		X.updateLocation(0, 0);
		Port P1 =new Port("P1",0,10);
		Port P2 =new Port("P2",0,30);
		Port P3 =new Port("P3",0,20);
		Port P4 =new Port("P4",0,1000);
		Port P5 =new Port("P5",10,10);
		Port P6 =new Port("P6",50,60);
		Port P7 =new Port("P7",30,70);
		Port P8 =new Port("P8",0,80);
		Port P9 =new Port("P9",100,100);
		Port[] ports = new Port[] {P1,P2,P3,P4,P5,P6,P7,P8,P9};
		double[][] graph=createGraph(ports, X);
		printGraph(graph);
		
        ShortestPath Z = new ShortestPath();
        Z.dijkstra(graph, 0);
        
        
        ShortestPath Y = new ShortestPath();
        Y.nearestNeighbor(graph, 0);	
        
//	     /* Let us create the example graph discussed above */
//        double graphA[][] = new double[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
//                                      { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
//                                      { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
//                                      { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
//                                      { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
//                                      { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
//                                      { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
//                                      { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
//                                      { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
//        ShortestPath t = new ShortestPath();
//        t.dijkstra(graphA, 0);
		
	}
	
	
}
