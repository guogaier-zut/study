package service;

import dao.AreaDao;
import dao.EquipmentDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    public Map<String, List> queryAreaAndEquipList() {
        Map<String, List> map = new HashMap<>();
        AreaDao areaDao = new AreaDao();
        EquipmentDao equipmentDao = new EquipmentDao();
        List<String> allBuilding, allEquipType;

        {
            try {
                allBuilding = areaDao.selectAllBuilding();
                allEquipType = equipmentDao.selectAllEquipType();
                map.put("area", allBuilding);
                map.put("equip", allEquipType);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return map;
        }
    }
}
