package smart_school.smartschool.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;



import de.hdodenhof.circleimageview.CircleImageView;
import smart_school.smartschool.Fragments.One_dialog_fragment;
import smart_school.smartschool.Model.All_dialogs.Dialog;
import smart_school.smartschool.Model.All_messages.Teacher;
import smart_school.smartschool.R;

public class Adapter_all_dialog_listview extends BaseAdapter {

    private List<Dialog> list = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    private static int positionItem = -1;
    private static int countClick = 0;

    public Adapter_all_dialog_listview(List<Dialog> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getViewTypeCount() {
        // menu type count
        return 2;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_list_all_messages, parent, false);
        }

        CircleImageView avatar;
        TextView tvMess;
        TextView tvClass;
        TextView tvPredmet;
        TextView tvDate;
        TextView tvCountMess;
        LinearLayout linItem;
        TextView tvOpenDialog = (TextView) view.findViewById(R.id.tv_open_dialog);
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        tvClass = (TextView) view.findViewById(R.id.tv_class);
        tvPredmet = (TextView) view.findViewById(R.id.tv_predmet);


        tvMess = (TextView) view.findViewById(R.id.tv_mess);
        tvDate = (TextView) view.findViewById(R.id.tv_date);
        tvCountMess = (TextView) view.findViewById(R.id.tv_count_mess);
        linItem = (LinearLayout) view.findViewById(R.id.lin_item);
        avatar = (CircleImageView) view.findViewById(R.id.img_ava);


        tvOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("human_id", String.valueOf(list.get(position).getInterlocutorId()));
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                One_dialog_fragment myFragment = new One_dialog_fragment();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("lesson").commit();


            }
        });


        Glide.with(mContext).load(list.get(position).getInterlocutorAvatar()).into(avatar);
        tvName.setText(list.get(position).getInterlocutorName());
        tvMess.setText(list.get(position).getPreviewText());
        String[] dateAndTime = list.get(position).getDateTime().split(" ");
        String[] date = dateAndTime[0].split("-");
        tvDate.setText(date[2] + "." + date[1]);
        tvPredmet.setText(list.get(position).getRole());

        if (list.get(position).getNotRead().equalsIgnoreCase("0")) {
            // tvCountMess.setText("Не прочитаних повідомлень : "+item.get(position).getNotRead());
            tvCountMess.setVisibility(View.GONE);
        } else {
            tvCountMess.setText(list.get(position).getNotRead());
        }


        return view;
    }
}
