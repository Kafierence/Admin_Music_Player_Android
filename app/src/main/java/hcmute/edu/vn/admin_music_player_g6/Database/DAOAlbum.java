package hcmute.edu.vn.admin_music_player_g6.Database;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import hcmute.edu.vn.admin_music_player_g6.Models.Album;


public class DAOAlbum {
    public DatabaseReference databaseReference;
    final FirebaseDatabase db;
    //start Album database
    public DAOAlbum() {
        db = FirebaseDatabase.getInstance("https://mediaplayer-bd6cd-default-rtdb.firebaseio.com/");

        databaseReference = db.getReference("Album"); // return class name
    }
    //function add Album
    public Task<Void> add(Album album){
        if(album != null) {
            try{
                // return databaseReference.push().setValue(album);
                return databaseReference.child(album.getName()).setValue(album);
            }
            catch (Exception e){
                e.getMessage();
            }
        }
        return null;
    }
    //function update Album
    public Task<Void> update(Album album){

        return databaseReference.child(album.getKey()).updateChildren(album.toMap());
    }
    //function remove Album
    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

    //get attribute of Album
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
