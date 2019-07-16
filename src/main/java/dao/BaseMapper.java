package dao;


import bean.Tender;
import org.apache.ibatis.annotations.Param;

import pojo1.*;


import java.util.List;


public interface BaseMapper {

    // 插入
    int insertTender(Tender tender);
    //插入正文表
    int insertTenderContent(Tender tender);
    //查询数据库中所有的城市
    List<String> selectCity();

    Cityregion selectLonLatByTitle(String sd);

    void updateTenderRegionLngLat(Cityregion cityregion);

    void deleteIshandled();

    List<String> selectRegionTitleFromTender();

    Cityregion selectRegionBytitle(String s);

    Cityregion selectRegionById(int id);

    void updateRegionInTender(UpdaterReg reg);

    void deleteHistory(@Param("asd") int daytime);

    int selectDetailUrl(String city);

    int selectTitle(String title);

    void updateFormalId(Tender tender);

    void deleteRepeat();

}
