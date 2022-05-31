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

import de.hdodenhof.circleimageview.CircleImageView;
import smart_school.smartschool.Model.Pupil_classes.Pupil;
import smart_school.smartschool.Fragments.Info_one_student;
import smart_school.smartschool.R;

public class Adapter_list_student extends RecyclerView.Adapter<Adapter_list_student.ViewHolder> {
    private List<Pupil> listInfo=new ArrayList<>();
    private Context mContext;



    public Adapter_list_student(Context mContext, List<Pupil> listInfo) {
        this.listInfo = listInfo;
        this.mContext = mContext;
    }


    @Override
    public Adapter_list_student.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_student, parent, false);
        Adapter_list_student.ViewHolder vh = new Adapter_list_student.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Adapter_list_student.ViewHolder holder, final int position) {

        Glide.with(mContext).load(listInfo.get(position).getHumanAvatar()).into(holder.imgImage);
        holder.tvFirstName.setText(listInfo.get(position).getFamilia()+" "+listInfo.get(position).getName()+" "+listInfo.get(position).getOtchestvo());
        holder.tv_second.setText(listInfo.get(position).getKlass().getFullKlassName());

        holder.linItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                bundle.putString("id",String.valueOf(listInfo.get(position).getPupilId()));
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Info_one_student myFragment = new Info_one_student();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("detalis").commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvFirstName;
        public TextView tv_second;
        public TextView tv_otchestvo;
        public CircleImageView imgImage;
        public LinearLayout linItem;


        public ViewHolder(View itemView) {
            super(itemView);
            tvFirstName=(TextView)itemView.findViewById(R.id.tv_first_name);
            tv_second=(TextView)itemView.findViewById(R.id.tv_second);
            tv_otchestvo=(TextView)itemView.findViewById(R.id.tv_otchestvo);
            imgImage=(de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.img_image);
            linItem=(LinearLayout)itemView.findViewById(R.id.lin_item);





        }
    }
}
