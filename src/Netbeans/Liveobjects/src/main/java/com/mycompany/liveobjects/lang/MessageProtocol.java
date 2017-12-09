package com.mycompany.liveobjects.lang;

import java.util.List;

public interface MessageProtocol {
    String getSelector();
    List<String> getParameters();
}
