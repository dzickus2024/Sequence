import java.util.Scanner;



public class Runner {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		do {
			Sequence sequence = new Sequence();
			sequence.play();
			
			System.out.print("Do you want to play again? ");
		} while(scan.next().
				substring(0,1).
				toLowerCase().equals("y"));
		
		System.out.println("Thank you for playing!");
		scan.close();
	}

}
