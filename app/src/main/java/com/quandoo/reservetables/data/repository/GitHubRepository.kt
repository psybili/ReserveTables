package com.quandoo.reservetables.data.repository

import com.quandoo.reservetables.data.api.GitHubService
import javax.inject.Inject

class GitHubRepository @Inject constructor(private val gitHubService: GitHubService) {

  fun loadRepos(owner: String) = gitHubService.listRepos(owner)

}
