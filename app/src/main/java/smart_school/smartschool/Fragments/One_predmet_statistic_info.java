package smart_school.smartschool.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import smart_school.smartschool.Activity.Container;
import smart_school.smartschool.Adapters.Adapter_one_predmet_statistic_info;
import smart_school.smartschool.Adapters.Adapter_statistic_list;
import smart_school.smartschool.Model.One_lesson_statistic_info.Ocenki;
import smart_school.smartschool.Model.One_lesson_statistic_info.StatisticOneLessonHead;
import smart_school.smartschool.Model.Statistic.StatisticHead;
import smart_school.smartschool.R;

import static android.content.Context.MODE_PRIVATE;

public class One_predmet_statistic_info extends Fragment {
    private View view;

    @BindView(R.id.rec_rozklad)
    RecyclerView recRozklad;
    @BindView(R.id.fr_progres)
    FrameLayout frProgress;
    @BindView(R.id.textView16)
    TextView tvNamePredmet;
    @BindView(R.id.fr_back)
    FrameLayout frBack;
    @BindView(R.id.fr_previos)
    FrameLayout frPrevios;
    @BindView(R.id.fr_next)
    FrameLayout frNext;
    @BindView(R.id.tv_page)TextView tvPage;


     /*
    @BindView(R.id.fr_previos)
    FrameLayout frPrevios;


    @BindView(R.id.fr_today)
    FrameLayout frToday;
    @BindView(R.id.fr_next)
    FrameLayout frNext;
    @BindView(R.id.fr_back)
    FrameLayout frBack;
    @BindView(R.id.tv_date_week)
    TextView tvDateVeek;
    @BindView(R.id.nested_scroll)
    NestedScrollView nestedScrollView;
    @BindView(R.id.textView17)
    TextView tv17;
    */

    private Adapter_one_predmet_statistic_info adapter;
    private List<Ocenki> listOcenki = new ArrayList<>();
    private String id;
    private String dateFrom = "";
    private String dateTo = "";
    private String predmetId;
    private String Klass_Pr_Obuch_LIST_ID;
    private String predmet_name;
    private int pageNumber = 1;
    private int pageTotal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.one_predmet_info_statistic, container, false);
        }
        ButterKnife.bind(this, view);
        try {
            id = getArguments().getString("id");
            dateFrom = ReverseDate(getArguments().getString("dateFrom"));
            dateTo = ReverseDate(getArguments().getString("dateTo"));
            predmetId = getArguments().getString("predmetId");
            Klass_Pr_Obuch_LIST_ID = getArguments().getString("Klass_Pr_Obuch_LIST_ID");
            predmet_name = getArguments().getString("predmet_name");

        } catch (Exception e) {
            Log.i("LOG", String.valueOf(e));
        }

        recRozklad.setLayoutManager(new LinearLayoutManager(getActivity()));
        recRozklad.setNestedScrollingEnabled(false);
        adapter = new Adapter_one_predmet_statistic_info(getActivity(), listOcenki);
        recRozklad.setAdapter(adapter);

        String name = tvNamePredmet.getText().toString();
        tvNamePredmet.setText(name + " " + predmet_name);

        frBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        frPrevios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pageNumber > 1) {
                    pageNumber -= 1;
                    GetStatisticInfo();
                }


            }
        });

        frNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pageNumber < pageTotal) {
                    pageNumber += 1;
                    GetStatisticInfo();
                }
            }
        });

        GetStatisticInfo();
        ((Container)getActivity()).GetNotReadMess();


        return view;
    }


    private void GetStatisticInfo() {
        frProgress.setVisibility(View.VISIBLE);
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");
        try {
            AndroidNetworking.get(" {link}index.php?r=rest/get-stat-predmet&token={token}&pupil_id={id}&date_from={date_from}&date_to={date_to}&PREDMET_ID={predmet_id}&predmetId={Klass_Pr_Obuch_LIST_ID}&page={page}&pagesize=200")
                    .addPathParameter("link", link)
                    .addPathParameter("token", token)
                    .addPathParameter("date_from", dateFrom)
                    .addPathParameter("date_to", dateTo)
                    .addPathParameter("id", id)
                    .addPathParameter("predmet_id", predmetId)
                    .addPathParameter("page", String.valueOf(pageNumber))
                    .addPathParameter("Klass_Pr_Obuch_LIST_ID", Klass_Pr_Obuch_LIST_ID)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsObjectList(StatisticOneLessonHead.class, new ParsedRequestListener<List<StatisticOneLessonHead>>() {
                        @Override
                        public void onResponse(List<StatisticOneLessonHead> response) {
                            List<Ocenki> listOcenki = response.get(0).getOcenki();
                            adapter = new Adapter_one_predmet_statistic_info(getActivity(), listOcenki);
                            recRozklad.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                            pageNumber=response.get(0).getPageNumber();
                            pageTotal=response.get(0).getPageCountTotal();
                            tvPage.setText(pageNumber+"/"+pageTotal);

                            frProgress.setVisibility(View.GONE);


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

}
