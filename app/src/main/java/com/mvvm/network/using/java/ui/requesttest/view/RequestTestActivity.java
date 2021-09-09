package com.mvvm.network.using.java.ui.requesttest.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import com.mvvm.network.using.java.databinding.ActivityRequestTestBinding;
import com.mvvm.network.using.java.ui.base.BaseActivity;
import com.mvvm.network.using.java.ui.requesttest.viewmodel.RequestTestViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RequestTestActivity extends BaseActivity<ActivityRequestTestBinding> {

    private RequestTestViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected ActivityRequestTestBinding getViewBinding() {
        return ActivityRequestTestBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initializeViewModel() {
        viewModel = createViewModel(RequestTestViewModel.class);
    }

    @Override
    protected void initializeObject() {

    }

    @Override
    protected void initializeToolBar() {
    }

    @Override
    protected void addTextChangedListener() {
    }

    @Override
    protected void observeViewModel() {
        final Observer<Integer> teamAObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                System.out.println("=================================" + integer);
            }
        };

        final Observer<Integer> teamBObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                System.out.println("=================================" + integer);
            }
        };

        viewModel.getTeamAScore().observe(this/*Activity or Fragment*/, teamAObserver);
        viewModel.getTeamBScore().observe(this/*Activity or Fragment*/, teamBObserver);
    }
}