package com.jg.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.NetworkConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    public HazelcastConfig() {
        Config config = new Config();
        NetworkConfig network = config.getNetworkConfig();
        network.setPort(5701).setPortCount(20);
        network.setPortAutoIncrement(true);
        JoinConfig join = network.getJoin();
        join.getMulticastConfig().setEnabled(false);
        join.getTcpIpConfig()
                .addMember("machine1")
                .addMember("localhost").setEnabled(true);

        ManagementCenterConfig manCenterCfg = new ManagementCenterConfig();
        manCenterCfg.setEnabled(true).setUrl("http://localhost:8080/mancenter");
    }

}
