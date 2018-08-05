package src.core;

import src.interfaces.io.Reader;
import src.interfaces.io.Writer;
import src.io.ConsoleReader;
import src.io.ConsoleWriter;
import src.repositories.BlobRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Engine implements Runnable {

    private static final String TERMINATE_COMMAND = "drop";

    private Reader reader;
    private Writer writer;
    private BlobRepository blobRepository;

    public Engine(){
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
        this.blobRepository = new BlobRepository();
    }

    public void run(){

        String line = reader.readLine();

        while (!TERMINATE_COMMAND.equals(line)){

            String[] arguments = line.split(" ");

            String className = arguments[0];

            try {
                Method method = BlobRepository.class.getDeclaredMethod(className,String[].class);

                method.setAccessible(true);

                String result = (String) method.invoke(this.blobRepository, (Object) arguments);

                if(result != null)
                    writer.whiteText(result);


            } catch (NoSuchMethodException e) {
                writer.writeTextWithNewLine("No such command!");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            line = reader.readLine();

        }

    }

}
