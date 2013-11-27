package com.welcu.android.zxingfragmentlib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.welcu.android.zxingfragmentlib.camera.CameraManager;

import java.util.Collection;
import java.util.Map;

/**
 * Created by mito on 9/15/13.
 */
public class BarCodeScannerHandler extends Handler implements IConstants {

    private static final String TAG = BarCodeScannerHandler.class.getSimpleName();
    private final DecodeThread decodeThread;
    private final CameraManager cameraManager;
    private final BarCodeScannerFragment fragment;

    private State state;

    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    BarCodeScannerHandler(BarCodeScannerFragment fragment,
                          Collection<BarcodeFormat> decodeFormats,
                          Map<DecodeHintType,?> baseHints,
                          String characterSet,
                          CameraManager cameraManager) {
        this.fragment = fragment;
        decodeThread = new DecodeThread(fragment, decodeFormats, baseHints, characterSet,
                new ViewfinderResultPointCallback(fragment.getViewfinderView()));
        decodeThread.start();
        state = State.SUCCESS;

        this.cameraManager = cameraManager;
        cameraManager.startPreview();
        restartPreviewAndDecode();
    }

    @Override
    public void handleMessage(Message message) {
        switch (message.what) {
            case RESTART_PREVIEW:
                restartPreviewAndDecode();       
                break;
            case DECODE_SUCCEDED:
                Log.v(TAG, "Decode SUCCEEDED");
                state = State.SUCCESS;
                Bundle bundle = message.getData();
                Bitmap barcode = null;
                float scaleFactor = 1.0f;
                if (bundle != null) {
                    byte[] compressedBitmap = bundle.getByteArray(DecodeThread.BARCODE_BITMAP);
                    if (compressedBitmap != null) {
                        barcode = BitmapFactory.decodeByteArray(compressedBitmap, 0, compressedBitmap.length, null);
                        // Mutable copy:
                        barcode = barcode.copy(Bitmap.Config.ARGB_8888, true);
                    }
                    scaleFactor = bundle.getFloat(DecodeThread.BARCODE_SCALED_FACTOR);
                }
                fragment.handleDecode((Result) message.obj, barcode, scaleFactor);
                break;
            case DECODE_FAILED:
                state = State.PREVIEW;
                cameraManager.requestPreviewFrame(decodeThread.getHandler(), DECODE);
                break;
            default:
                Log.v(TAG, "Unknown message: "+message.what);
        }
    }

    public void quitSynchronously() {
        state = State.DONE;
        cameraManager.stopPreview();
        Message quit = Message.obtain(decodeThread.getHandler(), QUIT);
        quit.sendToTarget();
        try {
            // Wait at most half a second; should be enough time, and onPause() will timeout quickly
            decodeThread.join(500L);
        } catch (InterruptedException e) {
            // continue
        }

        // Be absolutely sure we don't send any queued up messages
        removeMessages(DECODE_SUCCEDED);
        removeMessages(DECODE_FAILED);
    }

    private void restartPreviewAndDecode() {
        if (state == State.SUCCESS) {
            state = State.PREVIEW;
            cameraManager.requestPreviewFrame(decodeThread.getHandler(), DECODE);
            fragment.drawViewfinder();
        }
    }
}
