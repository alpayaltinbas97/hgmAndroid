package com.hgm.haritagenelmudurlugu.Pages;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService4 {
    @GET("api/get_management_data.php")
    Call<YonetimSonuc> listManagement();
}
