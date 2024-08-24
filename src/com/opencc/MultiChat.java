package com.opencc;

public class MultiChat {
    public static void main(String[] args) {
        Client c1 = new Client("127.0.0.1", "CatePillar");
        Client c2 = new Client("192.168.0.2", "Justin");

        ClientQueue queue = new ClientQueue();
        queue.addClientListener(new ClientListener() {
            @Override
            public void clientAdded(ClientEvent event) {
                System.out.printf("%s 从 %s 联机%n", event.getIP(), event.getName());
            }

            @Override
            public void clientRemoved(ClientEvent event) {
                System.out.printf("%s 从 %s 脱机%n", event.getIP(), event.getName());
            }
        });

        queue.add(c1);
        queue.add(c2);
        queue.remove(c1);
        queue.remove(c2);
    }
}
