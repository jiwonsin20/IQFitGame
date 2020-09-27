package comp1110.ass2;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
//D2E

public class PieceMove {

    private void test(String placement, int m, int n, boolean expected){
        boolean outcome = FitGame.pieceMove(placement,m,n);
        assertTrue("Expected " + outcome + " for " + placement + " to move "+ m +" in x direction "+n+" in y direction "+ "but got " +expected, outcome == expected);
    }
//Use the lime green piece in the South East corner for test
    @Test
    public void pieceOneStepX(){
        String piece = "L02W";
        int m = 1;
        int n = 0;

        test(piece,m,n,true);
    }
    @Test
    public void pieceEightStepX(){
        String piece = "L02W";
        int m = 8;
        int n = 0;

        test(piece,m,n,true);
    }
    @Test
    public void pieceNineStepX(){
        String piece = "L02W";
        int m = 9;
        int n= 0;

        test(piece,m,n,false);
    }
    @Test
    public void pieceOneStepY(){
        String piece = "L02W";
        int m = 0;
        int n= -1;

        test(piece,m,n,true);
    }
    @Test
    public void pieceTwoStepY(){
        String piece = "L02W";
        int m = 0;
        int n = -2;

        test(piece,m,n,true);
    }
    @Test
    public void pieceThreeStepY(){
        String piece = "L02W";
        int m = 0;
        int n = -3;

        test(piece,m,n,false);
    }
    @Test
    public void pieceOriginal(){
        String piece = "L02W";
        int m = 0;
        int n = 0;
        test(piece,m,n,true);
    }


}
