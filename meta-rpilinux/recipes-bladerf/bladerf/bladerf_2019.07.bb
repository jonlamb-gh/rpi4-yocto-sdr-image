DESCRIPTION = "BladeRF"
SECTION = "apps"
HOMEPAGE = "https://www.nuand.com/"
DEPENDS = "git libusb"

#FPGA HDL: MIT
#FX3 Firmware: MIT
#libbladeRF: LGPLv2.1
#bladeRF-cli: GPLv2
#Linux kernel driver: GPLv2
LICENSE = "CLOSED"

#LIC_FILES_CHKSUM = "file://LICENSE;md5=96af5705d6f64a88e035781ef00e98a8"

SRC_URI = "gitsm://github.com/Nuand/bladeRF.git;protocol=https;tag=2019.07"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

#EXTRA_OECMAKE = ""
