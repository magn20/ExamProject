package be;

public class Movie {
    private  int id;
    private String title;
    private float personalRating;
    private float imdbRating;
    private String filelink;
    private String lastview;
    private String categories;

    public Movie(int id, String title, float personalRating, float imdbRating, String filelink, String lastview) {
        this.id = id;
        this.title = title;
        this.personalRating = personalRating;
        this.imdbRating = imdbRating;
        this.filelink = filelink;
        this.lastview = lastview;
    }

    public Movie(String title, float personalRating, float imdbRating, String filelink, String lastview, String categories) {
        this.categories = categories;
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
    public void setTitle(String title) {
        this.title = title;
    }
    public float getPersonalRating(){
        return  personalRating;
    }

    public void setPersonalRating(float personalRating) {
        this.personalRating = personalRating;
    }

    public void setImdbRating(float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }

    public void setLastview(String lastview) {
        this.lastview = lastview;
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
