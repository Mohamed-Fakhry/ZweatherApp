package zmabrook.com.zweatherapp.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zMabrook on 27/01/18.
 */

public class AutoComplete {


    public class AutoCompleteResult {

        @SerializedName("predictions")
        @Expose
        private ArrayList<Prediction> predictions = null;
        @SerializedName("status")
        @Expose
        private String status;

        public ArrayList<Prediction> getPredictions() {
            return predictions;
        }

        public void setPredictions(ArrayList<Prediction> predictions) {
            this.predictions = predictions;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }



    public class Prediction {

        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("place_id")
        @Expose
        private String placeId;
        @SerializedName("reference")
        @Expose
        private String reference;
        @SerializedName("structured_formatting")
        @Expose
        private StructuredFormatting structuredFormatting;
        @SerializedName("types")
        @Expose
        private List<String> types = null;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public StructuredFormatting getStructuredFormatting() {
            return structuredFormatting;
        }

        public void setStructuredFormatting(StructuredFormatting structuredFormatting) {
            this.structuredFormatting = structuredFormatting;
        }


        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }


    }

    }


