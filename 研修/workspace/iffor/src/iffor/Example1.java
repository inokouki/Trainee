package iffor;

public class Example1 {
	public static void main(String[] args){
		boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false, flag7 = false;
		int a = 20;

		if ( a == 20 ) flag1 = true;
		if ( a != 20 ) flag2 = true;
		if ( a > 20 ) flag3 = true;
		if ( a < 20 ) flag4 = true;
		if ( a % 3 == 0 ) flag5 = true;
		if ( a % 2 == 0 ) flag6 = true;
		if ( a % 5 == 2 ) flag7 = true;

		System.out.println("flag1は" + flag1);
		System.out.println("flag2は" + flag2);
		System.out.println("flag3は" + flag3);
		System.out.println("flag4は" + flag4);
		System.out.println("flag5は" + flag5);
		System.out.println("flag6は" + flag6);
		System.out.println("flag7は" + flag7);
	}
}
