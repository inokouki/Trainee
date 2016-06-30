package fizzbuzz;

public class FizzBuzz {

	private static int currentNumber;

	public static void main(String[] args) {

		for (int i = 1; i <= 100; i++) {
			currentNumber = i;
			System.out.println(print());
		}
	}

	public FizzBuzz(int startNumber){
		currentNumber = startNumber;
	}

	public static String print(){

		if(currentNumber % 3 == 0 && currentNumber % 5 == 0) {
			return "FizzBuzz";

		} else if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
			return "Fizz";

		} else if (currentNumber % 3 != 0 && currentNumber % 5 == 0) {
			return "Buzz";

		} else {
			return Integer.toString(currentNumber);
		}
	}
}