package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	Calculator underTest = new Calculator();
	@Test
	void itShouldAddNumbers() {
		// given
		int a = 20;
		int b = 30;

		// when
		int result = underTest.add(a, b);

		// then
		int expected = 50;
		Assertions.assertThat(result).isEqualTo(expected);

	}

	class Calculator{
		int add(int a, int b){
			return a + b;
		}
	}

}
