package hcmute.edu.vn.admin_music_player_g6.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hcmute.edu.vn.admin_music_player_g6.Activity.Genre.AddGenreActivity;
import hcmute.edu.vn.admin_music_player_g6.Adapter.GenreAdapter;
import hcmute.edu.vn.admin_music_player_g6.Models.Genre;
import hcmute.edu.vn.admin_music_player_g6.R;


public class GenreFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseDatabase database;

    ImageButton button;
    ArrayList<Genre> genres;
    GenreAdapter genreAdapter;
    Genre genre;
    RecyclerView genreRecycler;
    public GenreFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_genre, container, false);

        //set id
        button = (ImageButton) view.findViewById(R.id.add_admin_genre);
        genreRecycler=(RecyclerView)view.findViewById(R.id.admin_genre_recycler);

        //set button to move add genre page
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddGenreActivity.class);
                startActivity(intent);
            }
        });

        createViewsGenre();

        return view;
    }

    //function to set RecycleView for genre
    public void createViewsGenre(){
        database=FirebaseDatabase.getInstance();
        genres=new ArrayList<>();

        //set RecycleView for genre
        genreAdapter=new GenreAdapter(getActivity(),genres);
        genreRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        genreRecycler.setAdapter(genreAdapter);

        //get genre database
        database.getReference().child("genres").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                genres.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Genre genre=snapshot1.getValue(Genre.class);
                  genres.add(genre);
                  System.out.println(genres.size());
                }
                genreAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}
