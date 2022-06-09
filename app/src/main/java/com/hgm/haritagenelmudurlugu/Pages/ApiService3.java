package com.hgm.haritagenelmudurlugu.Pages;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService3 {
    @GET("api/get_archive_data.php")
    Call<DergiArsivSonuc> listArchive();
}
