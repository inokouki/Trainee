package hairetu;

class Rectangle{
	double width;
	double height;

	static double getArea(double w,double h){
		double l;
		l = w * h;

		return l;
	}

	Rectangle(){
		this.width = 10;
		this.height = 5;
	}

	static boolean isLarger(double x, double y){
		boolean jjj=false;

		if(x>y){
			jjj=true;
		} else {
			jjj=false;
		}

		return jjj;
	}

}

public class Example4 {
	public static void main(String args[]){
		double L1, L2;
		boolean check=false;

		Rectangle Large = new Rectangle();
		Large.width=30;
		Large.height=10;

		L1 = Rectangle.getArea(Large.width,Large.height);

		Rectangle Rect = new Rectangle();
		L2 = Rectangle.getArea(Rect.width,Large.height);

		check = Rectangle.isLarger(L1, L2);

		System.out.println(check);
	}
}
