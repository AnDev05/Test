package com.simpledev.idog.interactor.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import lombok.Getter;
import lombok.Setter;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "breed")
@Getter
@Setter
public class Breed extends BaseModel implements Parcelable {

    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "breedName")
    private String breedName;
    @Property(nameInDb = "breedApi")
    private String breadApi;
    @Property(nameInDb = "imageUrl")
    private String imageUrl;
    @Property(nameInDb = "number")
    private Long numberOfImage;
    @Property(nameInDb = "firebaseId")
    private String firebaseId;
    @Property(nameInDb = "baseBreed")
    private String baseBreed;
    @Property(nameInDb = "subBreed")
    private String subBreed;
    @Generated(hash = 1419761915)
    public Breed(Long id, String breedName, String breadApi, String imageUrl,
            Long numberOfImage, String firebaseId, String baseBreed,
            String subBreed) {
        this.id = id;
        this.breedName = breedName;
        this.breadApi = breadApi;
        this.imageUrl = imageUrl;
        this.numberOfImage = numberOfImage;
        this.firebaseId = firebaseId;
        this.baseBreed = baseBreed;
        this.subBreed = subBreed;
    }
    @Generated(hash = 1184883001)
    public Breed() {
    }

    protected Breed(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        breedName = in.readString();
        breadApi = in.readString();
        imageUrl = in.readString();
        if (in.readByte() == 0) {
            numberOfImage = null;
        } else {
            numberOfImage = in.readLong();
        }
        firebaseId = in.readString();
        baseBreed = in.readString();
        subBreed = in.readString();
    }

    public static final Creator<Breed> CREATOR = new Creator<Breed>() {
        @Override
        public Breed createFromParcel(Parcel in) {
            return new Breed(in);
        }

        @Override
        public Breed[] newArray(int size) {
            return new Breed[size];
        }
    };

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBreedName() {
        return this.breedName;
    }
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }
    public String getBreadApi() {
        return this.breadApi;
    }
    public void setBreadApi(String breadApi) {
        this.breadApi = breadApi;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Long getNumberOfImage() {
        return this.numberOfImage;
    }
    public void setNumberOfImage(Long numberOfImage) {
        this.numberOfImage = numberOfImage;
    }
    public String getFirebaseId() {
        return this.firebaseId;
    }
    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }
    public String getBaseBreed() {
        return this.baseBreed;
    }
    public void setBaseBreed(String baseBreed) {
        this.baseBreed = baseBreed;
    }
    public String getSubBreed() {
        return this.subBreed;
    }
    public void setSubBreed(String subBreed) {
        this.subBreed = subBreed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(breedName);
        dest.writeString(breadApi);
        dest.writeString(imageUrl);
        if (numberOfImage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(numberOfImage);
        }
        dest.writeString(firebaseId);
        dest.writeString(baseBreed);
        dest.writeString(subBreed);
    }
}