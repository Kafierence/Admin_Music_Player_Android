package hcmute.edu.vn.admin_music_player_g6.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.admin_music_player_g6.Activity.Album.EditAlbumActivity;
import hcmute.edu.vn.admin_music_player_g6.Adapter.TrackAdapter;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOAlbum;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOTrack;
import hcmute.edu.vn.admin_music_player_g6.Models.Album;
import hcmute.edu.vn.admin_music_player_g6.Models.Track;
import hcmute.edu.vn.admin_music_player_g6.R;


public class DetailAlbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewTrack;
    TextView NameAlbumTextView;
    Button editButton, deleteButton;
    ImageButton backButton;

    ImageView AlbumPicture;
    TrackAdapter trackAdapter;
    DAOTrack daoTrack = new DAOTrack();
    DAOAlbum daoAlbum = new DAOAlbum();
    Album album;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_album);

        //get intent
        Intent intent = getIntent();
        album = (Album) intent.getSerializableExtra("album");

        //set id to button
        editButton = (Button) findViewById(R.id.activity_detail_album_BtnEdit);
        deleteButton = (Button) findViewById(R.id.activity_detail_album_BtnDelete);
        backButton = (ImageButton) findViewById(R.id.activity_detail_album_BackBtn);

        //set detail Album to textview
        NameAlbumTextView = (TextView)findViewById(R.id.activity_detail_album_editNameAlbumTextView);
        NameAlbumTextView.setText(album.getName().toString());
        AlbumPicture = (ImageView) findViewById(R.id.activity_detail_album_AlbumPicture);
        Glide.with(this).load(album.getResourceId()).into(AlbumPicture);

        //set RecycleView for track
        recyclerViewTrack = (RecyclerView) findViewById(R.id.activity_detail_album_AlbumSongsList);
        trackAdapter = new TrackAdapter(this);
        trackAdapter.setData(getListTrack());
        recyclerViewTrack.setAdapter(trackAdapter);
        recyclerViewTrack.setLayoutManager(new GridLayoutManager(this, 2));

        //set edit button
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailAlbumActivity.this, EditAlbumActivity.class);
                intent.putExtra("album", album);
                startActivity(intent);
            }
        });

        //set edit button
        AlbumPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailAlbumActivity.this, EditAlbumActivity.class);
                intent.putExtra("album", album);
                startActivity(intent);
            }
        });

        //set delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daoAlbum.remove(album.getKey()).addOnSuccessListener(suc-> {
                    Toast.makeText(DetailAlbumActivity.this, "Record is Deleted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(DetailAlbumActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show();
                });;
            }
        });

        //set back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailAlbumActivity.this, hcmute.edu.vn.admin_music_player_g6.Activity.AdminActivity.class);
                startActivity(intent);
            }
        });
    }
    //function to get Track list
    private List<Track> getListTrack()
    {
        //create new list
        List<Track>  list = new ArrayList<>();

        //get Track list
        daoTrack.getByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    Track track = data.getValue(Track.class);
                    String key = data.getKey();

                    track.setKey(key);
                    try{
                        if(track.gettAlbum() != null){
                            if(track.gettAlbum().getName().equals(album.getName())){
                                list.add(track);
                            }
                        }
                        else
                        if(track.gettAlbum() == null){
                            if(track.getAlbumId().equals(album.getKey())){
                                list.add(track);
                            }
                        }
                    }
                    catch (Exception e){

                    }

                }
                trackAdapter.notifyDataSetChanged();
                trackAdapter.setData(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return list;
    }

}
