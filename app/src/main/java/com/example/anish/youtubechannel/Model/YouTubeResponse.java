
package com.example.anish.youtubechannel.Model;

import java.util.ArrayList;
import java.util.List;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class YouTubeResponse {

    @SerializedName("kind")
    @Expose
    public String kind;
    @SerializedName("etag")
    @Expose
    public String etag;
    @SerializedName("nextPageToken")
    @Expose
    public String nextPageToken;
    @SerializedName("regionCode")
    @Expose
    public String regionCode;
    @SerializedName("pageInfo")
    @Expose
    public PageInfo pageInfo;
    @SerializedName("items")
    @Expose
    public List<Item> items = new ArrayList<>();

    @Override
    public String toString() {
        return "YouTubeResponse{" +
                "kind='" + kind + '\'' +
                ", etag='" + etag + '\'' +
                ", nextPageToken='" + nextPageToken + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", pageInfo=" + pageInfo +
                ", items=" + items +
                '}';
    }

    /**
     *
     * @return
     *     The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     *
     * @param kind
     *     The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     *
     * @return
     *     The etag
     */
    public String getEtag() {
        return etag;
    }

    /**
     *
     * @param etag
     *     The etag
     */
    public void setEtag(String etag) {
        this.etag = etag;
    }

    /**
     *
     * @return
     *     The nextPageToken
     */
    public String getNextPageToken() {
        return nextPageToken;
    }

    /**
     *
     * @param nextPageToken
     *     The nextPageToken
     */
    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    /**
     *
     * @return
     *     The regionCode
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     *
     * @param regionCode
     *     The regionCode
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    /**
     *
     * @return
     *     The pageInfo
     */
    public PageInfo getPageInfo() {
        return pageInfo;
    }

    /**
     *
     * @param pageInfo
     *     The pageInfo
     */
    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    /**
     *
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     *
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

}
