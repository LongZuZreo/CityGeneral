package com.example.citygeneral.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */

public class FindOneInfor {

    private int plateNo;
    private String plateName;
    private List<FindTwoInfor> list = new ArrayList<FindTwoInfor>();

    public FindOneInfor(int plateNo, String plateName, List<FindTwoInfor> list) {
        this.plateNo = plateNo;
        this.plateName = plateName;
        this.list = list;
    }

    public int getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(int plateNo) {
        this.plateNo = plateNo;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public List<FindTwoInfor> getList() {
        return list;
    }

    public void setList(List<FindTwoInfor> list) {
        this.list = list;
    }
}
