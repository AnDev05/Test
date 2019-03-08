package com.simpledev.idog.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BreedResponse extends BaseResponse implements Parcelable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private List<String> listBreedImages;

    protected BreedResponse(Parcel in) {
        status = in.readString();
        listBreedImages = in.createStringArrayList();
    }

    public static final Creator<BreedResponse> CREATOR = new Creator<BreedResponse>() {
        @Override
        public BreedResponse createFromParcel(Parcel in) {
            return new BreedResponse(in);
        }

        @Override
        public BreedResponse[] newArray(int size) {
            return new BreedResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeStringList(listBreedImages);
    }
}
