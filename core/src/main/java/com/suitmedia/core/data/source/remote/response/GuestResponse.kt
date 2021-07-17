package com.suitmedia.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GuestResponse(

	@field:SerializedName("birthdate")
	val birthdate: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
