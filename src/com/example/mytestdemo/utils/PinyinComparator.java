package com.example.mytestdemo.utils;

import java.util.Comparator;
import cn.ritu.bluephone.bean.BtContact;

public class PinyinComparator implements Comparator<BtContact> {

	public int compare(BtContact o1, BtContact o2) {
		if (o1.getNameInital().equals("@")
				|| o2.getNameInital().equals("#")) {
			return -1;
		} else if (o1.getNameInital().equals("#")
				|| o2.getNameInital().equals("@")) {
			return 1;
		} else {
			return o1.getNameInital().compareTo(o2.getNameInital());
		}
	}

}
