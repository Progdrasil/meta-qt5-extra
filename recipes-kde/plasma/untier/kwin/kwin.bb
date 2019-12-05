SUMMARY = "KDE's window manager"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c213a7de3f013310bd272cdb6eb7a24"

REQUIRED_DISTRO_FEATURES = "x11"

inherit kde-plasma features_check gettext

DEPENDS += " \
    libinput \
    qtsensors \
    fontconfig \
    \
    kauth-native \
    kconfig \
    kconfig-native \
    kconfigwidgets \
    kcoreaddons \
    kcoreaddons-native \
    kcrash \
    kglobalaccel \
    ki18n \
    kinit \
    knotifications \
    kpackage \
    kpackage-native \
    kservice \
    plasma-framework \
    kwidgetsaddons \
    kwindowsystem \
    kiconthemes \
    kidletime \
    kcompletion \
    kdeclarative \
    kcmutils \
    kio \
    knewstuff \
    kxmlgui \
    kdecoration \
    kscreenlocker \
    sonnet-native \
"

# this condition matches always currently - it is kept in this way as a marker
DEPENDS += " \
	${@bb.utils.contains("DISTRO_FEATURES", "x11", "virtual/libx11 qtx11extras libepoxy xcb-util-cursor", "",d)} \
"

# REVISIT: PACKAGECONFIG for optionals
DEPENDS += " \
    kactivities \
    kdoctools \
    kdoctools-native \
"

PV = "${PLASMA_VERSION}"
SRC_URI[md5sum] = "a32ce47f33af05ddd29d55be9b4fab7c"
SRC_URI[sha256sum] = "fba18f7d8a3f97a1852313ebc9ba65094957fda8deefc09c27fd0a97207b2513"

SRC_URI += " \
    file://0001-Make-building-of-QPA-wayland-plugin-an-option.patch \
    file://0002-add-xcb-namespace-to-effects-cpp.patch \
"

# pure X11 environments fail to build wayland plugin
EXTRA_OECMAKE += "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '-DKWIN_BUILD_QPA=ON', '-DKWIN_BUILD_QPA=OFF', d)}"

# kwin check libepoxy only -> egl pkgconfig is skipped
CXXFLAGS_append_mx6 += " -DLINUX=1"

FILES_${PN} += " \
    ${datadir}/config.kcfg \
    ${datadir}/dbus-1 \
    ${datadir}/kconf_update \
    ${datadir}/k*5 \
    ${datadir}/knsrcfiles \
    ${datadir}/kpackage \
    ${datadir}/icons \
    ${OE_QMAKE_PATH_PLUGINS} \
    ${OE_QMAKE_PATH_QML} \
    ${libdir}/kconf_update_bin \
"

# taken from bitbake.conf with lucky modification: all lt libraries end with s.so
# only move them to -dev package
FILES_SOLIBSDEV = "${base_libdir}/lib*${SOLIBSDEV} ${libdir}/lib*s${SOLIBSDEV}"
FILES_${PN} += " \
    ${libdir}/*.so \
"

RDEPENDS_${PN} += " \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11 wayland", "xserver-xorg-xwayland", "",d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "qtwayland", "",d)} \
    qtmultimedia \
    qtvirtualkeyboard-qmlplugins \
"
