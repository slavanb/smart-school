package smart_school.smartschool.Fragments;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;


import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;

import smart_school.smartschool.Activity.Container;
import smart_school.smartschool.Model.One_Studet_info.Example;
import smart_school.smartschool.Model.One_Studet_info.PupilInfo;
import smart_school.smartschool.R;

import static android.content.Context.MODE_PRIVATE;

public class Info_one_student extends Fragment {

    @BindView(R.id.tv_klass_director)TextView tvKlassDirector;
    //@BindView(R.id.tv_klass)TextView tvKlass;
    @BindView(R.id.img_arrow_back)ImageView imgBack;
    @BindView(R.id.btn_rozklad)Button btnRozklad;
    @BindView(R.id.fr_btn_individual)LinearLayout frBtnIndividual;
    @BindView(R.id.fr_btn_ocenka_statistika)LinearLayout frOcenkaStatistica;
    @BindView(R.id.tv_first_name)TextView tvFirstName;
    @BindView(R.id.tv_second)TextView tvSecondName;
    @BindView(R.id.tv_otchestvo)TextView tvOtchestvo;
    @BindView(R.id.img_image)CircleImageView imgAvatar;
    @BindView(R.id.img_ticher)CircleImageView imgTeacher;
    @BindView(R.id.fr_back)FrameLayout frBack;
    @BindView(R.id.fr_btn_diary)LinearLayout frBtnDiary;
    @BindView(R.id.fr_btn_home_work)LinearLayout frBtnHomeWork;
    @BindView(R.id.lin_send_mess)LinearLayout linSendMess;
    @BindView(R.id.lin_info_teacher)LinearLayout linInfoTeacher;
    @BindView(R.id.scroll_info)
    ScrollView scrollInfo;
    @BindView(R.id.tv_pay)TextView tvPay;
    @BindView(R.id.tv_link)TextView tvLink;


