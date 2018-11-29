package com.example.data

import com.example.data.database.LocalDataSource
import com.example.data.datasource.CountryModelMapperImpl
import com.example.data.datasource.DataRepository
import com.example.data.remote.RemoteDataSource
import com.example.domain.domain.CountryPojo
import com.example.georgeissac.mvp.domain.searchCountryUseCase.request.Request
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Maybe
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test

class DataRepositoryTest {

    private val localDataSource: LocalDataSource = mock()

    private val remoteDataSource: RemoteDataSource = mock()

    private val testSubject: DataRepository = DataRepository(localDataSource, remoteDataSource)

    private val request: Request = mock()

    private val testString = "ind"
    var list = ArrayList<CountryPojo>();
    val country = CountryPojo("india", "ind", "in", "jjdd")

    /*private fun given_noteFromEntity(noteEntity: NoteEntity): Note {
        val note: Note = mock()
        whenever(noteMapper.fromEntity(noteEntity)).thenReturn(note)
        whenever(note.id).thenReturn(noteEntity.id)
        whenever(note.noteText).thenReturn(noteEntity.noteText)
        return note
    }


    val noteEntity = noteEntity()
    val note = given_noteFromEntity(noteEntity)
    whenever(noteDao.findNoteById(any())).thenReturn(Maybe.just(noteEntity))

    // WHEN
    val testObserver = testSubject.findNoteById(note.id).test()

    // THEN
    testObserver.assertResult(note)

    argumentCaptor<Long>().apply {
        verify(noteDao).findNoteById(capture())
        MatcherAssert.assertThat(firstValue, Matchers.`is`(note.id))
    }

    verify(noteMapper).fromEntity(noteEntity)
*/
    @Before
    fun setUp(){
        list.add(CountryPojo("india", "ind", "in", "jjdd"))
    }


    @Test
    fun searchCountryInDb_test(){
        whenever(request.searchString).thenReturn(testString)
        val mapper = CountryModelMapperImpl()
        whenever(localDataSource.searchByTextUsingRx(testString).map { mapper.fromEntity(it) }).thenReturn(Maybe.just(list))

        val testObservable = testSubject.searchCountryInDb(request).test()
    }

}