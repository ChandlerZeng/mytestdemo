package com.example.mytestdemo.txz;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;

import com.example.mytestdemo.MyTestApplication;
import com.example.mytestdemo.R;
import com.example.mytestdemo.common.Common;
import com.example.mytestdemo.main.MainTestActivity;
import com.example.mytestdemo.utils.LogUtil;
import com.txznet.sdk.TXZAsrManager;
import com.txznet.sdk.TXZAsrManager.AsrComplexSelectCallback;
import com.txznet.sdk.TXZAsrManager.CommandListener;
import com.txznet.sdk.TXZCallManager;
import com.txznet.sdk.TXZConfigManager;
import com.txznet.sdk.TXZConfigManager.ActiveListener;
import com.txznet.sdk.TXZConfigManager.FloatToolType;
import com.txznet.sdk.TXZConfigManager.InitListener;
import com.txznet.sdk.TXZConfigManager.InitParam;
import com.txznet.sdk.TXZMusicManager;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;

public class TXZTestInterface implements InitListener, ActiveListener {

    public static final String ACTION_TXZ = "cn.ritu.rtnavi.voicecommand";
    public static final String KEY_TXZ_CMD = "command";
    public static final String KEY_TXZ_TIME = "time";
    public static final String TXZ_PACKAGE_NAME = "cn.ritu.rtnavi";
    public static final String TXZ_ACTIVITY_NAME = "cn.ritu.rtnavi.main.MainActivity";
    public static boolean isTxzScreenShow = false;
    public static boolean isFirstInit = true;

    private static TXZTestInterface m_txzInterface = null;

    private InitParam mInitParam;
    private Context m_context;

    private CommonRequest mXimalaya;
    private String mAppSecret = "4d8e605fa7ed546c4bcb33dee1381179";
    private boolean isActivited = false;

    public void Init(Context context) {
        // ��ȡ��������appId��appToken
        m_context = context;
        String appId = context.getResources().getString(
                R.string.txz_sdk_init_app_id);
        String appToken = context.getResources().getString(
                R.string.txz_sdk_init_app_token);
        // ���ó�ʼ������
        mInitParam = new InitParam(appId, appToken);
        String[] wakeupKeywords = context.getResources().getStringArray(
                R.array.txz_sdk_init_wakeup_keywords);
        mInitParam.setWakeupKeywordsNew(wakeupKeywords);
//        mInitParam.setFloatToolType(FloatToolType.FLOAT_NONE);
        TXZConfigManager.getInstance().initialize(context, mInitParam, this,
                this);
//         TXZConfigManager.getInstance().initialize(context, this);
        // TXZNavManager.getInstance().setNavTool(TXZNavManager.NavToolType.NAV_TOOL_BAIDU_MAP);
        /* cmdListenerStartNavi(); */
//        cmdAllCommandListener();
//        asrAllCommandListener();
//        TXZCallManager.getInstance().setCallTool(new CallToolInterface());
        // TXZAsrManager.getInstance().setAsrTool(new AsrToolInterface());

    }

    public static TXZTestInterface getInstance() {
        if (m_txzInterface == null) {
            m_txzInterface = new TXZTestInterface();
        }
        return m_txzInterface;
    }

    @Override
    public void onError(int arg0, String arg1) {
        // ��ʼ������
        MainTestActivity.dismissProgress();
        Log.d("RituNavi", "ͬ����������ʼ��ʧ��");
    }

    @Override
    public void onSuccess() {
        // ��ʼ���ɹ�
        MainTestActivity.dismissProgress();
        if (TXZNaviSettingReceiver.isFirstInit) {
            Log.d("RituNavi", "ͬ����������ʼ���ɹ�");
            TXZNaviSettingReceiver.isFirstInit = false;
        }
        // ���û��ѷ�ֵ
        TXZConfigManager.getInstance().setWakeupThreshhold(-3.0f);
        LogUtil.d("TXZNaviSettingReceiver.isFirstInit:"
                + TXZNaviSettingReceiver.isFirstInit);
        // SyncContacts.syncContacts();
    }

