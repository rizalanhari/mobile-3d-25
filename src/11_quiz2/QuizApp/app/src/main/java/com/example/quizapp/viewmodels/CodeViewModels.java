package com.example.quizapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.models.Code;

import java.util.ArrayList;

public class CodeViewModels extends ViewModel {
    private final MutableLiveData <String> Code = new MutableLiveData<>(new String());
    public String getCode(){
        return  Code.getValue();
    }

    public void setCode(String s){
        this.Code.setValue(s);
    }
}
