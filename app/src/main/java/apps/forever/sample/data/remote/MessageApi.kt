package apps.forever.sample.data.remote

import apps.forever.sample.data.remote.dto.MessagesDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Anil Kumar Reddy
 * on 02,February,2024
 */
interface MessageApi {
    @GET("posts")
    suspend fun getMessages(): Response<ArrayList<MessagesDto>>
}