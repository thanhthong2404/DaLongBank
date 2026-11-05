package com.example.studijetpack.feature.account.profile

data class CustomerProfile(
    val fullName: String,
    val customerId: String,
    val dateOfBirth: String,
    val gender: String,
    val phone: String,
    val email: String,
    val identityNumber: String,
    val identityIssueDate: String,
    val identityIssuePlace: String,
    val permanentAddress: String,
    val contactAddress: String,
    val registrationDate: String,
    val isVerified: Boolean,
    val isActive: Boolean
)

enum class ProfileField {
    FULL_NAME,
    DATE_OF_BIRTH,
    GENDER,
    PHONE,
    EMAIL,
    IDENTITY_NUMBER,
    PERMANENT_ADDRESS,
    CONTACT_ADDRESS
}