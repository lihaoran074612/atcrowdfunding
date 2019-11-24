package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.mapper.TMenuMapper;
import com.atguigu.atcrowdfunding.service.TMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TMenuServiceImpl implements TMenuService {

    Logger logger = LoggerFactory.getLogger(TMenuServiceImpl.class);
    @Autowired
    TMenuMapper tMenuMapper;


    @Override
    public List<TMenu> listMenuAll() {
        //只存放父菜单
        List<TMenu> parentList = new ArrayList<>();
        Map<Integer,TMenu> cache = new HashMap<>();

        List<TMenu> tMenus = tMenuMapper.selectByExample(null);
        for (TMenu tMenu: tMenus) {
            if (tMenu.getPid() == 0){
                parentList.add(tMenu);
                cache.put(tMenu.getId(),tMenu);
            }
        }
        tMenus.forEach(tMenu -> {
            if(tMenu.getPid() != 0){
                cache.get(tMenu.getPid()).getChildren().add(tMenu);
            }
        });
        logger.info(parentList.toString());
        return parentList;
    }

    @Override
    public List<TMenu> listMenuAllTree() {
        return tMenuMapper.selectByExample(null);
    }

    @Override
    public void saveTMenu(TMenu menu) {
        tMenuMapper.insertSelective(menu);
    }

    @Override
    public TMenu getMenuById(Integer id) {
        return tMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void doUpdate(TMenu menu) {
        tMenuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void doDelete(int id) {
        tMenuMapper.deleteByPrimaryKey(id);
    }
}