    @Override
    public void onFirstActived() {
        // �״�������������Ҫ����������ʾ���������������
        if (!isActivited) {
            Log.d("RituNavi", "ͬ������������");
            isActivited = true;
        }
        LogUtil.d("isActivited:" + isActivited);
    }

    private void cmdAllCommandListener() {
        cmdListenerStartNavi();
        cmdListenerOpenAddressBook();
        cmdListenerCloseAddressBook();
        cmdListenerOpenCalendar();
        cmdListenerOpenFM();
        cmdListenerOpenWeiXin();
        cmdListenerCloseWeiXin();
        cmdListenerOpenWeiTu();
        cmdListenerCloseWeiTu();
        cmdListenerOpenBluetoothPhone();
        cmdListenerOpenFileManager();
        cmdListenerOpenOneWifi();
        cmdListenerOpenOnlineMap();
        cmdListenerOpenOnlineShop();
        cmdListenerOpenWeixinAss();
         cmdListenerOpenOnlineFM();
         cmdListenerCloseOnlineFM();
        cmdListenerOpenSettings();
        cmdListenerCloseSettings();
        cmdListenerOpenTest();
        cmdListenerCloseTest();
        cmdListenerOpenKuWo();
        cmdListenerCloseKuWo();
        // cmdListenerGoHome();
        // cmdListenerGoToCompany();
        /*
         * cmdListenerOpenRealtimeTraffic(); //
         * cmdListenerCloseRealtimeTraffic(); //
         * cmdListenerChangeRouteTypeFast(); //
         * cmdListenerChangeRouteTypeShort(); //
         * cmdListenerChangeRouteTypeEconomic(); //
         * cmdListenerChangeRouteTypeOptimal(); cmdListenerOpenAddressBook();
         * cmdListenerCloseAddressBook(); cmdListenerOpenHistoryRecord();
         * cmdListenerCloseHistoryRecord(); cmdListenerOpenUsuallyplace();
         * cmdListenerCloseUsuallyplace(); cmdListenerViewRoute();
         * cmdListenerBackToMap(); cmdListenerOpenRoutePlan();
         * cmdListenerCloseRoutePlan(); cmdListenerOpenEagelView(); //
         * cmdListenerOpenDoubleScreen(); cmdListenerSingleScreen();
         * cmdListenerOpenDetail(); cmdListenerCloseDetail();
         * cmdListenerChangeViewMode(); cmdListenerZoomOut();
         * cmdListenerZoomIn(); cmdListenerBroadCastAgain();
         * cmdListenerNextServiceDistance(); // cmdListenerChangePagePrevious();
         * // cmdListenerChangePageNext();
         */}

    private void asrAllCommandListener() {
        asrListenerHome();
        asrListenerSreenOff();
        asrListenerSreenOn();
    }

    private void asrListenerHome() {
        TXZAsrManager.getInstance().useWakeupAsAsr(
                new AsrComplexSelectCallback() {

                    @Override
                    public String getTaskId() {
                        // TODO Auto-generated method stub
                        return "asr_cmd_home";
                    }

                    @Override
                    public boolean needAsrState() {
                        // TODO Auto-generated method stub
                        return false;
                    }

                    @Override
                    public void onCommandSelected(String type, String command) {
                        // TODO Auto-generated method stub
                        super.onCommandSelected(type, command);
                        if ("CMD_HOME".equals(type)) {
                            LogUtil.e("type0:" + type + ",command0:" + command);
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            MyTestApplication.getInstance().startActivity(
                                    intent);
                            LogUtil.e("type:" + type + ",command:" + command);
                        }
                    }
                }.addCommand("CMD_HOME", "�ص�����", "��������"));
        LogUtil.e("RituNavi", "����Ϊ��ע��ص����滽�Ѵ�");
    }
    
