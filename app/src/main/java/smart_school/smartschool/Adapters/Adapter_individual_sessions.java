package smart_school.smartschool.Adapters;



import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import smart_school.smartschool.Fragments.One_lesson_info;
import smart_school.smartschool.R;

public class Adapter_individual_sessions extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    //Header header;
    List<ListItem> list;

    public Adapter_individual_sessions(List<ListItem> headerItems) {
        this.list = headerItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
            return new Adapter_individual_sessions.VHHeader(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_individual_session, parent, false);
            return new Adapter_individual_sessions.VHItem(v);
        }
        // return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof Adapter_individual_sessions.VHHeader) {
            // VHHeader VHheader = (VHHeader)holder;
            Header currentItem = (Header) list.get(position);
            Adapter_individual_sessions.VHHeader VHheader = (Adapter_individual_sessions.VHHeader) holder;
            String[] date = currentItem.getHeader().split(" ");
            VHheader.txtTitle.setText(date[0]);
            VHheader.tvDateDivider.setText(date[1]);
        } else if (holder instanceof Adapter_individual_sessions.VHItem) {
            ContentItem currentItem = (ContentItem) list.get(position);
            final Adapter_individual_sessions.VHItem VHitem = (Adapter_individual_sessions.VHItem) holder;


            /*
            VHitem.linItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    int ooo = ((ContentItem) list.get(position)).getLESSONID();
                    bundle.putString("id", String.valueOf(((ContentItem) list.get(position)).getLESSONID()));
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    One_lesson_info myFragment = new One_lesson_info();
                    myFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("lesson").commit();

                }
            });
            */
/*

            VHitem.tvDate.setText(((ContentItem) list.get(position)).getLESSONDATEUA());
            VHitem.tvDateName.setText(((ContentItem) list.get(position)).getLESSONDATEL());
            VHitem.tvNameSTR.setText(((ContentItem) list.get(position)).getLESSONNUMSTR());
//            VHitem.tvNamePredmet.setText(((ContentItem) list.get(position)).getPREDMETNAME());


            VHitem.tvPredmetName.setText(((ContentItem) list.get(position)).getPREDMETNAME());
            //  VHitem.tvGroupName.setText(((ContentItem) list.get(position)).getNAMEGRUP());
           // String homeW = ((ContentItem) list.get(position)).getHomeWork();

            //VHitem.tvOcenci.setText(Html.fromHtml(homeW));
            String ocenkaHtml=((ContentItem) list.get(position)).getOCENKI();
            VHitem.tvOcenci.setText(Html.fromHtml(ocenkaHtml));
            String prisuts=((ContentItem) list.get(position)).getNotOnLesson();
            VHitem.tvPrisutstview.setText(Html.fromHtml(prisuts));
            VHitem.tvZamechanie.setText(((ContentItem) list.get(position)).getZamechanie());

            VHitem.tvNameTeacher.setText(((ContentItem) list.get(position)).getFio());
*/


            /*
            if (((ContentItem) list.get(position)).getHomeWork() == null || ((ContentItem) list.get(position)).getHomeWork().length() == 0) {
                VHitem.linOcenkiNew.setVisibility(View.GONE);
                } else {
                VHitem.linOcenkiNew.setVisibility(View.VISIBLE);
            }
            */


        }


    }

    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {

        return list.get(position) instanceof Header;

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VHHeader extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView tvDateDivider;

        public VHHeader(View itemView) {
            super(itemView);
            this.txtTitle = (TextView) itemView.findViewById(R.id.txtHeader);
            this.tvDateDivider = (TextView) itemView.findViewById(R.id.tv_date_divider);
        }
    }

    class VHItem extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvDateName;
        TextView tvNameSTR;
        TextView tvNamePredmet;
        TextView tvNameGroup;
        TextView tvLessonThema;
        TextView tvOcenci;
        TextView tvZamechanie;
        TextView tvPredmetName;
        TextView tvGroupName;
        TextView tvNameTeacher;
        TextView tvPrisutstview;

        LinearLayout linItem;
        LinearLayout linTema;
        LinearLayout linOcenki;
        LinearLayout linGrupa;
        LinearLayout linOcenkiNew;

        public VHItem(View itemView) {
            super(itemView);
            this.tvNameTeacher=(TextView)itemView.findViewById(R.id.tv_name_teacher);
            this.tvPrisutstview=(TextView)itemView.findViewById(R.id.tv_prisutstvie);
            this.tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            this.tvDateName = (TextView) itemView.findViewById(R.id.tv_date_name);
            this.tvNameSTR = (TextView) itemView.findViewById(R.id.tv_num_str);
            this.tvNamePredmet = (TextView) itemView.findViewById(R.id.tv_name_predmet);
            this.tvNameGroup = (TextView) itemView.findViewById(R.id.tv_name_group);
            this.tvLessonThema = (TextView) itemView.findViewById(R.id.tv_lesson_thema);
            this.tvOcenci = (TextView) itemView.findViewById(R.id.tv_ocenki);
            this.tvZamechanie = (TextView) itemView.findViewById(R.id.tv_zamechanie);
            this.linItem = (LinearLayout) itemView.findViewById(R.id.lin_item);
            this.linGrupa = (LinearLayout) itemView.findViewById(R.id.lin_grupa);
            this.linOcenki = (LinearLayout) itemView.findViewById(R.id.lin_ocenki);
            this.linTema = (LinearLayout) itemView.findViewById(R.id.lin_tema);
         //   this.linOcenkiNew = (LinearLayout) itemView.findViewById(R.id.lin_ocenki_new);
            this.tvPredmetName = (TextView) itemView.findViewById(R.id.tv_predmet_name);
            this.tvGroupName = (TextView) itemView.findViewById(R.id.tv_group_name);


        }
    }
}
