import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	
	static String str = "[INFO] [2017-09-25 10:46:04][com.gionee.baserom.search.controller.RedirectController][客户端IP:117.150.234.218 - 重定向的URL：http://mini.eastday.com/a/170924195318293.html?qid=gouwudating02] - - Mozilla/5.0 (Linux; U; Android 5.1; zh-cn; F100 Build/IMM76D) AppleWebKit/534.30 (KHTML,like Gecko) Version/4.0 Chrome/50.0.0.0 Mobile Safari/534.30 Id/2D4FA7BED64BA70401E4F22314CFC7CB RV/5.0.16";

	public static void main(String[] args) {
		String pattern = ",?((\\w+?\\.)+?(com|net))";
        Pattern p = Pattern.compile(pattern);
        String line = str;
        Matcher m = p.matcher(line);

        if(m.find()){
            //匹配结果
            System.out.println(m.group());
        }else{
        	System.out.println("匹配失败");
        }
        //替换
        //System.out.println(line.replaceAll(pattern, ""));
	}
}
