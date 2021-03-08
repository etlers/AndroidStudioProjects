package com.etlers.kalecture09

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.view.KeyEventDispatcher
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.security.Permission
import java.text.SimpleDateFormat
import java.util.*

/*
    Camera (카메라)
 */
class MainActivity : AppCompatActivity() {

    // 카메라 사진 촬영 요청코드 임의로 생성
    val REQUEST_IMAGE_CAPTURE = 1
    // 문자열 형태의 사진 경로 값. 늦게 초기화 된다. 널 초기화 된다
    lateinit var curPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPermission()

        btnCamera.setOnClickListener {
            takeCapture()
        }
    }

    /*
        카메라 촬영
     */
    private fun takeCapture() {
        // 기본 카메라 앱 실행
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }

                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                            this,
                            "com.etlers.kaLecture09.fileprovider",
                            it
                    )

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    // 앱에서의 결과 값을 메인으로 돌려준다
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    /*
        이미지 파일 생성
     */
    private fun createImageFile(): File? {
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storagedir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", storagedir)
                .apply { curPhotoPath = absolutePath }
    }

    /*
        ted permission 설정
     */
    private fun setPermission() {
        val permission = object : PermissionListener {
            // 설정한 권한 허용인 경우
            override fun onPermissionGranted() {
                Toast.makeText(this@MainActivity, "권한이 허용 됐습니다.", Toast.LENGTH_SHORT).show()
            }
            // 거부한 경우
            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(this@MainActivity, "권한이 거부 됐습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        TedPermission.with(this)
                .setPermissionListener(permission)
                .setRationaleMessage("카메라 앱을 사용하시면 권한을 허용해 주세요.")
                .setDeniedMessage("권한을 거부 했습니다.")
                .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
                .check()
    }

    /*
        'startActivityForResult'를 통해서 기본 카메라 앱으로 부터 받아온 사진 결과 값
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 이미지를 성공적으로 가져왔다면
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val bitmap: Bitmap
            val file = File(curPhotoPath)
            // 버전에 따라 이미지 가져오는 방식을 다르게 해줘야 함. android 9.0(Pie) 이전 버전
            if (Build.VERSION.SDK_INT < 28) {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(file))
                ivProfile.setImageBitmap(bitmap)
            } else {
                val decode = ImageDecoder.createSource(
                        this.contentResolver,
                        Uri.fromFile(file)
                )
                bitmap = ImageDecoder.decodeBitmap(decode)
                ivProfile.setImageBitmap(bitmap)
            }
            // 갤러리에 저장
            savePhoto(bitmap)
        }
    }

    /*
        갤러리에 저장
     */
    private fun savePhoto(bitmap: Bitmap) {
        // 사진 폴더로 저장하기 위한 경로 선언
        val folderPath = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/"
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val fileName = "${timestamp}.jpeg"
        val folder = File(folderPath)
        // 촐더가 없다면
        if (!folder.isDirectory) {
            folder.mkdirs()
        }

        val out = FileOutputStream(folderPath + fileName)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        Toast.makeText(this, "사진이 저장 되었습니다.", Toast.LENGTH_SHORT).show()
    }
}