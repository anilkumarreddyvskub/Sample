package apps.forever.sample.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Anil Kumar Reddy
 * on 02,February,2024
 */
data class Message(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val body: String = ""
)