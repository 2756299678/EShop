package com.oracle.jsp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ������
 * һ��goon������ķ������Ǿ�̬�ķ���
 * @author BaoHui
 *
 */
public class DateUtil {
	/**
	 * ��ȡ����
	 * @return
	 */
	public static String getDate(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	/**
	 * ��ȡ����
	 * @return
	 */
	public static String getDateStr(){
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	/**
	 * ��ȡʱ��
	 * @return
	 */
	public static String getTimeStr(){
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		return sdf.format(date);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}