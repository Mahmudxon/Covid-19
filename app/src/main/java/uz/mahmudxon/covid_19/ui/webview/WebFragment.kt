package uz.mahmudxon.covid_19.ui.webview

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_web.*
import uz.mahmudxon.covid_19.R


class WebFragment : Fragment(R.layout.fragment_web)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.javaScriptCanOpenWindowsAutomatically = true
        webView?.settings?.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webView?.settings?.loadsImagesAutomatically = true
        webView?.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView?.settings?.domStorageEnabled = true
        webView?.settings?.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webView?.settings?.allowFileAccess = true
        webView?.settings?.allowContentAccess = true
        webView?.settings?.setSupportZoom(true)
        webView?.loadUrl("https://coronavirus.jhu.edu/map.html")
    }
}
