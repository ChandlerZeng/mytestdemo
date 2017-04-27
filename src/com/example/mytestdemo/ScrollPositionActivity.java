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
        scrollText.setText("据我自己的实践，这种说法确实成立。然而，我也发现许多关于速度的怀疑都"
                + "来自一些早期的实现方式。由于这些方式并非特别有效，所以没有一个模型可供参考，"
                + "不能解释Java 速度快的原因。我之所以想到速度，部分原因是由于 C++模型。 "
                + "C++将自己的主要精力放在编译期间“静态”发生的所有事情上，所以程序的运行期版本"
                + "非常短小和快速。 C++也直接建立在 C 模型的基础上（主要为了向后兼容），但有时"
                + "仅仅由于它在 C 中能按特定的方式工作，所以也是 C++中最方便的一种方法。最重要的"
                + "一种情况是 C 和 C++对内存的管理方式，它是某些人觉得 Java 速度肯定慢的重要依据："
                + "在 Java 中，所有对象都必须在内存“堆”里创建。而在 C++中，对象是在堆栈中创建的。"
                + "这样可达到更快的速度，因为当我们进入一个特定的作用域时，堆栈指针会向下移动一个单位，"
                + "为那个作用域内创建的、以堆栈为基础的所有对象分配存储空间。而当我们离开作用域的时"
                + "（调用完毕所有局部构建器后），堆栈指针会向上移动一个单位。然而，在C++里创建“内存堆”"
                + "（ Heap）对象通常会慢得多，因为它建立在 C 的内存堆基础上。这种内存堆实际是一个"
                + "大的内存池，要 求必须进行再循环（再生）。在 C++里调用 delete 以后，释放的内存会"
                + "在堆里留下一个洞，所以再调用new的时候，存储分配机制必须进行某种形式的搜索，"
                + "使对象的存储与堆内任何现成的洞相配，否则就会很快用光堆的存储空间。"
                + "之所以内存堆的分配会在 C++里对性能造成如此重大的性能影响，对可用内存的搜索正是"
                + "一个重要的原因。所以创建基于堆栈的对象要快得多。 同样地，由于 C++如此多的工作都"
                + "在编译期间进行，所以必须考虑这方面的因素。但在 Java 的某些地方，事 情的发生"
                + "却要显得“动态”得多，它会改变模型。创建对象的时候，垃圾收集器的使用对于提高对象"
                + "创建的速度产生了显著的影响。从表面上看，这种说法似乎有些奇怪—— 存储空间的"
                + "释放会对存储空间的分配造成影响，但它正是 JVM 采取的重要手段之一，这意味着"
                + "在 Java 中为堆对象分配存储空间几乎能达到与 C++中在堆栈里创建存储空间一样快的速度。");
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
