package zmabrook.com.zweatherapp.Home.Extras;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import zmabrook.com.zweatherapp.Configs.CommonConstants;
import zmabrook.com.zweatherapp.Details.DetailsActivity;
import zmabrook.com.zweatherapp.Entities.WeatherItem;
import zmabrook.com.zweatherapp.Listeners.AddCityListener;
import zmabrook.com.zweatherapp.R;
import zmabrook.com.zweatherapp.Utils.ConnectionUtil;

import static zmabrook.com.zweatherapp.Configs.CommonConstants.ADD_CITY_LISTENER;
import static zmabrook.com.zweatherapp.Configs.CommonConstants.CITY_ID;
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
                removeFromCache(itemsArrayList.get(position));
                itemsArrayList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.itemCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDetailsActivity(String.valueOf(itemsArrayList.get(position).getId()));
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

    public void addItemWithToast(WeatherItem item){
        if (!IsAlreadyAdded(item)){
            if (itemsArrayList.size()<MAX_SIZE){
                itemsArrayList.add(item);
                addTocache(item);
            }else {
                Toast.makeText(mcontext, R.string.max_number_of_cities,Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(mcontext, R.string.city_already_added,Toast.LENGTH_LONG).show();
        }

    }

    public void addItemWithoutToast(WeatherItem item){
        if (!IsAlreadyAdded(item)){
            if (itemsArrayList.size()<MAX_SIZE){
                itemsArrayList.add(item);
                addTocache(item);
            }else {
                Toast.makeText(mcontext, R.string.max_number_of_cities,Toast.LENGTH_LONG).show();
            }
        }

    }




    private void startDetailsActivity(String id){
        if (ConnectionUtil.isConnected(mcontext)) {
            Intent intent = new Intent(mcontext, DetailsActivity.class);
            intent.putExtra(CITY_ID, id);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mcontext.startActivity(intent);
        }
    }

    private  boolean IsAlreadyAdded(WeatherItem item){
        boolean flag = false;
        for (int i=0 ; i < itemsArrayList.size() ; i++){
            if (itemsArrayList.get(i).getId().equals(item.getId()))
                flag=true;
        }
        return flag;
    }


    private void addTocache(WeatherItem item){
        SharedPreferences pref = mcontext.getApplicationContext().getSharedPreferences(CommonConstants.SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();

        String result = pref.getString(CommonConstants.ID_LIST, "");
        if (result.equals("")) {
            result=item.getId();
        }else {
            result=result+","+item.getId();
        }
        editor.putString(CommonConstants.ID_LIST,result);
        editor.commit();

    }

    private void removeFromCache(WeatherItem item){
        SharedPreferences pref = mcontext.getApplicationContext().getSharedPreferences(CommonConstants.SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        String result = pref.getString(CommonConstants.ID_LIST, "");

        if (result.contains(item.getId())) {
            String[] parts = result.split(",");
            for (int i=0 ; i < parts.length ; i++){
                if (parts[i].equals(item.getId())){
                    parts=ArrayUtils.remove(parts,i);
                    break;
                }

            }
            result = Arrays.stream(parts).collect(Collectors.joining(","));
            editor.putString(CommonConstants.ID_LIST,result);
            editor.commit();
        }
        /*if (result.contains(item.getId())){
            if (result.contains(","+item.getId())){
                result.replace(","+item.getId(),"");
            }else{
                result.replace(item.getId(),"");
            }
            editor.putString(CommonConstants.ID_LIST,result);
            editor.commit();
        }*/
    }
}
