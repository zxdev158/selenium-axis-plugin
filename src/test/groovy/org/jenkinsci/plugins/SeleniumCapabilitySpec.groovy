package org.jenkinsci.plugins

import org.jenkinsci.plugins.selenium.Manual
import spock.lang.Specification

class SeleniumCapabilitySpec extends Specification {

    def 'Create Param'() {

        def selCap = new Manual('Browser', 'Platform', 'Version', 'SEL')

        expect:
        selCap.browserName.matches('Browser')
        selCap.browserVersion.matches('Version')
        selCap.platformName.matches('Platform')
    }

    def 'Create No Param'() {

        def selCap = new Manual()

        expect:
        selCap.browserName.matches('Any')
        selCap.browserVersion.matches('Any')
        selCap.platformName.matches('Any')
    }
}
