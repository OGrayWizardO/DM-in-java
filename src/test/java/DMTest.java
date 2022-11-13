import org.junit.Test;
import tool.DM;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


public class DMTest {
    

    @Test
    public void test() throws IOException {
        DM dm = new DM();
        dm.register();
        dm.setPath("D:\\IdeaProjects\\大漠工具测试\\src\\main\\resources\\static\\pics");

        String picSize = dm.getPicSize("开始制作作业.bmp");
        System.out.println(picSize);

    }

}
