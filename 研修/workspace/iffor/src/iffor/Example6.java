package iffor;

public class Example6 {
	public static void main(String args[]){
		int a = 5;
		int b = 3;
		int c = (a > b) ? a : b;

		int x = 5;
		int y = 3;
		int z = (x > y * 2) ? x + 1 : y - 3;

		int m = -5;
		int n = (m > 0) ? m : -m;

		System.out.println(c);
		System.out.println(z);
		System.out.println(n);
	}
}
