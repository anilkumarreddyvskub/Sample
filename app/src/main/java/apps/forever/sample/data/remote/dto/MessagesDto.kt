package apps.forever.sample.data.remote.dto

import apps.forever.sample.domain.model.Message
import com.google.gson.annotations.SerializedName

/**
 * Created by Anil Kumar Reddy
 * on 02,February,2024
 */
data class MessagesDto(
    @SerializedName("userId") val userId: Int = 0,
    @SerializedName("id") val id: Int = 0,
    @SerializedName("title") val title: String = "",
    @SerializedName("body") val body: String = ""
)

fun MessagesDto.toMessage(): Message {
    return Message(userId = userId, id = id, title = title, body = body)
}