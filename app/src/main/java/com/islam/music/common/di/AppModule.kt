package com.islam.music.common.di

import android.app.Application
import com.islam.music.BuildConfig
import com.islam.music.common.TIME_OUT_IN_SECONDS
import com.islam.music.features.album_details.data.db.AlbumDao
import com.islam.music.features.album_details.data.db.AlbumDatabase
import com.islam.music.features.album_details.data.db.AlbumDetailsToAlbumMapper
import com.islam.music.features.album_details.data.db.datasource.AlbumDetailsLocalDataSource
import com.islam.music.features.album_details.data.db.datasource.AlbumDetailsLocalDataSourceImpl
import com.islam.music.features.album_details.data.remote.api.AlbumDetailsAPIService
import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsRemoteDataSource
import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsRemoteDataSourceImpl
import com.islam.music.features.album_details.data.repositories.AlbumDetailsRepositoryImpl
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.album_details.domain.usecases.AlbumDetailsUseCase
import com.islam.music.features.album_details.domain.usecases.GetFavoriteUseCase
import com.islam.music.features.album_details.domain.usecases.SetFavoriteUseCase
import com.islam.music.features.album_details.presentation.view.TrackAdapter
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsViewModel
import com.islam.music.features.main_screen.data.AlbumEntityToAlbumMapper
import com.islam.music.features.main_screen.domain.usecases.MainScreenUseCase
import com.islam.music.features.main_screen.presentation.view.AlbumsAdapter
import com.islam.music.features.main_screen.presentation.viewmodel.MainScreenViewModel
import com.islam.music.features.search.data.remote.api.SearchAPIService
import com.islam.music.features.search.data.remote.datasource.SearchArtistRemoteDataSource
import com.islam.music.features.search.data.remote.datasource.SearchArtistRemoteDataSourceImpl
import com.islam.music.features.search.data.repositories.SearchArtistRepositoryImpl
import com.islam.music.features.search.domain.repositories.SearchArtistRepository
import com.islam.music.features.search.domain.usecases.SearchArtistUseCase
import com.islam.music.features.search.presentation.view.ArtistsAdapter
import com.islam.music.features.search.presentation.viewmodel.SearchViewModel
import com.islam.music.features.top_albums.data.remote.api.TopAlbumsAPIService
import com.islam.music.features.top_albums.data.remote.datasource.TopAlbumsRemoteDataSource
import com.islam.music.features.top_albums.data.remote.datasource.TopAlbumsRemoteDataSourceImpl
import com.islam.music.features.top_albums.data.repositories.TopAlbumsRepositoryImpl
import com.islam.music.features.top_albums.domain.repositories.TopAlbumsRepository
import com.islam.music.features.top_albums.domain.usecases.TopAlbumsUseCase
import com.islam.music.features.top_albums.presentation.viewmodel.TopAlbumsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


@Module
@ComponentScan("com.islam.music.features.album_details.presentation.viewmodel")
class ViewModelModule

@Module
@ComponentScan("com.islam.music.features.top_albums.domain.usecases")
class UseCaseModule

@Module
@ComponentScan("com.islam.music.common.di")
class ApiModule

val appModule = module {
    factory { AlbumDetailsToAlbumMapper() }
    factory { AlbumEntityToAlbumMapper() }

    factory<AlbumDetailsRepository> { AlbumDetailsRepositoryImpl(get(), get()) }
    factory<TopAlbumsRepository> { TopAlbumsRepositoryImpl(get()) }
    factory<SearchArtistRepository> { SearchArtistRepositoryImpl(get()) }

    factory<AlbumDetailsLocalDataSource> { AlbumDetailsLocalDataSourceImpl(get(), get()) }
    factory<TopAlbumsRemoteDataSource> { TopAlbumsRemoteDataSourceImpl(get()) }
    factory<AlbumDetailsRemoteDataSource> { AlbumDetailsRemoteDataSourceImpl(get(), get()) }
    factory<SearchArtistRemoteDataSource> { SearchArtistRemoteDataSourceImpl(get()) }

    factory { AlbumsAdapter(get()) }
    factory { ArtistsAdapter(get()) }
    factory { TrackAdapter() }
}

val apiModule = module {
    single { provideHttpLoggingInterceptor() }
  //  single { URLInterceptor() }
    single { provideHttpClient(get(), get()) }
    single { provideRetrofit(get()) }
    single { provideTopAlbumsAPIService(get()) }
    single { provideSearchAPIService(get()) }
    single { provideAlbumDetailsAPIService(get()) }
}

val useCaseModule = module {
    factory { AlbumDetailsUseCase(get()) }
    factory { GetFavoriteUseCase(get()) }
    factory { SetFavoriteUseCase(get()) }
    factory { MainScreenUseCase(get()) }
    factory { SearchArtistUseCase(get()) }
  //  factory { TopAlbumsUseCase(get()) }
}

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideAlbumDao(get()) }
}

val viewModelModule = module {
  //  viewModel { AlbumDetailsViewModel(get(), get(), get()) }
    viewModel { MainScreenViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { TopAlbumsViewModel(get()) }
}

fun provideAlbumDao(db: AlbumDatabase): AlbumDao = db.getAlbumDao()

fun provideDatabase(appContext: Application): AlbumDatabase =
    AlbumDatabase.invoke(appContext)

fun provideTopAlbumsAPIService(retrofit: Retrofit): TopAlbumsAPIService {
    return retrofit.create(TopAlbumsAPIService::class.java)
}

fun provideSearchAPIService(retrofit: Retrofit): SearchAPIService {
    return retrofit.create(SearchAPIService::class.java)
}

fun provideAlbumDetailsAPIService(retrofit: Retrofit): AlbumDetailsAPIService {
    return retrofit.create(AlbumDetailsAPIService::class.java)
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun provideHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    urlInterceptor: URLInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
        .connectTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(urlInterceptor)
        .build()
}