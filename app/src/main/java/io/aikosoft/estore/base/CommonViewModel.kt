package io.aikosoft.estore.base

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.aikosoft.estore.R
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class CommonViewModel @Inject constructor(
    private val app: Application
) : ViewModel(),
    OnErrorListener {

    private val _error = MutableLiveData<String>()

    val error: LiveData<String> get() = _error

    override fun onError(error: Throwable) {
        when (error) {
            is HttpException -> onHttpException(error)
            is SocketTimeoutException -> setErrorMessage(R.string.error_timeout)
            is IOException -> setErrorMessage(R.string.error_network_error)
            else -> setErrorMessage(R.string.error_unknown_error)
        }
    }

    private fun onHttpException(error: HttpException) {
        val message = error.getErrorMessage()
        when (error.code()) {
            401 -> {
                // Unauthorized
                setErrorMessage(message ?: app.getString(R.string.error_unknown_error))
            }
            400 -> {
                setErrorMessage(
                    when (message) {
                        SAMPLE_ERROR_MESSAGE -> R.string.error_invalid_sms_code
                        else -> R.string.error_unknown_error
                    }
                )
            }
            else -> {
                setErrorMessage(message ?: app.getString(R.string.error_unknown_error))
            }
        }
    }

    override fun setErrorMessage(msg: String) {
        _error.value = msg
    }

    override fun setErrorMessage(msgRes: Int) {
        _error.value = app.getString(msgRes)
    }

    companion object {
        private const val SAMPLE_ERROR_MESSAGE = "Sample error message"

        private fun HttpException.getErrorMessage(): String? {
            return try {
                val str = response().errorBody()?.string() ?: return null
                val jsonObject = JSONObject(str)
                jsonObject.getString("msg")
            } catch (e: Exception) {
                null
            }
        }
    }
}