package xuenn.lesson_2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class ActivityMain extends AppCompatActivity {

    private Context context;
    private LinearLayout llRoot;
    private Button btnAddLinearLayout;
    private Button btnAddRelativeLayout;
    private Button btnAddFrameLayout;
    private Button btnAddTextView;
    private Button btnAddImageView;
    private Button btnAddEditText;
    private SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initUI();
        initAction();
    }

    private void init() {
        this.context = this;
    }

    private void initUI() {
        llRoot = (LinearLayout) findViewById(R.id.llRoot);
        btnAddLinearLayout = (Button) findViewById(R.id.btnAddLinearLayout);
        btnAddRelativeLayout = (Button) findViewById(R.id.btnAddRelativeLayout);
        btnAddFrameLayout = (Button) findViewById(R.id.btnAddFrameLayout);
        btnAddTextView = (Button) findViewById(R.id.btnAddTextView);
        btnAddImageView = (Button) findViewById(R.id.btnAddImageView);
        btnAddEditText = (Button) findViewById(R.id.btnAddEditText);
        seekBar= (SeekBar) findViewById(R.id.seekBar);
    }

    private void initAction() {
        btnAddLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLinearLayout();
            }
        });

        btnAddRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRelativeLayout();
            }
        });
        btnAddFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFrameLayout();
            }
        });
        btnAddTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTextView();
            }
        });
        btnAddImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addImageView();
            }
        });
        btnAddEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditText();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("seekBar","onProgressChanged:"+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i("seekBar","onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("seekBar","onStopTrackingTouch");
            }
        });
    }

    private void addLinearLayout() {
        LinearLayout ll = new LinearLayout(context);
        //設定背景-純色
        ll.setBackgroundColor(0x0092C3);
        //設定背景-圖檔
        ll.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        //設定排列方向
        ll.setOrientation(LinearLayout.VERTICAL);
        //設定內部對齊屬性，用|連結對齊選項
        ll.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        //設定padding
        ll.setPadding(16, 16, 16, 16);
        //新增View-預設
//        llRoot.addView(ll);
        //新增View-指定寬高
        llRoot.addView(ll, 200, 200);

        //設定長寬與比重
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                1);
//        //設定Margin
//        lp.setMargins(10, 10, 10, 10);
//        //新增View-帶入其他設定 (與父容器有關)
//        llRoot.addView(ll, lp);

    }

    private void addRelativeLayout() {
        RelativeLayout rl = new RelativeLayout(context);
        //設定背景-純色
        rl.setBackgroundColor(0xFF0092C3);
        //設定背景-圖檔
        //rl.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        //設定padding
        rl.setPadding(16, 16, 16, 16);
        //新增View-預設
        //llRoot.addView(rl);
        //新增View-指定寬高
        llRoot.addView(rl, 200, 200);
        //設定長寬與比重
        //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(200, 200, 1);
        //設定Margin
        //lp.setMargins(10, 10, 10, 10);
        //新增View-帶入其他設定 (與父容器有關)
        //llRoot.addView(rl, lp);
    }

    private void addFrameLayout() {
        FrameLayout fl = new FrameLayout(context);
        //設定背景-純色
        fl.setBackgroundColor(0x0092C3);
        //設定背景-圖檔
        fl.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        //設定padding
        fl.setPadding(16, 16, 16, 16);
        //新增View-預設
        //llRoot.addView(fl);
        //新增View-指定寬高
        llRoot.addView(fl, 300, 200);
        //設定長寬與比重
        //LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(200,200,1);
        //設定Margin
        //lp.setMargins(10,10,10,10);
        //新增View-帶入其他設定 (與父容器有關)
        //llRoot.addView(fl,lp);

    }

    private void addTextView() {
        TextView tv = new TextView(context);
        tv.setText("this is message\ntest textview");
        //tv.setText(context.getString(R.string.hello_world));
        //設定字元太常要變成...的位置
        tv.setEllipsize(TextUtils.TruncateAt.END);
        //設定顏色
        tv.setTextColor(0xFFFF0000);
        //設定行距
        tv.setLineSpacing(48,0);
        //設定強制單行
//        tv.setSingleLine(true);
        //設定行數
        //tv.setLines(1);
        //設定自動連結
        tv.setAutoLinkMask(Linkify.ALL);
        llRoot.addView(tv,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void addImageView() {
        ImageView iv = new ImageView(context);
        //設定Image圖片
        //iv.setImageBitmap(bitmap);
        iv.setImageDrawable(getResources().getDrawable(R.mipmap.bg));
        //設定padding
        iv.setPadding(16, 16, 16, 16);
        //設定縮放屬性
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //新增View-預設
        //llRoot.addView(iv);
        //新增View-指定寬高
        llRoot.addView(iv, 200, 200);
        //設定長寬與比重
        //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(200, 200, 1);
        //設定Margin
        //lp.setMargins(10, 10, 10, 10);
        //新增View-帶入其他設定 (與父容器有關)
        //llRoot.addView(iv, lp);
    }

    private void addEditText() {
        EditText et = new EditText(context);
        //設定自動連結
        et.setAutoLinkMask(Linkify.ALL);
        //設定提示
        et.setHint("input something");
        //設定提示顏色
        et.setHintTextColor(0x77000000);
        //設定文字顏色
        et.setTextColor(0xFF000000);
        //設定間距
        et.setLineSpacing(48, 0);
        //設定文字大小
        et.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        //設定單行
        et.setSingleLine(true);
        llRoot.addView(et);
    }

}
