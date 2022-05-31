package smart_school.smartschool.Adapters;

import android.content.Context;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import smart_school.smartschool.Fragments.One_lesson_info;
import smart_school.smartschool.Fragments.One_predmet_statistic_info;
import smart_school.smartschool.Model.Rozpisanie.Lesson;
import smart_school.smartschool.Model.Statistic.Predmet;
import smart_school.smartschool.Model.Statistic.StatisticHead;
import smart_school.smartschool.R;

public class Adapter_statistic_list extends RecyclerView.Adapter<Adapter_statistic_list.ViewHolder> {
    private List<Predmet> listInfo=new ArrayList<>();
    private Context mContext;
    private String dateFrom="";
    private String dateTo="";
    private String id="";
    private String mark_system="";




    public Adapter_statistic_list(Context mContext, List<Predmet> listInfo,String dateFrom,String dateTo,String id,String mark_system) {
        this.listInfo = listInfo;
        this.mContext = mContext;
        this.dateFrom=dateFrom;
        this.dateTo=dateTo;
        this.id=id;
        this.mark_system=mark_system;
    }


    @Override
    public Adapter_statistic_list.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_statistic, parent, false);
        Adapter_statistic_list.ViewHolder vh = new Adapter_statistic_list.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Adapter_statistic_list.ViewHolder holder, final int position) {


        holder.linItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("dateFrom",dateFrom);
                bundle.putString("dateTo",dateTo);
                bundle.putString("id",id);
                bundle.putString("predmet_name",listInfo.get(position).getPREDMETNAME());
                bundle.putString("predmetId",listInfo.get(position).getPREDMETID());
                bundle.putString("Klass_Pr_Obuch_LIST_ID",listInfo.get(position).getKlass_Pr_Obuch_LIST_ID());

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                One_predmet_statistic_info myFragment = new One_predmet_statistic_info();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("lesson").commit();
                }
        });




        holder.tvPredmet.setText(listInfo.get(position).getPREDMETNAME()+" "+listInfo.get(position).getKLASSNAME());
        String number=String.valueOf(position+1);
        holder.tvNumber.setText(number+".");
        holder.tvSerBal.setText(listInfo.get(position).getAvgOcenka());

        holder.tvSerVagOcinka.setText(listInfo.get(position).getAvgWeightOcenka().trim());
        holder.tvZalikiv.setText(listInfo.get(position).getCntZachet());
        holder.tvNeZalikiv.setText(listInfo.get(position).getCntNoZachet  ());
        holder.tvOcinok.setText(listInfo.get(position).getCntOcenki  ());
        holder.tvPropuskiv.setText(listInfo.get(position).getCntNotOnLesson());
        holder.tvZapizneny.setText(listInfo.get(position).getCntLateOnLesson());
        holder.tvSerBalEtalon.setText(" / "+mark_system);
        holder.tvSerVagOcinkaEtalon.setText(" / "+mark_system);




        if (listInfo.get(position).getAvgWeightOcenka().equalsIgnoreCase("не задано")){
            holder.tvSerBalEtalon.setVisibility(View.GONE);
            holder.pbSerOcinka.setVisibility(View.GONE);
        }else {
            holder.tvSerBalEtalon.setVisibility(View.VISIBLE);
            holder.pbSerOcinka.setVisibility(View.VISIBLE);
            String prog=listInfo.get(position).getAvgWeightOcenka();
            Log.e("TAG",prog+"---------"+position);

            if (prog.length()>2){
                String[] fff=prog.split("\\.");
                if (fff.length>0)
                    holder.pbSerOcinka.setProgress(Integer.valueOf(fff[0]));
            }else {
                holder.pbSerOcinka.setProgress(Integer.valueOf(prog));
            }


        }
        if (listInfo.get(position).getAvgOcenka().equalsIgnoreCase("не задано")){
            holder.tvSerVagOcinkaEtalon.setVisibility(View.GONE);
            holder.pbSerVagOcinka.setVisibility(View.GONE);
        }else {
            String prog=listInfo.get(position).getAvgOcenka();
            Log.e("TAG",prog+"---------"+position);
            if (prog.length()>2){
                String[] fff=prog.split("\\.");
                if (fff.length>0)
                    holder.pbSerVagOcinka.setProgress(Integer.valueOf(fff[0]));
            }else {
                holder.pbSerVagOcinka.setProgress(Integer.valueOf(prog));
            }
            holder.pbSerVagOcinka.setVisibility(View.VISIBLE);
            holder.tvSerVagOcinkaEtalon.setVisibility(View.VISIBLE);
        }




    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

         TextView tvPredmet;
         TextView tvNumber;
         TextView tvSerBal;
        TextView tvSerVagOcinka;
         TextView tvZalikiv;
         TextView tvNeZalikiv;
         TextView tvOcinok;
         TextView tvPropuskiv;
         TextView tvZapizneny;
         TextView tvSerBalEtalon;
         TextView tvSerVagOcinkaEtalon;
         ProgressBar pbSerOcinka;
         ProgressBar pbSerVagOcinka;


         LinearLayout linItem;


        public ViewHolder(View itemView) {
            super(itemView);
            tvPredmet=(TextView)itemView.findViewById(R.id.tv_predmet);
            tvNumber=(TextView)itemView.findViewById(R.id.tv_number);
            tvSerBal=(TextView)itemView.findViewById(R.id.tv_serednij_bal);
            tvSerVagOcinka=(TextView)itemView.findViewById(R.id.tv_ser_vag_ocinka);
            tvZalikiv=(TextView)itemView.findViewById(R.id.tv_zalik);
            tvNeZalikiv=(TextView)itemView.findViewById(R.id.tv_ne_zalik);
            tvOcinok=(TextView)itemView.findViewById(R.id.tv_ocinok);
            tvPropuskiv=(TextView)itemView.findViewById(R.id.tv_propuskiv);
            tvZapizneny=(TextView)itemView.findViewById(R.id.tv_zapizneny);
            tvSerBalEtalon=(TextView)itemView.findViewById(R.id.tv_ser_bal_etalon);
            tvSerVagOcinkaEtalon=(TextView)itemView.findViewById(R.id.tv_ser_vag_ocinka_etalon);
            linItem=(LinearLayout)itemView.findViewById(R.id.lin_item);
            pbSerOcinka=(ProgressBar)itemView.findViewById(R.id.progressBar_ser_bal);
            pbSerVagOcinka=(ProgressBar)itemView.findViewById(R.id.progressBar_ser_vag);





        }
    }
}

