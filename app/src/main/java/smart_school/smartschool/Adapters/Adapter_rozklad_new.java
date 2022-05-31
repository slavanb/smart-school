package smart_school.smartschool.Adapters;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import smart_school.smartschool.Fragments.One_lesson_info;
import smart_school.smartschool.R;

public class Adapter_rozklad_new extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
private static final int TYPE_HEADER = 0;
private static final int TYPE_ITEM = 1;
//Header header;
List<ListItem> list;
public Adapter_rozklad_new(List<ListItem> headerItems)
{
    this.list = headerItems;
    }

@Override
public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if(viewType==TYPE_HEADER)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
        return  new VHHeader(v);
    }
    else
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_rozklad, parent, false);
        return new VHItem(v);
    }
   // return null;
}

@Override
public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)      {

    if(holder instanceof VHHeader)
    {
       // VHHeader VHheader = (VHHeader)holder;
        Header  currentItem = (Header) list.get(position);
        VHHeader VHheader = (VHHeader)holder;
        String[] date=currentItem.getHeader().split(" ");
        VHheader.txtTitle.setText(date[0]);
        VHheader.tvDateDivider.setText(date[1]);
        //VHheader.tvGroupName.setText(currentItem.getFio());
    }
    else if(holder instanceof VHItem)
    {
        ContentItem currentItem = (ContentItem) list.get(position);
        final VHItem VHitem = (VHItem)holder;


        /*
        VHitem.tv35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();

                int ooo=((ContentItem) list.get(position)).getLESSONID();
                bundle.putString("id",String.valueOf(((ContentItem) list.get(position)).getLESSONID()));
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                One_lesson_info myFragment = new One_lesson_info();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("lesson").commit();

            }
        });
 */
   //     VHitem.tvLessonThema.setText(((ContentItem) list.get(position)).getLESSONTHEMA());
    //    VHitem.tvNameGroup.setText(((ContentItem) list.get(position)).getNAMEGRUP());

        /*
        VHitem.tvDate.setText(((ContentItem) list.get(position)).getLESSONDATEUA());
        VHitem.tvDateName.setText(((ContentItem) list.get(position)).getLESSONDATEL());
        VHitem.tvNameSTR.setText(((ContentItem) list.get(position)).getLESSONNUMSTR());
        VHitem.tvNamePredmet.setText(((ContentItem) list.get(position)).getPREDMETNAME());



        VHitem.tvPredmetName.setText(((ContentItem) list.get(position)).getPREDMETNAME());
      //  VHitem.tvGroupName.setText(((ContentItem) list.get(position)).getNAMEGRUP());
          String kkk=  ((ContentItem) list.get(position)).getOCENKI();

        //VHitem.tvOcenci.setText(((ContentItem) list.get(position)).getOCENKI());
        VHitem.tvOcenci.setText( Html.fromHtml(kkk));

        if (((ContentItem) list.get(position)).getOCENKI()==null||((ContentItem) list.get(position)).getOCENKI().length()==0){
            VHitem.linOcenkiNew.setVisibility(View.GONE);

        }else {
            VHitem.linOcenkiNew.setVisibility(View.VISIBLE);
        }
        */




/*
        if (((ContentItem) list.get(position)).getNAMEGRUP()==null){
            VHitem.linGrupa.setVisibility(View.GONE);
        }else {
            VHitem.linGrupa.setVisibility(View.VISIBLE);
            VHitem.tvNameGroup.setText(((ContentItem) list.get(position)).getNAMEGRUP());
        }
        if (((ContentItem) list.get(position)).getLESSONTHEMA().equalsIgnoreCase("")){
            VHitem.linTema.setVisibility(View.GONE);
        }else {
            VHitem.linTema.setVisibility(View.VISIBLE);
            VHitem.tvLessonThema.setText(((ContentItem) list.get(position)).getLESSONTHEMA());

        }
        if (String.valueOf(((ContentItem) list.get(position)).getOCENKI()).equalsIgnoreCase("null")){
            VHitem.linOcenki.setVisibility(View.GONE);
        }else {
           // VHitem.tvOcenci.setText(((ContentItem) list.get(position)).getOCENKI());
        }
  /*
        if (list.get(position).getZAMECHANIE()==null){
            VHitem.tvZamechanie.setVisibility(View.GONE);
        }else {
            VHitem.tvZamechanie.setText(list.get(position).getZAMECHANIE());
        }
*/






    }


}

public int getItemViewType(int position) {
    if(isPositionHeader(position))
        return TYPE_HEADER;
    return TYPE_ITEM;
}

private boolean isPositionHeader(int position)
{

    return list.get(position) instanceof Header;

}

@Override
public int getItemCount() {
    return list.size();
}
class VHHeader extends RecyclerView.ViewHolder{
    TextView txtTitle;
    TextView tvDateDivider;
     TextView tvGroupName;

    public VHHeader(View itemView) {
        super(itemView);
        this.tvGroupName=(TextView) itemView.findViewById(R.id.tv_group_name);
        this.txtTitle = (TextView)itemView.findViewById(R.id.txtHeader);
        this.tvDateDivider = (TextView)itemView.findViewById(R.id.tv_date_divider);
    }
}
class VHItem extends RecyclerView.ViewHolder{
     TextView tvDate;
     TextView tvDateName;
     TextView tvNameSTR;
     TextView tvNamePredmet;
     TextView tvNameGroup;
     TextView tvLessonThema;
     TextView tvOcenci;
     TextView tvZamechanie;
     TextView tvPredmetName;
     TextView tv35;


     LinearLayout linItem;
     LinearLayout linParent;
     LinearLayout linNext;
     LinearLayout linTema;
     LinearLayout linOcenki;
     LinearLayout linGrupa;
     LinearLayout linOcenkiNew;

    public VHItem(View itemView) {
        super(itemView);
        this.tvDate=(TextView)itemView.findViewById(R.id.tv_date);
       // this.tv35=(TextView)itemView.findViewById(R.id.textView35);
        this.tvDateName=(TextView)itemView.findViewById(R.id.tv_date_name);
        this.tvNameSTR=(TextView)itemView.findViewById(R.id.tv_num_str);
        this.tvNamePredmet=(TextView)itemView.findViewById(R.id.tv_name_predmet);
        this.tvNameGroup=(TextView)itemView.findViewById(R.id.tv_name_group);
        this.tvLessonThema=(TextView)itemView.findViewById(R.id.tv_lesson_thema);
        this.tvOcenci=(TextView)itemView.findViewById(R.id.tv_ocenki);
        this.tvZamechanie=(TextView)itemView.findViewById(R.id.tv_zamechanie);
        this.linItem=(LinearLayout)itemView.findViewById(R.id.lin_item);
        this.linNext=(LinearLayout)itemView.findViewById(R.id.lin_next);
        this.linParent=(LinearLayout)itemView.findViewById(R.id.lin_parent);
        this.linGrupa=(LinearLayout)itemView.findViewById(R.id.lin_grupa);
        this.linOcenki=(LinearLayout)itemView.findViewById(R.id.lin_ocenki);
        this.linTema=(LinearLayout)itemView.findViewById(R.id.lin_tema);
        this.linOcenkiNew=(LinearLayout)itemView.findViewById(R.id.lin_ocenki_new);
        this.tvPredmetName=(TextView) itemView.findViewById(R.id.tv_predmet_name);



    }
}
}