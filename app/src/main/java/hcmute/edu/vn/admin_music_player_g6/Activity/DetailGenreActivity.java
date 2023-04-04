package hcmute.edu.vn.admin_music_player_g6.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.admin_music_player_g6.Activity.Genre.EditGenreActivity;
import hcmute.edu.vn.admin_music_player_g6.R;


public class DetailGenreActivity extends AppCompatActivity {

    Button editButton, deleteButton;
    ImageButton backButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_genre);
        SetViews();
    }
    void SetViews(){
        // Set id to button
        editButton = (Button) findViewById(R.id.btn_edit1);
        deleteButton = (Button) findViewById(R.id.btn_delete1);
        backButton = (ImageButton) findViewById(R.id.backGenre1);

        //set edit button
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailGenreActivity.this, EditGenreActivity.class);
                startActivity(intent);
            }
        });

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
                Intent intent = new Intent(DetailGenreActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }

}
