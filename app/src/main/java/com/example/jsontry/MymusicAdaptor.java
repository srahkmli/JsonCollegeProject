package com.example.jsontry;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class  MymusicAdaptor extends RecyclerView.Adapter<MymusicAdaptor.ViewHolder>{
    ViewHolder viewHolder;
    Context context;
    List<musics> music;

    public MymusicAdaptor(Context context , List<musics> music) {
        this.context = context;
        this.music = music;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View contextview = layoutInflater.inflate(R.layout.item,parent,false);
        viewHolder = new ViewHolder(contextview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position ) {
        TextView tvName =  holder.textView;
        CardView cdView = holder.cardView;
        final ImageView imView= holder.imageView;
        ImageView imView2= holder.imageView2;
        int id = music.get(position).getPath();
        int [] songs= new int[] {R.raw.music1,R.raw.music2,R.raw.music3,R.raw.music4};
        final MediaPlayer mediaPlayer= MediaPlayer.create(context, songs[id]);


       /* switch (position){
            case 0: tvName.setText(context.getString(R.string.item1));break;
            case 1: tvName.setText(context.getString(R.string.item2));break;
        }*/
        final musics mus = music.get(position);
        tvName.setText(mus.getName());


//Sop BTN
        imView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                imView.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);

                int id =mus.getPath();
                int [] songs= new int[] {R.raw.music1,R.raw.music2,R.raw.music3,R.raw.music4};
                mediaPlayer.create(context, songs[id]);
                notifyItemChanged(position);
            }
        });

//Start btn
        imView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Edited
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imView.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
                }else {
                    mediaPlayer.start();
                    imView.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                }

                //Edited
            }
        });
        cdView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });


    }

    @Override
    public int getItemCount() { return music.size(); }


    public  static  class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        CardView cardView;
        ImageView imageView;
        ImageView imageView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textAdapter);
            cardView = itemView.findViewById(R.id.cardview);
            imageView=itemView.findViewById(R.id.img);
            imageView2=itemView.findViewById(R.id.img2);
        }
    }
}

