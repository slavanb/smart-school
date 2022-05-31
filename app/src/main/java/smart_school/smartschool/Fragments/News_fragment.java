package smart_school.smartschool.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import smart_school.smartschool.Activity.Container;
import smart_school.smartschool.Adapters.Adapter_list_news;
import smart_school.smartschool.Model.List_news;

import smart_school.smartschool.R;

import static android.content.Context.MODE_PRIVATE;

public class News_fragment extends Fragment {

    @BindView(R.id.rec_list_news)
    RecyclerView recListView;
    private View view;
    private Adapter_list_news adapterListNews;
    private Realm mRealm;
    private List<List_news> list_news = new ArrayList<>();

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout refreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.news_fragment, container, false);
        }
        ButterKnife.bind(this, view);
        mRealm = Realm.getDefaultInstance();

        recListView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapterListNews = new Adapter_list_news(getActivity(), list_news,News_fragment.this);
        recListView.setAdapter(adapterListNews);
        list_news = mRealm.where(List_news.class).findAll();
        if (isNetworkAvailable(getActivity())) {
            GetNews();
        } else {
            if (list_news != null||list_news.size()!=0) {
                adapterListNews = new Adapter_list_news(getActivity(), list_news,News_fragment.this);
                recListView.setAdapter(adapterListNews);
            } else {
                Toast.makeText(getActivity(), "Подключитесь к интернет...", Toast.LENGTH_SHORT).show();
            }
        }

        refreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetNews();
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                }
                return false;
            }
        });
        ((Container)getActivity()).GetNotReadMess();


        return view;
    }

    private void GetNews() {
        SharedPreferences preferences = getActivity().getSharedPreferences("linkServer", MODE_PRIVATE);
        String link = preferences.getString("link", "");
        String token = preferences.getString("token", "");
        String userId=preferences.getString("userId", "");
        try {
            AndroidNetworking.get("{link}index.php?r=rest/news&token={token}&{device_id}")
                    .addPathParameter("link", link)
                    .addPathParameter("token", token)
                    .addPathParameter("device_id",userId)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsObjectList(List_news.class, new ParsedRequestListener<List<List_news>>() {
                        @Override
                        public void onResponse(List<List_news> response) {
                            adapterListNews = new Adapter_list_news(getActivity(), response,News_fragment.this);
                            recListView.setAdapter(adapterListNews);
                            adapterListNews.notifyDataSetChanged();
                            mRealm.beginTransaction();
                            mRealm.copyToRealmOrUpdate(response);
                            mRealm.commitTransaction();
                            refreshLayout.setRefreshing(false);
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


    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}
