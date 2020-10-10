inherit cargo

SUMMARY = "Smart Meter Detector"
HOMEPAGE = "https://github.com/jonlamb-gh/rtl-sdr-rust-workspace"
LICENSE = "MIT"
DEPENDS = "rtl-sdr"

SRC_URI = "gitsm://github.com/jonlamb-gh/rtl-sdr-rust-workspace.git;protocol=https;branch=master"
SRCREV="1fb2d2cec2a176d83ec054bc3d29258446eefecd"
S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://LICENSE;md5=e3c9e47460390dd5e89276db680e97b9"
