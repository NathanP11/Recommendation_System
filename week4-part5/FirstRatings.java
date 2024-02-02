import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    
    public FirstRatings(){
    
    }
    
    public ArrayList<Movie> loadMovies ( String filename){
        FileResource fr = new FileResource( filename );
	CSVParser myParser = fr.getCSVParser();
	CSVRecord largestSoFar = null;
	ArrayList<Movie> myMovies = new ArrayList<Movie>();
	for (CSVRecord currentRow : myParser) {
	    int theMinutes = Integer.parseInt(currentRow.get("minutes"));	    String anID = currentRow.get("id");
	    String aTitle = currentRow.get("title");	    String aYear = currentRow.get("year");
	    String theGenres = currentRow.get("genre");	    String aDirector = currentRow.get("director");
	    String aCountry = currentRow.get("country");	    String aPoster = currentRow.get("poster");
	    Movie myMovie = new Movie(anID, aTitle, aYear, theGenres, aDirector, aCountry, aPoster, theMinutes);
	    myMovies.add(myMovie);
	}
	return myMovies;
 }
 
    public void testLoadMovies(){
        //String fileName = "data/ratedmovies_short.csv";
        String fileName = "data/ratedmoviesfull.csv";
        ArrayList<Movie> ms = loadMovies(fileName);
        HashMap<String,Integer> DirectorsMap = new HashMap<String,Integer>();
        int countGenre = 0;
        int count150Mins = 0;
        int largest = 0;
        
        for( Movie m : ms ){
            //System.out.println(m);
            if(m.getGenres().indexOf("Comedy") > -1){
                countGenre++;
            }
            if( m.getMinutes() > 150 ){
                count150Mins++;
            }
            String d = m.getDirector();
            String[] ds = d.split(",");
            for(String director :ds ){
                if(DirectorsMap.containsKey(director)){
                    DirectorsMap.put(director,(DirectorsMap.get(director)+1));
                }
                else{
                    DirectorsMap.put(director,1);
                }
                if(DirectorsMap.get(director) > largest ) {
                    largest = DirectorsMap.get(director);
                }
            }
        }
        
        System.out.println("# movies found: " + ms.size());
        System.out.println("# comedy movies found: " + countGenre);
        System.out.println("# 150+ minute movies found: " + count150Mins);
        System.out.println("largest # movies by 1 director: " + largest);
        
        for( String director :  DirectorsMap.keySet() ){
            if( DirectorsMap.get(director) == largest ){
                System.out.println( director + " directed " + largest + " movies");
            }
        }
        
        
    }
   
    
        public ArrayList<Rater> loadRaters ( String filename){
        FileResource fr = new FileResource( filename );
	CSVParser myParser = fr.getCSVParser();
	CSVRecord largestSoFar = null;
	ArrayList<Rater> myRaters = new ArrayList<Rater>();
	Rater myRater = new EfficientRater ("");
	
	for (CSVRecord currentRow : myParser) {
	    double rating = Double.parseDouble(currentRow.get("rating"));
	    double time = Double.parseDouble(currentRow.get("time"));
	    String movie_id = currentRow.get("movie_id");
	    String rater_id = currentRow.get("rater_id");
	    if( rater_id.equals(myRater.getID())){
	        myRater.addRating(movie_id, rating);
	    }
	    else{
               myRater = new EfficientRater (rater_id);
               myRater.addRating(movie_id, rating);
               myRaters.add(myRater);
	    }
	}
	return myRaters;
    }
 
    public void testLoadRaters(){
        //String fileName = "data/ratings_short.csv";
        String fileName = "data/ratings.csv";
        ArrayList<Rater> ms = loadRaters(fileName);
        HashMap<String,Integer> RatersMap = new HashMap<String,Integer>();
        int largest = 0;
        Rater largestRater = null;
        
        String printRaterWithID = "193";
        Rater printRater = null;
        
        for( Rater m : ms ){
            ArrayList<String> moviesRated = m.getItemsRated();
            //System.out.println("rater ID : " + m.getID() +" rated " + moviesRated.size() + " moveies");
            
            int count = 0;
            for (String s : moviesRated){
                Double myRating = m.getRating(s);
                //System.out.println("movie ID : " + s +" rated " + myRating);
                count++;
                if( count > largest ){
                    largest = count;
                    largestRater = m;
                }
            }
            if(m.getID().equals(printRaterWithID)){
                printRater = m;
            }
            
        }
        
        
        System.out.println("# raters found: " + ms.size());
        System.out.println("largest# rater was id: " + largestRater.getID() + " @ " + largestRater.numRatings() + " reviews");
        
        System.out.println("rater ID + " + printRaterWithID + "rated this many movies: " + printRater.numRatings());
        
        //Add code to find the number of ratings a particular movie has. If you run this code on the file ratings_short.csv for the movie “1798709”, you will see it was rated by four raters.
        int numRatings = 0;
        String movieIMDB = "1798709";
        for( Rater m : ms ){
            //ArrayList<String> moviesRated = m.getItemsRated();

            if( m.hasRating(movieIMDB) ){numRatings++;}
        }
        System.out.println(movieIMDB + " has " + numRatings +  " ratings"); 
        
        int numMoviesRated = 0;
        HashSet<String> myMovieSet = new HashSet<String>();
        //Add code to determine how many different movies have been rated by all these raters. If you run this code on the file ratings_short.csv, you will see there were four movies rated. 
        for( Rater m : ms ){
            ArrayList<String> moviesRated = m.getItemsRated();
            for (String s : moviesRated){
                if( !myMovieSet.contains(s)){
                    myMovieSet.add(s);
                }
            }
        }
        System.out.println("# unique movies reviewed = " + myMovieSet.size() ); 
        
    }
}
