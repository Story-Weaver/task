package by.roman.test_app.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import by.roman.test_app.R;
import by.roman.test_app.business_logic.adapters.SpinnerAdapter;
import by.roman.test_app.business_logic.viewModels.ModelViewModel;
import by.roman.test_app.ui.activities.AddModelActivity;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private ImageView addModel;
    private Spinner chooseUNP;
    private ModelViewModel viewModel;
    private SpinnerAdapter adapter;
    private List<String> list = new ArrayList<>();
    @Override
    public void onResume(){
        super.onResume();
        addModel.setEnabled(true);
        adapter.setItems(viewModel.getUMPList());
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        findAll(view);
        initAll();
        observeData();
    }
    private void findAll(@NonNull View view){
        addModel = view.findViewById(R.id.addModel);
        chooseUNP = view.findViewById(R.id.spinner_home);
    }
    private void initAll(){
        viewModel = new ViewModelProvider(this).get(ModelViewModel.class);
        addModel.setOnClickListener(v -> {
            addModel.setEnabled(false);
            Intent intent = new Intent(requireContext(), AddModelActivity.class);
            startActivity(intent);
        });
        list = viewModel.getUMPList();
        adapter = new SpinnerAdapter(requireContext(), list);
        chooseUNP.setAdapter(adapter);
        chooseUNP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.getModel(list.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void observeData(){
        viewModel.getCurrentModel().observe(getViewLifecycleOwner(), model -> {
            switch (model.status){
                case LOADING:
                    break;

                case SUCCESS:
                    //TODO
                    break;

                case ERROR:
                    //TODO
                    break;
            }
        });
    }
}