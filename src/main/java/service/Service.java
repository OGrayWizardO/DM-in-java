package service;

import tool.DM;

import java.io.IOException;

public class Service {
    static DM dm = new DM();

    static {
        try {
            dm.register();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dm.setPath("D:\\IdeaProjects\\大漠工具测试\\src\\main\\resources\\static\\pics");
        String s1 = dm.enumWindow("最终幻想XIV", "FFXIVGAME");
        int hwnd = Integer.parseInt(s1);
        int i = dm.bindWindow(hwnd);
        System.out.println((i==1)?"绑定成功":"绑定失败");
    }
}
