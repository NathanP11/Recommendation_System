/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        this("ratings.csv");
    }
    
    public ThirdRatings( String ratingFile ) {
        FirstRatings f = new FirstRatings();
        myRaters = f.loadRaters(ratingFile);
    }

    public int getRaterSize(){        
        return myRaters.size(); 
    }
    
    public double getAverageByID ( String IMDB , int minRaters ) {
        double numRatings = 0.0;
        double ratingSum = 0.0;
        for( Rater r : myRaters ){
            if( r.hasRating(IMDB) ){
                numRatings++;
                ratingSum = ratingSum + r.getRating(IMDB);
            }
        }
        if( numRatings >= minRaters ){
            return ratingSum / numRatings;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings( int minRaters ){
        ArrayList<Rating> myList = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for( String m : movies ){
            double avg = getAverageByID( m, minRaters );
            if( avg != 0.0 ){
                Rating r = new Rating( m ,avg );
                myList.add( r );
            }
        }
        
        return myList;
    }
    
        public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters , Filter filterCriteria ){
        ArrayList<Rating> myRatingList = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
        for( String m : myMovies ){
            double avg = getAverageByID( m, minimalRaters );
            if( avg != 0.0 ){
                Rating r = new Rating( m ,avg );
                myRatingList.add( r );
            }
        }
        return myRatingList;
    }
    
    
}
