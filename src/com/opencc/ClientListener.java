package com.opencc;

public interface ClientListener {
    public abstract void clientAdded(ClientEvent event);
    public abstract void clientRemoved(ClientEvent event);
}
