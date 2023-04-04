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

import java.util.List;

import hcmute.edu.vn.admin_music_player_g6.Activity.Artists.EditArtistActivity;
import hcmute.edu.vn.admin_music_player_g6.Models.Artist;
import hcmute.edu.vn.admin_music_player_g6.R;


public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private Context context;
    private List<Artist> artists;


    public  ArtistAdapter(Context context){ this.context=context; }

    public void setData(List<Artist> list)
    {
        this.artists = list;
    }

    @Override
    public int getItemCount () {
        if(artists!=null){
            return artists.size();
        }
        return 0;
    }
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ArtistViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Artist artist=artists.get(position);
        if(artist==null){
            return;
        }
        else{
            //set artist to recycle view
            holder.artistName.setText(artist.getNameArtist());
            //set artist item to artist detail
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EditArtistActivity.class);
                    intent.putExtra("idArtist",artist.getIdArtist());
                    intent.putExtra("nameArtist",artist.getNameArtist());
                    intent.putExtra("numFollower",artist.getNumFollower());
                    intent.putExtra("imageArtist",artist.getImageArtist());
                    intent.putExtra("emailArtist",artist.getEmailArtist());
                    intent.putExtra("genderArtist",artist.getGenderArtist());
                    intent.putExtra("linkSocial",artist.getLinkSocial());

                    context.startActivity(intent);
                }
            });
        }
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        ImageView artistImage;
        TextView artistName;
        TextView artistDescription;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            artistImage=itemView.findViewById(R.id.img_artist);
            artistName=itemView.findViewById(R.id.item_tracks_TrackNameTxt);
            artistDescription=itemView.findViewById(R.id.item_tracks_AlbumTxt);

        }
    }
}
