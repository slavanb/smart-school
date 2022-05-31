package smart_school.smartschool.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
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
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import smart_school.smartschool.Activity.Container;
import smart_school.smartschool.Adapters.Adapter_Chat_One_Task;
import smart_school.smartschool.Adapters.Adapter_all_messages;
import smart_school.smartschool.Adapters.Adapter_one_dialog_messages;
import smart_school.smartschool.Model.All_messages.Teacher;

import smart_school.smartschool.Model.One_dialog_messages.Message;
import smart_school.smartschool.R;

import static android.content.Context.MODE_PRIVATE;

public class One_dialog_fragment extends Fragment {
    private View view;

    @BindView(R.id.rec_messages)
    RecyclerView recMessages;
    @BindView(R.id.tv_avtor)
    TextView tvAvtor;
    @BindView(R.id.fr_progres)
    FrameLayout frProgress;
    @BindView(R.id.fr_back)
    FrameLayout frBack;
    @BindView(R.id.fr_send)
    FrameLayout frSend;
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.tv_predmet)
    TextView tvPredmet;
    @BindView(R.id.img_fly)
    ImageView imgFly;


    @BindView(R.id.edt_text_mess)
    EditText edtTextMess;
    @BindView(R.id.nest_scroll)
    NestedScrollView nestScroll;
    @BindView(R.id.listView)
    ListView listView;
    private Adapter_one_dialog_messages adapter;
    private Adapter_Chat_One_Task adapterLisView;
    private List<Message> listMess = new ArrayList<>();

    private String interlocator_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.one_dailog_message, container, false);
        }
        ButterKnife.bind(this, view);
        try {
            interlocator_id = getArguments().getString("human_id");
        } catch (Exception e) {
            Log.i("LOG", String.valueOf(e));
        }

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        // recMessages.setLayoutManager(new LinearLayoutManager(getActivity()));
        recMessages.setLayoutManager(mLayoutManager);
        recMessages.setNestedScrollingEnabled(false);
        adapter = new Adapter_one_dialog_messages(getActivity(), listMess, "", "", "");
        recMessages.setAdapter(adapter);


        adapterLisView = new Adapter_Chat_One_Task(getActivity(), listMess, "", "", "");

        frBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        frSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if ( edtTextMess.getText().toString().trim().length()!=0){
                   SendMessage();
                   getActivity().getWindow().setSoftInputMode(
                           WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                   InputMethodManager inputManager = (InputMethodManager) v.getContext()
                           .getSystemService(Context.INPUT_METHOD_SERVICE);
                   inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
               }

            }
        });

        edtTextMess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    imgFly.setColorFilter(Color.parseColor("#358DCC"), PorterDuff.Mode.SRC_IN);
                } else {
                    imgFly.setColorFilter(Color.parseColor("#b8b8b9"), PorterDuff.Mode.SRC_IN);
                }
            }
        });


        GetAllMessages();

        ((Container) getActivity()).GetNotReadMess();


        return view;
    }

    private void SendMessage() {
        frProgress.setVisibility(View.VISIBLE);
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");
        String mess = edtTextMess.getText().toString();
        try {
            AndroidNetworking.post("{server_name}/?r=rest/send-message&token={auth_token}&sel=" + interlocator_id)
                    .addPathParameter("server_name", link)
                    .addPathParameter("auth_token", token)
                    //  .addPathParameter("interlocator_id", interlocator_id)
                    .addBodyParameter("message", mess)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            String answer = null;
                            try {
                                answer = response.getString("result");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (answer.equalsIgnoreCase("ok")) {
                                edtTextMess.setText("");
                                GetAllMessages();
                            } else {
                                Toast.makeText(getActivity(), "Повідомлення не відправлено...", Toast.LENGTH_SHORT).show();
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


    private void GetAllMessages() {
        frProgress.setVisibility(View.VISIBLE);
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");
        try {
            AndroidNetworking.get("{server_name}/?r=rest/get-dialog&token={auth_token}&sel={interlocator_id}")
                    .addPathParameter("server_name", link)
                    .addPathParameter("auth_token", token)
                    .addPathParameter("interlocator_id", interlocator_id)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONArray arr = null;
                            try {
                                arr = response.getJSONArray("messages");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Type listType = new TypeToken<List<Message>>() {
                            }.getType();
                            List<Message> listMess = new Gson().fromJson(arr.toString(), listType);
                            String name = "";
                            String avatar = "";
                            String MyAvatar = "";
                            try {
                                name = response.getString("interlocutor_name");
                                tvAvtor.setText(name);
                                avatar = response.getString("interlocutor_avatar");
                                MyAvatar = response.getString("my_avatar");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapterLisView = new Adapter_Chat_One_Task(view.getContext(), listMess, name, avatar, MyAvatar);
                            listView.setAdapter(adapterLisView);
                            adapterLisView.notifyDataSetChanged();

                            listView.post(new Runnable() {
                                public void run() {
                                    listView.setSelection(listView.getCount() - 1);
                                }
                            });


                            adapter = new Adapter_one_dialog_messages(getActivity(), listMess, name, avatar, MyAvatar);
                            recMessages.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            frProgress.setVisibility(View.GONE);

                            // recMessages.scrollToPosition(listMess.size());
                            // recMessages.smoothScrollToPosition(listMess.size()-1);

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
