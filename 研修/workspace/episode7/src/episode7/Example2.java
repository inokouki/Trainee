package episode7;

class A {
	A() { System.out.println("[A()]");}
	A(int i) { System.out.println("A(int i)]");}
}

class B extends A {
	B() { System.out.println("[B()]");}
	B(int i) { System.out.println("[B(int i)]");}
}

class C extends B {}

public class Example2 {
	public static void main(String args[]){
		B b0 = new B();
		System.out.println();

		B b1 = new B(10);
		System.out.println();

		C c = new C();
	}
}
