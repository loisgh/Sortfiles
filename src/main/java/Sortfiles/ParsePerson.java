package Sortfiles;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by loisgh on 12/30/15.
 */
public class ParsePerson {

    private SimpleDateFormat dfSlash = new SimpleDateFormat("m/dd/yyyy");
    private SimpleDateFormat dfDash = new SimpleDateFormat("m-dd-yyyy");
    private Map<String, String> delimeter = new HashMap<>();
    private Map<String, Integer> sexIdx = new HashMap<>();
    private Map<String, int[]> order = new HashMap<>();

    public static void main(String[] args) throws Exception{

        if (args == null || args.length == 0)
            throw new Exception("You need to include an input file directory as a parameter");
        else{
            ParsePerson parsePerson = new ParsePerson();
            sortAndPrintFiles(args[0], parsePerson);
        }
    }

    public static void sortAndPrintFiles(String inDirectory, ParsePerson parsePerson) throws Exception{
        ReadFile readFile = new ReadFile();
        parsePerson.initLists();
        Comparator<Person> byBirthday = (p1, p2) -> p1.getbirthDate().compareTo(p2.getbirthDate());
        Comparator<Person> byLastName = (p1, p2) -> p1.getLastName().compareTo(p2.getLastName());
        Comparator<Person> bySex = (p1, p2) -> p1.getSexType().compareTo(p2.getSexType());

        List<Person> Persons = parsePerson.fileIterator(inDirectory, parsePerson, readFile);
        if (Persons.size() > 0) {
            //Sex LastName
            parsePerson.printList(Persons.stream().sorted(bySex.thenComparing(byLastName)).collect(Collectors.toList()), "Output 1:");
            //Birthday LastName
            parsePerson.printList((Persons.stream().sorted(byBirthday.thenComparing(byLastName)).collect(Collectors.toList())), "Output 2:");
            //LastName Descending
            parsePerson.printList(Persons.stream().sorted(byLastName.reversed()).collect(Collectors.toList()), "Output 3:");
        }
    }

    public void initLists() {
        delimeter.put("comma.txt",",");
        delimeter.put("pipe.txt","\\|");
        delimeter.put("space.txt", " ");
        sexIdx.put("comma.txt", 2);
        sexIdx.put("pipe.txt", 3);
        sexIdx.put("space.txt", 3);
        order.put("comma.txt", new int[]{0, 1, 3, 4});
        order.put("pipe.txt", new int[]{0, 1, 4, 5});
        order.put("space.txt",new int[]{0,1,5,4});
    }

    public List<Person> fileIterator(String inDirectory, ParsePerson ParsePerson, ReadFile ReadFile) throws ParseException, Exception {

        File[] listOfFiles = ReadFile.getFiles(inDirectory);
        List<Person> Persons = new ArrayList<>();

        for (File f : listOfFiles) {
            if (f.getName().endsWith(".txt"))
                Persons.addAll(ParsePerson.createPersons(ReadFile.readFile(f.getAbsolutePath()), f.getName()));
        }
        return Persons;
    }

    private Person parsePerson(String in, String delimeter, int[] order, int sexIdx, String filename) throws ParseException{
            SimpleDateFormat df;
            df = (filename.equals("comma.txt")) ? dfSlash : dfDash;
            df.setLenient(false);
            String[] inLine = in.split(delimeter);
            if (!Person.sexFormatValid(inLine[sexIdx]))
                throw new ParseException("The sex is in the wrong format",0);
            Person.SexType insex = Person.SexType.valueOf(inLine[sexIdx].trim().toUpperCase());
        return new Person(inLine[order[0]], inLine[order[1]], inLine[order[2]], insex, df.parse(inLine[order[3]]));
    }

    private List<Person> createPersons(List<String> lines, String filename) throws ParseException{
        List<Person> Persons = new ArrayList<>();
        for (String l: lines) {
            Persons.add(parsePerson(l, delimeter.get(filename), order.get(filename), sexIdx.get(filename), filename));
        }

        return Persons;
    }

    private void printList(List<Person> Persons, String title){
        System.out.println(title);
        for (Person p: Persons){
            System.out.println(p.toString());
        }
        System.out.println(" ");
        System.out.println(" ");
    }


}
