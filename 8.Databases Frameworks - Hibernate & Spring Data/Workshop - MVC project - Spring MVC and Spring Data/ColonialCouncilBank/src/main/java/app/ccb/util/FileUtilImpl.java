package app.ccb.util;

import java.io.*;

public class FileUtilImpl implements FileUtil {
    @Override
    public String readFile(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filePath)
                )
        );

        StringBuilder result = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null){
            result.append(line);
            result.append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
