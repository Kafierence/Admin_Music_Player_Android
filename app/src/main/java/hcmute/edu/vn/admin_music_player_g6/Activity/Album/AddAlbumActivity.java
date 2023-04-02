package hcmute.edu.vn.admin_music_player_g6.Activity.Album;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.admin_music_player_g6.Activity.AdminActivity;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOAlbum;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOArtist;
import hcmute.edu.vn.admin_music_player_g6.Models.Album;
import hcmute.edu.vn.admin_music_player_g6.Models.Artist;

import hcmute.edu.vn.admin_music_player_g6.R;

public class AddAlbumActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText name, imageURL, description, releaseDateTxt;
    DAOAlbum daoAlbum = new DAOAlbum();
    Spinner artistDropDown;
    List<Artist> artistList = new ArrayList<>();
    Artist selectedArtist;
    DAOArtist daoArtist = new DAOArtist();
    ArrayList<String > artistItems = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_album);

        //set id to button
        btnAdd = (Button) findViewById(R.id.btnSaveAdminAdd2);
        btnCancel = (Button) findViewById(R.id.btnCancelAdminAdd2);

        //set id to edit text
        name = (EditText) findViewById(R.id.activity_add_album_nameEdit);
        imageURL = (EditText) findViewById(R.id.activity_add_AlbumImageEdit);
        description  = (EditText) findViewById(R.id.activity_add_album_descriptionEdit);
        releaseDateTxt  = (EditText) findViewById(R.id.activity_add_album_ReleaseDateEditTxt);

        //get Artist from database
        daoArtist.getByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                artistList = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()){
                    Artist artist = data.getValue(Artist.class);

                    String key = data.getKey();
                    artist.setKey(key);
                    artistList.add(artist);
                }
                artistItems = new ArrayList<>();
                if(artistList.isEmpty() == false){
                    for(Artist artist: artistList){
                        artistItems.add(artist.getNameArtist());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAlbumActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        artistItems
                );
                //set artist list to spinner
                artistDropDown.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //set spinner artist
        artistDropDown = (Spinner) findViewById(R.id.activity_add_album_ArtistSpinner);
        artistDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedArtistName = parent.getItemAtPosition(position).toString();
                for(Artist artist: artistList){
                    if(artist.getNameArtist() == selectedArtistName){
                        selectedArtist = artist;
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //set button add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set a new album
                Album album = new Album();
                album.setAlbumId(name.getText().toString());
                album.setName(name.getText().toString());
                album.setResourceId(imageURL.getText().toString());
                album.setDescription(description.getText().toString());
                album.setArtist(selectedArtist);
                album.setArtistId(selectedArtist.getIdArtist());
                album.setArtistName(selectedArtist.getNameArtist().toString());
                album.setReleaseDate(releaseDateTxt.getText().toString());

                //add album to database
                daoAlbum.add(album).addOnSuccessListener(suc-> {
                    Toast.makeText(AddAlbumActivity.this, "Record is inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(AddAlbumActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });

        //set button cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAlbumActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }

}
