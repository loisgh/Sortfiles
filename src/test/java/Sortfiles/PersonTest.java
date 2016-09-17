package Sortfiles;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;


/**
 * Created by loisgh on 12/30/15.
 */
public class PersonTest {

    @Mock
    ReadFile readFile;
    private List<String> space = new ArrayList<>();
    private List<String> pipe = new ArrayList<>();
    private List<String> comma = new ArrayList<>();
    private File[] listOfFiles = new File[3];

    public void setUp(){
        space.add("Kournikova Anna F F 6-3-1975 Red");
        space.add("Hingis Martina M F 4-2-1979 Green");
        space.add("Seles Monica H F 12-2-1973 Black");
        pipe.add("Smith | Steve | D | M | Red | 3-3-1985");
        pipe.add("Bonk | Radek | S | M | Green | 6-3-1975");
        pipe.add("Bouillon | Francis | G | M | Blue | 6-3-1975");
        comma.add("Abercrombie, Neil, Male, Tan, 2/13/1943");
        comma.add("Bishop, Timothy, Male, Yellow, 4/23/1967");
        comma.add("Kelly, Sue, Female, Pink, 7/12/1959");
        listOfFiles[0] = new File("space.txt");
        listOfFiles[1] = new File("pipe.txt");
        listOfFiles[2] = new File("comma.txt");
    }

    public void setUp2(){
        space.add("Kournikova Anna F J 6-3-1975 Red");
        space.add("Hingis Martina M K 4-2-1979 Green");
        space.add("Seles Monica H F 12-2-1973 Black");
        pipe.add("Smith | Steve | D | M | Red | 3-3-1985");
        pipe.add("Bonk | Radek | S | M | Green | 6-3-1975");
        pipe.add("Bouillon | Francis | G | M | Blue | 6-3-1975");
        comma.add("Abercrombie, Neil, Male, Tan, 2/13/1943");
        comma.add("Bishop, Timothy, Male, Yellow, 4/23/1967");
        comma.add("Kelly, Sue, Female, Pink, 7/12/1959");
        listOfFiles[0] = new File("space.txt");
        listOfFiles[1] = new File("pipe.txt");
        listOfFiles[2] = new File("comma.txt");
    }

    public void setUp3(){
        space.add("Kournikova Anna F F 6-39-1975 Red");
        space.add("Hingis Martina M F 4-2-1979 Green");
        space.add("Seles Monica H F 12-2-1973 Black");
        pipe.add("Smith | Steve | D | M | Red | 3-3-1985");
        pipe.add("Bonk | Radek | S | M | Green | 6-3-1975");
        pipe.add("Bouillon | Francis | G | M | Blue | 6-3-1975");
        comma.add("Abercrombie, Neil, Male, Tan, 2/13/1943");
        comma.add("Bishop, Timothy, Male, Yellow, 4/23/1967");
        comma.add("Kelly, Sue, Female, Pink, 7/12/1959");
        listOfFiles[0] = new File("space.txt");
        listOfFiles[1] = new File("pipe.txt");
        listOfFiles[2] = new File("comma.txt");
    }

    @Test
    public void personTest() throws ParseException, Exception{

        //Setup
        MockitoAnnotations.initMocks(this);
        setUp();

        //Mock Behaviors
        when(readFile.readFile(contains("pipe"))).thenReturn(pipe);
        when(readFile.readFile(contains("comma"))).thenReturn(comma);
        when(readFile.readFile(contains("space"))).thenReturn(space);
        when(readFile.getFiles(anyString())).thenReturn(listOfFiles);

        ParsePerson parsePerson = new ParsePerson();
        String inDirectory = "/";
        parsePerson.initLists();
        List<Person> persons = parsePerson.fileIterator(inDirectory, parsePerson, readFile);
    }

    @Test(expected= ParseException.class)
    public void badFormatSex() throws ParseException, Exception{

        //Setup
        MockitoAnnotations.initMocks(this);
        setUp2();

        //Mock Behaviors
        when(readFile.readFile(contains("pipe"))).thenReturn(pipe);
        when(readFile.readFile(contains("comma"))).thenReturn(comma);
        when(readFile.readFile(contains("space"))).thenReturn(space);
        when(readFile.getFiles(anyString())).thenReturn(listOfFiles);

        ParsePerson parsePerson = new ParsePerson();
        String inDirectory = "/";
        parsePerson.initLists();
        List<Person> persons = parsePerson.fileIterator(inDirectory, parsePerson, readFile);

    }

    @Test(expected= ParseException.class)
    public void badFormatDate() throws ParseException, Exception{

        //Setup
        MockitoAnnotations.initMocks(this);
        setUp3();

        //Mock Behaviors
        when(readFile.readFile(contains("pipe"))).thenReturn(pipe);
        when(readFile.readFile(contains("comma"))).thenReturn(comma);
        when(readFile.readFile(contains("space"))).thenReturn(space);
        when(readFile.getFiles(anyString())).thenReturn(listOfFiles);

        ParsePerson parsePerson = new ParsePerson();
        String inDirectory = "/";
        parsePerson.initLists();
        List<Person> persons = parsePerson.fileIterator(inDirectory, parsePerson, readFile);

    }

}
