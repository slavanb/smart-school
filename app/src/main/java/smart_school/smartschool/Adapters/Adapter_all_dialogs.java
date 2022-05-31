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
import smart_school.smartschool.Model.All_dialogs.Dialog;

import smart_school.smartschool.R;

public class Adapter_all_dialogs  extends RecyclerView.Adapter<Adapter_all_dialogs.ViewHolder> {
    private List<Dialog> listInfo=new ArrayList<>();
    private Context mContext;





    public Adapter_all_dialogs(Context mContext, List<Dialog> listInfo) {
        this.listInfo = listInfo;
        this.mContext = mContext;
        }


    @Override
    public Adapter_all_dialogs.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_all_messages, parent, false);
        Adapter_all_dialogs.ViewHolder vh = new Adapter_all_dialogs.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Adapter_all_dialogs.ViewHolder holder, final int position) {
        holder.linItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();

                /*
                bundle.putString("predmet_name",listInfo.get(position).getPREDMETNAME());
                bundle.putString("predmetId",listInfo.get(position).getPREDMETID());
               */
                bundle.putString("human_id", String.valueOf(listInfo.get(position).getInterlocutorId()));


                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                One_dialog_fragment myFragment = new One_dialog_fragment();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("lesson").commit();

            }
        });

        Glide.with(mContext).load(listInfo.get(position).getInterlocutorAvatar()).into(holder.avatar);
        holder.tvName.setText(listInfo.get(position).getInterlocutorName());
         holder.tvMess.setText(listInfo.get(position).getPreviewText());
         String[] dateAndTime=listInfo.get(position).getDateTime().split(" ");
         String[] date=dateAndTime[0].split("-");
         holder.tvDate.setText(date[2]+"."+date[1]);
         holder.tvPredmet.setText(listInfo.get(position).getRole());

         if (listInfo.get(position).getNotRead().equalsIgnoreCase("0")){
            // holder.tvCountMess.setText("Не прочитаних повідомлень : "+listInfo.get(position).getNotRead());
             holder.tvCountMess.setVisibility(View.GONE);
         }else {
             holder.tvCountMess.setText(listInfo.get(position).getNotRead());
         }






    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;

        public CircleImageView avatar;
        public TextView tvMess;
        public TextView tvClass;
        public TextView tvPredmet;
        public TextView tvDate;
        public TextView tvCountMess;


        public LinearLayout linItem;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName=(TextView)itemView.findViewById(R.id.tv_name);
            tvClass=(TextView)itemView.findViewById(R.id.tv_class);
            tvPredmet=(TextView)itemView.findViewById(R.id.tv_predmet);

            tvMess=(TextView)itemView.findViewById(R.id.tv_mess);
            tvDate=(TextView)itemView.findViewById(R.id.tv_date);
            tvCountMess=(TextView)itemView.findViewById(R.id.tv_count_mess);
            linItem=(LinearLayout)itemView.findViewById(R.id.lin_item);
            avatar=(CircleImageView)itemView.findViewById(R.id.img_ava);





        }
    }
}

