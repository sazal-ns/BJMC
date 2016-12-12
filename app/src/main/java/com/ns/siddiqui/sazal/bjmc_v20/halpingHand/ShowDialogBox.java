package com.ns.siddiqui.sazal.bjmc_v20.halpingHand;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ns.siddiqui.sazal.bjmc_v20.R;

/**
 * Created by sazal on 2016-12-03.
 */

public class ShowDialogBox {

    MaterialDialog.Builder builder;
    MaterialDialog dialog;

    public ShowDialogBox() {
    }

    public void showDialog(final Context context, String title, String content){

        builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .cancelable(true)
                .iconRes(R.mipmap.warning).autoDismiss(true);

         dialog = builder.build();
        dialog.show();
    }

}
