package com.simpledev.idog.interactor.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import lombok.Getter;
import lombok.Setter;
import org.greenrobot.greendao.annotation.Generated;

@Getter
@Setter
@Entity(nameInDb = "random_dog")
public class RandomDog extends BaseModel implements Parcelable {

    @Property(nameInDb = "url")
    private String url;

    /**
     * type 0: video
     * type 1: image
     */
    @Property(nameInDb = "type")
    private int type;

    @Id(autoincrement = true)
    private Long id;

    protected RandomDog(Parcel in) {
        url = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
    }

    @Generated(hash = 1532884629)
    public RandomDog(String url, int type, Long id) {
        this.url = url;
        this.type = type;
        this.id = id;
    }

    @Generated(hash = 1602863416)
    public RandomDog() {
    }

    public static final Creator<RandomDog> CREATOR = new Creator<RandomDog>() {
        @Override
        public RandomDog createFromParcel(Parcel in) {
            return new RandomDog(in);
        }

        @Override
        public RandomDog[] newArray(int size) {
            return new RandomDog[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
