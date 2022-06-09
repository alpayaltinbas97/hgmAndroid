package com.hgm.haritagenelmudurlugu.Pages;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService5 {
    @GET("api/get_science_data.php")
    Call<BilimSonuc> listScience();
}
