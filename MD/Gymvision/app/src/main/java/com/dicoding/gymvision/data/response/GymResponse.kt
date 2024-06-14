package com.dicoding.gymvision.data.response

import com.google.gson.annotations.SerializedName

data class GymResponse(

	@field:SerializedName("latihan")
	val latihan: String? = null,

	@field:SerializedName("hasil")
	val hasil: String? = null
)
