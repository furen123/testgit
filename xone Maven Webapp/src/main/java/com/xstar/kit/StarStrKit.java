package com.xstar.kit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;


public class StarStrKit {
	/**
	 * 为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}

	/**
	 * 为空
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(final Object[] array) {
        return array == null || array.length == 0;
    }

	/**
	 * 不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 转大写
	 * @param instr
	 * @return
	 */
    public static String toUpperCase(String instr) {
        return instr == null ? instr : instr.toUpperCase();
    }
    
    /**
     * 转小写
     * @param instr
     * @return
     */
    public static String toLowerCase(String instr) {
        return instr == null ? instr : instr.toLowerCase();
    }
    

	/**
	 * 首字母大写 ,其余不变
	 * @param str
	 * @return
	 */
	public static String toUpperCaseFirst(String str) {
		if (str == null)
			return null;
		if (str.length() == 0)
			return str;
		String pre = String.valueOf(str.charAt(0));
		return str.replaceFirst(pre, pre.toUpperCase());
	}
	
	/**
	 * 首字母小写 ,其余不变
	 * @param str
	 * @return
	 */
	public static String toLowerCaseFirst(String str) {
		if (str == null)
			return null;
		if (str.length() == 0)
			return str;
		String pre = String.valueOf(str.charAt(0));
		return str.replaceFirst(pre, pre.toLowerCase());
	}
	
	/**
	 * 不会抛NullPointerException 的trim(), 传入null会返回null
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}
	
	/**
	 * 过滤 ;当instr==null时返回长度为0的""; <br>
	 * 与 nvl(...)系的区别在于只处理null ,不处理长度为0的"";
	 * 
	 * @param instr
	 * @return
	 */
	public static String nvl(String instr) {
		return nvl(instr, "");
	}

	/**
	 * 过滤 ,把null和长度为0的""当成同一种情况处理; <br>
	 * 当instr==null||"".equals(instr)时返回defaultValue ;其它情况返回 instr
	 * 
	 * @param instr
	 * @param defaultValue
	 * @return
	 */
	public static String nvl(String instr, String defaultValue) {
		return instr == null || "".equals(instr) ? defaultValue : instr;
	}
	
	/**
	 * 比较 str1 和 str2 如果都是 null 或者 str1.equals(str2) 返回 true 表示一样 ;
	 *
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2) {
		if (str1 == null && str2 == null)
			return true;
		if (str1 != null && str1.equals(str2))
			return true;
		return false;
	}

	public static String apadLeft(double a, int b, int len) {
		return apadLeft(String.valueOf(a), String.valueOf(b), len);
	}

	public static String apadRight(double a, int b, int len) {
		return apadRight(String.valueOf(a), String.valueOf(b), len);
	}

	public static String apadLeft(String str, String str2, int len) {
		if (str == null || str.length() == len || str2 == null)
			return str;
		if (str.length() > len)
			return str.substring(str.length() - len, len);
		return apadpro(str, str2, len, true);
	}

	public static String apadRight(String str, String str2, int len) {
		if (str == null || str.length() == len || str2 == null)
			return str;
		if (str.length() > len)
			return str.substring(0, len);
		return apadpro(str, str2, len, false);
	}

	private static String apadpro(String a, String b, int len, boolean appendleft) {
		int f = len - a.length();
		for (int i = 0; i < f; i++) {
			a = appendleft == true ? b + a : a + b;
		}
		return a;
	}

	/**
	 * 清除字符串中所有的空格 ,传入null返回null
	 * @param str
	 * @return
	 */
	public static String clear(String str) {
		return clear(str, " ");
	}

	/**
	 * 清除str中出现的所有str2字符序列 直到结果中再也找不出str2为止 str2 == null时 返回str
	 * @param str 原始字符串
	 * @param str2 清除的目标
	 * @return
	 */
	public static String clear(String str, String str2) {
		if (str == null)
			return str;
		if (str2 == null)
			return str;
		String reg = "(" + str2 + ")+";
		Pattern p = Pattern.compile(reg);
		while (p.matcher(str).find()) {
			str = str.replaceAll(reg, "");
		}
		return str;
	}

	/**
	 * 如果str的长度超过了c则取c-sub.length长度,然后拼上sub结尾
	 * @param str
	 * @param c
	 * @param sub
	 * @return
	 */
	public static String cutStr(String str, int c, String sub) {
		if (isEmpty(str))
			return str;
		if (str.length() <= c)
			return str;
		sub = nvl(sub);
		c = c - sub.length();
		c = c > str.length() ? 0 : c;
		str = str.substring(0, c);
		return str + sub;
	}

	/**
	 * 如果str的长度超过了length,取前length位然后拼上...
	 * @param str
	 * @param length
	 * @return
	 */
	public static String cutStr(String str, int length) {
		return cutStr(str, length, "…");
	}

	public static String replaceOnce(String text, String searchString, String replacement) {
		return replace(text, searchString, replacement, 1);
	}

	public static String replace(String text, String searchString, String replacement) {
		return replace(text, searchString, replacement, -1);
	}

	public static String replace(String text, String searchString, String replacement, int max) {
		if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0)
			return text;
		int start = 0;
		int end = text.indexOf(searchString, start);
		if (end == -1)
			return text;
		int replLength = searchString.length();
		int increase = replacement.length() - replLength;
		increase = increase >= 0 ? increase : 0;
		increase *= max >= 0 ? max <= 64 ? max : 64 : 16;
		StringBuffer buf = new StringBuffer(text.length() + increase);
		do {
			if (end == -1)
				break;
			buf.append(text.substring(start, end)).append(replacement);
			start = end + replLength;
			if (--max == 0)
				break;
			end = text.indexOf(searchString, start);
		} while (true);
		buf.append(text.substring(start));
		return buf.toString();
	}

	/**
	 * startWith
     *
	 * @param str
	 * @param key
	 * @return
	 */
	public boolean startWith(String str, String key) {
		if (isEmpty(str) || isEmpty(key))
			return false;
		return str.startsWith(key);
	}

	/**
	 * endWith
     *
	 * @param str
	 * @param key
	 * @return
	 */
	public boolean endWith(String str, String key) {
		if (isEmpty(str) || isEmpty(key))
			return false;
		return str.endsWith(key);
	}

	/**
	 * length
     *
	 * @param str
	 * @return
	 */
	public int length(String str) {
		if (isEmpty(str))
			return 0;
		return str.length();
	}

	public String subStringTo(String str, int start, int end) {
		if (isEmpty(str))
			return str;
		return str.substring(start, end);
	}

	public String subString(String str, int start) {
		if (isEmpty(str))
			return str;
		return str.substring(start);
	}

	public String[] split(String str, String key) {
		if (isEmpty(str) || isEmpty(key))
			return new String[] { str };
		return str.split(key);
	}

	public boolean contain(String str, String key) {
		if (isEmpty(str) || isEmpty(key))
			return false;
		return str.indexOf(key) != -1;
	}

	//字符串转日期
	public static Date parseDate(String key){
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(key);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
     * 获取32位随机字符串
     *
     * @return String
     */
    public static String getNonceStr() {
        Random random = new Random();
        return MD5Kit.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
    }

	/**
     * 获取当前时间 yyyyMMddHHmmss
     *
     * @return String
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

	/**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length
     *            int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

}
