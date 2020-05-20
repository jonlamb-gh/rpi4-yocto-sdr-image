# Raspberry Pi 4 Yocto SDR Image

Yocto on RPi4, used to run my various SDR projects.

## TODO

* Do the network recipe
* Recipe to setup custom `systemd` user level (or root) unit file
  - Runs the SDR stuff, either scripts + blade-cli, or my own thing in Rust
* Rust related
  - https://crates.io/crates/cargo-bitbake
  - https://pagefault.blog/2018/07/04/embedded-development-with-yocto-and-rust/
  - https://github.com/meta-rust/meta-rust
  - https://github.com/rust-embedded/meta-rust-bin

## Recipes & Customizations

* Custom `config.txt` and `cmdline.txt` in [bcm2835-bootfiles](meta-rpilinux/recipes-bsp/bootfiles/bcm2835-bootfiles.bbappend)
* [bladerf](meta-rpilinux/recipes-bladerf/bladerf/bladerf_2019.07.bb)

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

Files (TODO):

```bash
/card
├── config.txt
├── cmdline.txt
├── fixup4.dat
└── start4.elf
```

`config.txt`:

```bash
enable_uart=1
arm_64bit=1
kernel=kernel_rpilinux.img
dtoverlay=disable-bt
dtoverlay=disable-wifi
```

`cmdline.txt`:

```bash
TODO
```

## Links

- [BCM2711](https://www.raspberrypi.org/documentation/hardware/raspberrypi/bcm2711/README.md)
- [Datasheet](https://github.com/raspberrypi/documentation/raw/master/hardware/raspberrypi/bcm2711/rpi_DATA_2711_1p0.pdf)
- [rpi-firmware mirror](https://github.com/Hexxeh/rpi-firmware)
- [HackingRaspberryPi4WithYocto](https://lancesimms.com/RaspberryPi/HackingRaspberryPi4WithYocto_Introduction.html)
- [Raspberry-Pi-4-64bit-Systems-with-Yocto](https://jumpnowtek.com/rpi/Raspberry-Pi-4-64bit-Systems-with-Yocto.html)
- [BladeRF Git](https://github.com/Nuand/bladeRF)
