require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL += "libstdc++ mtd-utils"
IMAGE_INSTALL += "openssh openssl openssh-sftp-server usbutils bladerf"
IMAGE_INSTALL += "libcrypto libssl rtl-sdr gpio-utils"
IMAGE_INSTALL += "smart-meter-detector"
IMAGE_INSTALL += "python3 netcat"
IMAGE_INSTALL += "vim"
