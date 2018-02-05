package zmabrook.com.zweatherapp.Details.Extras;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import zmabrook.com.zweatherapp.Details.DetailsActivity;
import zmabrook.com.zweatherapp.Entities.WeatherItem;
import zmabrook.com.zweatherapp.R;

import static zmabrook.com.zweatherapp.Configs.CommonConstants.CITY_ID;
import static zmabrook.com.zweatherapp.Configs.CommonConstants.ICONS_BASIC_URL;

/**
 * Created by zMabrook on 27/01/18.
 */


public class DetailsRecyclerViewAdapter extends RecyclerView.Adapter<DetailsRecyclerViewAdapter.ItemViewHolder> {
    private final int MAX_SIZE = 5;

    ArrayList<WeatherItem> itemsArrayList;
    Context mcontext;

    public DetailsRecyclerViewAdapter(ArrayList<WeatherItem> itemsArrayList, Context context) {
        this.itemsArrayList = itemsArrayList;
        mcontext = context;
    }

    @Override
    public DetailsRecyclerViewAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_recyclerview_item, parent, false);
        return new DetailsRecyclerViewAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailsRecyclerViewAdapter.ItemViewHolder holder, final int position) {
        holder.emptyViewHolder();
        try {
        String[] parts = itemsArrayList.get(position).getDtTxt().split(" ");
        SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;

            date = inFormat.parse(parts[0]);

        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
        String goal = outFormat.format(date);

            holder.nameTextView.setText(goal+"\n"+parts[1]);

        } catch (ParseException e) {
            e.printStackTrace();
            holder.nameTextView.setText(itemsArrayList.get(position).getDtTxt());
        }
        holder.descriptionTextView.setText(itemsArrayList.get(position).getWeather().get(0).getDescription());
        holder.tempratureTextView.setText(String.valueOf(itemsArrayList.get(position).getMain().getTarget().getTemp())+"°");
        Picasso.with(mcontext)
                .load(ICONS_BASIC_URL+itemsArrayList.get(position).getWeather().get(0).getIcon()+".png")
                .into(holder.iconImageview);
    }

    @Override
    public int getItemCount() {
        return (itemsArrayList==null) ? 0: itemsArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cityNameTextView)
        TextView nameTextView;
        @BindView(R.id.descriptionTextView)TextView descriptionTextView;
        @BindView(R.id.tempratureTextView)TextView tempratureTextView;
        @BindView(R.id.iconImageview)ImageView iconImageview;
        @BindView(R.id.itemCardview)CardView itemCardview;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void emptyViewHolder(){
            nameTextView.setText("");
            descriptionTextView.setText("");
            tempratureTextView.setText("");
        }
    }

    public void addItem(WeatherItem item){
        if (itemsArrayList.size()<MAX_SIZE){
            itemsArrayList.add(item);
        }else {
            Toast.makeText(mcontext, R.string.max_number_of_cities,Toast.LENGTH_LONG).show();
        }
    }


    private void startDetailsActivity(String id){
        Intent intent = new Intent(mcontext, DetailsActivity.class);
        intent.putExtra(CITY_ID,id);
        mcontext.startActivity(intent);
    }

}

