package Sortfiles;

import org.junit.Test;

/**
 * Created by loisgh on 12/30/15.
 */
public class EmptyParameterTest {

    @Test(expected= Exception.class)
    public void emptyParameterTest() throws Exception{
        ParsePerson parsePerson = new ParsePerson();
        String[] args = null;
        parsePerson.main(args);
    }
}
