package pers.abaneo.web.utils.fileuploader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileValidteUtils {
	
	public static String getFileHeadName(InputStream inputStream) throws IOException{
		String head=getFileHead(inputStream);
		for(Map.Entry<String, String> ent:mFileTypes.entrySet()){
			if(head.startsWith(ent.getKey())){
				return ent.getValue();
			}
		}
		return null;
	}
	public static String getFileHead(InputStream inputStream) throws IOException{
		byte[] b = new byte[20];
		inputStream.read(b, 0, b.length);
		return bytesToHexString(b);
	}
	
	 // 缓存文件头信息-文件头信息
    public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();

    static {
    	// images 
    	mFileTypes.put("FFD8FFE0", "jpg"); 
    	mFileTypes.put("89504E47", "png"); 
    	mFileTypes.put("47494638", "gif"); 
    	mFileTypes.put("49492A00", "tif"); 
    	mFileTypes.put("424D", "bmp"); 
    	// PS和CAD 
    	mFileTypes.put("38425053", "psd"); 
    	mFileTypes.put("41433130", "dwg"); // CAD 
    	mFileTypes.put("252150532D41646F6265", "ps"); 
    	// 办公文档类 
    	mFileTypes.put("D0CF11E0", "doc"); // ppt、doc、xls 
    	mFileTypes.put("504B0304", "docx");// pptx、docx、xlsx 
    	/** 注意由于文本文档录入内容过多，则读取文件头时较为多变-START **/
    	mFileTypes.put("0D0A0D0A", "txt");// txt 
    	mFileTypes.put("0D0A2D2D", "txt");// txt 
    	mFileTypes.put("0D0AB4B4", "txt");// txt 
    	mFileTypes.put("B4B4BDA8", "txt");// 文件头部为汉字 
    	mFileTypes.put("73646673", "txt");// txt,文件头部为英文字母 
    	mFileTypes.put("32323232", "txt");// txt,文件头部内容为数字 
    	mFileTypes.put("0D0A09B4", "txt");// txt,文件头部内容为数字 
    	mFileTypes.put("3132330D", "txt");// txt,文件头部内容为数字 
    	/** 注意由于文本文档录入内容过多，则读取文件头时较为多变-END **/
    	mFileTypes.put("7B5C727466", "rtf"); // 日记本 
    	mFileTypes.put("255044462D312E", "pdf"); 
    	// 视频或音频类 
    	mFileTypes.put("3026B275", "wma"); 
    	mFileTypes.put("57415645", "wav"); 
    	mFileTypes.put("41564920", "avi"); 
    	mFileTypes.put("4D546864", "mid"); 
    	mFileTypes.put("2E524D46", "rm"); 
    	mFileTypes.put("000001BA", "mpg"); 
    	mFileTypes.put("000001B3", "mpg"); 
    	mFileTypes.put("6D6F6F76", "mov"); 
    	mFileTypes.put("3026B2758E66CF11", "asf"); 
    	// 压缩包 
    	mFileTypes.put("52617221", "rar"); 
    	mFileTypes.put("1F8B08", "gz"); 
    	// 程序文件 
    	mFileTypes.put("3C3F786D6C", "xml"); 
    	mFileTypes.put("68746D6C3E", "html"); 
    	mFileTypes.put("7061636B", "java"); 
    	mFileTypes.put("3C254020", "jsp"); 
    	mFileTypes.put("4D5A9000", "exe"); 
    	mFileTypes.put("44656C69766572792D646174653A", "eml"); // 邮件 
    	mFileTypes.put("5374616E64617264204A", "mdb");// Access数据库文件 
    	mFileTypes.put("46726F6D", "mht"); 
    	mFileTypes.put("4D494D45", "mhtml");
    }
	
	private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println(mFileTypes.get(getFileHead(new FileInputStream("C:\\Users\\ASUS\\Downloads\\TIM截图20171020163532.png")).substring(0, 8)));
		//504B03040A000000000017BE9A4A000000000000
		//504B03040A0000000000F98BFB3E000000000000
		//504B03041400000008006E6E524BAB65145F4504
		
	}
	
	
}
