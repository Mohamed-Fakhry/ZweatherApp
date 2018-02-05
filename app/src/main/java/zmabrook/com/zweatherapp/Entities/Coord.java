package zmabrook.com.zweatherapp.Entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Coord extends BaseEntity implements Parcelable
{
    @Id
    public long id;

    @SerializedName("lon")
    @Expose
    private double lon;
    @SerializedName("lat")
    @Expose
    private double lat;
    public final static Parcelable.Creator<Coord> CREATOR = new Creator<Coord>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Coord createFromParcel(Parcel in) {
            return new Coord(in);
        }

        public Coord[] newArray(int size) {
            return (new Coord[size]);
        }

    }
            ;

    protected Coord(Parcel in) {
        this.lon = ((int) in.readValue((int.class.getClassLoader())));
        this.lat = ((int) in.readValue((int.class.getClassLoader())));
    }

    public Coord() {
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lon);
        dest.writeValue(lat);
    }

    public int describeContents() {
        return 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}