package com.mvvm.network.using.java.di.qualifier.local;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface SharedPreferencesName {
}
