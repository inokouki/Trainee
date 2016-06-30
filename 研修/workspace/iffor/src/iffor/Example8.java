package iffor;

public class Example8 {
	public static void main(String args[]){
		boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false;
		int a = 4, b = 5, c = 2;

		if(a > b && b > c)
			flag1 = true;

		if(a < b || a < c)
			flag2 = true;

		if(c > a && c > b)
			flag3 = true;

		if(c > b && b > a)
			flag4 = true;

		if(a == c && a != b)
			flag5 = true;

		if(a > b * 2 && a < b * 3)
			flag6 = true;

		System.out.println(flag1);
		System.out.println(flag2);
		System.out.println(flag3);
		System.out.println(flag4);
		System.out.println(flag5);
		System.out.println(flag6);
	}
}
