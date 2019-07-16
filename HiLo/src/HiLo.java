import java.util.Scanner;

public class HiLo {

	public static void main(String[] args) {
		int counter = 0;
		String playAgain = "";
		Scanner scan = new Scanner(System.in);
		do {
		int theNumber = (int) (Math.random() * 100) + 1;
		//System.out.print(theNumber);
		int guess = 0;
		while(guess != theNumber ) {
			System.out.println("Guess a number between 1 and 100: ");
			guess = scan.nextInt();
			counter++;
			if(guess > theNumber) {
				System.out.println("Go lower!!");
			}else if(guess < theNumber) {
				System.out.println("Go higher!!");				
			}else {
				System.out.println("You won! The number was " + theNumber);
				System.out.println("You took only " + counter + " guesses!");
			}
		
		}
		System.out.println("Do you want to play again? (y/n)");
		playAgain = scan.next();
		}while(playAgain.equalsIgnoreCase("y"));
		System.out.println("Goodbye!");
		scan.close();
	}

}
