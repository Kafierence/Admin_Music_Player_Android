package hcmute.edu.vn.admin_music_player_g6.Database;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import hcmute.edu.vn.admin_music_player_g6.Models.Genre;


public class DAOGenre {
    public DatabaseReference databaseReference;
    final FirebaseDatabase db;
    //start genre database
    public DAOGenre() {
        db = FirebaseDatabase.getInstance("https://mediaplayer-bd6cd-default-rtdb.firebaseio.com/");

        databaseReference = db.getReference("genres"); // return class name
    }
    //function add genre
    public Task<Void> add(Genre genre){
        if(genre != null) {
            try{
                // return databaseReference.push().setValue(album);
                return databaseReference.child(genre.getName()).setValue(genre);
            }
            catch (Exception e){
                e.getMessage();
            }
        }
        return null;
    }
    //function update genre
    public Task<Void> update(Genre genre){
        return databaseReference.child(genre.getKey()).updateChildren(genre.toMap());
    }
    //function remove genre
    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

    //get attribute of genre
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
