package com.mycompany.liveobjects.lang;

import java.util.List;

public class SyntaxErrorException extends Exception {
    private List<String> formattedSyntaxErrors;

    public SyntaxErrorException(List<String> formattedSyntaxErrors) {
        this.formattedSyntaxErrors = formattedSyntaxErrors;
    }

    public List<String> getFormattedSyntaxErrors() {
        return formattedSyntaxErrors;
    }
}
