import java.util.Scanner;

public class TravellingSalesman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TravellingSalesman a = new TravellingSalesman();
		a.run();
	}
	public void run() {
		Scanner sc = new Scanner(System.in);
		int cities = getCities(sc);
		long maxDist = getMaxDist(sc, cities);
		long[][] map = new long[cities][cities];
		generateMap(cities, maxDist, map);
		for(long[] a: map) {
			for(long entry : a) {
				System.out.print(entry + " ");
			}
			System.out.print("\n");
		}
		int[] journey = randomJourney(cities);
		for(int a : journey) System.out.println(a);
		long journeyLength = getJourneyLength(journey, map);
		System.out.println(journeyLength);
		for(int i = 0; i < 2000; i++) {
			checkSwap(journey, map);
		}
		System.out.println(journeyLength);
		sc.close();
	}
	 
	public void generateMap(int cities, long maxDist, long[][] map) {
		for(int city1 = 0; city1 < cities; city1++) {
			for(int city2 = 0; city2 < cities; city2++) {
				map[city1][city2] = (city1 == city2) ? 0 : (city1 > city2) ? map[city2][city1] : (long) (Math.random() * (maxDist -1)) + 1;
			}
		}
	}
	public int getCities(Scanner sc) {
		int cities = 0;
		do {
			try {
				System.out.println("Bitte geben sie die Anzahl der Städte ein");
				cities = sc.nextInt();
			}catch(java.util.InputMismatchException e) {
				//Clear Scanner
				while(sc.hasNext()) sc.nextLine();
			}
		}while(!(cities > 0));
		return cities;
	}
	public long getMaxDist(Scanner sc, int cities) {
		long maxDist = 0;
		do {
			try {
				System.out.println("Bitte geben sie die maximale Distanz ein");
				maxDist = sc.nextLong();
			}catch(java.util.InputMismatchException e) {
				//CLear Scanner
				while(sc.hasNext()) sc.nextLine();
			}
			
		}while(!(maxDist > 0 && maxDist < ((long)(Long.MAX_VALUE / cities))));
		return maxDist;
	}
	public int[] randomJourney(int cities) {
		int[] list = new int[cities];
		for(int i = 0; i < cities; i++) {
			list[i] = i;
		}
		for(int i = 0; i < list.length; i++) {
			int index = (int)(Math.random() * cities); 
			int temp = list[index];
			list[index] = list[i];
			list[i] = temp;
		}
		return list;
	}
	public long getJourneyLength(int[] randomJourney, long[][]map) {
		//initialize variable
		long journeyLength = 0;
		//Add up the distances between all cities from first to last
		for(int i = 0; i < randomJourney.length - 1; i++) {
			journeyLength +=  map[randomJourney[i]][randomJourney[i+1]];
		}
		//Back to the start
		journeyLength += map[randomJourney[0]][randomJourney[randomJourney.length - 1]];
		return journeyLength;
	}
	public void checkSwap(int[]randomJourney, long[][] map) {
		System.out.println((getJourneyLength(randomJourney, map)) + "start");
		int indexA = (int)(Math.random() * (randomJourney.length -1));
		int indexB = 0;
		do{
			indexB = (int)(Math.random() * (randomJourney.length -1));
		}while(indexA == indexB);
		int[] testJourney = randomJourney;
		int temp = testJourney[indexA];
		testJourney[indexA] = testJourney[indexB];
		testJourney[indexB] = temp;
		System.out.println((getJourneyLength(testJourney, map)) + "test");
		System.out.println((getJourneyLength(randomJourney, map)) + "dsd");
		if(getJourneyLength(testJourney, map) < getJourneyLength(randomJourney, map)) {
			System.out.println("banane");
			randomJourney = testJourney;
		}
	}
	

}