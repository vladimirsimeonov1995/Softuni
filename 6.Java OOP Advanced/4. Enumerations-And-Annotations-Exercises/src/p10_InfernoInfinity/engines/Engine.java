package p10_InfernoInfinity.engines;

import p10_InfernoInfinity.interfaces.InputReader;
import p10_InfernoInfinity.interfaces.OutputWriter;
import p10_InfernoInfinity.io.ConsoleReader;
import p10_InfernoInfinity.io.ConsoleWriter;
import p10_InfernoInfinity.utils.Constants;

import java.io.IOException;

public class Engine {

    private OutputWriter writer;
    private InputReader reader;
    private WeaponManager weaponManager;
    private AnnotationManager annotationManager;

    public Engine(){
        this.writer = new ConsoleWriter();
        this.reader = new ConsoleReader();
        this.weaponManager = new WeaponManager();
        this.annotationManager = new AnnotationManager();
    }

    public void start() throws IOException {

        String command = reader.readLine();

        while (!Constants.END_COMMAND.equals(command)){

            String[] cmdArgs = command.split(";");

            switch (cmdArgs[0]){

                case "Create":
                    weaponManager.create(cmdArgs[1],cmdArgs[2]);
                    break;

                case "Add":
                    weaponManager.addGemToWep(cmdArgs[1],cmdArgs[2],cmdArgs[3]);
                    break;

                case "Remove":
                    weaponManager.removeGemFromWep(cmdArgs[1],cmdArgs[2]);
                    break;

                case "Compare":
                    String result = weaponManager.compare(cmdArgs[1],cmdArgs[2]);
                    writer.writeNewLine(result);
                    break;

                case "Author":
                    writer.writeNewLine(annotationManager.author());
                    break;

                case "Revision":
                    writer.writeNewLine(annotationManager.revision());
                    break;

                case "Description":
                    writer.writeNewLine(annotationManager.description());
                    break;

                case "Reviewers":
                    writer.writeNewLine(annotationManager.reviewers());
            }
            command = reader.readLine();
        }



    }

    public void getAnotations() throws IOException {

        String command = reader.readLine();

        while (!Constants.END_COMMAND.equals(command)){

            switch (command){

                case "Author":
                    writer.writeNewLine(annotationManager.author());
                    break;
                case "Revision":
                    writer.writeNewLine(annotationManager.revision());
                    break;
                case "Description":
                    writer.writeNewLine(annotationManager.description());
                    break;
                case "Reviewers":
                    writer.writeNewLine(annotationManager.reviewers());
            }

            command = reader.readLine();
        }
    }

}
