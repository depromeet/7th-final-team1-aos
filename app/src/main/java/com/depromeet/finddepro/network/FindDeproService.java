package com.depromeet.finddepro.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FindDeproService {

	private static Retrofit retrofit = null;
	private static final String BASE_URL = "~~~~~/v1";

	public static UserApi getUserApi() {
		if (retrofit == null) {
			retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}

		return retrofit.create(UserApi.class);
	}

	public static NoticeApi getNoticeApi() {
		if (retrofit == null) {
			retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}

		return retrofit.create(NoticeApi.class);
	}

}
