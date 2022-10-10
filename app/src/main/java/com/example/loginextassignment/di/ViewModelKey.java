package com.example.loginextassignment.di;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) /*Same Retention policy that is used to create a Sinngleton scope annotationn*/
@MapKey /*To mark it as a MapKey*/
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}
