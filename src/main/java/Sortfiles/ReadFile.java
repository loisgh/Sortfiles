package Sortfiles;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by loisgh on 12/30/15.
 */
public class ReadFile {

    public List<String> readFile(String filename) throws Exception{
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
            if (lines.size() < 1)
                throw new Exception("The following file is empty: " + filename);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return lines;
    }

    public File[] getFiles(String inDirectory){
        File folder = new File(inDirectory);
        return folder.listFiles();
    }

}
