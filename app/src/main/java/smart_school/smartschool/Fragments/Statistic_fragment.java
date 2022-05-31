package smart_school.smartschool.Fragments;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;


import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import smart_school.smartschool.Activity.Container;
import smart_school.smartschool.Adapters.Adapter_home_work;
import smart_school.smartschool.Adapters.Adapter_statistic_list;
import smart_school.smartschool.Adapters.ContentItem;
import smart_school.smartschool.Adapters.Header;
import smart_school.smartschool.Adapters.ListItem;
import smart_school.smartschool.Model.Rozpisanie.Lesson;
import smart_school.smartschool.Model.Rozpisanie.New.LESSON;
import smart_school.smartschool.Model.Rozpisanie.New.LessonDay;
import smart_school.smartschool.Model.Rozpisanie.New.Rozpisanie_model_new;
import smart_school.smartschool.Model.Statistic.Predmet;
import smart_school.smartschool.Model.Statistic.StatisticHead;
import smart_school.smartschool.R;

import static android.content.Context.MODE_PRIVATE;

public class Statistic_fragment extends Fragment {
    private View view;

    @BindView(R.id.rec_rozklad)
    RecyclerView recRozklad;
    @BindView(R.id.fr_progres)
    FrameLayout frProgress;
    @BindView(R.id.spin_sort)
    Spinner spinSort;
    @BindView(R.id.btn_sort)
    Button btnSort;
    @BindView(R.id.edt_date_from)
    EditText edtDateFrom;
    @BindView(R.id.edt_date_to)
    EditText edtDateTo;

    @BindView(R.id.fr_back)
    FrameLayout frBack;
    @BindView(R.id.tv_date_week)
    TextView tvDateVeek;
    @BindView(R.id.nested_scroll)
    NestedScrollView nestedScrollView;
    @BindView(R.id.img_settings)ImageView imgSettings;
    @BindView(R.id.img_cross)ImageView imgCross;
    @BindView(R.id.lin_settings)LinearLayout linSettings;
    @BindView(R.id.progressBar3)ProgressBar pb3;
    @BindView(R.id.progressBar4)ProgressBar pb4;
    @BindView(R.id.imageView20)ImageView imgArrow;

    @BindView(R.id.tv_all_srednij_bal)
    TextView tvAllSerednijBal;
    @BindView(R.id.tv_all_sered_vag_ocinka)
    TextView tvAllSerednyovagovaOcinka;
    @BindView(R.id.tv_all_kil_ocinok)
    TextView tvAllKilOcinok;
    @BindView(R.id.tv_all_kil_zalikiv)
    TextView tvAllKilZalikiv;
    @BindView(R.id.tv_ne_zalik)
    TextView tvAllKilNeZalikiv;
    @BindView(R.id.tv_all_kil_propuskiv)
    TextView tvAllKilPropuskiv;
    @BindView(R.id.tv_kil_zapizneny)
    TextView tvAllKilZapizneny;
    @BindView(R.id.textView18)TextView mark18;
    @BindView(R.id.textView23)TextView mark23;


