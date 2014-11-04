package com.hangman

import org.hibernate.dialect.H2Dialect;

/**
 * Bug in Security plugin for H2 database
 * Code copied from http://stackoverflow.com/questions/23872473/grails-spring-security-plugin-rc3-shows-error-about-table-creation-in-developme
 */
public class ImprovedH2Dialect extends H2Dialect {
    @Override
    public String getDropSequenceString(String sequenceName) {
        return "drop sequence if exists " + sequenceName;
    }

    @Override
    public boolean dropConstraints() {
        return false;
    }
}