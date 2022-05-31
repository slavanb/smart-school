package smart_school.smartschool.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import smart_school.smartschool.Activity.Container;
import smart_school.smartschool.ActivityFirst;
import smart_school.smartschool.Fragments.All_student;
import smart_school.smartschool.Fragments.Messages_fragment;
import smart_school.smartschool.Fragments.News_fragment;
import smart_school.smartschool.R;

import static android.content.Context.MODE_PRIVATE;

public class Adapter_drawer_list extends RecyclerView.Adapter<Adapter_drawer_list.ViewHolder> {
    private List<String> listInfo=new ArrayList<>();
    private Context mContext;



    public Adapter_drawer_list(Context mContext, List<String> listInfo) {
        this.listInfo = listInfo;
        this.mContext = mContext;
    }


    @Override
    public Adapter_drawer_list.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_drawer, parent, false);
        Adapter_drawer_list.ViewHolder vh = new Adapter_drawer_list.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Adapter_drawer_list.ViewHolder holder, final int position) {


        holder.tvName.setText(listInfo.get(position));



        holder.linItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listInfo.get(position).equalsIgnoreCase("учні")||listInfo.get(position).equalsIgnoreCase("Мій щоденник")){
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    All_student myFragment = new All_student();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("detalis").commit();
                }else if (listInfo.get(position).equalsIgnoreCase("вихід")) {
                    SharedPreferences preferences = mContext.getSharedPreferences("linkServer", MODE_PRIVATE);
                    preferences.edit().clear().commit();
                    mContext.startActivity(new Intent(mContext, ActivityFirst.class));
                    ((Container)mContext).finish();
                }else if (listInfo.get(position).equalsIgnoreCase("новини")){
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    News_fragment myFragment = new News_fragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("news").commit();
                }else if (listInfo.get(position).equalsIgnoreCase("Повідомлення")){
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Messages_fragment myFragment = new Messages_fragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("messages").commit();
                }
                ((Container)mContext).DrawerClose();

            }
        });


    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public FrameLayout linItem;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName=(TextView)itemView.findViewById(R.id.tv_text);
            linItem=(FrameLayout)itemView.findViewById(R.id.lin_item);





        }
    }
}

