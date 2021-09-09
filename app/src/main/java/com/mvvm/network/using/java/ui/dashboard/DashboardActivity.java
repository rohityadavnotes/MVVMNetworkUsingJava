package com.mvvm.network.using.java.ui.dashboard;

import android.os.Bundle;
import com.mvvm.network.using.java.R;
import com.mvvm.network.using.java.databinding.ActivityDashboardBinding;
import com.mvvm.network.using.java.ui.base.BaseActivity;
import com.mvvm.network.using.java.ui.dashboard.fragment.view.DashboardFragment;
import com.mvvm.network.using.java.utilities.fragment.ManageFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardActivity extends BaseActivity<ActivityDashboardBinding> {

    private ManageFragment manageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected ActivityDashboardBinding getViewBinding() {
        return ActivityDashboardBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initializeViewModel() {
    }

    @Override
    protected void initializeObject() {
        manageFragment = new ManageFragment(getSupportFragmentManager(), R.id.fragmentContainer);
        showDefaultFragment();
    }

    @Override
    protected void initializeToolBar() {
    }

    @Override
    protected void addTextChangedListener() {
    }

    @Override
    protected void observeViewModel() {
    }

    private void showDefaultFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("fragment_name", " Dashboard Fragment");
        DashboardFragment fragment = DashboardFragment.newInstance(bundle);
        manageFragment.addFragment(fragment, null, true, null);
    }
}