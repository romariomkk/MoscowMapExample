package com.romariomkk.moscowmapexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.romariomkk.moscowmapexample.R;
import com.romariomkk.moscowmapexample.ui.CustomStationItem;
import com.romariomkk.moscowmapexample.model.StationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romariomkk on 29.05.2017.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<StationModel> stationList;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClicked(StationModel station, int pos);
    }

    public RecyclerAdapter(Context c, ArrayList<StationModel> stations) {
        context = c;
        stationList = stations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(R.layout.station_list_item, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StationModel station = stationList.get(position);
        holder.setModel(station, position);
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        clickListener = listener;
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        private CustomStationItem stationItem;

        public ViewHolder(View itemView) {
            super(itemView);
            stationItem = (CustomStationItem) itemView.findViewById(R.id.stationItem);
        }

        public void setModel(StationModel station, int position) {
            stationItem.setStation(station);

            itemView.setOnClickListener(view -> {
                if (clickListener != null) {
                    clickListener.onItemClicked(station, position);
                }
            });
        }
    }
}