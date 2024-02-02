
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String[] Director;
    
    public DirectorsFilter(String director) {
        Director = director.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        String directors = MovieDatabase.getDirector(id);
        for( String d : Director ){
            if(directors.indexOf(d) > -1) return true;
        }
        return false;
    }
}
