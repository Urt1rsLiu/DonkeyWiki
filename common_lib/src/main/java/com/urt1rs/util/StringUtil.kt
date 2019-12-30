package com.urt1rs.util

import java.util.regex.Pattern

class StringUtil {

    companion object {

        private val IPV4_REGEX: String = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$"

        //无全0块无压缩，标准IPv6地址的正则表达式
        private val IPV6_STD_REGEX = "^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$"

        //非边界压缩正则表达式
        //private val IPV6_COMPRESS_REGEX = "^((?:[0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4})*)?)::((?:([0-9A-Fa-f]{1,4}:)*[0-9A-Fa-f]{1,4})?)$"

        //边界压缩情况正则表达式
        //private val IPV6_COMPRESS_REGEX_BORDER = "^(::(?:[0-9A-Fa-f]{1,4})(?::[0-9A-Fa-f]{1,4}){5})|((?:[0-9A-Fa-f]{1,4})(?::[0-9A-Fa-f]{1,4}){5}::)$"

        fun isValidIpAddress(str: String):Boolean {
            return isValidIpv4Address(str) || isValidIpv6Address(str)
        }

        fun isValidIpv4Address(str: String): Boolean {
            val pattern = Pattern.compile(IPV4_REGEX)
            return pattern.matcher(str).matches()
        }

        fun isValidIpv6Address(str: String): Boolean {
            val pattern = Pattern.compile(IPV6_STD_REGEX)
            return pattern.matcher(str).matches()
        }
    }
}