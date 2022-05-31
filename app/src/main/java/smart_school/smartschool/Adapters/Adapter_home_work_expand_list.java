package smart_school.smartschool.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import smart_school.smartschool.Fragments.Home_work_fragment;
import smart_school.smartschool.Fragments.One_lesson_info;
import smart_school.smartschool.Model.Child;
import smart_school.smartschool.Model.Class_check_item;
import smart_school.smartschool.Model.Group;
import smart_school.smartschool.Model.Home_work_group.Child_home_work;
import smart_school.smartschool.Model.Home_work_group.Group_home_work;
import smart_school.smartschool.Model.Rozpisanie.New.LESSON;
import smart_school.smartschool.R;

public class Adapter_home_work_expand_list extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Group_home_work> groups;
    private int lastExpandedGroupPosition;
    private List<LESSON> listLess=null;
    private Home_work_fragment fragment;

    public Adapter_home_work_expand_list(Context context, ArrayList<Group_home_work> groups,Home_work_fragment fragment) {
        this.context = context;
        this.groups = groups;
        this.fragment = fragment;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Child_home_work> chList = groups.get(groupPosition)
                .getItems();
        return chList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Child_home_work child = (Child_home_work) getChild(groupPosition,
                childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_expandlist_home_work, null);

        }

        TextView tvNumber=(TextView)convertView.findViewById(R.id.tv_number);
        TextView tvName=(TextView)convertView.findViewById(R.id.tv_name);
        TextView tvDz=(TextView)convertView.findViewById(R.id.tv_dz);
        LinearLayout linParent=(LinearLayout)convertView.findViewById(R.id.lin_parent);

        tvNumber.setText(child.getName());
        tvName.setText(child.getListLesson().get(childPosition).getPREDMETNAME());
        String homeWork= String.valueOf(Html.fromHtml(child.getListLesson().get(childPosition).getHomeWork()));
       //tvDz.setText(homeWork);
        tvDz.setText(Html.fromHtml(child.getListLesson().get(childPosition).getHomeWork()));
        tvDz.setMovementMethod(LinkMovementMethod.getInstance());
        Log.i("tv_dz_vlue",child.getListLesson().get(childPosition).getHomeWork());

        if (homeWork.equalsIgnoreCase("")) {
            Typeface _font = Typeface.createFromAsset(context.getAssets(), "firasansregular.ttf");
            tvName.setTypeface(_font);
            tvNumber.setTypeface(_font);
            tvNumber.setTextColor(Color.parseColor("#1F2C33"));
           // tvDz.setVisibility(View.GONE);
        }


        listLess=child.getListLesson();
        if (listLess!=null) {
            //adapterForLessonListInItemList = new Adapter_for_lesson_list_in_item_list(context, listLess);
            //recList.setAdapter(adapterForLessonListInItemList);
            //adapterForLessonListInItemList.notifyDataSetChanged();
        }


        linParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Bundle bundle=new Bundle();
                int ooo=((ContentItem) list.get(position)).getLESSONID();
                bundle.putString("id",String.valueOf(((ContentItem) child.get(position)).getLESSONID()));
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                One_lesson_info myFragment = new One_lesson_info();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("lesson").commit();

                 */

                Bundle bundle=new Bundle();
                bundle.putString("id",String.valueOf(listLess.get(childPosition).getLESSONID()));
                bundle.putString("ava",String.valueOf(listLess.get(childPosition).getTeacher_avatar_image()));
                // bundle.putString("id_teacher",String.valueOf(listLess.get(childPosition).get()));
                //   bundle.putString("teacher_id",String.valueOf(listLess.get(childPosition).get()));
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                One_lesson_info myFragment = new One_lesson_info();
                myFragment.setArguments(bundle);

              //  activity.getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.container, myFragment).addToBackStack("lessonDyari").commit();
                activity.getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.container, myFragment).addToBackStack("lessonDyari").commit();

            }
        });



        return convertView;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Child_home_work> chList = groups.get(groupPosition)
                .getItems();

        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Group_home_work group = (Group_home_work) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.header, null);
        }

        //  TextView tvGroupName=(TextView) convertView.findViewById(R.id.tv_group_name);
        TextView txtTitle = (TextView)convertView.findViewById(R.id.txtHeader);
        TextView tvDate = (TextView)convertView.findViewById(R.id.tv_date);
        TextView tvCntLess = (TextView)convertView.findViewById(R.id.tv_ctn_less);
        ImageView imgOpenClose=(ImageView)convertView.findViewById(R.id.img_open_close);
        LinearLayout lin_head_item=(LinearLayout)convertView.findViewById(R.id.lin_head_item);

        txtTitle.setText(group.getName());
        tvDate.setText(group.getDate());
        tvCntLess.setText(group.getCountLesonGrades());

        /*
        tv.setText(group.getName());
        */
        if (isExpanded) {
            imgOpenClose.setImageResource(R.drawable.ic_minus);
            lin_head_item.setBackgroundColor(Color.parseColor("#148ecc"));
            tvDate.setTextColor(Color.WHITE);
            txtTitle.setTextColor(Color.WHITE);

        } else {
            imgOpenClose.setImageResource(R.drawable.ic_plus);
            lin_head_item.setBackgroundColor(Color.WHITE);
            tvDate.setTextColor(Color.parseColor("#148ecc"));
            txtTitle.setTextColor(Color.BLACK);

        }

        lastExpandedGroupPosition = groupPosition;

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