    private void asrListenerSreenOn() {
        TXZAsrManager.getInstance().useWakeupAsAsr(
                new AsrComplexSelectCallback() {

                    @Override
                    public String getTaskId() {
                        // TODO Auto-generated method stub
                        return "asr_cmd_screen_on";
                    }

                    @Override
                    public boolean needAsrState() {
                        // TODO Auto-generated method stub
                        return false;
                    }

                    @Override
                    public void onCommandSelected(String type, String command) {
                        // TODO Auto-generated method stub
                        super.onCommandSelected(type, command);
                        if ("CMD_SCREEN_ON".equals(type)) {
                            ScreenControl.screenOn();
                        }
                    }
                }.addCommand("CMD_SCREEN_ON", "����Ļ", "������Ļ"));
        LogUtil.e("RituNavi", "����Ϊ��ע�����Ļ���Ѵ�");
    }
    
    private void asrListenerSreenOff() {
        TXZAsrManager.getInstance().useWakeupAsAsr(
                new AsrComplexSelectCallback() {

                    @Override
                    public String getTaskId() {
                        // TODO Auto-generated method stub
                        return "asr_cmd_screen_off";
                    }

                    @Override
                    public boolean needAsrState() {
                        // TODO Auto-generated method stub
                        return false;
                    }

                    @Override
                    public void onCommandSelected(String type, String command) {
                        // TODO Auto-generated method stub
                        super.onCommandSelected(type, command);
                        if ("CMD_SCREEN_OFF".equals(type)) {
                            ScreenControl.screenOff();
                        }
                    }
                }.addCommand("CMD_SCREEN_OFF", "�ر���Ļ", "Ϩ����Ļ"));
        LogUtil.e("RituNavi", "����Ϊ��ע��ر���Ļ���Ѵ�");
    }

    private void cmdListenerGoHome() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {
            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_GO_HOME".equals(arg1)) {
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ComponentName component = new ComponentName(
                            "cn.ritu.rtnavi",
                            "cn.ritu.rtnavi.main.MainActivity");
                    intent.setComponent(component);
                    if (intent.resolveActivity(MyTestApplication.getInstance()
                            .getPackageManager()) != null) {
                        MyTestApplication.getInstance().getApplicationContext()
                                .startActivity(intent);
                    }
                    transferCMD(TXZBasic.ACTION_TYPE_GO_HOME);
                }
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "�ؼ�", "��Ҫ�ؼ�" },
                "CMD_GO_HOME");
    }

    private void cmdListenerGoToCompany() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {
            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_GO_TO_COMPANY".equals(arg1)) {
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ComponentName component = new ComponentName(
                            "cn.ritu.rtnavi",
                            "cn.ritu.rtnavi.main.MainActivity");
                    intent.setComponent(component);
                    if (intent.resolveActivity(MyTestApplication.getInstance()
                            .getPackageManager()) != null) {
                        MyTestApplication.getInstance().getApplicationContext()
                                .startActivity(intent);
                    }

                    transferCMD(TXZBasic.ACTION_TYPE_GO_TO_COMPANY);
                }
            }
        });
        TXZAsrManager.getInstance().regCommand(
                new String[] { "��λ", "ȥ��λ", "��˾", "ȥ��˾" }, "CMD_GO_TO_COMPANY");
    }

    private void cmdListenerStartNavi() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
                cmdCommandListener("CMD_START_NAVI", arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(
                new String[] { "�ҵĵ�ͼ", "�ҵĵ���", "����", "�򿪵���", "��������", "�л�������" },
                "CMD_START_NAVI");
        Log.d("RituNavi", "����Ϊ��ע��򿪵�������");
    }

    private void cmdListenerOpenAddressBook() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {
            @Override
            public void onCommand(String arg0, String arg1) {
                cmdCommandListener("CMD_OPEN_ADDR", arg1,
                        TXZBasic.ACTION_TYPE_VIEWADDRESSBOOK_OPEN);
            }
        });
        TXZAsrManager.getInstance().regCommand(
                new String[] { "�򿪵�ַ��", "�鿴��ַ��", "���ҵĵ�ַ", "��ַ��", "ѡ���ַ",
                        "�鿴����ĵ�ַ" }, "CMD_OPEN_ADDR");
    }

    private void cmdListenerCloseAddressBook() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {
            @Override
            public void onCommand(String arg0, String arg1) {
                cmdCommandListener("CMD_CLOSE_ADDR", arg1,
                        TXZBasic.ACTION_TYPE_VIEWADDRESSBOOK_CLOSE);
            }
        });
        TXZAsrManager.getInstance().regCommand(
                new String[] { "�رյ�ַ��", "�رյ�ַ" }, "CMD_CLOSE_ADDR");
    }

    private void cmdListenerOpenCalendar() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_CALENDAR".equals(arg1)) {
                    openAppByPackageName("com.android.calendar");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "������" },
                "CMD_OPEN_CALENDAR");
        Log.d("RituNavi", "����Ϊ��ע�����������");
    }

    private void cmdListenerOpenFM() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_FM".equals(arg1)) {
                    openAppByPackageName("cn.ritu.fm");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "�򿪹㲥" },
                "CMD_OPEN_FM");
        Log.d("RituNavi", "����Ϊ��ע��򿪹㲥����");
    }

    private void cmdListenerOpenKuWo() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_KUWO".equals(arg1)) {
                    openAppByPackageName("cn.kuwo.kwmusiccar");
                    // AppMgrToolInterface.getInstance().openApp("com.example.mynewqq");

                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "�򿪿���" },
                "CMD_OPEN_KUWO");
        Log.d("RituNavi", "����Ϊ��ע��򿪿�������");
    }

    private void cmdListenerCloseKuWo() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_CLOSE_KUWO".equals(arg1)) {
                    // closeAppByPackageName("com.example.mynewqq");
