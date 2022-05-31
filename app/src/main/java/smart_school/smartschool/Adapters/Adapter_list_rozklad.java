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

import java.util.ArrayList;
import java.util.List;


import smart_school.smartschool.Fragments.One_lesson_info;
import smart_school.smartschool.Model.Pupil_classes.Pupil;
import smart_school.smartschool.Model.Rozpisanie.Lesson;
import smart_school.smartschool.Model.Rozpisanie.Rozpisanie_model;
import smart_school.smartschool.R;

public class Adapter_list_rozklad  extends RecyclerView.Adapter<Adapter_list_rozklad.ViewHolder> {
    private List<Lesson> listInfo=new ArrayList<>();
    private Context mContext;



    public Adapter_list_rozklad(Context mContext, List<Lesson> listInfo) {
        this.listInfo = listInfo;
        this.mContext = mContext;
    }


    @Override
    public Adapter_list_rozklad.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_rozklad, parent, false);
        Adapter_list_rozklad.ViewHolder vh = new Adapter_list_rozklad.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Adapter_list_rozklad.ViewHolder holder, final int position) {





        holder.linItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("id",String.valueOf(listInfo.get(position).getLESSONID()));
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                One_lesson_info myFragment = new One_lesson_info();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("lesson").commit();

            }
        });


        holder.tvDate.setText(listInfo.get(position).getLESSONDATEUA());
        holder.tvDateName.setText(listInfo.get(position).getLESSONDATEL());
        holder.tvNameSTR.setText(listInfo.get(position).getLESSONNUMSTR());
        holder.tvNamePredmet.setText(listInfo.get(position).getPREDMETNAME());

        if (listInfo.get(position).getNAMEGRUP()==null){
            holder.tvNameGroup.setVisibility(View.GONE);
        }else {
            holder.tvNameGroup.setText(listInfo.get(position).getNAMEGRUP());
        }
        if (listInfo.get(position).getLESSONTHEMA()==null){
            holder.tvLessonThema.setVisibility(View.GONE);
        }else {
            holder.tvLessonThema.setText(listInfo.get(position).getLESSONTHEMA());

        }
        if (String.valueOf(listInfo.get(position).getOCENKI()).equalsIgnoreCase("null")){
            holder.tvOcenci.setVisibility(View.GONE);
        }else {
            holder.tvOcenci.setText(listInfo.get(position).getOCENKI());
        }
        if (listInfo.get(position).getZAMECHANIE()==null){
            holder.tvZamechanie.setVisibility(View.GONE);
        }else {
            holder.tvZamechanie.setText(listInfo.get(position).getZAMECHANIE());
            }






    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDate;
        public TextView tvDateName;
        public TextView tvNameSTR;
        public TextView tvNamePredmet;
        public TextView tvNameGroup;
        public TextView tvLessonThema;
        public TextView tvOcenci;
        public TextView tvZamechanie;

        public LinearLayout linItem;



        public ViewHolder(View itemView) {
            super(itemView);
            tvDate=(TextView)itemView.findViewById(R.id.tv_date);
            tvDateName=(TextView)itemView.findViewById(R.id.tv_date_name);
            tvNameSTR=(TextView)itemView.findViewById(R.id.tv_num_str);
            tvNamePredmet=(TextView)itemView.findViewById(R.id.tv_name_predmet);
            tvNameGroup=(TextView)itemView.findViewById(R.id.tv_name_group);
            tvLessonThema=(TextView)itemView.findViewById(R.id.tv_lesson_thema);
            tvOcenci=(TextView)itemView.findViewById(R.id.tv_ocenki);
            tvZamechanie=(TextView)itemView.findViewById(R.id.tv_zamechanie);
            linItem=(LinearLayout)itemView.findViewById(R.id.lin_item);






        }
    }
}

