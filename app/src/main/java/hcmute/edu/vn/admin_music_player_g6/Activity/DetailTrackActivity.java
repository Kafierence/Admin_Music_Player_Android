package hcmute.edu.vn.admin_music_player_g6.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import hcmute.edu.vn.admin_music_player_g6.Database.DAOAlbum;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOArtist;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOTrack;
import hcmute.edu.vn.admin_music_player_g6.Models.Album;
import hcmute.edu.vn.admin_music_player_g6.Models.Artist;
import hcmute.edu.vn.admin_music_player_g6.Models.Track;
import hcmute.edu.vn.admin_music_player_g6.R;


public class DetailTrackActivity extends AppCompatActivity {

    Button editButton, deleteButton;
    TextView nameTxt, genreTxt, albumTxt, artistTxt;
    ImageView trackImage;
    ImageButton backButton;
    DAOTrack daoTrack = new DAOTrack();
    DAOAlbum daoAlbum = new DAOAlbum();
    DAOArtist daoArtist = new DAOArtist();
    Track track;
    Album currentAlbum;
    Artist currentArtist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_track);

        //get intent
        Intent intent = getIntent();
        track = (Track) intent.getSerializableExtra("track");

        //set id to button
        deleteButton = (Button) findViewById(R.id.btn_delete3);
        backButton = (ImageButton) findViewById(R.id.backTrack3);

        //set delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //set back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailTrackActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        //set detail Track to textview
        trackImage = findViewById(R.id.ImageView_imageTrack);
        Glide.with(this).load(track.getImage()).into(trackImage);
        nameTxt = findViewById(R.id.TextView_TrackName);
        nameTxt.setText("Song: " + track.getName());
        genreTxt = findViewById(R.id.textView_TrackGenre);
        genreTxt.setText("Genre: " + track.gettGenre());
        albumTxt = findViewById(R.id.textView_TrackAlbum);
        try {
            albumTxt.setText("Album Name: " + getAlbum().getName());
        }
        catch (Exception e){
            try{
                getAlbum();
            }
            catch (Exception ignored){

            }
        }
        artistTxt = findViewById(R.id.textView_ArtistName);
        if(track.gettArtist() != null){

            try{
                artistTxt.setText("Artist: " + track.gettArtist().getNameArtist());
            }catch (Exception e){
                try{
                    getArtist();
                }
                catch (Exception ignored){

                }
            }
        }
        else{
            artistTxt.setVisibility(View.GONE);
        }

    }
    //function to get Album
    private Album getAlbum()
    {
        try{
            daoAlbum.getByKey().addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot data: snapshot.getChildren()){
                        Album album = data.getValue(Album.class);
                        if(track.getAlbumId().trim().equals(album.getAlbumId().trim())){
                            String key = data.getKey();
                            album.setKey(key);
                            currentAlbum = album;
                            break;
                        }
                    }
                    albumTxt.setText("Album Name: " + getAlbum().getName());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            return currentAlbum;
        }
        catch (Exception ignored){
            return currentAlbum;
        }
    }
    //function to get Artist
    private Artist getArtist()
    {

        daoArtist.getByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    Artist artist = data.getValue(Artist.class);
                    if(track.getArtistId().trim().equals(artist.getIdArtist().trim())){
                        String key = data.getKey();
                        artist.setKey(key);
                        currentArtist = artist;
                        break;
                    }

                }
                artistTxt.setText("Artist: " + getArtist().getNameArtist());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return currentArtist;
    }
}
