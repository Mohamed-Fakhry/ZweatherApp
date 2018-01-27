package zmabrook.com.zweatherapp.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zMabrook on 27/01/18.
 */

public class StructuredFormatting implements Parcelable,SearchSuggestion
{

    @SerializedName("main_text")
    @Expose
    private String mainText;

    @SerializedName("secondary_text")
    @Expose
    private String secondaryText;
    public final static Parcelable.Creator<StructuredFormatting> CREATOR = new Creator<StructuredFormatting>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StructuredFormatting createFromParcel(Parcel in) {
            return new StructuredFormatting(in);
        }

        public StructuredFormatting[] newArray(int size) {
            return (new StructuredFormatting[size]);
        }

    }
            ;

    protected StructuredFormatting(Parcel in) {
        this.mainText = ((String) in.readValue((String.class.getClassLoader())));
        this.secondaryText = ((String) in.readValue((String.class.getClassLoader())));
    }

    public StructuredFormatting() {
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }


    public String getSecondaryText() {
        return secondaryText;
    }

    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mainText);
        dest.writeValue(secondaryText);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String getBody() {
        String[] parts = mainText.split(" ");
        return parts[0];
    }
}
