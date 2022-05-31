package smart_school.smartschool.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import smart_school.smartschool.Fragments.One_lesson_info;
import smart_school.smartschool.Fragments.Rozklad_fragment;
import smart_school.smartschool.Model.Child;
import smart_school.smartschool.Model.Class_check_item;
import smart_school.smartschool.Model.Group;
import smart_school.smartschool.Model.Rozpisanie.New.LESSON;
import smart_school.smartschool.Model.Rozpisanie.New.OCENKI;
import smart_school.smartschool.R;

public class ExpandListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Group> groups;
    private int countClick = 0;
    private int lastExpandedGroupPosition;
    private List<Class_check_item> listCheck = new ArrayList<>();
    private Adapter_for_lesson_list_in_item_list adapterForLessonListInItemList;
    private List<LESSON> listLess = null;
    private Rozklad_fragment fragment;

    public ExpandListAdapter(Context context, ArrayList<Group> groups, Rozklad_fragment fragment) {
        this.fragment = fragment;
        this.context = context;
        this.groups = groups;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Child> chList = groups.get(groupPosition)
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

        final Child child = (Child) getChild(groupPosition,
                childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_list_rozklad, null);

        }

        listLess = child.getListLesson();

        TextView tvDateName = (TextView) convertView.findViewById(R.id.tv_date_name);
        TextView tvNameSTR = (TextView) convertView.findViewById(R.id.tv_num_str);
        TextView tvNamePredmet = (TextView) convertView.findViewById(R.id.tv_name_predmet);
        TextView tvNameGroup = (TextView) convertView.findViewById(R.id.tv_name_group);
        TextView tvLessonThema = (TextView) convertView.findViewById(R.id.tv_lesson_thema);
        TextView tvOcenci = (TextView) convertView.findViewById(R.id.tv_ocenki);
        TextView tvZamechanie = (TextView) convertView.findViewById(R.id.tv_zamechanie);
        LinearLayout linItem = (LinearLayout) convertView.findViewById(R.id.lin_item);
        LinearLayout linGrupa = (LinearLayout) convertView.findViewById(R.id.lin_grupa);
        LinearLayout linOcenki = (LinearLayout) convertView.findViewById(R.id.lin_ocenki);
        LinearLayout linTema = (LinearLayout) convertView.findViewById(R.id.lin_tema);
        // LinearLayout libParent=(LinearLayout)convertView.findViewById(R.id.lin_parent);
        LinearLayout linOcenkiNew = (LinearLayout) convertView.findViewById(R.id.lin_ocenki_new);
        TextView tvPredmetName = (TextView) convertView.findViewById(R.id.tv_predmet_name);

        LinearLayout linParent = (LinearLayout) convertView.findViewById(R.id.lin_parent);
        TextView tvPosNumber = (TextView) convertView.findViewById(R.id.tv_pos_number);


        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        LinearLayout linHeadMess = (LinearLayout) convertView.findViewById(R.id.lin_head_mess);

        tvPosNumber.setText(String.valueOf(child.getListLesson().get(childPosition).getLESSONNUM()));
        tvName.setText(child.getListLesson().get(childPosition).getPREDMETNAME());
        if (child.getListLesson().get(childPosition).getNAMEGRUP() != null) {
            tvName.setText(child.getListLesson().get(childPosition).getPREDMETNAME() + " (" + child.getListLesson().get(childPosition).getNAMEGRUP() + ")");
        }


        linParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(listLess.get(childPosition).getLESSONID()));
                bundle.putString("ava", String.valueOf(listLess.get(childPosition).getTeacher_avatar_image()));
                // bundle.putString("id_teacher",String.valueOf(listLess.get(childPosition).get()));
                //   bundle.putString("teacher_id",String.valueOf(listLess.get(childPosition).get()));
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                One_lesson_info myFragment = new One_lesson_info();
                myFragment.setArguments(bundle);

                activity.getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.container, myFragment).addToBackStack("lessonDyari").commit();

            }
        });

        LinearLayout linHeadPrusutnisty = (LinearLayout) convertView.findViewById(R.id.lin_head_prusutnisty);
        ImageView imgHeadPrusutnisty = (ImageView) convertView.findViewById(R.id.img_head_prusutnisty);
        TextView tvMessHead = (TextView) convertView.findViewById(R.id.tv_mess_head);
        TextView tvHeadOcinka = (TextView) convertView.findViewById(R.id.tv_head_ocinka);
        TextView tvOcinkaName = (TextView) convertView.findViewById(R.id.textView37);
        ImageView imgHeadAva = (ImageView) convertView.findViewById(R.id.img_head_ava);


        LinearLayout linOcenkaOne = (LinearLayout) convertView.findViewById(R.id.lin_ocenka_one);
        LinearLayout linMessOne = (LinearLayout) convertView.findViewById(R.id.lin_mess_one);
        TextView tvNameOne = (TextView) convertView.findViewById(R.id.tv_name_one);
        ImageView imgPrisutnistyOne = (ImageView) convertView.findViewById(R.id.img_prustnisty_one);
        TextView tvOcenkaOne = (TextView) convertView.findViewById(R.id.tv_ocenka_one);
        ProgressBar pbOne = (ProgressBar) convertView.findViewById(R.id.progressBar2_one);
        ImageView imgAvaOne = (ImageView) convertView.findViewById(R.id.ava_one);
        TextView messOne = (TextView) convertView.findViewById(R.id.mess_one);


        LinearLayout linOcenkaTwo = (LinearLayout) convertView.findViewById(R.id.lin_ocenka_two);
        LinearLayout linMessTwo = (LinearLayout) convertView.findViewById(R.id.lin_mess_two);
        TextView tvNameTwo = (TextView) convertView.findViewById(R.id.tv_name_two);
        ImageView imgPrisutnistyTwo = (ImageView) convertView.findViewById(R.id.img_prustnisty_two);
        TextView tvOcenkaTwo = (TextView) convertView.findViewById(R.id.tv_ocenka_two);
        ProgressBar pbTwo = (ProgressBar) convertView.findViewById(R.id.progressBar2_two);
        ImageView imgAvaTwo = (ImageView) convertView.findViewById(R.id.ava_two);
        TextView messTwo = (TextView) convertView.findViewById(R.id.mess_two);

        LinearLayout linOcenkaThree = (LinearLayout) convertView.findViewById(R.id.lin_ocenka_three);
        LinearLayout linMessThree = (LinearLayout) convertView.findViewById(R.id.lin_mess_three);
        TextView tvNameThree = (TextView) convertView.findViewById(R.id.tv_name_three);
        ImageView imgPrisutnistyThree = (ImageView) convertView.findViewById(R.id.img_prustnisty_three);
        TextView tvOcenkaThree = (TextView) convertView.findViewById(R.id.tv_ocenka_three);
        ProgressBar pbThree = (ProgressBar) convertView.findViewById(R.id.progressBar2_three);
        ImageView imgAvaThree = (ImageView) convertView.findViewById(R.id.ava_three);
        TextView messThree = (TextView) convertView.findViewById(R.id.mess_three);

        LinearLayout linOcenkaFour = (LinearLayout) convertView.findViewById(R.id.lin_ocenka_four);
        LinearLayout linMessFour = (LinearLayout) convertView.findViewById(R.id.lin_mess_four);
        TextView tvNameFour = (TextView) convertView.findViewById(R.id.tv_name_four);
        ImageView imgPrisutnistyFour = (ImageView) convertView.findViewById(R.id.img_prustnisty_four);
        TextView tvOcenkaFour = (TextView) convertView.findViewById(R.id.tv_ocenka_four);
        ProgressBar pbFour = (ProgressBar) convertView.findViewById(R.id.progressBar2_four);
        ImageView imgAvaFour = (ImageView) convertView.findViewById(R.id.ava_four);
        TextView messFour = (TextView) convertView.findViewById(R.id.mess_four);

        LinearLayout linOcenkaFive = (LinearLayout) convertView.findViewById(R.id.lin_ocenka_five);
        LinearLayout linMessFive = (LinearLayout) convertView.findViewById(R.id.lin_mess_five);
        TextView tvNameFive = (TextView) convertView.findViewById(R.id.tv_name_five);
        ImageView imgPrisutnistyFive = (ImageView) convertView.findViewById(R.id.img_prustnisty_five);
        TextView tvOcenkaFive = (TextView) convertView.findViewById(R.id.tv_ocenka_five);
        ProgressBar pbFive = (ProgressBar) convertView.findViewById(R.id.progressBar2_five);
        ImageView imgAvaFive = (ImageView) convertView.findViewById(R.id.ava_five);
        TextView messFive = (TextView) convertView.findViewById(R.id.mess_five);

        Glide.with(convertView).load(listLess.get(childPosition).getTeacher_avatar_image()).into(imgHeadAva);
        Glide.with(convertView).load(listLess.get(childPosition).getTeacher_avatar_image()).into(imgAvaFive);
        Glide.with(convertView).load(listLess.get(childPosition).getTeacher_avatar_image()).into(imgAvaFour);
        Glide.with(convertView).load(listLess.get(childPosition).getTeacher_avatar_image()).into(imgAvaThree);
        Glide.with(convertView).load(listLess.get(childPosition).getTeacher_avatar_image()).into(imgAvaTwo);
        Glide.with(convertView).load(listLess.get(childPosition).getTeacher_avatar_image()).into(imgAvaOne);


        if (listLess.get(childPosition).getOCENKI() != null) {
            int size = listLess.get(childPosition).getOCENKI().size();
            if (size > 5) {
                size = 5;
            }

            linHeadMess.setVisibility(View.GONE);
            linOcenkaOne.setVisibility(View.GONE);
            linOcenkaTwo.setVisibility(View.GONE);
            linOcenkaThree.setVisibility(View.GONE);
            linOcenkaFour.setVisibility(View.GONE);
            linOcenkaFive.setVisibility(View.GONE);

            switch (size) {
                case 0: {
                    linHeadMess.setVisibility(View.GONE);
                    linOcenkaOne.setVisibility(View.GONE);
                    linOcenkaTwo.setVisibility(View.GONE);
                    linOcenkaThree.setVisibility(View.GONE);
                    linOcenkaFour.setVisibility(View.GONE);
                    linOcenkaFive.setVisibility(View.GONE);


                }
                case 1: {
                    linHeadPrusutnisty.setVisibility(View.VISIBLE);

                    if (listLess.get(childPosition).getOCENKI().get(0).getOcenka().equals("")) {
                        tvHeadOcinka.setVisibility(View.INVISIBLE);
                        tvOcinkaName.setVisibility(View.INVISIBLE);
                    } else {
                        tvHeadOcinka.setVisibility(View.VISIBLE);
                        tvHeadOcinka.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(0).getOcenka()));
                        tvOcinkaName.setVisibility(View.VISIBLE);
                    }

                    switch (listLess.get(childPosition).getOCENKI().get(0).getNOTONLESSON()) {
                        case 0: {
                            imgHeadPrusutnisty.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {
                            imgHeadPrusutnisty.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgHeadPrusutnisty.setImageResource(R.drawable.late_img);
                            break;
                        }
                    }

                    if (listLess.get(childPosition).getOCENKI().get(0).getZAMECHANIE().equalsIgnoreCase("")) {
                        linHeadMess.setVisibility(View.GONE);
                    } else {
                        linHeadMess.setVisibility(View.VISIBLE);
                        tvMessHead.setText(listLess.get(childPosition).getOCENKI().get(0).getZAMECHANIE());
                    }
                    break;
                }
                case 2: {

                    linHeadPrusutnisty.setVisibility(View.VISIBLE);

                    if (listLess.get(childPosition).getOCENKI().get(0).getOcenka().equals("")) {
                        tvHeadOcinka.setVisibility(View.INVISIBLE);
                        tvOcinkaName.setVisibility(View.INVISIBLE);
                    } else {
                        tvHeadOcinka.setVisibility(View.VISIBLE);
                        tvHeadOcinka.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(0).getOcenka()));
                        tvOcinkaName.setVisibility(View.VISIBLE);
                    }

                    switch (listLess.get(childPosition).getOCENKI().get(0).getNOTONLESSON()) {
                        case 0: {
                            imgHeadPrusutnisty.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {


                            imgHeadPrusutnisty.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgHeadPrusutnisty.setImageResource(R.drawable.late_img);
                            break;
                        }
                    }

                    if (listLess.get(childPosition).getOCENKI().get(0).getZAMECHANIE().equalsIgnoreCase("")) {
                        linHeadMess.setVisibility(View.GONE);
                    } else {
                        linHeadMess.setVisibility(View.VISIBLE);
                        tvMessHead.setText(listLess.get(childPosition).getOCENKI().get(0).getZAMECHANIE());
                    }

                    linOcenkaOne.setVisibility(View.VISIBLE);
                    tvNameOne.setText(listLess.get(childPosition).getOCENKI().get(1).getOcenkaName());
                    if (listLess.get(childPosition).getOCENKI().get(1).getZAMECHANIE().equalsIgnoreCase("")) {
                        linMessOne.setVisibility(View.GONE);
                    } else {
                        linMessOne.setVisibility(View.VISIBLE);
                        messOne.setText(listLess.get(childPosition).getOCENKI().get(1).getZAMECHANIE());

                    }

                    if (listLess.get(childPosition).getOCENKI().get(1).getOcenka_progressbar() == null) {
                        pbOne.setVisibility(View.GONE);
                        tvOcenkaOne.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(1).getOcenka()));
                    } else {
                        tvOcenkaOne.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(1).getOcenka()));

                        pbOne.setVisibility(View.VISIBLE);
                        pbOne.setProgress(Integer.parseInt(listLess.get(childPosition).getOCENKI().get(1).getOcenka_progressbar()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            int colorCodeDark = Color.parseColor(listLess.get(childPosition).getOCENKI().get(1).getOcenka_bal_color());
                            pbOne.getProgressDrawable().setColorFilter(colorCodeDark, PorterDuff.Mode.SRC_IN);
                        }
                    }


                    switch (listLess.get(childPosition).getOCENKI().get(1).getNOTONLESSON()) {
                        case 0: {
                            imgPrisutnistyOne.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {

                            imgPrisutnistyOne.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgPrisutnistyOne.setImageResource(R.drawable.late_img);

                            break;
                        }


                    }

                    break;
                }
                case 3: {


                    /** Head item*/
                    linHeadPrusutnisty.setVisibility(View.VISIBLE);

                    if (listLess.get(childPosition).getOCENKI().get(0).getOcenka().equals("")) {
                        tvHeadOcinka.setVisibility(View.INVISIBLE);
                        tvOcinkaName.setVisibility(View.INVISIBLE);
                    } else {
                        tvHeadOcinka.setVisibility(View.VISIBLE);
                        tvHeadOcinka.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(0).getOcenka()));
                        tvOcinkaName.setVisibility(View.VISIBLE);
                    }

                    switch (listLess.get(childPosition).getOCENKI().get(0).getNOTONLESSON()) {
                        case 0: {
                            imgHeadPrusutnisty.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {


                            imgHeadPrusutnisty.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgHeadPrusutnisty.setImageResource(R.drawable.late_img);
                            break;
                        }
                    }

                    if (listLess.get(childPosition).getOCENKI().get(0).getZAMECHANIE().equalsIgnoreCase("")) {
                        linHeadMess.setVisibility(View.GONE);
                    } else {
                        linHeadMess.setVisibility(View.VISIBLE);
                        tvMessHead.setText(listLess.get(childPosition).getOCENKI().get(0).getZAMECHANIE());
                    }

                    /** ***************************************************************************************/

                    /** First item*/
                    linOcenkaOne.setVisibility(View.VISIBLE);
                    tvNameOne.setText(listLess.get(childPosition).getOCENKI().get(1).getOcenkaName());
                    if (listLess.get(childPosition).getOCENKI().get(1).getZAMECHANIE().equalsIgnoreCase("")) {
                        linMessOne.setVisibility(View.GONE);
                    } else {
                        linMessOne.setVisibility(View.VISIBLE);
                        messOne.setText(listLess.get(childPosition).getOCENKI().get(1).getZAMECHANIE());

                    }

                    if (listLess.get(childPosition).getOCENKI().get(1).getOcenka_progressbar() == null) {
                        pbOne.setVisibility(View.GONE);
                        tvOcenkaOne.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(1).getOcenka()));
                    } else {
                        tvOcenkaOne.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(1).getOcenka()));

                        pbOne.setVisibility(View.VISIBLE);
                        pbOne.setProgress(Integer.parseInt(listLess.get(childPosition).getOCENKI().get(1).getOcenka_progressbar()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            int colorCodeDark = Color.parseColor(listLess.get(childPosition).getOCENKI().get(1).getOcenka_bal_color());
                            pbOne.getProgressDrawable().setColorFilter(colorCodeDark, PorterDuff.Mode.SRC_IN);
                        }
                    }

                    switch (listLess.get(childPosition).getOCENKI().get(1).getNOTONLESSON()) {
                        case 0: {
                            imgPrisutnistyOne.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {


                            imgPrisutnistyOne.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgPrisutnistyOne.setImageResource(R.drawable.late_img);
                            break;
                        }
                    }
                    /** *********************************************************************************************/
                    /** Second item*/
                    linOcenkaTwo.setVisibility(View.VISIBLE);
                    tvNameTwo.setText(listLess.get(childPosition).getOCENKI().get(2).getOcenkaName());
                    if (listLess.get(childPosition).getOCENKI().get(2).getZAMECHANIE().equalsIgnoreCase("")) {
                        linMessTwo.setVisibility(View.GONE);
                    } else {
                        linMessTwo.setVisibility(View.VISIBLE);
                        messTwo.setText(listLess.get(childPosition).getOCENKI().get(2).getZAMECHANIE());

                    }

                    if (listLess.get(childPosition).getOCENKI().get(2).getOcenka_progressbar() == null) {
                        pbTwo.setVisibility(View.GONE);
                        tvOcenkaTwo.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(2).getOcenka()));
                    } else {
                        tvOcenkaTwo.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(2).getOcenka()));
                        pbTwo.setVisibility(View.VISIBLE);
                        pbTwo.setProgress(Integer.parseInt(listLess.get(childPosition).getOCENKI().get(2).getOcenka_progressbar()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            int colorCodeDark = Color.parseColor(listLess.get(childPosition).getOCENKI().get(2).getOcenka_bal_color());
                            pbTwo.getProgressDrawable().setColorFilter(colorCodeDark, PorterDuff.Mode.SRC_IN);
                        }
                    }

                    switch (listLess.get(childPosition).getOCENKI().get(2).getNOTONLESSON()) {
                        case 0: {
                            imgPrisutnistyTwo.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {


                            imgPrisutnistyTwo.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgPrisutnistyTwo.setImageResource(R.drawable.late_img);
                            break;
                        }
                    }
                    /** *********************************************************************************************/

                    break;
                }
                case 4: {


                    /** Head item*/
                    linHeadPrusutnisty.setVisibility(View.VISIBLE);
                    if (listLess.get(childPosition).getOCENKI().get(0).getOcenka().equals("")) {
                        tvHeadOcinka.setVisibility(View.INVISIBLE);
                        tvOcinkaName.setVisibility(View.INVISIBLE);
                    } else {
                        tvHeadOcinka.setVisibility(View.VISIBLE);
                        tvHeadOcinka.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(0).getOcenka()));
                        tvOcinkaName.setVisibility(View.VISIBLE);
                    }

                    switch (listLess.get(childPosition).getOCENKI().get(0).getNOTONLESSON()) {
                        case 0: {
                            imgHeadPrusutnisty.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {


                            imgHeadPrusutnisty.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgHeadPrusutnisty.setImageResource(R.drawable.late_img);
                            break;
                        }
                    }

                    if (listLess.get(childPosition).getOCENKI().get(0).getZAMECHANIE().equalsIgnoreCase("")) {
                        linHeadMess.setVisibility(View.GONE);
                    } else {
                        linHeadMess.setVisibility(View.VISIBLE);
                        tvMessHead.setText(listLess.get(childPosition).getOCENKI().get(0).getZAMECHANIE());
                    }

                    /** ***************************************************************************************/

                    /** First item*/
                    linOcenkaOne.setVisibility(View.VISIBLE);
                    tvNameOne.setText(listLess.get(childPosition).getOCENKI().get(1).getOcenkaName());
                    if (listLess.get(childPosition).getOCENKI().get(1).getZAMECHANIE().equalsIgnoreCase("")) {
                        linMessOne.setVisibility(View.GONE);
                    } else {
                        linMessOne.setVisibility(View.VISIBLE);
                        messOne.setText(listLess.get(childPosition).getOCENKI().get(1).getZAMECHANIE());

                    }

                    if (listLess.get(childPosition).getOCENKI().get(1).getOcenka_progressbar() == null) {
                        pbOne.setVisibility(View.GONE);
                        tvOcenkaOne.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(1).getOcenka()));
                    } else {
                        tvOcenkaOne.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(1).getOcenka()));

                        pbOne.setVisibility(View.VISIBLE);
                        pbOne.setProgress(Integer.parseInt(listLess.get(childPosition).getOCENKI().get(1).getOcenka_progressbar()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            int colorCodeDark = Color.parseColor(listLess.get(childPosition).getOCENKI().get(1).getOcenka_bal_color());
                            pbOne.getProgressDrawable().setColorFilter(colorCodeDark, PorterDuff.Mode.SRC_IN);
                        }
                    }

                    switch (listLess.get(childPosition).getOCENKI().get(1).getNOTONLESSON()) {
                        case 0: {
                            imgPrisutnistyOne.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {


                            imgPrisutnistyOne.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgPrisutnistyOne.setImageResource(R.drawable.late_img);
                            break;
                        }
                    }
                    /** *********************************************************************************************/
                    /** Second item*/
                    linOcenkaTwo.setVisibility(View.VISIBLE);
                    tvNameTwo.setText(listLess.get(childPosition).getOCENKI().get(2).getOcenkaName());
                    if (listLess.get(childPosition).getOCENKI().get(2).getZAMECHANIE().equalsIgnoreCase("")) {
                        linMessTwo.setVisibility(View.GONE);
                    } else {
                        linMessTwo.setVisibility(View.VISIBLE);
                        messTwo.setText(listLess.get(childPosition).getOCENKI().get(2).getZAMECHANIE());

                    }

                    if (listLess.get(childPosition).getOCENKI().get(2).getOcenka_progressbar() == null) {
                        pbTwo.setVisibility(View.GONE);
                        tvOcenkaTwo.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(2).getOcenka()));
                    } else {
                        tvOcenkaTwo.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(2).getOcenka()));

                        pbTwo.setVisibility(View.VISIBLE);
                        pbTwo.setProgress(Integer.parseInt(listLess.get(childPosition).getOCENKI().get(2).getOcenka_progressbar()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            int colorCodeDark = Color.parseColor(listLess.get(childPosition).getOCENKI().get(2).getOcenka_bal_color());
                            pbTwo.getProgressDrawable().setColorFilter(colorCodeDark, PorterDuff.Mode.SRC_IN);
                        }
                    }

                    switch (listLess.get(childPosition).getOCENKI().get(2).getNOTONLESSON()) {
                        case 0: {
                            imgPrisutnistyTwo.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {


                            imgPrisutnistyTwo.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgPrisutnistyTwo.setImageResource(R.drawable.late_img);
                            break;
                        }
                    }
                    /** *********************************************************************************************/

                    /** Three item*/
                    linOcenkaThree.setVisibility(View.VISIBLE);
                    tvNameThree.setText(listLess.get(childPosition).getOCENKI().get(3).getOcenkaName());
                    if (listLess.get(childPosition).getOCENKI().get(3).getZAMECHANIE().equalsIgnoreCase("")) {
                        linMessThree.setVisibility(View.GONE);
                    } else {
                        linMessThree.setVisibility(View.VISIBLE);
                        messThree.setText(listLess.get(childPosition).getOCENKI().get(3).getZAMECHANIE());

                    }

                    if (listLess.get(childPosition).getOCENKI().get(3).getOcenka_progressbar() == null) {
                        pbThree.setVisibility(View.GONE);
                        tvOcenkaThree.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(3).getOcenka()));
                    } else {
                        tvOcenkaThree.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(3).getOcenka()));

                        pbThree.setVisibility(View.VISIBLE);
                        pbThree.setProgress(Integer.parseInt(listLess.get(childPosition).getOCENKI().get(3).getOcenka_progressbar()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            int colorCodeDark = Color.parseColor(listLess.get(childPosition).getOCENKI().get(3).getOcenka_bal_color());
                            pbThree.getProgressDrawable().setColorFilter(colorCodeDark, PorterDuff.Mode.SRC_IN);
                        }
                    }

                    switch (listLess.get(childPosition).getOCENKI().get(3).getNOTONLESSON()) {
                        case 0: {
                            imgPrisutnistyTwo.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {


                            imgPrisutnistyTwo.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgPrisutnistyTwo.setImageResource(R.drawable.late_img);
                            break;
                        }
                    }
                    /** *********************************************************************************************/


                    break;
                }
                case 5: {


                    /** Head item*/
                    linHeadPrusutnisty.setVisibility(View.VISIBLE);
                    switch (listLess.get(childPosition).getOCENKI().get(0).getNOTONLESSON()) {
                        case 0: {
                            imgHeadPrusutnisty.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {


                            imgHeadPrusutnisty.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgHeadPrusutnisty.setImageResource(R.drawable.late_img);
                            break;
                        }
                    }

                    if (listLess.get(childPosition).getOCENKI().get(0).getZAMECHANIE().equalsIgnoreCase("")) {
                        linHeadMess.setVisibility(View.GONE);
                    } else {
                        linHeadMess.setVisibility(View.VISIBLE);
                        tvMessHead.setText(listLess.get(childPosition).getOCENKI().get(0).getZAMECHANIE());
                    }

                    /** ***************************************************************************************/

                    /** First item*/
                    linOcenkaOne.setVisibility(View.VISIBLE);
                    tvNameOne.setText(listLess.get(childPosition).getOCENKI().get(1).getOcenkaName());
                    if (listLess.get(childPosition).getOCENKI().get(1).getZAMECHANIE().equalsIgnoreCase("")) {
                        linMessOne.setVisibility(View.GONE);
                    } else {
                        linMessOne.setVisibility(View.VISIBLE);
                        messOne.setText(listLess.get(childPosition).getOCENKI().get(1).getZAMECHANIE());

                    }

                    if (listLess.get(childPosition).getOCENKI().get(1).getOcenka_progressbar() == null) {
                        pbOne.setVisibility(View.GONE);
                        tvOcenkaOne.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(1).getOcenka()));
                    } else {
                        tvOcenkaOne.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(1).getOcenka()));

                        pbOne.setVisibility(View.VISIBLE);
                        pbOne.setProgress(Integer.parseInt(listLess.get(childPosition).getOCENKI().get(1).getOcenka_progressbar()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            int colorCodeDark = Color.parseColor(listLess.get(childPosition).getOCENKI().get(1).getOcenka_bal_color());
                            pbOne.getProgressDrawable().setColorFilter(colorCodeDark, PorterDuff.Mode.SRC_IN);
                        }
                    }

                    switch (listLess.get(childPosition).getOCENKI().get(1).getNOTONLESSON()) {
                        case 0: {
                            imgPrisutnistyOne.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {

                            imgPrisutnistyOne.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgPrisutnistyOne.setImageResource(R.drawable.late_img);

                            break;
                        }
                    }
                    /** *********************************************************************************************/
                    /** Second item*/
                    linOcenkaTwo.setVisibility(View.VISIBLE);
                    tvNameTwo.setText(listLess.get(childPosition).getOCENKI().get(2).getOcenkaName());
                    if (listLess.get(childPosition).getOCENKI().get(2).getZAMECHANIE().equalsIgnoreCase("")) {
                        linMessTwo.setVisibility(View.GONE);
                    } else {
                        linMessTwo.setVisibility(View.VISIBLE);
                        messTwo.setText(listLess.get(childPosition).getOCENKI().get(2).getZAMECHANIE());

                    }

                    if (listLess.get(childPosition).getOCENKI().get(2).getOcenka_progressbar() == null) {
                        pbTwo.setVisibility(View.GONE);
                        tvOcenkaTwo.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(2).getOcenka()));
                    } else {
                        tvOcenkaTwo.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(2).getOcenka()));

                        pbTwo.setVisibility(View.VISIBLE);
                        pbTwo.setProgress(Integer.parseInt(listLess.get(childPosition).getOCENKI().get(2).getOcenka_progressbar()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            int colorCodeDark = Color.parseColor(listLess.get(childPosition).getOCENKI().get(2).getOcenka_bal_color());
                            pbTwo.getProgressDrawable().setColorFilter(colorCodeDark, PorterDuff.Mode.SRC_IN);
                        }
                    }

                    switch (listLess.get(childPosition).getOCENKI().get(2).getNOTONLESSON()) {
                        case 0: {
                            imgPrisutnistyTwo.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {


                            imgPrisutnistyTwo.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgPrisutnistyTwo.setImageResource(R.drawable.late_img);
                            break;
                        }
                    }
                    /** *********************************************************************************************/

                    /** Three item*/
                    linOcenkaThree.setVisibility(View.VISIBLE);
                    tvNameThree.setText(listLess.get(childPosition).getOCENKI().get(3).getOcenkaName());
                    if (listLess.get(childPosition).getOCENKI().get(3).getZAMECHANIE().equalsIgnoreCase("")) {
                        linMessThree.setVisibility(View.GONE);
                    } else {
                        linMessThree.setVisibility(View.VISIBLE);
                        messThree.setText(listLess.get(childPosition).getOCENKI().get(3).getZAMECHANIE());

                    }

                    if (listLess.get(childPosition).getOCENKI().get(3).getOcenka_progressbar() == null) {
                        pbThree.setVisibility(View.GONE);
                        tvOcenkaThree.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(3).getOcenka()));
                    } else {
                        tvOcenkaThree.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(3).getOcenka()));

                        pbThree.setVisibility(View.VISIBLE);

                        pbThree.setProgress(Integer.parseInt(listLess.get(childPosition).getOCENKI().get(3).getOcenka_progressbar()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            int colorCodeDark = Color.parseColor(listLess.get(childPosition).getOCENKI().get(3).getOcenka_bal_color());
                            pbThree.getProgressDrawable().setColorFilter(colorCodeDark, PorterDuff.Mode.SRC_IN);
                        }
                    }

                    switch (listLess.get(childPosition).getOCENKI().get(2).getNOTONLESSON()) {
                        case 0: {
                            imgPrisutnistyTwo.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {


                            imgPrisutnistyTwo.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgPrisutnistyTwo.setImageResource(R.drawable.late_img);
                            break;
                        }
                    }
                    /** *********************************************************************************************/

                    /** Four item*/
                    linOcenkaFour.setVisibility(View.VISIBLE);
                    tvNameFour.setText(listLess.get(childPosition).getOCENKI().get(4).getOcenkaName());
                    if (listLess.get(childPosition).getOCENKI().get(4).getZAMECHANIE().equalsIgnoreCase("")) {
                        linMessFour.setVisibility(View.GONE);
                    } else {
                        linMessFour.setVisibility(View.VISIBLE);
                        messFour.setText(listLess.get(childPosition).getOCENKI().get(4).getZAMECHANIE());

                    }

                    if (listLess.get(childPosition).getOCENKI().get(4).getOcenka_progressbar() == null) {
                        pbFour.setVisibility(View.GONE);
                        tvOcenkaFour.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(4).getOcenka()));
                    } else {
                        tvOcenkaFour.setText(Html.fromHtml(listLess.get(childPosition).getOCENKI().get(4).getOcenka()));

                        pbFour.setVisibility(View.VISIBLE);
                        pbFour.setProgress(Integer.parseInt(listLess.get(childPosition).getOCENKI().get(4).getOcenka_progressbar()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            int colorCodeDark = Color.parseColor(listLess.get(childPosition).getOCENKI().get(4).getOcenka_bal_color());
                            pbFour.getProgressDrawable().setColorFilter(colorCodeDark, PorterDuff.Mode.SRC_IN);
                        }
                    }

                    switch (listLess.get(childPosition).getOCENKI().get(4).getNOTONLESSON()) {
                        case 0: {
                            imgPrisutnistyTwo.setImageResource(R.drawable.tick_green);
                            break;
                        }
                        case 1: {


                            imgPrisutnistyTwo.setImageResource(R.drawable.ic_cross_red);
                            break;
                        }
                        case 2: {
                            imgPrisutnistyTwo.setImageResource(R.drawable.late_img);
                            break;
                        }
                    }
                    /** *********************************************************************************************/


                    break;
                }


            }


        } else {
            linHeadPrusutnisty.setVisibility(View.GONE);
            linHeadMess.setVisibility(View.GONE);
            linOcenkaOne.setVisibility(View.GONE);
            linOcenkaTwo.setVisibility(View.GONE);
            linOcenkaThree.setVisibility(View.GONE);
            linOcenkaFour.setVisibility(View.GONE);
            linOcenkaFive.setVisibility(View.GONE);

        }


        return convertView;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Child> chList = groups.get(groupPosition)
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
        Group group = (Group) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.header, null);
        }

        //  TextView tvGroupName=(TextView) convertView.findViewById(R.id.tv_group_name);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtHeader);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tv_date);
        TextView tvCtnLess = (TextView) convertView.findViewById(R.id.tv_ctn_less);
        ImageView imgOpenClose = (ImageView) convertView.findViewById(R.id.img_open_close);
        LinearLayout lin_head_item = (LinearLayout) convertView.findViewById(R.id.lin_head_item);

        txtTitle.setText(group.getName());
        tvDate.setText(group.getDate());
        tvCtnLess.setText(group.getCountLesonGrades());

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