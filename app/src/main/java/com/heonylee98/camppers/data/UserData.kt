package com.heonylee98.camppers.data

import android.net.Uri

data class UserData(val userUid: String,
                    val userName: String,
                    val userEmail: String,
                    val userImage: Uri?,
                    val userArea: String?)
