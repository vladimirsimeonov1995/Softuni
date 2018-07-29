package pr02PrivateClassFiddling;

import pr02PrivateClassFiddling.com.BlackBoxInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException, NoSuchFieldException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		Constructor constructor = BlackBoxInt.class.getDeclaredConstructor();

		constructor.setAccessible(true);

		BlackBoxInt blackBoxInt = (BlackBoxInt) constructor.newInstance();

		String line = reader.readLine();

		while (!"END".equals(line)){

			String[] commandArgs  =line.split("_");

			method(blackBoxInt,commandArgs[1],commandArgs[0]);

			line = reader.readLine();

		}







	}

	private static void method(BlackBoxInt blackBoxInt, String intCommandInSting,String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
		Method method = BlackBoxInt.class.getDeclaredMethod(methodName,int.class);
		method.setAccessible(true);
		method.invoke(blackBoxInt,Integer.parseInt(intCommandInSting));
		method.setAccessible(false);

		printResult(blackBoxInt);
	}



	private static void printResult(BlackBoxInt blackBoxInt) throws NoSuchFieldException, IllegalAccessException {
		Field result = blackBoxInt.getClass().getDeclaredField("innerValue");
		result.setAccessible(true);

		System.out.println(result.get(blackBoxInt));

		result.setAccessible(false);
	}
}
