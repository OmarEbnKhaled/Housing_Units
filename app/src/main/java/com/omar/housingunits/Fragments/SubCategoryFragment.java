package com.omar.housingunits.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omar.housingunits.Activities.MainActivity;
import com.omar.housingunits.Activities.SecondActivity;
import com.omar.housingunits.Activities.UnitShowActivity;
import com.omar.housingunits.Adapters.SubCategoryAdapter;
import com.omar.housingunits.Models.CategoryModel;
import com.omar.housingunits.R;
import com.omar.housingunits.databinding.FragmentSubCategoryBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubCategoryFragment extends Fragment implements SubCategoryAdapter.onSubCategoryListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SubCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubCategoryFragment newInstance(String param1, String param2) {
        SubCategoryFragment fragment = new SubCategoryFragment();
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

        FragmentSubCategoryBinding binding = FragmentSubCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        List<CategoryModel> list = new ArrayList<>();
        list.add(new CategoryModel("sub 1","1350",R.drawable.ic_bed));
        list.add(new CategoryModel("sub 2","1750",R.drawable.ic_image));
        list.add(new CategoryModel("sub 3","1350",R.drawable.ic_image));
        list.add(new CategoryModel("sub 4","1350",R.drawable.ic_image));
        list.add(new CategoryModel("sub 5","1450",R.drawable.ic_key));
        list.add(new CategoryModel("sub 6","1850",R.drawable.ic_image));

        SubCategoryAdapter adapter = new SubCategoryAdapter(list, this);
        binding.recyclerSubCategory.setHasFixedSize(true);
        binding.recyclerSubCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerSubCategory.setAdapter(adapter);

        return root;
    }

    @Override
    public void onSubCategoryClick(int position) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations( R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.main_fragment, new ItemsFragment())
                .addToBackStack(null)
                .commit();
    }
}