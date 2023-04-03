package hcmute.edu.vn.admin_music_player_g6.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.admin_music_player_g6.Activity.Album.AddAlbumActivity;
import hcmute.edu.vn.admin_music_player_g6.Adapter.AlbumAdapter;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOAlbum;
import hcmute.edu.vn.admin_music_player_g6.Models.Album;
import hcmute.edu.vn.admin_music_player_g6.R;


public class AlbumFragment extends Fragment {

    ImageButton button;
    DAOAlbum daoAlbum = new DAOAlbum();
    AlbumAdapter albumAdapter;

    public AlbumFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_album, container, false);
        button = (ImageButton) view.findViewById(R.id.add_admin_album);

        //set recycleView for Album
        RecyclerView recyclerView1 = view.findViewById(R.id.admin_album_recycler);
        albumAdapter = new AlbumAdapter(getActivity());
        albumAdapter.setData(getListAlbum());
        recyclerView1.setAdapter(albumAdapter);
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        //set button to move add Album page
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddAlbumActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

    //function to get list Album
    private List<Album> getListAlbum()
    {
        List<Album> list = new ArrayList<>();

        daoAlbum.getByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    Album album = data.getValue(Album.class);

                    String key = data.getKey();
                    album.setKey(key);
                    list.add(album);
                }
                albumAdapter.notifyDataSetChanged();
                albumAdapter.setData(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return list;
    }
}
