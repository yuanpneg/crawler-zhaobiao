package dao;


import bean.Tender;
import org.apache.ibatis.session.SqlSession;


import pojo1.Cityregion;
import pojo1.UpdaterReg;
import utils.MyBatisUtils;

import java.util.List;

public class BaseDao {

    // 插入对像
    public void insertTender(Tender tender) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.insertTender(tender);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(tender.getTitle());
            System.out.println(tender.getFrom());
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }


    //查询数据库中所有城市
    public List<String> selectCity() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            List<String> citys = mapper.selectCity();
            if (citys != null) {
                return citys;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
        return null;
    }


    public Cityregion selectLonLatByTitle(String asd) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            Cityregion stringList = mapper.selectLonLatByTitle(asd);
            return stringList;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
        return null;

    }

    public void updateTenderRegionLngLat(Cityregion cityregion) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.updateTenderRegionLngLat(cityregion);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    public void deleteIshandled() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.deleteIshandled();
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }


    public List<String> selectRegionTitleFromTender() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            List<String> stringList = mapper.selectRegionTitleFromTender();
            return stringList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
        return null;
    }

    public Cityregion selectRegionBytitle(String s) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            return mapper.selectRegionBytitle(s);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);

        }
        return null;
    }

    public Cityregion selectRegionById(int id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            Cityregion cityregion = mapper.selectRegionById(id);
            return cityregion;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);

        }
        return null;
    }


    public void updateRegionInTender(UpdaterReg reg) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.updateRegionInTender(reg);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);

        }
    }

    public void deleteHistory(long daytime) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.deleteHistory((int) daytime);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);

        }
    }

    public int selectDetailUrl(String url) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            int flag = mapper.selectDetailUrl(url);
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);

        }
        return -1;
    }


    public int selectTitle(String title){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            int flag = mapper.selectTitle(title);
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtils.closeSession(sqlSession);

        }
        return -1;
    }

    //修改测试服的FormalId字段
    public void updateFormalId(Tender tender){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.updateFormalId(tender);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }


    //删除重复
    public void  deleteRepeat(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getBaseFactory(false);
            BaseMapper mapper = sqlSession.getMapper(BaseMapper.class);
            mapper.deleteRepeat();
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }



}
