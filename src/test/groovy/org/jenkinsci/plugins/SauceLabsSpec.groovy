package org.jenkinsci.plugins

import org.jenkinsci.plugins.hub.Selenium
import org.jenkinsci.plugins.selenium.Manual
import org.jenkinsci.plugins.saucelabs.CapabilityReader
import org.jenkinsci.plugins.selenium.Exception
import spock.lang.Specification

class SauceLabsSpec extends Specification {

    def 'Available'() {
        CapabilityReader.metaClass.rawRead = { String s -> this.class.getResource(s).text }

        when:
        def reader = new CapabilityReader()
        reader.loadCapabilities('/saucelabs_full.json')
        def sel = new Selenium(reader, Manual)

        then:
        assert sel.seleniumCapabilities.size() == 330
        assert sel.seleniumLatest.size() == 39
        assert sel.seleniumSelected.size() == 28
    }

    def 'No Connection'() {
        CapabilityReader.metaClass.rawRead = { String s -> this.class.getResource(s).text }

        when:
        def reader = new CapabilityReader()
        reader.loadCapabilities('/saucelabs_noaccess.json')
        new Selenium(reader, Manual)

        then:
        thrown(Exception)
    }

}
