package com.example.mytestdemo;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mytestdemo.base.BaseActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class DimBarcodeActivity extends BaseActivity implements OnClickListener{

    private Button btnGenBarcode;
    private ImageView ivBarcode;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dim_barcode);
        init();
    }
    
    private void init(){
        btnGenBarcode = (Button)findViewById(R.id.btn_gen_barcode);
        ivBarcode = (ImageView)findViewById(R.id.iv_barcode);
        
        btnGenBarcode.setOnClickListener(this);
    }
    
    private Bitmap generateBitmap(String content,int width, int height) {  
        QRCodeWriter qrCodeWriter = new QRCodeWriter();  
        Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        try {  
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);  
            int[] pixels = new int[width * height];  
            for (int i = 0; i < height; i++) {  
                for (int j = 0; j < width; j++) {  
                    if (encode.get(j, i)) {  
                        pixels[i * width + j] = 0x00000000;  
                    } else {  
                        pixels[i * width + j] = 0xffffffff;  
                    }  
                }  
            }  
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);  
        } catch (WriterException e) {  
            e.printStackTrace();  
        }  
        return null;  
    } 
    
    private Bitmap addLogo(Bitmap qrBitmap, Bitmap logoBitmap) {  
        int qrBitmapWidth = qrBitmap.getWidth();  
        int qrBitmapHeight = qrBitmap.getHeight();  
        int logoBitmapWidth = logoBitmap.getWidth();  
        int logoBitmapHeight = logoBitmap.getHeight();  
        Bitmap blankBitmap = Bitmap.createBitmap(qrBitmapWidth, qrBitmapHeight, Bitmap.Config.ARGB_8888);  
        Canvas canvas = new Canvas(blankBitmap);  
        canvas.drawBitmap(qrBitmap, 0, 0, null);  
        canvas.save(Canvas.ALL_SAVE_FLAG);  
        float scaleSize = 1.0f;  
        while ((logoBitmapWidth / scaleSize) > (qrBitmapWidth / 5) || (logoBitmapHeight / scaleSize) > (qrBitmapHeight / 5)) {  
            scaleSize *= 2;  
        }  
        float sx = 1.0f / scaleSize;  
        canvas.scale(sx, sx, qrBitmapWidth / 2, qrBitmapHeight / 2);  
        canvas.drawBitmap(logoBitmap, (qrBitmapWidth - logoBitmapWidth) / 2, (qrBitmapHeight - logoBitmapHeight) / 2, null);  
        canvas.restore();  
        return blankBitmap;  
    }  
    
    public void generate(View view,ImageView iv) {  
//        Bitmap qrBitmap = generateBitmap("http://xw.qq.com/index.htm",400, 400);  
        Bitmap qrBitmap = generateBitmap("ƒ„√√∞°£¨…µ±∆",400, 400);  
        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qq);  
        Bitmap bitmap = addLogo(qrBitmap, logoBitmap);  
        iv.setImageBitmap(bitmap);  
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.btn_gen_barcode:
            generate(v, ivBarcode);
            break;

        default:
            break;
        }
        
    }  
}
