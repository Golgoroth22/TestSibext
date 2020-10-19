package com.vfalin.sibext

import com.vfalin.sibext.network.pojo.FilmsResponse
import com.vfalin.sibext.presenters.FilmsActivityPresenter
import com.vfalin.sibext.ui.activities.FilmsActivityContract
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions

class FilmsActivityPresenterTest {
    @RelaxedMockK
    lateinit var view: FilmsActivityContract.View

    @RelaxedMockK
    lateinit var repository: FilmsActivityContract.Model

    private lateinit var presenter: FilmsActivityContract.Presenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = FilmsActivityPresenter(repository)
        presenter.onAttach(view)
    }

    @After
    fun unmock() {
        unmockkAll()
        presenter.onDestroy()
    }

    @Test
    fun testReturnEmptyFilmsResult() {
        val dataCallback = slot<(data: FilmsResponse) -> Unit>()
        val errorCallback = slot<(error: Throwable) -> Unit>()
        every { repository.getFilms(capture(dataCallback), capture(errorCallback)) } answers {
            val fakeData = FilmsResponse(emptyList())
            dataCallback.captured.invoke(fakeData)
        }
        presenter.loadFilms()

        val captureData = slot<FilmsResponse>()

        verify(exactly = 1) {
            view.showAllFilms(capture(captureData).films.map { it.convertTo() })
        }
        captureData.captured.let { response ->
            Assertions.assertNotNull(response)
            Assertions.assertTrue(response.films.isEmpty())
        }
    }

    @Test
    fun testReturnWithAnException() {
        val dataCallback = slot<(data: FilmsResponse) -> Unit>()
        val errorCallback = slot<(error: Throwable) -> Unit>()
        every { repository.getFilms(capture(dataCallback), capture(errorCallback)) } answers {
            errorCallback.invoke(Throwable("Houston we have a problem"))
        }
        presenter.loadFilms()

        val captureData = slot<String>()

        verify(exactly = 1) {
            view.showMessage(capture(captureData))
        }
        captureData.captured.let { response ->
            Assertions.assertEquals("Houston we have a problem", response)
            Assertions.assertNotNull(response)
        }
    }
}