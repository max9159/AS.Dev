package xuenn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import xuenn.lesson_2.R;
import xuenn.utils.UtilScreen;

/**
 * Created by SsuChi on 12/30/2015.
 */
public class MyDialog {
    private Context context;
    private Dialog dialog;
    private View contentView;

    private ImageView imageView;

    public MyDialog(Context context){
        this.context=context;
    }

    private void initUI(){
        contentView = LayoutInflater.from(context).inflate(R.layout.dialog,null);
        imageView= (ImageView) contentView.findViewById(R.id.imageView);

    }

    private void initAction(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void initDialog() {
        dialog=new Dialog(context,R.style.DialogNoFrame);
        dialog.setTitle("this is title");
        dialog.setContentView(contentView);
        dialog.setCancelable(true);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //.....
            }
        });

        //設定 Dialog的寬與高
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = (int) (UtilScreen.getScreenSmallerSide(context)*0.8f);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
    }

    public void show(){
        if(dialog==null){
            initUI();
            initAction();
            initDialog();
        }


        if(!dialog.isShowing()){
            dialog.show();
        }
    }

    public void dismiss(){
        if(dialog.isShowing()){
            dialog.dismiss();
        }
    }

    public boolean isShowing(){
        return (dialog!=null && dialog.isShowing());
    }
}
