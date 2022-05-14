
package com.yashjain.orderpizza.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.yashjain.orderpizza.model.Crust;

public class PizzaDataList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("isVeg")
    @Expose
    private Boolean isVeg;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("defaultCrust")
    @Expose
    private Integer defaultCrust;
    @SerializedName("crusts")
    @Expose
    private List<Crust> crusts = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsVeg() {
        return isVeg;
    }

    public void setIsVeg(Boolean isVeg) {
        this.isVeg = isVeg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDefaultCrust() {
        return defaultCrust;
    }

    public void setDefaultCrust(Integer defaultCrust) {
        this.defaultCrust = defaultCrust;
    }

    public List<Crust> getCrusts() {
        return crusts;
    }

    public void setCrusts(List<Crust> crusts) {
        this.crusts = crusts;
    }

}
