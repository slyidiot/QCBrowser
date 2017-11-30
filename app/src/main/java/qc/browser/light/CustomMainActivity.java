package qc.browser.light;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableAction;
import com.anthonycr.bonsai.CompletableOnSubscribe;
import com.anthonycr.bonsai.CompletableSubscriber;
import com.anthonycr.bonsai.Schedulers;

import qc.browser.light.dialog.BrowserDialog;
import qc.browser.light.settings.activity.SettingsActivity;
import qc.browser.light.utils.Preconditions;
import qc.browser.light.utils.Utils;
import qc.browser.light.utils.WebUtils;
import qc.browser.light.view.LightningView;

/**
 * Created by Akshay Soni on 8/28/2017 5:28 PM
 * Lightning-Browser-dev
 */

public class CustomMainActivity extends MainActivity {

    Dialog dialog = null;
    RelativeLayout backword_layout, forward_layout, home_layout, general_setting_layout, rlToolbar;

    private LinearLayout bottom_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        backword_layout = (RelativeLayout) findViewById(R.id.backword_layout);
        forward_layout = (RelativeLayout) findViewById(R.id.forward_layout);
        general_setting_layout = (RelativeLayout) findViewById(R.id.general_setting_layout);
        home_layout = (RelativeLayout) findViewById(R.id.home_layout);
        rlToolbar = (RelativeLayout) findViewById(R.id.rlToolbar);
        bottom_layout = (LinearLayout) findViewById(R.id.bootomview);

        backword_layout.setOnClickListener(this);
        forward_layout.setOnClickListener(this);
        general_setting_layout.setOnClickListener(this);
        home_layout.setOnClickListener(this);

        mToolbarLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));


    }

    @Override
    public void onClick(@NonNull View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.backword_layout:
                vibe.vibrate(50);
                onBackButtonPressed();
                break;
            case R.id.forward_layout:
                vibe.vibrate(50);
                onForwardButtonPressed();
                break;
            case R.id.general_setting_layout:
                vibe.vibrate(50);
                showMainSettingDialog();

                break;
            case R.id.home_layout:
                vibe.vibrate(50);
                onHomeButtonPressed();

                break;
        }
    }


    private void showMainSettingDialog() {
        try {
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_main_setting,
                    null, false);

            if (dialog != null) {
                dialog.dismiss();
            }
            dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(view);
            //dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogZoomOutInAnimation;

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = dialog.getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);

            RelativeLayout pc_layout = (RelativeLayout) dialog.findViewById(R.id.pc_layout);
            RelativeLayout bookmark_layout = (RelativeLayout) dialog.findViewById(R.id.bookmark_layout);
            RelativeLayout history_layout = (RelativeLayout) dialog.findViewById(R.id.history_layout);
            RelativeLayout clear_history_layout = (RelativeLayout) dialog.findViewById(R.id.clear_history_layout);
            RelativeLayout download_layout = (RelativeLayout) dialog.findViewById(R.id.download_layout);
            RelativeLayout share_layout = (RelativeLayout) dialog.findViewById(R.id.share_layout);
            RelativeLayout setting_layout = (RelativeLayout) dialog.findViewById(R.id.setting_layout);
            RelativeLayout full_screen_layout = (RelativeLayout) dialog.findViewById(R.id.full_screen_layout);
            RelativeLayout exist_layout = (RelativeLayout) dialog.findViewById(R.id.exist_layout);

            setTextColorForTheme(dialog);
            pc_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    openDesktopVersion();
                    dialog.dismiss();
                }
            });

            bookmark_layout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    openBookmarks();
                    dialog.dismiss();
                }
            });

            history_layout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    openHistory();
                    dialog.dismiss();
                }
            });

            clear_history_layout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    clearHistoryDialog();
                    dialog.dismiss();
                }
            });


            download_layout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    openDownloads();
                    dialog.dismiss();
                }
            });


            share_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    LightningView currentView = mTabsManager.getCurrentTab();
                    final String currentUrl = currentView != null ? currentView.getUrl() : "";

                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=qc.browser.light");
                    startActivity(Intent.createChooser(sharingIntent, "Share Via"));
                    dialog.dismiss();

                }
            });

            setting_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    startActivity(new Intent(CustomMainActivity.this, SettingsActivity.class));
                    dialog.dismiss();
                }
            });

            full_screen_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    fullScreenview();
                    dialog.dismiss();
                }
            });

            exist_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                    //onBackPressed();
                    onBackButtonPressed();
                    exit_popup();
                }
            });

            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextColorForTheme(Dialog dialog) {

        CardView cardView = (CardView) dialog.findViewById(R.id.menu_icons_cardview);


        ImageView iv_pc_view = (ImageView) dialog.findViewById(R.id.iv_pc_view);
        ImageView iv_bookmark = (ImageView) dialog.findViewById(R.id.iv_bookmark);
        ImageView iv_history = (ImageView) dialog.findViewById(R.id.iv_history);
        ImageView iv_clear_history = (ImageView) dialog.findViewById(R.id.iv_clear_history);
        ImageView iv_download = (ImageView) dialog.findViewById(R.id.iv_download);
        ImageView iv_share = (ImageView) dialog.findViewById(R.id.iv_share);
        ImageView iv_general_Setting = (ImageView) dialog.findViewById(R.id.iv_general_Setting);
        ImageView iv_full_screen = (ImageView) dialog.findViewById(R.id.iv_full_screen);
        ImageView iv_exit = (ImageView) dialog.findViewById(R.id.iv_exit);


        TextView txtPCView = (TextView) dialog.findViewById(R.id.pcview);
        TextView txtBookmark = (TextView) dialog.findViewById(R.id.bookmark);
        TextView txtHistory = (TextView) dialog.findViewById(R.id.history);
        TextView txtClearHistory = (TextView) dialog.findViewById(R.id.clearhistory);
        TextView txtDownloads = (TextView) dialog.findViewById(R.id.downloads);
        TextView txtShare = (TextView) dialog.findViewById(R.id.share);
        TextView txtSettings = (TextView) dialog.findViewById(R.id.settings);
        TextView txtFullScreen = (TextView) dialog.findViewById(R.id.fullscreen);
        TextView txtExit = (TextView) dialog.findViewById(R.id.exit);


        if (mTheme == 0) {
            cardView.setCardBackgroundColor(Color.WHITE);
            txtPCView.setTextColor(Color.BLACK);
            txtBookmark.setTextColor(Color.BLACK);
            txtHistory.setTextColor(Color.BLACK);
            txtClearHistory.setTextColor(Color.BLACK);
            txtDownloads.setTextColor(Color.BLACK);
            txtShare.setTextColor(Color.BLACK);
            txtSettings.setTextColor(Color.BLACK);
            txtFullScreen.setTextColor(Color.BLACK);
            txtExit.setTextColor(Color.BLACK);

            iv_pc_view.setColorFilter(Color.BLACK);
            iv_bookmark.setColorFilter(Color.BLACK);
            iv_history.setColorFilter(Color.BLACK);
            iv_clear_history.setColorFilter(Color.BLACK);
            iv_download.setColorFilter(Color.BLACK);
            iv_share.setColorFilter(Color.BLACK);
            iv_general_Setting.setColorFilter(Color.BLACK);
            iv_full_screen.setColorFilter(Color.BLACK);
            iv_exit.setColorFilter(Color.BLACK);
        } else if (mTheme == 1) {
            cardView.setCardBackgroundColor(Color.BLACK);
            txtPCView.setTextColor(Color.WHITE);
            txtBookmark.setTextColor(Color.WHITE);
            txtHistory.setTextColor(Color.WHITE);
            txtClearHistory.setTextColor(Color.WHITE);
            txtDownloads.setTextColor(Color.WHITE);
            txtShare.setTextColor(Color.WHITE);
            txtSettings.setTextColor(Color.WHITE);
            txtFullScreen.setTextColor(Color.WHITE);
            txtExit.setTextColor(Color.WHITE);


            iv_pc_view.setColorFilter(Color.WHITE);
            iv_bookmark.setColorFilter(Color.WHITE);
            iv_history.setColorFilter(Color.WHITE);
            iv_clear_history.setColorFilter(Color.WHITE);
            iv_download.setColorFilter(Color.WHITE);
            iv_share.setColorFilter(Color.WHITE);
            iv_general_Setting.setColorFilter(Color.WHITE);
            iv_full_screen.setColorFilter(Color.WHITE);
            iv_exit.setColorFilter(Color.WHITE);

        } else if (mTheme == 2) {
            cardView.setCardBackgroundColor(Color.BLACK);
            txtPCView.setTextColor(Color.WHITE);
            txtBookmark.setTextColor(Color.WHITE);
            txtHistory.setTextColor(Color.WHITE);
            txtClearHistory.setTextColor(Color.WHITE);
            txtDownloads.setTextColor(Color.WHITE);
            txtShare.setTextColor(Color.WHITE);
            txtSettings.setTextColor(Color.WHITE);
            txtFullScreen.setTextColor(Color.WHITE);
            txtExit.setTextColor(Color.WHITE);


            iv_pc_view.setColorFilter(Color.WHITE);
            iv_bookmark.setColorFilter(Color.WHITE);
            iv_history.setColorFilter(Color.WHITE);
            iv_clear_history.setColorFilter(Color.WHITE);
            iv_download.setColorFilter(Color.WHITE);
            iv_share.setColorFilter(Color.WHITE);
            iv_general_Setting.setColorFilter(Color.WHITE);
            iv_full_screen.setColorFilter(Color.WHITE);
            iv_exit.setColorFilter(Color.WHITE);

         }
    }

    private void fullScreenview() {
        if (bottom_layout.getVisibility() == View.VISIBLE) {
            bottom_layout.setVisibility(View.GONE);
            rlToolbar.setVisibility(View.GONE);
        } else {
            bottom_layout.setVisibility(View.VISIBLE);
            rlToolbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        if (bottom_layout.getVisibility() == View.GONE)
            fullScreenview();
        else
            exit_popup();

    }

    public void exit_popup()
    {
        final  Dialog d = new MyDialog(this).getMyDialog(R.layout.dialog_exit);
        d.show();
        final CheckBox chkCookies = (CheckBox) d.findViewById(R.id.chkCookie);
        final CheckBox chkCache = (CheckBox) d.findViewById(R.id.chkCache);
        final CheckBox chkHistory= (CheckBox) d.findViewById(R.id.chkHistory);


        d.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });

        d.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (chkCookies.isChecked()){
                    clearCookies()
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.main())
                            .subscribe(new CompletableOnSubscribe() {
                                @Override
                                public void onComplete() {

                                }
                            });
                }

                if (chkCache.isChecked()){
                    clearCache();
                }

                if (chkHistory.isChecked()){
                    clearHistory()
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.main())
                            .subscribe(new CompletableOnSubscribe() {
                                @Override
                                public void onComplete() {

                                }
                            });
                }

                finishAffinity();
                //  mPresenter.deleteTab(mTabsManager.positionOf(currentTab));
            }
        });
    }

    private void clearHistoryDialog() {
        final Activity activity = this;
        Preconditions.checkNonNull(activity);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(getResources().getString(R.string.title_clear_history));
        Dialog dialog = builder.setMessage(getResources().getString(R.string.dialog_history))
                .setPositiveButton(getResources().getString(R.string.action_yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                clearHistory()
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(Schedulers.main())
                                        .subscribe(new CompletableOnSubscribe() {
                                            @Override
                                            public void onComplete() {
                                                Utils.showSnackbar(activity, R.string.message_clear_history);
                                            }
                                        });
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.action_no), null).show();
        BrowserDialog.setDialogSize(activity, dialog);
    }

    private Completable clearHistory() {
        return Completable.create(new CompletableAction() {
            @Override
            public void onSubscribe(@NonNull CompletableSubscriber subscriber) {
                if (CustomMainActivity.this != null) {
                    // TODO: 6/9/17 clearHistory is not synchronous
                    WebUtils.clearHistory(CustomMainActivity.this, mHistoryModel);
                    subscriber.onComplete();
                }
                subscriber.onError(new RuntimeException("Activity was null in clearHistory"));
            }
        });
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

}
