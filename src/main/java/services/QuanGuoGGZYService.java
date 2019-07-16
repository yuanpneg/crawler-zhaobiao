package services;

import bean.Area;
import bean.Tender;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dao.BaseDao;
import dao.FormalDao;
import http.HttpUtils;
import okhttp3.FormBody;
import okhttp3.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pojo1.Cityregion;
import pojo1.QuanGuoGGZYJson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class QuanGuoGGZYService {

    // 获得所有省市区域
    public List<Area> getAllArea() {

        List<Area> areas = new ArrayList<Area>();

        String cityJson = "{\"120000\":[{\"id\":\"120101\",\"name\":\"和平区\"},{\"id\":\"120102\",\"name\":\"河东区\"},{\"id\":\"120103\",\"name\":\"河西区\"},{\"id\":\"120104\",\"name\":\"南开区\"},{\"id\":\"120105\",\"name\":\"河北区\"},{\"id\":\"120106\",\"name\":\"红桥区\"},{\"id\":\"120110\",\"name\":\"东丽区\"},{\"id\":\"120111\",\"name\":\"西青区\"},{\"id\":\"120112\",\"name\":\"津南区\"},{\"id\":\"120113\",\"name\":\"北辰区\"},{\"id\":\"120114\",\"name\":\"武清区\"},{\"id\":\"120115\",\"name\":\"宝坻区\"},{\"id\":\"120116\",\"name\":\"滨海新区\"},{\"id\":\"120221\",\"name\":\"宁河县\"},{\"id\":\"120223\",\"name\":\"静海县\"},{\"id\":\"120225\",\"name\":\"蓟县\"}],\"450000\":[{\"id\":\"450100\",\"name\":\"南宁市\"},{\"id\":\"450200\",\"name\":\"柳州市\"},{\"id\":\"450300\",\"name\":\"桂林市\"},{\"id\":\"450400\",\"name\":\"梧州市\"},{\"id\":\"450500\",\"name\":\"北海市\"},{\"id\":\"450600\",\"name\":\"防城港市\"},{\"id\":\"450700\",\"name\":\"钦州市\"},{\"id\":\"450800\",\"name\":\"贵港市\"},{\"id\":\"450900\",\"name\":\"玉林市\"},{\"id\":\"451000\",\"name\":\"百色市\"},{\"id\":\"451100\",\"name\":\"贺州市\"},{\"id\":\"451200\",\"name\":\"河池市\"},{\"id\":\"451300\",\"name\":\"来宾市\"},{\"id\":\"451400\",\"name\":\"崇左市\"}],\"140000\":[{\"id\":\"140100\",\"name\":\"太原市\"},{\"id\":\"140200\",\"name\":\"大同市\"},{\"id\":\"140300\",\"name\":\"阳泉市\"},{\"id\":\"140400\",\"name\":\"长治市\"},{\"id\":\"140500\",\"name\":\"晋城市\"},{\"id\":\"140600\",\"name\":\"朔州市\"},{\"id\":\"140700\",\"name\":\"晋中市\"},{\"id\":\"140800\",\"name\":\"运城市\"},{\"id\":\"140900\",\"name\":\"忻州市\"},{\"id\":\"141000\",\"name\":\"临汾市\"},{\"id\":\"141100\",\"name\":\"吕梁市\"}],\"630000\":[{\"id\":\"630100\",\"name\":\"西宁市\"},{\"id\":\"630200\",\"name\":\"海东市\"},{\"id\":\"632200\",\"name\":\"海北\"},{\"id\":\"632300\",\"name\":\"黄南\"},{\"id\":\"632500\",\"name\":\"海南\"},{\"id\":\"632600\",\"name\":\"果洛\"},{\"id\":\"632700\",\"name\":\"玉树\"},{\"id\":\"632800\",\"name\":\"海西\"}],\"440000\":[{\"id\":\"440100\",\"name\":\"广州市\"},{\"id\":\"440200\",\"name\":\"韶关市\"},{\"id\":\"440300\",\"name\":\"深圳市\"},{\"id\":\"440400\",\"name\":\"珠海市\"},{\"id\":\"440500\",\"name\":\"汕头市\"},{\"id\":\"440600\",\"name\":\"佛山市\"},{\"id\":\"440700\",\"name\":\"江门市\"},{\"id\":\"440800\",\"name\":\"湛江市\"},{\"id\":\"440900\",\"name\":\"茂名市\"},{\"id\":\"441200\",\"name\":\"肇庆市\"},{\"id\":\"441300\",\"name\":\"惠州市\"},{\"id\":\"441400\",\"name\":\"梅州市\"},{\"id\":\"441500\",\"name\":\"汕尾市\"},{\"id\":\"441600\",\"name\":\"河源市\"},{\"id\":\"441700\",\"name\":\"阳江市\"},{\"id\":\"441800\",\"name\":\"清远市\"},{\"id\":\"441900\",\"name\":\"东莞市\"},{\"id\":\"442000\",\"name\":\"中山市\"},{\"id\":\"445100\",\"name\":\"潮州市\"},{\"id\":\"445200\",\"name\":\"揭阳市\"},{\"id\":\"445300\",\"name\":\"云浮市\"}],\"430000\":[{\"id\":\"430100\",\"name\":\"长沙市\"},{\"id\":\"430200\",\"name\":\"株洲市\"},{\"id\":\"430300\",\"name\":\"湘潭市\"},{\"id\":\"430400\",\"name\":\"衡阳市\"},{\"id\":\"430500\",\"name\":\"邵阳市\"},{\"id\":\"430600\",\"name\":\"岳阳市\"},{\"id\":\"430700\",\"name\":\"常德市\"},{\"id\":\"430800\",\"name\":\"张家界市\"},{\"id\":\"430900\",\"name\":\"益阳市\"},{\"id\":\"431000\",\"name\":\"郴州市\"},{\"id\":\"431100\",\"name\":\"永州市\"},{\"id\":\"431200\",\"name\":\"怀化市\"},{\"id\":\"431300\",\"name\":\"娄底市\"},{\"id\":\"433100\",\"name\":\"湘西\"}],\"620000\":[{\"id\":\"620100\",\"name\":\"兰州市\"},{\"id\":\"620200\",\"name\":\"嘉峪关市\"},{\"id\":\"620300\",\"name\":\"金昌市\"},{\"id\":\"620400\",\"name\":\"白银市\"},{\"id\":\"620500\",\"name\":\"天水市\"},{\"id\":\"620600\",\"name\":\"武威市\"},{\"id\":\"620700\",\"name\":\"张掖市\"},{\"id\":\"620800\",\"name\":\"平凉市\"},{\"id\":\"620900\",\"name\":\"酒泉市\"},{\"id\":\"621000\",\"name\":\"庆阳市\"},{\"id\":\"621100\",\"name\":\"定西市\"},{\"id\":\"621200\",\"name\":\"陇南市\"},{\"id\":\"622900\",\"name\":\"临夏\"},{\"id\":\"623000\",\"name\":\"甘南\"}],\"640000\":[{\"id\":\"640100\",\"name\":\"银川市\"},{\"id\":\"640200\",\"name\":\"石嘴山市\"},{\"id\":\"640300\",\"name\":\"吴忠市\"},{\"id\":\"640400\",\"name\":\"固原市\"},{\"id\":\"640500\",\"name\":\"中卫市\"}],\"230000\":[{\"id\":\"230100\",\"name\":\"哈尔滨市\"},{\"id\":\"230200\",\"name\":\"齐齐哈尔市\"},{\"id\":\"230300\",\"name\":\"鸡西市\"},{\"id\":\"230400\",\"name\":\"鹤岗市\"},{\"id\":\"230500\",\"name\":\"双鸭山市\"},{\"id\":\"230600\",\"name\":\"大庆市\"},{\"id\":\"230700\",\"name\":\"伊春市\"},{\"id\":\"230800\",\"name\":\"佳木斯市\"},{\"id\":\"230900\",\"name\":\"七台河市\"},{\"id\":\"231000\",\"name\":\"牡丹江市\"},{\"id\":\"231100\",\"name\":\"黑河市\"},{\"id\":\"231200\",\"name\":\"绥化市\"},{\"id\":\"232700\",\"name\":\"大兴安岭地区\"}],\"410000\":[{\"id\":\"410100\",\"name\":\"郑州市\"},{\"id\":\"410200\",\"name\":\"开封市\"},{\"id\":\"410300\",\"name\":\"洛阳市\"},{\"id\":\"410400\",\"name\":\"平顶山市\"},{\"id\":\"410500\",\"name\":\"安阳市\"},{\"id\":\"410600\",\"name\":\"鹤壁市\"},{\"id\":\"410700\",\"name\":\"新乡市\"},{\"id\":\"410800\",\"name\":\"焦作市\"},{\"id\":\"410900\",\"name\":\"濮阳市\"},{\"id\":\"411000\",\"name\":\"许昌\"},{\"id\":\"411100\",\"name\":\"漯河市\"},{\"id\":\"411200\",\"name\":\"三门峡市\"},{\"id\":\"411300\",\"name\":\"南阳市\"},{\"id\":\"411400\",\"name\":\"商丘市\"},{\"id\":\"411500\",\"name\":\"信阳市\"},{\"id\":\"411600\",\"name\":\"周口市\"},{\"id\":\"411700\",\"name\":\"驻马店市\"},{\"id\":\"419001\",\"name\":\"济源市\"}],\"330000\":[{\"id\":\"330100\",\"name\":\"杭州市\"},{\"id\":\"330200\",\"name\":\"宁波市\"},{\"id\":\"330300\",\"name\":\"温州市\"},{\"id\":\"330400\",\"name\":\"嘉兴市\"},{\"id\":\"330500\",\"name\":\"湖州市\"},{\"id\":\"330600\",\"name\":\"绍兴市\"},{\"id\":\"330700\",\"name\":\"金华市\"},{\"id\":\"330800\",\"name\":\"衢州市\"},{\"id\":\"330900\",\"name\":\"舟山市\"},{\"id\":\"331000\",\"name\":\"台州市\"},{\"id\":\"331100\",\"name\":\"丽水市\"}],\"510000\":[{\"id\":\"510100\",\"name\":\"成都市\"},{\"id\":\"510300\",\"name\":\"自贡市\"},{\"id\":\"510400\",\"name\":\"攀枝花市\"},{\"id\":\"510500\",\"name\":\"泸州市\"},{\"id\":\"510600\",\"name\":\"德阳市\"},{\"id\":\"510700\",\"name\":\"绵阳市\"},{\"id\":\"510800\",\"name\":\"广元市\"},{\"id\":\"510900\",\"name\":\"遂宁市\"},{\"id\":\"511000\",\"name\":\"内江市\"},{\"id\":\"511100\",\"name\":\"乐山市\"},{\"id\":\"511300\",\"name\":\"南充市\"},{\"id\":\"511400\",\"name\":\"眉山市\"},{\"id\":\"511500\",\"name\":\"宜宾市\"},{\"id\":\"511600\",\"name\":\"广安市\"},{\"id\":\"511700\",\"name\":\"达州市\"},{\"id\":\"511800\",\"name\":\"雅安市\"},{\"id\":\"511900\",\"name\":\"巴中市\"},{\"id\":\"512000\",\"name\":\"资阳市\"},{\"id\":\"513200\",\"name\":\"阿坝\"},{\"id\":\"513300\",\"name\":\"甘孜\"},{\"id\":\"513400\",\"name\":\"凉山\"}],\"210000\":[{\"id\":\"210100\",\"name\":\"沈阳市\"},{\"id\":\"210200\",\"name\":\"大连市\"},{\"id\":\"210300\",\"name\":\"鞍山市\"},{\"id\":\"210400\",\"name\":\"抚顺市\"},{\"id\":\"210500\",\"name\":\"本溪市\"},{\"id\":\"210600\",\"name\":\"丹东市\"},{\"id\":\"210700\",\"name\":\"锦州市\"},{\"id\":\"210800\",\"name\":\"营口市\"},{\"id\":\"210900\",\"name\":\"阜新市\"},{\"id\":\"211000\",\"name\":\"辽阳市\"},{\"id\":\"211100\",\"name\":\"盘锦市\"},{\"id\":\"211200\",\"name\":\"铁岭市\"},{\"id\":\"211300\",\"name\":\"朝阳市\"},{\"id\":\"211400\",\"name\":\"葫芦岛市\"}],\"530000\":[{\"id\":\"530100\",\"name\":\"昆明市\"},{\"id\":\"530300\",\"name\":\"曲靖市\"},{\"id\":\"530400\",\"name\":\"玉溪市\"},{\"id\":\"530500\",\"name\":\"保山市\"},{\"id\":\"530600\",\"name\":\"昭通市\"},{\"id\":\"530700\",\"name\":\"丽江市\"},{\"id\":\"530800\",\"name\":\"普洱市\"},{\"id\":\"530900\",\"name\":\"临沧市\"},{\"id\":\"532300\",\"name\":\"楚雄\"},{\"id\":\"532500\",\"name\":\"红河\"},{\"id\":\"532600\",\"name\":\"文山\"},{\"id\":\"532800\",\"name\":\"西双版纳\"},{\"id\":\"532900\",\"name\":\"大理\"},{\"id\":\"533100\",\"name\":\"德宏\"},{\"id\":\"533300\",\"name\":\"怒江\"},{\"id\":\"533400\",\"name\":\"迪庆\"}],\"130000\":[{\"id\":\"130100\",\"name\":\"石家庄市\"},{\"id\":\"130200\",\"name\":\"唐山市\"},{\"id\":\"130300\",\"name\":\"秦皇岛市\"},{\"id\":\"130400\",\"name\":\"邯郸市\"},{\"id\":\"130500\",\"name\":\"邢台市\"},{\"id\":\"130600\",\"name\":\"保定市\"},{\"id\":\"130700\",\"name\":\"张家口市\"},{\"id\":\"130800\",\"name\":\"承德市\"},{\"id\":\"130900\",\"name\":\"沧州市\"},{\"id\":\"131000\",\"name\":\"廊坊市\"},{\"id\":\"131100\",\"name\":\"衡水市\"}],\"340000\":[{\"id\":\"340100\",\"name\":\"合肥市\"},{\"id\":\"340200\",\"name\":\"芜湖市\"},{\"id\":\"340300\",\"name\":\"蚌埠市\"},{\"id\":\"340400\",\"name\":\"淮南市\"},{\"id\":\"340500\",\"name\":\"马鞍山市\"},{\"id\":\"340600\",\"name\":\"淮北市\"},{\"id\":\"340700\",\"name\":\"铜陵市\"},{\"id\":\"340800\",\"name\":\"安庆市\"},{\"id\":\"341000\",\"name\":\"黄山市\"},{\"id\":\"341100\",\"name\":\"滁州市\"},{\"id\":\"341200\",\"name\":\"阜阳市\"},{\"id\":\"341300\",\"name\":\"宿州市\"},{\"id\":\"341500\",\"name\":\"六安市\"},{\"id\":\"341600\",\"name\":\"亳州市\"},{\"id\":\"341700\",\"name\":\"池州市\"},{\"id\":\"341800\",\"name\":\"宣城市\"}],\"500000\":[{\"id\":\"500101\",\"name\":\"万州区\"},{\"id\":\"500102\",\"name\":\"涪陵区\"},{\"id\":\"500103\",\"name\":\"渝中区\"},{\"id\":\"500104\",\"name\":\"大渡口区\"},{\"id\":\"500105\",\"name\":\"江北区\"},{\"id\":\"500106\",\"name\":\"沙坪坝区\"},{\"id\":\"500107\",\"name\":\"九龙坡区\"},{\"id\":\"500108\",\"name\":\"南岸区\"},{\"id\":\"500109\",\"name\":\"北碚区\"},{\"id\":\"500110\",\"name\":\"綦江区\"},{\"id\":\"500111\",\"name\":\"大足区\"},{\"id\":\"500112\",\"name\":\"渝北区\"},{\"id\":\"500113\",\"name\":\"巴南区\"},{\"id\":\"500114\",\"name\":\"黔江区\"},{\"id\":\"500115\",\"name\":\"长寿区\"},{\"id\":\"500116\",\"name\":\"江津区\"},{\"id\":\"500117\",\"name\":\"合川区\"},{\"id\":\"500118\",\"name\":\"永川区\"},{\"id\":\"500119\",\"name\":\"南川区\"},{\"id\":\"500152\",\"name\":\"潼南区\"},{\"id\":\"500153\",\"name\":\"荣昌区\"},{\"id\":\"500224\",\"name\":\"铜梁区\"},{\"id\":\"500227\",\"name\":\"璧山区\"},{\"id\":\"500228\",\"name\":\"梁平县\"},{\"id\":\"500229\",\"name\":\"城口县\"},{\"id\":\"500230\",\"name\":\"丰都县\"},{\"id\":\"500231\",\"name\":\"垫江县\"},{\"id\":\"500232\",\"name\":\"武隆县\"},{\"id\":\"500233\",\"name\":\"忠县\"},{\"id\":\"500234\",\"name\":\"开县\"},{\"id\":\"500235\",\"name\":\"云阳县\"},{\"id\":\"500236\",\"name\":\"奉节县\"},{\"id\":\"500237\",\"name\":\"巫山县\"},{\"id\":\"500238\",\"name\":\"巫溪县\"},{\"id\":\"500240\",\"name\":\"石柱土家族自治县\"},{\"id\":\"500241\",\"name\":\"秀山土家族苗族自治县\"},{\"id\":\"500242\",\"name\":\"酉阳土家族苗族自治县\"},{\"id\":\"500243\",\"name\":\"彭水苗族土家族自治县\"}],\"350000\":[{\"id\":\"350100\",\"name\":\"福州市\"},{\"id\":\"350200\",\"name\":\"厦门市\"},{\"id\":\"350300\",\"name\":\"莆田市\"},{\"id\":\"350400\",\"name\":\"三明市\"},{\"id\":\"350500\",\"name\":\"泉州市\"},{\"id\":\"350600\",\"name\":\"漳州市\"},{\"id\":\"350700\",\"name\":\"南平市\"},{\"id\":\"350800\",\"name\":\"龙岩市\"},{\"id\":\"350900\",\"name\":\"宁德市\"}],\"320000\":[{\"id\":\"320100\",\"name\":\"南京市\"},{\"id\":\"320200\",\"name\":\"无锡市\"},{\"id\":\"320300\",\"name\":\"徐州市\"},{\"id\":\"320400\",\"name\":\"常州市\"},{\"id\":\"320500\",\"name\":\"苏州市\"},{\"id\":\"320600\",\"name\":\"南通市\"},{\"id\":\"320700\",\"name\":\"连云港市\"},{\"id\":\"320800\",\"name\":\"淮安市\"},{\"id\":\"320900\",\"name\":\"盐城市\"},{\"id\":\"321000\",\"name\":\"扬州市\"},{\"id\":\"321100\",\"name\":\"镇江市\"},{\"id\":\"321200\",\"name\":\"泰州市\"},{\"id\":\"321300\",\"name\":\"宿迁市\"}],\"220000\":[{\"id\":\"220100\",\"name\":\"长春市\"},{\"id\":\"220200\",\"name\":\"吉林市\"},{\"id\":\"220300\",\"name\":\"四平市\"},{\"id\":\"220400\",\"name\":\"辽源市\"},{\"id\":\"220500\",\"name\":\"通化市\"},{\"id\":\"220600\",\"name\":\"白山市\"},{\"id\":\"220700\",\"name\":\"松原市\"},{\"id\":\"220800\",\"name\":\"白城市\"},{\"id\":\"222400\",\"name\":\"延边\"}],\"310000\":[{\"id\":\"310101\",\"name\":\"黄浦区\"},{\"id\":\"310104\",\"name\":\"徐汇区\"},{\"id\":\"310105\",\"name\":\"长宁区\"},{\"id\":\"310106\",\"name\":\"静安区\"},{\"id\":\"310107\",\"name\":\"普陀区\"},{\"id\":\"310108\",\"name\":\"闸北区\"},{\"id\":\"310109\",\"name\":\"虹口区\"},{\"id\":\"310110\",\"name\":\"杨浦区\"},{\"id\":\"310112\",\"name\":\"闵行区\"},{\"id\":\"310113\",\"name\":\"宝山区\"},{\"id\":\"310114\",\"name\":\"嘉定区\"},{\"id\":\"310115\",\"name\":\"浦东新区\"},{\"id\":\"310116\",\"name\":\"金山区\"},{\"id\":\"310117\",\"name\":\"松江区\"},{\"id\":\"310118\",\"name\":\"青浦区\"},{\"id\":\"310120\",\"name\":\"奉贤区\"},{\"id\":\"310230\",\"name\":\"崇明县\"}],\"650000\":[{\"id\":\"650100\",\"name\":\"乌鲁木齐市\"},{\"id\":\"650200\",\"name\":\"克拉玛依市\"},{\"id\":\"652100\",\"name\":\"吐鲁番\"},{\"id\":\"652200\",\"name\":\"哈密\"},{\"id\":\"652300\",\"name\":\"昌吉\"},{\"id\":\"652700\",\"name\":\"博尔塔拉\"},{\"id\":\"652800\",\"name\":\"巴音郭楞\"},{\"id\":\"652900\",\"name\":\"阿克苏地区\"},{\"id\":\"653000\",\"name\":\"克孜勒苏柯尔克孜自治州\"},{\"id\":\"653100\",\"name\":\"喀什\"},{\"id\":\"653200\",\"name\":\"和田\"},{\"id\":\"654000\",\"name\":\"伊犁\"},{\"id\":\"654200\",\"name\":\"塔城\"},{\"id\":\"654300\",\"name\":\"阿勒泰地区\"},{\"id\":\"659001\",\"name\":\"石河子市\"},{\"id\":\"659002\",\"name\":\"阿拉尔市\"},{\"id\":\"659003\",\"name\":\"图木舒克市\"},{\"id\":\"659004\",\"name\":\"五家渠市\"}],\"150000\":[{\"id\":\"150100\",\"name\":\"呼和浩特市\"},{\"id\":\"150200\",\"name\":\"包头市\"},{\"id\":\"150300\",\"name\":\"乌海市\"},{\"id\":\"150400\",\"name\":\"赤峰市\"},{\"id\":\"150500\",\"name\":\"通辽市\"},{\"id\":\"150600\",\"name\":\"鄂尔多斯市\"},{\"id\":\"150700\",\"name\":\"呼伦贝尔市\"},{\"id\":\"150800\",\"name\":\"巴彦淖尔市\"},{\"id\":\"150900\",\"name\":\"乌兰察布市\"},{\"id\":\"152200\",\"name\":\"兴安盟\"},{\"id\":\"152500\",\"name\":\"锡林郭勒盟\"},{\"id\":\"152900\",\"name\":\"阿拉善盟\"}],\"610000\":[{\"id\":\"610100\",\"name\":\"西安市\"},{\"id\":\"610200\",\"name\":\"铜川市\"},{\"id\":\"610300\",\"name\":\"宝鸡市\"},{\"id\":\"610400\",\"name\":\"咸阳市\"},{\"id\":\"610500\",\"name\":\"渭南市\"},{\"id\":\"610600\",\"name\":\"延安市\"},{\"id\":\"610700\",\"name\":\"汉中市\"},{\"id\":\"610800\",\"name\":\"榆林市\"},{\"id\":\"610900\",\"name\":\"安康市\"},{\"id\":\"611000\",\"name\":\"商洛市\"}],\"540000\":[{\"id\":\"540100\",\"name\":\"拉萨市\"},{\"id\":\"542100\",\"name\":\"昌都\"},{\"id\":\"542200\",\"name\":\"山南\"},{\"id\":\"542300\",\"name\":\"日喀则市\"},{\"id\":\"542400\",\"name\":\"那曲\"},{\"id\":\"542500\",\"name\":\"阿里\"},{\"id\":\"542600\",\"name\":\"林芝\"}],\"360000\":[{\"id\":\"360100\",\"name\":\"南昌市\"},{\"id\":\"360200\",\"name\":\"景德镇市\"},{\"id\":\"360300\",\"name\":\"萍乡市\"},{\"id\":\"360400\",\"name\":\"九江市\"},{\"id\":\"360500\",\"name\":\"新余市\"},{\"id\":\"360600\",\"name\":\"鹰潭市\"},{\"id\":\"360700\",\"name\":\"赣州市\"},{\"id\":\"360800\",\"name\":\"吉安市\"},{\"id\":\"360900\",\"name\":\"宜春市\"},{\"id\":\"361000\",\"name\":\"抚州市\"},{\"id\":\"361100\",\"name\":\"上饶市\"}],\"420000\":[{\"id\":\"420100\",\"name\":\"武汉市\"},{\"id\":\"420200\",\"name\":\"黄石市\"},{\"id\":\"420300\",\"name\":\"十堰市\"},{\"id\":\"420500\",\"name\":\"宜昌市\"},{\"id\":\"420600\",\"name\":\"襄阳市\"},{\"id\":\"420700\",\"name\":\"鄂州市\"},{\"id\":\"420800\",\"name\":\"荆门市\"},{\"id\":\"420900\",\"name\":\"孝感市\"},{\"id\":\"421000\",\"name\":\"荆州市\"},{\"id\":\"421100\",\"name\":\"黄冈市\"},{\"id\":\"421200\",\"name\":\"咸宁市\"},{\"id\":\"421300\",\"name\":\"随州市\"},{\"id\":\"422800\",\"name\":\"恩施\"},{\"id\":\"429004\",\"name\":\"仙桃市\"},{\"id\":\"429005\",\"name\":\"潜江市\"},{\"id\":\"429006\",\"name\":\"天门市\"},{\"id\":\"429021\",\"name\":\"神农架林区\"}],\"520000\":[{\"id\":\"520100\",\"name\":\"贵阳市\"},{\"id\":\"520200\",\"name\":\"六盘水市\"},{\"id\":\"520300\",\"name\":\"遵义市\"},{\"id\":\"520400\",\"name\":\"安顺市\"},{\"id\":\"520500\",\"name\":\"毕节市\"},{\"id\":\"520600\",\"name\":\"铜仁市\"},{\"id\":\"522300\",\"name\":\"黔西南\"},{\"id\":\"522600\",\"name\":\"黔东南\"},{\"id\":\"522700\",\"name\":\"黔南\"}],\"370000\":[{\"id\":\"370100\",\"name\":\"济南市\"},{\"id\":\"370200\",\"name\":\"青岛市\"},{\"id\":\"370300\",\"name\":\"淄博市\"},{\"id\":\"370400\",\"name\":\"枣庄市\"},{\"id\":\"370500\",\"name\":\"东营市\"},{\"id\":\"370600\",\"name\":\"烟台市\"},{\"id\":\"370700\",\"name\":\"潍坊市\"},{\"id\":\"370800\",\"name\":\"济宁市\"},{\"id\":\"370900\",\"name\":\"泰安市\"},{\"id\":\"371000\",\"name\":\"威海市\"},{\"id\":\"371100\",\"name\":\"日照市\"},{\"id\":\"371200\",\"name\":\"莱芜市\"},{\"id\":\"371300\",\"name\":\"临沂市\"},{\"id\":\"371400\",\"name\":\"德州市\"},{\"id\":\"371500\",\"name\":\"聊城市\"},{\"id\":\"371600\",\"name\":\"滨州市\"},{\"id\":\"371700\",\"name\":\"菏泽市\"}],\"110000\":[{\"id\":\"110101\",\"name\":\"东城区\"},{\"id\":\"110102\",\"name\":\"西城区\"},{\"id\":\"110105\",\"name\":\"朝阳区\"},{\"id\":\"110106\",\"name\":\"丰台区\"},{\"id\":\"110107\",\"name\":\"石景山区\"},{\"id\":\"110108\",\"name\":\"海淀区\"},{\"id\":\"110109\",\"name\":\"门头沟区\"},{\"id\":\"110111\",\"name\":\"房山区\"},{\"id\":\"110112\",\"name\":\"通州区\"},{\"id\":\"110113\",\"name\":\"顺义区\"},{\"id\":\"110114\",\"name\":\"昌平区\"},{\"id\":\"110115\",\"name\":\"大兴区\"},{\"id\":\"110116\",\"name\":\"怀柔区\"},{\"id\":\"110117\",\"name\":\"平谷区\"},{\"id\":\"110228\",\"name\":\"密云县\"},{\"id\":\"110229\",\"name\":\"延庆县\"}],\"460000\":[{\"id\":\"460100\",\"name\":\"海口市\"},{\"id\":\"460200\",\"name\":\"三亚市\"},{\"id\":\"460300\",\"name\":\"三沙市\"},{\"id\":\"469001\",\"name\":\"五指山市\"},{\"id\":\"469002\",\"name\":\"琼海市\"},{\"id\":\"469003\",\"name\":\"儋州市\"},{\"id\":\"469005\",\"name\":\"文昌市\"},{\"id\":\"469006\",\"name\":\"万宁市\"},{\"id\":\"469007\",\"name\":\"东方市\"},{\"id\":\"469021\",\"name\":\"定安\"},{\"id\":\"469022\",\"name\":\"屯昌\"},{\"id\":\"469023\",\"name\":\"澄迈\"},{\"id\":\"469024\",\"name\":\"临高\"},{\"id\":\"469025\",\"name\":\"白沙\"},{\"id\":\"469026\",\"name\":\"昌江\"},{\"id\":\"469027\",\"name\":\"乐东\"},{\"id\":\"469028\",\"name\":\"陵水\"},{\"id\":\"469029\",\"name\":\"保亭\"},{\"id\":\"469030\",\"name\":\"琼中\"}]}";

        JSONObject cityJsonObj = JSONObject.parseObject(cityJson);

        String url = "http://deal.ggzy.gov.cn/ds/deal/dealList.jsp";

        okhttp3.Request request = new Request.Builder()
                .headers(HttpUtils.getCommonHeaders())
                .url(url)
                .build();

        //HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttp(request);  //代理
        HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttpNoProxy(request);

        if (responseWrap.isSuccess()) {
            String body = responseWrap.body;

            Document doc = Jsoup.parse(body);

            // 获取所有省
            Elements provElements = doc.select("#provinceId option");

            for (Element provEl : provElements) {
                String id = provEl.val().trim();

                String title = provEl.ownText().trim();

                if (!title.equals("不限")) {

                    boolean isZhiXiaShi =
                            title.equals("北京") ||
                                    title.equals("天津") ||
                                    title.equals("上海") ||
                                    title.equals("重庆") ||
                                    title.equals("兵团");

                    Area area = new Area(isZhiXiaShi, id, title);

                    // 不是直辖市,获得子城市
                    if (!isZhiXiaShi) {

                        JSONArray childAreaJsonArr = cityJsonObj.getJSONArray(id);

                        if (childAreaJsonArr != null)
                            for (int i = 0; i < childAreaJsonArr.size(); i++) {
                                JSONObject childAreaObj = childAreaJsonArr.getJSONObject(i);

                                String childId = childAreaObj.getString("id");

                                String childName = childAreaObj.getString("name");

                                Area childArea = new Area(false, childId, childName);

                                area.getChildAreas().add(childArea);

                            }
                        else
                            System.out.println(id + " ,  " + title);
                    }

                    areas.add(area);

                }
            }
        }

        return areas;
    }


    // 获取 WebClient 的Cookie
    private String getCookieString(WebClient webClient) {

        webClient.waitForBackgroundJavaScript(3000);

        Iterator<Cookie> cookieIterator = webClient.getCookieManager().getCookies().iterator();

        Map<String, String> cookiesMaps = new HashMap<>();


        while (cookieIterator.hasNext()) {
            Cookie cookie = cookieIterator.next();

            String cName = cookie.getName();

            String cValue = cookie.getValue();

            cookiesMaps.put(cName, cValue);
        }

        String result = "";

        for (String cookieKey : cookiesMaps.keySet()) {
            result += " " + cookieKey + "=" + cookiesMaps.get(cookieKey) + ";";
        }

        return result;
    }


    public void getTender() {
        // 获得所有省
        List<Area> areaList = getAllArea();

        // 循环所有省
        for (Area area : areaList) {
//            webClient.waitForBackgroundJavaScript(2000);
            getList(area);
        }

    }


    /**
     * 获得列表
     */
    public void getList(Area prov) {
        // TODO 查询数据库中，该平台下的，当前省市最新的一条记录,然后用作新旧判断
        BaseDao dao = new BaseDao();
        FormalDao formalDao = new FormalDao();
        // 读取数据的起步时间，默认是1个月前
        Calendar startReadTimeCalendar = Calendar.getInstance();
        startReadTimeCalendar.add(Calendar.DAY_OF_YEAR, -8);

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setDownloadImages(false);

        //final String url = "http://deal.ggzy.gov.cn/ds/deal/dealList.jsp";
        final String url = "http://deal.ggzy.gov.cn/ds/deal/dealList_find.jsp";

        // 设置代理
        //ProxyConfig proxyConfig = webClient.getOptions().getProxyConfig(); //代理
        //proxyConfig.setProxyHost("http-dyn.abuyun.com");
        // proxyConfig.setProxyPort(9020);
        // webClient.addRequestHeader("Proxy-Authorization", "Basic SDczNFQ4UkMwN1czM0c3RDo5MEQzMEM4QkMxOUIyNDMw");

        // 待循环城市列表
        List<Area> areas = new ArrayList<Area>();

        if (prov.isZhiXia()) {
            // 直辖市
            areas.add(prov);
        } else {
            // 省
            areas.addAll(prov.getChildAreas());
        }


        // 遍历所有城市
        for (int i = 0; i < areas.size(); i++) {


            Area area = areas.get(i);

            System.out.println("省====>    " + prov.getId() + " | " + prov.getTitle());

            if (prov.isZhiXia()) {
                // 如果是直辖市 , 则直接读取
                System.out.println("市====>    " + prov.getId() + " | " + prov.getTitle());
            } else {
                System.out.println("市====>    " + area.getId() + " | " + area.getTitle());
            }

            int maxPage = 20;
            int currentPage = 1;

            loopPage:
            do {
                try {

                    HtmlPage page = webClient.getPage(url);

                    String cookie = getCookieString(webClient);

                    System.out.println("cookie : " + cookie);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    Calendar calendar = Calendar.getInstance();

                    String nowDate = sdf.format(calendar.getTime());

                    calendar.add(Calendar.DATE, -9);

                    String startDate = sdf.format(calendar.getTime());

                    FormBody.Builder formBodyBuilder = new FormBody.Builder();

                    formBodyBuilder
                            .add("TIMEBEGIN_SHOW", startDate)
                            .add("TIMEEND_SHOW", nowDate)
                            .add("TIMEBEGIN", startDate)
                            .add("TIMEEND", nowDate)
                            .add("SOURCE_TYPE","1")
                            .add("DEAL_TIME", "02")
                            .add("DEAL_CLASSIFY", "01")
                            .add("DEAL_STAGE", "0101")
                            .add("DEAL_PLATFORM", "0")
                            .add("DEAL_TRADE", "0")
                            .add("isShowAll", "1")
                            .add("PAGENUMBER", currentPage + "")
                            .add("FINDTXT", "");

                    if (prov.isZhiXia()) {
                        formBodyBuilder.add("DEAL_PROVINCE", prov.getId())
                                .add("DEAL_CITY", "0");
                    } else {
                        formBodyBuilder.add("DEAL_PROVINCE", prov.getId())
                                .add("DEAL_CITY", area.getId());
                    }

                    FormBody body = formBodyBuilder.build();

                    okhttp3.Request request = new Request.Builder()
                            .headers(HttpUtils.getCommonHeaders())
                            .url(url)
                            .post(body)
                            .header("Cookie", getCookieString(webClient))
                            .build();
                    //代理
                    HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttp(request);
                    //HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttpNoProxy(request); //代理

                    if (responseWrap.isSuccess()) {
                        System.out.println("============== page = " + currentPage + "==============");

                        String responseBody = responseWrap.body;

                        Gson gson = new Gson();

                        QuanGuoGGZYJson quanGuoGGZYJson = gson.fromJson(responseBody, QuanGuoGGZYJson.class);

                        List<QuanGuoGGZYJson.DataBean> list = quanGuoGGZYJson.getData();

                        if (list.size() > 0) {

                            for (QuanGuoGGZYJson.DataBean dataBean : list) {
                                //用这个url来判断是否存在
                                String detailUrl = dataBean.getUrl();

                                String time = dataBean.getTimeShow();

                                Date timeDate = new SimpleDateFormat("yyyy-MM-dd").parse(time);

                                String category = dataBean.getClassifyShow();
                                if("工程建设".equals(category)){
                                    category = "建设工程";
                                }
                                //category
                                String yeWuLeiXing = category;

                                String xinXiLeiXing = dataBean.getStageShow();

                                //String hangYe = tenderEl.select(".p_tw .span_on").get(4).ownText().trim();

                                int tit = dao.selectDetailUrl(detailUrl);
                                if (tit == 1) {
                                    break loopPage;
                                }
                                // todo 如果读取到数据库中最新的，根据来源URL判断,如果发现则 break 掉 (break loopPage;)

//                            if(detailUrl == url){}

                                // 如果发布时间大于 开始读取时间则读取
                                if (timeDate.getTime() < startReadTimeCalendar.getTimeInMillis()) {
                                    break loopPage;
                                }


                                //判断是否有相同的title
                                String title = dataBean.getTitle();
                                if (new BaseDao().selectTitle(title) != 0) continue;
                                Tender tender = new Tender();
                                //根据城市存入地址
                                if (prov.isZhiXia()) {
                                    tender.setAddress(prov.getTitle());
                                } else {
                                    tender.setAddress(area.getTitle());
                                }
                                tender.setAddtime(timeDate.getTime() / 1000);
                                tender.setEdittime(timeDate.getTime() / 1000);
                                int dsjaddtime = (int) (LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() / 1000);
                                tender.setDsjaddtime(dsjaddtime);
                                tender.setCatid(1);
                                tender.setStatus(1);
                                getDetail(detailUrl, tender);

                                // 如果有内容才插入
                                if (tender.getContent().length() > 5) {

                                    if (prov.isZhiXia()) {
                                        // 直辖市
                                        tender.setAreaId(prov.getId());
                                        tender.setAreaTitle(prov.getTitle());
                                        tender.setParentAreaId("");
                                        tender.setParentAreaTitle("");
                                    } else {
                                        tender.setAreaId(area.getId());
                                        tender.setAreaTitle(area.getTitle());
                                        tender.setParentAreaId(prov.getId());
                                        tender.setParentAreaTitle(prov.getTitle());
                                    }

                                    tender.setYeWuType(yeWuLeiXing);
                                    tender.setInfoType(xinXiLeiXing);
                                    //tender.setHangYe(hangYe);
                                    tender.setSendTime(time);
                                    tender.setTitle(title);
                                    updateTenderRegionLngLat(tender);
                                    if(null == tender.getLongitude() || "".equals(tender.getLongitude())){
                                        continue;
                                    }
                                    System.out.println("【" + time + "】 " + title + " , " + detailUrl);
                                    System.out.println("经度：" + tender.getLongitude() + "," + " 纬度：" + tender.getLatitude());
                                    System.out.println("城市id" + tender.getRegionid() + "," + "城市名称" + tender.getRegiontitle());
                                    //保存tender
                                    insertThreadData(tender);

                                } else {
                                    System.out.println("【内容为空】 ： " + tender.getFrom());
                                }
                            }
                        }else {
                            break loopPage;
                        }
                    }else {
                        System.out.println("获取列表页出现权限问题，无法正常读取列表!!!");
                        break loopPage;
                    }
                    currentPage++;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } while (currentPage <= maxPage);
        }
        webClient.close();
    }

    //插入数据库
    public synchronized static void insertThreadData(Tender tender) {
        BaseDao baseDao = new BaseDao();
        FormalDao formalDao = new FormalDao();
        baseDao.insertTender(tender);
        int id = tender.getId();
        if (-1 != id) {
            formalDao.insertTender(tender);
            int flag = tender.getId();
            if (-1 != flag) {
                tender.setId(id);
                tender.setFormalId(flag);
                baseDao.updateFormalId(tender);
                tender.setId(flag);
                formalDao.insertTenderContent(tender);
            }
        }
    }

    /**
     * 修改经纬度
     */
    public static void updateTenderRegionLngLat(Tender tender) {
        FormalDao formalDao = new FormalDao();
        String city = tender.getAddress();
        try {
            String cityb = city.replace("省", "").replace("市", "")
                    .replace("区", "");
            if (!cityb.equals("") && null != cityb) {
                Cityregion cityregion = formalDao.selectLonLatByTitle(cityb);
                tender.setLongitude(cityregion.getLon());
                tender.setLatitude(cityregion.getLat());
                tender.setRegionid(cityregion.getId());
                tender.setRegiontitle(cityregion.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDetail(String url, Tender tender) {

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setDownloadImages(false);

        url = url.replace("html/a", "html/b");

        try {
            webClient.getPage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        okhttp3.Request request = new Request.Builder()
                .headers(HttpUtils.getCommonHeaders())
                .url(url)
                .header("Cookie", getCookieString(webClient))
                .build();

        HttpUtils.ResponseWrap responseWrap = HttpUtils.retryHttpNoProxy(request);

        if (responseWrap.isSuccess()) {
            String body = responseWrap.body;

            String content = "";

            Document doc = Jsoup.parse(body);
            Element element = doc.getElementById("mycontent");

            if (element != null) {
                content = html_encode(element.html().trim());
            }

            tender.setContent(content);


            String realFrom = doc.getElementById("platformName").ownText().trim();

            String realFromUrl = doc.select(".detail_url a").attr("href").trim();
            tender.setFromurl(realFromUrl);//点击跳转这个url
            tender.setFrom(realFrom);
            //tender.setFrom("全国公共资源交易平台");
            tender.setRealFrom(url.replace("html/b", "html/a"));
            tender.setRealFromName(realFrom);

        }

        webClient.close();

    }

    public static String html_encode(String strHtml) {
        String content = "";
        if (strHtml.length() == 0) return content;
        content = strHtml.replace("&gt;", ">");
        content = content.replace("&lt;", "<");
        return content;
    }

}