//                    killProcessByPackageName2(MyTestApplication.getInstance()
//                            .getApplicationContext(), "cn.kuwo.kwmusiccar");
//                    pauseMusic(MyTestApplication.getInstance()
//                            .getApplicationContext(),KeyEvent.KEYCODE_MEDIA_PAUSE);
                    closeAppByPackageName("cn.kuwo.kwmusiccar");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "�رտ���", "�˳�����" },
                "CMD_CLOSE_KUWO");
        Log.d("RituNavi", "����Ϊ��ע��رտ�������");
    }

    private void cmdListenerOpenWeiXin() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_WEIXIN".equals(arg1)) {
                    openAppByPackageName("com.example.mynewqq");
                    // AppMgrToolInterface.getInstance().openApp("com.example.mynewqq");

                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "��΢��" },
                "CMD_OPEN_WEIXIN");
        Log.d("RituNavi", "����Ϊ��ע���΢������");
    }

    private void cmdListenerCloseWeiXin() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_CLOSE_WEIXIN".equals(arg1)) {
                    // closeAppByPackageName("com.example.mynewqq");
                    killProcessByPackageName2(MyTestApplication.getInstance()
                            .getApplicationContext(), "com.example.mynewqq");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "�ر�΢��", "�˳�΢��" },
                "CMD_CLOSE_WEIXIN");
        Log.d("RituNavi", "����Ϊ��ע��ر�΢������");
    }

    private void cmdListenerOpenTest() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_TEST".equals(arg1)) {
                    openAppByPackageName("com.example.mytestdemo");
                    // TXZSysManager.getInstance().setAppMgrTool(AppMgrToolInterface.getInstance());
                    // AppMgrToolInterface.getInstance().openApp("com.example.mytestdemo");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "�򿪲���" },
                "CMD_OPEN_TEST");
        Log.d("RituNavi", "����Ϊ��ע��򿪲���");
    }

    private void cmdListenerCloseTest() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_CLOSE_TEST".equals(arg1)) {
                    // closeAppByPackageName("com.example.mytestdemo");
                    killProcessByPackageName2(MyTestApplication.getInstance()
                            .getApplicationContext(), "com.example.mytestdemo");
                    // SuUtil.kill("com.example.mytestdemo");
                    // TXZSysManager.getInstance().setAppMgrTool(AppMgrToolInterface.getInstance());
                    // AppMgrToolInterface.getInstance().closeApp("com.example.mytestdemo");
                    // pauseMusic();
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "�رղ���", "�˳�����" },
                "CMD_CLOSE_TEST");
        Log.d("RituNavi", "����Ϊ��ע��رղ�������");
    }

    private void pauseMusic() {
        Intent freshIntent = new Intent();
        freshIntent.setAction("com.android.music.musicservicecommand.pause");
        freshIntent.putExtra("command", "pause");
        MyTestApplication.getInstance().sendBroadcast(freshIntent);
    }

    private void cmdListenerOpenSettings() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_SETTINGS".equals(arg1)) {
                    openAppByPackageName("cn.ritu.settings");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);

            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "�򿪵�������" },
                "CMD_OPEN_SETTINGS");
        Log.d("RituNavi", "����Ϊ��ע��򿪵�������");
    }

    private void cmdListenerCloseSettings() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_CLOSE_SETTINGS".equals(arg1)) {
                    closeAppByPackageName("cn.ritu.settings");
                    killProcessByPackageName(MyTestApplication.getInstance()
                            .getApplicationContext(), "cn.ritu.settings");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(
                new String[] { "�رյ�������", "�˳���������" }, "CMD_CLOSE_SETTINGS");
        Log.d("RituNavi", "����Ϊ��ע��رյ�����������");
    }

    private void cmdListenerOpenOnlineMap() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_ONLINE_MAP".equals(arg1)) {
                    openAppByPackageName("com.baidu.BaiduMap");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "�����ߵ�ͼ" },
                "CMD_OPEN_ONLINE_MAP");
        Log.d("RituNavi", "����Ϊ��ע������ߵ�ͼ");
    }

    private void cmdListenerOpenWeixinAss() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_WEIXIN_ASSISTANT".equals(arg1)) {
                    openAppByPackageName("com.txznet.webchat");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "��΢������" },
                "CMD_OPEN_WEIXIN_ASSISTANT");
        Log.d("RituNavi", "����Ϊ��ע���΢������");
    }

    private void cmdListenerOpenBluetoothPhone() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_BLUETOOTH_PHONE".equals(arg1)) {
                    openAppByPackageName("cn.ritu.bluephone");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "�������绰" },
                "CMD_OPEN_BLUETOOTH_PHONE");
        Log.d("RituNavi", "����Ϊ��ע��������绰");
    }

    private void cmdListenerOpenOnlineFM() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_ONLINE_FM".equals(arg1)) {
                    openAppByPackageName("com.ximalaya.ting.android");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "�������̨" },
                "CMD_OPEN_ONLINE_FM");
        Log.d("RituNavi", "����Ϊ��ע��������̨");
    }

    private void cmdListenerCloseOnlineFM() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_CLOSE_ONLINE_FM".equals(arg1)) {
