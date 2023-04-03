package hcmute.edu.vn.admin_music_player_g6.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.admin_music_player_g6.Activity.Artists.AddArtistActivity;
import hcmute.edu.vn.admin_music_player_g6.Adapter.ArtistAdapter;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOArtist;
import hcmute.edu.vn.admin_music_player_g6.Models.Artist;
import hcmute.edu.vn.admin_music_player_g6.R;
import hcmute.edu.vn.admin_music_player_g6.databinding.FragmentArtistBinding;



public class ArtistFragment extends Fragment {


    private FragmentArtistBinding binding;
    DAOArtist daoArtist = new DAOArtist();
    ArtistAdapter artistAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentArtistBinding.inflate(getLayoutInflater(),container,false);
        View view=binding.getRoot();
        //set recycleView for Artist
        RecyclerView recyclerView1 = view.findViewById(R.id.admin_artist_recycler);
        artistAdapter = new ArtistAdapter(getActivity());
        artistAdapter.setData(getListArtist());
        recyclerView1.setAdapter(artistAdapter);
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        //set button to move add Artist page
        binding.addAdminArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AddArtistActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }

    //function to get list Artist
    private List<Artist> getListArtist()
    {
        List<Artist> list = new ArrayList<>();

        daoArtist.getByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    Artist artist = data.getValue(Artist.class);

                    String key = data.getKey();
                    artist.setKey(key);
                    list.add(artist);
                }
                artistAdapter.notifyDataSetChanged();
                artistAdapter.setData(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return list;
    }
}