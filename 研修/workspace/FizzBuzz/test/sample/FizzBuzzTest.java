/**
 *
 */
package sample;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import fizzbuzz.FizzBuzz;

public class FizzBuzzTest {

	@SuppressWarnings("unused")
	private FizzBuzz fb;

	@Test
	public void 数字が2の場合(){
		fb = new FizzBuzz(2);
		assertThat(FizzBuzz.print(), is("2"));
	}

	@Test
	public void 数字が3の倍数だが5の倍数ではない場合(){
		fb = new FizzBuzz(3);
		assertThat(FizzBuzz.print(), is("Fizz"));
	}

	@Test
	public void 数字が4の場合(){
		fb = new FizzBuzz(4);
		assertThat(FizzBuzz.print(), is("4"));
	}

	@Test
	public void 数字が5の倍数だが3の倍数ではない場合(){
		fb = new FizzBuzz(5);
		assertThat(FizzBuzz.print(), is("Buzz"));
	}

	@Test
	public void 数字が6の場合(){
		fb = new FizzBuzz(6);
		assertThat(FizzBuzz.print(), is("Fizz"));
	}

	@Test
	public void 数字が14の場合(){
		fb = new FizzBuzz(14);
		assertThat(FizzBuzz.print(), is("14"));
	}

	@Test
	public void 数字が3と5の公倍数の場合(){
		fb = new FizzBuzz(15);
		assertThat(FizzBuzz.print(), is("FizzBuzz"));
	}

	@Test
	public void 数字が16の場合(){
		fb = new FizzBuzz(16);
		assertThat(FizzBuzz.print(), is("16"));
	}

	@Test
	public void 数字が100の場合(){
		fb = new FizzBuzz(100);
		assertThat(FizzBuzz.print(), is("Buzz"));
	}

}
