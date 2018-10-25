package com.example.georgeissac.mvp.presentationLayer.interfaces;


import com.example.georgeissac.mvp.domainLayer.getCountry.CountryPojo;

import java.util.List;

public interface ViewInterface {
	void showList(List<CountryPojo> list);
	void showError(String error);
	void callDb (String string);
	void callWebService();
	void navigateToShowCountryDetailActivity (CountryPojo country);
}
