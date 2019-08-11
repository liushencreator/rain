package constant.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 日期格式枚举类
 *
 * @author raojing
 * @date 2019/8/8 23:28
 */
@AllArgsConstructor
public enum DateFormatEnum {

    //
    FORMAT_SYMBOL_BASE("yyyy-MM-dd", "2019-08-08"),

    //
    FORMAT_SYMBOL_EXTEND("yyyy-MM-dd HH:mm:ss", "2019-08-08 00:00:00"),

    //
    FORMAT_WORD_BASE("yyyy年MM月dd日", "2019年08月08日"),

    //
    FORMAT_WORD_EXTEND("yyyy年MM月dd日 HH时mm分ss秒", "2019年08月08日 00时00分00秒"),
    ;

    @Getter
    private String formatString;

    @Getter
    private String formatExample;

}
