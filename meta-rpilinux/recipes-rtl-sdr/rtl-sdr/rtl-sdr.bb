DESCRIPTION = "RTL-SDR"
SECTION = "apps"
HOMEPAGE = "https://osmocom.org/projects/rtl-sdr/wiki"
DEPENDS = "git libusb"

LICENSE = "CLOSED"

#LIC_FILES_CHKSUM = "file://LICENSE;md5=96af5705d6f64a88e035781ef00e98a8"

SRC_URI = "gitsm://github.com/osmocom/rtl-sdr.git;protocol=https;branch=master"
SRCREV="ed0317e6a58c098874ac58b769cf2e609c18d9a5"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

#EXTRA_OECMAKE = ""
