package zmabrook.com.zweatherapp.Entities;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

@Entity
public class WeatherItem extends BaseEntity implements Parcelable {

    @Id
    public long databaseId;

    @SerializedName("coord")
    @Expose
    private ToOne<Coord> coord;
    @SerializedName("sys")
    @Expose
    private ToOne<Sys> sys;
    @SerializedName("weather")
    @Expose
    @Backlink
    private ToMany<Weather> weather = null;
    @SerializedName("main")
    @Expose
    private ToOne<Main> main;
    @SerializedName("wind")
    @Expose
    private ToOne<Wind> wind;
    @SerializedName("rain")
    @Expose
    private ToOne<Rain> rain;
    @SerializedName("clouds")
    @Expose
    private ToOne<Clouds> clouds;
    @SerializedName("dt")
    @Expose
    private double dt;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private double cod;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    public WeatherItem() {
    }

    public ToOne<Coord> getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord.setTarget(coord);
    }

    public ToOne<Sys> getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys.setTarget(sys);
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weathers) {
        this.weather.addAll(weathers);
    }

    public ToOne<Main> getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main.setTarget(main);
    }

    public ToOne<Wind> getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind.setTarget(wind);
    }

    public ToOne<Rain> getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain.setTarget(rain);
    }

    public ToOne<Clouds> getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds.setTarget(clouds);
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCod() {
        return cod;
    }

    public void setCod(double cod) {
        this.cod = cod;
    }


    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.databaseId);
        dest.writeSerializable(this.coord);
        dest.writeSerializable(this.sys);
//        dest.writeTypedList(this.weather);
        dest.writeSerializable(this.main);
        dest.writeSerializable(this.wind);
        dest.writeSerializable(this.rain);
        dest.writeSerializable(this.clouds);
        dest.writeDouble(this.dt);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeDouble(this.cod);
        dest.writeString(this.dtTxt);
    }

    protected WeatherItem(Parcel in) {
        this.databaseId = in.readLong();
        this.coord = (ToOne<Coord>) in.readSerializable();
        this.sys = (ToOne<Sys>) in.readSerializable();
//        this.weather = in.createTypedArrayList(Weather.CREATOR);
        this.main = (ToOne<Main>) in.readSerializable();
        this.wind = (ToOne<Wind>) in.readSerializable();
        this.rain = (ToOne<Rain>) in.readSerializable();
        this.clouds = (ToOne<Clouds>) in.readSerializable();
        this.dt = in.readDouble();
        this.id = in.readString();
        this.name = in.readString();
        this.cod = in.readDouble();
        this.dtTxt = in.readString();
    }

    public static final Creator<WeatherItem> CREATOR = new Creator<WeatherItem>() {
        @Override
        public WeatherItem createFromParcel(Parcel source) {
            return new WeatherItem(source);
        }

        @Override
        public WeatherItem[] newArray(int size) {
            return new WeatherItem[size];
        }
    };
}