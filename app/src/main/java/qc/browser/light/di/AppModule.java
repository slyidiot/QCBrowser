package qc.browser.light.di;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import net.i2p.android.ui.I2PAndroidHelper;

import javax.inject.Singleton;

import qc.browser.light.BrowserApp;
import qc.browser.light.database.bookmark.BookmarkDatabase;
import qc.browser.light.database.bookmark.BookmarkModel;
import qc.browser.light.database.downloads.DownloadsDatabase;
import qc.browser.light.database.downloads.DownloadsModel;
import qc.browser.light.database.history.HistoryDatabase;
import qc.browser.light.database.history.HistoryModel;
import qc.browser.light.download.DownloadHandler;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final BrowserApp mApp;

    public AppModule(BrowserApp app) {
        this.mApp = app;
    }

    @Provides
    public Application provideApplication() {
        return mApp;
    }

    @Provides
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @NonNull
    @Provides
    @Singleton
    public BookmarkModel provideBookmarkModel() {
        return new BookmarkDatabase(mApp);
    }

    @NonNull
    @Provides
    @Singleton
    public DownloadsModel provideDownloadsModel() {
        return new DownloadsDatabase(mApp);
    }

    @NonNull
    @Provides
    @Singleton
    public HistoryModel providesHistoryModel() {
        return new HistoryDatabase(mApp);
    }

    @NonNull
    @Provides
    @Singleton
    public DownloadHandler provideDownloadHandler() {
        return new DownloadHandler();
    }

    @NonNull
    @Provides
    @Singleton
    public I2PAndroidHelper provideI2PAndroidHelper() {
        return new I2PAndroidHelper(mApp.getApplicationContext());
    }

}
