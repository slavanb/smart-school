package smart_school.smartschool.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;


import android.text.format.Time;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.text.ParseException;
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
import smart_school.smartschool.Adapters.Adapter_list_rozklad;
import smart_school.smartschool.Adapters.ContentItem;
import smart_school.smartschool.Adapters.ExpandListAdapter;
import smart_school.smartschool.Adapters.Header;
import smart_school.smartschool.Adapters.ListItem;
import smart_school.smartschool.Adapters.Adapter_rozklad_new;
import smart_school.smartschool.Model.Child;
import smart_school.smartschool.Model.Class_check_item;
import smart_school.smartschool.Model.Group;
import smart_school.smartschool.Model.Rozpisanie.Lesson;
import smart_school.smartschool.Model.Rozpisanie.New.LESSON;
import smart_school.smartschool.Model.Rozpisanie.New.LessonDay;
import smart_school.smartschool.Model.Rozpisanie.New.OCENKI;
import smart_school.smartschool.Model.Rozpisanie.New.Rozpisanie_model_new;
import smart_school.smartschool.R;
import smart_school.smartschool.Support.OnSwipeTouchListener;

import static android.content.Context.MODE_PRIVATE;

public class Rozklad_fragment extends Fragment {
    private View view;
    @BindView(R.id.rec_rozklad)
    RecyclerView recRozklad;
    @BindView(R.id.fr_progres)
    FrameLayout frProgress;
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
    //@BindView(R.id.textView17)TextView tv17;
    @BindView(R.id.exp_list)ExpandableListView expList;
    @BindView(R.id.fr_line_early)FrameLayout frLineEarly;
    @BindView(R.id.fr_line_today)FrameLayout frLineToday;
    @BindView(R.id.fr_line_tomorow)FrameLayout frLineTomorow;
    @BindView(R.id.lin_kan)LinearLayout linKan;


    private String id;
    private String klass_id;
    private Adapter_list_rozklad adapterListRozklad;
    private Realm mRealm;
    private List<Lesson> listLesson = new ArrayList<>();
    private String globalDate;
    private String ocenciAll;
    private GestureDetector gestureDetector;
    private List<Class_check_item> listCheck = new ArrayList<>();
    ArrayList<Group> group_list=new ArrayList<>();
    ArrayList<Child> child_list=new ArrayList<>();
    Group gru1=new Group();
    ExpandListAdapter expandListAdapter;
    String staticDateToday;


