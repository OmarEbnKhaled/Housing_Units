package com.omar.housingunits.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omar.housingunits.Activities.UnitShowActivity;
import com.omar.housingunits.Adapters.ItemsAdapter;
import com.omar.housingunits.Models.ItemModel;
import com.omar.housingunits.R;
import com.omar.housingunits.databinding.FragmentItemsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemsFragment extends Fragment implements ItemsAdapter.onItemsListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentItemsBinding binding;

    public ItemsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemsFragment newInstance(String param1, String param2) {
        ItemsFragment fragment = new ItemsFragment();
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

        binding = FragmentItemsBinding.inflate(inflater, container, false);

        List<ItemModel> list = new ArrayList<>();
        list.add(new ItemModel("title 1", "location 1", "3000", "1", "2", "3"));
        list.add(new ItemModel("title 2", "location 2", "2500", "1", "2", "3"));
        list.add(new ItemModel("title 3", "location 3", "3000", "1", "2", "3"));

        ItemsAdapter adapter = new ItemsAdapter(list,this);
        binding.recyclerItems.setHasFixedSize(true);
        binding.recyclerItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerItems.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(getActivity(), UnitShowActivity.class));
    }
}