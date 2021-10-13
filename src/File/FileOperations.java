package File;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.*;


public class FileOperations {

    private final String chemin;


    public FileOperations(String chemin){
        this.chemin = chemin;
    }

    public static List<String> readFile(String chemin){
        List<String> lignes = Collections.emptyList();
        try {
            lignes = Files.readAllLines(Paths.get(chemin) , StandardCharsets.UTF_8);
        }catch (IOException e){
            e.printStackTrace();
        }
        return lignes;
    }

}
