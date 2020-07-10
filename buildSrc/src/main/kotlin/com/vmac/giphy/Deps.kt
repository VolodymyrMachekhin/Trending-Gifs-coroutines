package com.vmac.giphy

import com.vmac.giphy.Versions.androidxAppCompatVer
import com.vmac.giphy.Versions.androidxConstraintlayoutVer
import com.vmac.giphy.Versions.androidxFragmentVer
import com.vmac.giphy.Versions.bindingCollectionVer
import com.vmac.giphy.Versions.glideVer
import com.vmac.giphy.Versions.koinVersion
import com.vmac.giphy.Versions.kotlinCoroutinesVer
import com.vmac.giphy.Versions.kotlinVersion
import com.vmac.giphy.Versions.okhttpVer
import com.vmac.giphy.Versions.retrofitVer

object Versions {
    val appVerCode = 1
    val appVerName = "1.0"

    val koinVersion = "2.1.6"
    val kotlinVersion = "1.3.72"
    val androidGradlePluginVer = "4.0.0"

    val compileSdkVer = 29
    val targetSdkVer = 29
    val buildToolsVer = "29.0.2"
    val minSdkVer = 21

    val bindingCollectionVer = "3.2.0"
    val retrofitVer = "2.9.0"
    val glideVer = "4.11.0"
    val okhttpVer = "4.0.0"

    val kotlinCoroutinesVer = "1.3.5"
    val androidxAppCompatVer = "1.1.0"
    val androidxFragmentVer = "1.2.4"
    val androidxConstraintlayoutVer = "1.1.3"
}

object Deps {
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    val androidxAppCompat = "androidx.appcompat:appcompat:$androidxAppCompatVer"
    val androidxCore = "androidx.core:core-ktx:1.2.0"
    val androidxConstraintlayout =
        "androidx.constraintlayout:constraintlayout:$androidxConstraintlayoutVer"
    val androidxFragment = "androidx.fragment:fragment-ktx:$androidxFragmentVer"


    val okhttp3 = "com.squareup.okhttp3:okhttp:$okhttpVer"

    val retrofit2 = "com.squareup.retrofit2:retrofit:$retrofitVer"
    val retrofit2Coroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    val retrofit2Moshi = "com.squareup.retrofit2:converter-moshi:$retrofitVer"

    val glide = "com.github.bumptech.glide:glide:$glideVer"


    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.7.2"


    val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVer"
    val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutinesVer"
    val bindingcollectionadapter =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter:$bindingCollectionVer"
    val bindingcollectionadapterRecyclerview =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:$bindingCollectionVer"

    val koinCore = "org.koin:koin-core:$koinVersion"
    val koinAndroid = "org.koin:koin-android:$koinVersion"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
}