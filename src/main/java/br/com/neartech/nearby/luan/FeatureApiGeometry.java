package br.com.neartech.nearby.luan;

import java.io.Serializable;

public class FeatureApiGeometry implements Serializable {

    private Double x;
    private Double y;

    public FeatureApiGeometry() {
    }

    public FeatureApiGeometry(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