    //List<List<ListItem>> arraylist;
    ArrayList<ListItem> arrayList = new ArrayList<>();
    Adapter_rozklad_new adapter;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.rozklad_fragment, container, false);
        }
        ButterKnife.bind(this, view);
        mRealm = Realm.getDefaultInstance();

        try {
            id = getArguments().getString("id");
            klass_id = getArguments().getString("klass_id");
        } catch (Exception e) {
            Log.i("LOG", String.valueOf(e));
        }

        recRozklad.setLayoutManager(new LinearLayoutManager(getActivity()));
        recRozklad.setNestedScrollingEnabled(false);
        adapter = new Adapter_rozklad_new(arrayList);
        recRozklad.setAdapter(adapter);

        expList.setOnTouchListener(new OnSwipeTouchListener() {

            public void onSwipeTop() {
               // Toast.makeText(getActivity(), "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
              //  Toast.makeText(getActivity(), "right", Toast.LENGTH_SHORT).show();


                /** Розкоментувати для Славіка */ /* GetLessonInfoPreviosOrNextExpand(GetDate(0));
                frLineEarly.setVisibility(View.VISIBLE);
                frLineTomorow.setVisibility(View.INVISIBLE);
                frLineToday.setVisibility(View.INVISIBLE);
                */
            }
            public void onSwipeLeft() {
              //  Toast.makeText(getActivity(), "left", Toast.LENGTH_SHORT).show();



                /** Розкоментувати для Славіка */    /*GetLessonInfoPreviosOrNextExpand(GetDate(1));
                frLineEarly.setVisibility(View.INVISIBLE);
                frLineTomorow.setVisibility(View.VISIBLE);
                frLineToday.setVisibility(View.INVISIBLE);\
                */
                }
            public void onSwipeBottom() {
               // Toast.makeText(getActivity(), "bottom", Toast.LENGTH_SHORT).show();
            }



        });

        expList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousItem )
                    expList.collapseGroup(previousItem );
                previousItem = groupPosition;
            }
        });

        frBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        frPrevios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetLessonInfoPreviosOrNextExpand(GetDate(0));
                frLineEarly.setVisibility(View.VISIBLE);
                frLineTomorow.setVisibility(View.INVISIBLE);
                frLineToday.setVisibility(View.INVISIBLE);
            }
        });

        frNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetLessonInfoPreviosOrNextExpand(GetDate(1));
                frLineEarly.setVisibility(View.INVISIBLE);
                frLineTomorow.setVisibility(View.VISIBLE);
                frLineToday.setVisibility(View.INVISIBLE);
            }
        });

        frToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  GetLessonInfo();
                GetLessonInfoExpandList();
            }
        });

      //  GetLessonInfo();
        GetLessonInfoExpandList();
        ((Container)getActivity()).GetNotReadMess();


        return view;
    }




    /*
    private void GetLessonInfoPreviosOrNext(String date) {

        frProgress.setVisibility(View.VISIBLE);
        frToday.setBackgroundColor(Color.WHITE);
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");
        try {
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    . writeTimeout(120, TimeUnit.SECONDS)
                    .build();
            AndroidNetworking.get(" {link}index.php?r=rest/get-week-lessons-new&token={token}&date={date}&pupil_id={id}&klass_id={klass_id}")
                    .addPathParameter("link", link)
                    .addPathParameter("token", token)
                    .addPathParameter("date", date)
                    .addPathParameter("klass_id", klass_id)
                    .addPathParameter("id", id)
                    .setPriority(Priority.HIGH)
                    .setOkHttpClient(okHttpClient)
                    .build()
                    .getAsObjectList(Rozpisanie_model_new.class, new ParsedRequestListener<List<Rozpisanie_model_new>>() {
                        @Override
                        public void onResponse(List<Rozpisanie_model_new> response) {
                            String monday = ReverseDate(response.get(0).getDate().getMonday());
                            String sunday = ReverseDate(response.get(0).getDate().getSunday());
                            String[] mon_arr=monday.split("-");
                            String[] sun_arr=sunday.split("-");
                            tvDateVeek.setText(mon_arr[0]+"."+mon_arr[1] + "-" + sun_arr[0]+"."+sun_arr[1]);
                            List<LessonDay> listLesson = response.get(0).getLessonDays();
                            arrayList = new ArrayList<>();
                            for (int j = 0; j < listLesson.size(); j++) {
                                Header header = new Header();
                                header.setHeader(listLesson.get(j).getDAYDATEL() + " " + listLesson.get(j).getDAYDATE());
                                arrayList.add(header);

                                List<LESSON> size=listLesson.get(j).getLESSONS();
                                if (size!=null) {
                                    for (int i = 0; i < listLesson.get(j).getLESSONS().size(); i++) {
                                        ContentItem item = new ContentItem();
                                        item.setPREDMETNAME(listLesson.get(j).getLESSONS().get(i).getPREDMETNAME());
                                        item.setLESSONDATEUA(listLesson.get(j).getLESSONS().get(i).getLESSONDATEUA());
                                        item.setLESSONDATEL(listLesson.get(j).getLESSONS().get(i).getLESSONDATEL());
                                        item.setLESSONNUMSTR(listLesson.get(j).getLESSONS().get(i).getLESSONNUMSTR());
                                        item.setLESSONID(listLesson.get(j).getLESSONS().get(i).getLESSONID());
                                        item.setNAMEGRUP(listLesson.get(j).getLESSONS().get(i).getNAMEGRUP());
                                        if (listLesson.get(j).getLESSONS().get(i).getLESSONTHEMA() == null) {
                                            item.setLESSONTHEMA("");
                                        } else {
                                            item.setLESSONTHEMA(listLesson.get(j).getLESSONS().get(i).getLESSONTHEMA());
                                        }

                                        if (listLesson.get(j).getLESSONS().get(i).getOCENKI() != null) {
                                            for (int z = 0; z < listLesson.get(j).getLESSONS().get(i).getOCENKI().size(); z++) {
                                                String ocenkaName = listLesson.get(j).getLESSONS().get(i).getOCENKI().get(z).getOcenkaName();
                                                String ocenka = listLesson.get(j).getLESSONS().get(i).getOCENKI().get(z).getOcenka();
                                                String notOnLesson = String.valueOf(listLesson.get(j).getLESSONS().get(i).getOCENKI().get(z).getNOTONLESSON());
                                                String notOnLessonText = listLesson.get(j).getLESSONS().get(i).getOCENKI().get(z).getNOTONLESSONTEXT();
                                                String zamechanie = listLesson.get(j).getLESSONS().get(i).getOCENKI().get(z).getZAMECHANIE();
                                                String ocenkaBal = String.valueOf(listLesson.get(j).getLESSONS().get(i).getOCENKI().get(z).getOcenka_bal());

                                                if (ocenciAll.length() != 0) {
                                                    ocenciAll += "<br>";
                                                }
                                                if (!ocenkaName.equalsIgnoreCase("") || ocenka != null) {
                                                    ocenciAll +=
                                                            ocenkaName + ": " + ocenka;
                                                }
                                                if (!notOnLessonText.equalsIgnoreCase("")) {
                                                    ocenciAll += "<br>" + notOnLessonText;
                                                }


                                            if (notOnLesson != null) {
                                                switch (Integer.parseInt(notOnLesson)){
                                                    case 0:
                                                        notOnLesson="Присутній";
                                                        SpannableString ss=  new SpannableString(notOnLesson);
                                                        ss.setSpan(new ForegroundColorSpan(Color.GREEN), 0, notOnLesson.length(), 0);
                                                        ocenciAll += "<br>" +ss;
                                                        break;

                                                    case 1:
                                                        notOnLesson="Відсутній";
                                                        SpannableString sss=  new SpannableString(notOnLesson);
                                                        sss.setSpan(new ForegroundColorSpan(Color.RED), 0, notOnLesson.length(), 0);
                                                        ocenciAll += "<br>" +sss;
                                                        break;

                                                    case 2:
                                                        notOnLesson="Запізнився";
                                                        SpannableString ssss=  new SpannableString(notOnLesson);
                                                        ssss.setSpan(new ForegroundColorSpan(Color.YELLOW), 0, notOnLesson.length(), 0);
                                                        ocenciAll += "<br>" +ssss;
                                                        break;
                                                        }
                                                //  ocenciAll += "\n" + notOnLesson + ": " + notOnLessonText;

                                            }



                                            }
                                        } else {
                                            ocenciAll = "";
                                        }
                                        item.setOCENKI(ocenciAll);
                                        arrayList.add(item);
                                       // Toast.makeText(getActivity(), "Get Data 1", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            adapter = new Adapter_rozklad_new(arrayList);
                            recRozklad.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            frProgress.setVisibility(View.GONE);
                          //  Toast.makeText(getActivity(),"Get Data 2",Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.i("LOG", String.valueOf(anError));
                           // Toast.makeText(getActivity(),"Get Data Error",Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (Exception e) {
            Log.i("TAG", String.valueOf(e));
        }


    }
    */

    public List<Class_check_item> CheckItem(int gpPos, int chPos, int stat) {
        if (stat == 1) {
            listCheck.add(new Class_check_item(gpPos, chPos, stat));
        } else {
            for (int i = 0; i < listCheck.size(); i++) {
                if (gpPos == listCheck.get(i).getGroupPos() && chPos == listCheck.get(i).getChildPos()) {
                    listCheck.remove(i);
                    //listCheck.add(i,new Class_check_item(gpPos, chPos, stat));
                }
            }
        }
        return listCheck;
    }


    private String ReverseDate(String date){
        String dateNew=date;
        try{
            String[] arr=date.split("-");
            dateNew=arr[2]+"-"+arr[1]+"-"+arr[0];
        }catch (Exception e){

        }

        return dateNew;
    }

    private void GetLessonInfo() {
        frProgress.setVisibility(View.VISIBLE);
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm");
        String dateDyrti = df.format(c);


        // String dateDyrti= new SimpleDateFormat().format(Calendar.getInstance().getTime());

        String[] dateClear = dateDyrti.split(" ");
        globalDate = dateClear[0] + " " + dateClear[1];
        try {
            AndroidNetworking.get(" {link}index.php?r=rest/get-week-lessons-new&token={token}&date={date}&pupil_id={id}&klass_id={klass_id}")
                    .addPathParameter("link", link)
                    .addPathParameter("token", token)
                    .addPathParameter("date", dateClear[0])
                    .addPathParameter("klass_id", klass_id)
                    .addPathParameter("id", id)
                  //  .setOkHttpClient(okHttpClient)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsObjectList(Rozpisanie_model_new.class, new ParsedRequestListener<List<Rozpisanie_model_new>>() {
                        @Override
                        public void onResponse(List<Rozpisanie_model_new> response) {
                            String monday = ReverseDate(response.get(0).getDate().getMonday());
                            String sunday = ReverseDate(response.get(0).getDate().getSunday());
                            tvDateVeek.setText(monday + "/" + sunday);
                            List<LessonDay> listLesson = response.get(0).getLessonDays();

                            arrayList = new ArrayList<>();
                            for (int j = 0; j < listLesson.size(); j++) {
                                Header header = new Header();
                                header.setHeader(listLesson.get(j).getDAYDATEL() + " " + listLesson.get(j).getDAYDATE());
                               // header.setFio(listLesson.get(j).get);
                                arrayList.add(header);
                                ocenciAll = "";
                                List<LESSON> size=listLesson.get(j).getLESSONS();
                                if (size!=null) {
                                    ContentItem item = new ContentItem();
                                    item.setLESSONS(listLesson.get(j).getLESSONS());
                                    arrayList.add(item);

                                for (int i = 0; i < listLesson.get(j).getLESSONS().size(); i++) {

                                }
                            }
                            }

                            adapter = new Adapter_rozklad_new(arrayList);
                            recRozklad.setAdapter(adapter);

                            adapter.notifyDataSetChanged();
                            frProgress.setVisibility(View.GONE);
                            frToday.setBackgroundColor(Color.parseColor("#5691C4F1"));
                            frNext.setBackgroundColor(Color.WHITE);
                            frPrevios.setBackgroundColor(Color.WHITE);

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

    private void GetLessonInfoExpandList() {
        frProgress.setVisibility(View.VISIBLE);
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm");
        String dateDyrti = df.format(c);
        String[] dateClear = dateDyrti.split(" ");
        globalDate = dateClear[0] + " " + dateClear[1];
        String[] temp=globalDate.split(" ");
        staticDateToday=temp[0];
        try {
            AndroidNetworking.get(" {link}index.php?r=rest/get-week-lessons-new&token={token}&date={date}&pupil_id={id}&klass_id={klass_id}")
                    .addPathParameter("link", link)
                    .addPathParameter("token", token)
                    .addPathParameter("date", dateClear[0])
                    .addPathParameter("klass_id", klass_id)
                    .addPathParameter("id", id)
                    //  .setOkHttpClient(okHttpClient)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsObjectList(Rozpisanie_model_new.class, new ParsedRequestListener<List<Rozpisanie_model_new>>() {
                        @Override
                        public void onResponse(List<Rozpisanie_model_new> response) {
                            String monday = ReverseDate(response.get(0).getDate().getMonday());
                            String sunday = ReverseDate(response.get(0).getDate().getSunday());
                            String[] mon_arr=monday.split("-");
                            String[] sun_arr=sunday.split("-");
                            tvDateVeek.setText(mon_arr[0]+"."+mon_arr[1] + "-" + sun_arr[0]+"."+sun_arr[1]);
                            List<LessonDay> listLesson = response.get(0).getLessonDays();

                            group_list = new ArrayList<Group>();
                            for (int j = 0; j < listLesson.size(); j++) {

                                 gru1 = new Group();
                                gru1.setName(listLesson.get(j).getDAYDATEL());
                                String[] arr= listLesson.get(j).getDAYDATE().split("-");
                                gru1.setDate(arr[0]+"."+arr[1]);

                                gru1.setCountLesonGrades("уроків "+listLesson.get(j).getCnt_lessons()+" оцінок "+listLesson.get(j).getCnt_ocenki());
                                group_list.add(gru1);
                                ocenciAll = "";
                                List<LESSON> size=listLesson.get(j).getLESSONS();

                                if (size!=null) {
                                    child_list=new ArrayList<>();
                                    for (int i = 0; i < listLesson.get(j).getLESSONS().size(); i++) {
                                        Child item = new Child();
                                        List<LESSON> les=listLesson.get(j).getLESSONS();
                                        if (les!=null){
                                            item.setListLesson(les);
                                        }

                                        item.setName(listLesson.get(j).getLESSONS().get(i).getLESSONNUMSTR());
                                        item.setVid(0);
                                        child_list.add(item);
                                        }
                                    gru1.setItems(child_list);
                                }


                            }
                            group_list.add(gru1);
                            group_list.remove(group_list.size()-1);
                            expandListAdapter=new ExpandListAdapter(getActivity(),group_list,Rozklad_fragment.this);
                            expList.setAdapter(expandListAdapter);
                            expandListAdapter.notifyDataSetChanged();
                            frProgress.setVisibility(View.GONE);


                            if (group_list.size()==0){
                                expList.setVisibility(View.GONE);
                                linKan.setVisibility(View.VISIBLE);
                            }else {
                                expList.setVisibility(View.VISIBLE);
                                linKan.setVisibility(View.GONE);
                            }


                            frLineEarly.setVisibility(View.INVISIBLE);
                            frLineTomorow.setVisibility(View.INVISIBLE);
                            frLineToday.setVisibility(View.VISIBLE);
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

    private void GetLessonInfoPreviosOrNextExpand(String date) {

        frProgress.setVisibility(View.VISIBLE);
       // frToday.setBackgroundColor(Color.WHITE);
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");
        try {
            AndroidNetworking.get(" {link}index.php?r=rest/get-week-lessons-new&token={token}&date={date}&pupil_id={id}&klass_id={klass_id}")
                    .addPathParameter("link", link)
                    .addPathParameter("token", token)
                    .addPathParameter("date", date)
                    .addPathParameter("klass_id", klass_id)
                    .addPathParameter("id", id)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsObjectList(Rozpisanie_model_new.class, new ParsedRequestListener<List<Rozpisanie_model_new>>() {
                        @Override
                        public void onResponse(List<Rozpisanie_model_new> response) {
                            String monday = ReverseDate(response.get(0).getDate().getMonday());
                            String sunday = ReverseDate(response.get(0).getDate().getSunday());
                            String[] mon_arr=monday.split("-");
                            String[] sun_arr=sunday.split("-");
                            tvDateVeek.setText(mon_arr[0]+"."+mon_arr[1] + "-" + sun_arr[0]+"."+sun_arr[1]);
                            List<LessonDay> listLesson = response.get(0).getLessonDays();

                            group_list = new ArrayList<Group>();
                            for (int j = 0; j < listLesson.size(); j++) {

                                gru1 = new Group();
                                gru1.setName(listLesson.get(j).getDAYDATEL());
                                String[] arr= listLesson.get(j).getDAYDATE().split("-");
                                gru1.setDate(arr[0]+"."+arr[1]);
                                gru1.setCountLesonGrades("уроків "+listLesson.get(j).getCnt_lessons()+" оцінок "+listLesson.get(j).getCnt_ocenki());

                                group_list.add(gru1);
                                ocenciAll = "";
                                List<LESSON> size=listLesson.get(j).getLESSONS();

                                if (size!=null) {
                                    child_list=new ArrayList<>();
                                    for (int i = 0; i < listLesson.get(j).getLESSONS().size(); i++) {
                                        Child item = new Child();
                                        List<LESSON> les=listLesson.get(j).getLESSONS();
                                        if (les!=null){
                                            item.setListLesson(les);
                                        }
                                        item.setName(listLesson.get(j).getLESSONS().get(i).getLESSONNUMSTR());
                                        item.setVid(0);
                                        child_list.add(item);
                                    }
                                    gru1.setItems(child_list);
                                }


                            }
                            group_list.add(gru1);
                            group_list.remove(group_list.size()-1);
                            expandListAdapter=new ExpandListAdapter(getActivity(),group_list,Rozklad_fragment.this);

                            expList.setAdapter(expandListAdapter);

                            if (group_list.size()==0){
                                expList.setVisibility(View.GONE);
                                linKan.setVisibility(View.VISIBLE);
                            }else {
                                expList.setVisibility(View.VISIBLE);
                                linKan.setVisibility(View.GONE);
                            }

                            expandListAdapter.notifyDataSetChanged();
                            frProgress.setVisibility(View.GONE);

                            }

                        @Override
                        public void onError(ANError anError) {
                            Log.i("LOG", String.valueOf(anError));
                        }
                    });

        } catch (Exception e) {
            Log.i("TAG", String.valueOf(e));
        }


    }



    private String GetDate(int ident) {
        String[] jjj = globalDate.split(" ");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy HH:mm");
        Date ddd = null;
        try {
            ddd = format.parse(jjj[0] + " " + jjj[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long yourDateMillis;
        if (ident == 0) {
            yourDateMillis = ddd.getTime() - (7 * 24 * 60 * 60 * 1000);
           // frPrevios.setBackgroundColor((Color.parseColor("#5691C4F1")));
           // frNext.setBackgroundColor(Color.WHITE);
        } else {
            yourDateMillis = ddd.getTime() + (7 * 24 * 60 * 60 * 1000);
          //  frNext.setBackgroundColor(Color.parseColor("#5691C4F1"));
           // frPrevios.setBackgroundColor(Color.WHITE);
        }

        Time yourDate = new Time();
        yourDate.set(yourDateMillis);
        String formattedDate = yourDate.format("%m/%d/%y %H:%M");
        globalDate = formattedDate;
        String[] dateClear = formattedDate.split(" ");
        return dateClear[0];
    }

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
