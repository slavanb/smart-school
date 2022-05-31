package smart_school.smartschool.Adapters;

import android.content.Context;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import smart_school.smartschool.Fragments.One_dialog_fragment;
import smart_school.smartschool.Fragments.One_predmet_statistic_info;
import smart_school.smartschool.Model.All_messages.Teacher;
import smart_school.smartschool.Model.Statistic.Predmet;
import smart_school.smartschool.R;

public class Adapter_all_messages extends RecyclerView.Adapter<Adapter_all_messages.ViewHolder> {
    private List<Teacher> listInfo=new ArrayList<>();
    private Context mContext;
    private String ava;




    public Adapter_all_messages(Context mContext, List<Teacher> listInfo,String ava) {
        this.listInfo = listInfo;
        this.mContext = mContext;
        this.ava=ava;

    }


    @Override
    public Adapter_all_messages.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_contacts, parent, false);
        Adapter_all_messages.ViewHolder vh = new Adapter_all_messages.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Adapter_all_messages.ViewHolder holder, final int position) {





        holder.linItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();

                /*
                bundle.putString("predmet_name",listInfo.get(position).getPREDMETNAME());
                bundle.putString("predmetId",listInfo.get(position).getPREDMETID());
               */
                bundle.putString("human_id", String.valueOf(listInfo.get(position).getHumanId()));


                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                One_dialog_fragment myFragment = new One_dialog_fragment();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("lesson").commit();

            }
        });

        Glide.with(mContext).load(listInfo.get(position).getAvatar()).into(holder.avatar);
        holder.tvName.setText(listInfo.get(position).getName());
        holder.tvClass.setText(listInfo.get(position).getKlass_name());
        holder.tvPredmet.setText(listInfo.get(position).getPredmet_name());


       // holder.tvText.setText(listInfo.get(position).getKLASSNAME());





    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvText;
        public TextView tvNotRead;
        public CircleImageView avatar;
        public TextView tvClass;
        public TextView tvPredmet;


        public LinearLayout linItem;


        public ViewHolder(View itemView) {
            super(itemView);
            tvClass=(TextView)itemView.findViewById(R.id.tv_class);
            tvPredmet=(TextView)itemView.findViewById(R.id.tv_predmet);
            tvName=(TextView)itemView.findViewById(R.id.tv_name);
            tvText=(TextView)itemView.findViewById(R.id.tv_text);
            linItem=(LinearLayout)itemView.findViewById(R.id.lin_item);
            avatar=(CircleImageView)itemView.findViewById(R.id.img_ava);





        }
    }
}

