package service;

import tool.DM;

public class ProductionService extends Service{
    private DM dm=null;

    public ProductionService() {
        this.dm = Service.dm;
    }

    /**
     *
     * @param number 制作数量
     */
    public void make(int number){
        for (int i = 0; i < number; i++) {
            dm.delays();
            dm.findAndMoveToPic("开始制作作业.bmp");
            dm.delays();
            dm.leftClick();
            dm.delays(1900,2100);
            dm.keyPressChar("9");
            dm.delays(43000,45000);
        }
    }

    /**
     * 按输入的技能序列依次点击技能
     * @param skillOrder
     */
    private void executeSkills(String skillOrder){
        String[] skills = skillOrder.split("");
        for (String skill : skills) {
            dm.keyPressChar(skill);
            delays();
        }

    }

    /**
     * 每个技能的间隔时间
     */
    private void delays(){
        dm.delays(2900,3100);
    }
}
