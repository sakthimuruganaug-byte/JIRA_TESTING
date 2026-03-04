package com.example.code;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class calTest {
	// ---- add() ----
	cal calc=new cal();
//	@Order(1)
	   @ParameterizedTest
	   @CsvSource({
	       "1, 2, 3",
	       "3, 5, 8",
	       "10, 20, 30",
	       "0, 0, 0",           // edge: zero + zero
	       "-1, 1, 0",          // edge: negative + positive
	       "-5, -7, -12"        // edge: negative + negative
	   })
	   void testAdd(int a, int b, int expected) {
	       assertEquals(expected, calc.add(a, b));
	   }

	   // ---- even() ----
	   // True cases
//	@Order(2)
	   @ParameterizedTest(name = "isEven({0}) should be true")
	   @ValueSource(ints = {0, 2, -2, 100, -100, Integer.MIN_VALUE})
	   void testEvenTrueCases(int n) {
	       assertTrue(calc.isEven(n));
	   }

	   // False cases
//	@Order(3)
	   @ParameterizedTest(name = "even({0}) should be false")
	   @ValueSource(ints = {1, -1, 3, -5, 101, Integer.MAX_VALUE})
	   void testEvenFalseCases(int n) {
	       assertFalse(calc.isEven(n));  
	   }

	   // ---- mul() ----
//	@Order(4)
	   @ParameterizedTest(name = "mul({0}, {1}) = {2}")
	   @CsvSource({
	       "1, 2, 2",
	       "3, 5, 15",
	       "10, 0, 0",          // identity: anything * 0 = 0
	       "0, 10, 0",
	       "-3, 7, -21",        // sign checks
	       "4, -5, -20",
	       "-6, -7, 42",
	       "1, -1, -1"
	       // You could add limits-based tests if your impl changes to long/bigint
	   })
	   void testMul(int a, int b, int expected) {
	       assertEquals(expected, calc.mul(a, b));
	   }

}
