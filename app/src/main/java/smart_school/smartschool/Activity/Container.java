package smart_school.smartschool.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;


import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bumptech.glide.Glide;
//import com.crashlytics.android.Crashlytics;
import com.google.android.material.navigation.NavigationView;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
//import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import smart_school.smartschool.ActivityFirst;
import smart_school.smartschool.Adapters.Adapter_drawer_list;
import smart_school.smartschool.Adapters.Adapter_list_news;
import smart_school.smartschool.BuildConfig;
import smart_school.smartschool.Fragments.All_student;
import smart_school.smartschool.Fragments.Messages_fragment;
import smart_school.smartschool.Fragments.One_dialog_fragment;
import smart_school.smartschool.Model.Pupil_classes.List_student;
import smart_school.smartschool.Model.Pupil_classes.Pupil;
import smart_school.smartschool.Model.Token.Token_class_user;
import smart_school.smartschool.Fragments.News_fragment;
import smart_school.smartschool.R;
import smart_school.smartschool.Support.OnSwipeListener;
//import smart_school.smartschool.Support.oneSignalNotif;

public class Container extends AppCompatActivity implements View.OnTouchListener {

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.rec_list_student)
    RecyclerView recListStudent;
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.tv_name_parent)
    TextView tvNameParent;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.img_open_menu)
    ImageView imgOpenMenu;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.fr_messages)
    FrameLayout frMessages;
    @BindView(R.id.tv_not_read)
    TextView tvNotReadMess;
    @BindView(R.id.lin_news)
    LinearLayout linNews;
    @BindView(R.id.lin_student)
    LinearLayout linStudent;
    @BindView(R.id.lin_message)
    LinearLayout linMess;
    @BindView(R.id.lin_exit)
    LinearLayout linExit;
    @BindView(R.id.tv_mail)TextView tvMail;
    @BindView(R.id.tv_count_mess)FrameLayout tvCountMess;
    @BindView(R.id.tv_roly)TextView tvRoly;
    @BindView(R.id.lin_web_version)LinearLayout linWebVersion;


    private List<String> listItem = new ArrayList<>();
    private List<Pupil> list_student = new ArrayList<>();
    private Adapter_drawer_list adapterListNews;
    private Realm mRealm;
    private GestureDetector gestureDetector;

    public void handleUncaughtException(Thread thread, Throwable e) {
        String stackTrace = Log.getStackTraceString(e);
        String message = e.getMessage();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"andrij2198@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "App Crash log file");
        intent.putExtra(Intent.EXTRA_TEXT, stackTrace);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // required when starting from Application
        try {
            startActivity(Intent.createChooser(intent, "Отправте ошибку по електронной почте..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Container.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private SharedPreferences mPrefDate;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor editorDays;
    private SharedPreferences mDisplayDialog;
    private int totalCount;
    private long startDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);

//        Fabric.with(this, new Crashlytics());

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                handleUncaughtException(thread, ex);
            }

        });



        mDisplayDialog = getPreferences(Context.MODE_PRIVATE);
        String dialog = mDisplayDialog.getString("DialogManager", " ");
        prefs = getPreferences(Context.MODE_PRIVATE);
        editor = prefs.edit();
        totalCount = prefs.getInt("counter", 0);
        totalCount++;
        editor.putInt("counter", totalCount);
        editor.apply();
        //int x = totalCount;
        mPrefDate = getPreferences(Context.MODE_PRIVATE);


        if (totalCount == 1) {
            if (Build.VERSION.SDK_INT >= 23) {
                editorDays = mPrefDate.edit();
                Date date = new Date(System.currentTimeMillis());
                editorDays.putLong("countDate", date.getTime());
                editorDays.apply();

                //startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)));

            }


        }
        startDate = mPrefDate.getLong("countDate", new Date().getTime());
        long msDiff = Calendar.getInstance().getTimeInMillis() - startDate;
        long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
        if (!dialog.equals("no")) {
            if (daysDiff == 5 || totalCount == 5 || daysDiff == 10 || totalCount == 10 || daysDiff == 15 || totalCount == 15 ||
                    daysDiff == 20 || totalCount == 20 || daysDiff == 25 || totalCount == 25 || daysDiff == 30 || totalCount == 30
                    || daysDiff == 35 || totalCount == 35 || daysDiff == 40 || totalCount == 40 || daysDiff == 45 || totalCount == 45) {

                showReview();

            }

        }



