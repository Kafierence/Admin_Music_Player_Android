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

import hcmute.edu.vn.admin_music_player_g6.Models.Album;
import hcmute.edu.vn.admin_spotify.Activity.DetailAlbumActivity;
import hcmute.edu.vn.admin_spotify.Model.Album;
import hcmute.edu.vn.admin_spotify.R;


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>{

    private Context mContext;
    private List<Album> albumList;

    public AlbumAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Album> list)
    {
        this.albumList = list;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        if(album == null)
        {
            return;
        }
        else {
            //set Album to recycle view
            Glide.with(mContext).load(album.getResourceId()).into(holder.imgUser);
            holder.tvName.setText(album.getName());
            holder.tvDescription.setText(album.getDescription());

            //set Album item to Album detail
            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick(view, holder, album);
                }
            });
            holder.tvDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick(view, holder, album);
                }
            });
            holder.imgUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick(view, holder, album);
                }
            });
        }
    }
    public void itemClick(View view, AlbumViewHolder holder, Album album){
        Intent intent = new Intent(holder.itemView.getContext(), DetailAlbumActivity.class);
        intent.putExtra("album", album);
        holder.itemView.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {

        if(albumList != null)
        {
            return albumList.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_albums, parent, false);

        return new AlbumViewHolder(view);
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder{

        ImageView imgUser;
        TextView tvName;
        TextView tvDescription;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUser = itemView.findViewById(R.id.item_tracks_TrackImg);
            tvName = itemView.findViewById(R.id.item_tracks_TrackNameTxt);
            tvDescription = itemView.findViewById(R.id.item_tracks_AlbumTxt);

        }
    }

}
