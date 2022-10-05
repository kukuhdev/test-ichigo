package com.eve.testichigo.core.utils

import android.content.Context
import android.content.SharedPreferences

@Suppress("UNCHECKED_CAST")
class SharedPrefs (private val context: Context) {
    companion object {
        private const val PREF = "MyAppPrefName"
        private const val PREF_IS_LOGIN = "is_login"
        private const val PREF_TOKEN = "user_token"
        private const val PREF_KOMUNITAS_ID = "komunitas_id"
        private const val PREF_NAMA = "nama"
        private const val PREF_EMAIL = "email"
        private const val PREF_IMAGE = "image"
        private const val PREF_KODE_PROVINSI = "kode_provinsi"
        private const val PREF_NAMA_PROVINSI = "nama_provinsi"
        private const val PREF_KODE_KABUPATEN = "kode_kabupaten"
        private const val PREF_NAMA_KABUPATEN = "nama_kabupaten"
        private const val PREF_KODE_KECAMATAN = "kode_kecamatan"
        private const val PREF_NAMA_KECAMATAN = "nama_kecamatan"
        private const val PREF_NAMA_PIC = "nama_pic"
        private const val PREF_PHONE_PIC = "phone_pic"
        private const val PREF_OBJECT_BUDAYA_ID = "objek_budaya_id"
        private const val PREF_KATEGORI_BUDAYA_ID = "kategori_budaya_id"
        private const val PREF_ALAMAT = "alamat"
        private const val PREF_LATITUDE = "latitude"
        private const val PREF_LONGITUDE = "longitude"
        private const val PREF_BIO = "bio"
        private const val PREF_VISI = "visi"
        private const val PREF_MISI = "misi"
        private const val PREF_FACEBOOK = "facebook"
        private const val PREF_INSTAGRAM = "instagram"
        private const val PREF_TWITTER = "twitter"
        private const val PREF_TIKTOK = "tiktok"
        private const val PREF_YOUTUBE = "youtube"
        private const val PREF_PRESTASI = "prestasi"
        private const val PREF_VERIFIED_AT = "verified_at"
        private const val PREF_OBJECT_BUDAYA_TEXT = "objek_budaya_text"
        private const val PREF_KATEGORI_BUDAYA_TEXT = "kategori_budaya_text"
        private const val PREF_IS_VERIFIED = "is_verified"
    }

    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

//    fun saveLoginPref(loginResponse: LoginResponse){
//        val data = loginResponse.dATA?.komunitas
//        put(PREF_IS_LOGIN, true)
//        put(PREF_TOKEN, loginResponse.dATA?.token)
//        put(PREF_KOMUNITAS_ID, loginResponse.dATA?.token)
//        put(PREF_NAMA, data?.nama)
//        put(PREF_EMAIL, data?.email)
//        put(PREF_IMAGE, data?.image)
//        put(PREF_KODE_PROVINSI, data?.kodeProvinsi)
//        put(PREF_NAMA_PROVINSI, data?.namaProvinsi)
//        put(PREF_KODE_KABUPATEN, data?.kodeKabupaten)
//        put(PREF_NAMA_KABUPATEN, data?.namaKabupaten)
//        put(PREF_KODE_KECAMATAN, data?.kodeKecamatan)
//        put(PREF_NAMA_KECAMATAN, data?.namaKecamatan)
//        put(PREF_NAMA_PIC, data?.namaPic)
//        put(PREF_PHONE_PIC, data?.phonePic)
//        put(PREF_OBJECT_BUDAYA_ID, data?.objekBudayaId)
//        put(PREF_KATEGORI_BUDAYA_ID, "")
//        put(PREF_ALAMAT, data?.alamat)
//        put(PREF_LATITUDE, data?.latitude)
//        put(PREF_LONGITUDE, data?.longitude)
//        put(PREF_BIO, data?.bio)
//        put(PREF_VISI, data?.visi)
//        put(PREF_MISI, data?.misi)
//        put(PREF_FACEBOOK, data?.facebook)
//        put(PREF_INSTAGRAM, data?.instagram)
//        put(PREF_TWITTER, data?.twitter)
//        put(PREF_TIKTOK, data?.tiktok)
//        put(PREF_YOUTUBE, data?.youtube)
//        put(PREF_PRESTASI, data?.prestasi)
//        put(PREF_OBJECT_BUDAYA_TEXT, data?.objekBudayaText)
//        put(PREF_KATEGORI_BUDAYA_TEXT, data?.kategoriBudayaText)
//        put(PREF_IS_VERIFIED, data?.isVerified)
//
//    }

