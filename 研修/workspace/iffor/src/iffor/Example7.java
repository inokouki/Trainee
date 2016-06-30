package iffor;

public class Example7 {
	public static void main(String args[]){
		String day;
		day = "Sunday";

		String weather;
		weather = "rainy";

		int money;
		money = 2000;

		if ((day == "Sunday" || day == "Saturday") && weather == "shiny" && money >= 3000){
			System.out.println("遊びに行く！");
		} else {
			System.out.println("家でおとなしくしましょう");
		}
	}
}
