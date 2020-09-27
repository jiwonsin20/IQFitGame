package comp1110.ass2;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This test checks whether the getMissingPieces method brings out correct missing pieces.
 * Tests 2 of each difficulty level and if all pieces are taken (testAll) and when none of the pieces are placed(testNull).
 * Code written and edited by Jiwon Sin
 */

public class GetMissingPiecesTest {
    public void test(String placement, List<String> expected) {
        List<String> outcome = FitGame.getMissingPieces(placement);
        assertEquals("Expected " + expected + " for " + placement + " but got " + outcome, expected, outcome);
    }
    @Test
    public void testNull() {
        String placement = "B03SG70Si52SL00Nn01Eo63Sp20Er41WS40Ny62N";
        List<String> missingP = new ArrayList<>();
        test(placement, missingP);
    }

    @Test
    public void testAll() {
        String placement = "";
        List<String> missingP = new ArrayList<>(Arrays.asList("B","G","I","L","N","O","P","R","S","Y","b","g","i","l","n","o","p","r","s","y"));
        test(placement, missingP);
    }

    @Test
    public void testStarter() {
        String pl1 = "b43Sg42So80EP03SR00WS20NY11S";
        List<String> missingP = new ArrayList<>(Arrays.asList("I", "L", "N", "i", "l", "n"));
        test(pl1,missingP);
        String pl2 = "B12Ng02WI82EL62En71No51Ws13S";
        List<String> missingP2 = new ArrayList<>(Arrays.asList("P", "R", "Y", "p","r","y"));
        test(pl2, missingP2);
    }

    @Test
    public void testJunior() {
        String pl1 = "b60Ni63SO81EP01Ws00NY23S";
        List<String> missingP = new ArrayList<>(Arrays.asList("G", "L", "N", "R", "g", "l", "n", "r"));
        test(pl1,missingP);
        String pl2 = "B00Ni82EN50WP03Ss43S";
        List<String> missingP2 = new ArrayList<>(Arrays.asList("G", "L", "O", "R","Y","g","l","o","r","y"));
        test(pl2, missingP2);
    }

    @Test
    public void testExpert() {
        String pl1 = "b10Nl00Wn32NS60N";
        List<String> missingP = new ArrayList<>(Arrays.asList("G","I","O","P","R","Y","g","i","o","p","r","y"));
        test(pl1,missingP);
        String pl2 = "b81EO11Sr52N";
        List<String> missingP2 = new ArrayList<>(Arrays.asList("G","I","L","N","P","S","Y","g","i","l","n","p","s","y"));
        test(pl2, missingP2);
    }

    @Test
    public void testMaster() {
        String pl1 = "b01WG23Ni42En71W";
        List<String> missingP = new ArrayList<>(Arrays.asList("L","O","P","R","S","Y","l","o","p","r","s","y"));
        test(pl1,missingP);
        String pl2 = "G41Ny30W";
        List<String> missingP2 = new ArrayList<>(Arrays.asList("B","I","L","N","O","P", "R","S","b","i","l","n","o", "p","r","s"));
        test(pl2, missingP2);
    }

    @Test
    public void testWizard() {
        String pl1 = "G52Nl21S";
        List<String> missingP = new ArrayList<>(Arrays.asList("B","I","N","O","P", "R","S", "Y","b","i","n","o", "p","r","s","y"));
        test(pl1,missingP);
        String pl2 = "B03SN43S";
        List<String> missingP2 = new ArrayList<>(Arrays.asList("G","I","L","O","P", "R","S", "Y","g","i","l","o", "p","r","s","y"));
        test(pl2, missingP2);

    }

}
