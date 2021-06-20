package com.example.rs3_snailtent.EventBus;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class BusProvider {

    public static final Bus bus = new Bus(ThreadEnforcer.ANY);

    public static Bus getInstance(){

        return bus;
    }

    public BusProvider() {
    }
}
