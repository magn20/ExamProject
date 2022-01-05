package be;

public class Movie {
    private  int id;
    private String title;
    private float personalRating;
    private float imdbRating;
    private String filelink;
    private String lastview;

    public Movie(int id, String title, float personalRating, float imdbRating, String filelink, String lastview) {
        this.id = id;
        this.title = title;
        this.personalRating = personalRating;
        this.imdbRating = imdbRating;
        this.filelink = filelink;
        this.lastview = lastview;
    }
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public float getPersonalRating(){
        return  personalRating;
    }
    public float getImdbRating (){
        return imdbRating;
    }
    public String getFilelink (){
        return filelink;
    }
    public String getLastview (){
        return lastview;
    }
}
