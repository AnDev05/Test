package com.simpledev.idog.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RandomDogResponse extends BaseResponse {
    @SerializedName("url")
    @Expose
    private String url;
}