    private View view;
    private Realm mRealm;
    private String id;
    private String klass_id;
    private String teacherId=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.info_one_student, container, false);
        }
        ButterKnife.bind(this, view);
        mRealm = Realm.getDefaultInstance();

        try {
            id = getArguments().getString("id");
        } catch (Exception e) {
            Log.i("LOG", String.valueOf(e));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            frBtnDiary.setElevation(12);
        }

        final PupilInfo pupilInfo = mRealm.where(PupilInfo.class).equalTo("pupilId", Integer.parseInt(id)).findFirst();
       /*
        if (pupilInfo != null) {
            tvFirstName.setText(pupilInfo.getFamilia()+""+pupilInfo.getName()+" "+pupilInfo.getOtchestvo());
            tvSecondName.setText(pupilInfo.getName());
            //tvOtchestvo.setText(pupilInfo.getOtchestvo());
            tvKlassDirector.setText(pupilInfo.getKlass().getMAINTEACHER().getMAINTEACHERFAMILIA()+" "+pupilInfo.getKlass().getMAINTEACHER().getMAINTEACHERNAME()+" "+pupilInfo.getKlass().getMAINTEACHER().getMAINTEACHEROTCHESTVO());
            tvKlass.setText("поточный клас :  "+pupilInfo.getKlass().getFullKlassName());
            Glide.with(this).load(pupilInfo.getHumanAvatar()).into(imgAvatar);

            klass_id=String.valueOf(pupilInfo.getKlass().getKlassId());
        } else {
            GetStudentInfo();
        }
        */
        GetStudentInfo();

        linSendMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teacherId!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("human_id", teacherId);
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    One_dialog_fragment myFragment = new One_dialog_fragment();
                    myFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("OneLesson").commit();
                }else {
                    Toast.makeText(getActivity().getApplicationContext(),"Відсутній викладач",Toast.LENGTH_SHORT).show();
                }


            }
        });

        frBtnDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("id",String.valueOf(id));
                bundle.putString("klass_id",klass_id);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Rozklad_fragment myFragment = new Rozklad_fragment();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("rozklad").commit();

            }
        });

        frBtnHomeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("id",String.valueOf(id));
                bundle.putString("klass_id",klass_id);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Home_work_fragment myFragment = new Home_work_fragment();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("homeWork").commit();
            }
        });

        frBtnIndividual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("id",String.valueOf(id));
                bundle.putString("klass_id",klass_id);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Individual_sessions_fragment myFragment = new Individual_sessions_fragment();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("individual").commit();
            }
        });

        frOcenkaStatistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("id",String.valueOf(id));

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Statistic_fragment myFragment = new Statistic_fragment();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("statistic").commit();
            }
        });


        frBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
        ((Container)getActivity()).GetNotReadMess();

        return view;
    }

    private void GetStudentInfo() {
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");

        try {
            AndroidNetworking.get("{link}index.php?r=rest/get-pupil-info&pupil_id={id}&token={token}")
                    .addPathParameter("link", link)
                    .addPathParameter("token", token)
                    .addPathParameter("id", id)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsObjectList(Example.class, new ParsedRequestListener<List<Example>>() {
                        @Override
                        public void onResponse(List<Example> response) {
                           boolean ggg=response.get(0).getPupilInfo().getAllow_view();
                           if (ggg==false){
                               scrollInfo.setVisibility(View.GONE);
                               tvPay.setText(Html.fromHtml(response.get(0).getPupilInfo().getPay_text()));
                               tvPay.setVisibility(View.VISIBLE);
                               tvLink.setMovementMethod(LinkMovementMethod.getInstance());
                               tvLink.setVisibility(View.VISIBLE);
                               tvLink.setText(Html.fromHtml(response.get(0).getPupilInfo().getPay_link()));

                           }else {
                               scrollInfo.setVisibility(View.VISIBLE);



                               try{
                                   mRealm.beginTransaction();
                                   mRealm.copyToRealmOrUpdate(response);
                                   mRealm.commitTransaction();
                               }catch (Exception e){
                                   Log.i("TAG",String.valueOf(e));
                               }


                            //PupilInfo pupilInfoOld = mRealm.where(PupilInfo.class).equalTo("pupilId", Integer.parseInt(id)).findFirst();
                            //String kl=pupilInfoOld.getKlass();
                            PupilInfo pupilInfo = response.get(0).getPupilInfo();
                            klass_id=String.valueOf(pupilInfo.getKlass().getKlassId());
                            try{
                                if (pupilInfo.getKlass().getMAINTEACHER().getMAINTEACHERHUMANID()!=null){
                                    teacherId=String.valueOf(pupilInfo.getKlass().getMAINTEACHER().getMAINTEACHERHUMANID());
                                    tvKlassDirector.setText(pupilInfo.getKlass().getMAINTEACHER().getMAINTEACHERFAMILIA()+" "+pupilInfo.getKlass().getMAINTEACHER().getMAINTEACHERNAME()+" "+pupilInfo.getKlass().getMAINTEACHER().getMAINTEACHEROTCHESTVO());
                                    Glide.with(view.getContext()).load(pupilInfo.getKlass().getMAINTEACHER().getMAIN_TEACHER_AVATAR()).into(imgTeacher);
                                }else {
                                    teacherId=null;
                                }
                            }catch (Exception e){
                                Log.i("TAG",String.valueOf(e));
                            }
                            if (teacherId==null){
                                linInfoTeacher.setVisibility(View.GONE);
                            }else {
                                linInfoTeacher.setVisibility(View.VISIBLE);
                            }

                            tvFirstName.setText(pupilInfo.getFamilia()+" "+pupilInfo.getName()+" "+pupilInfo.getOtchestvo());
                            tvSecondName.setText(pupilInfo.getKlass().getFullKlassName()+" КЛАС");
                           // tvOtchestvo.setText();
                            //tvKlass.setText("поточный клас "+pupilInfo.getKlass().getFullKlassName());
                            Glide.with(view.getContext()).load(pupilInfo.getHumanAvatar()).into(imgAvatar);
                           }

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
