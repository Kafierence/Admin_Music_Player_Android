package hcmute.edu.vn.admin_music_player_g6.Models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Genre implements Serializable {

    @Exclude

    //create attribute of genre
    private String name;
    private String description;
    private String key;
    private String image;

    public Genre() {
    }

    public Genre(String name, String description,String key) {
        this.name = name;

        this.description = description;
        this.key=key;
    }

    //create genre
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("description", description);
        result.put("key",key);
        result.put("image",image);
        return result;
    }

    //get and set of each attribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