//        OneSignal.startInit(this)
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .setNotificationOpenedHandler(new oneSignalNotif(this))
//                .init();
//
//        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
//            @Override
//            public void idsAvailable(String userId, String registrationId) {
//                String idUser = userId;
//                String regID = registrationId;
//                SharedPreferences.Editor editor = getSharedPreferences("linkServer", MODE_PRIVATE).edit();
//                editor.putString("userId", idUser);
//                editor.apply();
//
//            }
//        });

        ButterKnife.bind(this);
        RealmConfiguration config = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);

        mRealm = Realm.getDefaultInstance();

        listItem = new ArrayList<>();
        listItem.add("Новини");
        SharedPreferences sharedPref = getSharedPreferences("linkServer", Context.MODE_PRIVATE);
        String roly = sharedPref.getString("parentORpupil", "pupil");
        if (roly.equalsIgnoreCase("pupil")) {
            listItem.add("Мій щоденник");
            tvRoly.setText("Мій щоденник");
        } else {
            listItem.add("Учні");
        }
        listItem.add("Повідомлення");
        listItem.add("Вихід");

        linWebVersion.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("linkServer", MODE_PRIVATE);
            String link = prefs.getString("link", "");
            String token = prefs.getString("token", "");
            String address=link+"index.php?r=token-login/index&token="+token;
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
            startActivity(browserIntent);
        });

        linNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                News_fragment myFragment = new News_fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("news").commit();
                DrawerClose();
            }
        });

        linStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                All_student myFragment = new All_student();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("detalis").commit();
                DrawerClose();
            }
        });

        linMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Messages_fragment myFragment = new Messages_fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack("messages").commit();
                DrawerClose();
            }
        });

        linExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRealm.beginTransaction();
              mRealm.clear(Token_class_user.class);
              mRealm.commitTransaction();

                SharedPreferences preferences = getSharedPreferences("linkServer", MODE_PRIVATE);
                preferences.edit().clear().commit();
                startActivity(new Intent(Container.this, ActivityFirst.class));
                finish();
            }
        });


        recListStudent.setLayoutManager(new LinearLayoutManager(this));
        adapterListNews = new Adapter_drawer_list(Container.this, listItem);
        recListStudent.setAdapter(adapterListNews);
        adapterListNews.notifyDataSetChanged();

        Token_class_user user = mRealm.where(Token_class_user.class).findFirst();
        if (user != null) {
            tvNameParent.setText(user.getFamilia() + " " + user.getName() + " " + user.getOtchestvo());
            Glide.with(this).load(user.getHumanAvatar()).into(imgAvatar);
            tvMail.setText(user.getEmail());
        } else {
            SharedPreferences preferences = getSharedPreferences("linkServer", MODE_PRIVATE);
            preferences.edit().clear().commit();
            startActivity(new Intent(this, ActivityFirst.class));
            finish();

        }

        OnSwipeListener onSwipeListener = new OnSwipeListener() {

            @Override
            public boolean onSwipe(Direction direction) {
                if (direction == Direction.down) {

                } else if (direction == Direction.up) {

                    return true;
                }

                return super.onSwipe(direction);
            }
        };


        gestureDetector = new GestureDetector(this, onSwipeListener);
        container.setOnTouchListener(this);


        imgOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerClose();
            }
        });


        News_fragment news_fragment = new News_fragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, news_fragment)
                .commit();


        list_student = mRealm.where(Pupil.class).findAll();
        if (isNetworkAvailable(this)) {
            GetStudent();
        } else {
            if (list_student != null || list_student.size() != 0) {
                Toast.makeText(this, "Перевірте підключення...", Toast.LENGTH_SHORT).show();
            }
        }

        frMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Messages_fragment messFrag = new Messages_fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, messFrag).addToBackStack("mess_frag").commit();
            }
        });

        GetNotReadMess();


    }


    private void showReview() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Нагадування")
                .setMessage("Оцініть додаток")
                .setPositiveButton("Добре", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        CustomRatingDialog();
                    }
                })
                .setNegativeButton("Нагадати", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNeutralButton("Не нагадувати", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDisplayDialog = getPreferences(Context.MODE_PRIVATE);
                        editor = mDisplayDialog.edit();
                        editor.putString("DialogManager", "no");
                        editor.apply();
                    }
                });
        dialog.show();
    }


    private void CustomRatingDialog() {
        final AlertDialog.Builder customDialog = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.custom_rating_dialog, null);
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar1);

        customDialog.setView(view);
        final AlertDialog dialog = customDialog.create();
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating <= 3) {
                    SendMailDialog();
                    dialog.dismiss();
                } else {
                    mDisplayDialog = getPreferences(Context.MODE_PRIVATE);
                    editor = mDisplayDialog.edit();
                    editor.putString("DialogManager", "no");
                    editor.apply();
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                    dialog.dismiss();

                }
            }
        });

        dialog.show();
    }

    private void SendMailDialog() {
        AlertDialog.Builder sendBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.send_mail_for_as, null);
        final EditText sendMailText = (EditText) view.findViewById(R.id.tv_mailText);
        final EditText tvMailUser = (EditText) view.findViewById(R.id.tv_mailUser);
        sendBuilder.setView(view);
        sendBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"andrij2198@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, tvMailUser.getText().toString());
                i.putExtra(Intent.EXTRA_TEXT, sendMailText.getText().toString());
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Container.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sendBuilder.setNegativeButton("No, Thanks", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = sendBuilder.create();
        dialog.show();

    }




    public void GetNotReadMess() {
        SharedPreferences preferences = getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");
        try {
            AndroidNetworking.get(link + "?r=rest/get-count-unreaded-messages&token&token=" + token)
                    //   AndroidNetworking.get("https://demo2018.smart-school.com.ua/frontend/web/?r=rest/get-count-unreaded-messages&token=v4MEUSjFxGYGGamTj6_9tGp3v4bWYnLc")
                    // .addPathParameter("link", link)
                    // .addPathParameter("token", token)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String notRead = response.getString("result");
                                if (notRead.equalsIgnoreCase("0")) {
                                    tvNotReadMess.setVisibility(View.INVISIBLE);
                                    tvCountMess.setVisibility(View.INVISIBLE);

                                } else {
                                    tvNotReadMess.setVisibility(View.VISIBLE);
                                    tvNotReadMess.setText(notRead);
                                    tvCountMess.setVisibility(View.VISIBLE);
                                }
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void GetStudent() {
        SharedPreferences preferences = getSharedPreferences("linkServer", MODE_PRIVATE);
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
                            mRealm.beginTransaction();
                            mRealm.copyToRealmOrUpdate(response);
                            mRealm.commitTransaction();
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

    public void DrawerClose() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(Gravity.LEFT);
            // drawer.closeDrawer(GravityCompat.START);

            //  super.onBackPressed();
        }
    }

/*
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
    */

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }
}
