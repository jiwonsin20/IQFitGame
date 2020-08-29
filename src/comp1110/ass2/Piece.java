package comp1110.ass2;


import java.util.Arrays;

import static comp1110.ass2.PieceDirection.*;
import static comp1110.ass2.PieceType.*;
import static comp1110.ass2.PieceCoordinates.*;


/** This Class defines each Piece on
 *  (1) PieceType
 *  (2) PieceDirection
 *  (3) PieceCoordinates
 *
 *
 *
 */


public class Piece {

    public final PieceType type;
    public final PieceDirection dir;
    public final PieceCoordinates coords; // Jiwon 22/08

    Piece (PieceType type, PieceCoordinates coords, PieceDirection dir) {
        this.type = type;
        this.coords = coords; // Jiwon 22/08
        this.dir = dir;
    }

    /**
     * This sets the piece dimension, especially on what space it occupies.
     */

    public Piece [][] piece = new Piece [this.getXDimensions()] [this.getYDimensions()];

    public static void main(String[] args) {
        String str = "S21E";
        String str2 = "b43S";
        Piece [] pieces = toPieces(str);
        System.out.println(pieces.length);
        System.out.println(Arrays.deepToString(toPieces(str)));
        Piece [] array = toPieces(str);

        for (int k = 0; k < array.length; k++) {
            System.out.println(Arrays.deepToString(array[k].getCoords()));
        }
    }

    public static Piece toPiece(String placement) {
        PieceType type = PieceType.fromChar(placement);
        PieceCoordinates coords = getPCoord(placement);
        PieceDirection dir = PieceDirection.getDirection(placement);
        return new Piece(type, coords, dir);

    }

    public static Piece [] toPieces(String placement) {
        int r = 0;
        Piece [] pieces = new Piece[placement.length()/4];
        String str = "";
        for (int i = 0; i < placement.length(); i+=4) {
;
            str = placement.substring(i, i + 4);
            pieces[r] = toPiece(str);
            r++;
        }
        return pieces;
    }

