package com.example.autoimageslider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.MyViewHolder> {

    private ArrayList<SliderItem> list=new ArrayList<>();
    private ViewPager2 viewPager2;

    public SliderAdapter(ArrayList<SliderItem> list, ViewPager2 viewPager2){
        this.list=list;
        this.viewPager2=viewPager2;
    }

    @NonNull
    @Override
    public SliderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_imgage_container,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.setBind(list.get(position));

        if(position==list.size()-2){
            viewPager2.post(runnable);
        }

    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        private RoundedImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(RoundedImageView) itemView.findViewById(R.id.imageSlide);
        }

        public void setBind(SliderItem item){
            imageView.setImageResource(item.getImage());
        }
    }


    private Runnable runnable=new Runnable() {
        @Override
        public void run() {

            list.addAll(list);
            notifyDataSetChanged();

        }
    };



}
