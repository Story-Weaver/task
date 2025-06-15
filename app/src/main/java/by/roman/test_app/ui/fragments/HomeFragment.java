package by.roman.test_app.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import by.roman.test_app.R;
import by.roman.test_app.business_logic.adapters.SpinnerAdapter;
import by.roman.test_app.business_logic.viewModels.ModelViewModel;
import by.roman.test_app.ui.activities.AddModelActivity;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private TextView mail;
    private TextView VNAIMP;
    private TextView VNAIMK;
    private TextView DREG;
    private TextView NMNS;
    private TextView VMNS;
    private TextView CKODSOST;
    private TextView DLIKV;
    private TextView VPADRES;

    private ImageView addModel;
    private Spinner chooseUNP;
    private ModelViewModel viewModel;
    private SpinnerAdapter adapter;
    private List<String> list = new ArrayList<>();
    @Override
    public void onResume(){
        super.onResume();
        addModel.setEnabled(true);
        list = viewModel.getList();
        adapter = new SpinnerAdapter(requireContext(), list);
        chooseUNP.setAdapter(adapter);
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

        mail = view.findViewById(R.id.textView_mail);
        VNAIMP = view.findViewById(R.id.textView14_fullname);
        VNAIMK = view.findViewById(R.id.textView15_name);
        DREG = view.findViewById(R.id.textView16_dreg);
        NMNS = view.findViewById(R.id.textView17_mns);
        VMNS = view.findViewById(R.id.textView18_vmns);
        CKODSOST = view.findViewById(R.id.textView19_status);
        DLIKV = view.findViewById(R.id.textView20_change_status);
        VPADRES = view.findViewById(R.id.textView24_adress);
    }
    private void initAll(){
        viewModel = new ViewModelProvider(this).get(ModelViewModel.class);
        addModel.setOnClickListener(v -> {
            addModel.setEnabled(false);
            Intent intent = new Intent(requireContext(), AddModelActivity.class);
            startActivity(intent);
        });
        list = viewModel.getList();
        adapter = new SpinnerAdapter(requireContext(), list);
        chooseUNP.setAdapter(adapter);
        chooseUNP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("dfh","trig");
                if(!list.isEmpty()){
                    Log.d("dfh","push");
                    viewModel.getModel(list.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if(!viewModel.hasRecord()){
            Intent intent = new Intent(requireContext(), AddModelActivity.class);
            startActivity(intent);
        }
    }
    private void observeData(){
        viewModel.getCurrentModel().observe(getViewLifecycleOwner(), model -> {
            switch (model.status){
                case LOADING:
                    break;

                case SUCCESS:
                    mail.setText(model.data.getMail());
                    VNAIMP.setText(model.data.getVnaimp());
                    VNAIMK.setText(model.data.getVnaimk());
                    DREG.setText(model.data.getDreg());
                    NMNS.setText(model.data.getNmns());
                    VMNS.setText(model.data.getVmns());
                    CKODSOST.setText(model.data.getCkodsost());
                    DLIKV.setText(model.data.getDlikv());
                    VPADRES.setText(model.data.getVpadres());
                    break;

                case ERROR:
                    Toast.makeText(requireContext(), model.message, Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}