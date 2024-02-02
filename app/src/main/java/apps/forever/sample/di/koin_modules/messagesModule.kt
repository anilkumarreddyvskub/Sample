package apps.forever.sample.di.koin_modules

import apps.forever.sample.ui.viewmodels.MainViewModel
import apps.forever.sample.ui.adapters.MessagesAdapter
import apps.forever.sample.data.remote.MessageApi
import apps.forever.sample.domain.use_cases.MessageUseCase
import apps.forever.sample.domain.use_cases.MessageUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Anil Kumar Reddy
 * on 02,February,2024
 */

val messagesModule = module {
    viewModel {
        MainViewModel(get())
    }
    single<MessageApi> {
        get<Retrofit>().create(MessageApi::class.java)
    }
    single<MessageUseCase> {
        MessageUseCaseImpl(get())
    }

    factory { params ->
        MessagesAdapter(params.get())
    }


}