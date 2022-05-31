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
import smart_school.smartschool.Adapters.Adapter_home_work_expand_list;
import smart_school.smartschool.Adapters.Adapter_list_rozklad;
import smart_school.smartschool.Adapters.ContentItem;
import smart_school.smartschool.Adapters.ExpandListAdapter;
import smart_school.smartschool.Adapters.Header;
import smart_school.smartschool.Adapters.ListItem;
import smart_school.smartschool.Adapters.Adapter_home_work;
import smart_school.smartschool.Model.Child;
import smart_school.smartschool.Model.Group;
import smart_school.smartschool.Model.Home_work_group.Child_home_work;
import smart_school.smartschool.Model.Home_work_group.Group_home_work;
import smart_school.smartschool.Model.Rozpisanie.Lesson;
import smart_school.smartschool.Model.Rozpisanie.New.LESSON;
import smart_school.smartschool.Model.Rozpisanie.New.LessonDay;
import smart_school.smartschool.Model.Rozpisanie.New.Rozpisanie_model_new;
import smart_school.smartschool.R;
import smart_school.smartschool.Support.OnSwipeTouchListener;

import static android.content.Context.MODE_PRIVATE;

public class Home_work_fragment extends Fragment {
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
    //@BindView(R.id.textView17)  TextView tv17;
    @BindView(R.id.exp_list)ExpandableListView expList;
    @BindView(R.id.fr_line_early)FrameLayout frLineEarly;
    @BindView(R.id.fr_line_today)FrameLayout frLineToday;
    @BindView(R.id.fr_line_tomorow)FrameLayout frLineTomorow;


    private String id;
    private String klass_id;

    private Realm mRealm;
    private List<Lesson> listLesson = new ArrayList<>();
    private String globalDate;
    private String homeWork="";
    private GestureDetector gestureDetector;


    //List<List<ListItem>> arraylist;
    ArrayList<ListItem> arrayList = new ArrayList<>();
    Adapter_home_work adapter;

