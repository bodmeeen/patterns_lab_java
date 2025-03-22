package creational;
import java.util.ArrayList;
import java.util.List;

class SingMusicLib{                    //Одинак
    private static SingMusicLib instance = null;
    private List<Song> songs = new ArrayList<>();

    private SingMusicLib(){};

    public static SingMusicLib getInstance(){
        if (instance == null){
            instance = new SingMusicLib();
        }
        return instance;
    }
    public void addSong(Song song){
        songs.add(song);
    }

    public void showSongs(){
        for (Song song : songs){
            System.out.println(song);
        }
    }
}

abstract class Song{
    protected String title;
    protected String artist;
    protected String format;
    protected String genre;

    public Song(String title, String artist, String format, String genre){
        this.title = title;
        this.artist = artist;
        this.format = format;
        this.genre = genre;
    }

    @Override
    public String toString(){
        return "Song: "+title + " by " +artist + " ,format: " + format + " ,genre: " + genre;
    }
    public abstract Song clone();                    //Прототип
}

class rockSong extends Song{
    public rockSong (String title, String artist, String format, String genre){
        super (title, artist, format, genre);
    }
    @Override
    public rockSong clone() {                        //Прототип
        return new rockSong(title, artist, format, genre);
    }
}

class electronicSong extends Song{
    public electronicSong(String title, String artist, String format, String genre){
        super (title, artist, format, genre);
    }
    @Override
    public electronicSong clone() {                  //Прототип
        return new electronicSong(title, artist, format, genre);
    }
}


class absFactory{                     //Абстрактна фабрика
    public genreFactory getGenreFactory(String type) {
        if ("modern".equals(type.toLowerCase())) {
            return new modernGenFactory();
        }
        else if("electronic".equalsIgnoreCase(type)){
            return new electronicGenFactory();
        }
        else{
            throw new IllegalArgumentException("Unknown genre factory");
        }
    }
}

abstract class genreFactory{           //Фабрика
    public abstract Song getSong(String title, String artist, String format, String genre);
}

class modernGenFactory extends genreFactory{
    @Override
    public Song getSong(String title, String artist, String format,String genre){
        return new rockSong(title, artist, format, genre);
        }
}


class electronicGenFactory extends genreFactory{
    @Override
    public Song getSong(String title, String artist, String format, String genre){
        return new electronicSong(title, artist, format, genre);
    }
}


class songBuilder{                      //Будівельник
    private String title;
    private String artist;
    private String format;
    private String genre;

    public songBuilder setTitle(String title){
        this.title = title;
        return this;
    }
    public songBuilder setArtist(String artist){
        this.artist = artist;
        return this;
    }
    public songBuilder setFormat(String format){
        this.format = format;
        return this;
    }
    public songBuilder setGenre(String genre){
        this.genre = genre;
        return this;
    }
    public Song build(){
        return new rockSong(title, artist, format, genre);
    }
}