## Code Review

Reviewed by: < Jiwon Sin >, < u5666033 >

Reviewing code written by: < Mingxuan Wang > < u7202260 >

Component: < Task 2: isPiecePlacementWellFormed()>

### Comments 

<
isPiecePlacementWellFormed() method checks whether the piece meets certain conditions.
These conditions are
    1. placement consists of four characters
    2. first chracter represents the piece type
    3. second character is the x coordinate on the board,
    4. Thrid character is the y coordinate on the board,
    5. Last character represents the direction of the piece.
  
  Mingxuan's code did display some insights to it, discussing every cases in detail.
  
  His method states the conditions well, by using charAt() to compare the validity of pieces.
  It features in-depth and thorough comparison of the piece Strings.
  Also it is quite easy to understand and test whether the code was written correctly.
  
  However, its clear that the method is quite bulky and could be simplified.
  Instead of comparing every single pieces, the method could call different smaller functions that
  decides the validity of the piece String.
  For example, it is possible to diversify by making an Array that contains all 20 characters and use
  iteration to compare one by one.
  Also the method is quite bulky.
  For example, it is possible to write if-statements to filter out any obscure placement Strings
  and simplify the code.
  On top of that, the code lacks comments that could explain the rational behind writing the code this way.
  
  Overall Mingxuan's method does check the validity of piece String thoroughly with no loopholes
  and the style was consistent throughout the entire method, which was convenient for others to read.>


