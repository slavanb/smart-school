package smart_school.smartschool.Adapters;

import android.content.Context;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import smart_school.smartschool.Fragments.News_fragment;
import smart_school.smartschool.Model.List_news;
import smart_school.smartschool.Fragments.Detalis_one_news;
import smart_school.smartschool.R;

public class Adapter_list_news extends RecyclerView.Adapter<Adapter_list_news.ViewHolder> {
    private List<List_news> listInfo=new ArrayList<>();
    private Context mContext;
    private News_fragment news_fragment;


    public Adapter_list_news(Context mContext, List<List_news> listInfo,News_fragment news_fragment) {
        this.listInfo = listInfo;
        this.mContext = mContext;
        this.news_fragment=news_fragment;
    }


    @Override
    public Adapter_list_news.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_news, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Glide.with(mContext).load(listInfo.get(position).getIMAGE()).into(holder.imgImage);
        holder.tvName.setText(listInfo.get(position).getPOSTTITLE());
        holder.tvDate.setText(listInfo.get(position).getPOSTDATE());


        holder.linItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("id",String.valueOf(listInfo.get(position).getID()));
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Detalis_one_news myFragment = new Detalis_one_news();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().hide(news_fragment).add(R.id.container, myFragment).addToBackStack("detalis").commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public ImageView imgImage;
        public LinearLayout linItem;
        public TextView tvDate;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName=(TextView)itemView.findViewById(R.id.tv_text);
            imgImage=(ImageView) itemView.findViewById(R.id.img_image);
            linItem=(LinearLayout)itemView.findViewById(R.id.lin_item);
            tvDate=(TextView)itemView.findViewById(R.id.tv_date);





        }
    }
}
