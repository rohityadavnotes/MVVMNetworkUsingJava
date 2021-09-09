package com.mvvm.network.using.java.data.remote.picasso;

public interface PicassoImageLoadingListener {
    void imageLoadSuccess();
    void imageLoadError(Exception exception);
}