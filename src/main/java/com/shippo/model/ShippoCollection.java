package com.shippo.model;

import java.util.List;
import com.shippo.serialization.ShippoObject;


public abstract class ShippoCollection<T> extends ShippoObject {
    List<T> results;
    Boolean hasMore;
    String url;

    public List<T> getData() {
        return results;
    }

    public void setData(List<T> data) {
        this.results = data;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
