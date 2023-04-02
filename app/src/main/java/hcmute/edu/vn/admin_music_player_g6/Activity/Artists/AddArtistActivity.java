package hcmute.edu.vn.admin_music_player_g6.Activity.Artists;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import hcmute.edu.vn.admin_music_player_g6.Models.Artist;
import hcmute.edu.vn.admin_music_player_g6.R;



public class AddArtistActivity extends AppCompatActivity {



    ImageView btnGetImage;
    Button btnAddArtist,btnCancel,btnGetDate;

    EditText nameArtist,emailArtist,linkSocialArtist;
    EditText releaseDateTxt;
    RadioGroup radioGroup;
    public Date selectedDate;
    DatePickerDialog datePickerDialog;

    Uri selectedImage;
    String gender="Male";

    FirebaseDatabase database;
    FirebaseStorage storage;
    FirebaseAuth auth;
    //String randomKey default
    String randomKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artist);

        //set ID of edit text
        nameArtist=findViewById(R.id.editArtistNameAdd4);
        emailArtist=findViewById(R.id.editArtistEmailAdd4);
        linkSocialArtist=findViewById(R.id.editLinkSocialAdd4);
        radioGroup=findViewById(R.id.genderSelect4);
        releaseDateTxt=(EditText)findViewById(R.id.editDateArtist);

        //set id of button get image
        btnGetImage=findViewById(R.id.imageViewArtist);
        btnAddArtist=findViewById(R.id.btnSaveAdminAdd4);
        btnCancel=findViewById(R.id.btnCancelAdminAdd4);
        btnGetDate=findViewById(R.id.btnReleaseDay4);

        //set initialize firebase component
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        randomKey=database.getReference().push().getKey();



        //set cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // set radio get value
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=radioGroup.findViewById(checkedId);
                gender=radioButton.getText().toString();

            }
        });
        //set listener for btn save button
        btnAddArtist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //condition when admin setting image
                String name=nameArtist.getText().toString();
                String email=emailArtist.getText().toString();
                String linkSocial=linkSocialArtist.getText().toString();
                String birthDate=releaseDateTxt.getText().toString();

                if(name.length()<6){
                    nameArtist.setError("Name must length > 6 character");
                    nameArtist.setFocusable(true);
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailArtist.setError("Invalid Email");
                    emailArtist.setFocusable(true);
                    return;
                }
                if(linkSocial.isEmpty()){
                    linkSocialArtist.setError("Invalid Link Social Artist");
                    linkSocialArtist.setFocusable(true);
                    return;
                }

                if(selectedImage!=null){
                    //initialize Artist(String idArtist, String nameArtist,String emailArtist, int numFollower, long imageArtist,String genderArtist,String linkSocial){
                    StorageReference reference = storage.getReference().child("ImgArtists").child(randomKey);
                    reference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                            String urlImg=uri.toString();
                                            Artist artist=new Artist(randomKey,name,email,0,urlImg,gender,linkSocial);
                                            database.getReference()
                                                .child("artists")
                                                .child(randomKey)
                                                .setValue(artist)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                        Toast.makeText(AddArtistActivity.this, "Creating Sucess", Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(AddArtistActivity.this, "Feail Creating", Toast.LENGTH_SHORT).show();
                                                            finish();
                                                        }
                                                    });

                                    }
                                });
                            }
                        }
                    });


                }
                else{
                    Artist artist=new Artist(randomKey,name,email,0,"NO IMAGE",gender,linkSocial);
                    database.getReference()
                            .child("artists")
                            .child(randomKey)
                            .setValue(artist)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    finish();
                                }
                            });

                }

            }
        });
        //set event for getting image
        btnGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 45);
            }
        });
        btnGetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

    }
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
    public static Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            if(data.getData() != null) {
                Uri uri = data.getData(); // filepath
                FirebaseStorage storage = FirebaseStorage.getInstance();
                long time = new Date().getTime();
                StorageReference reference = storage.getReference().child("ImgArtists").child(time+"");
                reference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String filePath = uri.toString();
                                    HashMap<String, Object> obj = new HashMap<>();
                                    obj.put("image", filePath);
                                    database.getReference().child("artists")
                                            .child(randomKey)
                                            .updateChildren(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {

                                                }
                                            });
                                }
                            });
                        }
                    }
                });

                btnGetImage.setImageURI(data.getData());
                selectedImage = data.getData();
            }
        }
    }
}