package com.xiaoniucr.string;

public class VersionTest {


    public static void main(String[] args) {


        String nextVersion = getNextVersion("AA.2");
        System.out.println(nextVersion);



    }


    /**
     * i.组成：【1位字母】+【.】+【1位数字】，例子：A.2
     * ii.初始值为A.0。
     * iii.通过系统新增，上传，编制申请等获得初始版本号
     * iv.每次经过文控修订流程后数字自增1
     * v.数字增加至5后，字母位按字母表顺序自增1
     * i)字母表增加完成后，增加一位字母按字幕表顺序自增。
     * ii)例子：A.5的下一个修订的文件版本号为B.1；Z.5的下一个修订版本号为AA.0
     * @param v
     * @return
     */
    public static String getNextVersion(String v){

        String[] str = v.split("\\.");
        String chars = str[0];
        int num = Integer.parseInt(str[1]);
        if(num < 5){
            num ++;
            return chars + "." + num;
        }else{
            char c = chars.charAt(chars.length()-1);
            if(c == 'Z'){
                chars = String.format("%"+(chars.length()+1)+"s", "").replace(' ', 'A')+"."+1;
            }else{
                chars = String.format("%"+(chars.length())+"s", "").replace(' ',(char)(c+1))+"."+1;
            }
            return chars;
        }
    }

}
