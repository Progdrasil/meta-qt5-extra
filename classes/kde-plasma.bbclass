inherit kde-base

PLASMA_VERSION = "5.17.3"

SRC_URI = "${KDE_MIRROR}/stable/plasma/${PLASMA_VERSION}/${BPN}-${PV}.tar.xz"

DEPENDS += " \
    kwayland \
"
