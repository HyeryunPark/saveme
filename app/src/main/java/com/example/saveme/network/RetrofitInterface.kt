package com.example.saveme.network

import com.example.saveme.missing.MissingModel
import com.example.saveme.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE


interface RetrofitInterface {

    /*
    @Path = (@Path("pk") int pk)에서 pk는 윗줄에 rest api 어노테이션 뒤에 사용하는 변수
    @Query = (@Query("example") String test)에서 test는 변수, 입력을 aaaa로 받았을때 url은 xxxxx?esample=aaaa
    @Field = (@Field("email") String test)에서 Test는 데이터베이스의 필드 하나에 해당함. 즉 email 레코드의 필드 하나 값
    @FormUrlEncoded = 입력된 스트링이나 해시맵을 데이터베이스에 반영될 수 있도록 인코딩해줌
    */


    // 회원가입
    @FormUrlEncoded
    @POST("/saveme_app/auth/register/")
    fun signUp(
        @Field("email") userEmail: String,
        @Field("username") userName: String,
        @Field("password") userPw: String
    ): Call<ResponseBody>

    // 로그인
/*    @GET("/users")
    fun logIn(@Query("userEmail") userEmail: String): Call<List<Json_User>>*/
    @FormUrlEncoded
    @POST("/saveme_app/auth/login/")
    fun logIn(
        @Field("username") userEmail: String,
        @Field("password") userPw: String
    ): Call<GetUser>

    // 로그아웃
    // 헤더에 KEY : Authorization, VALUE : Token 토큰값 으로 보내기
    @POST("/saveme_app/auth/logout/")
    fun logout(): Call<ResponseBody>

    // 사용자 정보 가져오기
    @GET("/saveme_app/auth/user/")
//    fun getUser(@Header("Authorization") token: String): Call<ResponseBody>
    fun getUser(): Call<userData>

    // 보호소 동물 리스트 받아오기
    @GET("/shelters")
    fun getShelterData(): Call<List<GetShelterList>>

    // 실종동물 글 리스트 조회
    @GET("/missings")
    fun getMissingData(): Call<List<GetMissingList>>

    // 실종동물 글 작성
    /*@POST("/missings/")
    fun createMissingData(@Body createMissingData: CreateMissing): Call<CreateMissing>*/
    @Multipart
    @POST("/missings/")
    fun createMissingData(
        @Part("status") status: RequestBody,
        @Part("date") date: RequestBody,
        @Part("city") city: RequestBody,
        @Part("district") district: RequestBody,
        @Part("detailLocation") detailLocation: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("species") species: RequestBody,
        @Part("breed") breed: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("neuter") neuter: Boolean,
        @Part("age") age: RequestBody,
        @Part("weight") weight: RequestBody,
        @Part("pattern") pattern: RequestBody,
        @Part("feature") feature: RequestBody,
        @Part("etc") etc: RequestBody,
        @Part image1: MultipartBody.Part,
        @Part image2: MultipartBody.Part?,
        @Part image3: MultipartBody.Part?
    ): Call<CreateMissing>

    // 실동종물 글 수정
    @PUT("/missings/{pk}/")
    fun updateMissingData(@Path("pk") pk: Int, @Body missingModel: MissingModel): Call<MissingModel>

    // 실종동물 글 삭제하기
    @DELETE("/missings/{pk}/")
    fun deleteMissingData(@Path("pk") pk: Int): Call<ResponseBody>

    // 커뮤니티 글 조회
    @GET("/community")
    fun getCommunityData(): Call<List<GetCommunityList>>

    // 커뮤니티 글 작성
    @Multipart
    @POST("/community/")
    fun createCommunityData(
        @Part("user_id") user_id: Int,
        @Part("community_category") community_category: RequestBody,
        @Part("community_title") community_title: RequestBody,
        @Part("community_content") community_content: RequestBody,
        @Part img1: MultipartBody.Part,
        @Part img2: MultipartBody.Part?,
        @Part img3: MultipartBody.Part?
    ): Call<CreateCommunity>

    // 커뮤니티 글 수정

    // 커뮤니티 글 삭제


    // 팝업 뉴스데이터 가져오기
    @GET("/newsdata")
    fun loadNewsData(): Call<List<GetPopUp>>


}