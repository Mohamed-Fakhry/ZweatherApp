package zmabrook.com.zweatherapp.Entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FiveDaysResponse implements Parcelable {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private ArrayList<WeatherItem> list = null;
    public final static Parcelable.Creator<FiveDaysResponse> CREATOR = new Creator<FiveDaysResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public FiveDaysResponse createFromParcel(Parcel in) {
            return new FiveDaysResponse(in);
        }

        public FiveDaysResponse[] newArray(int size) {
            return (new FiveDaysResponse[size]);
        }

    };

    protected FiveDaysResponse(Parcel in) {
        this.city = ((City) in.readValue((City.class.getClassLoader())));
        this.coord = ((Coord) in.readValue((Coord.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.cod = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((double) in.readValue((double.class.getClassLoader())));
        this.cnt = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.list, (zmabrook.com.zweatherapp.Entities.WeatherItem.class.getClassLoader()));
    }

    public FiveDaysResponse() {
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public ArrayList<WeatherItem> getList() {
        return list;
    }

    public void setList(ArrayList<WeatherItem> list) {
        this.list = list;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(city);
        dest.writeValue(coord);
        dest.writeValue(country);
        dest.writeValue(cod);
        dest.writeValue(message);
        dest.writeValue(cnt);
        dest.writeList(list);
    }

    public int describeContents() {
        return 0;
    }
}