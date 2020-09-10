package com.redzone.bandipora.dialog;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.redzone.bandipora.R;


public class AppUpdateDialog extends Dialog
{
    private TextView tvTitle,tvDescription,tvUpdate;
    ImageView ivClose;

    private Context context;

    String title;
    String description;
    String update_url;
    private boolean isCancelable;


    public AppUpdateDialog(Context context, String title, String description, boolean isCancelable) {
        super(context);
        // TODO Day selector
        this.context = context;
        this.title = title;
        this.description = description;
        this.isCancelable = isCancelable;
        this.update_url = update_url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_app_update);

        tvTitle=findViewById(R.id.tvTitle);
        tvDescription=findViewById(R.id.tvMessage);
        tvUpdate=findViewById(R.id.tvUpdateNow);

        ivClose=findViewById(R.id.ivClose);

        if (isCancelable)
            ivClose.setVisibility(View.VISIBLE);
        else
            ivClose.setVisibility(View.GONE);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        if(!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setVisibility(View.GONE);


        if(!TextUtils.isEmpty(description))
            tvDescription.setText(String.format(description));
        else
            tvDescription.setVisibility(View.GONE);

        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // getPackageName() from Context or Activity object
                final String appPackageName = context.getPackageName();

                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/bprapp")));
                } catch (ActivityNotFoundException activityNotFoundException) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/bprapp")));
                }
            }
        });

    }

}