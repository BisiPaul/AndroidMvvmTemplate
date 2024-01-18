package com.applakazam.androidmvvmtemplate.api

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import retrofit2.Response

/**
 * Status of a resource that is provided to the UI.
 *
 * These are usually created by the Repository classes where they return
 * `LiveData<Resource<T>>` to pass back the latest data to the UI with its fetch status.
 */
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

interface EntityMapper<ENTITY, DOMAIN_MODEL> {
    fun mapFromEntity(entity: ENTITY): DOMAIN_MODEL {
        throw java.lang.Exception("Need to implement method")
    }

    fun mapToEntity(domainModel: DOMAIN_MODEL): ENTITY {
        throw java.lang.Exception("Need to implement method")
    }
}

class ServerException(message: String) : Exception(message)

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val errorCode: Int? = null,
    val exception: Exception? = null
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(errorCode: Int, exception: Exception? = null): Resource<T> {
            return Resource(Status.ERROR, errorCode = errorCode, exception = exception)
        }

        fun loading(): Resource<Nothing> {
            return Resource(Status.LOADING)
        }
    }
}

val Resource<*>.succeeded: Boolean
    get() = status == Status.SUCCESS

interface ResponseInterpreter {
    fun <ENTITY, DOMAIN_MODEL> interpret(
        response: Response<ENTITY>,
        mapper: EntityMapper<ENTITY, DOMAIN_MODEL>
    ): Resource<DOMAIN_MODEL>
}

class DefaultResponseInterpreter(private val moshi: Moshi) : ResponseInterpreter {

    data class ServerMessage(@Json(name = "message") val message: String)

    override fun <ENTITY, DOMAIN_MODEL> interpret(
        response: Response<ENTITY>,
        mapper: EntityMapper<ENTITY, DOMAIN_MODEL>
    ): Resource<DOMAIN_MODEL> {
        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Resource.success(mapper.mapFromEntity(body))
            } else {
                Resource.error(ApiConstants.WRONG_ARGUMENTS)
            }
        } else {
            val errorBody = response.errorBody()
            if (errorBody != null) {
                val serverMessage = moshi.adapter(ServerMessage::class.java).fromJson(
                    errorBody.string()
                )
                Resource.error(ApiConstants.SERVER_ERROR, serverMessage?.message?.let { ServerException(it) })
            } else {
                Resource.error(ApiConstants.GENERAL_ERROR_CODE)
            }
        }
    }
}