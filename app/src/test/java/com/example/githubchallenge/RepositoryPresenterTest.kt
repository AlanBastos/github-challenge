package com.example.githubchallenge

import com.example.githubchallenge.contract.RepositoryContract
import com.example.githubchallenge.model.GithubService
import com.example.githubchallenge.model.PullRequest
import com.example.githubchallenge.model.Repository
import com.example.githubchallenge.model.RepositoryResponse
import com.example.githubchallenge.presenter.GithubRepository
import com.example.githubchallenge.presenter.RepositoryPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RepositoryPresenterTest {

    private lateinit var view: RepositoryContract.View
    private lateinit var githubRepository: GithubRepository
    private lateinit var presenter: RepositoryPresenter

    @Before
    fun setup() {
        view = mock()
        githubRepository = mock()
        presenter = RepositoryPresenter(view, githubRepository)
    }

    @Test
    fun testGetJavaPopRepositories_Success() {
        val mockResponse: Response<RepositoryResponse> = mock()
        val mockCall: Call<RepositoryResponse> = mock()
        val emptyRepositoryList = emptyList<Repository>()
        whenever(mockResponse.isSuccessful).thenReturn(true)
        whenever(mockResponse.body()).thenReturn(RepositoryResponse(0, false, emptyRepositoryList))

        whenever(githubRepository.getGithubService()).thenReturn(mock())
        whenever(githubRepository.getGithubService().getJavaPopRepositories(page = 1)).thenReturn(mockCall)

        presenter.getJavaPopRepositories(page = 1)

        verify(githubRepository.getGithubService()).getJavaPopRepositories(
            "language:java",
            "stars",
            1
        )

        verify(mockCall).enqueue(any())

        val callBackCaptor = argumentCaptor<Callback<RepositoryResponse>>()
        verify(mockCall).enqueue(callBackCaptor.capture())

        callBackCaptor.firstValue.onResponse(mockCall, mockResponse)

        verify(view).showRepositories(any())

    }

    @Test
    fun testGetJavaPopRepositories_Fail() {}

    @Test
    fun testGetPullRequests_Success() {
        // Criação dos mocks necessários
        val mockResponse: Response<List<PullRequest>> = mock()
        val mockCall: Call<List<PullRequest>> = mock()

        // Configuração do comportamento de stubbing
        whenever(mockResponse.isSuccessful).thenReturn(true)
        whenever(mockResponse.body()).thenReturn(listOf(PullRequest(
            1,
            "Pull request",
            null,
            "2024-05-09T08:47:37Z",
            null,
            "open"
        )))

        // Configuração do comportamento do mock do serviço
        val mockGithubService: GithubService = mock()
        whenever(mockGithubService.getPullRequests(any(), any(), any(), any())).thenReturn(mockCall)
        whenever(githubRepository.getGithubService()).thenReturn(mockGithubService)

        // Execução do método a ser testado
        presenter.getPullRequests("AlanBastos", "github-challenge")

        // Verificação do comportamento esperado
        verify(githubRepository.getGithubService()).getPullRequests(
            "AlanBastos",
            "github-challenge",
            "all", // Corrigido para "all" de acordo com a implementação real
            1
        )
        verify(mockCall).enqueue(any())

        val callBackCaptor = argumentCaptor<Callback<List<PullRequest>>>()
        verify(mockCall).enqueue(callBackCaptor.capture())

        callBackCaptor.firstValue.onResponse(mockCall, mockResponse)

        verify(view).showPullRequests(any())
    }


}