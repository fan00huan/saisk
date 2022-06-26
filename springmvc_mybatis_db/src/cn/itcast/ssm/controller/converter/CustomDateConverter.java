package cn.itcast.ssm.controller.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

import cn.itcast.ssm.common.core.MyUtils;

/**
 *
 * <p>Title: CustomDateConverter</p>
 * <p>Description:日期转换器 </p>
 * <p>Company: www.itcast.com</p>
 * @author	传智.燕青
 * @date	2015-4-13下午5:49:14
 * @version 1.0
 */
public class CustomDateConverter implements Converter<String,Date>{

	protected Logger log = Logger.getLogger(CustomDateConverter.class);

	@Override
	public Date convert(String source) {

		Date date = null;

		//实现 将日期串转成日期类型(格式是yyyy-MM-dd HH:mm:ss)
		for (Entry<String,SimpleDateFormat> entry : MyUtils.getDateFormatMap().entrySet()) {

			try {
				//转成直接返回
				log.debug("from:["+source + "]"+"to["+"java.util.Date" + "]"+"by[" +entry.getKey()+ "]");
				date = entry.getValue().parse(source);
				if (date!=null) {
					return date;
				}

			} catch (Exception e) {
			}
		}

		//如果参数绑定失败返回null
		return null;
	}

}
