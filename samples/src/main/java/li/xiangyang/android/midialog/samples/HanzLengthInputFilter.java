package li.xiangyang.android.midialog.samples;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eraise on 2017/6/29.
 * 汉字长度算两位，其他字符算一位
 */
public class HanzLengthInputFilter implements InputFilter {
    private int mMax;

    public HanzLengthInputFilter(int max) {
        this.mMax = max;
    }

    @Override
    public CharSequence filter(CharSequence source, // 新输入的字符串
                               int start,   // 新输入的字符串起始下标，一般为0
                               int end, // 新输入的字符串终点下标，一般为source长度-1
                               Spanned dest,    // 输入之前文本框内容
                               int dstart,  // 原内容起始坐标，一般为0
                               int dend) {  // 原内容终点坐标，一般为dest长度-1
        // 新输入的字符串长度不应该超过最长字符串的长度
        int keep = mMax - (getDoubleHanzLength(dest) - (dend - dstart));
        if (keep <= 0) {
            return "";
        } else if (keep >= end - start) {
            return null; // keep original
        } else {
            keep += start;
            if (Character.isHighSurrogate(source.charAt(keep - 1))) {
                --keep;
                if (keep == start) {
                    return "";
                }
            }
            return source.subSequence(start, keep);
        }
    }

    /**
     * 获取字符串的长度，对双字符（包括汉字）按两位计数
     *
     * @param value
     * @return
     */
    private int getDoubleHanzLength(CharSequence value) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        Pattern pattern = Pattern.compile(chinese);
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            valueLength ++;
        }
        valueLength += value.length();
        return valueLength;
    }
}
