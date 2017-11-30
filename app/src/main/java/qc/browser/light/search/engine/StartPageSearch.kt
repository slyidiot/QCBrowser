package qc.browser.light.search.engine

import qc.browser.light.R
import qc.browser.light.constant.Constants

/**
 * The StartPage search engine.
 */
class StartPageSearch : BaseSearchEngine(
        "file:///android_asset/startpage.png",
        Constants.STARTPAGE_SEARCH,
        R.string.search_engine_startpage
)
