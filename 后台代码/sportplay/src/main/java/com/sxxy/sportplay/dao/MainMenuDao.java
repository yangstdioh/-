package com.sxxy.sportplay.dao;

import com.sxxy.sportplay.bean.MainMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainMenuDao {

    public List<MainMenu> getMainMenus();
}
