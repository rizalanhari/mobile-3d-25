package com.example.quizapp.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.R;
import com.example.quizapp.databinding.FragmentHomeBinding;
import com.example.quizapp.viewmodels.CodeViewModels;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    CodeViewModels mViewModels;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container,false);
        mViewModels = new ViewModelProvider(requireActivity()).get(CodeViewModels.class);
        binding.setLifecycleOwner(this);
        binding.setCodeViewModel(mViewModels);

        binding.buttonCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = binding.code.getText().toString();
                binding.getCodeViewModel().setCode(s);
                binding.textProses.setText(binding.getCodeViewModel().getCode());
            }
        });

        return binding.getRoot();
    }

}