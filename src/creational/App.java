package creational;
import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) {
        SingMusicLib library = SingMusicLib.getInstance();
        AbsFactory absFactory = new AbsFactory();
        SongBuilder builder = new SongBuilder();

        GenreFactory modernFactory = absFactory.getGenreFactory("modern");
        Song song1 = modernFactory.getSong("Bohemian Rhapsody", "Queen", "Vinyl", "rock");
        GenreFactory electronicFactory = absFactory.getGenreFactory("electronic");
        Song song2 = electronicFactory.getSong("One More Time", "Daft Punk", "Vinyl", "house");
        Song song3 = builder.setTitle("Lithium")
                .setArtist("Nirvana")
                .setFormat("CD")
                .setGenre("rock")
                .build();

        Song clonedSong = ((SongPrototype) song2).cloneSong();
        library.addSong(clonedSong);

        library.addSong(song1);
        library.addSong(song2);
        library.addSong(song3);

        library.showSongs();
    }
}
