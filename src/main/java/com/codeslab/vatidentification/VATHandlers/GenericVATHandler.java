package com.codeslab.vatidentification.VATHandlers;

/**
 *
 */
public class GenericVATHandler implements IVATHandler {

    private final String mRegex;

    public GenericVATHandler(String regex) {
        mRegex = regex;
    }

    @Override
    public boolean isVATIDValid(String vatID) {
        return vatID.matches(mRegex);
    }
}
