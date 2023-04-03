package hcmute.edu.vn.admin_music_player_g6.Models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Track implements Serializable {

    @Exclude
    String key;
    //create attribute of Track
    private String tId;
    private String name;
    private String source;
    private int tListens;
    private String tLyric;
    private String tGenre;
    private String image;
    private Date tReleaseDate;
    private Album tAlbum;
    private Artist tArtist;
    private String artistId;
    private String albumId;
    private String artistName;
    public Track() {
    }

    //create Track
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("tId", tId);
        result.put("tName", name);
        result.put("source", source);
        result.put("tListens", tListens);
        result.put("tGenre", tGenre);
        result.put("image", image);
        result.put("tReleaseDate", tReleaseDate);
        result.put("tAlbum", tAlbum);
        result.put("tArtist", tArtist);
        result.put("artistId", artistId);
        result.put("albumId", albumId);

        return result;
    }

    //get and set of each attribute
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int gettListens() {
        return tListens;
    }

    public void settListens(int tListens) {
        this.tListens = tListens;
    }

    public String gettLyric() {
        return tLyric;
    }

    public void settLyric(String tLyric) {
        this.tLyric = tLyric;
    }

    public String gettGenre() {
        return tGenre;
    }

    public void settGenre(String tGenre) {
        this.tGenre = tGenre;
    }

    public Date gettReleaseDate() {
        return tReleaseDate;
    }

    public void settReleaseDate(Date tReleaseDate) {
        this.tReleaseDate = tReleaseDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Album gettAlbum() {
        return tAlbum;
    }

    public void settAlbum(Album tAlbum) {
        this.tAlbum = tAlbum;
    }

    public Artist gettArtist() {
        return tArtist;
    }

    public void settArtist(Artist tArtist) {
        this.tArtist = tArtist;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }
}
