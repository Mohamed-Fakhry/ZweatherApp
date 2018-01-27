package zmabrook.com.zweatherapp.Home.Extras;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import zmabrook.com.zweatherapp.Entities.WeatherItem;
import zmabrook.com.zweatherapp.R;

import static zmabrook.com.zweatherapp.Configs.CommonConstants.ICONS_BASIC_URL;

/**
 * Created by zMabrook on 27/01/18.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ItemViewHolder> {
    private final int MAX_SIZE = 5;

    ArrayList<WeatherItem> itemsArrayList;
    Context mcontext;

    public HomeRecyclerViewAdapter(ArrayList<WeatherItem> itemsArrayList, Context context) {
        this.itemsArrayList = itemsArrayList;
        mcontext = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.emptyViewHolder();
        holder.nameTextView.setText(itemsArrayList.get(position).getName());
        holder.descriptionTextView.setText(itemsArrayList.get(position).getWeather().get(0).getDescription());
        holder.tempratureTextView.setText(String.valueOf(itemsArrayList.get(position).getMain().getTemp())+"°");
        Picasso.with(mcontext)
                .load(ICONS_BASIC_URL+itemsArrayList.get(position).getWeather().get(0).getIcon()+".png")
                .into(holder.iconImageview);
        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsArrayList.remove(position);
                notifyDataSetChanged();
                //TODO: remove from cache
            }
        });
    }

    @Override
    public int getItemCount() {
        return (itemsArrayList==null) ? 0: itemsArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cityNameTextView) TextView nameTextView;
        @BindView(R.id.descriptionTextView)TextView descriptionTextView;
        @BindView(R.id.tempratureTextView)TextView tempratureTextView;
        @BindView(R.id.deleteCityImageView)ImageView deleteImageView;
        @BindView(R.id.iconImageview)ImageView iconImageview;

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


}