package creational;

import java.util.ArrayList;
import java.util.List;

class SingMusicLib {                    // Одинак
    private static SingMusicLib instance = null;
    private List<Song> songs = new ArrayList<>();

    private SingMusicLib() {}

    public static SingMusicLib getInstance() {
        if (instance == null) {
            instance = new SingMusicLib();
        }
        return instance;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void showSongs() {
        for (Song song : songs) {
            System.out.println(song);
        }
    }
}

abstract class Song {
    protected String title;
    protected String artist;
    protected String format;
    protected String genre;

    public Song(String title, String artist, String format, String genre) {
        this.title = title;
        this.artist = artist;
        this.format = format;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Song: " + title + " by " + artist + ", format: " + format + ", genre: " + genre;
    }
}

// Окремий клас для Прототипу
interface SongPrototype {
    Song cloneSong();
}

class RockSong extends Song implements SongPrototype {
    public RockSong(String title, String artist, String format, String genre) {
        super(title, artist, format, genre);
    }

    @Override
    public RockSong cloneSong() {
        return new RockSong(title, artist, format, genre);
    }
}

class ElectronicSong extends Song implements SongPrototype {
    public ElectronicSong(String title, String artist, String format, String genre) {
        super(title, artist, format, genre);
    }

    @Override
    public ElectronicSong cloneSong() {
        return new ElectronicSong(title, artist, format, genre);
    }
}

class AbsFactory {                     // Абстрактна фабрика
    public GenreFactory getGenreFactory(String type) {
        if ("modern".equalsIgnoreCase(type)) {
            return new ModernGenFactory();
        } else if ("electronic".equalsIgnoreCase(type)) {
            return new ElectronicGenFactory();
        } else {
            throw new IllegalArgumentException("Unknown genre factory");
        }
    }
}

abstract class GenreFactory {           // Фабрика
    public abstract Song getSong(String title, String artist, String format, String genre);
}

class ModernGenFactory extends GenreFactory {
    @Override
    public Song getSong(String title, String artist, String format, String genre) {
        return new RockSong(title, artist, format, genre);
    }
}

class ElectronicGenFactory extends GenreFactory {
    @Override
    public Song getSong(String title, String artist, String format, String genre) {
        return new ElectronicSong(title, artist, format, genre);
    }
}

class SongBuilder {                      // Будівельник
    private String title;
    private String artist;
    private String format;
    private String genre;

    public SongBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public SongBuilder setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public SongBuilder setFormat(String format) {
        this.format = format;
        return this;
    }

    public SongBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public Song build() {
        return new RockSong(title, artist, format, genre);
    }
}
