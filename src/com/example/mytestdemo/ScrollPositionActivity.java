package com.example.mytestdemo;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.mytestdemo.base.BaseActivity;
import com.example.mytestdemo.utils.LogUtil;
import com.example.mytestdemo.utils.SharedPreferencesUtil;

public class ScrollPositionActivity extends BaseActivity {

    private TextView scrollText;
    private ScrollView scrollView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_position);       
        init();
    }
    
    private void init(){
        scrollView = (ScrollView)findViewById(R.id.scroll_view);
        scrollText = (TextView)findViewById(R.id.scroll_text);
        scrollText.setText("�����Լ���ʵ��������˵��ȷʵ������Ȼ������Ҳ�����������ٶȵĻ��ɶ�"
                + "����һЩ���ڵ�ʵ�ַ�ʽ��������Щ��ʽ�����ر���Ч������û��һ��ģ�Ϳɹ��ο���"
                + "���ܽ���Java �ٶȿ��ԭ����֮�����뵽�ٶȣ�����ԭ�������� C++ģ�͡� "
                + "C++���Լ�����Ҫ�������ڱ����ڼ䡰��̬�����������������ϣ����Գ���������ڰ汾"
                + "�ǳ���С�Ϳ��١� C++Ҳֱ�ӽ����� C ģ�͵Ļ����ϣ���ҪΪ�������ݣ�������ʱ"
                + "������������ C ���ܰ��ض��ķ�ʽ����������Ҳ�� C++������һ�ַ���������Ҫ��"
                + "һ������� C �� C++���ڴ�Ĺ���ʽ������ĳЩ�˾��� Java �ٶȿ϶�������Ҫ���ݣ�"
                + "�� Java �У����ж��󶼱������ڴ桰�ѡ��ﴴ�������� C++�У��������ڶ�ջ�д����ġ�"
                + "�����ɴﵽ������ٶȣ���Ϊ�����ǽ���һ���ض���������ʱ����ջָ��������ƶ�һ����λ��"
                + "Ϊ�Ǹ��������ڴ����ġ��Զ�ջΪ���������ж������洢�ռ䡣���������뿪�������ʱ"
                + "������������оֲ��������󣩣���ջָ��������ƶ�һ����λ��Ȼ������C++�ﴴ�����ڴ�ѡ�"
                + "�� Heap������ͨ�������ö࣬��Ϊ�������� C ���ڴ�ѻ����ϡ������ڴ��ʵ����һ��"
                + "����ڴ�أ�Ҫ ����������ѭ�������������� C++����� delete �Ժ��ͷŵ��ڴ��"
                + "�ڶ�������һ�����������ٵ���new��ʱ�򣬴洢������Ʊ������ĳ����ʽ��������"
                + "ʹ����Ĵ洢������κ��ֳɵĶ����䣬����ͻ�ܿ��ù�ѵĴ洢�ռ䡣"
                + "֮�����ڴ�ѵķ������ C++��������������ش������Ӱ�죬�Կ����ڴ����������"
                + "һ����Ҫ��ԭ�����Դ������ڶ�ջ�Ķ���Ҫ��öࡣ ͬ���أ����� C++��˶�Ĺ�����"
                + "�ڱ����ڼ���У����Ա��뿼���ⷽ������ء����� Java ��ĳЩ�ط����� ��ķ���"
                + "ȴҪ�Եá���̬���ö࣬����ı�ģ�͡����������ʱ�������ռ�����ʹ�ö�����߶���"
                + "�������ٶȲ�����������Ӱ�졣�ӱ����Ͽ�������˵���ƺ���Щ��֡��� �洢�ռ��"
                + "�ͷŻ�Դ洢�ռ�ķ������Ӱ�죬�������� JVM ��ȡ����Ҫ�ֶ�֮һ������ζ��"
                + "�� Java ��Ϊ�Ѷ������洢�ռ伸���ܴﵽ�� C++���ڶ�ջ�ﴴ���洢�ռ�һ������ٶȡ�");
        final int y = (Integer) SharedPreferencesUtil.get(ScrollPositionActivity.this, "scroll_position", 0);
        LogUtil.i("scroll y is "+y);
        scrollView.post(new Runnable() {  
            @Override  
            public void run() {  
                scrollView.scrollTo(0, y);  
            }   
        });         
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        saveScrollPosition();
    }
    
    private void saveScrollPosition(){
        int y = scrollView.getScrollY();
        LogUtil.d("scroll y is "+y);
        SharedPreferencesUtil.put(ScrollPositionActivity.this, "scroll_position", y);
    }
      
}
