package com.mobile.plugins

import com.mobile.repository.TodoRepository
import com.mobile.repository.TodoRepositoryImpl
import com.mobile.repository.UserRepository
import com.mobile.repository.UserRepositoryImpl
import com.mobile.services.TodoService
import com.mobile.services.UserService
import io.ktor.server.application.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin(){
    val todoModule = module{
        singleOf(::TodoRepositoryImpl) { bind<TodoRepository>()}
        singleOf(::TodoService)
    }
    val userModule = module{
        singleOf(::UserRepositoryImpl){ bind<UserRepository>()}
        singleOf(::UserService)
    }
    install(Koin){
        slf4jLogger()
        modules(todoModule, userModule)
        createEagerInstances()
    }
}

