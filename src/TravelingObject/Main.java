package TravelingObject;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//Initialize variables
		Main m = new Main();
		Towns towns;
		Scanner sc = new Scanner(System.in);
		//get user input number of towns
		int numberOfTowns = m.getTowns(sc);
		String answer = "";
		//get user Input repeat until valid answer is entered
		do {
			try {
				System.out.println("Wollen sie die maximale Distanz aendern?");
				answer = sc.next();
			}catch(java.util.InputMismatchException e) {
				while(sc.hasNext()) sc.nextLine();
			}
		}while(!(answer.contains("j") || answer.contains("n")));
		if(answer.contains("j")) {
			long maxDist = m.getMaxDist(sc, numberOfTowns);
			towns = new Towns(numberOfTowns, maxDist);
		}else {
			towns = new Towns(numberOfTowns);
		}
		//Create new Journey
		Journey j = new Journey(towns);
		//Print Journey length
		System.out.println(j.getLength());
		//Check swaps
		m.checkJourneySwaps(j, 200);
		//Close scanner
		sc.close();		
	}
	//swap indices and check new length
	public void checkJourneySwaps(Journey j, long swaps) {
		long oldLength;
		long newLength;
		Journey testJourney;
		for(long i = 0; i < swaps; i++) {
			
			//generate a test journey 
			testJourney = new Journey(j);
			//swap indices in test journey
			testJourney.randomSwap();
			//compare new and old length use the new journey if its shorter
			if((newLength = testJourney.getLength())< (oldLength = j.getLength())) {
				System.out.println(oldLength + " " + newLength);
				j = testJourney;
			}
			
			
		}
		System.out.println(j.getLength());
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
	public int getTowns(Scanner sc) {
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
}
