package com.example.georgeissac.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.example.georgeissac.mvp.view.ViewInterface;

public class PresenterFactory<T> {

     T  getPresenterFromFactory(String s) {
         if(s.equals("MyViewPresenter")){
            return (T) new MyPresenter();
         }
        return null;
    }


}
