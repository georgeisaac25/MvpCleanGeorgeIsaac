package com.example.georgeissac.mvp.domain.interfaces;

public interface UseCaseContractInterface {
    void getCountyList();
    void searchCountry(String string);
    void getCountyListUsingRx();
}
