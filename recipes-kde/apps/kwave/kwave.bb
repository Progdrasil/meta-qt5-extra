SUMMARY = "Kwave is a sound editor for KDE"
LICENSE = "GPLv2 & LGPLv2 & CC-BY-SA-3.0 & CC-BY-SA-4.0 & GFDL-1.2 & BSD"
LIC_FILES_CHKSUM = " \
    file://LICENSES;md5=a5262554ba5698535ed8c962a5248dff \
    file://GNU-LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"

inherit kde-apps gtk-icon-cache

DEPENDS += "\
    audiofile \
    fftw \
    libopus \
    libsamplerate0 \
    librsvg-native \
    \
    kcompletion \
    kconfig-native \
    kauth-native \
    kconfigwidgets \
    kcoreaddons-native \
    kcrash \
    kdbusaddons \
    kdoctools-native \
    sonnet-native \
    ki18n \
    kiconthemes \
    kio \
    kservice \
    ktextwidgets \
    kxmlgui \
    kwidgetsaddons \
"

PV = "${KDE_APP_VERSION}"
SRC_URI[md5sum] = "deb593947ca90bc2deab8ab3d3d5a487"
SRC_URI[sha256sum] = "bde8d2c3805b52bdcf0371c28e870d20fdfdd8b69831a9ba4e59ee9ae0a863ef"
SRC_URI += "file://0001-FIND_REQUIRED_PROGRAM-is-broken-use-the-tools-direct.patch"

# Aagh: To select a soundcard we need to select another type - so keep oss in as dummy
#EXTRA_OECMAKE += "-DWITH_OSS=OFF"

PACKAGECONFIG ??= "alsa mp3"
PACKAGECONFIG[alsa] = "-DWITH_ALSA=ON,-DWITH_ALSA=OFF,alsa-lib"
PACKAGECONFIG[flac] = "-DWITH_FLAC=ON,-DWITH_FLAC=OFF,flac"
PACKAGECONFIG[mp3] = "-DWITH_MP3=ON,-DWITH_MP3=OFF,id3lib libmad"
PACKAGECONFIG[pulseaudio] = "-DWITH_PULSEAUDIO=ON,-DWITH_PULSEAUDIO=OFF,pulseaudio,pulseaudio-server"
PACKAGECONFIG[qtmultimedia] = "-DWITH_QT_AUDIO=ON,-DWITH_QT_AUDIO=OFF,qtmultimedia"

FILES_${PN} += " \
    ${datadir}/kservicetypes5 \
    ${OE_QMAKE_PATH_PLUGINS} \
"
