package com.danssion.java.baseType.string;

public class StringSubSplit {


    public static void main(String[] args) {
        String str = new String("good");
        char[] ch = {'a','b','c'};
        StringSubSplit test = new StringSubSplit();

        test.change(str,ch);
        System.out.println(str+"  and");
        System.out.println(ch);

//        test.checkChinese();
//        findStr();

//        stringSplit();
//        splitStr();

//        subStr();
        findInJsonStr();
    }

    public static void subStr() {
        String str1 = " abc defg";

        String subStr1 = str1.trim().substring(0,str1.indexOf(" "));
        System.out.println("str1 -> "+str1+" | sub->"+subStr1);

        String str2 = "btc/usdt";
        String subStr2 = str2.substring(0,str2.indexOf("/"));
        System.out.println("str2 -> "+str2+" | sub->"+subStr2);

    }

    public static void splitStr() {
        String str = "1231234123411";
        String[] ss = str.split("_");

//        for (String s : ss) {
//           System.out.println(s);
//        }
        System.out.println(ss[0]);

//        String str1 = "sdfsdf&&sdfsdfsdf&sdfsdfsdf&&111112222";
        String str1 = "sdfsdf111112222";

        String[] ss1 = str1.split("&&");
        System.out.println(ss1[0]);
    }
    public static void findStr() {
        String [] strArr = {"sdbds@163.com","sssddd@126.com","117433502@qq.com","abcdef@vip.163.com"
                ,"aabbcc@vvip.126.com","13330560585@qq.126.com"};
        for (String str : strArr) {
            if(str.substring(str.indexOf("@")).contains("126") || str.substring(str.indexOf("@")).contains("163")) {
                System.out.println(str.substring(str.indexOf("@")));
            }
        }
    }

    public static void findInJsonStr() {
        String jsonStr = "a:23:{s:2:\"id\";i:4990725;s:4:\"udid\";s:36:\"418f297e-76c3-4729-b429-c6f73908c792\";" +
                "s:7:\"user_id\";i:4990725;s:5:\"token\";s:32:\"123412a99f3190f7a25b645ca892a615\";s:8:\"platform\";" +
                "s:6:\"web_pc\";s:10:\"is_deleted\";i:0;s:10:\"created_at\";i:1592020150;s:10:\"updated_at\";" +
                "i:1592020150;s:9:\"nick_name\";s:22:\"pyf7562228267u@163.com\";s:6:\"avatar\";N;s:6:\"mobile\";" +
                "s:0:\"\";s:5:\"email\";s:22:\"pyf7562228267u@163.com\";s:15:\"email_confirmed\";i:1;s:6:\"gender\";" +
                "i:1;s:8:\"birthday\";N;s:8:\"language\";s:5:\"zh_cn\";s:7:\"enabled\";i:1;" +
                "s:13:\"alarm_enabled\";i:0;s:10:\"risk_score\";i:0;s:11:\"register_ip\";s:0:\"\";" +
                "s:6:\"kyc_id\";i:0;s:13:\"kyc_confirmed\";i:0;s:16:\"mobile_confirmed\";i:0;}";


        System.out.println("4990725 index: "+jsonStr.indexOf("4990725"));

    }

    public void change(String str,char ch[]) {
        str = "test ok";
        ch[0] = 'g';
    }

    public void checkChinese() {
        char a = 'a';
        char b = '好';
        String astr = String.valueOf(a);
        String bstr = String.valueOf(b);
        System.out.println("char length:"+astr.length()+" bytes length："+astr.getBytes().length);
        System.out.println("char length:"+bstr.length()+" bytes length："+bstr.getBytes().length);
    }

    public static void stringSplit() {
        String a = "_2019_11|50000|99";
        String arr[] = a.split("\\|");

        for (String str:arr) {
            System.out.println(str);
        }

        System.out.println(arr);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }
}
