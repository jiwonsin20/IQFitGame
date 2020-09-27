package comp1110.ass2;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * This tests for the method isPlacementOverlapping
 * Returns true if the piece does not overlap with current board
 * Returns false if the piece overlaps with any part of pre-installed objective pieces.
 */

public class IsPlacementNotOverlappingTest {

    private void test(String placement, boolean expected) {
        boolean outcome = FitGame.isPlacementNotOverlapping(originalBoard, placement);
        assertTrue("Expected " + outcome + " for " + placement + " but got " + expected, outcome == expected);
    }

    int randomNumber = new Random().nextInt(120);
    int randX = new Random().nextInt(4);
    int randY = new Random().nextInt(9);

    String objectiveString = Games.getObjective(randomNumber);

    Set<String> viableAtOrigin = FitGame.getViablePiecePlacements(objectiveString, 0,0);
    Set<String> viableAtXCorner = FitGame.getViablePiecePlacements(objectiveString, 9, 0);
    Set<String> viableAtYCorner = FitGame.getViablePiecePlacements(objectiveString, 0, 4);
    Set<String> viableAtXYCorner = FitGame.getViablePiecePlacements(objectiveString, randX, randY);

    private List<String> getViablePieces(String objective) {
        List <String> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                if (FitGame.getViablePiecePlacements(objective, i, j) != null) {
                    result.addAll(FitGame.getViablePiecePlacements(objective, i, j));
                }
            }
        }
        return result;
    }

    PieceType [][] originalBoard = {
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null}
    };

    List<String> missingPieces = FitGame.getMissingPieces(objectiveString);

    private List<String> makeIncorrectPieces(List<String> missingP, String objectiveString) {
        String [] objectiveArray = new String[objectiveString.length()/4];
        List<String> overlappingPieces = new ArrayList<>();
        int [] x = new int[objectiveString.length() / 4];
        int [] y = new int[objectiveString.length() / 4];
        int k = 0;

        for (int i = 0; i < objectiveString.length()/4; i+=4) {
            objectiveArray[i] = objectiveString.substring(i, i + 4);
            x[i] = objectiveString.charAt(i + 1);
            y[i] = objectiveString.charAt(i + 2);
        }

        for (int i = 0; i < objectiveArray.length; i++) {
            overlappingPieces.add(missingP.get(i) + x[i] + y[i] + "N");
            overlappingPieces.add(missingP.get(i) + x[i] + y[i] + "E");
            overlappingPieces.add(missingP.get(i) + x[i] + y[i] + "S");
            overlappingPieces.add(missingP.get(i) + x[i] + y[i] + "W");
        }
        return overlappingPieces;
    }

    @Test // Must always return true
    public void testNotOverlappingPieces() {
        FitGame.boardUpdate(objectiveString, originalBoard);
//        System.out.println(objectiveString);
        for (String placement : getViablePieces(objectiveString)) {
            test(placement, true);
//            System.out.println("Placement " + placement + " is not overlapping at " + Arrays.deepToString(originalBoard));
        }
    }

    @Test // Must always return false since they are overlapping
    public void testOverlappingPieces() {
        FitGame.boardUpdate(objectiveString, originalBoard);
        for (String list : makeIncorrectPieces(missingPieces, objectiveString)) {
            test(objectiveString, false);
        }


    }
}
