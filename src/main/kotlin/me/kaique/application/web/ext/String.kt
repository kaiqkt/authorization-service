package me.kaique.application.web.ext

//Min 1 uppercase letter.
//Min 1 lowercase letter.
//Min 1 special character.
//Min 1 number.
//Min 8 characters.
//Max 30 characters.

const val MAIL_REGEX = "^([a-zA-Z0-9!#\$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#\$%&'*+\\/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?)\$"
const val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~\$^+=<>]).{8,20}\$"

fun String.isEmailValid(): Boolean = this.isNotBlank() && Regex(MAIL_REGEX).matches(this)

fun String.isPasswordValid(): Boolean = this.isNotBlank() && Regex(PASSWORD_REGEX).matches(this)