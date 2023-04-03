package hcmute.edu.vn.admin_music_player_g6.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import hcmute.edu.vn.admin_music_player_g6.Activity.DetailTrackActivity;
import hcmute.edu.vn.admin_music_player_g6.Models.Album;
import hcmute.edu.vn.admin_music_player_g6.Models.Track;
import hcmute.edu.vn.admin_music_player_g6.R;


public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder>{

    private Context mContext;
    private List<Track> trackList;
    Album finding_album = new Album();
    public TrackAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Track> list)
    {
        this.trackList = list;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
            Track track = trackList.get(position);
            //set Track to recycle view
            Glide.with(mContext).load(track.getImage()).into(holder.item_tracks_TrackImg);
            holder.item_tracks_TrackNameTxt.setText(track.getName());
            try{
                if(track.gettAlbum() != null){
                    holder.item_tracks_AlbumTxt.setText(track.gettAlbum().getName());
                }

            }
            catch (Exception e){

            }

            //set Track item to Track detail
            holder.item_tracks_TrackImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick(holder, track);
                }
            });
            holder.item_tracks_TrackNameTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick(holder, track);
                }
            });
            holder.item_tracks_AlbumTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
    }

    public void itemClick(TrackViewHolder holder,  Track track){
        Intent intent = new Intent(holder.itemView.getContext(), DetailTrackActivity.class);
        intent.putExtra("track", track);
        holder.itemView.getContext().startActivity(intent);
    }
    @Override
    public int getItemCount() {

        if(trackList != null)
        {
            return trackList.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_albums, parent, false);

        return new TrackViewHolder(view);
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder{

        ImageView item_tracks_TrackImg;
        TextView item_tracks_TrackNameTxt, item_tracks_AlbumTxt;

        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);

            item_tracks_TrackImg = itemView.findViewById(R.id.item_tracks_TrackImg);
            item_tracks_TrackNameTxt = itemView.findViewById(R.id.item_tracks_TrackNameTxt);
            item_tracks_AlbumTxt = itemView.findViewById(R.id.item_tracks_AlbumTxt);

        }
    }

}
