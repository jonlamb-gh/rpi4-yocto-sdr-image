# Raspberry Pi 4 Yocto SDR Image

Yocto on RPi4, used to run my various SDR projects.

## TODO

* Disable bluetooth in device tree overlay or `config.txt`
* Do the network recipe
  - may need to add `meta-openembedded/meta-networking` to layers
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

## Dependencies

Using

```bash
sudo apt install gawk wget git-core diffstat unzip texinfo gcc-multilib build-essential chrpath socat libsdl1.2-dev xterm python
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

Custom changes to `config.txt` and `cmdline.txt` happen in
the [meta-rpilinux recipe](meta-rpilinux/recipes-bsp/bootfiles/bcm2835-bootfiles.bbappend).

Files:

```bash
/card
├── config.txt
├── cmdline.txt
├── fixup4.dat
└── start4.elf
```

Things added/modified in `config.txt`:

```bash
enable_uart=1
arm_64bit=1
kernel=kernel_rpilinux.img
```

## Links

- [BCM2711](https://www.raspberrypi.org/documentation/hardware/raspberrypi/bcm2711/README.md)
- [Datasheet](https://github.com/raspberrypi/documentation/raw/master/hardware/raspberrypi/bcm2711/rpi_DATA_2711_1p0.pdf)
- [rpi-firmware mirror](https://github.com/Hexxeh/rpi-firmware)
- [HackingRaspberryPi4WithYocto](https://lancesimms.com/RaspberryPi/HackingRaspberryPi4WithYocto_Introduction.html)
- [Raspberry-Pi-4-64bit-Systems-with-Yocto](https://jumpnowtek.com/rpi/Raspberry-Pi-4-64bit-Systems-with-Yocto.html)
