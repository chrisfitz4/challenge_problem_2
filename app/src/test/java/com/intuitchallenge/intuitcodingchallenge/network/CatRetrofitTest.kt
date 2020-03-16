package com.intuitchallenge.intuitcodingchallenge.network

import org.junit.Test

import org.junit.Assert.*

class CatRetrofitTest {

    @Test
    fun searchCat() {
        val myCatRetrofit = CatRetrofit
        val response = myCatRetrofit.searchCat("beng", true, 10)
            .blockingFirst()
        assertNotNull(response)
        assert(response[0].url.contains(".jpg"))
        assertEquals("aaxNf4D0H".length,response[0].id.length)
    }


}