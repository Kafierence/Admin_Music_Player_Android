package hcmute.edu.vn.admin_music_player_g6.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.admin_music_player_g6.Activity.Album.AddAlbumActivity;
import hcmute.edu.vn.admin_music_player_g6.Activity.Genre.AddGenreActivity;
import hcmute.edu.vn.admin_music_player_g6.Activity.Track.AddTrackActivity;
import hcmute.edu.vn.admin_music_player_g6.Adapter.AlbumAdapter;
import hcmute.edu.vn.admin_music_player_g6.Adapter.TrackAdapter;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOAlbum;
import hcmute.edu.vn.admin_music_player_g6.Database.DAOTrack;
import hcmute.edu.vn.admin_music_player_g6.Models.Album;
import hcmute.edu.vn.admin_music_player_g6.Models.Track;
import hcmute.edu.vn.admin_music_player_g6.R;


public class DashboarFragment extends Fragment {

    LineChart lineChart;
    Button addAlbum, addTrack, addGenre;
    RecyclerView recyclerViewAlbum;
    RecyclerView recyclerViewTrack;
    AlbumAdapter albumAdapter;
    TrackAdapter trackAdapter;
    DAOAlbum daoAlbum = new DAOAlbum();
    DAOTrack daoTrack = new DAOTrack();
    public DashboarFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //set button to move add Album page
        addAlbum = (Button) view.findViewById(R.id.fragment_dashboard_addAlbumBtn);
        addAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AddAlbumActivity.class);
                startActivity(intent);

            }
        });

        //set button to move add Track page
        addTrack = (Button) view.findViewById(R.id.fragment_dashboard_addTrackBtn);
        addTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AddTrackActivity.class);
                startActivity(intent);

            }
        });

        //set button to move add genre page
        addGenre = (Button) view.findViewById(R.id.fragment_dashboard_addGernBtn);
        addGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AddGenreActivity.class);
                startActivity(intent);

            }
        });

        //create line chart
        lineChart = view.findViewById(R.id.line_chart);
        LineDataSet pageView = new LineDataSet(data1(), "Page View");
        pageView.setColor(Color.BLUE);
        pageView.setCircleColor(Color.BLUE);
        LineDataSet visitor = new LineDataSet(data2(), "Visitor");
        visitor.setColor(Color.RED);
        visitor.setCircleColor(Color.RED);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(pageView);
        dataSets.add(visitor);
        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();

        //set RecycleView for Album
        recyclerViewAlbum = view.findViewById(R.id.activity_detail_album_AlbumSongsList);
        albumAdapter = new AlbumAdapter(getActivity());
        albumAdapter.setData(getListAlbum());
        recyclerViewAlbum.setAdapter(albumAdapter);
        recyclerViewAlbum.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        //set RecycleView for Track
        recyclerViewTrack = view.findViewById(R.id.list_songs_dash);
        trackAdapter = new TrackAdapter(getActivity());
        trackAdapter.setData(getListTrack());
        recyclerViewTrack.setAdapter(trackAdapter);
        recyclerViewTrack.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return view;
    }
    //set data for line Page View
    private ArrayList<Entry> data1() {
        ArrayList<Entry> dataValue = new ArrayList<Entry>();
        dataValue.add(new Entry(0, 23));
        dataValue.add(new Entry(1, 10));
        dataValue.add(new Entry(2, 13));
        dataValue.add(new Entry(3, 24));

        return dataValue;
    }
    //set data for Visitor
    private ArrayList<Entry> data2() {
        ArrayList<Entry> dataValue = new ArrayList<Entry>();
        dataValue.add(new Entry(0, 7));
        dataValue.add(new Entry(1, 13));
        dataValue.add(new Entry(2, 8));
        dataValue.add(new Entry(3, 10));

        return dataValue;
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

    //function to get list Track
    private List<Track> getListTrack()
    {
        List<Track>  list = new ArrayList<>();

        daoTrack.getByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    Track track = data.getValue(Track.class);
                    if(track != null){
                        String key = data.getKey();
                        track.setKey(key);
                        list.add(track);
                    }

                }
                trackAdapter.notifyDataSetChanged();
                trackAdapter.setData(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return list;
    }
}
