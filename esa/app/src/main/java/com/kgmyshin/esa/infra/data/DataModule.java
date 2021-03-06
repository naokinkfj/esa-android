/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.infra.data;

import android.app.Application;

import com.kgmyshin.esa.infra.data.api.v1.ApiClientFactory;
import com.kgmyshin.esa.infra.data.api.v1.IApiClient;
import com.kgmyshin.esa.infra.data.pref.AccessTokenPreferences;
import com.kgmyshin.esa.infra.data.pref.TeamPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class DataModule {

    @Singleton
    @Provides
    public AccessTokenPreferences provideAccessTokenPreferences(Application application) {
        return new AccessTokenPreferences(application);
    }

    @Singleton
    @Provides
    public TeamPreferences provideTeamPreferences(Application application) {
        return new TeamPreferences(application);
    }

    @Singleton
    @Provides
    public ApiClientFactory provideApiClientFactory(AccessTokenPreferences pref) {
        return new ApiClientFactory(pref);
    }

    @Singleton
    @Provides
    public IApiClient provideApiClient(ApiClientFactory factory) {
        return factory.createClient();
    }

}
