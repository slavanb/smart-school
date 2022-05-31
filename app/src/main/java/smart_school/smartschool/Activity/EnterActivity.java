package smart_school.smartschool.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.onesignal.OneSignal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import io.realm.RealmConfiguration;
import smart_school.smartschool.ActivityFirst;
import smart_school.smartschool.Model.Token.Token_class;
import smart_school.smartschool.R;
//import smart_school.smartschool.Support.oneSignalNotif;

public class EnterActivity extends AppCompatActivity {

    @BindView(R.id.edt_login)
    EditText edtLogin;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.btn_enter)
    Button btnEnter;
    @BindView(R.id.tv_name_school)TextView tvNameSchool;
    private Realm mRealm;
    private String idUser;
    private String nameSchool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_activity);
        ButterKnife.bind(this);
        RealmConfiguration config = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
        mRealm = Realm.getDefaultInstance();

        try{
            nameSchool=getIntent().getStringExtra("nameSchool");
            }catch (Exception e){
            Log.i("log",String.valueOf(e));
        }

        //edtLogin.setText("slavanb777");
       // edtPass.setText("slavik123");
        if (nameSchool!=null){
            tvNameSchool.setText(nameSchool);
        }

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtLogin.getText().toString().trim().length() != 0 && edtPass.getText().toString().trim().length() != 0) {
                    Login();
                    btnEnter.setEnabled(false);
                } else {
                    Toast.makeText(EnterActivity.this, "Введите логин и пароль...", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        OneSignal.startInit(this)
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                //.setNotificationOpenedHandler(new oneSignalNotif(this))
//                .init();
//
//        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
//            @Override
//            public void idsAvailable(String userId, String registrationId) {
//                idUser = userId;
//                String regID = registrationId;
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EnterActivity.this,ActivityFirst.class));
        finish();
    }

        private void Login() {
//            OneSignal.startInit(this)
//                    .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                    .unsubscribeWhenNotificationsAreDisabled(true)
//                    //.setNotificationOpenedHandler(new oneSignalNotif(this))
//                    .init();
//
//            OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
//                @Override
//                public void idsAvailable(String userId, String registrationId) {
//                    idUser = userId;
//                    String regID = registrationId;
//                }
//            });

        SharedPreferences preferences = getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        try {
            String authString = edtLogin.getText().toString().trim() + ":" + edtPass.getText().toString().trim();
            System.out.println("auth string: " + authString);
            byte[] authEncBytes = Base64.encode(authString.getBytes(), Base64.NO_WRAP);
            String authStringEnc = new String(authEncBytes);

            AndroidNetworking.get(link+"index.php?r=rest/get-token&android_device_id="+idUser)
                    //.addPathParameter("link", link)
                    .addHeaders("Authorization", "Basic " + authStringEnc)

                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsObjectList(Token_class.class, new ParsedRequestListener<List<Token_class>>() {
                        @Override
                        public void onResponse(List<Token_class> response) {
                            mRealm.beginTransaction();
                            mRealm.copyToRealmOrUpdate(response);
                            mRealm.commitTransaction();
                            String token = response.get(0).getToken();
                            String roly=response.get(0).getUser().getParentOrPupil();
                            SharedPreferences.Editor editor = getSharedPreferences("linkServer", MODE_PRIVATE).edit();
                            editor.putString("token", token);
                            editor.putString("parentORpupil", roly);
                            editor.apply();
                            if (response.get(0).getAuth().equalsIgnoreCase("ok")) {
                                startActivity(new Intent(EnterActivity.this, Container.class));
                            }else {
                                btnEnter.setEnabled(true);
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                        Log.i("TAG",String.valueOf(anError));
                            Toast.makeText(EnterActivity.this,"Не вірний логін або пароль",Toast.LENGTH_SHORT).show();
                            btnEnter.setEnabled(true);
                        }
                    });
        } catch (Exception e) {
            Log.i("TAG", String.valueOf(e));
        }


    }

}
