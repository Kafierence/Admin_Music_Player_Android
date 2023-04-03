package hcmute.edu.vn.admin_music_player_g6.Activity.Track;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hcmute.edu.vn.admin_music_player_g6.Activity.AdminActivity;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOAlbum;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOArtist;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOGenre;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOTrack;
import hcmute.edu.vn.admin_music_player_g6.Models.Album;
import hcmute.edu.vn.admin_music_player_g6.Models.Artist;
import hcmute.edu.vn.admin_music_player_g6.Models.Genre;
import hcmute.edu.vn.admin_music_player_g6.Models.Track;
import hcmute.edu.vn.admin_music_player_g6.R;


public class AddTrackActivity extends AppCompatActivity {

    // Views
    Button btnAdd, btnCancel, btnReleaseDate;
    EditText name, lyric, image, source;
    Spinner genreDropDown, artistDropDown, albumDropDown;
    EditText releaseDateTxt;

    // DB
    DAOGenre daoGenre = new DAOGenre();
    DAOTrack daoTrack = new DAOTrack();
    DAOAlbum daoAlbum = new DAOAlbum();
    DAOArtist daoArtist = new DAOArtist();

    // List
    List<Genre> genreList = new ArrayList<>();
    ArrayList<String> genreItems = new ArrayList<>();
    List<Artist> artistList = new ArrayList<>();
    ArrayList<String > artistItems = new ArrayList<>();
    List<Album> albumList = new ArrayList<>();
    ArrayList<String> albumItems = new ArrayList<>();
    // Variables
    String selectedGenre = new String();
    public Date selectedDate;
    DatePickerDialog datePickerDialog;
    Album selectedAlbum;
    Artist selectedArtist;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);
        InitViews();

    }
    void InitViews(){
        // Set id to button
        btnAdd = (Button) findViewById(R.id.activity_add_track_AddBtn);
        btnCancel = (Button) findViewById(R.id.activity_add_track_CancelBtn);
        btnReleaseDate = (Button) findViewById(R.id.activity_add_track_PickReleaseDateBtn);

        // Set id to edit text
        name = (EditText) findViewById(R.id.activity_add_track_NameEditTxt);
        genreDropDown = (Spinner) findViewById(R.id.activity_add_track_GenreSpinner);
        artistDropDown = (Spinner) findViewById(R.id.activity_add_track_ArtistSpinner);
        albumDropDown = (Spinner) findViewById(R.id.activity_add_track_AlbumSpinner);

        lyric= (EditText) findViewById(R.id.activity_add_track_LyricEditTxt);
        image  = (EditText) findViewById(R.id.activity_add_track_ImageEditTxt);
        source = (EditText) findViewById(R.id.activity_add_track_SourceEditTxt);
        releaseDateTxt  = (EditText) findViewById(R.id.activity_add_track_ReleaseDateEditTxt);

        //get genre from database
        daoGenre.getByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                genreList = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()){
                    Genre genre = data.getValue(Genre.class);

                    String key = data.getKey();
                    genre.setKey(key);
                    genreList.add(genre);
                }
                genreItems = new ArrayList<>();
                if(genreList.isEmpty() == false){
                    for(Genre genre: genreList){
                        genreItems.add(genre.getName());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddTrackActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        genreItems
                );
                genreDropDown.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //set genre list to spinner
        genreDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGenre = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddTrackActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        artistItems
                );
                artistDropDown.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //set artist list to spinner
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

        //get Album from database
        daoAlbum.getByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                albumList = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()){
                    Album album = data.getValue(Album.class);

                    String key = data.getKey();
                    album.setKey(key);
                    albumList.add(album);
                }
                albumItems = new ArrayList<>();
                if(albumList.isEmpty() == false){
                    for(Album album: albumList){
                        albumItems.add(album.getName());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddTrackActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        albumItems
                );
                albumDropDown.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //set Album list to spinner
        albumDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedAlbumName = parent.getItemAtPosition(position).toString();
                for(Album album: albumList){
                    if(album.getName() == selectedAlbumName){
                        selectedAlbum = album;
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //set add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Track track = new Track();
                track.setName(name.getText().toString());
                track.settId(name.getText().toString());
                track.settGenre(selectedGenre);
                track.settLyric(lyric.getText().toString());
                track.settReleaseDate(selectedDate);
                track.settAlbum(selectedAlbum);
                track.setAlbumId(selectedAlbum.getAlbumId());
                track.settArtist(selectedArtist);
                track.setArtistName(selectedArtist.getNameArtist());
                track.setArtistId(selectedArtist.getIdArtist());
                track.setSource(source.getText().toString());
                track.setImage(image.getText().toString());
                daoTrack.add(track).addOnSuccessListener(suc-> {
                    Toast.makeText(AddTrackActivity.this, "Track is inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(AddTrackActivity .this, er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
        //set cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTrackActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        //set release date button
        btnReleaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
    }

    //function to choose date for release date
    public void showDatePickerDialog(View v) {
        DatePickerDialog.OnDateSetListener dataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectedDate = getDateFromDatePicker(view);
                releaseDateTxt.setText(selectedDate.toString());
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dataSetListener, year, month, day);
        datePickerDialog.show();
    }

    //function to get data from the the release date
    public static Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

}
