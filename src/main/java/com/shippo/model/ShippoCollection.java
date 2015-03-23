package com.shippo.model;

import java.util.List;

public abstract class ShippoCollection<T> extends ShippoObject {
    List<T> results;
    Boolean hasMore;
    String url;
    /** 3/2014: Legacy (from before newstyle pagination API) */
    Integer count;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
