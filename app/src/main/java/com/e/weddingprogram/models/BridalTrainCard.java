package com.e.weddingprogram.models;

public class BridalTrainCard {
    int imageId;
    String bridalTrainName;

    public BridalTrainCard(int imageId, String bridalTrainName) {
        this.imageId = imageId;
        this.bridalTrainName = bridalTrainName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getBridalTrainName() {
        return bridalTrainName;
    }

    public void setBridalTrainName(String bridalTrainName) {
        this.bridalTrainName = bridalTrainName;
    }
}
