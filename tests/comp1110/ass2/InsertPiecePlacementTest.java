package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InsertPiecePlacementTest {
//D2E

    @Rule
    public Timeout globalTimeout = Timeout.millis(500);

    private void test(List<String> piecePlacements, String piecePlacement, List<String> expected) {
        FitGame.insertPiecePlacement(piecePlacements, piecePlacement);
        assertEquals("Input was '" + piecePlacements + " and " + piecePlacement + "', expected " + expected + " but got " + piecePlacements, piecePlacements, expected);
    }

    @Test
    public void simpleInsertBefore() {
        List<String> piecePlacements = new ArrayList<String>();
        piecePlacements.add("g70N");
        String piecePlacement = "B81E";
        List<String> expected = new ArrayList<String>(Arrays.asList("B81E", "g70N"));
        test(piecePlacements, piecePlacement, expected);
    }

    @Test
    public void simpleInsertAfter() {
        List<String> piecePlacements = new ArrayList<String>();
        piecePlacements.add("g70N");
        String piecePlacement = "I50E";
        List<String> expected = new ArrayList<String>(Arrays.asList("g70N", "I50E"));
        test(piecePlacements, piecePlacement, expected);
    }

    @Test
    public void complexInsert() {
        List<String> piecePlacements = new ArrayList<String>();
        piecePlacements.add("g70N");
        piecePlacements.add("n03S");
        piecePlacements.add("s71W");
        String piecePlacement = "l43S";
        List<String> expected = new ArrayList<String>(Arrays.asList("g70N", "l43S", "n03S", "s71W"));
        test(piecePlacements, piecePlacement, expected);
        piecePlacement = "o23N";
        expected = new ArrayList<String>(Arrays.asList("g70N", "l43S", "n03S", "o23N", "s71W"));
        test(piecePlacements, piecePlacement, expected);
        piecePlacement = "B81E";
        expected = new ArrayList<String>(Arrays.asList("B81E", "g70N", "l43S", "n03S", "o23N", "s71W"));
        test(piecePlacements, piecePlacement, expected);
        piecePlacement = "Y00W";
        expected = new ArrayList<String>(Arrays.asList("B81E", "g70N", "l43S", "n03S", "o23N", "s71W", "Y00W"));
        test(piecePlacements, piecePlacement, expected);
    }
}

