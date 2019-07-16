import bean.Area;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.Gson;
import org.junit.Test;
import services.QuanGuoGGZYService;

import java.io.IOException;
import java.util.List;

public class Tester {


    public void t (){
        List<Area> areas = new QuanGuoGGZYService().getAllArea();

        System.out.println(new Gson().toJson(areas));
    }


    public static void main(String[] args) {

    }
    @Test
    public void aa() {



        //ProxyConfig proxyConfig =  = webClient.getOptions().s().getProxyConfig(); //代理
        // //proxyConfig.setProxyHost(ost("t("http-dyn.abuyun.com");

// // proxyConfig.setProxyPort(ort(9020);
//  /  webClient.addRequestHeatHeatHeader("Proxy-Authorization", "Basic SDczNFQ4UkMwN1czM0c3RDo5MEQzMEM4QkMxOUIyNDMw");

        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);

            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setTimeout(5000);

//            String url = "= "https://shop.wuage.com/lfsztwz05691/page/contactinfo.htm?psa=W9.p10.c69.34";

            String url = "https://shop.wuage.com/lfsztwz05691/page/contactinfo.htm?psa=W9.p10.c69.34";

            HtmlPage htmlpage =  webClient.getPage(url);
//               htmlpage.get("sp("span[class=show-complete active]").get(0);
//            HtmlPage nextPage =  = button.click();

            DomNodeList<DomElement> elementsByTagName =  htmlpage.getElementsByTagName("span");
            for (DomElement domElement : elementsByTagName) {

                if  (domElement.asText().indexOf("点击查看")!=-1){

                    System.out.println((domElement.asText()));
                    HtmlPage na =  domElement.click();

                    System.out.println(na.asXml());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




