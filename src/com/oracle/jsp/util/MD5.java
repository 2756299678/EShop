package com.oracle.jsp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//MD5加密方式
//byte字节的意思
//周宝辉 2017 5 6
public class MD5 {
	//全局数组(不可更改)
	private final static String[]strDigit={
		"0" ,"1" ,"2" ,"3" ,"4" ,"5" ,"6" ,"7",
		"8" ,"9" ,"a" ,"b" ,"c" ,"d" ,"e" ,"f" 
	};
	//构造函数
	public MD5(){
		
	}
	
	//返回形式为字符串
	private static String byteToArrayString(byte bByte)
	{
		int iRet = bByte;
		//System.out.print("iRet="+iRet);
		if(iRet<0)
		{
			iRet += 256;
		}
		int iD1 = iRet /16 ;
		int iD2 = iRet %16 ;
		return strDigit[iD1]+strDigit[iD2];
	}
	
	//返回形式只为数字
	//被批注的代码元素内部的某些警告保持静默_(没有用到的)
	@SuppressWarnings("unused")
	private static String byteToNum(byte bByte)
	{
		int iRet = bByte;
		//测试函数
		//System.out.print("iRet1=" + iRet);
		if(iRet<0)
		{
			iRet +=256;
		}
		return String.valueOf(iRet);
	}
	
	//转换字节数组为16进制
	private static String byteToString(byte[] bByte)
	{
		StringBuffer  sBuffer=new StringBuffer();
		for(int i =0; i < bByte.length; i++){
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}
	
	public static String GetMD5Code(String strObj)
	{
		String resultString = null;
		try
		{
			resultString = new String(strObj);
			//为应用程序提供信息摘要算法的功能，它接收任意大小的数据，并输出固定长度的哈希值
			MessageDigest md = MessageDigest.getInstance("MD5");
			//md.digst()该函数返回值为存放哈希值结果的byte数组
			resultString = byteToString(md.digest(strObj.getBytes()));
		}
		catch(NoSuchAlgorithmException  ex)
		{
			ex.printStackTrace();
		}
		return resultString;
	}
	
}