//                    Log.d("RituNavi",
//                            "XmPlayerManager isPlaying "
//                                    + XmPlayerManager.getInstance(
//                                            MyTestApplication.getInstance()
//                                                    .getApplicationContext())
//                                            .isPlaying());
//                    XmPlayerManager.getInstance(
//                            MyTestApplication.getInstance()
//                                    .getApplicationContext()).stop();
//                    Log.d("RituNavi", "XmPlayerManager stop "
//                            + "com.ximalaya.ting.android");
                    // killProcessByPackageName2(MyTestApplication.getInstance().getApplicationContext(),"com.ximalaya.ting.android");
                    closeAppByPackageName("com.ximalaya.ting.android");
                }
            }
        });
        TXZAsrManager.getInstance().regCommand(
                new String[] { "�ر������̨", "�˳������̨","�ر����ߵ�̨" }, "CMD_CLOSE_ONLINE_FM");
        Log.d("RituNavi", "����Ϊ��ע��ر������̨����");
    }

    private void cmdListenerOpenOnlineShop() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_ONLINE_SHOP".equals(arg1)) {
                    openAppByPackageName("com.ritu.waterseven.android");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "��Ӧ���̵�" },
                "CMD_OPEN_ONLINE_SHOP");
        Log.d("RituNavi", "����Ϊ��ע���Ӧ���̵�");
    }

    private void cmdListenerOpenFileManager() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_FILE_MANAGER".equals(arg1)) {
                    openAppByPackageName("com.chaozhuo.filemanager");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "���ļ�����" },
                "CMD_OPEN_FILE_MANAGER");
        Log.d("RituNavi", "����Ϊ��ע����ļ�����");
    }

    private void cmdListenerOpenOneWifi() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_ONE_WIFI".equals(arg1)) {
                    openAppByPackageNameWithAction("cn.ritu.launcher",
                            "cn.ritu.start.wifi");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "��һ��WIFI" },
                "CMD_OPEN_ONE_WIFI");
        Log.d("RituNavi", "����Ϊ��ע���һ��WIFI");
    }

    private void cmdListenerOpenWeiTu() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_OPEN_WEITU".equals(arg1)) {
                    openAppByPackageName("com.libtop.weitu");
                    // TXZSysManager.getInstance().setAppMgrTool(AppMgrToolInterface.getInstance());
                    AppMgrToolInterface.getInstance().openApp(
                            "com.libtop.weitu");
                }
                Log.d("RituNavi", "arg0:" + arg0 + "arg1:" + arg1);
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "��΢ͼ" },
                "CMD_OPEN_WEITU");
        Log.d("RituNavi", "����Ϊ��ע���΢ͼ����");
    }

    private void cmdListenerCloseWeiTu() {
        TXZAsrManager.getInstance().addCommandListener(new CommandListener() {

            @Override
            public void onCommand(String arg0, String arg1) {
                if ("CMD_CLOSE_WEITU".equals(arg1)) {
                    Log.d("RituNavi", "killBackgroundProcesses 0 "
                            + "com.libtop.weitu");
                    // closeAppByPackageName("com.libtop.weitu");
                    killProcessByPackageName2(MyTestApplication.getInstance()
                            .getApplicationContext(), "com.libtop.weitu");
                    // SuUtil.kill("com.libtop.weitu");
                    // TXZSysManager.getInstance().setAppMgrTool(AppMgrToolInterface.getInstance());
                    AppMgrToolInterface.getInstance().closeApp(
                            "com.libtop.weitu");
                }
            }
        });
        TXZAsrManager.getInstance().regCommand(new String[] { "�ر�΢ͼ", "�˳�΢ͼ" },
                "CMD_CLOSE_WEITU");
        Log.d("RituNavi", "����Ϊ��ע��ر�΢ͼ����");
    }

    private void openAppByPackageName(String packageName) {
        PackageManager manager = MyTestApplication.getInstance()
                .getApplicationContext().getPackageManager();
        Intent intent = manager.getLaunchIntentForPackage(packageName);
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyTestApplication.getInstance().getApplicationContext()
                .startActivity(intent);
        Log.d("RituNavi", "����Ϊ����" + packageName);
    }

    private void openAppByPackageNameWithAction(String packageName,
            String action) {
        PackageManager manager = MyTestApplication.getInstance()
                .getApplicationContext().getPackageManager();
        Intent intent = manager.getLaunchIntentForPackage(packageName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(action);
        MyTestApplication.getInstance().getApplicationContext()
                .startActivity(intent);
        Log.d("RituNavi", "����Ϊ����" + packageName);
    }

    private void closeAppByPackageName(String packageName) {
        ActivityManager am = (ActivityManager) MyTestApplication.getInstance()
                .getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        try {
            Method forceStopPackage = Class.forName(
                    "android.app.ActivityManager").getMethod(
                    "forceStopPackage", String.class);
            forceStopPackage.invoke(am, packageName);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("RituNavi", "forceStopPackage 6 " + e.toString());
        }
    }

    private void killProcessByPackageName2(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        // ���intent��ָ��category����ô����intent filter��������ʲô��Ӧ����ƥ��ġ�
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        System.exit(0);
        ActivityManager activityManger = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        activityManger.killBackgroundProcesses(packageName);
    }

    private void killProcessByPackageName(Context context, String packageName) {
        int count = 0;// ��ɱ���̼���
        long beforeClearMemory = getAvailMemory(context);
        ActivityManager activityManger = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = activityManger
                .getRunningAppProcesses();
        for (int i = 0; i < list.size(); i++) {
            ActivityManager.RunningAppProcessInfo apinfo = list.get(i);
            if (apinfo.processName.equals(new String(packageName))) {
                String[] pkgList = apinfo.pkgList;
                Log.d("RituNavi", "pkgList " + pkgList.toString());
                for (int j = 0; j < pkgList.length; j++) {
                    // 2.2�����ǹ�ʱ��,����killBackgroundProcesses����
                    activityManger.killBackgroundProcesses(pkgList[j]);
                    Log.d("RituNavi", "pkgList[j] " + pkgList[j].toString());
                    count++;
                }
            }
        }
        long afterMem = getAvailMemory(context);// �������ڴ�ռ��
        long cleanMem = afterMem - beforeClearMemory;
        Log.d("RituNavi", "forceStopPackage kill process size is " + count
                + ", clean memory is " + cleanMem + "M");
    }

    /**
     * ��ȡandroid��ǰ�����ڴ��С
     * 
     */
    private long getAvailMemory(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        return mi.availMem / (1024 * 1024);
    }

    // Intent intent = new Intent(Intent.ACTION_MAIN);
    // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    // //���intent��ָ��category����ô����intent filter��������ʲô��Ӧ����ƥ��ġ�
    // intent.addCategory(Intent.CATEGORY_HOME);
    // MyTestApplication.getInstance().getBaseContext().startActivity(intent);
    // }catch (IllegalAccessException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // Log.d("RituNavi","forceStopPackage IllegalAccessException"+e.toString());
    // } catch (IllegalArgumentException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // Log.d("RituNavi","forceStopPackage IllegalArgumentException"+e.toString());
    // } catch (InvocationTargetException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // Log.d("RituNavi","forceStopPackage InvocationTargetException"+e.toString());
    // } catch (NoSuchMethodException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // Log.d("RituNavi","forceStopPackage NoSuchMethodException"+e.toString());
    // } catch (ClassNotFoundException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // Log.d("RituNavi","forceStopPackage ClassNotFoundException"+e.toString());
    // }
    // }

    private void cmdCommandListener(String cmd, String arg1) {
        cmdCommandListener(cmd, arg1, -1);
    }

    private void cmdCommandListener(String cmd, String arg1, int type) {
        if (cmd.equals(arg1)) {

            if (type != -1) {
                transferCMD(type);
            } else {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ComponentName component = new ComponentName(TXZ_PACKAGE_NAME,
                        TXZ_ACTIVITY_NAME);
                Log.d("RituNavi", "�򿪵��������յ�������Ϊ���򿪵���");
                startActivity(component, intent);
            }
        }
    }

    private void startActivity(ComponentName component, Intent intent) {
        intent.setComponent(component);
        if (intent.resolveActivity(MyTestApplication.getInstance()
                .getPackageManager()) != null) {
            MyTestApplication.getInstance().startActivity(intent);
        }
    }

    public void transferCMD(int command) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss SSS");
        String time = format.format(date);

        ComponentName componentName = new ComponentName(TXZ_PACKAGE_NAME,
                TXZ_ACTIVITY_NAME);
        Intent intentMain = new Intent();
        intentMain.setAction(ACTION_TXZ);
        intentMain.putExtra(KEY_TXZ_CMD, command);
        intentMain.putExtra(KEY_TXZ_TIME, time);
        intentMain.putExtra(Common.BOOT_PARAM, Common.BOOT_PARAM_DEFAULT_VALUE);
        intentMain.setComponent(componentName);
        intentMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(componentName, intentMain);
    }

    public void checkNewIntent() {
        Activity activity = (Activity) m_context;
        Intent intent = activity.getIntent();
        String action = intent.getAction();
        Bundle bundle = intent.getExtras();
        if (action.equals("android.intent.action.ritu.keyword.name")) {
            long lon = bundle.getLong("navi_longitude");
            long lat = bundle.getLong("navi_latitude");
            String poiName = bundle.getString("navi_poiName");
            // ·���滮
            native_RoutePlan(lon, lat, poiName);
        }
    }

    public native void native_RoutePlan(long lon, long lat, String poiName);

    PowerManager pm;
    WakeLock wakeLock;

    /**
     * ������Ļ
     */
    public void screenOn() {
//        Log.i("RituNavi", "screenOn");
//        pm = (PowerManager) MyTestApplication.getInstance()
//                .getApplicationContext()
//                .getSystemService(Context.POWER_SERVICE);
//        wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP, "WakeAndLock");
//        wakeLock.acquire();
        ScreenControl screenControl = new ScreenControl();
        screenControl.turnOnBacklight();
        Log.i("RituNavi", "screenOn");
    }

    /**
     * Ϩ����Ļ
     */
    public void screenOff() {
//        Log.i("RituNavi", "screenOff");
//        pm = (PowerManager) MyTestApplication.getInstance()
//                .getApplicationContext()
//                .getSystemService(Context.POWER_SERVICE);
//        pm.goToSleep(SystemClock.uptimeMillis());
        ScreenControl screenControl = new ScreenControl();
        screenControl.turnOffBacklight();
        Log.i("RituNavi", "screenOff");
    }
    
    public void pauseMusic(Context context, int keyCode) {
        AudioManager audioManager = (AudioManager) context
                .getSystemService(Context.AUDIO_SERVICE);
        // ���жϺ�̨�Ƿ��ڲ�������
        if (audioManager.isMusicActive()) {
            KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
            Intent intent = new Intent(Intent.ACTION_MEDIA_BUTTON);
            intent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent);
            context.sendOrderedBroadcast(intent, null);

            keyEvent = new KeyEvent(KeyEvent.ACTION_UP, keyCode);
            intent = new Intent(Intent.ACTION_MEDIA_BUTTON);
            intent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent);
            context.sendOrderedBroadcast(intent, null);
            Log.i("RituNavi", "pausemusic");
        }
    }
    
    public String getPackageName(String name) {
        String packageName = "";
        try {
            XmlResourceParser parser = MyTestApplication.getInstance().getApplicationContext().getResources().getXml(
                    R.xml.launcher_config);
            int event = parser.getEventType();// ������һ���¼�
            while (event != XmlPullParser.END_DOCUMENT) {
                String appName = null;
                switch (event) {
                case XmlPullParser.START_TAG:// �жϵ�ǰ�¼��Ƿ��Ǳ�ǩԪ�ؿ�ʼ�¼�
                    Log.e("RituNavi", "parser.getName():"+parser.getName());
                    if ("name".equals(parser.getName())) {// �жϿ�ʼ��ǩԪ��
                        appName = parser.getAttributeValue(0);// �õ���ǩ������ֵ
                        Log.e("RituNavi", "appName0:"+appName);
                    }
                    if (appName != null && appName.equals(name)) {
                        if ("package".equals(parser.getName())) {
                            packageName = parser.getAttributeValue(0);
                            Log.e("RituNavi", "packageName:"+packageName);
                            return packageName;
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:// �жϵ�ǰ�¼��Ƿ��Ǳ�ǩԪ�ؽ����¼�
                    if ("app".equals(parser.getName())) {
                        appName = null;
                    }
                    break;
                }
                event = parser.next();// ������һ��Ԫ�ز�������Ӧ�¼�
            }// end while
            return packageName;
        }
        catch (Exception e) {
            e.printStackTrace();
            return packageName;
        }
    }
}
