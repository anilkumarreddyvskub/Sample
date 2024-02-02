package apps.forever.sample.ui.viewmodels

import androidx.lifecycle.ViewModel
import apps.forever.sample.common.NetworkResponse
import apps.forever.sample.common.Util.APIException
import apps.forever.sample.data.remote.dto.MessagesDto
import apps.forever.sample.domain.use_cases.MessageUseCase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Anil Kumar Reddy
 * on 02,February,2024
 */
class MainViewModel(private val messageUseCase: MessageUseCase) : ViewModel() {

    fun getMessages() = flow {
        emit(NetworkResponse.Loading(null))
        try {
            val response = messageUseCase.getMessages()
            if (response.isSuccessful) {
                emit(NetworkResponse.Success(response.body()))
            } else {
                val gson = Gson()
                val type = object : TypeToken<MessagesDto>() {}.type
                val errorResponse: MessagesDto? =
                    gson.fromJson(response.errorBody()!!.charStream(), type)
                emit(NetworkResponse.Success(errorResponse))
            }
        } catch (e: Exception) {
            throw APIException("Error occurred")
        }
    }.catch {
        emit(NetworkResponse.Error(APIException(it.message)))
    }
}