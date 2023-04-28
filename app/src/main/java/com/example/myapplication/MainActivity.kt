package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences


class MainActivity : AppCompatActivity() {


    private var edtText: EditText? = null
    private var btnGravar: Button? = null
    private var btnRecuperar:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        click()
    }

    fun initView() {
        edtText = findViewById(R.id.edtNome)
        btnGravar = findViewById(R.id.btn_salvar)
        btnRecuperar = findViewById(R.id.btn_ler)
    }

    fun gravar() {
        val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
            "exemplo2",
            "fiap",
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        with (sharedPreferences.edit()) {
            putString("teste",edtText?.text.toString())
            apply()
        }
    }

    fun leitura() {
        val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
            "exemplo2",
            "fiap",
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        edtText?.setText(sharedPreferences.getString("teste",""))
    }

    fun click() {
        btnGravar?.setOnClickListener {
            gravar()
        }

        btnRecuperar?.setOnClickListener {
            leitura()
        }
    }
}