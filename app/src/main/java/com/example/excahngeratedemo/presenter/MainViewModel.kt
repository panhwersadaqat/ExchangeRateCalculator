package com.example.excahngeratedemo.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.excahngeratedemo.data.models.Rates
import com.example.excahngeratedemo.domain.MainRepository
import com.example.excahngeratedemo.util.DispatcherProvider
import com.example.excahngeratedemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    sealed class CurrencyEvent {
        class Success(val resultText: String): CurrencyEvent()
        class Failure(val errorText: String): CurrencyEvent()
        object Loading: CurrencyEvent()
        object Empty: CurrencyEvent()
    }

    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: StateFlow<CurrencyEvent> = _conversion

    fun convert(amount: String, fromCurrency: String, toCurrency: String) {
        val fromAmount = amount.toFloatOrNull()
        if (fromAmount == null) {
            _conversion.value = CurrencyEvent.Failure("Not a valid amount")
            return
        }

        /**
        launched background thread for network call
         */
        viewModelScope.launch(dispatchers.io) {
            _conversion.value = CurrencyEvent.Loading
            when(val rateResponse = mainRepository.getRates(fromCurrency)) {
                is Resource.Error -> _conversion.value =
                    CurrencyEvent.Failure(rateResponse.message!!)
                is Resource.Success -> {
                    val rates = rateResponse.data!!.rates
                    val rate = getRateForCurrency(toCurrency, rates)

                    if (rate == null) {
                        _conversion.value = CurrencyEvent.Failure("Currency not found")
                    } else {
                        val convertedCurrency = (fromAmount * rate * 100).roundToInt() / 100
                        _conversion.value = CurrencyEvent.Success(
                            "$fromAmount $fromCurrency = $convertedCurrency $toCurrency"
                        )
                    }
                }
            }
        }
    }

    private fun getRateForCurrency(currency: String, rates: Rates) = when (currency) {
        "USD" -> rates.uSD
        "AED" -> rates.aED
        "ARS" -> rates.aRS
        "AUD" -> rates.aUD
        "CAD" -> rates.cAD
        else -> null
    }
}