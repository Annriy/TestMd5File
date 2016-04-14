package com.example.enn.testmd5file.md;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class MD5 {
	public static String  getFileMd5(File file){
		try {
		StringBuffer sb =new StringBuffer();
		MessageDigest digest = MessageDigest.getInstance("md5");
		FileInputStream fin = new FileInputStream(file);
		int len = -1;
		byte[] buffer =new byte[1024];//�����������Ļ����С �ֽ�
		//������ļ�ȫ�����뵽��������
		while((len = fin.read(buffer))!= -1){
		digest.update(buffer,0,len);
		}
		//�Զ������ݽ��м���
		byte[] bytes = digest.digest();
		for (byte b : bytes) {
		// ��byte ����ת��Ϊ�޷�ŵ�����
		int n = b & 0XFF;
		// ������ת��Ϊ16����
		String s = Integer.toHexString(n);
		// ���16�����ַ���һλ����ôǰ�油0
		if(s.length() == 1){
		sb.append("0"+s);
		}else{
		sb.append(s);
		}
		}
		return sb.toString();
		} catch (Exception e) {
		e.printStackTrace();
		}
		return null;
		}
}
