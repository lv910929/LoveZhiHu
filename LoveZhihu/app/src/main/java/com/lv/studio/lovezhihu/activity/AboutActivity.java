package com.lv.studio.lovezhihu.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lv.studio.lovezhihu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.label_email)
    TextView emailItem;

    @Bind(R.id.label_thanks)
    TextView thanksItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initToolBar();

        emailItem.setOnClickListener(this);
        thanksItem.setOnClickListener(this);
    }

    protected void initToolBar() {
        setTitle("关于");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.label_email:
                showDialog("联系","1025202464@qq.com");
                break;
            case R.id.label_thanks:
                showDialog("感谢",getResources().getString(R.string.thanks_github));
                break;
        }
    }

    private void showDialog(String title, String message) {
        final MaterialDialog mMaterialDialog = new MaterialDialog(this);

        mMaterialDialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                });

        mMaterialDialog.show();
        mMaterialDialog.setCanceledOnTouchOutside(true);
        // You can change the message anytime. before show
        //mMaterialDialog.setTitle("提示");
        //mMaterialDialog.show();
        // You can change the message anytime. after show
        //mMaterialDialog.setMessage("你好，世界~");
    }

}
