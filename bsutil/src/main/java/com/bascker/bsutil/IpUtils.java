package com.bascker.bsutil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpUtils.class);

    private static final String SEP_IPV6 = "::/";
    private static final String SEP_COLON = ":";
    private static final int HEX = 16;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int BINARY = 2;

    /**
     * 判断 ipv6 地址是否在给定的 ipv6 cidr 内
     * @param ipv6Cidr
     * @param ipv6
     * @return
     */
    public static boolean isContains(final String ipv6Cidr, final String ipv6) {
        // TODO: 参数合法性校验
        final String[] cidrArr = ipv6Cidr.split(SEP_IPV6);
        // ::/128 的处理
        if (StringUtils.isEmpty(cidrArr[ZERO])) {
            return true;
        }

        final int cidrBitNum = Integer.parseInt(cidrArr[ONE]);
        if ((cidrBitNum & (cidrBitNum - ONE)) == ZERO) {
            return true;
        }

        // 1. 根据掩码位数，计算掩码表示。高位 FFFFFFF 的个数 + 剩余 1 的个数
        final int cidrFNum = Integer.parseInt(cidrArr[ONE]) / HEX;
        final int cidrOneNum = Integer.parseInt(cidrArr[ONE]) % HEX;

        // 2. 获取到 cidr ip表示部分最后一个 16 位
        final String[] cidrSections = StringUtils.split(cidrArr[ZERO], SEP_COLON);
        if (cidrSections.length < cidrFNum) {
            return true;
        }

        final String cidrLastSec = cidrSections[cidrFNum];
        String cidrLastSecBinary = Long.toBinaryString(Long.parseLong(cidrLastSec, HEX));
        final int length = cidrLastSecBinary.length();
        for (int i = ZERO; i < HEX - length; i ++) {
            cidrLastSecBinary = (ZERO + cidrLastSecBinary);
        }
        LOGGER.debug("cidrLastSec, 2进制: {}, 10进制: {}, 16进制: {}", cidrLastSecBinary, cidrLastSec, Long.parseLong(cidrLastSec, HEX));

        // 3. 计算 cidr 网络前缀非 FFFFF 部分的 2 进制表示
        final StringBuilder sb = new StringBuilder();
        for (int i = ZERO; i < HEX; i ++) {
            if (i < cidrOneNum) {
                sb.append(ONE);
            } else {
                sb.append(ZERO);
            }
        }
        final String cidrPrefix = sb.toString();
        LOGGER.debug("cidrPrefix, 2进制: {}, 10进制: {}, 16进制: {}", cidrPrefix, Long.parseLong(cidrPrefix, BINARY), Long.toHexString(Long.parseLong(cidrPrefix, BINARY)));

        // 4. 计算 cidr 表示中最小范围值
        final Long min = Long.parseLong(cidrPrefix, BINARY) & Long.parseLong(cidrLastSec, HEX);
        LOGGER.info("min, 2进制: {}, 10进制: {}, 16进制: {}", Long.toBinaryString(min),  min, Long.toHexString(min));

        // 5. 计算 cidr 表示中最大范围值
        String s = cidrLastSecBinary.substring(ZERO, cidrOneNum);
        for (int i = ZERO; i < HEX - cidrOneNum; i ++) {
            s += ONE;
        }
        final long max = Long.parseLong(s, BINARY);
        LOGGER.info("max, 2进制: {}, 10进制: {}, 16进制: {}", Long.toBinaryString(max), max, Long.toHexString(max));

        // 6. 获取 ipv6 跟 cidr 非 FFFF 部分的区间值的 10 进制表示
        final String[] ipSec = StringUtils.split(ipv6, SEP_COLON);
        final Long ipLastSec = Long.parseLong(ipSec[cidrFNum], HEX);
        LOGGER.debug("ipLastSec, 2进制: {}, 10进制: {}, 16进制: {}", Long.toBinaryString(ipLastSec), ipLastSec, Long.toHexString(ipLastSec));

        return ipLastSec >= min && ipLastSec <= max;
    }

}
