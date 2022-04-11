package org.prgms.kdt.kdtspringorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ansi.AnsiOutput;

public class LogTester {

//    1. 패키지 이름: 각 패키지의 클래스마다 로그 레벨을 다르게 하여 원하는 정보를 불러 들일 수 있다.
//    private static final Logger logger = LoggerFactory.getLogger("org.prgms.kdt.kdtspringorder.LogTester");

    // 2. class
    private static final Logger logger = LoggerFactory.getLogger(LogTester.class);
    //private final Logger logger = LoggerFactory.getLogger(this.class);

    public static void main(String[] args) {
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
        logger.warn("logger name => {}", logger.getName());
    }
}
