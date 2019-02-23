package com.example.i312_04.projectandroid0208;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by gy2 on 2018-05-20.
 */

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    public static final String TAG = "CameraSurfaceView";

    private SurfaceHolder mHolder;
    private Camera mCamera = null;

    public CameraSurfaceView(Context context) {
        super(context);

        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
        mCamera.startPreview();
    }

    public void surfaceCreated(SurfaceHolder holder) {
        openCamera();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        stopPreview();
    }

    public Surface getSurface() {
        return mHolder.getSurface();
    }

    public boolean capture(Camera.PictureCallback jpegHandler) {
        if (mCamera != null) {
            mCamera.takePicture(null, null, jpegHandler);
            return true;
        } else {
            return false;
        }
    }

    public void stopPreview() {
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }
    public void startPreview() {
        openCamera();
        mCamera.startPreview();
    }

    public void openCamera() {
        mCamera = Camera.open();
        try {
            mCamera.setPreviewDisplay(mHolder);
        } catch (Exception ex) {
            Log.e(TAG, "Failed to set camera preview display", ex);
        }
    }

}

