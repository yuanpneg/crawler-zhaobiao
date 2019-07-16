package app;

import bean.Area;
import dao.BaseDao;
import dao.FormalDao;
import org.apache.commons.logging.LogFactory;

import pojo1.Cityregion;
import pojo1.UpdaterReg;
import services.QuanGuoGGZYService;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class AllApp {

    public static void main(String[] args) {
        AllApp allApp = new AllApp();
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http.client").setLevel(Level.OFF);
        BaseDao baseDao = new BaseDao();
        FormalDao formalDao = new FormalDao();

        while (true) {
            System.out.println("任务开始");
            //baseDao.deleteHistory(allApp.getfoct());
            //formalDao.deleteHistory(allApp.getfoct());
            //System.out.println("删除30天前的数据");
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if (hour >= 8 && hour <= 20) {
                try {
                    threadMendth();
                    //删除重复数据
                    allApp.updateTenderRegionLngLat();//改表经纬度城市id
                    //allApp.updateRegiontitle();
                    //System.out.println("更改经纬度完成");
                    //baseDao.deleteIshandled();
                    //System.out.println("删除未处理的数据");
                    System.out.println("删除重复数据");
                    baseDao.deleteRepeat();
                    formalDao.deleteRepeat();
                    System.out.println("删除重复数据完成");
                    System.out.println("任务结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            allApp.updateTenderRegionLngLat();//改表经纬度城市id
//            baseDao.deleteIshandled();
            try {
                Thread.sleep(TimeUnit.MINUTES.toMillis(90));
                //Thread.sleep(TimeUnit.MINUTES.toMillis(15));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void threadMendth() throws InterruptedException {
        QuanGuoGGZYService service = new QuanGuoGGZYService();

        // 开启线程池
        int threadSize = 1;
        ExecutorService threadPool = Executors.newFixedThreadPool(threadSize);

        List<Area> areas = service.getAllArea();

        for (int i = 0; i < areas.size(); i++) {
            threadPool.submit(new CrawlerTask(areas.get(i)));
        }
        threadPool.shutdown();

        while (!threadPool.awaitTermination(20, TimeUnit.SECONDS)) ;
       // while (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) ;

        System.out.println("执行完成~");

    }

    public void updateTenderRegionLngLat() {
        FormalDao formalDao = new FormalDao();
        List<String> citys = formalDao.selectCity();
        for (String city : citys) {
            String cityb = city.replace("省", "").replace("市", "")
                    .replace("区", "");
            if (!cityb.equals("") && null != cityb) {
                Cityregion cityregion = formalDao.selectLonLatByTitle(cityb);
                if (cityregion != null) {
                    System.out.println(cityb);
                    cityregion.setAddress(city);
                    formalDao.updateTenderRegionLngLat(cityregion);
                    System.out.println(cityregion.getTitle() + cityregion.getLetter());
                }
            }
        }
    }

//    public void updateRegiontitle() {
//        BaseDao baseDao = new BaseDao();
//        List<String> stringList = baseDao.selectRegionTitleFromTender();
//        System.out.println(stringList.size());
//        for (String s : stringList) {
//            String cityb = s.replace("省", "").replace("市", "");
//            Cityregion cityregion = baseDao.selectRegionBytitle(cityb);
//            if (cityregion.getType() == 3) {
//                Cityregion cityregion1 = baseDao.selectRegionById(cityregion.getParentid());
//                System.out.println(cityregion1.getTitle());
//                UpdaterReg updaterReg = new UpdaterReg();
//                updaterReg.setRegionid(cityregion1.getId());
//                updaterReg.setNewcity(cityregion1.getTitle());
//                updaterReg.setOldcity(cityregion.getTitle());
//                updaterReg.setLng(cityregion1.getLon());
//                updaterReg.setLat(cityregion1.getLat());
//                baseDao.updateRegionInTender(updaterReg);
//            }
//        }
//    }


    public static long getfoct() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, -32);
        try {
            return date.getTimeInMillis() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


}
