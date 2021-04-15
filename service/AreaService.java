package service;

import dao.AreaDao;
import entity.Area;

import java.sql.SQLException;
import java.util.List;

public class AreaService {
    private AreaDao areaDao = new AreaDao();
    public boolean insert(Area area) throws RuntimeException{
        boolean result = false;
        try{
            result = areaDao.insert(area);
        }
        catch (Exception e){
            // 从编译时异常，转换为运行时异常
            throw new RuntimeException(e);
        }
        return result;
    }
    public boolean update(Area areaInfo) throws RuntimeException{
        boolean result = false;
        try{
            result = areaDao.update(areaInfo);
        }
        catch (Exception e){
            // 从编译时异常，转换为运行时异常
            throw new RuntimeException(e);
        }
        return result;
    }
    public boolean delete(int id) throws RuntimeException{
        boolean result = false;
        try{
            result = areaDao.delete(id);
        }
        catch (Exception e){
            // 从编译时异常，转换为运行时异常
            throw new RuntimeException(e);
        }
        return result;
    }
    public Area selectOne(int id) throws RuntimeException{
        Area area = null;
        try{
            area = areaDao.selectOne(id);
        }
        catch (Exception e){
            // 从编译时异常，转换为运行时异常
            throw new RuntimeException(e);
        }
        return area;
    }
    public List<Area> selectAll() throws RuntimeException{
        List<Area> areaList = null;
        try{
            areaList = areaDao.selectAll();
        }
        catch (Exception e){
            // 从编译时异常，转换为运行时异常
            throw new RuntimeException(e);
        }
        return areaList;
    }
    public List<Area> selectBy(String condition, int pageNum, int pageSize) throws RuntimeException{
        List<Area> areaInfoList = null;
        try{
            areaInfoList = areaDao.selectBy(condition,pageNum,pageSize);
        }
        catch (Exception e){
            // 从编译时异常，转换为运行时异常
            throw new RuntimeException(e);
        }
        return areaInfoList;
    }

    public int countRows(String condition) {
        int rows =0;
        try{
            rows = areaDao.countRows(condition);
        }
        catch (Exception e){
            // 从编译时异常，转换为运行时异常
            throw new RuntimeException(e);
        }
        return rows;
    }
    public List<Area> selectAreaBy(String condition){
        List<Area> areaList = null;
        try {
            areaList = areaDao.selectAreaInfoBy(condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return areaList;
    }
}
