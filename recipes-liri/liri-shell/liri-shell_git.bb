SUMMARY = "Responsive shell for Liri OS"
LICENSE = "LGPLv3 & GPLv3"
LIC_FILES_CHKSUM = " \
	file://LICENSE.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
	file://LICENSE.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
"

inherit liri pythonnative distro_features_check gsettings

REQUIRED_DISTRO_FEATURES = "wayland pam"

PV = "0.9.0+git${SRCPV}"
SRCREV = "f9953f259422d570df3175774e108c3976d8b137"
S = "${WORKDIR}/git"

DEPENDS += " \
    libpam \
    qtquickcontrols2 \
    qtsvg \
    liri-libliri \
    liri-qtaccountsservice \
    liri-qtgsettings \
    liri-eglfs \
    solid \
    polkit-qt-1 \
    pipewire \
"

EXTRA_OECMAKE += " \
    -DINSTALL_SYSCONFDIR=${sysconfdir} \
"

RDEPENDS_${PN} += " \
    qttools-tools \
    qtwayland-plugins \
    qtgraphicaleffects-qmlplugins \
"

FILES_${PN} += " \
    ${systemd_user_unitdir} \
    ${datadir}/wayland-sessions \
    ${datadir}/desktop-directories \
    ${OE_QMAKE_PATH_QML} \
"

RREPLACES_${PN} = "hawaii-shell"
RPROVIDES_${PN} = "hawaii-shell"
RCONFLICTS_${PN} = "hawaii-shell"
