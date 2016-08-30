package sun.cat.utils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 这是工具类
 * @author Sun Hailong
 * @version 1.0
 */
public class Util {

	/**
	 * 把Date类型转换成String类型
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 把String类型转换成Date类型
	 * @param date
	 * @return
	 */
	public static Date stringToDate(String date){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = format.parse(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 用来去掉List中空值和相同项的。
	 * @param list
	 * @return
	 */
	public static List<String> removeSameItem(List<String> list) {
		List<String> difList = new ArrayList<String>();
		for (String t : list) {
			if (t != null && !difList.contains(t)) {
				difList.add(t);
			}
		}
		return difList;
	}
	/**
	 * 返回不带"-"的UUID
	 * @author Sun Hailong
	 * @return UUID不带 "-"
	 * @version 1.0
	 */
	public static String getUUID32(){
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24);
	}
	/**
	 * 返回带"-"的UUID
	 * @author Sun Hailong
	 * @return UUID带 "-"
	 * @version 1.0
	 */
	public static String getUUID36(){
		return UUID.randomUUID().toString();
	}

	/**
	 * 验证邮箱地址是否正确
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		boolean flag = false;
		try{
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		}catch(Exception e){
			flag = false;
		}

		return flag;
	}
	/**
	 * 验证手机号码
	 * @param mobiles
	 * @return  [0-9]{5,9}
	 */
	public static boolean isMobileNO(String mobiles){
		boolean flag = false;
		try{
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	/**
	 * 验证是否为数字
	 * @param number
	 * @return
	 */
	public static boolean isNum(String number){
		boolean flag = false;
		try{
			Pattern p = Pattern.compile("^[0-9]*$");
			Matcher m = p.matcher(number);
			flag = m.matches();
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

//	/**
//	 * 拆分字符串 
//	 * @param pString 要拆分的数据
//	 * @param pSign 拆分标志
//	 * @return
//	 */
//	public static Object[] splitString(String pString , String pSign){
//		switch (pSign) {
//		case " ":
//			return pString.split(" +");
//		case ".":
//			return pString.split("\\.");
//		case "\\":
//			return pString.split("\\\\");
//		}
//		return pString.split(pSign);
//
//	}

	/**
	 * 中文
	 *
	 * @return
	 */
	public static String encodStr(String str) {
		try {
			return new String(str.getBytes("iso-8859-1"), "utf-8");
		} catch (Exception e) {
			return str;
		}
	}

	/**
	 * 中文
	 *
	 * @return
	 */
	public static String encodStr(String str, String encoding) {
		try {
			return new String(str.getBytes("iso-8859-1"), encoding);
		} catch (Exception e) {
			return str;
		}
	}
	/**
	 * 通过request 获取当前IP
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 生成验证码图片
	 * @param text
	 * @return
	 */
	public static BufferedImage createImage(String text) {
		int width = 100, height = 30;//设置矩形的长宽
		//创建BufferedImage.用来存储验证码图片
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		//图形类，用来画图
		Graphics g = image.getGraphics();//得到image上的画布
		Random random = new Random();//随机数的类
		g.setColor(Color.white);//设置画笔颜色
		g.fillRect(0, 0, width, height);//填充矩形
		g.setFont(new Font("Times New Roman", Font.PLAIN, 26));//设置画笔的样式
		g.setColor(Color.pink); //设置画笔颜色
		//生成15条干扰线
		for (int i = 0; i < 15; i++) {
			int x = random.nextInt(width);//生成要绘制椭圆的左上角的 x 坐标,小于矩形宽度
			int y = random.nextInt(height);//要绘制椭圆的左上角的 y 坐标,高度小于矩形高度
			int xl = random.nextInt(12); //要绘制椭圆的宽度。
			int yl = random.nextInt(12);//要绘制椭圆的宽度。
			g.drawOval(x, y, x + xl, y + yl);  //生成的干扰线为椭圆
		}
		for (int i = 0; i < text.length(); i++) {
			//设置画笔颜色，也是随机生成
			g.setColor(new Color(20 + random.nextInt(110),
					20 + random.nextInt(110), 20 + random.nextInt(110)));
			//在image上画出一个随机数
			g.drawString(String.valueOf(text.charAt(i)), 25 * i + 6, 25);
		}
		g.dispose();//释放此图形的上下文以及它使用的所有系统资源。
		return image;
	}
	/**
	 * 提供精确的小数位四舍五入处理。
	 *
	 * @param v     需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 将一个整数字符串分解成一个整数数组 例： num=123; --> numArray[3]{1,2,3}
	 *
	 * @param num
	 * @return
	 */
	public static int[] strToIntArray(String num) {
		String[] strArray = num.trim().split("");
		int[] numArray = new int[strArray.length - 1];
		for (int i = 1; i < strArray.length; i++) {
			numArray[i - 1] = Integer.parseInt(strArray[i]);
		}
		return numArray;
	}

	/**
	 * 格式化一个数字字符串为9，999，999.99的格式,如果字符串无法格式化返回0.00
	 *
	 * @param money 数字字符串
	 * @return 格式化好的数字字符串
	 */
	public static String formatMoney(double money) {
		String formatMoney = "0.00";
		try {
			DecimalFormat myformat3 = new DecimalFormat();
			myformat3.applyPattern(",##0.00");
			formatMoney = myformat3.format(money);
		} catch (Exception ex) {
		}
		return formatMoney;
	}

	/**
	 * 格式化一个数字字符串为9，999，999.99的格式,如果字符串无法格式化返回0.00
	 *
	 * @param money 数字字符串
	 * @return 格式化好的数字字符串
	 */
	public static String formatMoney(String money) {
		String formatMoney = "0.00";
		try {
			DecimalFormat myformat3 = new DecimalFormat();
			myformat3.applyPattern(",##0.00");
			double n = Double.parseDouble(money);
			formatMoney = myformat3.format(n);
		} catch (Exception ex) {
		}
		return formatMoney;
	}

	/**
	 * 人民币转成大写
	 *
	 * @param value
	 * @return String
	 */
	public static String hangeToBig(double value) {
		char[] hunit = {'拾', '佰', '仟'}; // 段内位置表示
		char[] vunit = {'万', '亿'}; // 段名表示
		char[] digit = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'}; // 数字表示
		long midVal = (long) (value * 100); // 转化成整形
		String valStr = String.valueOf(midVal); // 转化成字符串

		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
		String rail = valStr.substring(valStr.length() - 2); // 取小数部分

		String prefix = ""; // 整数部分转化的结果
		String suffix = ""; // 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) { // 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4; // 取段内位置
			int vidx = (chDig.length - i - 1) / 4; // 取段位置
			if (chDig[i] == '0') { // 如果当前字符是0
				zeroSerNum++; // 连续0次数递增
				if (zero == '0') { // 标志
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0; // 连续0次数清零
			if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // 转化该数字表示
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
			}
		}

		if (prefix.length() > 0)
			prefix += '圆'; // 如果整数部分存在,则有圆的字样
		return prefix + suffix; // 返回正确表示
	}

	/**
	 * 判断输入的字符串是否为纯汉字
	 *
	 * @param str 传入的字符串
	 * @return 如果是纯汉字返回true, 否则返回false
	 */
	public static boolean isChinese(String str) {
		if (null == str)
			return false;
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 是否为空白,包括null和""
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 是否为空白,包括null和""
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(List str) {
		return str == null || str.size() == 0;
	}

	/**
	 * 奖字符转成16进制
	 *
	 * @return
	 */
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = "0000" + Integer.toHexString(ch);
			str = str + s4.substring(s4.length() - 4) + " ";
		}
		return str;
	}
	
	/**
	 *  系统计时
	 * @return
	 */
	public static long jieshi(){
		return System.currentTimeMillis();
	}

	//获的系统当前时间

	/**
	 * 获取系统当前时间
	 * @return
	 */
	public static String getNewDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
	/**
	 * 创建UUID编号32位
	 * @return
	 */
	public static String getUUID(){
		String s = UUID.randomUUID().toString();
		//去掉“-”符号
		return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
	}

	/**
	 * 去掉分支结点hang的条件标志位
	 * @param pSatisfy
	 * @return
	 */
	public static String getSatisfy(String pSatisfy){
		return  pSatisfy.substring(1, pSatisfy.length()-1);

	}

}
