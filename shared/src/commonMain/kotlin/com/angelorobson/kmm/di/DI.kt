package com.angelorobson.kmm.di

import com.angelorobson.kmm.cache
import com.angelorobson.kmm.data.datasource.local.IPostLocalDataSource
import com.angelorobson.kmm.data.datasource.local.PostLocalDataSourceImpl
import com.angelorobson.kmm.data.datasource.network.IPostNetworkDataSource
import com.angelorobson.kmm.data.datasource.network.PostRemoteDataSourceImpl
import com.angelorobson.kmm.data.repository.PostRepository
import com.angelorobson.kmm.data.repository.PostRepositoryImpl
import com.angelorobson.kmm.initLogger
import com.angelorobson.kmm.domain.usecases.IGetPostUseCase
import com.angelorobson.kmm.domain.usecases.GetPostUseCaseImpl
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.coroutines.Dispatchers
import org.kodein.di.*
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal


@ThreadLocal
val KodeinInjector = DI {

    bind<CoroutineContext>() with provider { Dispatchers.Main }

    val client = HttpClient() {
        install(Logging) {
            level = LogLevel.BODY
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "HTTP Client", message = message)
                }
            }
        }
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json = json)
        }
    }.also {
        initLogger()
    }

    val queries = cache().appDatabseQueries

    /**
     * NETWORK DATA SOURCE
     */
    bind<IPostNetworkDataSource>() with provider { PostRemoteDataSourceImpl(client) }


    /**
     * LOCAL DATA SOURCE
     */
    bind<IPostLocalDataSource>() with provider { PostLocalDataSourceImpl(queries) }

    /**
     * REPOSITORIES
     */
    bind<PostRepository>() with provider { PostRepositoryImpl(instance(), instance()) }

    /**
     * USECASES
     */
    bind<IGetPostUseCase>() with singleton { GetPostUseCaseImpl(instance()) }
}