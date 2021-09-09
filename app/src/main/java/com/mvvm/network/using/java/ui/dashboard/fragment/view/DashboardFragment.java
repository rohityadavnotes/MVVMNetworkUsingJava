package com.mvvm.network.using.java.ui.dashboard.fragment.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import com.mvvm.network.using.java.databinding.FragmentDashboardBinding;
import com.mvvm.network.using.java.ui.base.BaseFragment;
import com.mvvm.network.using.java.ui.dashboard.fragment.viewmodel.DashboardViewModel;
import com.mvvm.network.using.java.utilities.fragment.IFragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardFragment extends BaseFragment<FragmentDashboardBinding> implements IFragment {

    public static final String TAG = DashboardFragment.class.getSimpleName();
    private String bundleData;

    private DashboardViewModel viewModel;

    public DashboardFragment() {
    }

    public static DashboardFragment newInstance(Bundle bundle) {
        DashboardFragment fragment = new DashboardFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bundleData = getArguments().getString("fragment_name");
        }
    }

    @Override
    protected FragmentDashboardBinding getViewBinding() {
        return FragmentDashboardBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initializeViewModel() {
        viewModel = createViewModel(DashboardViewModel.class);
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

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}