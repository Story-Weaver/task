package by.roman.test_app.ui.activities;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import by.roman.test_app.R;
import by.roman.test_app.business_logic.modules.Hilt;
import by.roman.test_app.business_logic.viewModels.ModelViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddModelActivity extends AppCompatActivity {
    private ImageView back;
    private ImageView local;
    private ImageView api;
    private Button add;
    private EditText mail;
    private EditText ump;
    private String email;
    private String vump = "";
    private ModelViewModel viewModel;
    private final Handler handler = new Handler();
    private Runnable debounceRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_model);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findAll();
        viewModel = new ViewModelProvider(this).get(ModelViewModel.class);
        add.setOnClickListener(v -> addButton());
        back.setOnClickListener(v -> finish());
        if(!viewModel.hasRecord()){
            back.setVisibility(INVISIBLE);
        }
        ump.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String newText = s.toString();
                if (!newText.equals(vump)) {
                    if (debounceRunnable != null) {
                        handler.removeCallbacks(debounceRunnable);
                    }

                    debounceRunnable = () -> {
                        vump = newText;
                        Log.d("AddActivity", "Text changed: " + vump);
                        viewModel.findModel(vump);
                    };
                    handler.postDelayed(debounceRunnable, 1000); // 1000 мс = 1 сек
                }
            }
        });

        observeAll();
    }

    private void findAll() {
        back = findViewById(R.id.back);
        add = findViewById(R.id.add);
        mail = findViewById(R.id.editTextTextEmailAddress);
        ump = findViewById(R.id.editTextText);
        local = findViewById(R.id.localStatus);
        api = findViewById(R.id.apiStatus);
    }

    private void addButton() {
        email = mail.getText().toString();
        vump = ump.getText().toString();
        if(!email.isEmpty()) {
            viewModel.addModel(vump, email);
        } else {
            //TODO
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void observeAll() {
        viewModel.getApiModel().observe(this, status -> {
            switch (status.status) {
                case LOADING:
                    api.setImageDrawable(getDrawable(R.drawable.cloud_svgrepo_com));
                    api.setVisibility(VISIBLE);
                    local.setVisibility(VISIBLE);
                    break;
                case SUCCESS:
                    api.setImageDrawable(getDrawable(R.drawable.cloud_svgrepo_com__3_));
                    break;
                case ERROR:
                    api.setImageDrawable(getDrawable(R.drawable.cloud_svgrepo_com__1_));
                    break;
            }
        });
        viewModel.getLocalModel().observe(this, status -> {
            switch (status.status) {
                case LOADING:
                    local.setImageDrawable(getDrawable(R.drawable.database_svgrepo_com__1_));
                    local.setVisibility(VISIBLE);
                    api.setVisibility(VISIBLE);
                    break;
                case SUCCESS:
                    local.setImageDrawable(getDrawable(R.drawable.database_svgrepo_com));
                    break;
                case ERROR:
                    local.setImageDrawable(getDrawable(R.drawable.database_svgrepo_com__2_));
                    break;
            }
        });
        viewModel.getAddModel().observe(this, status -> {
            switch (status.status){
                case LOADING:
                    break;

                case SUCCESS:
                    finish();
                    break;

                case ERROR:
                    //TODO
                    break;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}