import java.util.Scanner;

public class TravellingSalesman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TravellingSalesman a = new TravellingSalesman();
		a.run();
	}
	public void run() {
		//new scanner
		Scanner sc = new Scanner(System.in);
		//get user input- amount of cities and maxDist
		int cities = getCities(sc);
		long maxDist = getMaxDist(sc, cities);
		//initialize array
		long[][] map = new long[cities][cities];
		//fill array with random numbers
		generateMap(cities, maxDist, map);
		//print array
		for(long[] a: map) {
			for(long entry : a) {
				System.out.print(entry + " ");
			}
			System.out.print("\n");
		}
		//generate random journey
		int[] journey = randomJourney(cities);
		//check a certain amount of different orders for journey
		journey = checkJourneyPosibilities(journey, map, 1000);
		//close scanner
		sc.close();
	}
	//
	public void generateMap(int cities, long maxDist, long[][] map) {
		for(int city1 = 0; city1 < cities; city1++) {
			for(int city2 = 0; city2 < cities; city2++) {
				//Cool oneliner
				//map[city1][city2] = (city1 == city2) ? 0 : (city1 > city2) ? map[city2][city1] : (long) (Math.random() * (maxDist -1)) + 1;
				//Less cool version of cool oneliner
				//If city1 and city2 are the same the distance is 0
				if(city1 == city2) {
					map[city1][city2] = 0;
				//if city1 is a higher number than city2 [city2][city1] has already been created and since the distance 
				//between city 1 and 2 is the same as  2 and 1  12 can just assume the value of 21
				}else if(city1 > city2) {
					map[city1][city2] = map[city2][city1];
				//generate a random distance between 1 and maxDist
				}else {
					map[city1][city2] = (long) (Math.random() * (maxDist -1)) + 1;
				}
			}
		}
	}
	public int getCities(Scanner sc) {
		//Initialize variable
		int cities = 0;
		//Get user input , repeat until valid input is entered
		do {
			try {
				System.out.println("Bitte geben sie die Anzahl der Staedte ein");
				cities = sc.nextInt();
			}catch(java.util.InputMismatchException e) {
				//Clear Scanner
				while(sc.hasNext()) sc.nextLine();
			}
		}while(!(cities > 0));
		return cities;
	}
	public long getMaxDist(Scanner sc, int cities) {
		//Initialize variable
		long maxDist = 0;
		//Get user input , repeat until valid input is entered
		do {
			try {
				System.out.println("Bitte geben sie die maximale Distanz ein");
				maxDist = sc.nextLong();
			}catch(java.util.InputMismatchException e) {
				//CLear Scanner
				while(sc.hasNext()) sc.nextLine();
			}
		//Repeat until maxDist is bigger than 0 but small enough that if all distances are maxDist journeyLength still fits into a long
		}while(!(maxDist > 0 && maxDist < ((long)(Long.MAX_VALUE / (cities+1)))));
		return maxDist;
	}
	//generate a random sequence of cities
	public int[] randomJourney(int cities) {
		//Create new array and fill it with all cities
		int[] list = new int[cities];
		for(int i = 0; i < cities; i++) {
			list[i] = i;
		}
		//shuffle the array, swap every index with a random index
		for(int i = 0; i < list.length; i++) {
			int index = (int)(Math.random() * cities); 
			int temp = list[index];
			list[index] = list[i];
			list[i] = temp;
		}
		//return the shuffled array
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
	public int[] checkSwap(int[]journey, long[][] map) {
		//generate a random index 
		int indexA = (int)(Math.random() * (journey.length -1));
		int indexB = 0;
		//generate a second random index that is not equal to the first one
		do{
			indexB = (int)(Math.random() * (journey.length -1));
		}while(indexA == indexB);
		//generate a new test array and swap the two indices generated above
		int[] testJourney = journey.clone();
		int temp = testJourney[indexA];
		testJourney[indexA] = testJourney[indexB];
		testJourney[indexB] = temp;
		//return the test array
		return testJourney;
	}
	public int[] checkJourneyPosibilities(int[] journey, long[][] map, long swaps) {
		//Initialize variable
		int[] testJourney;
		//Get initial journey length
		long journeyLength = getJourneyLength(journey, map);
		System.out.println("Initial length: " + journeyLength);
		//Generate test journey and compare original journey length with test journey length 
		//If test journey is shorter it becomes the new journey| repeat *swaps* times
		for(long i = 0; i < swaps; i++) {
			if(getJourneyLength(testJourney = checkSwap(journey, map), map) < getJourneyLength(journey, map)) {
				journey = testJourney;
			}
			
		}
		//Print new journey length
		System.out.println("Final length: " + getJourneyLength(journey, map));
		//return the new journey
		return journey;
	}
	
	

}