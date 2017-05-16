package com.example.citygeneral.utils;

import android.content.Context;
import android.content.SharedPreferences;



import org.json.JSONObject;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class PeizhiParserTools {
    /**
     * 配置数据解析
     */
    public static void parserResultPeizhi(Context context, String result) {
        SharedPreferences sp = context.getSharedPreferences("cla",
                context.MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.clear();
        e.commit();
        if (result != null) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.getString("ServerInfo") != null) {
                    JSONObject info = jsonObject.getJSONObject("ServerInfo");
                    CityApp.MAINFRAGMENT = info.getInt("HomeType");
                    String chan = info.getString("Channel");
                    String one = chan.split(",")[0];
                    String two = chan.split(",")[1];
                    String three = chan.split(",")[2];
                    CityApp.MAINMENU1 = Integer.parseInt(one);
                    CityApp.MAINMENU2 = Integer.parseInt(two);
                    CityApp.MAINMENU3 = Integer.parseInt(three);
                    CityApp.TGISOPEN = Integer.parseInt(info
                            .getString("TgIsOpen"));

                    ChangeTypeTools tools = new ChangeTypeTools();
                    int type1 = tools.parseInt(info.getString("ChannelHomeType").split(",")[0]);
                    int type2 = tools.parseInt(info.getString("ChannelHomeType").split(",")[1]);
                    int type3 = tools.parseInt(info.getString("ChannelHomeType").split(",")[2]);
                    int Watermark1 = tools.parseInt(info.getString("Watermark").split("\\|")[0]);
                    int Watermark2 = tools.parseInt(info.getString("Watermark").split("\\|")[1]);
                    int Watermark3 = tools.parseInt(info.getString("Watermark").split("\\|")[2]);
                    int Watermark4 = tools.parseInt(info.getString("Watermark").split("\\|")[3]);
                    int Watermark5 = tools.parseInt(info.getString("Watermark").split("\\|")[4]);


                    e.putInt("MAINFRAGMENT", info.getInt("HomeType"));
                    e.putInt("MAINMENU1", Integer.parseInt(one));
                    e.putInt("MAINMENU2", Integer.parseInt(two));
                    e.putInt("MAINMENU3", Integer.parseInt(three));
                    e.putInt("TGISOPEN",
                            Integer.parseInt(info.getString("TgIsOpen")));


                    e.putInt("type1", type1);
                    e.putInt("type2", type2);
                    e.putInt("type3", type3);
                    e.putInt("Watermark1", Watermark1);
                    e.putInt("Watermark2", Watermark2);
                    e.putInt("Watermark3", Watermark3);
                    e.putInt("Watermark4", Watermark4);
                    e.putInt("Watermark5", Watermark5);

                    e.putInt("alltype1", tools.parseInt(info.getString("AllChannelHomeType").split(",")[0]));
                    e.putInt("alltype2", tools.parseInt(info.getString("AllChannelHomeType").split(",")[1]));
                    e.putInt("alltype3", tools.parseInt(info.getString("AllChannelHomeType").split(",")[2]));
                    e.putInt("alltype4", tools.parseInt(info.getString("AllChannelHomeType").split(",")[3]));
                    e.putInt("alltype5", tools.parseInt(info.getString("AllChannelHomeType").split(",")[4]));
                    e.putInt("alltype6", tools.parseInt(info.getString("AllChannelHomeType").split(",")[5]));
                    e.putInt("alltype7", tools.parseInt(info.getString("AllChannelHomeType").split(",")[6]));
                    e.putInt("alltype8", tools.parseInt(info.getString("AllChannelHomeType").split(",")[7]));
                    e.putInt("alltype9", tools.parseInt(info.getString("AllChannelHomeType").split(",")[8]));
                    e.putInt("alltype10", tools.parseInt(info.getString("AllChannelHomeType").split(",")[9]));
                    e.putInt("alltype11", tools.parseInt(info.getString("AllChannelHomeType").split(",")[10]));
                    if (jsonObject.getString("Extend") != null && !jsonObject.getString("Extend").equals("null")) {
                        JSONObject extendObj = jsonObject.getJSONObject("Extend");
                        e.putInt("FindType", tools.parseInt(extendObj.getString("FindType")));
                    }


                    String[] IndexPubConfig = info.getString("IndexPubConfig").split("\\|");
                    String[] IndexPubConfigLeft = IndexPubConfig[0].split(",");
                    System.out.println("标记："+IndexPubConfigLeft.toString());
                    if (IndexPubConfigLeft.length >= 1) {
                        e.putString("index1", IndexPubConfigLeft[0]);
                        if (IndexPubConfigLeft.length >= 2) {
                            e.putString("index2", IndexPubConfigLeft[1]);
                        }
                    }
                    e.putString("index4", IndexPubConfig[1]);


                    e.commit();

                }
            } catch (Exception ee) {
                ee.printStackTrace();
                e.commit();
            }
        }
    }

}
