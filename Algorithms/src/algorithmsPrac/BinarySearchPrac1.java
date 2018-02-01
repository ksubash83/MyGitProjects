package algorithmsPrac;

public class BinarySearchPrac1 {

	public static void main(String[] args) {
		
		//Find the number guessed between 1 and 100
		int min=1;
		int max=100;
		int guessedNum=98;
		
		int calcGuessedNum=1;
		//System.out.println((int)1.6);
		
		while(guessedNum!=calcGuessedNum) {

			if(guessedNum==(int)(min+max)/2) {
				calcGuessedNum=(int)(min+max)/2;
				break;
			}
			
			else if(guessedNum > (int)(min+max)/2) {
				min=(int)(min+max)/2 +1;
			}
			else {
				max=(int)(min+max)/2 -1;
			}
			calcGuessedNum=min;
		}
		
		
		System.out.println("Guessed number is: "+calcGuessedNum);

	}

}
