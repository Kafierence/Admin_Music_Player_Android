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

import java.util.ArrayList;

import hcmute.edu.vn.admin_music_player_g6.Activity.Genre.EditGenreActivity;
import hcmute.edu.vn.admin_music_player_g6.Models.Genre;
import hcmute.edu.vn.admin_music_player_g6.R;


public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder>{

    private Context mContext;
    private ArrayList<Genre> genreList;

    public GenreAdapter(Context mContext,ArrayList<Genre> genreList) {
        this.mContext = mContext;
        this.genreList=genreList;
    }

    public void setData(ArrayList<Genre> list)
    {
        this.genreList = list;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        Genre genre = genreList.get(position);
        if(genre == null)
        {
            return;
        }
        else {
            //set genre to recycle view
            holder.genreName.setText(genre.getName());

            //set genre item to genre detail
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, EditGenreActivity.class);
                    intent.putExtra("name", genre.getName());

                    intent.putExtra("description", genre.getDescription());
                    intent.putExtra("key",genre.getKey());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        if(genreList != null)
        {
            return genreList.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);

        return new GenreViewHolder(view);
    }

    public class GenreViewHolder extends RecyclerView.ViewHolder{

        ImageView genreImg;
        TextView genreName;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);

            genreImg = itemView.findViewById(R.id.img_genre);
            genreName = itemView.findViewById(R.id.name_genre);

        }
    }

}