    public PieceType [][] getCoords() {
        PieceType [][] array = new PieceType[getYDimensions()][getXDimensions()];
        switch (type) {
            case b:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j < getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = b;
                            else if (i == 1 && j == 0)
                                array[i][j] = b;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j < getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = b;
                            else if (i == 0 && j == 0)
                                array[i][j] = b;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = b;
                            else if (j == 3 && i == 0)
                                array[i][j] = b;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = b;
                            else if (j == 1 && i == 3)
                                array[i][j] = b;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;

            case B:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = B;
                            else if (i == 1 && j == 1)
                                array[i][j] = B;
                            else if (i == 1 && j == 3)
                                array[i][j] = B;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = B;
                            else if (j == 0 && i == 1)
                                array[i][j] = B;
                            else if (j == 0 && i == 3)
                                array[i][j] = B;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = B;
                            else if (i == 0 && j == 0)
                                array[i][j] = B;
                            else if (j == 2 && i == 0)
                                array[i][j] = B;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = B;
                            else if (j == 1 && i == 0)
                                array[i][j] = B;
                            else if (j == 1 && i == 2)
                                array[i][j] = B;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;
            case g:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = g;
                            else if (i == 1 && j == 1)
                                array[i][j] = g;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = g;
                            else if (j == 0 && i == 1)
                                array[i][j] = g;
                            else if (j == 0 && i == 3)
                                array[i][j] = g;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = g;
                            else if (i == 0 && j == 1)
                                array[i][j] = g;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = g;
                            else if (j == 1 && i == 1)
                                array[i][j] = g;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;
            case G:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = G;
                            else if (i == 1 && j == 0)
                                array[i][j] = G;
                            else if (i == 1 && j == 1)
                                array[i][j] = G;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1 || i == 0)
                                array[i][j] = G;
                            else if (j == 1 && i == 2)
                                array[i][j] = G;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = G;
                            else if (i == 0 && j == 1)
                                array[i][j] = G;
                            else if (i == 0 && j == 2)
                                array[i][j] = G;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1 || i == 2)
                                array[i][j] = G;
                            else if (j == 0 && i == 0)
                                array[i][j] = G;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;
            case i:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = PieceType.i;
                            else if (i == 1 && j == 2)
                                array[i][j] = PieceType.i;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = PieceType.i;
                            else if (j == 0 && i == 2)
                                array[i][j] = PieceType.i;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = PieceType.i;
                            else if (i == 0 && j == 0)
                                array[i][j] = PieceType.i;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = PieceType.i;
                            else if (j == 1 && i == 0)
                                array[i][j] = PieceType.i;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;
            case I:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = I;
                            else if (i == 1 && j == 1)
                                array[i][j] = I;
                            else if (i == 1 && j == 2)
                                array[i][j] = I;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = I;
                            else if (j == 0 && i == 1)
                                array[i][j] = I;
                            else if (j == 0 && i == 2)
                                array[i][j] = I;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = I;
                            else if (i == 0 && j == 0)
                                array[i][j] = I;
                            else if (j == 1 && i == 0)
                                array[i][j] = I;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = B;
                            else if (j == 1 && i == 0)
                                array[i][j] = B;
                            else if (j == 1 && i == 1)
                                array[i][j] = B;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;
            case l:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = l;
                            else if (i == 1 && j == 0)
                                array[i][j] = l;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = l;
                            else if (j == 0 && i == 0)
                                array[i][j] = l;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = l;
                            else if (j == 2 && i == 0)
                                array[i][j] = l;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = l;
                            else if (j == 1 && i == 2)
                                array[i][j] = l;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;
            case L:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = L;
                            else if (i == 1 && j == 0)
                                array[i][j] = L;
                            else if (i == 1 && j == 2)
                                array[i][j] = L;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0 || i == 2)
                                array[i][j] = L;
                            else if (j == 1 && i == 1)
                                array[i][j] = L;

                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = L;
                            else if (i == 0 && j == 0)
                                array[i][j] = L;
                            else if (j == 2 && i == 0)
                                array[i][j] = L;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0 || i == 2)
                                array[i][j] = L;
                            else if (j == 0 && i == 1)
                                array[i][j] = L;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;
            case n:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = n;
                            else if (i == 1 && j == 1)
                                array[i][j] = n;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = n;
                            else if (j == 0 && i == 1)
                                array[i][j] = n;
                            else if (j == 0 && i == 3)
                                array[i][j] = n;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = n;
                            else if (i == 0 && j == 1)
                                array[i][j] = n;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = n;
                            else if (j == 1 && i == 1)
                                array[i][j] = n;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;
            case N:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = N;
                            else if (i == 1 && j == 0)
                                array[i][j] = N;
                            else if (i == 1 && j == 2)
                                array[i][j] = N;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0 || i == 2)
                                array[i][j] = N;
                            else if (j == 1 && i == 1)
                                array[i][j] = N;

                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = N;
                            else if (i == 0 && j == 0)
                                array[i][j] = N;
                            else if (j == 2 && i == 0)
                                array[i][j] = N;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0 || i == 2)
                                array[i][j] = N;
                            else if (j == 0 && i == 1)
                                array[i][j] = N;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;
            case o:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j < getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = o;
                            else if (i == 1 && j == 1)
                                array[i][j] = o;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j < getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = o;
                            else if (i == 1 && j == 0)
                                array[i][j] = o;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = o;
                            else if (j == 2 && i == 0)
                                array[i][j] = o;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = o;
                            else if (j == 1 && i == 2)
                                array[i][j] = o;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;
            case O:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = O;
                            else if (i == 1 && j == 0)
                                array[i][j] = O;
                            else if (i == 1 && j == 2)
                                array[i][j] = O;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0 || i == 2)
                                array[i][j] = O;
                            else if (j == 1 && i == 1)
                                array[i][j] = O;
                            else if (j == 1 && i == 3)
                                array[i][j] = O;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = O;
                            else if (i == 0 && j == 1)
                                array[i][j] = O;
                            else if (i == 0 && j == 3)
                                array[i][j] = O;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = O;
                            else if (j == 1 && i == 1)
                                array[i][j] = O;
                            else if (j == 1 && i == 3)
                                array[i][j] = O;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;
            case p:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j < getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = p;
                            else if (i == 1 && j == 2)
                                array[i][j] = p;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j < getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = p;
                            else if (i == 2 && j == 0)
                                array[i][j] = p;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = p;
                            else if (j == 1 && i == 0)
                                array[i][j] = p;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = p;
                            else if (j == 1 && i == 1)
                                array[i][j] = p;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;
            case P:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = P;
                            else if (i == 1 && j == 0)
                                array[i][j] = P;
                            else if (i == 1 && j == 1)
                                array[i][j] = P;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0 || i == 1)
                                array[i][j] = P;
                            else if (j == 1 && i == 2)
                                array[i][j] = P;
                            else if (j == 1 && i == 3)
                                array[i][j] = P;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = P;
                            else if (i == 0 && j == 2)
                                array[i][j] = P;
                            else if (i == 0 && j == 3)
                                array[i][j] = P;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = P;
                            else if (j == 1 && i == 2)
                                array[i][j] = P;
                            else if (j == 1 && i == 3)
                                array[i][j] = P;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;

            case r:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j < getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = r;
                            else if (i == 1 && j == 0)
                                array[i][j] = r;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j < getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = r;
                            else if (i == 0 && j == 0)
                                array[i][j] = r;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = r;
                            else if (j == 3 && i == 0)
                                array[i][j] = r;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = r;
                            else if (j == 1 && i == 3)
                                array[i][j] = r;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;

            case R:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = R;
                            else if (i == 1 && j == 0)
                                array[i][j] = R;
                            else if (i == 1 && j == 3)
                                array[i][j] = R;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = R;
                            else if (j == 0 && i == 0)
                                array[i][j] = R;
                            else if (j == 0 && i == 3)
                                array[i][j] = R;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = R;
                            else if (i == 0 && j == 0)
                                array[i][j] = R;
                            else if (i == 0 && j == 3)
                                array[i][j] = R;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = R;
                            else if (j == 1 && i == 0)
                                array[i][j] = R;
                            else if (j == 1 && i == 3)
                                array[i][j] = R;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;

            case s:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j < getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = s;
                            else if (i == 1 && j == 1)
                                array[i][j] = s;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j < getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = s;
                            else if (i == 1 && j == 0)
                                array[i][j] = s;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = s;
                            else if (j == 2 && i == 0)
                                array[i][j] = s;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = s;
                            else if (j == 1 && i == 2)
                                array[i][j] = s;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;

            case S:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = S;
                            else if (i == 1 && j == 1)
                                array[i][j] = S;
                            else if (i == 1 && j == 2)
                                array[i][j] = S;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = S;
                            else if (j == 0 && i == 1)
                                array[i][j] = S;
                            else if (j == 0 && i == 2)
                                array[i][j] = S;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = S;
                            else if (i == 0 && j == 1)
                                array[i][j] = S;
                            else if (i == 0 && j == 2)
                                array[i][j] = S;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = S;
                            else if (j == 1 && i == 1)
                                array[i][j] = S;
                            else if (j == 1 && i == 2)
                                array[i][j] = S;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;

            case y:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j < getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = y;
                            else if (i == 1 && j == 3)
                                array[i][j] = y;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j < getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = y;
                            else if (i == 3 && j == 0)
                                array[i][j] = y;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = y;
                            else if (j == 0 && i == 0)
                                array[i][j] = y;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = y;
                            else if (j == 1 && i == 0)
                                array[i][j] = y;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;

            case Y:
                if (dir == NORTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = Y;
                            else if (i == 1 && j == 2)
                                array[i][j] = Y;
                            else if (i == 1 && j == 3)
                                array[i][j] = Y;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = Y;
                            else if (j == 0 && i == 2)
                                array[i][j] = Y;
                            else if (j == 0 && i == 3)
                                array[i][j] = Y;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = Y;
                            else if (i == 0 && j == 0)
                                array[i][j] = Y;
                            else if (i == 0 && j == 1)
                                array[i][j] = Y;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getYDimensions(); i++) {
                        for (int j = 0; j <getXDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = Y;
                            else if (j == 1 && i == 0)
                                array[i][j] = Y;
                            else if (j == 1 && i == 1)
                                array[i][j] = Y;
                            else
                                array[i][j] = null;
                        }
                    }
                }
                break;

        }
    return array;
    }



    /**
     * Knowing the piece's direction, return the dimensions of the piece.
     * Knowing that whatever the Piece is, if the Direction is North or South, dimension will always be
     * Spine length.
     *
     * And if the direction is West or East, dimension will always be 2 by Spine length.
     *
     * @return An integer value that indicates the width of piece.
     */

    public int getXDimensions() {
        int xValue = 0;

        if (dir == EAST || dir == WEST)
            xValue = 2;
        else if (dir == NORTH || dir == SOUTH) {
            assert type != null;
            xValue = type.getSpineNum();
        }

        return xValue;
    }

    /**
     * Knowing the piece's direction, return the dimensions of the piece.
     * Knowing that whatever the Piece is, if the Direction is North or South, dimension will always be
     * Spine length.
     *
     * And if the direction is West or East, dimension will always be 2 by Spine length.
     *
     * @return An integer value that indicates the height of piece.
     */

    public int getYDimensions() {
        int yValue = 0;
        if (dir == NORTH || dir == SOUTH)
            yValue = 2;
        else if (dir == WEST || dir == EAST) {
            assert type != null;
            yValue = type.getSpineNum();
        }

        return yValue;
    }


    /** Given a string (two character) position, extract the value of X coordinate.
     *  Used charAt() to extract the relevant character and since it will be in ASCII code
     *
     *  returns integer value of x coordinate
     *
     *  Jiwon 22/08
     **/


    public static int getXCoordinate(String placement) {
        return placement.charAt(1) - 48;
    }

    /** Given a string (two character) position, extract the value of Y coordinate.
     *  Used charAt() to extract the relevant character and since it will be in ASCII code
     *
     *  Param: String type input
     *  Returns: Y coordinate of this Piece.
     *
     *  Jiwon 22/08
     **/

    public static int getYCoordinate(String placement) {
        return placement.charAt(2) - 48;
    }

    public PieceDirection getDir() {
        return dir;
    }
}
