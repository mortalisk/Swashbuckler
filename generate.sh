#!/bin/bash
sh lib/jaxb-ri-20110115/bin/xjc.sh -d src/main/generated/ -p no.mamot.swashbuckler.xml data/level.xsd
