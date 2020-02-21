package com.weiliai.lod.optimize;

import com.weiliai.lod.simple.Boss;
import com.weiliai.lod.simple.TeamLeader;

/**
 * @Author: Doug Li
 * @Date 2020/2/21
 * @Describe: 符合迪米特法则
 */
public class Test {

    public static void main(String[] args) {
        Boss boss = new Boss();
        TeamLeader teamLeader = new TeamLeader();
        boss.commandCheckNumber(teamLeader);
    }
}
