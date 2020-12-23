package com.djw.util;

import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.models.auth.In;
import net.bytebuddy.matcher.NullMatcher;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

class HttpClientUtilTest {

    @Test
    void testSendHttpPost() {
        System.out.println("成功！");
    }

    @Test
    void test() {
        int num = 707829217;
        int i = 1;
        while (i <= num) {
            i += 2;
            if (num % i == 0) {
                System.out.println("发现: " + num + " / " + i + " = " + (num / i));
            }
        }
    }
}