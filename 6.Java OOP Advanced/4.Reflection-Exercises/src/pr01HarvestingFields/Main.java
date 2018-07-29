package pr01HarvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		Field[] fields = RichSoilLand.class.getDeclaredFields();

		List<Field> publicFields = new ArrayList<>();
		List<Field> protectedFields = new ArrayList<>();
		List<Field> privateFields = new ArrayList<>();

		Arrays.stream(fields).forEach(field -> {
			int modifier = field.getModifiers();
			if(Modifier.isPublic(modifier)){
				publicFields.add(field);
			}else if(Modifier.isProtected(modifier)){
				protectedFields.add(field);
			}else if(Modifier.isPrivate(modifier)){
				privateFields.add(field);
			}
		});


		String command = reader.readLine();

		while (!"HARVEST".equals(command)){

			switch (command){
				case "private":
					privateFields.forEach(Main::printField);
					break;
				case "public":
					publicFields.forEach(Main::printField);
					break;
				case "protected":
					protectedFields.forEach(Main::printField);
					break;
				case "all":
					Arrays.stream(fields).forEach(Main::printField);
					break;
			}

			command = reader.readLine();
		}
	}

	public static void printField(Field field){
		System.out.printf("%s %s %s\n",Modifier.toString(field.getModifiers())
				,field.getType().getSimpleName(),field.getName());
	}
}
