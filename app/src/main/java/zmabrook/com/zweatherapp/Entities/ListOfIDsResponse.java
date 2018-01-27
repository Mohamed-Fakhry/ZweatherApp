package zmabrook.com.zweatherapp.Entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListOfIDsResponse extends BaseEntity implements Parcelable
{

    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private ArrayList<WeatherItem> list = null;
    public final static Parcelable.Creator<ListOfIDsResponse> CREATOR = new Creator<ListOfIDsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ListOfIDsResponse createFromParcel(Parcel in) {
            return new ListOfIDsResponse(in);
        }

        public ListOfIDsResponse[] newArray(int size) {
            return (new ListOfIDsResponse[size]);
        }

    }
            ;

    protected ListOfIDsResponse(Parcel in) {
        this.cnt = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.list, (WeatherItem.class.getClassLoader()));
    }

    public ListOfIDsResponse() {
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
        dest.writeValue(cnt);
        dest.writeList(list);
    }

    public int describeContents() {
        return 0;
    }

}
