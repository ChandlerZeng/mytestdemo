package com.example.mytestdemo.javatest;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.Arrays;

/** * 
 @author  zengcq
 @date 创建时间：2017年6月1日
 @version 1.0 
 */
public class Bytetest {

	private final static int UART_HEAD_0 = 0x5A;
    private final static int UART_HEAD_1 = 0xA5;
    private static DecimalFormat df = new DecimalFormat("#0.0");
    private static byte[] bytedatas = new byte[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		intndInteger();
		String[] dtas = new String[10];
		System.out.println(dtas.length);
		for(int i=0;i<dtas.length;i++){
			System.out.print(dtas[i]);
		}
		System.out.println();
		byte[] buffer = new byte[2];		   
	    buffer[0]=0x09;
		buffer[1]=(byte)023;
		System.out.println("buffer[0]:"+buffer[0]+" buffer[1]:"+buffer[1]);
		System.out.println(buffer[1]);
		int iValue = 233;
		byte bValue = (byte) iValue; //int强制转化为byte
		System.out.println(bValue);
		char[] charA = new char[]{'A','B','C','D'};
		char[] charB = new char[]{'a','b','c','d','e'};
		System.out.print("charA ");
		System.out.print(charA);
		System.out.print(" to lowerCase is: {");
		arrayToLowerCase(charA);
		System.out.println("}");
		System.out.print("charB {");
		for(int i =0;i<charB.length;i++){
			System.out.print("'"+charB[i]+"'");
			if(i!=charB.length-1)
			System.out.print(",");
		}
		System.out.print("} to upperCase is: {");
		arrayToUpperCase(charB);
		System.out.println("}");
		System.out.print("switchtoRadio:");
		switchtoRadio();
		System.out.print("swtichoutRadio:");
		swtichoutRadio();
		System.out.print("ReqSensitive:");
		ReqSensitive();
		System.out.print("ReqRadioInfo:");
		ReqRadioInfo();
		System.out.print("swtichToAux:");
		swtichToAux();
		caltest();
		byte[] datas = new byte[]{0x0A,0x1A,0x01,0x01,0x01,0x09};
		System.out.println(byteToHex(datas));
		for(int i=0;i<datas.length;i++){
			System.out.print(datas[i]);
			System.out.print(",");
		}
		System.out.println();
		for(int i=0;i<datas.length;i++){
			int k = datas[i];
			System.out.print(k);
			System.out.print(",");
		}
		System.out.println();
		int binter = datas[0];
		int bdecim = datas[1];
		StringBuffer buffer2 = new StringBuffer();
		buffer2.append(datas[0]).append(".").append(datas[1]);
		System.out.println(buffer2.toString());
		int mrange,range,lrange,frange;
		mrange = (datas[2] & 0xff) <<16;
		range = (datas[3] & 0xff) << 8;
		lrange = datas[4] & 0xff;
		frange = mrange | range | lrange;
		System.out.println(mrange+","+range+","+lrange);
		System.out.println(frange);
		String dform = df.format(0.14);
		String dform2 = df.format(22.151);
		String dform3 = df.format(0.16);
		String dform4 = df.format(23.14);
		System.out.println(dform+","+dform2+","+dform3+","+dform4);
		System.out.println(formateDecimData(byteToDouble(datas[0], datas[1])));
		is0or1(4, 224);
		is0or1(5, 224);
		is0or1(6, 224);
		is0or1(7, 224);
		System.out.println(getBitStatus(224, 2));
		System.out.println(getBitStatus(224, 3));
		System.out.println(getBitStatus(224,4));
		System.out.println(getBitStatus(224,5));
		System.out.println(getBitStatus(224, 6));
		System.out.println(getBitStatus(224, 7));
		System.out.println(getBitStatus(224, 8));
		System.out.println(getBitStatus(224, 9));
		byte[] byteSum = new byte[]{0x0f,0x33,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
		byte[] byteSum2 = new byte[]{0x00,-1,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
		checkSum(byteSum);
		System.out.println(byteToHex(bytedatas));
		boolean istrue = bytedatas[0]==0x00?true:false;
		boolean istrue2 = bytedatas[1]==-1?true:false;
		boolean isequal = Arrays.equals(byteSum2, bytedatas);
		System.out.println("istrue:"+istrue+" istrue2:"+istrue2+" isequal:"+isequal);
		bytedatas = byteSum2;
		System.out.println(byteToHex(bytedatas));
		istrue = bytedatas[0]==0x00?true:false;
		istrue2 = bytedatas[1]==-1?true:false;
		isequal = Arrays.equals(byteSum2, bytedatas);
		System.out.println("istrue:"+istrue+" istrue2:"+istrue2+" isequal:"+isequal);
		int data01 = 1;
		int data02 = 3;
		boolean isSame1 = isBitStatusSame(data01, data02, 0);
		boolean isSame2 = isBitStatusSame(data01, data02, 1);
		System.out.println("isSame1:"+isSame1+" isSame2:"+isSame2);
		byte[] yuv420sp = new byte[]{2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		int[] rgb = decodeYUV420SP(yuv420sp, 4, 5);
		for(int i=0;i<rgb.length;i++){
			System.out.print(i+",");
		}
		System.out.println();
	}
	
	private static void intndInteger(){
		int i = 128;
        Integer i2 = 128;
        Integer i3 = new Integer(128);
        //Integer会自动拆箱为int，所以为true
        System.out.println(i == i2);
        System.out.println(i == i3);
        System.out.println(i2 == i3);
        System.out.println("**************");
        Integer i5 = 127;//java在编译的时候,被翻译成-> Integer i5 = Integer.valueOf(127);
        Integer i6 = 127;
        System.out.println(i5 == i6);//true
        Integer i55 = 128;
        Integer i66 = 128;
        System.out.println(i55 == i66);//false
        Integer ii5 = new Integer(127);
        System.out.println(i5 == ii5); //false
        Integer i7 = new Integer(128);
        Integer i8 = new Integer(128);
        System.out.println(i7 == i8);  //false
	}
	
	public static int[] decodeYUV420SP(byte[] yuv420sp, int width, int height) {

        final int frameSize = width * height;

        int rgb[] = new int[width * height];
        for (int j = 0, yp = 0; j < height; j++) {
            int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
            for (int i = 0; i < width; i++, yp++) {
                int y = (0xff & ((int) yuv420sp[yp])) - 16;
                if (y < 0)
                    y = 0;
                if ((i & 1) == 0) {
                    v = (0xff & yuv420sp[uvp++]) - 128;
                    u = (0xff & yuv420sp[uvp++]) - 128;
                }

                int y1192 = 1192 * y;
                int r = (y1192 + 1634 * v);
                int g = (y1192 - 833 * v - 400 * u);
                int b = (y1192 + 2066 * u);

                if (r < 0)
                    r = 0;
                else if (r > 262143)
                    r = 262143;
                if (g < 0)
                    g = 0;
                else if (g > 262143)
                    g = 262143;
                if (b < 0)
                    b = 0;
                else if (b > 262143)
                    b = 262143;

                rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000)
                        | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);

            }
        }
        return rgb;
    }
	
	public static int getBitStatus(int data, int position){
        return (data>>position) & 1;
    }
	
	public static boolean isBitStatusSame(int data1,int data2,int position){
        return getBitStatus(data1,position)==getBitStatus(data2,position);
    }
	
	public static void is0or1(int N, int data){
		if(((data>>N)&1)!=0){
			System.out.println(data+":"+N+"位上是1");
		}else{
			System.out.println(data+":"+N+"位上是0");
		}
	}
	
	public static char toLowerCase(char a){
		int asc = (int)a;
		if(asc>64 && asc<98){
			asc = asc + 32;
			a = (char)asc;
		}
		return a;
	}
	
	public static char toUpperCase(char a){
		int asc = (int)a;
		if(asc>96 && asc<123){
			asc = asc - 32;
			a = (char)asc;
		}
		return a;
	}
	
	public static void arrayToLowerCase(char[] array){
		for(int i =0;i<array.length;i++){
			array[i] = toLowerCase(array[i]);
			System.out.print("'"+array[i]+"'");
			if(i!=array.length-1)
			System.out.print(",");
		}
	}
	
	public static void arrayToUpperCase(char[] array){
		for(int i =0;i<array.length;i++){
			array[i] = toUpperCase(array[i]);
			System.out.print("'"+array[i]+"'");
			if(i!=array.length-1)
			System.out.print(",");
		}
	}
	
	public static String getByteStringHex(byte[] data){
		char[] DIGITS_UPPER = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		StringBuffer ret = new StringBuffer(64);
		for(int i=0; i<data.length; i++){
			byte hf = (byte) ((data[i]>>4) & 0x0F);
			byte lf = (byte) (data[i] & 0x0F);
			ret.append("0x");
			ret.append(DIGITS_UPPER[hf]);
			ret.append(DIGITS_UPPER[lf]);
			if((i+1) < data.length)
				ret.append(",");
		}
		System.out.println(ret.toString());
		return ret.toString();
	}
	
	public static void switchtoRadio(){
		byte[] buffer = new byte[2];
		buffer[0]=0x01;
		buffer[1]=0x01;
		sendCommand((byte)0x10, buffer);
		
		
	}
	
	public static void swtichoutRadio(){
		byte[] buffer = new byte[2];
		buffer[0]=0x01;
		buffer[1]=0x00;
		sendCommand((byte)0x10, buffer);
	}
	
	public static void swtichToAux(){
		byte[] buffer = new byte[2];
		buffer[0]=0x02;
		buffer[1]=0x03;
		sendCommand((byte)0x27, buffer);
	}
	
	public static void ReqRadioInfo() {
		   byte[] buffer = new byte[1];
		   
		   buffer[0]=0x00;
		  
		   sendCommand((byte)0x10, buffer);
		
	}
	
	  public static void ReqSensitive()
	   {
		   byte[] buffer = new byte[2];
		   buffer[0]=0x08;
		   buffer[1]=0x02;
		   sendCommand((byte)0x10, buffer);
	   }
	
	private static ByteBuffer getPackage(byte cmd, byte... param) {
        int len = 5;
        byte sum = 0;
        if (param != null) {
            len += param.length;
        }
        byte[] buffer = new byte[len];
        buffer[0] = (byte) UART_HEAD_0;
        buffer[1] = (byte) UART_HEAD_1;
      //  buffer[2] = (byte) buffer.length;
        buffer[2]=(byte)(param.length+0x01);
        buffer[3] = cmd;
        if (param != null) {
            System.arraycopy(param, 0, buffer, 4, param.length);
        }
      //  checksum(buffer);
        for (int i = 2; i < buffer.length - 1; i++) {
            sum ^= buffer[i];
        }
        buffer[buffer.length - 1] = sum;
        return ByteBuffer.wrap(buffer);
    }
	
	public static void sendCommand(boolean needACK, byte cmd, byte... param) {
        ByteBuffer buffer = getPackage(cmd, param);
        getByteStringHex(buffer.array());

    }

    public static void sendCommand(byte cmd, byte... param) {
        sendCommand(false, cmd, param);
    }
    
    private static void checkSum(byte[] checkdata){
    	byte sum =0;
    	for (int i = 0; i < checkdata.length; i++) {
            sum ^= checkdata[i];
        }
    	System.out.println(byteToHex(checkdata)+" "+sum);
    }
    
    private static void caltest(){
    	int s = 3;
    	int q = 3;
    	int i = 1;
    	int j = --s-i;
    	int k = q---i;
    	System.out.println("s:"+s+" j:"+j+" q:"+q+" k:"+k);
    }
    
    public static String byteToHex(byte[] data) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            buffer.append(String.format("%02x", data[i]));
            if(i<data.length-1) buffer.append(",");
        }
        return buffer.toString();
    }
    
    public static double byteToDouble(byte bIterger, byte bDecimal){
        StringBuffer buffer = new StringBuffer();
        buffer.append(bIterger).append(".").append(bDecimal);
        String doubleString = buffer.toString();
        return Double.parseDouble(doubleString);
    }

    public static String formateDecimData(double data){
        return df.format(data);
    }

}
