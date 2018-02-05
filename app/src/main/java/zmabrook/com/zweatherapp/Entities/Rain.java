
package zmabrook.com.zweatherapp.Entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Rain extends BaseEntity implements Parcelable {

    @Id
    public long id;

    @SerializedName("3h")
    @Expose
    private double h;
    public final static Parcelable.Creator<Rain> CREATOR = new Creator<Rain>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Rain createFromParcel(Parcel in) {
            return new Rain(in);
        }

        public Rain[] newArray(int size) {
            return (new Rain[size]);
        }

    };

    protected Rain(Parcel in) {
        this.h = ((int) in.readValue((int.class.getClassLoader())));
    }

    public Rain() {
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(h);
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
