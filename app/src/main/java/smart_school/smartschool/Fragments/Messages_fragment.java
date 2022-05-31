package smart_school.smartschool.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;


import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import smart_school.smartschool.Activity.Container;
import smart_school.smartschool.Adapters.Adapter_all_dialog_listview;
import smart_school.smartschool.Adapters.Adapter_all_dialogs;
import smart_school.smartschool.Adapters.Adapter_all_messages;
import smart_school.smartschool.Model.All_dialogs.Dialog;
import smart_school.smartschool.Model.All_messages.Teacher;
import smart_school.smartschool.R;

import static android.content.Context.MODE_PRIVATE;

public class Messages_fragment extends Fragment {
    private View view;

    @BindView(R.id.rec_messages)
    RecyclerView recMessages;
    @BindView(R.id.fr_progres)
    FrameLayout frProgress;
    @BindView(R.id.fr_back)
    FrameLayout frBack;
    @BindView(R.id.fr_today)
    FrameLayout btnContacts;
    @BindView(R.id.fr_previos)
    FrameLayout btnDialogs;
    @BindView(R.id.fr_line_early)
    FrameLayout frLineContacts;
    @BindView(R.id.fr_line_today)
    FrameLayout frLineDialogs;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_dialog)
    TextView tvDialog;
    @BindView(R.id.listViewSwipe)
    SwipeMenuListView swipeMenuListView;

    private Adapter_all_messages adapter;
    private Adapter_all_dialogs adapterAllDialogs;
    // private List<Teacher> listTeacher= new ArrayList<>();
    private List<Dialog> listTeacher = new ArrayList<>();
    private Adapter_all_dialog_listview adapterAllDialogListview;
    SwipeMenuCreator creator;
    private GestureDetector gestureDetector;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
        }
        view = inflater.inflate(R.layout.messages_fragment, container, false);

        ButterKnife.bind(this, view);


        recMessages.setLayoutManager(new LinearLayoutManager(getActivity()));
        recMessages.setNestedScrollingEnabled(false);
        // adapter = new Adapter_all_messages(getActivity(), listTeacher);
        adapterAllDialogs = new Adapter_all_dialogs(getActivity(), listTeacher);
        // recMessages.setAdapter(adapter);
        recMessages.setAdapter(adapterAllDialogs);

        adapterAllDialogListview=new Adapter_all_dialog_listview(listTeacher,getActivity());
        swipeMenuListView.setAdapter(adapterAllDialogListview);
        creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(128, 128, 128)));
                // set item width
                deleteItem.setWidth(dp2px(70));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_frame);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        swipeMenuListView.setMenuCreator(creator);
        swipeMenuListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                swipeMenuListView.smoothOpenMenu(position);
            }

            @Override
            public void onSwipeEnd(int position) {

            }
            });


        swipeMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("human_id", String.valueOf(listTeacher.get(position).getInterlocutorId()));
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                One_dialog_fragment myFragment = new One_dialog_fragment();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("lesson").commit();

            }
        });

        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open

                        if (listTeacher.size()>0) {
                            DeleteDialog(String.valueOf(listTeacher.get(position).getInterlocutorId()));
                            listTeacher.remove(position);
                            adapterAllDialogListview.notifyDataSetChanged();

                        }
                    case 1:
                        // delete
                        //listBasket.remove(position);
                        // adapterMessage.notifyDataSetChanged();
                        break;
                        }
                // false : close the menu; true : not close the menu
                return true;
            }
        });


        GetAllDialogs();

        frBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAllMessages();
                frLineContacts.setVisibility(View.INVISIBLE);
                frLineDialogs.setVisibility(View.VISIBLE);
                tvDialog.setTextColor(Color.parseColor("#879399"));
                tvContact.setTextColor(Color.parseColor("#1F2C33"));
                swipeMenuListView.setVisibility(View.GONE);
                recMessages.setVisibility(View.VISIBLE);
            }
        });

        btnDialogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAllDialogs();
                frLineContacts.setVisibility(View.VISIBLE);
                frLineDialogs.setVisibility(View.INVISIBLE);
                tvDialog.setTextColor(Color.parseColor("#1F2C33"));
                tvContact.setTextColor(Color.parseColor("#879399"));
                swipeMenuListView.setVisibility(View.VISIBLE);
                recMessages.setVisibility(View.GONE);
            }
        });


        // GetAllMessages();

        frLineContacts.setVisibility(View.VISIBLE);
        frLineDialogs.setVisibility(View.INVISIBLE);
        tvDialog.setTextColor(Color.parseColor("#1F2C33"));
        tvContact.setTextColor(Color.parseColor("#879399"));
        ((Container) getActivity()).GetNotReadMess();


        return view;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private void GetAllDialogs() {
        frProgress.setVisibility(View.VISIBLE);
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");
        try {
            AndroidNetworking.get("{server_name}/?r=rest/get-dialogs&token={auth_token}")
                    .addPathParameter("server_name", link)
                    .addPathParameter("auth_token", token)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONArray arrTeachers = null;
                            try {
                                arrTeachers = response.getJSONArray("dialogs");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Type listType = new TypeToken<List<Dialog>>() {
                            }.getType();
                            //List<Dialog>
                                    listTeacher = new Gson().fromJson(arrTeachers.toString(), listType);
                            //List<Teacher> listTeacher=response.get(0).getInterlocutors().getTeachers();
                            adapterAllDialogs = new Adapter_all_dialogs(getActivity(), listTeacher);
                            recMessages.setAdapter(adapterAllDialogs);
                            adapterAllDialogs.notifyDataSetChanged();

                            adapterAllDialogListview=new Adapter_all_dialog_listview(listTeacher,getActivity());
                            swipeMenuListView.setAdapter(adapterAllDialogListview);
                            swipeMenuListView.setMenuCreator(creator);
                            adapterAllDialogListview.notifyDataSetChanged();



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

    private void DeleteDialog (String id_User ) {
        frProgress.setVisibility(View.VISIBLE);
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");
        try {
            AndroidNetworking.get("{server_name}/?r=rest/delete-communication&with_id={id_user}&token={auth_token}")
                    .addPathParameter("server_name", link)
                    .addPathParameter("auth_token", token)
                    .addPathParameter("id_user",id_User)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getString("result").equalsIgnoreCase("ok"))
                                 GetAllDialogs();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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



    private void GetAllMessages() {
        frProgress.setVisibility(View.VISIBLE);
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");
        try {
            AndroidNetworking.get("{server_name}/?r=rest/get-interlocutors-list&token={auth_token}")
                    .addPathParameter("server_name", link)
                    .addPathParameter("auth_token", token)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONObject arr = null;
                            JSONArray arrTeachers = null;
                            String avatar = "";
                            List<Teacher> listTeacher = new ArrayList<>();
                            try {
                                arr = response.getJSONObject("interlocutors");
                                SharedPreferences sharedPref = getActivity().getSharedPreferences("linkServer", Context.MODE_PRIVATE);
                                String roly = sharedPref.getString("parentORpupil", "pupil");
                                if (roly.equalsIgnoreCase("pupil")) {
                                    arrTeachers = arr.getJSONArray("classmates");
                                    JSONArray parent = arr.getJSONArray("parents");
                                    for (int p = 0; p < parent.length(); p++) {
                                        arrTeachers.put(parent.get(p));
                                    }

                                    JSONArray tech = arr.getJSONArray("techers");
                                    for (int t = 0; t < tech.length(); t++) {
                                        arrTeachers.put(tech.get(t));
                                    }

                                } else {
                                    arrTeachers = arr.getJSONArray("teachers");
                                }
                                Type listType = new TypeToken<List<Teacher>>() {}.getType();
                                listTeacher = new Gson().fromJson(arrTeachers.toString(), listType);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            //List<Teacher> listTeacher=response.get(0).getInterlocutors().getTeachers();
                            adapter = new Adapter_all_messages(getActivity(), listTeacher, "");
                            recMessages.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                            frProgress.setVisibility(View.GONE);


                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.i("LOG", String.valueOf(anError));
                            frProgress.setVisibility(View.GONE);
                        }
                    });


                    /*
                    .getAsObjectList(MessagesHead.class, new ParsedRequestListener<List<MessagesHead>>() {
                        @Override
                        public void onResponse(List<MessagesHead> response) {
                            List<Teacher> listTeacher=response.get(0).getInterlocutors().getTeachers();
                            adapter = new Adapter_all_messages(getActivity(), listTeacher);
                            recMessages.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                            frProgress.setVisibility(View.GONE);


                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.i("LOG", String.valueOf(anError));
                            frProgress.setVisibility(View.GONE);
                        }
                    });
                    */


        } catch (Exception e) {
            Log.i("TAG", String.valueOf(e));
        }


    }

    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
    }


}
