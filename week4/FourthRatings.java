
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {

    public FourthRatings() {
        //this("ratings.csv");
    }
    
    public FourthRatings( String ratingFile ) {
        //FirstRatings f = new FirstRatings();        myRaters = f.loadRaters(ratingFile);
    }

    public void printAllRaters(){
        //String raterfileName = "ratings.csv";   String moviefileName = "ratedmoviesfull.csv";
        String moviefileName = "ratedmovies_short.csv"; String raterfileName = "ratings_short.csv";
        MovieDatabase.initialize(moviefileName);        RaterDatabase.initialize(raterfileName);
        for( Rater r : RaterDatabase.getRaters()){
            for( String s : r.getItemsRated()){
                System.out.println(s + " : " + r.getRating(s));
            }  
        }
    }
    
        private double dotProduct( Rater me, Rater r) {
            double product = 0;
            
            for( String s : me.getItemsRated()){
                if( r.hasRating(s) )  product += (me.getRating(s)-5) * (r.getRating(s)-5);
            }
            return product;
        }
        
        private ArrayList<Rating> getSimilarities( String id ){
            ArrayList<Rating> ret = new ArrayList<Rating>();
            Rater me = RaterDatabase.getRater(id);
            for( Rater r : RaterDatabase.getRaters() ){
                if( !r.getID().equals( id ) ) {
                    double product = dotProduct( me, r );
                    if( product >= 0 ) ret.add( new Rating( r.getID() , product) );
                }
            }
            //Collections.sort(ret);
            Collections.sort(ret, Collections.reverseOrder());
            //for( Rating r : ret ){                System.out.println(r);            }
            return ret;
        }
    
    public ArrayList<Rating> getSimilarRatings ( String id, int numSimilarRaters, int minimalRaters, Filter f ){
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        for(String movieID : MovieDatabase.filterBy(f)) {
            Double val = 0.0;
            int count = 0;
            for(int k=0; k < numSimilarRaters; k++) {
                Rating r = list.get(k);
                Rater abc = RaterDatabase.getRater(r.getItem());    // use Rater id and weight in r
                if( abc.hasRating(movieID) ){
                    val += r.getValue() * abc.getRating(movieID);       // to update running total
                    count ++;
                }
            }
            if( count >= minimalRaters ) ret.add(new Rating( movieID , (val/count) )); //weighted average
        }
        //Collections.sort(ret, Collections.reverseOrder());
        Collections.sort(ret);
        return ret;
    }
        
    public double getAverageByID ( String IMDB , int minRaters ) {
        double numRatings = 0.0;
        double ratingSum = 0.0;
        for( Rater r : RaterDatabase.getRaters() ){
            if( r.hasRating(IMDB) ){
                numRatings++;
                ratingSum = ratingSum + r.getRating(IMDB);
            }
        }
        if( numRatings >= minRaters ) return ratingSum / numRatings;   
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
