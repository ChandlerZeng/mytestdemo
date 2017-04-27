package com.example.mytestdemo.txz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.util.Log;

/**
 * @author Zengcq
 * @date 2017年2月27日
 * @version 1.0
 * @description
 */
public class ScreenControl {
    public static final String BACKLIGHT_ENABLE_NODE = "/sys/devices/e6501800.agold620-backlight/bl_enable";
    public static final String TOUCHSCREEN_ENABLE_NODE = "/sys/bus/i2c/devices/i2c-4/4-005d/enable";
    private int mBackLightBrightness = 0;
    private static final String TAG = "RituNavi";

    private int getBackLightBrightness() {
        int level = -1;
        File localFile = new File("/sys/class/disp/disp/attr/xc_brightchange");
        if (!localFile.canRead()) {
            Log.e(TAG,
                    "xc /sys/class/disp/disp/attr/xc_brightchange can not read!");
            return level;
        }

        try {
            FileInputStream in = new FileInputStream(localFile);
            byte[] b = new byte[4];
            in.read(b);
            int count = 0;
            for (int i = 0; i < 4; i++) {
                if (b[i] >= '0' && b[i] <= '9') {
                    count++;
                } else {
                    break;
                }
            }

            String str = new String(b, 0, count);
            str = str.replaceAll("\\s+", "");
            level = Integer.parseInt(str);
            Log.e(TAG, "xc level:" + level);
            in.close();
        } catch (Exception e) {
            // TODO: handle exception
            Log.e(TAG, "getBackLightBrightness Exception");
        }
        Log.e(TAG, "getBackLightBrightness return:" + level);

        return level;
    }

    private void setBackLightBrightness(int level) {
        final String filename = "/sys/class/disp/disp/attr/xc_brightchange";
        File localFile = new File(filename);
        if (!localFile.canWrite()) {
            Log.e(TAG, filename + " can not write!");
            return;
        }
        FileOutputStream fos = null;
        try {
            Log.e(TAG, "setBackLightBrightness!" + level);
            fos = new FileOutputStream(localFile);
            fos.write(String.valueOf(level).getBytes());
            Log.e(TAG, "setBackLightBrightness over!");
        } catch (Exception e) {
            // TODO: handle exception
            Log.e(TAG, e.toString());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception exp) {
                }
            }
        }
    }

    public void turnOffBacklight() {
        int lightBrightness = getBackLightBrightness();
        if (lightBrightness != 0) {
            mBackLightBrightness = getBackLightBrightness();
            setBackLightBrightness(0);
        }
    }

    public boolean turnOnBacklight() {
        int lightBrightness = getBackLightBrightness();
        if (lightBrightness == 0) {
            setBackLightBrightness(mBackLightBrightness);
            Log.e(TAG, "xc turnOnBacklight:" + mBackLightBrightness);
            return true;
        }
        return false;
    }

    private static String processReadSysfsNode(String node) {
        String value = null;
        FileInputStream fInputStream = null;

        File path = new File(node);
        if (!path.exists())
            return null;

        try {
            fInputStream = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    fInputStream));
            value = br.readLine();
            fInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    private static void processWriteSysfsNode(int mode, String node) {
        FileOutputStream mFileOutputStream = null;
        String cmd = mode + "\n";

        File path = new File(node);
        if (!path.exists())
            return;

        try {
            mFileOutputStream = new FileOutputStream(path);
            mFileOutputStream.write(new String(cmd).getBytes());
            mFileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void screenOff() {
        // 处理SCREEN_SAVER事件(关屏/禁止TP功能)
        if (processReadSysfsNode(BACKLIGHT_ENABLE_NODE).equals("0")) {
            return;
        } else {
            processWriteSysfsNode(0, BACKLIGHT_ENABLE_NODE);
            processWriteSysfsNode(0, TOUCHSCREEN_ENABLE_NODE);
        }
    }

    public static void screenOn() {
        if (processReadSysfsNode(BACKLIGHT_ENABLE_NODE).equals("0")) {
            processWriteSysfsNode(1, BACKLIGHT_ENABLE_NODE);
            processWriteSysfsNode(1, TOUCHSCREEN_ENABLE_NODE);
        }
    }
}
