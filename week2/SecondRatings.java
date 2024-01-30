
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String movieFile, String ratingFile ) {
        FirstRatings f = new FirstRatings();
        myMovies = f.loadMovies(movieFile);
        myRaters = f.loadRaters(ratingFile);
        
    }
    
    public int getMovieSize(){        
        return myMovies.size(); 
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
        for( Movie m : myMovies ){
            double avg = getAverageByID( m.getID(), minRaters );
            if( avg != 0.0 ){
                Rating r = new Rating(m.getTitle(),avg );
                myList.add( r );
            }
        }
        
        return myList;
    }
    
    public String getTitle( String IMDB ){
        for( Movie m : myMovies ){
            if( m.getID().equals(IMDB) ){
                return m.getTitle();
            }
        }
        return "NO SUCH MOVIE WITH IMDB = " + IMDB;
    }
    
    public String getID( String title ){
        for( Movie m : myMovies ){
            if( m.getTitle().equals(title) ){
                return m.getID();
            }
        }
        return "NO SUCH MOVIE WITH TITLE = " + title;
    }
    
}
