package com.yzy.utils;

import java.util.Random;

public class UrlEncryptUtils {


    /*
     *功能：对url加密算法（只针对window.location.href跳转，不针对post表单提交及ajax方式）
     *算法：对于暴露在浏览器地址栏中的属性值进行加密，如一个属性为agentID=1，
     *     若对1加密后为k230101io934jksd32r4，说明如下：
     *     前三位为随机数；
     *     第四到第五位为要加密字符转换成16进制的位数，
     *       如：要加密字符为15转换成16进制为f，位数为1，则第四、五位为01；
     *     第六位标识要加密字符为何种字符，0：纯数字，1：字符
     *       若是字符和数字的混合，则不加密；
     *     从第七位开始为16进制转换后的字符（字母和非数字先转换成asc码）；
     *     若加密后的字符总位数不足20位，则用随机数补齐到20位，若超出20位，则不加随机数。
     *     即加密后总位数至少为20位。
     */
    public static String encryptValue (String str){
        //最终返回的加密后的字符串
        String encryptStr="";
        //产生3位随机数
        encryptStr+=produceRandom(3);
        String temp[]=encode16(str);//对要加密的字符转换成16进制
        int slh = temp[0].length();//转换后的字符长度
        String numLength = Integer.toHexString(slh);//字符长度换算成16进制
        if(numLength.length()==1){//如果是1，补一个0
            numLength="0"+numLength;
        }else if(numLength.length()>2){//转换后的16进制字符长度如果大于2位数，则返回，不支持
            return "";
        }
        encryptStr+=numLength;

        if(temp[1]=="0"){
            encryptStr+=0;
        }else if(temp[1]=="1"){
            encryptStr+=1;
        }

        encryptStr+=temp[0];

        if(encryptStr.length()<20){//如果小于20位，补上随机数
            String ran=produceRandom(20-encryptStr.length());
            encryptStr+=ran;
        }
        return encryptStr;
    }

    public static String produceRandom (int value){
        String num="";
        Random random = new Random();
        for(int i=0;i<value;i++){
            int i1 = random.nextInt(9)+1;
            num+=i1;
        }
        return num;
    }

    public static String[] encode16 (String str){
        char [] s = str.toCharArray();
        String temp="";
        for(int i=0;i<s.length;i++){
            temp += Integer.toString(s[i], 16);
        }
        if (!str.matches("[0-9]{1,}")){//非整数字符，对每一个字符都转换成16进制，然后拼接
            String arr[] = {temp,"1"};//1代表字符*/
            return arr;
        }else{//数字直接转换成16进制
            String arr[] = {temp,"0"};//0代表纯数字
            return arr;
        }
    }

    /*
     *解密为加密的逆过程
     */
    public static String decodeValue(String value){
        if(value.equals("")){
            throw new NullPointerException();
        }
        if(value.length()<20){
            throw new NullPointerException();
        }
        String charLength=value.substring(3, 5);//加密后的字符有多少位
        int charLen=Integer.parseInt(charLength,16);//转换成10进制
        int type=Integer.parseInt(value.substring(5, 6));//加密字符的类型（0：数字，1：字符串）
        String valueEnc=value.substring(6, 6+charLen);//16进制字符串
        if(type==0){
            int trueValue=Integer.parseInt(valueEnc,16);
            return String.valueOf(trueValue);
        }else{
            StringBuffer sb=new StringBuffer();
            String[] valueEncArray=valueEnc.split("");
            for(int i=0;i<valueEncArray.length;i+=2){
                int value10=Integer.parseInt(valueEncArray[i]+valueEncArray[i+1],16);//转换成10进制的asc码
                sb.append(String.valueOf((char)value10));//asc码转换成字符
            }
            return sb.toString();
        }
    }

}