    private String id;
    List<Predmet> listPredmets;
    Adapter_statistic_list adapter;
    private String dateFrom = "";
    private String dateTo = "";
    private List<String> listSort=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.statistic_fragment_layout, container, false);
        }
        ButterKnife.bind(this, view);
        try {
            id = getArguments().getString("id");
            // klass_id = getArguments().getString("klass_id");
        } catch (Exception e) {
            Log.i("LOG", String.valueOf(e));
        }
        listPredmets = new ArrayList<>();
        imgArrow.setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_IN);

        recRozklad.setLayoutManager(new LinearLayoutManager(getActivity()));
        recRozklad.setNestedScrollingEnabled(false);
        adapter = new Adapter_statistic_list(getActivity(), listPredmets, dateFrom, dateTo, id,"100");
        recRozklad.setAdapter(adapter);

        listSort.add(0,"PREDMET_ID");
        listSort.add(1,"-avgOcenka");
        listSort.add(2,"-avgWeightOcenka");
        listSort.add(3,"-cntOcenki");
        listSort.add(3,"-cntZachet");
        listSort.add(4,"-cntNoZachet");
        listSort.add(5,"-cntNotOnLesson");
        listSort.add(6,"-cntLateOnLesson");


        imgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand(linSettings);
                imgSettings.setVisibility(View.GONE);
                imgCross.setVisibility(View.VISIBLE);
                imgArrow.setVisibility(View.INVISIBLE);
                imgArrow.setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_IN);
                //nestedScrollView.smoothScrollBy(0,0);
                nestedScrollView.fullScroll(View.FOCUS_UP);

            }
        });

        imgCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgSettings.setVisibility(View.VISIBLE);
                imgCross.setVisibility(View.GONE);
                imgArrow.setVisibility(View.VISIBLE);
                imgArrow.setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_IN);
                collapse(linSettings);
            }
        });


        frBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgSettings.setVisibility(View.VISIBLE);
                imgCross.setVisibility(View.GONE);
                imgArrow.setVisibility(View.VISIBLE);
                imgArrow.setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_IN);
                collapse(linSettings);
                GetStatisticInfo();
            }
        });




        GetStatisticInfo();
        ((Container)getActivity()).GetNotReadMess();


        return view;
    }

    public static void expand(final View v) {
        v.measure(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? WindowManager.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(200);//(int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }


    private String ReverseDate(String date){
        String dateNew=date;
        if (dateNew.contains(".")){
           dateNew=dateNew.replace(".","-");
        }
        try{
            String[] arr=dateNew.split("-");
            dateNew=arr[2]+"-"+arr[1]+"-"+arr[0];
        }catch (Exception e){

        }

        return dateNew;
    }

    private void GetStatisticInfo() {
        frProgress.setVisibility(View.VISIBLE);
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");

       // String ddd=edtDateFrom.getText().toString();
        String ddd=ReverseDate(edtDateFrom.getText().toString());
      //  String ddd111=edtDateTo.getText().toString();
        String ddd111= ReverseDate(edtDateTo.getText().toString());


        try {
            AndroidNetworking.get(" {link}index.php?r=rest/get-stat-pupil-all&token={token}&pupil_id={id}&date_from={date_from}&date_to={date_to}&sort={sort}")
                    .addPathParameter("link", link)
                    .addPathParameter("token", token)
                    .addPathParameter("date_from", ddd)
                    .addPathParameter("date_to",ddd111)
                  //  .addPathParameter("sort", String.valueOf(spinSort.getSelectedItemPosition() + 1))
                    .addPathParameter("sort", listSort.get(spinSort.getSelectedItemPosition()))
                    // .addPathParameter("sort", "-avgOcenka")
                    .addPathParameter("id", id)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsObjectList(StatisticHead.class, new ParsedRequestListener<List<StatisticHead>>() {
                        @Override
                        public void onResponse(List<StatisticHead> response) {
                          //  listPredmets=new ArrayList<>();
                            String mark_system=response.get(0).getMark_system();
                            listPredmets = response.get(0).getPredmets();
                            edtDateFrom.setText(ReverseDate(response.get(0).getDate().getFrom()).replace("-","."));
                            edtDateTo.setText(ReverseDate(response.get(0).getDate().getTo()).replace("-","."));
                            String from=edtDateFrom.getText().toString();
                            String to=edtDateTo.getText().toString();
                            tvDateVeek.setText(from+" - "+to);
                            adapter = new Adapter_statistic_list(getActivity(), listPredmets, from, to, id,mark_system);
                            recRozklad.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            frProgress.setVisibility(View.GONE);
                            tvAllSerednijBal.setText(response.get(0).getAVGOcenkaAll());
                            tvAllSerednyovagovaOcinka.setText(response.get(0).getAVGWeightOcenkaAll());
                            tvAllKilOcinok.setText(response.get(0).getSUMCntOcenki());
                            tvAllKilZalikiv.setText(response.get(0).getSUMCntZachet());
                            tvAllKilNeZalikiv.setText(response.get(0).getSUMCntNoZachet());
                            tvAllKilPropuskiv.setText(response.get(0).getSUMCntNotOnLesson());
                            tvAllKilZapizneny.setText(response.get(0).getSUMCntLateOnLesson());
                            mark18.setText(" / "+mark_system);
                            mark23.setText(" / "+mark_system);

                            String prog1=response.get(0).getAVGOcenkaAll();
                            if (prog1.length()>2){
                                String[] fff=prog1.split("\\.");
                                if (fff.length>0)
                                    pb3.setProgress(Integer.valueOf(fff[0]));
                            }else {
                               pb3.setProgress(Integer.valueOf(prog1));
                            }

                            String prog=response.get(0).getAVGWeightOcenkaAll();
                            if (prog.length()>2){
                                String[] fff=prog.split("\\.");
                                if (fff.length>0)
                                    pb4.setProgress(Integer.valueOf(fff[0]));
                            }else {
                                pb4.setProgress(Integer.valueOf(prog));
                            }

                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.i("LOG", String.valueOf(anError));
                            frProgress.setVisibility(View.GONE);
                        }
                    });

        } catch (Exception e) {
            Log.i("TAG", String.valueOf(e));
        }


    }


}
