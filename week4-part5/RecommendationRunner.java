
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class RecommendationRunner implements Recommender{
    
    public ArrayList<String> getItemsToRate (){
        FourthRatings fourth = new FourthRatings();
        Random ran = new Random(); 
        ArrayList<String> IMDBs = MovieDatabase.filterBy( new TrueFilter());
        ArrayList<String> myMovies = new ArrayList<String>();
        //get 10 movies
        int numToGet = 10;
        for( int i = 0; i < numToGet ; i++ ){
            String IMDB = IMDBs.get(ran.nextInt(IMDBs.size()));
            if( fourth.checkPopularity(IMDB) ) {
                myMovies.add(IMDB);
            }
            else{
                numToGet ++;           
                //if( numToGet > 100 ) return myMovies; //break out if failing to find movies //failsafe
            }
        }
        return myMovies;
    }

    public void printRecommendationsFor (String webRaterID){
        FourthRatings fourth = new FourthRatings();
        Rater me = RaterDatabase.getRater(webRaterID);
        ArrayList<Rating> ratings = fourth.getSimilarRatings ( webRaterID, 100, 5, new TrueFilter() );
        if(ratings.size() == 0 ) {
            System.out.println("No reccomendations<br><br>" ); 
            return;
        }
        System.out.println("<table>  <tr>    <th>Movie</th>    <th>Similarity score</th>    <th>Genre</th>  </tr>");
        
        int numToPrint = 10;
        for( int i = 0; i < numToPrint; i++ ){
            Rating r = ratings.get(i);
            if( me.hasRating(r.getItem())){
                numToPrint ++; //skip this item
            }
            else {
                System.out.print("<tr>");
                System.out.print("<td>" + MovieDatabase.getTitle(r.getItem()) + "</td>" );
                System.out.print("<td>" + r.getValue() + "</td>" );
                System.out.print("<td>" + MovieDatabase.getGenres(r.getItem()) + "</td>" );
                System.out.print("</tr>");
            }
        }
        System.out.println("</table>");
    }
    
}
