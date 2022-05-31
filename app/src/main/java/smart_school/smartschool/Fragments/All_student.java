package smart_school.smartschool.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bumptech.glide.Glide;
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
import io.realm.Realm;
import smart_school.smartschool.Activity.Container;
import smart_school.smartschool.Adapters.Adapter_list_student;

import smart_school.smartschool.Model.All_dialogs.Dialog;
import smart_school.smartschool.Model.One_Studet_info.Example;
import smart_school.smartschool.Model.One_Studet_info.PupilInfo;
import smart_school.smartschool.Model.Pupil_classes.List_student;
import smart_school.smartschool.Model.Pupil_classes.Pupil;
import smart_school.smartschool.R;

import static android.content.Context.MODE_PRIVATE;

public class All_student extends Fragment {

    @BindView(R.id.rec_list_student)
    RecyclerView recListStudent;
    @BindView(R.id.fr_back)FrameLayout frBack;
    private View view;
    private Realm mRealm;
    private Adapter_list_student adapterListStudent;
    private List<Pupil> list_student=new ArrayList<>();


    /**
     *
     *
     * storePassword "STORE_PASSWORD"
     *             keyAlias "KEY_ALIAS"
     *             keyPassword "KEY_PASSWORD"
     */


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view==null){
            view=inflater.inflate(R.layout.all_student,container,false);
        }
        ButterKnife.bind(this,view);
        mRealm=Realm.getDefaultInstance();

      //  list_student=mRealm.where(Pupil.class).findAll();
        recListStudent.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterListStudent=new Adapter_list_student(getActivity(),list_student);
        recListStudent.setAdapter(adapterListStudent);
        adapterListStudent.notifyDataSetChanged();


        ((Container)getActivity()).GetNotReadMess();

        frBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();


            }
        });


        GetStudentInfo();


        return view;
    }

    private void GetStudentInfo() {
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");

        try {
            AndroidNetworking.get("{link}index.php?r=rest/get-pupils&token={token}")
                    .addPathParameter("link", link)
                    .addPathParameter("token", token)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsObjectList(List_student.class, new ParsedRequestListener<List<List_student>>() {
                        @Override
                        public void onResponse(List<List_student> response) {
                            list_student= response.get(0).getPupils();
                            adapterListStudent=new Adapter_list_student(getActivity(),list_student);
                            recListStudent.setAdapter(adapterListStudent);
                            adapterListStudent.notifyDataSetChanged();

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






}
