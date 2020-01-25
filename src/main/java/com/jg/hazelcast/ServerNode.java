package com.jg.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.Data;

@Data
public class ServerNode {

    private HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();

    public HazelcastInstance getHazelcastInstance(){
        return hzInstance;
    }

}
