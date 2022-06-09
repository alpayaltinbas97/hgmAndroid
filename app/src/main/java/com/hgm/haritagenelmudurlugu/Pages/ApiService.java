package com.hgm.haritagenelmudurlugu.Pages;

import com.hgm.haritagenelmudurlugu.Model.MakaleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/get_article_data.php")
    Call<MakaleSonuc> listArticles();
}
