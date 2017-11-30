package qc.browser.light.di;

import javax.inject.Singleton;

import qc.browser.light.adblock.AssetsAdBlocker;
import qc.browser.light.adblock.NoOpAdBlocker;
import qc.browser.light.browser.activity.BrowserActivity;
import qc.browser.light.network.NetworkObservable;
import qc.browser.light.reading.activity.ReadingActivity;
import qc.browser.light.browser.TabsManager;
import qc.browser.light.browser.activity.ThemableBrowserActivity;
import qc.browser.light.settings.activity.ThemableSettingsActivity;
import qc.browser.light.BrowserApp;
import qc.browser.light.browser.BrowserPresenter;
import qc.browser.light.browser.SearchBoxModel;
import qc.browser.light.constant.BookmarkPage;
import qc.browser.light.constant.DownloadsPage;
import qc.browser.light.constant.HistoryPage;
import qc.browser.light.constant.StartPage;
import qc.browser.light.dialog.LightningDialogBuilder;
import qc.browser.light.download.DownloadHandler;
import qc.browser.light.download.LightningDownloadListener;
import qc.browser.light.settings.fragment.BookmarkSettingsFragment;
import qc.browser.light.browser.fragment.BookmarksFragment;
import qc.browser.light.settings.fragment.DebugSettingsFragment;
import qc.browser.light.settings.fragment.GeneralSettingsFragment;
import qc.browser.light.settings.fragment.LightningPreferenceFragment;
import qc.browser.light.settings.fragment.PrivacySettingsFragment;
import qc.browser.light.browser.fragment.TabsFragment;
import qc.browser.light.search.SearchEngineProvider;
import qc.browser.light.search.SuggestionsAdapter;
import qc.browser.light.utils.ProxyUtils;
import qc.browser.light.view.LightningChromeClient;
import qc.browser.light.view.LightningView;
import qc.browser.light.view.LightningWebClient;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(BrowserActivity activity);

    void inject(BookmarksFragment fragment);

    void inject(BookmarkSettingsFragment fragment);

    void inject(LightningDialogBuilder builder);

    void inject(TabsFragment fragment);

    void inject(LightningView lightningView);

    void inject(ThemableBrowserActivity activity);

    void inject(LightningPreferenceFragment fragment);

    void inject(BrowserApp app);

    void inject(ProxyUtils proxyUtils);

    void inject(ReadingActivity activity);

    void inject(LightningWebClient webClient);

    void inject(ThemableSettingsActivity activity);

    void inject(LightningDownloadListener listener);

    void inject(PrivacySettingsFragment fragment);

    void inject(StartPage startPage);

    void inject(HistoryPage historyPage);

    void inject(BookmarkPage bookmarkPage);

    void inject(DownloadsPage downloadsPage);

    void inject(BrowserPresenter presenter);

    void inject(TabsManager manager);

    void inject(DebugSettingsFragment fragment);

    void inject(SuggestionsAdapter suggestionsAdapter);

    void inject(LightningChromeClient chromeClient);

    void inject(DownloadHandler downloadHandler);

    void inject(SearchBoxModel searchBoxModel);

    void inject(SearchEngineProvider searchEngineProvider);

    void inject(GeneralSettingsFragment generalSettingsFragment);

    void inject(NetworkObservable networkObservable);

    AssetsAdBlocker provideAssetsAdBlocker();

    NoOpAdBlocker provideNoOpAdBlocker();

}
