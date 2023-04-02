package hcmute.edu.vn.admin_music_player_g6.Activity.Album;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.admin_music_player_g6.Activity.AdminActivity;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOAlbum;
import hcmute.edu.vn.admin_music_player_g6.Models.Album;
import hcmute.edu.vn.admin_music_player_g6.R;


public class EditAlbumActivity extends AppCompatActivity {

    Button btnUpdate, btnCancel, btnDelete;
    EditText name, image, description;
    DAOAlbum albumDAO = new DAOAlbum();
    Album album;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_album);

        //get intent
        Intent intent = getIntent();
        album = (Album) intent.getSerializableExtra("album");

        //set id
        btnUpdate = (Button) findViewById(R.id.btnSaveAdminEdit2);
        btnCancel = (Button) findViewById(R.id.btnCancelAdminEdit2);
        btnDelete = (Button) findViewById(R.id.activity_edit_album_deleteButton);
        name = (EditText) findViewById(R.id.activity_edit_album_editAlbumNameEditText);
        name.setText(album.getName());
        image = (EditText) findViewById(R.id.activity_edit_album_editAlbumImageEditText);
        image.setText(String.valueOf(album.getResourceId()));
        description = (EditText) findViewById(R.id.activity_edit_album_editAlbumDescriptionEditText);
        description.setText(album.getDescription());

        //set button update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update album
                album.setName(name.getText().toString());
                album.setResourceId(image.getText().toString());
                album.setDescription(description.getText().toString());
                albumDAO.update(album).addOnSuccessListener(suc-> {
                    Toast.makeText(EditAlbumActivity.this, "Record is updated", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(EditAlbumActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });

        //set button cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditAlbumActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        //set button delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //remove album from database
                albumDAO.remove(album.getKey()).addOnSuccessListener(suc-> {
                    Toast.makeText(EditAlbumActivity.this, "Record is Deleted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(EditAlbumActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show();
                });;
            }
        });
    }
}
