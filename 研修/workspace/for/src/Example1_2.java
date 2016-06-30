
public class Example1_2 {
	public static void main(String args[]){
		int i, j=0;

		for(i=1 ; i<101 ; i++){
			if((i%2 == 0) || (i%3 == 0) && i%12 != 0){
				j += i;
			}
		}

		System.out.println(j);
	}
}
