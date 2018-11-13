package TravelingObject;

public class Towns {
	//Initialize variables
	private int numberOfTowns;
	private long maxDistance = 10;
	private long[][]map;
	//Constructor 
	public Towns(int numberOfTowns) {
		//generate local variables
		this.numberOfTowns = numberOfTowns;
		map = new long[numberOfTowns][numberOfTowns];
		generateMap(numberOfTowns, maxDistance, map);
	}
	//Constructor with maxDist
	public Towns(int numberOfTowns, long maxDist) {
		this.numberOfTowns = numberOfTowns;
		this.maxDistance = maxDist;
		map = new long[numberOfTowns][numberOfTowns];
		generateMap(numberOfTowns, maxDistance, map);
	}
	//generate map from input
	private void generateMap(int cities, long maxDist, long[][] map) {
		for(int city1 = 0; city1 < cities; city1++) {
			for(int city2 = 0; city2 < cities; city2++) {
				map[city1][city2] = (city1 == city2) ? 0 : (city1 > city2) ? map[city2][city1] : (long) (Math.random() * (maxDist -1)) + 1;
				//If city1 and city2 are the same the distance is 0
				//if city1 is a higher number than city2 [city2][city1] has already been created and since the distance 
				//between city 1 and 2 is the same as  2 and 1  12 can just assume the value of 21
				//generate a random distance between 1 and maxDist
			}
		}
	}
	//towns getter
	public int getNumberOfTowns() {
		return this.numberOfTowns;
	}
	//map getter
	public long[][] getMap(){
		return this.map;
	}
	//maxDist getter
	public long getMaxDistance() {
		return this.maxDistance;
	}
	//print the distances
	public void printMap() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				System.out.println(map[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	
}

