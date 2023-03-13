package com.shcoding.notediary.data.repository

import com.shcoding.notediary.BuildConfig
import com.shcoding.notediary.common.utils.RequestState
import com.shcoding.notediary.common.utils.toInstant
import com.shcoding.notediary.domain.model.Diary
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.ZoneId

object MongoDBRepositoryImpl : MongoDBRepository {

    private val app = App.create(BuildConfig.APP_ID)
    private val user = app.currentUser
    private lateinit var realm: Realm

    init {
        configuraTheRealm()
    }

    override fun configuraTheRealm() {
        if (user != null) {
            val config = SyncConfiguration.Builder(user, setOf(Diary::class))
                .initialSubscriptions { sub ->
                    add(
                        query = sub.query<Diary>("ownerId == $0", user.id),
                        name = "User's Diaries"
                    )
                }
                .log(level = LogLevel.ALL)
                .build()

            realm = Realm.open(config)
        }
    }

    override fun getAllDiaries(): Flow<Diaries> {
        return if (user != null) {
            try {
                realm.query<Diary>(query = "ownerId == $0", user.id)
                    .sort(property = "date", sortOrder = Sort.DESCENDING)
                    .asFlow()
                    .map { result ->
                        RequestState.Success(
                            data = result.list.groupBy { diary ->

                                diary.date.toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()

                            }
                        )

                    }

            } catch (e: Exception) {
                flow { emit(RequestState.Error(e)) }
            }
        } else {
            flow { emit(RequestState.Error(UserAuthenticationException())) }
        }
    }
}

private class UserAuthenticationException : Exception("User is not logged in.")