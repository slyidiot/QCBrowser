package qc.browser.light.search.engine

import qc.browser.light.R
import qc.browser.light.constant.Constants

/**
 * The Ask search engine.
 */
class AskSearch : BaseSearchEngine(
        "file:///android_asset/ask.png",
        Constants.ASK_SEARCH,
        R.string.search_engine_ask
)
