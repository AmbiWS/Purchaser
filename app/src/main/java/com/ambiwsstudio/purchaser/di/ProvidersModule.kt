package com.ambiwsstudio.purchaser.di

import com.ambiwsstudio.purchaser.core.providers.PreferencesProvider
import com.ambiwsstudio.purchaser.core.providers.PreferencesProviderImpl
import com.ambiwsstudio.purchaser.core.providers.ResourceProvider
import com.ambiwsstudio.purchaser.core.providers.ResourceProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val providersModule = module {
    factory<ResourceProvider> { ResourceProviderImpl(androidContext()) }
    factory<PreferencesProvider> { PreferencesProviderImpl(androidContext()) }
}
