package hcmute.edu.vn.admin_music_player_g6.Models;



import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Album implements Serializable {
    @Exclude
    String key;

    //create attribute of Album
    private String name;
    private String resourceId;
    private String description;
    private String artistId;
    private String artistName;
    private String albumId;
    private String releaseDate;
    private Artist Artist;

    //create Album
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("resourceId", resourceId);
        result.put("description", description);
        result.put("artistId", artistId);
        result.put("artistName", artistName);
        result.put("albumId", albumId);
        result.put("releaseDate", releaseDate);
        result.put("Artist", Artist);

        return result;
    }

    public Album() {

    }

    //get and set of each attribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public hcmute.edu.vn.admin_music_player_g6.Models.Artist getArtist() {
        return Artist;
    }

    public void setArtist(hcmute.edu.vn.admin_music_player_g6.Models.Artist artist) {
        Artist = artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


}
