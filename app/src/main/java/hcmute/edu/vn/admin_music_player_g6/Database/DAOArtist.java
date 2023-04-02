package hcmute.edu.vn.admin_music_player_g6.Database;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import hcmute.edu.vn.admin_music_player_g6.Models.Artist;


public class DAOArtist {
    public DatabaseReference databaseReference;
    final FirebaseDatabase db;
    //start Artist database
    public DAOArtist() {
        db = FirebaseDatabase.getInstance("https://mediaplayer-bd6cd-default-rtdb.firebaseio.com/");

        databaseReference = db.getReference("artists"); // return class name
    }
    //function add Artist
    public Task<Void> add(Artist artist){
        if(artist != null) {
            try{
                // return databaseReference.push().setValue(album);
                return databaseReference.child(String.valueOf(artist.getIdArtist())).setValue(artist);
            }
            catch (Exception e){
                e.getMessage();
            }
        }
        return null;
    }
    //function update Artist
    public Task<Void> update(Artist artist){
        return databaseReference.child(artist.getKey()).updateChildren(artist.toMap());
    }
    //function remove Artist
    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

    //get attribute of Artist
    public Query getByKey(){
        return databaseReference.orderByKey();
    }
    public Query getByChild(){
        return databaseReference.orderByChild("");
    }
    public Query getByValue(){
        return databaseReference.orderByValue();
    }
}
