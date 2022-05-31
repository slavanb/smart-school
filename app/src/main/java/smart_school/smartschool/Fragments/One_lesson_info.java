package smart_school.smartschool.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import smart_school.smartschool.Activity.Container;
import smart_school.smartschool.Adapters.Adapter_list_rozklad;
import smart_school.smartschool.Model.Rozpisanie.Lesson;
import smart_school.smartschool.Model.Rozpisanie.Rozpisanie_model;
import smart_school.smartschool.R;

import static android.content.Context.MODE_PRIVATE;

public class One_lesson_info extends Fragment {

    @BindView(R.id.tv_name_predmet)
    TextView tvPredmetName;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_place)
    TextView tvPlace;
    @BindView(R.id.tv_teacher_name)
    TextView tvTeacherName;
    @BindView(R.id.img_ticher)
    CircleImageView imgTeacher;
    @BindView(R.id.tv_lesson_shema)
    TextView tvLessonShema;
    @BindView(R.id.tv_dz)
    TextView tv_dz;
    @BindView(R.id.tv_next_less_data)
    TextView tvNextLessData;
    @BindView(R.id.fr_back)FrameLayout frBack;
    @BindView(R.id.lin_send_mess)LinearLayout linSendMess;

    private View view;
    private String id;
    private String ava;
    private String teacherId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.info_one_lesson, container, false);
        }
        ButterKnife.bind(this, view);
        try {
            id = getArguments().getString("id");
            ava = getArguments().getString("ava");

        } catch (Exception e) {
            Log.i("LOG", String.valueOf(e));
        }

        frBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        linSendMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("human_id",teacherId);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                One_dialog_fragment myFragment = new One_dialog_fragment();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("OneLesson").commit();

            }
        });

        GetInfoOneLesson();
        ((Container) getActivity()).GetNotReadMess();

        return view;

    }

    private void GetInfoOneLesson() {
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");
        try {
            AndroidNetworking.get(" {link}index.php?r=rest/get-lesson-info&token={token}&lesson_id={id}")
                    .addPathParameter("link", link)
                    .addPathParameter("token", token)
                    .addPathParameter("id", id)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                JSONObject data = response.getJSONObject(0);
                                tvDate.setText(data.getString("LESSON_DATE_UA"));
                                tvPredmetName.setText(data.getString("PREDMET_NAME"));
                                if (!data.getString("KABINET_NAME").equalsIgnoreCase("null")){
                                    tvPlace.setText(data.getString("KABINET_NAME"));
                                }else {
                                    tvPlace.setText("Не задано");
                                }
                                tvTeacherName.setText(data.getString("teacherFullName"));
                                Glide.with(view).load(ava).into(imgTeacher);
                                if (!data.getString("LESSON_THEMA").equalsIgnoreCase("null")){
                                    tvLessonShema.setText(Html.fromHtml(data.getString("LESSON_THEMA")));
                                }else {
                                    tvLessonShema.setText("Не задано");
                                }
                                teacherId=data.getString("teacher_human_id");

                                tv_dz.setText(Html.fromHtml(data.getString("HomeWork")));
                                tv_dz.setMovementMethod(LinkMovementMethod.getInstance());
                                Log.i("tv_dz_vlue",data.getString("HomeWork"));
                                } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.i("TAG", String.valueOf(anError));
                        }
                    });


        } catch (Exception e) {
            Log.i("TAG", String.valueOf(e));
        }


    }


}
