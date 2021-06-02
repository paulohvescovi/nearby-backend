package br.com.neartech.nearby.luan;

import java.io.Serializable;

public class FeatureApi implements Serializable {

    private FeatureApiAttribute attributes;
    private FeatureApiGeometry geometry;

    public FeatureApi() {
    }

    public FeatureApi(FeatureApiAttribute attributes, FeatureApiGeometry geometry) {
        this.attributes = attributes;
        this.geometry = geometry;
    }

    public FeatureApiAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(FeatureApiAttribute attributes) {
        this.attributes = attributes;
    }

    public FeatureApiGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(FeatureApiGeometry geometry) {
        this.geometry = geometry;
    }
}
