package com.example.georgeissac.mvp.view;


import com.example.georgeissac.mvp.usecase.getCountry.response.Country;

import java.util.List;

public interface ViewInterface {
	void showList(List<Country> list);
	void showError(String error);

}
