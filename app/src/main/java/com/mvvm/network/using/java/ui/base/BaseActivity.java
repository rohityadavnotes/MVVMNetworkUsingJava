package com.mvvm.network.using.java.ui.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    protected VB viewBinding;

    protected abstract VB getViewBinding();
    protected abstract void initializeViewModel();
    protected abstract void initializeObject();
    protected abstract void initializeToolBar();
    protected abstract void addTextChangedListener();
    protected abstract void observeViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewBinding = getViewBinding();
        setContentView(viewBinding.getRoot());

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
