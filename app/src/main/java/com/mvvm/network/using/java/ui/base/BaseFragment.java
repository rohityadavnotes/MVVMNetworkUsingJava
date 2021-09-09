package com.mvvm.network.using.java.ui.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();

    protected View rootView;

    protected abstract VB getViewBinding();
    protected abstract void initializeViewModel();
    protected abstract void initializeObject();
    protected abstract void initializeToolBar();
    protected abstract void addTextChangedListener();
    protected abstract void observeViewModel();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        VB viewBinding = getViewBinding();
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;

        initializeViewModel();
        initializeObject();
        initializeToolBar();
        addTextChangedListener();
        observeViewModel();
    }

    protected  <VMC extends ViewModel> VMC createViewModel(@NonNull Class<VMC> viewModelClass) {
        return new ViewModelProvider(this).get(viewModelClass);
    }
}
