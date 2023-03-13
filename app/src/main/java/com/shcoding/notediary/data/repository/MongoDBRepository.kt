package com.shcoding.notediary.data.repository

import com.shcoding.notediary.common.utils.RequestState
import com.shcoding.notediary.domain.model.Diary
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

typealias Diaries = RequestState<Map<LocalDate,List<Diary>>>

interface MongoDBRepository {
    fun configuraTheRealm()
    fun getAllDiaries(): Flow<Diaries>
}