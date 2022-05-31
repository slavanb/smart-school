package smart_school.smartschool;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bumptech.glide.Glide;
import com.onesignal.OneSignal;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;
import smart_school.smartschool.Activity.Container;
import smart_school.smartschool.Activity.EnterActivity;
import smart_school.smartschool.Adapters.Adapter_Spinner_city;
import smart_school.smartschool.Adapters.Adapter_spinner_school;
import smart_school.smartschool.Model.City_name;
import smart_school.smartschool.Model.One_Studet_info.Example;
import smart_school.smartschool.Model.School_data;
import smart_school.smartschool.Model.Token.Token_class_user;

public class ActivityFirst extends AppCompatActivity {

    @BindView(R.id.spin_city)
    Spinner spinCity;
    @BindView(R.id.spin_scholl)
    Spinner spinScholl;
    @BindView(R.id.button)
    Button btnEnter;
    @BindView(R.id.fr_progres)
    FrameLayout frProgress;
    @BindView(R.id.img_gps)
    ImageView imgGps;
    @BindView(R.id.img_hat)
    ImageView imgHat;

    private HashMap<Integer, String> spinnerMap;
    private HashMap<Integer, String> spinnerMapScholl;
    private List<City_name> cityList = new ArrayList<>();
    private List<School_data> schoolList = new ArrayList<>();
    Adapter_spinner_school adapter;
    Adapter_Spinner_city adapterCity;
    private Realm mRealm;
    private String nameSchool;

    private String latestVersion = null;
    private String version;
    PackageInfo pInfo;
    private static final String ONESIGNAL_APP_ID = "05476caa-804b-4fe5-9dcb-bbe0cd70493a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


//        manifestPlaceholders = [onesignal_app_id : "05476caa-804b-4fe5-9dcb-bbe0cd70493a",
//                // Project number pulled from dashboard, local value is ignored.
//                onesignal_google_project_number: "REMOTE"]

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);


        RealmConfiguration config = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
        mRealm = Realm.getDefaultInstance();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Example> rows = realm.where(Example.class).findAll();
                rows.clear();
            }
        });

        SharedPreferences preferences = getSharedPreferences("linkServer", MODE_PRIVATE);
        String token = preferences.getString("token", "");
        String link = preferences.getString("link", "");
        Token_class_user user = mRealm.where(Token_class_user.class).findFirst();

        /******************************************************************************/
        /** Check for Update App **/



//        try {
//            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
//            version = pInfo.versionName;
//            Log.i("VersionName", version);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        VersionChecker versionChecker = new VersionChecker();
//        try {
//            latestVersion = versionChecker.execute().get();
//            try {
//                Log.i("VersionName", String.valueOf(latestVersion));
//
//            } catch (Exception e) {
//
//            }
//            if (latestVersion != null && !latestVersion.isEmpty()) {
//                if (Float.valueOf(version) < Float.valueOf(latestVersion)) {
//                    Log.i("VersionName", String.valueOf(latestVersion)+"HAVE NEW WERSION");
//
//                    //show dialog
//
//                }
//            }
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
/***********************************************************************************/


        if (token.equalsIgnoreCase("") || link.equalsIgnoreCase("") || user == null) {
            if (isNetworkAvailable(this)) {
                GetDataForSpinCity();
            } else {
                Toast.makeText(ActivityFirst.this, "Подключитесь к интернет...", Toast.LENGTH_SHORT).show();
            }
        } else {
            startActivity(new Intent(ActivityFirst.this, Container.class));
            finish();
        }


        spinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    imgGps.setColorFilter(Color.parseColor("#3DCE41"), PorterDuff.Mode.SRC_IN);

                } else {
                    imgGps.setColorFilter(Color.parseColor("#C6D7E8"), PorterDuff.Mode.SRC_IN);
                }
                String idCity = String.valueOf(cityList.get(position).getId());
                GetDataSchool(idCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnEnter.setEnabled(false);
        spinScholl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    imgHat.setColorFilter(Color.parseColor("#3DCE41"), PorterDuff.Mode.SRC_IN);
                    btnEnter.setEnabled(true);
                } else {
                    imgHat.setColorFilter(Color.parseColor("#C6D7E8"), PorterDuff.Mode.SRC_IN);
                    btnEnter.setEnabled(false);
                }
                String link = schoolList.get(position).getServerLink();
                nameSchool = schoolList.get(position).getServerName();
                SharedPreferences.Editor editor = getSharedPreferences("linkServer", MODE_PRIVATE).edit();
                editor.putString("link", link);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityFirst.this, EnterActivity.class).putExtra("nameSchool", nameSchool));
                finish();
            }
        });


    }

    public Realm buildDatabase() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();

        try {
            return Realm.getInstance(realmConfiguration);
        } catch (RealmMigrationNeededException e) {
            try {
                Realm.deleteRealm(realmConfiguration);
                //Realm file has been deleted.
                return Realm.getInstance(realmConfiguration);
            } catch (Exception ex) {
                throw ex;
                //No Realm file to remove.
            }
        }
    }

    private void GetDataSchool(String id) {
        frProgress.setVisibility(View.VISIBLE);
        try {
            AndroidNetworking.get("https://server.smart-school.com.ua/get-info/servers.php?city-id={id}")
                    .addPathParameter("id", id)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsObjectList(School_data.class, new ParsedRequestListener<List<School_data>>() {
                        @Override
                        public void onResponse(List<School_data> response) {
                            //Toast.makeText(ActivityFirst.this,"Get data school",Toast.LENGTH_SHORT).show();
                            schoolList = response;
                            schoolList.add(0, new School_data(0, 0, "Оберіть школу", "Оберіть школу"));
                            adapter = new Adapter_spinner_school(ActivityFirst.this, schoolList);
                            spinScholl.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            //  btnEnter.setEnabled(true);
                            frProgress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(ActivityFirst.this, String.valueOf(anError), Toast.LENGTH_SHORT).show();
                            Log.i("LOG", String.valueOf(anError));
                        }
                    });

        } catch (Exception e) {
            Log.i("TAG", String.valueOf(e));
        }
    }


    private void GetDataForSpinCity() {
        frProgress.setVisibility(View.VISIBLE);
        try {
            AndroidNetworking.get("https://server.smart-school.com.ua/get-info/citys.php")
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsObjectList(City_name.class, new ParsedRequestListener<List<City_name>>() {
                        @Override
                        public void onResponse(List<City_name> response) {
                            // Toast.makeText(ActivityFirst.this,"Get data city",Toast.LENGTH_SHORT).show();
                            cityList = response;
                            cityList.add(0, new City_name(0, "Оберіть регіон"));

                            adapterCity = new Adapter_Spinner_city(ActivityFirst.this, cityList);
                            spinCity.setAdapter(adapterCity);
                            adapterCity.notifyDataSetChanged();
                            frProgress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(ActivityFirst.this, String.valueOf(anError), Toast.LENGTH_SHORT).show();
                            Log.i("LOG", String.valueOf(anError));
                        }
                    });

        } catch (Exception e) {
            Log.i("TAG", String.valueOf(e));
        }
    }

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}
