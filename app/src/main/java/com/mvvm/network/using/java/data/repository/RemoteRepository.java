package com.mvvm.network.using.java.data.repository;

import android.app.Activity;
import android.app.Service;

import com.mvvm.network.using.java.data.remote.ApiService;

import javax.inject.Inject;
import dagger.hilt.android.scopes.ViewModelScoped;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

@ViewModelScoped
public class RemoteRepository  {

    public static final String TAG = RemoteRepository.class.getSimpleName();

    private ApiService apiService;

    @Inject
    public RemoteRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getEmployee(Activity activity, Retrofit retrofit, ApiListener<Base> networkListener) {
        Observable<Response<Base>> observable = apiService.getEmployee();
        ApiObserver<Base> observer = new ApiObserver<Base>() {
            @Override
            public void onBegin(Disposable disposable) {
                networkListener.requestAccept(disposable);
            }

            @Override
            public void onSuccess(Response<Base> response) {
                if (response.code() == 200)
                {
                    Base baseResponse = response.body();
                    if (networkListener != null) {
                        networkListener.onSuccess(baseResponse);
                    }
                }
                else
                {
                    HttpStatusCode httpStatusCode   = HttpStatusCode.valueOf(response.code());
                    RemoteException remoteException = new RemoteException(httpStatusCode.value(), httpStatusCode.getReasonPhrase());

                    if (networkListener != null) {
                        networkListener.onError(remoteException.getDisplayMessage());
                    }
                }
            }

            @Override
            public void onFailure(RemoteException remoteException) {
                if (networkListener != null) {
                    networkListener.onError(remoteException.getDisplayMessage());
                }
            }
        };

        observable
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        activity.runOnUiThread(new Runnable() {
                            public void run()
                            {
                                if (networkListener != null){
                                    networkListener.requestCancel();
                                }
                            }
                        });
                    }
                })
                .compose(RxHelper.getInstance().<Response<Base>>applySchedulers(3, 3))
                /* Subscribe */
                .subscribe(observer);
    }

    public void getPage(Activity activity, Retrofit retrofit, ApiListener<ApiResponse<Page>> networkListener) {
    }
}