package hcmute.edu.vn.admin_music_player_g6.Activity.Genre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import hcmute.edu.vn.admin_music_player_g6.Activity.AdminActivity;
import hcmute.edu.vn.admin_music_player_g6.Models.Genre;
import hcmute.edu.vn.admin_music_player_g6.R;


public class EditGenreActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText name, description;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_genre);


        //initialize database//initialize database
        mDatabase= FirebaseDatabase.getInstance().getReference();

        // Set id to button
        btnAdd = (Button) findViewById(R.id.btnSaveAdminEdit1);
        btnCancel = (Button) findViewById(R.id.btnCancelAdminEdit1);

        // Set id to edit text
        name = (EditText) findViewById(R.id.editGenreNameEdit1);// name genre
        description = (EditText) findViewById(R.id.editGenreDescriptionEdit1);// description of genre

        //get intent
        String nameGenre= getIntent().getStringExtra("name");
        String descriptionGenre= getIntent().getStringExtra("description");
        String key= getIntent().getStringExtra("key");

        //set value
        name.setText(nameGenre);
        description.setText(descriptionGenre);


        //set add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update genre
                String nameNew=  name.getText().toString();
                String descriptionNew=description.getText().toString();

                Genre genreNew=new Genre(nameNew,descriptionNew,key);
                updateGenre(genreNew);

            }
        });

        //set cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditGenreActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

    }

    //function to update the genre
    public void updateGenre(Genre genre){
        Map<String,Object> genValue=genre.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/Genres/"+genre.getKey(),genValue);
        mDatabase.updateChildren(childUpdates);
        finish();

    }
}
