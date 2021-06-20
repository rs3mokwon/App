package com.example.rs3_snailtent.EventBus;

public class BusEvent {
    public static class Bus{
        private String eventnum;

        public Bus(String eventnum){

            this.eventnum = eventnum;
        }
        public String getEventnum() {

            return eventnum;
        }
    }
}
