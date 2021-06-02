package br.com.neartech.nearby.luan;

import java.io.Serializable;

public class FeatureApiAttribute implements Serializable {

    private Integer OBJECTID;
    private String NUM_SEQ_GEO;
    private String SITUACAO;
    private Double LONGITUDE;
    private Double LATITUDE;

    public FeatureApiAttribute() {
    }

    public FeatureApiAttribute(Integer OBJECTID, String NUM_SEQ_GEO, String SITUACAO, Double LONGITUDE, Double LATITUDE) {
        this.OBJECTID = OBJECTID;
        this.NUM_SEQ_GEO = NUM_SEQ_GEO;
        this.SITUACAO = SITUACAO;
        this.LONGITUDE = LONGITUDE;
        this.LATITUDE = LATITUDE;
    }

    public Integer getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(Integer OBJECTID) {
        this.OBJECTID = OBJECTID;
    }

    public String getNUM_SEQ_GEO() {
        return NUM_SEQ_GEO;
    }

    public void setNUM_SEQ_GEO(String NUM_SEQ_GEO) {
        this.NUM_SEQ_GEO = NUM_SEQ_GEO;
    }

    public String getSITUACAO() {
        return SITUACAO;
    }

    public void setSITUACAO(String SITUACAO) {
        this.SITUACAO = SITUACAO;
    }

    public Double getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(Double LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public Double getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(Double LATITUDE) {
        this.LATITUDE = LATITUDE;
    }
}
