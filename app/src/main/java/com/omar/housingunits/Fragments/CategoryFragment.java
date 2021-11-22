package com.omar.housingunits.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omar.housingunits.Adapters.CategoryAdapter;
import com.omar.housingunits.Models.CategoryModel;
import com.omar.housingunits.R;
import com.omar.housingunits.databinding.FragmentCategoryBinding;
import com.omar.housingunits.databinding.FragmentDetailsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentCategoryBinding binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        List<CategoryModel> list = new ArrayList<>();
        list.add(new CategoryModel("شقق مفروشة","1350",R.drawable.ic_bed));
        list.add(new CategoryModel("فلا","1450",R.drawable.ic_key));
        list.add(new CategoryModel("شاليهات","1850",R.drawable.ic_image));
        list.add(new CategoryModel("محلات تمليك","1750",R.drawable.ic_image));
        list.add(new CategoryModel("محلات إيجار","1350",R.drawable.ic_image));

        CategoryAdapter adapter = new CategoryAdapter(list);
        binding.recyclerCategory.setHasFixedSize(true);
        binding.recyclerCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerCategory.setAdapter(adapter);

        return root;
    }
}