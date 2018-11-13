package TravelingObject;

public class Journey implements Cloneable{
	//Initialize variables
	private int[] journey;
	private long[][] map;
	public Journey(Towns towns) {
		this.map = towns.getMap();
		randomJourney(towns.getNumberOfTowns());
	}
	//generate a random sequence of cities
	private void randomJourney(int cities) {
		//Create new array and fill it with all cities
		journey = new int[cities];
		for(int i = 0; i < cities; i++) {
			journey[i] = i;
		}
		//shuffle the array, swap every index with a random index
		for(int i = 0; i < journey.length; i++) {
			int index = (int)(Math.random() * cities); 
			swapIndex(i, index, journey);
		}
	}
	public long getLength() {
		//initialize variable
		long journeyLength = 0;
		//Add up the distances between all cities from first to last
		for(int i = 0; i < journey.length - 1; i++) {
			journeyLength +=  map[journey[i]][journey[i+1]];
		}
		//Back to the start
		journeyLength += map[journey[0]][journey[journey.length - 1]];
		return journeyLength;
	}
	//Swap two indices 
	private void swapIndex(int indexA, int indexB, int[] list) {
		int temp = list[indexB];
		list[indexB] = list[indexA];
		list[indexA] = temp;
	}
	public void randomSwap() {
		//generate a random index 
		int indexA = (int)(Math.random() * (journey.length -1));
		int indexB = 0;
		//generate a second random index that is not equal to the first one
		do{
			indexB = (int)(Math.random() * (journey.length -1));
		}while(indexA == indexB);
		//generate a new test array and swap the two indices generated above
		swapIndex(indexA, indexB, journey);
	}
	//journey getter
	public int[] getJourney() {
		return this.journey;
	}
	//Journey setter
	public void setJourney(int[] journey) {
		this.journey = journey;
	}
	//clone function that depp copies the array 
	@Override
	protected Journey clone() throws CloneNotSupportedException {
		Journey clonedJourney = (Journey) super.clone();
		clonedJourney.setJourney(clonedJourney.getJourney().clone());
		return clonedJourney;
	}
	
}
