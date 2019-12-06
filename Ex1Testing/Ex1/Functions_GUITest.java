package Ex1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Functions_GUITest {

    static Functions_GUI funcs;
    static Functions_GUI funcs2;
    @BeforeAll
    static void init()
    {
     funcs=new Functions_GUI();
     funcs2=new Functions_GUI();
    }
    @Test
    void initAndSaveFromFile() { //created funcs from file and saved the collection into the after file, created funcs2 and compare
        String file = "file.txt";
        String afterSaveToFile="afterSaveToFile.txt";
        try {
            funcs.initFromFile(file);
            funcs.saveToFile(afterSaveToFile);
            funcs2.initFromFile(afterSaveToFile);
            assertTrue(funcs.funcs.containsAll(funcs2.funcs));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}