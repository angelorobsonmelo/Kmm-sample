package com.angelorobson.opsmoonkmm.di

import com.angelorobson.opsmoonkmm.data.datasource.network.PostNetworkDataSource
import com.angelorobson.opsmoonkmm.data.datasource.network.PostNetworkDataSourceImpl
import com.angelorobson.opsmoonkmm.data.repository.PostRepository
import com.angelorobson.opsmoonkmm.data.repository.PostRepositoryImpl
import com.angelorobson.opsmoonkmm.initLogger
import com.angelorobson.opsmoonkmm.domain.usecases.GetPostUseCase
import com.angelorobson.opsmoonkmm.domain.usecases.GetPostUseCaseImpl
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

    /**
     * NETWORK DATA SOURCE
     */
    bind<PostNetworkDataSource>() with provider { PostNetworkDataSourceImpl(client) }

    /**
     * REPOSITORIES
     */
    bind<PostRepository>() with provider { PostRepositoryImpl(instance()) }

    /**
     * USECASES
     */
    bind<GetPostUseCase>() with singleton { GetPostUseCaseImpl(instance()) }
}