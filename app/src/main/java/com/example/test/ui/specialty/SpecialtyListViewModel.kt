package com.example.test.ui.specialty

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.test.data.db.Specialty
import com.example.test.extensions.addTo
import com.example.test.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class SpecialtyListViewModel
@Inject
constructor(private val repository: Repository) : ViewModel() {

    private val disposables = CompositeDisposable()

    fun getSpecialties(errorAction: () -> Unit): LiveData<List<Specialty>> {
        refresh(errorAction)
        return repository.getListOfSpecialties()
    }

    private fun refresh(errorAction: () -> Unit) {
        repository.refresh()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {
                errorAction.invoke()
                it.printStackTrace()
            }).addTo(disposables)

    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}