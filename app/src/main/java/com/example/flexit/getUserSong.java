package com.example.flexit;


public class getUserSong {
    private String name;
    private int CurrentSong;
    private String Artist;


    public getUserSong(String name, String Artist, int CurrentSong) {
        this.name = name;
        this.Artist = Artist;
        this.CurrentSong = CurrentSong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        this.Artist = artist;
    }

    public int getCurrentSong() {
        return CurrentSong;
    }

    public void setCurrentSong(int currentSong) {
        this.CurrentSong = currentSong;
    }
}
