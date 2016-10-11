package com.example.anish.youtubechannel.Rest;


import com.example.anish.youtubechannel.Model.YouTubeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by anish on 04-10-2016.
 */
public interface ApiInterface
{
    @GET("search?&channelId=UC6ZkHcW5QQubZ-Q6XYINE3Q&part=snippet,id&order=date&maxResults=10")
    Call<YouTubeResponse> getChannelResult(@Query("api_key") String apiKey);//key=AIzaSyAKY_5SRDhyy0CiYCwdIASimezW0CNt2HA

    @GET("search")
    Call<YouTubeResponse> getChannelResult1(@Query("channelId") String channelId,@Query("part") String part,@Query("order") String order,@Query("maxResults") int maxResults,@Query("key") String apiKey);//key=AIzaSyAKY_5SRDhyy0CiYCwdIASimezW0CNt2HA

    @GET("search")
    Call<YouTubeResponse> getNextPage(@Query("channelId") String channelId,@Query("part") String part,@Query("order") String order,@Query("maxResults") int maxResults,@Query("pageToken") String pageToken,@Query("key") String apiKey);//key=AIzaSyAKY_5SRDhyy0CiYCwdIASimezW0CNt2HA
}
