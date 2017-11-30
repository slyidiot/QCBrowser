package qc.browser.light.search.engine

import qc.browser.light.R
import qc.browser.light.constant.Constants

/**
 * The StartPage mobile search engine.
 */
class StartPageMobileSearch : BaseSearchEngine(
        "file:///android_asset/startpage.png",
        Constants.STARTPAGE_MOBILE_SEARCH,
        R.string.search_engine_startpage_mobile
)
