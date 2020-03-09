package com.e.weddingprogram.task_results;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MapTaskResult {
    com.google.maps.model.LatLng receptionLatLong=null;
    com.google.maps.model.LatLng churchLatLong=null;
    List<LatLng> churchLocationPath=null;
    List<LatLng> receptionLocationPath=null;

    public MapTaskResult(com.google.maps.model.LatLng receptionLatLong, com.google.maps.model.LatLng churchLatLong, List<LatLng> churchLocationPath, List<LatLng> receptionLocationPath) {
        this.receptionLatLong = receptionLatLong;
        this.churchLatLong = churchLatLong;
        this.churchLocationPath = churchLocationPath;
        this.receptionLocationPath = receptionLocationPath;
    }

    public com.google.maps.model.LatLng getReceptionLatLong() {
        return receptionLatLong;
    }

    public void setReceptionLatLong(com.google.maps.model.LatLng receptionLatLong) {
        this.receptionLatLong = receptionLatLong;
    }

    public com.google.maps.model.LatLng getChurchLatLong() {
        return churchLatLong;
    }

    public void setChurchLatLong(com.google.maps.model.LatLng churchLatLong) {
        this.churchLatLong = churchLatLong;
    }

    public List<LatLng> getChurchLocationPath() {
        return churchLocationPath;
    }

    public void setChurchLocationPath(List<LatLng> churchLocationPath) {
        this.churchLocationPath = churchLocationPath;
    }

    public List<LatLng> getReceptionLocationPath() {
        return receptionLocationPath;
    }

    public void setReceptionLocationPath(List<LatLng> receptionLocationPath) {
        this.receptionLocationPath = receptionLocationPath;
    }
}
