
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {

    
        public void printAverageRatings (){
        //String moviefileName = "ratedmovies_short.csv";
        String moviefileName = "ratedmoviesfull.csv";
        //String raterfileName = "data/ratings_short.csv";
        String raterfileName = "data/ratings.csv";
        MovieDatabase.initialize(moviefileName);
        
        ThirdRatings r = new ThirdRatings (raterfileName);
        System.out.println(moviefileName + " contains # movies: " + MovieDatabase.size());
        System.out.println(raterfileName + " contains # raters: " + r.getRaterSize());
        
        //YearAfterFilter yf = new YearAfterFilter(2012);
        TrueFilter af = new TrueFilter();
        //GenreFilter gf = new YearAfterFilter("Romance");
        //af.addFilter(gf);
        //af.addFilter(yf);
        
        
        ArrayList<Rating> myRatingList = r.getAverageRatingsByFilter (35,af);
        Collections.sort(myRatingList);
        
        System.out.println("priting ratings with min#Raters = 35, # found = " + myRatingList.size() );
        for( Rating myR : myRatingList){
            System.out.println(myR + " : " + MovieDatabase.getTitle(myR.getItem())) ;
        }
    }
    
    
    public void printAverageRatingsByYear (){
        //String moviefileName = "ratedmovies_short.csv";
        String moviefileName = "ratedmoviesfull.csv";
        //String raterfileName = "data/ratings_short.csv";
        String raterfileName = "data/ratings.csv";
        MovieDatabase.initialize(moviefileName);
        
        ThirdRatings r = new ThirdRatings (raterfileName);
        System.out.println(moviefileName + " contains # movies: " + MovieDatabase.size());
        System.out.println(raterfileName + " contains # raters: " + r.getRaterSize());
        
        YearAfterFilter yf = new YearAfterFilter(2000);
        AllFilters af = new AllFilters();
        //GenreFilter gf = new YearAfterFilter("Romance");
        //af.addFilter(gf);
        af.addFilter(yf);
        
        
        ArrayList<Rating> myRatingList = r.getAverageRatingsByFilter (20,af);
        Collections.sort(myRatingList);
        
        System.out.println("priting ratings with min#Raters = 20 and year after 2000, #found = " + myRatingList.size());
        for( Rating myR : myRatingList){
            //System.out.println(myR + " : " + MovieDatabase.getTitle(myR.getItem()) + " : " + MovieDatabase.getYear(myR.getItem())) ;
        }
    }
    
        public void printAverageRatingsByGenre  (){
        //String moviefileName = "ratedmovies_short.csv";
        String moviefileName = "ratedmoviesfull.csv";
        //String raterfileName = "data/ratings_short.csv";
        String raterfileName = "data/ratings.csv";
        MovieDatabase.initialize(moviefileName);
        
        ThirdRatings r = new ThirdRatings (raterfileName);
        System.out.println(moviefileName + " contains # movies: " + MovieDatabase.size());
        System.out.println(raterfileName + " contains # raters: " + r.getRaterSize());
        
        AllFilters af = new AllFilters();
        YearAfterFilter yf = new YearAfterFilter(1990);
        af.addFilter(yf);
        GenreFilter gf = new GenreFilter("Drama");
        af.addFilter(gf);
        
        
        
        ArrayList<Rating> myRatingList = r.getAverageRatingsByFilter (8,af);
        Collections.sort(myRatingList);
        
        System.out.println("priting ratings with min#Raters = 8, year=1990, and genre =Comedy , #found = " + myRatingList.size());
        for( Rating myR : myRatingList){
            //System.out.println(myR + " : " + MovieDatabase.getTitle(myR.getItem()) + " : " + MovieDatabase.getGenres(myR.getItem())) ;
        }
    }

    
    public void printAverageRatingsByMinutes(){
        //String moviefileName = "ratedmovies_short.csv";
        String moviefileName = "ratedmoviesfull.csv";
        //String raterfileName = "data/ratings_short.csv";
        String raterfileName = "data/ratings.csv";
        MovieDatabase.initialize(moviefileName);
        
        ThirdRatings r = new ThirdRatings (raterfileName);
        System.out.println(moviefileName + " contains # movies: " + MovieDatabase.size());
        System.out.println(raterfileName + " contains # raters: " + r.getRaterSize());
        
        AllFilters af = new AllFilters();
        //YearAfterFilter yf = new YearAfterFilter(2000);
       
        //GenreFilter gf = new GenreFilter("Crime");
        //af.addFilter(gf);
        //af.addFilter(yf);
        MinutesFilter mf = new MinutesFilter(105,135);
        af.addFilter(mf);
        
        ArrayList<Rating> myRatingList = r.getAverageRatingsByFilter (5,af);
        Collections.sort(myRatingList);
        
        System.out.println("priting ratings with min#Raters = 5 and minutes filter = " + myRatingList.size());
        for( Rating myR : myRatingList){
            //System.out.println(myR + " : " + MovieDatabase.getTitle(myR.getItem()) + " : " + MovieDatabase.getMinutes(myR.getItem())) ;
        }
    }
    
        public void printAverageRatingsByDirectors (){
        //String moviefileName = "ratedmovies_short.csv";
        String moviefileName = "ratedmoviesfull.csv";
        //String raterfileName = "data/ratings_short.csv";
        String raterfileName = "data/ratings.csv";
        MovieDatabase.initialize(moviefileName);
        
        ThirdRatings r = new ThirdRatings (raterfileName);
        System.out.println(moviefileName + " contains # movies: " + MovieDatabase.size());
        System.out.println(raterfileName + " contains # raters: " + r.getRaterSize());
        
        AllFilters af = new AllFilters();

        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        af.addFilter(df);
        MinutesFilter mf = new MinutesFilter(90,180);
        af.addFilter(mf);
        
        
        
        ArrayList<Rating> myRatingList = r.getAverageRatingsByFilter (3,af);
        Collections.sort(myRatingList);
        
        System.out.println("priting ratings with min#Raters = 3 and directors & minutes filter = " + myRatingList.size());
        for( Rating myR : myRatingList){
            //System.out.println(myR + " : " + MovieDatabase.getTitle(myR.getItem()) + " : " + MovieDatabase.getDirector(myR.getItem())) ;
        }
    }
}
