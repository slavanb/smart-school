package smart_school.smartschool.Adapters;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import smart_school.smartschool.Fragments.Detalis_one_news;
import smart_school.smartschool.Model.List_news;
import smart_school.smartschool.Model.Rozpisanie.New.LESSON;
import smart_school.smartschool.Model.Rozpisanie.New.OCENKI;
import smart_school.smartschool.R;

public class Adapter_for_lesson_list_in_item_list extends RecyclerView.Adapter<Adapter_for_lesson_list_in_item_list.ViewHolder> {
    private List<OCENKI> listInfo=new ArrayList<>();
    private Context mContext;



    public Adapter_for_lesson_list_in_item_list(Context mContext, List<OCENKI> listInfo) {
        this.listInfo = listInfo;
        this.mContext = mContext;
    }


    @Override
    public Adapter_for_lesson_list_in_item_list.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ocenki_lesson_in_item, parent, false);
        Adapter_for_lesson_list_in_item_list.ViewHolder vh = new Adapter_for_lesson_list_in_item_list.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Adapter_for_lesson_list_in_item_list.ViewHolder holder, final int position) {

        holder.tvHead.setText(listInfo.get(position).getOcenkaName());
      /*
        if (!listInfo.get(position).getZAMECHANIE().equalsIgnoreCase("")||listInfo.get(position).getZAMECHANIE()!=null) {
            holder.tvMess.setText(listInfo.get(position).getZAMECHANIE());
        }
        */

        /*
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
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("detalis").commit();
            }
        });

        */

    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvHead;
        public TextView tvMess;


        public ViewHolder(View itemView) {
            super(itemView);
            tvHead=(TextView)itemView.findViewById(R.id.tv_head);

            tvMess=(TextView)itemView.findViewById(R.id.tv_mess);





        }
    }
}