package com.company.Dto;

public class PostDto {
    private Tracks tracks;
    private Tracks artists;

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public Tracks getArtists() {
        return artists;
    }

    public void setArtists(Tracks artists) {
        this.artists = artists;
    }
}
