inherit cargo

SUMMARY = "Smart Meter Detector"
HOMEPAGE = "https://github.com/jonlamb-gh/rtl-sdr-rust-workspace"
LICENSE = "MIT"
DEPENDS = "rtl-sdr"

SRC_URI = "gitsm://github.com/jonlamb-gh/rtl-sdr-rust-workspace.git;protocol=https;branch=master"
SRCREV="a404f2862559559ce2a6ba57d55370905a837dae"
S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://LICENSE;md5=e3c9e47460390dd5e89276db680e97b9"
