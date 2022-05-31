package smart_school.smartschool.Adapters;

import android.content.Context;


import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import smart_school.smartschool.Fragments.One_predmet_statistic_info;
import smart_school.smartschool.Model.One_lesson_statistic_info.Ocenki;
import smart_school.smartschool.Model.Statistic.Predmet;
import smart_school.smartschool.R;

public class Adapter_one_predmet_statistic_info extends RecyclerView.Adapter<Adapter_one_predmet_statistic_info.ViewHolder> {
    private List<Ocenki> listInfo=new ArrayList<>();
    private Context mContext;




    public Adapter_one_predmet_statistic_info(Context mContext, List<Ocenki> listInfo) {
        this.listInfo = listInfo;
        this.mContext = mContext;

    }


    @Override
    public Adapter_one_predmet_statistic_info.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_one_predmet_statistic_info, parent, false);
        Adapter_one_predmet_statistic_info.ViewHolder vh = new Adapter_one_predmet_statistic_info.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Adapter_one_predmet_statistic_info.ViewHolder holder, final int position) {

        holder.tvPredmet.setText(listInfo.get(position).getLESSONDATEUA()+" "+listInfo.get(position).getLESSONDATEL()+" "+listInfo.get(position).getLESSONNUMSTR());

        String predHtml=listInfo.get(position).getPREDMETHTMLTEXT();
        if (predHtml!=null)
        holder.tvklassName.setText(Html.fromHtml(predHtml));
        holder.tvSrednijBall.setText(Html.fromHtml(listInfo.get(position).getNOTONLESSON()));
        holder.tvSerednyovagova.setText(Html.fromHtml(listInfo.get(position).getOCENKA()));
        holder.tvKilOcinok.setText(listInfo.get(position).getLESSONTHEMA());
        holder.tvMess.setText(Html.fromHtml(listInfo.get(position).getZAMECHANIE()));

       // holder.tvDate.setText(listInfo.get(position).getLESSONDATE().substring(0,5));
        holder.tvDate.setText(listInfo.get(position).getLESSONDATE());
        holder.tvDay.setText(listInfo.get(position).getLESSONDATEL());
        holder.tvNum.setText(listInfo.get(position).getLESSONNUMSTR());
        holder.tvThema.setText(listInfo.get(position).getLESSONTHEMA());
        holder.tvOcenka.setText(Html.fromHtml(listInfo.get(position).getOCENKA()));
        if (listInfo.get(position).getNOTONLESSON().equalsIgnoreCase("")){
            holder.tvNot.setText("Присутній");
        }else {
            holder.tvNot.setText(Html.fromHtml(listInfo.get(position).getNOTONLESSON()));
        }



    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDate;
        public TextView tvDay;
        public TextView tvNum;
        public TextView tvThema;
        public TextView tvOcenka;
        public TextView tvNot;
        public TextView tvMess;
        public ImageView imgTick;





        public TextView tvPredmet;
        public TextView tvklassName;
        public TextView tvSrednijBall;
        public TextView tvSerednyovagova;
        public TextView tvKilOcinok;
        public TextView tvKilZalikiv;
        public TextView tvKilNeZalikiv;
        public TextView tvKilPropuskiv;
        public TextView tvKilZapizneny;
        public TextView tvNameTeacher;

        public LinearLayout linItem;


        public ViewHolder(View itemView) {
            super(itemView);
            tvDate=(TextView)itemView.findViewById(R.id.tv_date);
            tvMess=(TextView)itemView.findViewById(R.id.tv_mess);
            tvDay=(TextView)itemView.findViewById(R.id.tv_day);
            tvNum=(TextView)itemView.findViewById(R.id.tv_num);
            tvOcenka=(TextView)itemView.findViewById(R.id.tv_ocenka);
            tvThema=(TextView)itemView.findViewById(R.id.tv_thema);
            tvNot=(TextView)itemView.findViewById(R.id.tv_not);
            imgTick=(ImageView) itemView.findViewById(R.id.img_tick);









            tvPredmet=(TextView)itemView.findViewById(R.id.tv_predmet);
            tvklassName=(TextView)itemView.findViewById(R.id.tv_class_name);
            tvSrednijBall=(TextView)itemView.findViewById(R.id.tv_serednij_ball);
            tvSerednyovagova=(TextView)itemView.findViewById(R.id.tv_ser_vagova_ocinka);
            tvKilOcinok=(TextView)itemView.findViewById(R.id.tv_kil_ocinok);
            tvKilZalikiv=(TextView)itemView.findViewById(R.id.tv_kil_zalikiv);
            tvKilNeZalikiv=(TextView)itemView.findViewById(R.id.tv_kil_ne_zalikiv);
            tvKilPropuskiv=(TextView)itemView.findViewById(R.id.tv_kil_propuskiv);
            tvKilZapizneny=(TextView)itemView.findViewById(R.id.tv_kil_zapizneny);
            tvNameTeacher=(TextView)itemView.findViewById(R.id.tv_group_name);
            linItem=(LinearLayout)itemView.findViewById(R.id.lin_item);





        }
    }
}

