
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    
    public void printSimilarRatings (){
        //String moviefileName = "ratedmovies_short.csv"; String raterfileName = "ratings_short.csv";
        String raterfileName = "ratings.csv";   String moviefileName = "ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefileName);        RaterDatabase.initialize(raterfileName);
        
        FourthRatings r = new FourthRatings ();
        System.out.println(moviefileName + " contains # movies: " + MovieDatabase.size());
        System.out.println(raterfileName + " contains # raters: " + RaterDatabase.size());
        TrueFilter af = new TrueFilter();

        ArrayList<Rating> myRatingList = r.getSimilarRatings ( "71" , 20 , 5 , af );
        
        System.out.println("priting top10 similar ratings with min#Raters = 3, # found = " + myRatingList.size() );
        for( Rating myR : myRatingList){
            System.out.println(myR + " : " + MovieDatabase.getTitle(myR.getItem())) ;
        }
    }
    
        public void printSimilarRatingsByGenre  (){
        //String moviefileName = "ratedmovies_short.csv"; String raterfileName = "ratings_short.csv";
        String raterfileName = "ratings.csv";   String moviefileName = "ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefileName);        RaterDatabase.initialize(raterfileName);
        
        FourthRatings r = new FourthRatings ();
        System.out.println(moviefileName + " contains # movies: " + MovieDatabase.size());
        System.out.println(raterfileName + " contains # raters: " + RaterDatabase.size());
        AllFilters af = new AllFilters();
        GenreFilter gf = new GenreFilter("Mystery");
        af.addFilter(gf);

        ArrayList<Rating> myRatingList = r.getSimilarRatings ( "964" , 20 , 5 , af );
        
        System.out.println("priting top20 similar ratings with min#Raters = 5, # found = " + myRatingList.size() );
        for( Rating myR : myRatingList){
            System.out.println(myR + " : " + MovieDatabase.getTitle(myR.getItem())) ;
        }
    }
    
    public void printSimilarRatingsByDirector (){
        //String moviefileName = "ratedmovies_short.csv"; String raterfileName = "ratings_short.csv";
        String raterfileName = "ratings.csv";   String moviefileName = "ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefileName);        RaterDatabase.initialize(raterfileName);
        
        FourthRatings r = new FourthRatings ();
        System.out.println(moviefileName + " contains # movies: " + MovieDatabase.size());
        System.out.println(raterfileName + " contains # raters: " + RaterDatabase.size());
        AllFilters af = new AllFilters();
        DirectorsFilter gf = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        af.addFilter(gf);

        ArrayList<Rating> myRatingList = r.getSimilarRatings ( "120" , 10 , 2 , af );
        
        System.out.println("priting top10 similar ratings with min#Raters = 2, # found = " + myRatingList.size() );
        for( Rating myR : myRatingList){
            System.out.println(myR + " : " + MovieDatabase.getTitle(myR.getItem())) ;
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes  (){
        //String moviefileName = "ratedmovies_short.csv"; String raterfileName = "ratings_short.csv";
        String raterfileName = "ratings.csv";   String moviefileName = "ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefileName);        RaterDatabase.initialize(raterfileName);
        
        FourthRatings r = new FourthRatings ();
        System.out.println(moviefileName + " contains # movies: " + MovieDatabase.size());
        System.out.println(raterfileName + " contains # raters: " + RaterDatabase.size());
        AllFilters af = new AllFilters();
        MinutesFilter yf = new MinutesFilter(80,160);
        GenreFilter gf = new GenreFilter("Drama");
        af.addFilter(gf);        af.addFilter(yf);

        ArrayList<Rating> myRatingList = r.getSimilarRatings ( "168" , 10 , 3 , af );
        
        System.out.println("priting top10 similar ratings with min#Raters = 3, # found = " + myRatingList.size() );
        for( Rating myR : myRatingList){
            System.out.println(myR + " : " + MovieDatabase.getTitle(myR.getItem())) ;
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes (){
        //String moviefileName = "ratedmovies_short.csv"; String raterfileName = "ratings_short.csv";
        String raterfileName = "ratings.csv";   String moviefileName = "ratedmoviesfull.csv";
        MovieDatabase.initialize(moviefileName);        RaterDatabase.initialize(raterfileName);
        
        FourthRatings r = new FourthRatings ();
        System.out.println(moviefileName + " contains # movies: " + MovieDatabase.size());
        System.out.println(raterfileName + " contains # raters: " + RaterDatabase.size());
        AllFilters af = new AllFilters();
        MinutesFilter yf = new MinutesFilter(70,200);
        YearAfterFilter gf = new YearAfterFilter(1975);
        af.addFilter(gf);        af.addFilter(yf);

        ArrayList<Rating> myRatingList = r.getSimilarRatings ( "314" , 10 , 5 , af );
        
        System.out.println("priting top10 similar ratings with min#Raters = 5, # found = " + myRatingList.size() );
        for( Rating myR : myRatingList){
            System.out.println(myR + " : " + MovieDatabase.getTitle(myR.getItem())) ;
        }
    }
    
    public void printAverageRatings (){
        //String moviefileName = "ratedmovies_short.csv"; String raterfileName = "ratings_short.csv";
        String moviefileName = "ratedmoviesfull.csv";  String raterfileName = "ratings.csv";
        MovieDatabase.initialize(moviefileName);        RaterDatabase.initialize(raterfileName);
        
        FourthRatings r = new FourthRatings (raterfileName);
        System.out.println(moviefileName + " contains # movies: " + MovieDatabase.size());
        System.out.println(raterfileName + " contains # raters: " + RaterDatabase.size());

        TrueFilter af = new TrueFilter();

        ArrayList<Rating> myRatingList = r.getAverageRatingsByFilter (35,af);
        Collections.sort(myRatingList);
        
        System.out.println("priting ratings with min#Raters = 35, # found = " + myRatingList.size() );
        for( Rating myR : myRatingList){
            System.out.println(myR + " : " + MovieDatabase.getTitle(myR.getItem())) ;
        }
    }
    
        public void printAverageRatingsByGenre  (){
        //String moviefileName = "ratedmovies_short.csv"; String raterfileName = "ratings_short.csv";
        String moviefileName = "ratedmoviesfull.csv";        String raterfileName = "ratings.csv";
        MovieDatabase.initialize(moviefileName);        RaterDatabase.initialize(raterfileName);
        
        FourthRatings r = new FourthRatings (raterfileName);
        System.out.println(moviefileName + " contains # movies: " + MovieDatabase.size());
        System.out.println(raterfileName + " contains # raters: " + RaterDatabase.size());
        
        AllFilters af = new AllFilters();
        YearAfterFilter yf = new YearAfterFilter(1990);
        af.addFilter(yf);
        GenreFilter gf = new GenreFilter("Drama");
        af.addFilter(gf);
        
        ArrayList<Rating> myRatingList = r.getAverageRatingsByFilter (8,af);
       
        System.out.println("priting ratings with min#Raters = 8, year=1990, and genre =Comedy , #found = " + myRatingList.size());
        for( Rating myR : myRatingList){
            //System.out.println(myR + " : " + MovieDatabase.getTitle(myR.getItem()) + " : " + MovieDatabase.getGenres(myR.getItem())) ;
        }
    }
}
