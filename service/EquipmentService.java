package service;

import dao.EquipmentDao;
import entity.Equipment;

import java.sql.SQLException;
import java.util.List;

public class EquipmentService {
    EquipmentDao equipmentDao = new EquipmentDao();
    public boolean insert(Equipment area) throws RuntimeException{
        boolean result = false;
        try{
            result = EquipmentDao.insert(area);
        }
        catch (Exception e){
            // 从编译时异常，转换为运行时异常
            throw new RuntimeException(e);
        }
        return result;
    }
    public boolean update(Equipment areaInfo) throws RuntimeException{
        boolean result = false;
        try{
            result = EquipmentDao.update(areaInfo);
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
            result = EquipmentDao.delete(id);
        }
        catch (Exception e){
            // 从编译时异常，转换为运行时异常
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Equipment> selectAll() throws RuntimeException{
        List<Equipment> areaList = null;
        try{
            areaList = EquipmentDao.selectAll();
        }
        catch (Exception e){
            // 从编译时异常，转换为运行时异常
            throw new RuntimeException(e);
        }
        return areaList;
    }
    public List<Equipment> selectBy(String condition, int pageNum, int pageSize) throws RuntimeException{
        List<Equipment> areaInfoList = null;
        try{
            areaInfoList = EquipmentDao.selectBy(condition,pageNum,pageSize);
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
            rows = EquipmentDao.countRows(condition);
        }
        catch (Exception e){
            // 从编译时异常，转换为运行时异常
            throw new RuntimeException(e);
        }
        return rows;
    }
    public List<Equipment> selectAreaBy(String condition){
        List<Equipment> equipmentList = null;
        try {
            equipmentList = equipmentDao.selectEquInfoBy(condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipmentList;
    }
}
