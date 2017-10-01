package com.anastasko.lnucompass.configuration;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class P6SpyLoggingFormatter implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        String query = (sql != null && sql.length()>0) ? sql : prepared;
        return "c-" + connectionId + ". " + query + "  {executed in " + elapsed + " ms}";
    }

}
