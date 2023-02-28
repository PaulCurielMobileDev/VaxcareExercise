package com.mexicandeveloper.vaxcareexercise.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mexicandeveloper.vaxcareexercise.models.Book
import com.mexicandeveloper.vaxcareexercise.repository.MainRepository
import com.mexicandeveloper.vaxcareexercise.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _res = MutableLiveData<Resource<List<Book>>>()
    val res: LiveData<Resource<List<Book>>>
        get() = _res


    fun doSomething() {
        _res.postValue(Resource.loading(null))
    }

    fun getBooks() = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getBooks().let {
            if (it.isSuccessful) {
                _res.postValue(Resource.success(it.body()))
            } else {
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }

        }
    }


}