package com.agents.main.di

import com.agents.main.data.repository.ValorantApiRepositoryImpl
import com.agents.main.data.source.ValorantApi
import com.agents.main.domain.repository.ValorantApiRepository
import com.agents.main.domain.use_case.GetAgentInfo
import com.agents.main.domain.use_case.GetAgents
import com.agents.main.domain.use_case.GetWeaponInfo
import com.agents.main.domain.use_case.GetWeapons
import com.agents.main.presentation.screens.agent_info_screen.AgentInfoViewModel
import com.agents.main.presentation.screens.agents_screen.AgentsScreenViewModel
import com.agents.main.presentation.screens.weapon_info_screen.WeaponInfoViewModel
import com.agents.main.presentation.screens.weapons_screen.WeaponsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://valorant-api.com/v1/"

object MainModule {

    val dataModule = module {

        single {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        single(qualifier = named("client")) {
            OkHttpClient.Builder()
                .addInterceptor(get<HttpLoggingInterceptor>())
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(get(qualifier = named("client")))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        single { get<Retrofit>().create<ValorantApi>() }

        single<ValorantApiRepository> { ValorantApiRepositoryImpl(get()) }

    }

    val domainModule = module {
        single { GetAgents(repository = get()) }
        single { GetAgentInfo(repository = get()) }
        single { GetWeapons(repository = get()) }
        single { GetWeaponInfo(repository = get()) }
    }

    val viewModelModule = module {
        viewModelOf(::AgentsScreenViewModel)
        viewModelOf(::AgentInfoViewModel)
        viewModelOf(::WeaponsViewModel)
        viewModelOf(::WeaponInfoViewModel)
    }
}