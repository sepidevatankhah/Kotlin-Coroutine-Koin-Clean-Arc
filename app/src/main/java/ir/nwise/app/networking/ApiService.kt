package ir.nwise.app.networking

import ir.nwise.app.domain.model.SearchResult
import ir.nwise.app.domain.model.TopAlbums
import ir.nwise.app.domain.model.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST(Config.API_VERSION)
    fun getMobileSession(
        @Field("api_key") api_key: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("api_sig") api_sig: String,
        @Field("method") method: String,
        @Field("format") format: String
    ): Deferred<UserResponse>


    @FormUrlEncoded
    @POST(Config.API_VERSION)
    fun searchArtist(
        @Field("api_key") api_key: String,
        @Field("artist") artist: String,
        @Field("method") method: String,
        @Field("format") format: String,
        @Field("limit") limit: String,
        @Field("page") page: String
    ): Deferred<SearchResult>

    @FormUrlEncoded
    @POST(Config.API_VERSION)
    fun topAlbums(
        @Field("api_key") api_key: String,
        @Field("artist") artist: String,
        @Field("method") method: String,
        @Field("format") format: String
    ): Deferred<TopAlbums>
}