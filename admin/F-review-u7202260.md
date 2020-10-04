## Code Review

Reviewed by: < Mingxuan Wang >, < u7202260 >

Reviewing code written by: < Mou Di > < u6553212 >

Component: < insertPiecePlacement(List<String> piecePlacements, String piecePlacement)>

### Comments 

<
insertPiecePlacement(List<String> piecePlacements, String piecePlacement) method insert the new placement to the original placement.
1.List<String> piecePlacements is the original piece placement that already exists.
2.String piecePlacement is the placement that need to insert to the existed placements.
  
  Mou Di's code is good to convert all the placement string's character to the lower character so that there won't be character errors occur.
  Then she uses the loop method to check the sequence of the piece color.
  Finally, she inserts the new placement to the original placement in a right sequence.
  
  Mou checks the potential errors that might occur in the method so she avvoid it by the character convertion method. It is very useful in the code writing.
  Mou could add more comments about the code so that the viewer can understand the code easily.
  
  >


