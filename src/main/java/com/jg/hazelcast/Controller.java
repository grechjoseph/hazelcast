package com.jg.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IdGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    /**
     * With each call, this method creates a new ServerNode instance, ie: a new Hazelcast member.
     * This is followed by adding an entry in the Hazelcast Instance map with a value of "message" + (i+1).
     */
    @GetMapping("/start")
    public void start() {
        ServerNode serverNode = new ServerNode();
        HazelcastInstance hazelcastInstance = serverNode.getHazelcastInstance();
        Map<Long, String> map = hazelcastInstance.getMap("data");
        IdGenerator idGenerator = hazelcastInstance.getIdGenerator("newId");
        for (int i = 0; i < 10; i++) {
            map.put(idGenerator.newId(), "message" + (i + 1));
        }
    }

    /**
     * This method created a Hazelcast Client. Without being a member, this Client can retrieve the map shared across
     * the Hazelcast Cluster.
     */
    @GetMapping("/client")
    public void client() {
        ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");
        HazelcastInstance hzClient = HazelcastClient.newHazelcastClient(config);

        IMap<Long, String> map = hzClient.getMap("data");

        for (Map.Entry<Long, String> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

    }
}
