import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import service.ProductionService;
import sun.awt.windows.WPrinterJob;
import tool.DM;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        ProductionService pm = new ProductionService();
        pm.make(9);
    }

}
