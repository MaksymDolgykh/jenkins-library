/**
 * Helper methods to make logging more readable in the jenkins console output.
 * Based on ANSI codes from https://en.wikipedia.org/wiki/ANSI_escape_code
 * Depends on ansicolor jenkins plugin https://plugins.jenkins.io/ansicolor/
 */

def getColors() {
    NORMAL_COLOR='\033[0m'
    bgWhiteFgMagentaBoldItalic='\33[1;3;35m'
    bgYellowFgBlackBoldItalic='\33[1;3;43m'
    bgRedFgWhiteBoldItalic='\33[1;3;37;41m'
    bgWhiteFgBlueBoldItalic='\33[1;3;34m'
    bgGreenFgBlackBoldItalic='\33[1;3;42m'
    bgGreenFgWhiteBoldItalic='\33[1;3;37;42m'
    bgRedFgBlackBoldItalic='\33[1;3;41m'
}

def mysh(cmd) {
    sh('#!/usr/bin/env -S sh -e\n' + cmd)
}

def info(message) {
    getColors()
    mysh("echo '${bgWhiteFgMagentaBoldItalic}'[INFO] - ${message} '${NORMAL_COLOR}'")
}

def warn(message) {
    getColors()
    mysh("echo '${bgYellowFgBlackBoldItalic}'[WARN] - ${message} '${NORMAL_COLOR}'")
}

def error(message) {
    getColors()
    mysh("echo '${bgRedFgWhiteBoldItalic}'[ERROR] - ${message} '${NORMAL_COLOR}'")
}

def debug(message) {
    getColors()
    mysh("echo '${bgWhiteFgBlueBoldItalic}'[DEBUG] - ${message} '${NORMAL_COLOR}'")
}

def success(message) {
    getColors()
    mysh("echo '${bgGreenFgWhiteBoldItalic}'[SUCCESS] - ${message} '${NORMAL_COLOR}'")
}

def fail(message) {
    getColors()
    mysh("echo '${bgRedFgWhiteBoldItalic}'[FAIL] - ${message} '${NORMAL_COLOR}'")
}

