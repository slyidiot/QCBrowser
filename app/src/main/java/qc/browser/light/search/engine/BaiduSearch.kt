package qc.browser.light.search.engine

import qc.browser.light.R
import qc.browser.light.constant.Constants

/**
 * The Baidu search engine.
 *
 * See http://www.baidu.com/img/bdlogo.gif for the icon.
 */
class BaiduSearch : BaseSearchEngine(
        "file:///android_asset/baidu.png",
        Constants.BAIDU_SEARCH,
        R.string.search_engine_baidu
)
