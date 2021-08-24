package cpw;

public class TSP {
	

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
	public void printShortest(double dist[], String node[]) {
		int ttl=0;
		for(int i=0; i<dist.length-1;i++) {
			System.out.println(node[i] + " to " + node[i+1] +" " + dist[i+1]);
			ttl+= dist[i+1];
		}
		System.out.println("Total Journey : "+ttl);
	}
	
	public void nearestNeighbor(Port[] ports, Ship s, int src) {
		
		double[][] graph=createGraph(ports, s);
		//printGraph(graph);
		
		int V=graph.length;
		// The output array to hold the shortest path from src to i
		double dist[] = new double[V+1];
		String node[] = new String[V+1];
		// The array to hold whether True/False a port is included in shortest path tree
		boolean sptSet[] = new boolean[V];
		// Set sptSet[] as false/unvisited
		for (int i=0;i<V;i++) {
			sptSet[i]=false;
		}
		// Distance from source(Ship) from itself =0
		dist[src]=0;
		node[src]=s.getName();
		sptSet[src]=true;
		int x=src;
		
		//Find the shortest path for all 
		for(int count=1; count<V;count++) {
			//Pick the smallest distance
			int u=minDistance(graph[x], sptSet);
			//Picked port is marked called
			sptSet[u]=true;
			//Save the name of port
			node[count]=ports[u-1].getName();
			//Record the distance from previous to the latest
			dist[count]=graph[x][u];
			//Update latest node/port
			x=u;
		}
		//Record the distance from previous to the latest
		dist[V]=graph[src][x];
		node[V]=s.getName();
		// print the constructed distance array
		printShortest(dist, node);
		
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
		System.out.println(cnt+" duplications avoided");
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
		Port P2 =new Port("P2",0,20);
		Port P3 =new Port("P3",0,30);
		Port P4 =new Port("P4",0,40);
		Port P5 =new Port("P5",0,50);
		Port P6 =new Port("P6",0,60);
		Port P7 =new Port("P7",0,70);
		Port P8 =new Port("P8",0,80);
		Port P9 =new Port("P9",0,90);
		Port[] ports = new Port[] {P1,P2,P3,P4,P5,P6,P7,P8,P9};
        TSP Y = new TSP();
        Y.nearestNeighbor(ports, X, 0);	
        //https://www-m9.ma.tum.de/games/tsp-game/index_en.html
	}
	
	
}
