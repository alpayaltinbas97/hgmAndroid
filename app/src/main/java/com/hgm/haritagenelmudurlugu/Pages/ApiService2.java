package com.hgm.haritagenelmudurlugu.Pages;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService2 {
    @GET("api/get_special_data.php")
    Call<OzelSayiSonuc> listSpecial();
}
