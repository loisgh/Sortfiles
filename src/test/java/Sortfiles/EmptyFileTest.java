package Sortfiles;

import org.junit.Test;

/**
 * Created by loisgh on 12/30/15.
 */
public class EmptyFileTest {

    @Test(expected = Exception.class)
    public void emptyFilesTest() throws Exception{
        ParsePerson parsePerson = new ParsePerson();
        String[] args = {"Cyrus/src/main/resources/empties/"};
        parsePerson.main(args);
    }
}
