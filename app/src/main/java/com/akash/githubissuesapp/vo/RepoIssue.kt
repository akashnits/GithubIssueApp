package com.akash.githubissuesapp.vo

import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by akash on 25,July,2020
 */
@Entity(primaryKeys = ["id"])
data class RepoIssue (
    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("repository_url")
    @Expose
    var repositoryUrl: String? = null,

    @SerializedName("labels_url")
    @Expose
    var labelsUrl: String? = null,

    @SerializedName("comments_url")
    @Expose
    var commentsUrl: String? = null,

    @SerializedName("events_url")
    @Expose
    var eventsUrl: String? = null,

    @SerializedName("html_url")
    @Expose
    var htmlUrl: String? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("node_id")
    @Expose
    var nodeId: String? = null,

    @SerializedName("number")
    @Expose
    var number: Int? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("user")
    @Expose
    var user: User? = null,

    @SerializedName("labels")
    @Expose
    var labels: List<Label>? = null,

    @SerializedName("state")
    @Expose
    var state: String? = null,

    @SerializedName("locked")
    @Expose
    var locked: Boolean? = null,

    @SerializedName("assignee")
    @Expose
    var assignee: Any? = null,

    @SerializedName("assignees")
    @Expose
    var assignees: List<Assignee>? = null,

    @SerializedName("milestone")
    @Expose
    var milestone: Any? = null,

    @SerializedName("comments")
    @Expose
    var comments: Int? = null,

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null,

    @SerializedName("closed_at")
    @Expose
    var closedAt: String? = null,

    @SerializedName("author_association")
    @Expose
    var authorAssociation: String? = null,

    @SerializedName("active_lock_reason")
    @Expose
    var activeLockReason: Any? = null,

    @SerializedName("pull_request")
    @Expose
    var pullRequest: PullRequest? = null,

    @SerializedName("body")
    @Expose
    var body: String? = null,

    @SerializedName("performed_via_github_app")
    @Expose
    var performedViaGithubApp: Any? = null

){
    // does not show up in the response but set in post processing.
    @SerializedName("org")
    @Expose
    lateinit var org: String
    // does not show up in the response but set in post processing.
    @SerializedName("repo")
    @Expose
    lateinit var repo: String
}