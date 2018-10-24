package com.example.georgeissac.mvp.presentationLayer.interfaces;


import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country;

import java.util.List;

public interface ViewInterface {
	void showList(List<Country> list);
	void showError(String error);
	void callDb (String string);
	void callWebService();
	void naviagateToShowCountryDetailActivity (Country country);
}
