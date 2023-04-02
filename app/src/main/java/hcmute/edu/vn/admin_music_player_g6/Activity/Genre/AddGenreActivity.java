package hcmute.edu.vn.admin_music_player_g6.Activity.Genre;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import hcmute.edu.vn.admin_music_player_g6.Activity.AdminActivity;
import hcmute.edu.vn.admin_music_player_g6.Models.Genre;
import hcmute.edu.vn.admin_music_player_g6.R;


public class AddGenreActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText name, url, description;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_genre);

        // Set id to button
        btnAdd = (Button) findViewById(R.id.btnSaveAdminAdd1);
        btnCancel = (Button) findViewById(R.id.btnCancelAdminAdd1);

        //initialize database
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();

        // Set id to edit text
        name = (EditText) findViewById(R.id.editGenreNameAdd1);
        description = (EditText) findViewById(R.id.editGenreDescriptionAdd1);

        //set add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storageGenre();

            }
        });

        //set cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddGenreActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }


    //create function to store genre database
    public void storageGenre(){
        //set a new genre
        String nameGenre = name.getText().toString();
        String descriptionGenre = description.getText().toString();
        String randomKey = database.getReference().push().getKey();
        Genre genre =new Genre(nameGenre,descriptionGenre,randomKey);

        //check input
        if(nameGenre.isEmpty()){
            name.setError("Please Enter Name Genre");
            return;
        }
        if(descriptionGenre.isEmpty()){
            description.setError("Please Enter your description");
            return;
        }

        //add genre to database
        database.getReference()
                .child("genres")
                .child(randomKey)
                .setValue(genre).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddGenreActivity.this);
                        builder.setMessage("Create Genre Success")
                                .setTitle("Successful");

                        builder.setPositiveButton("Okk", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                        AlertDialog dialog = builder.create(); Intent intent = new Intent(AddGenreActivity.this, AddGenreActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

    }


}
