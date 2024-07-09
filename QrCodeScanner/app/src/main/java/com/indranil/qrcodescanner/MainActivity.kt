package com.indranil.qrcodescanner

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.indranil.qrcodescanner.ui.theme.QrCodeScannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QrCodeScannerTheme {
                // Use Scaffold for basic Material Design layout structure
                Scaffold { paddingValues ->
                    var code by remember {
                        mutableStateOf("")
                    }
                    val context = LocalContext.current
                    val lifecycleOwner = LocalLifecycleOwner.current
                    val cameraProviderFuture = remember {
                        ProcessCameraProvider.getInstance(context)
                    }
                    var hasCameraPermission by remember {
                        mutableStateOf(
                            ContextCompat.checkSelfPermission(
                                context,
                                android.Manifest.permission.CAMERA
                            ) == PackageManager.PERMISSION_GRANTED
                        )
                    }
                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = { granted ->
                            hasCameraPermission = granted
                        })
                    LaunchedEffect(key1 = true) {
                        launcher.launch(android.Manifest.permission.CAMERA)
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues) // Apply padding from Scaffold
                    ) {
                        if (hasCameraPermission) {
                            AndroidView(
                                factory = { context ->
                                    val previewView = PreviewView(context)
                                    val preview = Preview.Builder().build()
                                    val selector = CameraSelector.Builder()
                                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                                        .build()
                                    preview.setSurfaceProvider(previewView.surfaceProvider)
                                    val imageAnalysis = ImageAnalysis.Builder()
                                        .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_YUV_420_888) // Recommended format
                                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                        .build()
// Set the analyzer after building the ImageAnalysis instance
                                    imageAnalysis.setAnalyzer(
                                        ContextCompat.getMainExecutor(context),
                                        QrCodeAnalyzer { result ->
                                            code = result
                                        }
                                    )
                                    try {
                                        cameraProviderFuture.get().bindToLifecycle(
                                            lifecycleOwner,
                                            selector,
                                            preview,
                                            imageAnalysis
                                        )
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
                                    previewView
                                },
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = code,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp)
                            )
                        } else {
                            // Display a message when camera permission is denied
                            Text("Camera permission is required.")
                        }
                    }
                }
            }
        }
    }
}