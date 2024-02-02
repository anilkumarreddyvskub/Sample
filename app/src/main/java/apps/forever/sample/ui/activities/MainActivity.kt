package apps.forever.sample.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import apps.forever.sample.common.NetworkResponse
import apps.forever.sample.ui.adapters.MessagesAdapter
import apps.forever.sample.databinding.ActivityMainBinding
import apps.forever.sample.data.remote.dto.MessagesDto
import apps.forever.sample.data.remote.dto.toMessage
import apps.forever.sample.domain.model.Message
import apps.forever.sample.ui.viewmodels.MainViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideData()

        lifecycleScope.launch {
            mainViewModel.getMessages().collect { it ->
                when (it.status) {
                    NetworkResponse.STATUS.INPROGRESS -> {
                        hideData()
                    }
                    NetworkResponse.STATUS.SUCCESS -> {
                        val data = it.data as List<MessagesDto>
                        val messageData = data.map { it.toMessage() }
                        if (messageData.isNotEmpty()) {
                            setupRecyclerView(messageData)
                            showData()
                        } else {
                            hideData()
                        }
                    }

                    NetworkResponse.STATUS.ERROR -> {
                        hideData()
                    }
                }

            }
        }


    }

    private fun setupRecyclerView(messages: List<Message>) {
        val messagesAdapter: MessagesAdapter by inject {
            parametersOf(
                messages
            )
        }
        binding.rvMessages.apply {
            adapter = messagesAdapter
        }
        setupSearchFunctionality(messagesAdapter)
    }

    private fun setupSearchFunctionality(messagesAdapter: MessagesAdapter) {
        binding.etSearchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                messagesAdapter.filter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun showData() {
        binding.pbVisiblity = false
        binding.messagesVisiblity = true
        binding.searchVisiblity = true
    }

    private fun hideData() {
        binding.pbVisiblity = true
        binding.messagesVisiblity = false
        binding.searchVisiblity = false
    }

}