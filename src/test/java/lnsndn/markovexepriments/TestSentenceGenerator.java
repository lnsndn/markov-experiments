package lnsndn.markovexepriments;

import junit.framework.TestCase;
import lnsndn.markovexperiments.SentenceGenerator;
import lnsndn.markovexperiments.data.DataReader;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestSentenceGenerator extends TestCase {

    public void testHappyPathEvenNumberOfWords() {
        List<String> lines = Arrays.asList("BEGIN HERE this is a test END");
        DataReader reader = mock(DataReader.class);
        when(reader.getLines()).thenReturn(lines);

        SentenceGenerator generator = new SentenceGenerator(reader);
        assertEquals("this is a test", generator.generateSentence());
    }

    public void testHappyPathOddNumberOfWords() {
        List<String> lines = Arrays.asList("BEGIN HERE this is also a test END");
        DataReader reader = mock(DataReader.class);
        when(reader.getLines()).thenReturn(lines);

        SentenceGenerator generator = new SentenceGenerator(reader);
        assertEquals("this is also a test", generator.generateSentence());
    }

    public void testEmptyLine() {
        List<String> lines = Arrays.asList("");
        DataReader reader = mock(DataReader.class);
        when(reader.getLines()).thenReturn(lines);

        SentenceGenerator generator = new SentenceGenerator(reader);
        assertEquals("", generator.generateSentence());
    }

    public void testLineMissingStartAndStop() {
        List<String> lines = Arrays.asList("i am wrong");
        DataReader reader = mock(DataReader.class);
        when(reader.getLines()).thenReturn(lines);

        SentenceGenerator generator = new SentenceGenerator(reader);
        assertEquals("", generator.generateSentence());
    }
}
