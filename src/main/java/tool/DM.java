package tool;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.STA;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DM {
    private ActiveXComponent dm = new ActiveXComponent("dm.dmsoft");

    /************************************************基本设置******************************************************/

    public void register() throws IOException {
        InputStream is = DM.class.getClassLoader().getResourceAsStream("dmConfig.properties");
        Properties properties = new Properties();
        properties.load(is);
        String zhucema = properties.getProperty("zhucema");
        String fujiama = properties.getProperty("fujiama");
        int a = Dispatch.call(dm, "Reg", zhucema, fujiama).getInt();
        if (a == 1) {
            System.out.println("注册成功");
        } else {
            System.out.println("注册失败，具体原因查询返回数字");
            System.exit(0);
        }
    }

    public int setPath(String path) {
        int i = Dispatch.call(dm, "SetPath", path).getInt();
        return i;
    }

    /************************************************后台设置******************************************************/
    public int bindWindow(int hwnd, String display, String mouse, String keypad, int mode) {
        int i = Dispatch.call(dm, "BindWindow", hwnd, display, mouse, keypad, mode).getInt();
        return i;
    }

    public int bindWindow(int hwnd) {
        int i = bindWindow(hwnd, "normal", "normal", "windows", 0);
        return i;
    }

    /************************************************键鼠******************************************************/

    public void moveTo(int x, int y) {
        Dispatch.call(dm, "MoveTo", x, y);
    }
    public void moveTo(int[] point) {
        moveTo(point[0], point[1]);
    }

    /**
     * 找到图片并将鼠标移动至图片大小内随机坐标
     * @param picName
     */
    public void findAndMoveToPic(String picName){
        int[] picLocation = findPicE(picName);
        String picSize = getPicSize(picName);
        String[] sizeArray = picSize.split(",");
        picLocation[0] += Math.random() * Integer.parseInt(sizeArray[0]);
        picLocation[1] += Math.random() * Integer.parseInt(sizeArray[1]);
        moveTo(picLocation);
    }
    public void leftDown() {
        Dispatch.call(dm, "LeftDown");
    }
    public void leftUp() {
        Dispatch.call(dm, "LeftUp");
    }
    public void leftClick(){
        leftDown();
        delays(200,300);
        leftUp();
    }

    public void keyDownChar(String key_str) {
        Dispatch.call(dm, "KeyDownChar", key_str);
    }

    public void keyUpChar(String key_str) {
        Dispatch.call(dm, "KeyUpChar", key_str);
    }

    public void keyPressChar(String key_str) {
        keyDownChar(key_str);
        delays(200,300);
        keyUpChar(key_str);
    }

    /************************************************窗口******************************************************/
    public String enumWindow(int parent, String title, String class_name, int filter) {
        String s = Dispatch.call(dm, "EnumWindow", parent, title, class_name, filter).getString();
        return s;
    }

    public String enumWindow(String title, String class_name) {
        String s = enumWindow(0, title, class_name, 1 + 2);
        return s;
    }

    /************************************************图色******************************************************/
    public String findPicE(int x1, int y1, int x2, int y2, String pic_name, String delta_color, double sim, int dir) {
        String s = Dispatch.call(dm, "FindPicE", x1, y1, x2, y2, pic_name, delta_color, sim, dir).getString();
        return s;
    }

    public int[] findPicE(String pic_name) {
        String s = findPicE(0, 0, 1920, 1080, pic_name, "050505", 0.95, 2);
        String[] split = s.split("\\|");
        if (split[0].equals("-1")) {
            System.out.println(pic_name + "，抓图失败");
        }
        int[] point = new int[2];
        point[0] = Integer.parseInt(split[1]);
        point[1] = Integer.parseInt(split[2]);
        return point;
    }
    public String  getPicSize(String pic_name){
        String s = Dispatch.call(dm, "GetPicSize",pic_name).getString();
        return s;
    }

    /************************************************系统******************************************************/
    public void delays(int a, int b) {
        Dispatch.call(dm, "Delays", a, b);
    }

    /**
     * 默认延迟1秒左右
     */
    public void delays() {
        delays(900, 1100);
    }
}
