package infotech.wifi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class WifiAdapter extends BaseAdapter {
    Context mContext;
    List<String> wifis;

    public WifiAdapter(Context mContext, List<String> wifi) {
        this.mContext = mContext;
        this.wifis = wifi;
    }

    @Override
    public int getCount() {
        return wifis.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.listview_row,parent,false);
        TextView wifiValue = v.findViewById(R.id.wifi_value);
        wifiValue.setText(wifis.get(position));

        return v;
    }

}
