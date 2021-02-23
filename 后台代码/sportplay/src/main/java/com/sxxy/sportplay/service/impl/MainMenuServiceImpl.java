package com.sxxy.sportplay.service.impl;

import com.sxxy.sportplay.bean.MainMenu;
import com.sxxy.sportplay.dao.MainMenuDao;
import com.sxxy.sportplay.service.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainMenuServiceImpl implements MainMenuService {

    @Autowired
    private MainMenuDao mainMenuDao;

    @Override
    public List<MainMenu> getMainMenus() {
        return mainMenuDao.getMainMenus();
    }
}
