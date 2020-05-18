# Raspberry Pi 4 Yocto SDR Image

Yocto on RPi4, used to run my various SDR projects.

## TODO

* Configure to use `systemd` instead of `sysvinit`
  - https://www.yoctoproject.org/pipermail/yocto/2015-December/027881.html
  - done, just need to test it out
* Fixup the sd/boot files generated, per part7
  - https://lancesimms.com/RaspberryPi/HackingRaspberryPi4WithYocto_Part7.html
  - disable bluetooth in device tree overlay
  - extra stuff added to files in meta-rpilinux/recipes-bsp/bootfiles/bcm2835-bootfiles.bbappend
* Disable ipv6 in `cmdline.txt` or in the recipes
* Do the network and fstab recipes
  - add `meta-openembedded/meta-networking` to layers
* Recipe to build the bladeRF cli + libs
  - https://stackoverflow.com/questions/18382407/cmake-with-bitbake-recipe
  - http://bec-systems.com/site/1128/best-practices-for-using-cmake-in-openembeddedyocto
  - https://github.com/joaocfernandes/Learn-Yocto/blob/master/develop/Recipe-CMake.md
* Recipe to setup custom `systemd` user level (or root) unit file
  - Runs the SDR stuff, either scripts + blade-cli, or my own thing in Rust
* Rust related
  - https://crates.io/crates/cargo-bitbake
  - https://pagefault.blog/2018/07/04/embedded-development-with-yocto-and-rust/
  - https://github.com/meta-rust/meta-rust
  - https://github.com/rust-embedded/meta-rust-bin
* Setup + docs for u-boot
  - https://elinux.org/RPi_U-Boot
  - https://www.beyondlogic.org/compiling-u-boot-with-device-tree-support-for-the-raspberry-pi/
  - https://www.element14.com/community/community/designcenter/single-board-computers/riotboard/blog/2014/09/24/riotboard-yocto-part2-build-u-boot-using-yocto
  - http://variwiki.com/index.php?title=Yocto_Customizing_U-Boot
  - maybe build it in a recipe instead of out-of-band

## Dependencies

Using

```bash
sudo apt install gawk wget git-core diffstat unzip texinfo gcc-multilib build-essential chrpath socat libsdl1.2-dev xterm python
```

## U-Boot

Using U-Boot bootloader so I don't have to mess with the SD card as much.

```bash
git clone --depth 1 https://github.com/u-boot/u-boot.git u-boot
ARCH=arm64 CROSS_COMPILE=aarch64-linux-gnu- make rpi_4_defconfig
ARCH=arm64 CROSS_COMPILE=aarch64-linux-gnu- make
```

```bash
U-Boot 2020.04-rc2-00048-gf2a73d6867 (Feb 16 2020 - 08:29:41 -0800)

aarch64-linux-gnu-gcc (Ubuntu/Linaro 8.3.0-6ubuntu1) 8.3.0
GNU ld (GNU Binutils for Ubuntu) 2.32
```

Environment:

```bash
TODO
setenv ...
```

## Build

```bash
./setup

./build
```

### Clean and Re-run

```bash
bitbake -c cleanall rpilinux-image
bitbake rpilinux-image
```

## Deploy to SD Card

Find the image files:

```bash
bitbake -e rpilinux-image | grep ^DEPLOY_DIR_IMAGE
```

## SD Card Files

TODO - recipe(s) should set this stuff up, maybe just list what's required here?

changes for U-Boot things

Custom changes to `config.txt` and `cmdline.txt` happen in
the [meta-rpilinux recipe](meta-rpilinux/recipes-bsp/bootfiles/bcm2835-bootfiles.bbappend).

Files:

```bash
/card
├── config.txt
├── cmdline.txt
├── fixup4.dat
├── start4.elf
├── u-boot.bin
└── uboot.env
```

Things added/modified in `config.txt`:

```bash
enable_uart=1
arm_64bit=1
kernel=u-boot.bin
```

## Links

- [BCM2711](https://www.raspberrypi.org/documentation/hardware/raspberrypi/bcm2711/README.md)
- [Datasheet](https://github.com/raspberrypi/documentation/raw/master/hardware/raspberrypi/bcm2711/rpi_DATA_2711_1p0.pdf)
- [rpi-firmware mirror](https://github.com/Hexxeh/rpi-firmware)
- [HackingRaspberryPi4WithYocto](https://lancesimms.com/RaspberryPi/HackingRaspberryPi4WithYocto_Introduction.html)
- [Raspberry-Pi-4-64bit-Systems-with-Yocto](https://jumpnowtek.com/rpi/Raspberry-Pi-4-64bit-Systems-with-Yocto.html)
