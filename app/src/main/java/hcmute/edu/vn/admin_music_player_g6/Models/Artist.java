package hcmute.edu.vn.admin_music_player_g6.Models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Artist implements Serializable {
    @Exclude
    String key;

    //create attribute of Artist
    private String idArtist;
    private String nameArtist;
    private int numFollower;
    private String  imageArtist;
    private String emailArtist;
    private  String genderArtist;
    private String linkSocial;

    //create Artist
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("idArtist", idArtist);
        result.put("nameArtist", nameArtist);
        result.put("numFollower", numFollower);
        result.put("imageArtist", imageArtist);
        result.put("emailArtist",emailArtist);
        result.put("genderArtist",genderArtist);
        result.put("linkSocial",linkSocial);
        return result;
    }
    public Artist() {
    }

    public  Artist(String idArtist, String nameArtist,String emailArtist, int numFollower, String imageArtist,String genderArtist,String linkSocial){
        this.idArtist=idArtist;
        this.nameArtist=nameArtist;
        this.numFollower=numFollower;
        this.emailArtist=emailArtist;
        this.linkSocial=linkSocial;
        this.imageArtist=imageArtist;
        this.genderArtist=genderArtist;
    }

    //get and set of each attribute
    public String getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(String idArtist) {
        this.idArtist = idArtist;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public int getNumFollower() {
        return numFollower;
    }

    public void setNumFollower(int numFollower) {
        this.numFollower = numFollower;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getImageArtist() {
        return imageArtist;
    }

    public void setImageArtist(String imageArtist) {
        this.imageArtist = imageArtist;
    }

    public String getEmailArtist() {
        return emailArtist;
    }

    public void setEmailArtist(String emailArtist) {
        this.emailArtist = emailArtist;
    }

    public String getGenderArtist() {
        return genderArtist;
    }

    public void setGenderArtist(String genderArtist) {
        this.genderArtist = genderArtist;
    }

    public String getLinkSocial() {
        return linkSocial;
    }

    public void setLinkSocial(String linkSocial) {
        this.linkSocial = linkSocial;
    }
}
