package comp1110.ass2;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * This tests for the method isPlacementOverlapping
 * Returns true if the piece does not overlap with current board
 * Returns false if the piece overlaps with any part of pre-installed objective pieces.
 *
 * Code written and edited by Jiwon Sin
 */

public class IsPlacementNotOverlappingTest {

    private void test(String placement, boolean expected) {
        boolean outcome = FitGame.isPlacementNotOverlapping(originalBoard, placement);
        assertEquals("Expected " + expected + " for " + placement + " but got " + outcome, expected, outcome);
    }

    // This further complicates the test as every test will go through different board
    int randomNumber = new Random().nextInt(120);

    // Using random number to set the random problem.
    String objectiveString = Games.getObjective(randomNumber);

    /**
     * This method returns all the viable pieces given a certain objective String
     * @param objective Placement string of the game
     * @return List of all viable pieces that can be placed without overlapping.
     */
    private List<String> getViablePieces(String objective) {
        List <String> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                if (FitGame.getViablePiecePlacements(objective, i, j) != null) {
                    result.addAll(Objects.requireNonNull(FitGame.getViablePiecePlacements(objective, i, j)));
                }
            }
        }
        return result;
    }

    /**
     * This method brings out the list of pieces that OVERLAPS the current game board.
     * missingP list is brought in from getMissingPieces method (which is extensively tested at GetMissingPiecesTest.java)
     * Using the list of missing pieces, this method generates all possible pieces that could overlap with current board.
     *
     * @param missingP List of missing pieces, given certain placements
     * @param objectiveString The original placement of the board when the game starts
     * @return List of all pieces that will overlap with the board
     */
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

    /**
     * The initial board of the game.
     */

    PieceType [][] originalBoard = {
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null}
    };

    List<String> missingPieces = FitGame.getMissingPieces(objectiveString);

    @Test // Must always return true
    public void testNotOverlappingPieces() {
        FitGame.boardUpdate(objectiveString, originalBoard);
        for (String placement : getViablePieces(objectiveString)) {
            test(placement, true);
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
