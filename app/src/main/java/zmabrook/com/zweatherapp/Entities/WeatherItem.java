package zmabrook.com.zweatherapp.Entities;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherItem extends BaseEntity implements Parcelable
{

    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("rain")
    @Expose
    private Rain rain;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private double dt;
    @SerializedName("id")
    @Expose
    private double id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private double cod;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;
    public final static Parcelable.Creator<WeatherItem> CREATOR = new Creator<WeatherItem>() {


        @SuppressWarnings({
                "unchecked"
        })
        public WeatherItem createFromParcel(Parcel in) {
            return new WeatherItem(in);
        }

        public WeatherItem[] newArray(int size) {
            return (new WeatherItem[size]);
        }

    }
            ;

    protected WeatherItem(Parcel in) {
        this.coord = ((Coord) in.readValue((Coord.class.getClassLoader())));
        this.sys = ((Sys) in.readValue((Sys.class.getClassLoader())));
        in.readList(this.weather, (zmabrook.com.zweatherapp.Entities.Weather.class.getClassLoader()));
        this.main = ((Main) in.readValue((Main.class.getClassLoader())));
        this.wind = ((Wind) in.readValue((Wind.class.getClassLoader())));
        this.rain = ((Rain) in.readValue((Rain.class.getClassLoader())));
        this.clouds = ((Clouds) in.readValue((Clouds.class.getClassLoader())));
        this.dt = ((int) in.readValue((int.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.cod = ((int) in.readValue((int.class.getClassLoader())));
        this.dtTxt = ((String) in.readValue((String.class.getClassLoader())));

    }

    public WeatherItem() {
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(coord);
        dest.writeValue(sys);
        dest.writeList(weather);
        dest.writeValue(main);
        dest.writeValue(wind);
        dest.writeValue(rain);
        dest.writeValue(clouds);
        dest.writeValue(dt);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(cod);
        dest.writeValue(dtTxt);

    }

    public int describeContents() {
        return 0;
    }

}