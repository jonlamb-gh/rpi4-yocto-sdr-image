# Raspberry Pi 4 Yocto SDR Image

Yocto on RPi4, used to run my various SDR projects.

## TODO

* Do the network recipe
* `meta-rpilinux/recipes-bladerf` should copy over FPGA file, wget on build
  - `~/.config/Nuand/bladeRF/hostedxA4.rbf`
  - `/usr/share/Nuand/bladeRF/hostedxA4.rbf`
  - https://github.com/Nuand/bladeRF/wiki/FPGA-Autoloading#linux-or-osx
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

## Live View with baudline

Host side:

```bash
mkfifo /tmp/rx_samples.bin

baudline -reset -format le16 -channels 2 -quadrature -samplerate 2000000 -stdin < /tmp/rx_samples.bin
```

BladeRF commands:

```bash
set frequency rx 89.5M
set samplerate 2M
set bandwidth 1.5M
rx config file=/tmp/rx_samples.bin n=0
rx start

# Adjust parameters while viewing the spectrum in baudline
rx stop
```

## Open Binary Samples File with baudline

```bash
bladeRF-cli -s scripts/rx_log.txt

file /dev/shm/samples_2msps.bin
```

```bash
# TODO - header error, open manually works
baudline -reset -play -format le16 -channels 2 -quadrature -samplerate 2000000 /dev/shm/samples_2msps.bin
```

baudline:

1. Open baudline
1. Right-click and select Input -> Open File
1. Change File Format to raw
1. Select your file and click Open
1. Set the following parameters in the raw parameters dialog:
   - Decompression: OFF
   - Initial byte offset: 0
   - Sample Rate: Sample rate you recorded the samples at (2000000)
   - Channel: 2, quadrature
   - Decode Format: 16 bit linear, little endian

## Dropped Samples Check

```bash
bladeRF-cli -s bladerf_scripts/fpga_counter_samples.txt

./tools/check-samples /dev/shm/samples_4msps.bin
```

Expect to see:

```text
Number of gaps:0
```

## GUIs and Tools

* [GQRX](https://gqrx.dk/)
  - Related bladeRF [wiki page](https://github.com/Nuand/bladeRF/wiki/Getting-Started%3A-Linux#build-and-install-gqrx)
* [sdrangel](https://github.com/f4exb/sdrangel)
* [SigDigger](https://batchdrake.github.io/SigDigger/)
* [QSpectrumAnalyzer](https://pypi.org/project/QSpectrumAnalyzer/)
* [baudline](http://baudline.com/index.html)
  - Live view [wiki page](https://github.com/Nuand/bladeRF/wiki/bladeRF-CLI-Tips-and-Tricks#live-viewing-of-samples)
  - Can open the binary sample format natively
* [sdr-heatmap](https://crates.io/crates/sdr-heatmap)
* [rtl-power](http://kmkeen.com/rtl-power/2014-10-18-14-49-57-361.html)

## Links

* [BCM2711](https://www.raspberrypi.org/documentation/hardware/raspberrypi/bcm2711/README.md)
* [Datasheet](https://github.com/raspberrypi/documentation/raw/master/hardware/raspberrypi/bcm2711/rpi_DATA_2711_1p0.pdf)
* [rpi-firmware mirror](https://github.com/Hexxeh/rpi-firmware)
* [HackingRaspberryPi4WithYocto](https://lancesimms.com/RaspberryPi/HackingRaspberryPi4WithYocto_Introduction.html)
* [Raspberry-Pi-4-64bit-Systems-with-Yocto](https://jumpnowtek.com/rpi/Raspberry-Pi-4-64bit-Systems-with-Yocto.html)
* [BladeRF Git](https://github.com/Nuand/bladeRF)
* [BladeRF Getting Started](https://github.com/Nuand/bladeRF/wiki/Getting-Started%3A-Verifying-Basic-Device-Operation)
* [BladeRF Calibration](https://github.com/Nuand/bladeRF/wiki/DC-offset-and-IQ-Imbalance-Correction#dc-auto-calibration-for-a-single-frequency-and-gain)
* [BladeRF Scripting](https://github.com/Nuand/bladeRF/wiki/bladeRF-CLI-Tips-and-Tricks#using-bladerf-cli-in-shell-scripts)
* [BladeRF Achievable Sample Rates](https://github.com/Nuand/bladeRF/wiki/Debugging-dropped-samples-and-identifying-achievable-sample-rates)
* [libbladeRF API Docs](https://www.nuand.com/libbladeRF-doc)
