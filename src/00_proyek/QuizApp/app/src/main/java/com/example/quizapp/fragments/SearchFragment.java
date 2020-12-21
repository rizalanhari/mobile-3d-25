package com.example.quizapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.R;
import com.example.quizapp.adapters.KategoriAdapter;
import com.example.quizapp.models.KategoriModel;
import com.example.quizapp.models.TriviaCategoryModel;
import com.example.quizapp.retrofit.ApiService;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    LinkedList<String> kategori_name = new LinkedList<>();
    LinkedList<String> kategori_id = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private KategoriAdapter mAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        mRecyclerView = rootView.findViewById(R.id.searchrecyclerview);
        getKategoriApi();
        return rootView;
    }

    List<TriviaCategoryModel> daftar_kategori;

    private void getKategoriApi() {
        ApiService.endpoint().getKategori().enqueue(new Callback<KategoriModel>() {
            @Override
            public void onResponse(Call<KategoriModel> call, Response<KategoriModel> response) {
                daftar_kategori = response.body().getTriviaCategories();
                for (int i = 0; i < daftar_kategori.size(); i++) {
                    kategori_id.add(String.valueOf(daftar_kategori.get(i).getId()));
                    kategori_name.add(daftar_kategori.get(i).getName());

                }
                mAdapter = new KategoriAdapter(kategori_name, kategori_id, requireActivity());
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            }

            @Override
            public void onFailure(Call<KategoriModel> call, Throwable t) {

            }
        });
    }
}