    ArrayList<Group_home_work> group_list=new ArrayList<>();
    ArrayList<Child_home_work> child_list=new ArrayList<>();
    Group_home_work gru1=new Group_home_work();
    Adapter_home_work_expand_list expandListAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.home_work_fragment, container, false);
        }
        ButterKnife.bind(this, view);
        mRealm = Realm.getDefaultInstance();

        try {
            id = getArguments().getString("id");
            klass_id = getArguments().getString("klass_id");
        } catch (Exception e) {
            Log.i("LOG", String.valueOf(e));
        }

        /*
        recRozklad.setLayoutManager(new LinearLayoutManager(getActivity()));
        recRozklad.setNestedScrollingEnabled(false);
        adapter = new Adapter_home_work(arrayList);
        recRozklad.setAdapter(adapter);

         */

        expList.setOnTouchListener(new OnSwipeTouchListener() {

            public void onSwipeTop() {
                // Toast.makeText(getActivity(), "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                //  Toast.makeText(getActivity(), "right", Toast.LENGTH_SHORT).show();
                GetLessonInfoPreviosOrNext(GetDate(0));
                frLineEarly.setVisibility(View.VISIBLE);
                frLineTomorow.setVisibility(View.INVISIBLE);
                frLineToday.setVisibility(View.INVISIBLE);
            }
            public void onSwipeLeft() {
                //  Toast.makeText(getActivity(), "left", Toast.LENGTH_SHORT).show();

                GetLessonInfoPreviosOrNext(GetDate(1));
                frLineEarly.setVisibility(View.INVISIBLE);
                frLineTomorow.setVisibility(View.VISIBLE);
                frLineToday.setVisibility(View.INVISIBLE);
            }
            public void onSwipeBottom() {
                // Toast.makeText(getActivity(), "bottom", Toast.LENGTH_SHORT).show();
            }

        });

        /*
        listLesson=mRealm.where(Lesson.class).findAll();
        if (listLesson.size()==0||listLesson==null||isNetworkAvailable(getActivity())){
           // GetLessonInfo();
        }else {
            adapterListRozklad=new Adapter_list_rozklad(getActivity(),listLesson);
            recRozklad.setAdapter(adapterListRozklad);
            adapterListRozklad.notifyDataSetChanged();
        }
*/

        expList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousItem )
                    expList.collapseGroup(previousItem );
                previousItem = groupPosition;
            }
        });
        gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {


                if (velocityX < 0) {
                    GetLessonInfoPreviosOrNext(GetDate(1));
                } else if (velocityX > 0) {
                    GetLessonInfoPreviosOrNext(GetDate(0));

                }

                return super.onFling(e1, e2, velocityX, velocityY);
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return super.onDown(e);
            }
        });



        /*
        nestedScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
        */

        frBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        frPrevios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetLessonInfoPreviosOrNext(GetDate(0));
                frLineEarly.setVisibility(View.VISIBLE);
                frLineTomorow.setVisibility(View.INVISIBLE);
                frLineToday.setVisibility(View.INVISIBLE);
            }
        });

        frNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetLessonInfoPreviosOrNext(GetDate(1));
                frLineEarly.setVisibility(View.INVISIBLE);
                frLineTomorow.setVisibility(View.VISIBLE);
                frLineToday.setVisibility(View.INVISIBLE);
            }
        });

        frToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  GetLessonInfo();
                GetLessonInfo();
                frLineEarly.setVisibility(View.INVISIBLE);
                frLineTomorow.setVisibility(View.INVISIBLE);
                frLineToday.setVisibility(View.VISIBLE);
            }
        });
        GetLessonInfo();
        ((Container)getActivity()).GetNotReadMess();


        return view;
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

                            group_list = new ArrayList<Group_home_work>();
                            for (int j = 0; j < listLesson.size(); j++) {

                                gru1 = new Group_home_work();
                                gru1.setName(listLesson.get(j).getDAYDATEL());
                                String[] arr= listLesson.get(j).getDAYDATE().split("-");
                                gru1.setDate(arr[0]+"."+arr[1]);
                                gru1.setCountLesonGrades("уроків "+listLesson.get(j).getCnt_lessons());

                                group_list.add(gru1);

                                List<LESSON> size=listLesson.get(j).getLESSONS();

                                if (size!=null) {
                                    child_list=new ArrayList<>();
                                    for (int i = 0; i < listLesson.get(j).getLESSONS().size(); i++) {
                                        Child_home_work item = new Child_home_work();
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
                            expandListAdapter=new Adapter_home_work_expand_list(getActivity(),group_list,Home_work_fragment.this);

                            expList.setAdapter(expandListAdapter);

                            expandListAdapter.notifyDataSetChanged();
                            frProgress.setVisibility(View.GONE);
                          //  frProgress.setVisibility(View.GONE);

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
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    . writeTimeout(120, TimeUnit.SECONDS)
                    .build();

            AndroidNetworking.get(" {link}index.php?r=rest/get-week-lessons-new&token={token}&date={date}&pupil_id={id}&klass_id={klass_id}")
                    .addPathParameter("link", link)
                    .addPathParameter("token", token)
                    .addPathParameter("date", dateClear[0])
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

                            group_list = new ArrayList<Group_home_work>();
                            for (int j = 0; j < listLesson.size(); j++) {

                                gru1 = new Group_home_work();
                                gru1.setName(listLesson.get(j).getDAYDATEL());
                                String[] arr= listLesson.get(j).getDAYDATE().split("-");
                                gru1.setDate(arr[0]+"."+arr[1]);
                                gru1.setCountLesonGrades("уроків "+listLesson.get(j).getCnt_lessons());

                                group_list.add(gru1);

                                List<LESSON> size=listLesson.get(j).getLESSONS();

                                if (size!=null) {
                                    child_list=new ArrayList<>();
                                    for (int i = 0; i < listLesson.get(j).getLESSONS().size(); i++) {
                                        Child_home_work item = new Child_home_work();
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
                            expandListAdapter=new Adapter_home_work_expand_list(getActivity(),group_list,Home_work_fragment.this);
                            expList.setAdapter(expandListAdapter);
                            expandListAdapter.notifyDataSetChanged();
                            frProgress.setVisibility(View.GONE);
                            frProgress.setVisibility(View.GONE);


                            /*
                            frToday.setBackgroundColor(Color.parseColor("#5691C4F1"));
                            frNext.setBackgroundColor(Color.WHITE);
                            frPrevios.setBackgroundColor(Color.WHITE);
                            */


                            /*
                                 mRealm.beginTransaction();
                            mRealm.copyToRealmOrUpdate(listLesson);
                            mRealm.commitTransaction();


                            adapterListRozklad=new Adapter_list_rozklad(getActivity(),listLesson);
                            recRozklad.setAdapter(adapterListRozklad);
                            adapterListRozklad.notifyDataSetChanged();
                                */

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
          //  frNext.setBackgroundColor(Color.WHITE);
        } else {
            yourDateMillis = ddd.getTime() + (7 * 24 * 60 * 60 * 1000);
          //  frNext.setBackgroundColor(Color.parseColor("#5691C4F1"));
          //  frPrevios.setBackgroundColor(Color.WHITE);
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

