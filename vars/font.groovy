/**
 * Helper methods to set font foreground and/or background color in Jenkins pipeline.
 * Based on ANSI codes from https://en.wikipedia.org/wiki/ANSI_escape_code
 * Depends on ansicolor jenkins plugin https://plugins.jenkins.io/ansicolor/
 */

def mysh(cmd) {
    sh('#!/usr/bin/env -S sh -e\n' + cmd)
}

// Foreground color
def setFgBlack() {
    mysh("echo '\033[30m'")
}

def setFgRed() {
    mysh("echo '\033[31m'")
}

def setFgGreen() {
    mysh("echo '\033[32m'")
}

def setFgYellow() {
    mysh("echo '\033[33m'")
}

def setFgBlue() {
    mysh("echo '\033[34m'")
}

def setFgMagenta() {
    mysh("echo '\033[35m'")
}

def setFgCyan() {
    mysh("echo '\033[36m'")
}

def setFgWhite() {
    mysh("echo '\033[37m'")
}

// Background color
def setBgBlack() {
    mysh("echo '\033[40m'")
}

def setBgRed() {
    mysh("echo '\033[41m'")
}

def setBgGreen() {
    mysh("echo '\033[42m'")
}

def setBgYellow() {
    mysh("echo '\033[43m'")
}

def setBgBlue() {
    mysh("echo '\033[44m'")
}

def setBgMagenta() {
    mysh("echo '\033[45m'")
}

def setBgCyan() {
    mysh("echo '\033[46m'")
}

def setBgWhite() {
    mysh("echo '\033[47m'")
}

// Font effects
def setBold() {
    mysh("echo '\033[1m'")
}

def setItalic() {
    mysh("echo '\033[3m'")
}

def setUnderline() {
    mysh("echo '\033[4m'")
}

def setCrossed() {
    mysh("echo '\033[9m'")
}

// Normal color
def resetColor() {
    mysh("echo '\033[0m'")
}