    fun isLogin() : Boolean {
        return get(PREF_IS_LOGIN, Boolean::class.java)
    }

    fun getToken() : String {
        return get(PREF_TOKEN, String::class.java)
    }

    fun getMemberId() : Int {
        return get(PREF_KOMUNITAS_ID, Int::class.java)
    }

    fun getName() : String {
        return get(PREF_NAMA, String::class.java)
    }

    fun getEmail() : String {
        return get(PREF_EMAIL, String::class.java)
    }

    fun getImage() : String {
        return get(PREF_IMAGE, String::class.java)
    }

    fun getKodePovinsi() : String {
        return get(PREF_KODE_PROVINSI, String::class.java)
    }

    fun getNamaProvinsi() : String {
        return get(PREF_NAMA_PROVINSI, String::class.java)
    }

    fun getKodeKabupaten() : String {
        return get(PREF_KODE_KABUPATEN, String::class.java)
    }

    fun getNamaKabupaten() : String {
        return get(PREF_NAMA_KABUPATEN, String::class.java)
    }

    fun getKodeKecamatan() : String {
        return get(PREF_KODE_KECAMATAN, String::class.java)
    }

    fun getNamaKecamatan() : String {
        return get(PREF_NAMA_KECAMATAN, String::class.java)
    }

    fun getNamaPic() : String {
        return get(PREF_NAMA_PIC, String::class.java)
    }

    fun getPhonePic() : String {
        return get(PREF_PHONE_PIC, String::class.java)
    }

    fun getObjectBudayaId() : Int {
        return get(PREF_OBJECT_BUDAYA_ID, Int::class.java)
    }

    fun getKategoriBudayaId() : String {
        return get(PREF_KATEGORI_BUDAYA_ID, String::class.java)
    }

    fun getAlamat() : String {
        return get(PREF_ALAMAT, String::class.java)
    }

    fun getLatitude() : String {
        return get(PREF_LATITUDE, String::class.java)
    }

    fun getLongitude() : String {
        return get(PREF_LONGITUDE, String::class.java)
    }

    fun getBio() : String {
        return get(PREF_BIO, String::class.java)
    }

    fun getVisi() : String {
        return get(PREF_VISI, String::class.java)
    }

    fun getMisi() : String {
        return get(PREF_MISI, String::class.java)
    }

    fun getFacebook() : String {
        return get(PREF_FACEBOOK, String::class.java)
    }

    fun getInstagram() : String {
        return get(PREF_INSTAGRAM, String::class.java)
    }

    fun getTwitter() : String {
        return get(PREF_TWITTER, String::class.java)
    }

    fun getTiktok() : String {
        return get(PREF_TIKTOK, String::class.java)
    }

    fun getYoutube() : String {
        return get(PREF_YOUTUBE, String::class.java)
    }

    fun getPrestasi() : String {
        return get(PREF_PRESTASI, String::class.java)
    }

    fun getObjectBudayaText() : String {
        return get(PREF_OBJECT_BUDAYA_TEXT, String::class.java)
    }

    fun getKategoriBudayaText() : String {
        return get(PREF_KATEGORI_BUDAYA_TEXT, String::class.java)
    }

    fun getIsVerified() : Int {
        return get(PREF_IS_VERIFIED, Int::class.java)
    }



    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, "")
            Boolean::class.java -> sharedPref.getBoolean(key, false)
            Float::class.java -> sharedPref.getFloat(key, -1f)
            Double::class.java -> sharedPref.getFloat(key, -1f)
            Int::class.java -> sharedPref.getInt(key, -1)
            Long::class.java -> sharedPref.getLong(key, -1L)
            else -> null
        } as T

    private fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    fun clear() {
        sharedPref.edit().run {
            remove(PREF_TOKEN)
        }.apply()
    }
}