package smart_school.smartschool.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import smart_school.smartschool.Model.School_data;
import smart_school.smartschool.R;

public class Adapter_spinner_school extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<School_data> listSpinNewTask=new ArrayList<>();
    private Context mContext;

    public Adapter_spinner_school(Context context, List<School_data> listSpinNewTask) {
        this.listSpinNewTask = listSpinNewTask;
        this.mContext = context;
        mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return listSpinNewTask.size();
    }

    @Override
    public Object getItem(int position) {
        return listSpinNewTask.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if (view==null){
            view=mInflater.inflate(R.layout.spinner_selected_item,null);

        }

        School_data item=listSpinNewTask.get(position);
        TextView textSpin=(TextView)view.findViewById(R.id.textView2);
        textSpin.setText(item.getServerName());
        if (position==0){
            textSpin.setTextColor(Color.parseColor("#879399"));
        }else {
            textSpin.setTextColor(Color.BLACK);
        }



        return view;
    }
}

