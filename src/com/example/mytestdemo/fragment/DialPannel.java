package com.example.mytestdemo.fragment;


/**
 * @author Zengcq
 * @date 2017Äê1ÔÂ18ÈÕ
 * @version 1.0
 * @description
 */
public class DialPannel implements DialPannelInterface {

    private String strNum = "";

    private DialPannelInterface dialPannelInterface;
    private IDialPannelView dialPannelView;

    public DialPannel(IDialPannelView dialPannelView) {
        this.dialPannelView = dialPannelView;
    }

    public void setDialPannelListener(DialPannelInterface dialPannelInterface) {
        this.dialPannelInterface = dialPannelInterface;
    }

    @Override
    public void inputBtNum(int num) {
        if (strNum.length() >= 22) {
            dialPannelView.numInvalidAlarm();
            return;
        }
        if (10 == num)
            strNum += "*";
        else if (11 == num)
            strNum += "#";
        else
            strNum += ("" + num);
        dialPannelView.setDialText(getBtNum());
    }

    @Override
    public void inputBtNum(String num) {
        strNum = num;
    }

    @Override
    public void deleteBtnum() {
        if (strNum.length() > 0){
            strNum = strNum.substring(0, strNum.length() - 1); 
            dialPannelView.setDialText(getBtNum());
        }            
    }

    @Override
    public void deleteAllBtnum() {
        if (strNum.length() > 0){
            strNum = "";
            dialPannelView.setDialText(getBtNum());
        }
            
    }

    @Override
    public String getBtNum() {

        return strNum;
    }

}
