/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.akash.githubissuesapp.db

import androidx.room.TypeConverter
import com.akash.githubissuesapp.vo.Assignee
import com.akash.githubissuesapp.vo.Label
import com.akash.githubissuesapp.vo.PullRequest
import com.akash.githubissuesapp.vo.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

object GithubTypeConverters {
    @TypeConverter
    @JvmStatic
    fun stringToLabelList(data: String?): List<Label> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<Label?>?>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun assigneeListToString(assigneeList: List<Assignee?>?): String {
        return if(assigneeList != null){ Gson().toJson(assigneeList) } else ""
    }

    @TypeConverter
    @JvmStatic
    fun stringToAssigneeList(data: String?): List<Assignee> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<Assignee?>?>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun labelListToString(labelList: List<Label?>?): String {
        return if(labelList != null) { Gson().toJson(labelList) } else ""
    }

    @TypeConverter
    @JvmStatic
    fun stringToUser(data: String?): User? {
        return if(data != null ){Gson().fromJson(data, User::class.java)} else null
    }

    @TypeConverter
    @JvmStatic
    fun userToString(user: User?): String {
        return if(user != null ){Gson().toJson(user)} else ""
    }

    @TypeConverter
    @JvmStatic
    fun stringToAny(data: String?): Any? {
        return if(data != null) {Gson().fromJson(data, Any::class.java)} else null
    }

    @TypeConverter
    @JvmStatic
    fun anyToString(any: Any?): String {
        return if(any != null) {Gson().toJson(any)} else ""
    }

    @TypeConverter
    @JvmStatic
    fun stringToPullRequest(data: String?): PullRequest? {
        return if(data != null ){Gson().fromJson(data, PullRequest::class.java)} else null
    }

    @TypeConverter
    @JvmStatic
    fun pullRequestToString(pullRequest: PullRequest?): String {
        return if(pullRequest != null ) {Gson().toJson(pullRequest)} else ""
    }

//    @TypeConverter
//    @JvmStatic
//    fun stringToAssignee(data: String?): Assignee {
//        return Gson().fromJson(data, Assignee::class.java)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun assigneeToString(assignee: Assignee): String {
//        return Gson().toJson(assignee)
//    }
}
