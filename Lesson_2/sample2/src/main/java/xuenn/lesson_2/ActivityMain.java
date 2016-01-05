package xuenn.lesson_2;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import xuenn.dialog.MyDialog;

public class ActivityMain extends AppCompatActivity {
    private Context context;
    private Button btnDialogNormal;
    private Button btnDialogSingleChoice;
    private Button btnMultiChoice;
    private Button btnDialogCustomView;
    private Button btnDialogClass;

    private MyDialog myDialog;

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
        this.myDialog=new MyDialog(context);
    }

    private void initUI() {
        btnDialogNormal = (Button) findViewById(R.id.btnDialogNormal);
        btnDialogSingleChoice = (Button) findViewById(R.id.btnDialogSingleChoice);
        btnMultiChoice = (Button) findViewById(R.id.btnMultiChoice);
        btnDialogCustomView = (Button) findViewById(R.id.btnDialogCustomView);
        btnDialogClass = (Button) findViewById(R.id.btnDialogClass);
    }

    private void initAction() {
        btnDialogNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogNormal();
            }
        });
        btnDialogSingleChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogSingleChoice();
            }
        });
        btnMultiChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogMultiChoice();
            }
        });
        btnDialogCustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogCustomView();
            }
        });
        btnDialogClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
            }
        });
    }

    private void openDialogNormal() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("這是標題");
        builder.setMessage("這是內容");
        //設定圖片
        builder.setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        // 設定返回或擊點對話框範圍外，是否能夠關閉對話框
        builder.setCancelable(true);
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("Dialog", "確定");
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("Dialog", "取消");
            }
        });

        builder.setNeutralButton("下次再問我", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("Dialog", "下次再問我");
            }
        });
        builder.create().show();

    }

    private void openDialogSingleChoice() {
        //final String[] strItems = new String[]{"Item1", "Item2", "Item3"};
        //從資源中取得陣列
        final String[] strItems = getResources().getStringArray(R.array.strItems);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("這是標題");
        builder.setItems(strItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("onClick", strItems[i]);
            }
        });
        builder.create().show();
    }

    private void openDialogMultiChoice() {
        final String[] strItems = getResources().getStringArray(R.array.strItems);
        boolean[] checkList = new boolean[3];
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("這是標題");
        builder.setMultiChoiceItems(strItems, checkList, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

            }
        });
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //TODO Something you want
            }
        });

        builder.create().show();
    }

    private void openDialogCustomView() {
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("這是標題");
        View view = LayoutInflater.from(context).inflate(R.layout.dialog, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Imageview Click", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setView(view);
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog = builder.create();
        alertDialog.show();

    }

}
