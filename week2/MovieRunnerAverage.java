
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings (){
        //String moviefileName = "data/ratedmovies_short.csv";
        String moviefileName = "data/ratedmoviesfull.csv";
        //String raterfileName = "data/ratings_short.csv";
        String raterfileName = "data/ratings.csv";
        
        SecondRatings r = new SecondRatings(moviefileName,raterfileName);
        System.out.println(moviefileName + " contains # movies: " + r.getMovieSize());
        System.out.println(moviefileName + " contains # raters: " + r.getRaterSize());

        ArrayList<Rating> myRatingList = r.getAverageRatings(12);
        Collections.sort(myRatingList);
        
        System.out.println("priting ratings with min#Raters = 12");
        for( Rating myR : myRatingList){
            System.out.println(myR);
        }
    }
    
    public void getAverageRatingOneMovie(){
        //String moviefileName = "data/ratedmovies_short.csv";
        String moviefileName = "data/ratedmoviesfull.csv";
        //String raterfileName = "data/ratings_short.csv";
        String raterfileName = "data/ratings.csv";
        SecondRatings r = new SecondRatings(moviefileName,raterfileName);
        String title = "The Maze Runner";
        
        System.out.println("priting ratings for " + title );
        String IMDB = r.getID(title);
        System.out.println( r.getAverageByID(IMDB,1) );
        
        title = "Moneyball";
        System.out.println("priting ratings for " + title );
        IMDB = r.getID(title);
        System.out.println( r.getAverageByID(IMDB,1) );
        
        title = "Vacation";
        System.out.println("priting ratings for " + title );
        IMDB = r.getID(title);
        System.out.println( r.getAverageByID(IMDB,1) );
        
    }
    
}
