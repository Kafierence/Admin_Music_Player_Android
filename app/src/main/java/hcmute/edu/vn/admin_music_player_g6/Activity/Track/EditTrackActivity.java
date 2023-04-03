package hcmute.edu.vn.admin_music_player_g6.Activity.Track;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.admin_music_player_g6.Activity.AdminActivity;
import hcmute.edu.vn.admin_music_player_g6.R;


public class EditTrackActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText name, genre, lyric;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_track);

        //set id to button
        btnAdd = (Button) findViewById(R.id.btnSaveAdminEdit3);
        btnCancel = (Button) findViewById(R.id.btnCancelAdminEdit3);

        //set id to edit text
        name = (EditText) findViewById(R.id.editTrackNameEdit3);
        genre = (EditText) findViewById(R.id.editTrackGenreEdit3);
        lyric = (EditText) findViewById(R.id.editTrackDescriptionEdit3);

        //set add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        //set cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditTrackActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

    }
}
