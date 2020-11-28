package com.ihi.cope.springbootstartercope.model;

public enum Mode {
    RESTFUL,
    PUBSUB;

    public String toString() {
        return this.name();
    }
}
