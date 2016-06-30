package episode7;

class X{
	X() { System.out.println("[X]");}
	void a() { System.out.println("[x.a]");}
	void b() { System.out.println("[x.b]");}
}

class Y extends X{
	Y() { System.out.println("[Y]");}
	void a() { System.out.println("[y.a]");}
}

public class Example1 {
	public static void main(String args[]){
		X x = new X();
		x.a();
		x.b();

		Y y = new Y();
		y.a();
		y.b();
	}
}
