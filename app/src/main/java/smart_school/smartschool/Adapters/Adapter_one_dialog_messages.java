package smart_school.smartschool.Adapters;

import android.content.Context;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import smart_school.smartschool.Model.One_dialog_messages.Message;
import smart_school.smartschool.R;

public class Adapter_one_dialog_messages  extends RecyclerView.Adapter<Adapter_one_dialog_messages.ViewHolder> {
    private List<Message> listInfo=new ArrayList<>();
    private String name;
    private Context mContext;
    private String avatar;
    private String MyAvatar;
    private int my;



    public Adapter_one_dialog_messages(Context mContext, List<Message> listInfo,String name,String avatar,String MyAvatar) {
        this.listInfo = listInfo;
        this.mContext = mContext;
        this.name=name;
        this.avatar=avatar;
        this.MyAvatar=MyAvatar;

    }


    @Override
    public Adapter_one_dialog_messages.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_one_dialog_message_my, parent, false);
        Adapter_one_dialog_messages.ViewHolder vh = new Adapter_one_dialog_messages.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Adapter_one_dialog_messages.ViewHolder holder, final int position) {





        holder.linItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();

                /*
                bundle.putString("predmet_name",listInfo.get(position).getPREDMETNAME());
                bundle.putString("predmetId",listInfo.get(position).getPREDMETID());

                bundle.putString("human_id", String.valueOf(listInfo.get(position).getHumanId()));


                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                One_dialog_fragment myFragment = new One_dialog_fragment();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("lesson").commit();
  */
            }
        });


        holder.tvText.setText(listInfo.get(position).getText());
        holder.tvDate.setText(listInfo.get(position).getDateTime());
       holder.tvAvtor.setText(name);
        // holder.tvText.setText(listInfo.get(position).getKLASSNAME());





    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvAvtor;
        public TextView tvDate;
        public TextView tvText;


        public LinearLayout linItem;


        public ViewHolder(View itemView) {
            super(itemView);
            tvAvtor=(TextView)itemView.findViewById(R.id.tv_avtor);
            tvText=(TextView)itemView.findViewById(R.id.tv_text_message);
            tvDate=(TextView)itemView.findViewById(R.id.tv_date);
            linItem=(LinearLayout)itemView.findViewById(R.id.lin_item);





        }
    }
}
