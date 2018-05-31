package com.example.sirth.mybakingappnanod.networking;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CakePOJO implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();
    @SerializedName("steps")
    @Expose
    private List<Step> steps = new ArrayList<Step>();
    @SerializedName("servings")
    @Expose
    private Integer servings;
    @SerializedName("image")
    @Expose
    private String image;

    public final  Parcelable.Creator<CakePOJO> CREATOR = new Creator<CakePOJO>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CakePOJO createFromParcel(Parcel in) {
            return new CakePOJO(in);
        }

        public CakePOJO[] newArray(int size) {
            return (new CakePOJO[size]);
        }

    };

    protected CakePOJO(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.ingredients, (com.example.sirth.mybakingappnanod.networking.CakePOJO.Ingredient.class.getClassLoader()));
        in.readList(this.steps, (com.example.sirth.mybakingappnanod.networking.CakePOJO.Step.class.getClassLoader()));
        this.servings = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CakePOJO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CakePOJO withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CakePOJO withName(String name) {
        this.name = name;
        return this;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public CakePOJO withIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public CakePOJO withSteps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public CakePOJO withServings(Integer servings) {
        this.servings = servings;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CakePOJO withImage(String image) {
        this.image = image;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeList(ingredients);
        dest.writeList(steps);
        dest.writeValue(servings);
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }

    public class Ingredient implements Parcelable
    {

        @SerializedName("quantity")
        @Expose
        private Double quantity;
        @SerializedName("measure")
        @Expose
        private String measure;
        @SerializedName("ingredient")
        @Expose
        private String ingredient;
        public final  Parcelable.Creator<Ingredient> CREATOR = new Creator<Ingredient>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Ingredient createFromParcel(Parcel in) {
                return new Ingredient(in);
            }

            public Ingredient[] newArray(int size) {
                return (new Ingredient[size]);
            }

        }
                ;

        protected Ingredient(Parcel in) {
            this.quantity = ((Double) in.readValue((Integer.class.getClassLoader())));
            this.measure = ((String) in.readValue((String.class.getClassLoader())));
            this.ingredient = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Ingredient() {
        }

        public Double getQuantity() {
            return quantity;
        }

        public void setQuantity(Double quantity) {
            this.quantity = quantity;
        }

        public Ingredient withQuantity(Double quantity) {
            this.quantity = quantity;
            return this;
        }

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        public Ingredient withMeasure(String measure) {
            this.measure = measure;
            return this;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }

        public Ingredient withIngredient(String ingredient) {
            this.ingredient = ingredient;
            return this;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(quantity);
            dest.writeValue(measure);
            dest.writeValue(ingredient);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class Step implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("shortDescription")
        @Expose
        private String shortDescription;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("videoURL")
        @Expose
        private String videoURL;
        @SerializedName("thumbnailURL")
        @Expose
        private String thumbnailURL;
        public final  Parcelable.Creator<Step> CREATOR = new Creator<Step>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Step createFromParcel(Parcel in) {
                return new Step(in);
            }

            public Step[] newArray(int size) {
                return (new Step[size]);
            }

        }
                ;

        protected Step(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.shortDescription = ((String) in.readValue((String.class.getClassLoader())));
            this.description = ((String) in.readValue((String.class.getClassLoader())));
            this.videoURL = ((String) in.readValue((String.class.getClassLoader())));
            this.thumbnailURL = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Step() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Step withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public Step withShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Step withDescription(String description) {
            this.description = description;
            return this;
        }

        public String getVideoURL() {
            return videoURL;
        }

        public void setVideoURL(String videoURL) {
            this.videoURL = videoURL;
        }

        public Step withVideoURL(String videoURL) {
            this.videoURL = videoURL;
            return this;
        }

        public String getThumbnailURL() {
            return thumbnailURL;
        }

        public void setThumbnailURL(String thumbnailURL) {
            this.thumbnailURL = thumbnailURL;
        }

        public Step withThumbnailURL(String thumbnailURL) {
            this.thumbnailURL = thumbnailURL;
            return this;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(shortDescription);
            dest.writeValue(description);
            dest.writeValue(videoURL);
            dest.writeValue(thumbnailURL);
        }

        public int describeContents() {
            return 0;
        }

    }

}