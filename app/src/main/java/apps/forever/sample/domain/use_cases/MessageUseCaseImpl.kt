package apps.forever.sample.domain.use_cases

import apps.forever.sample.data.remote.MessageApi
import apps.forever.sample.data.remote.dto.MessagesDto
import retrofit2.Response

/**
 * Created by Anil Kumar Reddy
 * on 02,February,2024
 */
class MessageUseCaseImpl(private val messageApi: MessageApi) : MessageUseCase {

    override suspend fun getMessages(): Response<ArrayList<MessagesDto>> = messageApi.getMessages()

}