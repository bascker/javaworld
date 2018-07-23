package com.bascker.base;

import com.bascker.bsutil.CollectionHelper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式 Sample
 *
 * 1.正则: 主要包括 3 个类, Pattern, Matcher, PatternSyntaxException
 *
 * 2.Pattern
 *  2.1 一个正则表达式的编译表示, 使用 {@link Pattern#compile(String)} 创建
 *  2.2 Pattern.compile(regex): 生成一个 pattern 对象
 *  2.3 Pattern.matches(regex, input): 编译 regex, 并判断 input 是否符合 regex 的格式。等价于 matcher.matches()
 *  2.4 matcher(input): 返回一个 matcher 对象
 *
 * 3.Matcher
 *  3.1 对输入字符串进行解释和匹配操作的引擎, 使用 {@link Pattern#matcher(CharSequence)} 创建
 *  3.2 matches(): 将整个输入串与正则表达式匹配
 *  3.3 find(): 在输入串中查找与该模式匹配的下一子序列
 *  3.4 group(): 获取分组值
 *
 * 4.命名分组(捕获)
 *  4.1 即对分组命名，通过名称来捕获所需结果
 *  4.2 jdk7+ 支持命名分组
 *  4.3 语法: (?<NAME>REGEX)
 *      1) 以元组 () 包裹，以 NAME 表示分组的名字，REGEX 表示该分组的正则式
 *      2) 命名不能重复，不能以数字开头
 *
 * @author bascker
 */
public class RegexCases {

    private static final Logger LOG = LoggerFactory.getLogger(RegexCases.class);

    /**
     * 基础使用
     */
    @Test
    public void base() {
        final String regex = "\\d{2}";
        final String input = "22-33-44-aa-b1";
        LOG.info("input 是否符合 \\d{2} 的表达式：" + Pattern.matches(regex, input));
        LOG.info("input 是否符合 \\d{2} 的表达式：" + Pattern.matches(regex, "22"));


        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(input);
        LOG.info("input 是否符合 \\d{2} 的表达式：" + matcher.matches());
        matcher.reset();                            // 重置 matcher 的游标到 0，否则 22 不会被输出
        final List<Integer> rs = new ArrayList<>();
        while (matcher.find()) {
            rs.add(Integer.valueOf(matcher.group()));
        }
        LOG.info("所有符合 \\d{2} 的值: " + CollectionHelper.toString(rs));
    }

    /**
     * 命名分组
     */
    @Test
    public void namedGroup() {
        // {n,m} 表示前一个字符匹配至少 n 次，至多 m 次。不能在 n,m 之间加入空格，即 {n, m} 这种是错误的，编译无法通过
        String regex = "(?<uId>U\\d{3})-(?<uName>\\w+)-(?<uAge>\\d{1,3})";
        final String input = "U001-johnnie-21";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            LOG.info("uID = " + matcher.group("uId"));
            LOG.info("uName = " + matcher.group("uName"));
            LOG.info("uAge = " + matcher.group("uAge"));
        }

        // 复杂的正则式: 若是子正则表达式中又包含子正则式，则使用 () 包裹。如 time 分组
        regex = "(?<userId>U\\d+)\\s"
                + "(?<year>[1-9]\\d{3})-"
                + "(?<month>0[1-9]|1[0-2])-"
                + "(?<day>0[1-9]|[1-2][0-9]|3[0-1])\\s"
                + "(?<time>(09|1[0-9]|2[0-2]):0{2}~(09|1[0-9]|2[0-2]):0{2})\\s"
                + "(?<roomName>[ABCD])\\s?"
                + "(?<cancel>C?)";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher("U002 2017-08-01 19:00~22:00 A");
        if (matcher.find()) {
            LOG.info("uID: " + matcher.group("userId"));
            LOG.info("year: " + matcher.group("year"));
            LOG.info("month: " + matcher.group("month"));
            LOG.info("day: " + matcher.group("day"));
            LOG.info("time: " + matcher.group("time"));
            LOG.info("roomName: " + matcher.group("roomName"));
            LOG.info("cancel: " + (!"C".equals(matcher.group("cancel")) ? false : true));
        }
    }

}
